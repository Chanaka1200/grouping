FROM otel/opentelemetry-collector-contrib:latest

COPY collector-config.yaml /etc/otel/collector-config.yaml

CMD ["--config=/etc/otel/collector-config.yaml"]

USER 10014