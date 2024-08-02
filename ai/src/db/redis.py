import redis

class RedisClient:
    def __init__(self, port:int):
        self.port = port
    
    def create_client(self):
        return redis.Redis(host='localhost', port=self.port, decode_responses=True)
        
    