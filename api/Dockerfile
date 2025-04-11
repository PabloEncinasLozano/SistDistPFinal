FROM python:3.12-slim-bookworm

WORKDIR /app

COPY . .
RUN pip install --no-cache-dir -r requirements.txt

ENV FLASK_APP=main:app
CMD ["flask", "run", "--host=0.0.0.0", "--port=8000"]
