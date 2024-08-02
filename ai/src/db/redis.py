import redis

class RedisClient:
    def __init__(self, port):
        self.client = redis.StrictRedis(host='localhost', port=port, db=0, decode_responses=True)

    def create_client(self):
        return self.client