FROM --platform=linux/amd64 otel/opentelemetry-collector-contrib:0.116.0

COPY collector-config.yaml /etc/otel/collector-config.yaml

EXPOSE 4317
EXPOSE 4318
EXPOSE 8889

USER 10014

CMD ["--config=/etc/otel/collector-config.yaml"]