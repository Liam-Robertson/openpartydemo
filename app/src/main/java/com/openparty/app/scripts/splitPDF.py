import os
from PyPDF2 import PdfReader, PdfWriter

# Function to split a single PDF
def split_pdf(input_pdf_path, output_folder, log_file):
    try:
        # Get the base name of the input file (without extension)
        base_name = os.path.splitext(os.path.basename(input_pdf_path))[0]

        # Create a new folder within rawPdfOutput named after the input PDF (base name)
        pdf_output_folder = os.path.join(output_folder, base_name)

        # Create the new folder if it doesn't exist
        if not os.path.exists(pdf_output_folder):
            os.makedirs(pdf_output_folder)

        # Open the input PDF
        with open(input_pdf_path, 'rb') as infile:
            reader = PdfReader(infile)

            # For each page in the PDF
            for i in range(len(reader.pages)):
                writer = PdfWriter()
                writer.add_page(reader.pages[i])

                # Output file path in the newly created folder
                output_pdf_path = os.path.join(pdf_output_folder, f'Page_{i + 1} - {base_name}.pdf')

                # Write the single page to a new PDF file
                with open(output_pdf_path, 'wb') as outfile:
                    writer.write(outfile)

                print(f'Created: {output_pdf_path}')

        # After successful split, delete the original PDF
        os.remove(input_pdf_path)
        print(f'Successfully split and deleted: {input_pdf_path}')

    except Exception as e:
        # Log any errors to the log file
        with open(log_file, 'a') as log:
            log.write(f"Error processing {input_pdf_path}: {e}\n")
        print(f"An error occurred with {input_pdf_path}. Logged to {log_file}")


# Main function to loop through all PDFs in the input folder
def process_pdfs(input_folder):
    # Define the main output folder (rawPdfOutput) in the same directory as the script
    script_directory = os.path.dirname(os.path.abspath(__file__))
    main_output_folder = os.path.join(script_directory, 'rawPdfOutput')

    # Create the main output folder if it doesn't exist
    if not os.path.exists(main_output_folder):
        os.makedirs(main_output_folder)

    # Log file for errors
    log_file = os.path.join(input_folder, 'error_log.txt')

    # Loop through all files in the input folder
    for file_name in os.listdir(input_folder):
        # Only process PDF files
        if file_name.endswith('.pdf'):
            input_pdf_path = os.path.join(input_folder, file_name)
            print(f"Processing {input_pdf_path}")
            split_pdf(input_pdf_path, main_output_folder, log_file)


# Example usage
# Define the input folder, going back one directory and then into westLothianResources/toBeSplit
input_folder = os.path.join(os.path.dirname(os.path.abspath(__file__)), '../westLothianResources/toBeSplit')

# Start processing all PDFs in the input folder
process_pdfs(input_folder)
