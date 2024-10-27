import os
from PyPDF2 import PdfReader, PdfWriter

# Function to split PDF
def split_pdf(input_pdf_path):
    # Get the directory of the current script
    script_directory = os.path.dirname(os.path.abspath(__file__))

    # Define the output folder (rawPdfOutput) in the same directory as the script
    output_folder = os.path.join(script_directory, 'rawPdfOutput')

    # Create the output folder if it doesn't exist
    if not os.path.exists(output_folder):
        os.makedirs(output_folder)

    # Get the base name of the input file (without extension)
    base_name = os.path.splitext(os.path.basename(input_pdf_path))[0]

    # Open the input PDF
    with open(input_pdf_path, 'rb') as infile:
        reader = PdfReader(infile)

        # For each page in the PDF
        for i in range(len(reader.pages)):
            writer = PdfWriter()
            writer.add_page(reader.pages[i])

            # Output file path in the rawPdfOutput folder
            output_pdf_path = os.path.join(output_folder, f'Page_{i + 1} - {base_name}.pdf')

            # Write the single page to a new PDF file
            with open(output_pdf_path, 'wb') as outfile:
                writer.write(outfile)

            print(f'Created: {output_pdf_path}')

# Example usage
input_pdf = 'Committee Document 5 - Report to Members and Controller of Audit on 2023-24 audited accounts.pdf'  # Replace with your PDF file path

split_pdf(input_pdf)
