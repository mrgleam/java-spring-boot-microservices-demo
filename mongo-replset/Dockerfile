FROM mongo
COPY rs-init.sh /scripts/rs-init.sh
RUN chmod +x /scripts/rs-init.sh
ENTRYPOINT [ "/scripts/rs-init.sh" ]
