from db.redis import RedisClient
from llm.model import Translator
from services.translate import translate
import json

class MessageService:

    def __init__(self):
        pass
    
    def get_object_data(self, str_id:str, redis:RedisClient):
        string_input = redis.get(str_id)
        data = json.loads(string_input)

        input_lang = data['input_language']
        output_lang = data['output_language']
        text = data["text"]

        return input_lang, output_lang, text


    def process_message(self, translator:Translator, redis:RedisClient):
        while True:
            string_id = redis.lpop('input_id::queue')
            if string_id is not None:
                
                input_lang, output_lang, data = self.get_object_data(string_id, redis)
                model_response = translate(translator, input_lang, output_lang, data)
                
                response_message_dict = {
                    "input_language": input_lang,
                    "target_language": output_lang,
                    "translated_text": model_response
                }
                
                output_id = string_id + "::output"
                response = str(json.dumps(response_message_dict))
                
                redis.set(output_id, response)
                redis.rpush(f'output_id::queue', output_id)
