FROM mongo

COPY init.json /init.json

CMD mongoimport --host mymongodb --db quizdb --collection question --type json --file /init.json --jsonArray