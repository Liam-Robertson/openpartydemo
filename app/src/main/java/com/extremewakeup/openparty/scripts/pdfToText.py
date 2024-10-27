import os
import re
import logging
import pdfplumber
import openai

# Retrieve API key from environment variable
openai.api_key = os.getenv("OPENAI_API_KEY")

# Setup logging
logging.basicConfig(
    level=logging.INFO,
    format='%(asctime)s - %(levelname)s - %(message)s',
    handlers=[logging.StreamHandler()]
)

# Function to list available models using the correct API method
def list_available_models():
    try:
        # Correct method to list models
        response = openai.Model.list()
        print("Available Models:")
        for model in response['data']:
            print(model['id'])
    except Exception as e:
        logging.error(f"Error retrieving models: {e}")

# Function to extract text from a PDF using pdfplumber
def extract_text_from_pdf(pdf_path):
    try:
        with pdfplumber.open(pdf_path) as pdf:
            text = ''
            for page_number, page in enumerate(pdf.pages, start=1):
                try:
                    page_text = page.extract_text() or ''  # Handle None case
                    text += page_text
                except Exception as e:
                    logging.error(f"Error extracting text from page {page_number} of {pdf_path}: {e}")
                    continue  # Skip problematic page
        return text if text else None  # Return None if no text was extracted
    except Exception as e:
        logging.error(f"Error opening or reading {pdf_path}: {e}")
        return None

# Function to summarize text using GPT-4
def summarize_text_with_gpt(text, page_number):
    chunk_size = 3000
    prompt_header = f"Summarize the following text from Page {page_number}:\n"

    try:
        logging.info(f"Text length for Page {page_number}: {len(text)}")
        text_chunks = [text[i:i + chunk_size] for i in range(0, len(text), chunk_size)]
        summary = ""

        for idx, chunk in enumerate(text_chunks):
            prompt = f"{prompt_header}{chunk}"

            # Correct method to call chat-based completions
            response = openai.ChatCompletion.create(
                model="gpt-4",
                messages=[
                    {"role": "system", "content": "You are a helpful assistant."},
                    {"role": "user", "content": prompt}
                ],
                max_tokens=1000,
                temperature=0.7
            )

            # Correct response extraction
            chunk_summary = response['choices'][0]['message']['content']
            summary += f"\nChunk {idx + 1} summary: {chunk_summary}\n"

        return summary
    except Exception as e:
        logging.error(f"Error during GPT-4 API call for Page {page_number}: {e}")
        return f"Error summarizing text for Page {page_number}."

# Function to extract the page number from the filename
def extract_page_number_from_filename(filename):
    match = re.search(r'Page_(\d+)', filename)
    if match:
        return int(match.group(1))  # Convert to integer for numerical sorting
    else:
        logging.warning(f"Could not extract page number from {filename}")
        return float('inf')  # Put files without a page number at the end

# Main function to process all PDFs in a folder and summarize them
def summarize_pdfs_in_folder(input_folder):
    script_directory = os.path.dirname(os.path.abspath(__file__))

    # Define the output summary folder
    output_folder = os.path.join(script_directory, 'outputPdfSummaries')

    # Create output folder if it doesn't exist
    if not os.path.exists(output_folder):
        os.makedirs(output_folder)
        logging.info(f"Created output folder at {output_folder}")

    output_summary_file = os.path.join(output_folder, 'pdf_summaries.txt')

    # Get all the PDF files and sort them numerically by page number
    pdf_files = [f for f in os.listdir(input_folder) if f.endswith('.pdf')]
    if not pdf_files:
        logging.warning(f"No PDF files found in {input_folder}")
        return

    # Sort by page number
    pdf_files.sort(key=extract_page_number_from_filename)

    # Open (or create) the output file to write summaries
    with open(output_summary_file, 'w', encoding='utf-8') as outfile:
        logging.info(f"Opened output summary file: {output_summary_file}")

        for filename in pdf_files:
            pdf_path = os.path.join(input_folder, filename)
            logging.info(f"Processing {pdf_path}...")

            try:
                # Extract text from the PDF
                pdf_text = extract_text_from_pdf(pdf_path)
                if not pdf_text:
                    logging.error(f"Failed to extract text for {filename}")
                    continue

                # Extract the page number from the filename
                page_number = extract_page_number_from_filename(filename)

                # Summarize the text using GPT-4
                summary = summarize_text_with_gpt(pdf_text, page_number)

                # Write the summary to the output file
                outfile.write(f"Summary of {filename}:\n")
                outfile.write(summary + "\n\n")
                logging.info(f"Summary of {filename} written to {output_summary_file}")

            except Exception as e:
                logging.error(f"Error processing {pdf_path}: {e}")

    logging.info("Summarization of all files completed.")

# Define input folder containing your PDF files
input_folder = 'rawPdfOutput'

# First, list available models
list_available_models()

# Then, run the summarization process
summarize_pdfs_in_folder(input_folder)
