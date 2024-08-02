from db.redis import RedisClient
from llm.model import Translator
from services.translate import translate
import json

class MessageService:

    def __init__(self):
        pass
    
    def get_object_data(self, input:dict):
        input_lang, output_lang = input['input_lang'], input['output_lang']
        text = input["text"]
        return input_lang, output_lang, text


    def process_message(self, translator:Translator, redis:RedisClient):
        string_data = redis.lpop('input_text::queue')
        print(string_data)
        if string_data is not None:
            input_lang, output_lang, data = self.get_object_data(string_data)

            model_response = translate(translator, input_lang, output_lang, data)

            response_message_dict = {
                "input_lang": input_lang,
                "output_lang": output_lang,
                "data": model_response
            }

            response = str(json.dumps(response_message_dict))
            redis.rpush("output_text::queue", response)
            return True
        return False
    
    def get_object_data(self, input_string:str):
        dict_data = json.loads(input_string)
        
        input_lang, output_lang = dict_data['input_lang'], dict_data['output_lang']
        text = dict_data["text"]
        
        return input_lang, output_lang, text