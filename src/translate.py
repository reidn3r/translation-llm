from llm.model import Translator

def translate(translator:Translator, input_language:str, output_language:str, text:str):
    response = translator.translate(input_language, output_language, text)
    return response