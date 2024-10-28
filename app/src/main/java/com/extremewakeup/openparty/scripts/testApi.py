from openai import OpenAI
client = OpenAI()


OpenAI.api_key = "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"
completion = client.chat.completions.create(
    model="gpt-3.5-turbo",
    messages=[
        {"role": "user", "content": "write a haiku about ai"}
    ]
)
