import torch
from transformers import AutoTokenizer, AutoModelForCausalLM

# Load the tokenizer and model
model_name = "meta-llama/Meta-Llama-3-8B"
tokenizer = AutoTokenizer.from_pretrained(model_name)
model = AutoModelForCausalLM.from_pretrained(model_name)

# Force the use of the CPU instead of GPU
device = "cpu"
model.to(device)

# Example text generation (you can modify this based on your needs)
input_text = "LLaMA model for text generation using CPU."
inputs = tokenizer(input_text, return_tensors="pt").to(device)

# Generate text
outputs = model.generate(inputs['input_ids'], max_length=50, num_beams=5, early_stopping=True)
output_text = tokenizer.decode(outputs[0], skip_special_tokens=True)

print(output_text)
