FROM mongo

COPY data.json /init.json
CMD mongoimport --host mongo-db --db mathsermone-store --collection product  --type json --file /init.json --jsonArray