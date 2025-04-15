FROM python:3.12-slim-bookworm

WORKDIR /app

COPY . .
RUN pip install --no-cache-dir -r requirements.txt


#RUN python ..mysql/db/database_creator.py

ENV FLASK_APP=main:app

CMD ["sleep", "infinity"]
#CMD ["flask", "run", "--host=0.0.0.0", "--port=8000"]
