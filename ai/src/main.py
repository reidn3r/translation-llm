import os
import sys
import json
from dotenv import load_dotenv
from llm.model import Translator
from translate import translate

load_dotenv()
def main(translator:Translator, argc:int, argv:'list[str]'):
    if(argc < 4):
        raise ValueError("Missing Arguments")

    input_lang, output_lang, text = build_input(argv)
    model_response = translate(translator, input_lang, output_lang, text)

    container_response = {
        "input_language": input_lang,
        "target_language": output_lang,
        "translated_text": model_response
    }
    return json.dumps(container_response)
    
def build_input(argv):
    input_lang, output_lang = argv[1], argv[2]
    text =  " ".join(argv[3:])
    return input_lang, output_lang, text

if __name__ == "__main__":
    API_KEY = os.getenv("API_KEY")
    translator = Translator(API_KEY)

    argv, argc = sys.argv, len(sys.argv)
    main(translator, argc, argv)