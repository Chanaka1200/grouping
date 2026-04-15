FROM otel/opentelemetry-collector-contrib:0.116.0

COPY collector-config.yaml /etc/otel/collector-config.yaml

EXPOSE 4317
EXPOSE 4318
EXPOSE 8889

HEALTHCHECK --interval=30s --timeout=5s --start-period=10s --retries=3 \
  CMD wget --no-verbose --tries=1 --spider http://localhost:13133/ || exit 1

USER 10014

ENTRYPOINT ["/otelcol-contrib"]
CMD ["--config=/etc/otel/collector-config.yaml"]
