import os
import re
import logging
import pdfplumber
from openai import OpenAI

client = OpenAI()

# Setup logging
logging.basicConfig(
    level=logging.INFO,
    format='%(asctime)s - %(levelname)s - %(message)s',
    handlers=[logging.StreamHandler()]
)

# Initialize logger
logger = logging.getLogger(__name__)

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
                    logger.error(f"Error extracting text from page {page_number} of {pdf_path}: {e}")
                    continue  # Skip problematic page
        return text if text else None  # Return None if no text was extracted
    except Exception as e:
        logger.error(f"Error opening or reading {pdf_path}: {e}")
        return None

# Function to summarize text using GPT-4o
def summarize_text_with_gpt(text, page_number):
    prompt_header = f"Page {page_number}:\n"

    prompt = f"""
    Don't cite your sources

    With the following text, do this:
    - Give it a title which should be this - Page {page_number}
    - A detailed summary of the text
    - All of the statistics in the text. The statistics are the most important part, don't miss any statistics

    For the statistics, I want them to be very detailed. If 1/10 is an extremely low level of detail, and 10/10 is an unbelievably high level of detail, I want the level of detail for the statistics you give me to be a 10/10
    """

    logger.info(f"Summarizing text for Page {page_number}")
    logger.debug(f"Text to summarize:\n{text}")

    try:
        logger.info("Sending request to GPT-4o model")
        completion = client.chat.completions.create(
            model="gpt-4o",
            messages=[
                {"role": "system", "content": "You are a helpful assistant."},
                {"role": "user", "content": prompt_header + prompt + text}
            ]
        )

        logger.debug(f"Raw completion response: {completion}")
        result = completion.choices[0].message
        logger.info("Successfully retrieved completion")
        return result
    except Exception as e:
        logger.error(f"Error during GPT completion request: {e}")
        raise

# Function to extract the page number from the filename
def extract_page_number_from_filename(filename):
    match = re.search(r'Page_(\d+)', filename)
    if match:
        return int(match.group(1))  # Convert to integer for numerical sorting
    else:
        logger.warning(f"Could not extract page number from {filename}")
        return float('inf')  # Put files without a page number at the end

# Function to remove the page number from the filename
def remove_page_number(filename):
    return re.sub(r'Page_\d+\s*-\s*', '', filename)

# Main function to process all PDFs in the nested folder structure
def summarize_pdfs_in_folders(input_root_folder):
    script_directory = os.path.dirname(os.path.abspath(__file__))

    # Define the output summary root folder
    output_root_folder = os.path.join(script_directory, 'outputPdfSummaries')

    # Create output folder if it doesn't exist
    if not os.path.exists(output_root_folder):
        os.makedirs(output_root_folder)
        logger.info(f"Created output folder at {output_root_folder}")

    # Traverse each folder inside the root input folder (rawPdfOutput)
    for subdir, _, files in os.walk(input_root_folder):
        # Only process folders containing PDF files
        pdf_files = [f for f in files if f.endswith('.pdf')]
        if not pdf_files:
            continue  # Skip directories that don't contain PDF files

        # Get the folder name relative to the input root folder
        relative_folder_name = os.path.relpath(subdir, input_root_folder)

        # Create a corresponding folder in the output root folder
        output_folder = os.path.join(output_root_folder, relative_folder_name)
        if not os.path.exists(output_folder):
            os.makedirs(output_folder)
            logger.info(f"Created output folder: {output_folder}")

        # Loop through each PDF file in the current subdir
        for filename in pdf_files:
            pdf_path = os.path.join(subdir, filename)
            logger.info(f"Processing {pdf_path}...")

            try:
                # Extract text from the PDF
                pdf_text = extract_text_from_pdf(pdf_path)
                if not pdf_text:
                    logger.error(f"Failed to extract text for {filename}")
                    continue

                # Extract the page number from the filename
                page_number = extract_page_number_from_filename(filename)

                # Summarize the text using GPT-4o
                summary = summarize_text_with_gpt(pdf_text, page_number)

                # Remove page number from filename to create the output file name
                clean_filename = f"Summary - {remove_page_number(filename).replace('.pdf', '')}.txt"
                summary_file_path = os.path.join(output_folder, clean_filename)

                # Append the summary to the output file
                with open(summary_file_path, 'a', encoding='utf-8') as summary_file:
                    summary_file.write(f"Summary of {filename}:\n")
                    summary_file.write(summary.content + "\n\n")
                    logger.info(f"Summary of {filename} written to {summary_file_path}")

            except Exception as e:
                logger.error(f"Error processing {pdf_path}: {e}")

    logger.info("Summarization of all files completed.")

# Define the input root folder containing your PDF files inside subfolders
input_root_folder = 'rawPdfOutput'

# Then, run the summarization process
summarize_pdfs_in_folders(input_root_folder)
