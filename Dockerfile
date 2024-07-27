FROM python:3.8-slim

WORKDIR /llm/v1_translator

COPY . .

RUN pip install -r requirements.txt

EXPOSE 2703

CMD tail -f /dev/null