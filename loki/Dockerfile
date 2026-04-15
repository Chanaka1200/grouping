FROM grafana/loki:3.3.2

COPY loki-config.yaml /etc/loki/local-config.yaml

EXPOSE 3100

HEALTHCHECK --interval=30s --timeout=5s --start-period=15s --retries=3 \
  CMD wget --no-verbose --tries=1 --spider http://localhost:3100/ready || exit 1

USER 10001

ENTRYPOINT ["/usr/bin/loki"]
CMD ["-config.file=/etc/loki/local-config.yaml"]
