FROM mongo

COPY data.json /init.json
CMD mongoimport --version; mongoimport --host mongo-db --db mathsermone-store --collection product --mode delete --upsertFields name --type json --file /init.json --jsonArray; mongoimport --host mongo-db --db mathsermone-store --collection product --type json --file /init.json --jsonArray