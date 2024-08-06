import os
from dotenv import load_dotenv
from db.redis import RedisClient
from llm.model import Translator
from services.message import MessageService


load_dotenv()
def main(translator:Translator, redis:RedisClient, message:MessageService):

    message.process_message(translator, redis)


if __name__ == "__main__":
    API_KEY = os.getenv("API_KEY")
    
    translator = Translator(API_KEY)
    redis = RedisClient(6379).create_client()
    message_service = MessageService()

    main(translator, redis, message_service)