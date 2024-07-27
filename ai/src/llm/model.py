from groq import Groq
from llm.prompt import TranslatorTemplate

class Translator:
    def __init__(self, api_key):
        self.api_key = api_key
        self.client = self.__model__()
        self.prompt_builder = TranslatorTemplate()

    def translate(self, input_language, output_language, text):
        groq = self.client

        prompt = self.__create_prompt__(input_language, output_language, text)

        response = groq.chat.completions.create(
            messages=[{
                "role": "user",
                "content": prompt
            }],
            model = "llama3-8b-8192"
        )

        return response.choices[0].message.content

    def __model__(self):
        client = Groq(
            api_key=self.api_key
        )
        return client
    
    def __create_prompt__(self, input_language, output_language, text):
        return self.prompt_builder.build(input_language, output_language, text)


