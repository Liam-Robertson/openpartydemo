import os
import openai
import pdfplumber
import re

# Fetch the API key from the environment variable
openai.api_key = os.getenv('OPENAI_API_KEY')

# Function to extract text from a PDF using pdfplumber
def extract_text_from_pdf(pdf_path):
    with pdfplumber.open(pdf_path) as pdf:
        text = ''
        for page in pdf.pages:
            text += page.extract_text()
    return text

# Function to summarize text using GPT-4 with the updated OpenAI API
def summarize_text_with_gpt4(text, page_number):
    # Dynamic prompt with page number
    prompt = f"""
    Don't cite your sources

    With the following text, do this:
    - Give it a title which should be this - Page {page_number}
    - A detailed summary of the text
    - All of the statistics in the text. The statistics are the most important part, don't miss any statistics

    For the statistics, I want them to be very detailed. If 1/10 is an extremely low level of detail, and 10/10 is an unbelievably high level of detail, I want the level of detail for the statistics you give me to be a 10/10
    """

    # Updated API call for the new version
    response = openai.chat.completions.create(
        model="gpt-4",  # You can use gpt-3.5-turbo for faster and cheaper responses
        messages=[
            {"role": "system", "content": "You are a helpful assistant."},
            {"role": "user", "content": f"{prompt}\n\n{text}"}
        ],
        max_tokens=1000,  # Adjust this value based on how long you want the summary
        temperature=0.5  # Lower temperature keeps the summary concise and factual
    )

    # Extract the summary content from the response
    summary = response['choices'][0]['message']['content']
    return summary

# Function to extract the page number from the PDF filename
def extract_page_number_from_filename(filename):
    # Regex pattern to find "Page_X" where X is the page number
    match = re.search(r'Page_(\d+)', filename)
    if match:
        return match.group(1)
    else:
        return 'Unknown'

# Main function to process all split PDFs in a folder and summarize them
def summarize_pdfs_in_folder(input_folder):
    # Get the directory of the current script
    script_directory = os.path.dirname(os.path.abspath(__file__))

    # Define the output summary folder (outputPdfSummaries) in the same directory as the script
    output_folder = os.path.join(script_directory, 'outputPdfSummaries')

    # Create the output folder if it doesn't exist
    if not os.path.exists(output_folder):
        os.makedirs(output_folder)

    # Output summary file path
    output_summary_file = os.path.join(output_folder, 'pdf_summaries.txt')

    # Open the output file to write the summaries
    with open(output_summary_file, 'w') as outfile:
        # Loop through all PDF files in the folder
        for filename in os.listdir(input_folder):
            if filename.endswith('.pdf'):
                pdf_path = os.path.join(input_folder, filename)
                print(f"Processing {pdf_path}...")

                # Extract text from the PDF
                pdf_text = extract_text_from_pdf(pdf_path)

                # Extract the page number from the filename
                page_number = extract_page_number_from_filename(filename)

                # Summarize the text using GPT-4 with a custom prompt for each page
                summary = summarize_text_with_gpt4(pdf_text, page_number)

                # Write the summary to the output file
                outfile.write(f"Summary of {filename}:\n")
                outfile.write(summary + "\n\n")
                print(f"Summary of {filename} written to {output_summary_file}")

# Example usage
input_folder = 'rawPdfOutput'  # Folder containing your split PDF files

# Run the summarization process
summarize_pdfs_in_folder(input_folder)
