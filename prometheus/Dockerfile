FROM --platform=linux/amd64 prom/prometheus:v2.54.1

USER root

COPY prometheus.yml /etc/prometheus/prometheus.yml

RUN mkdir -p /tmp/prometheus && chown -R 10001:10001 /tmp/prometheus

EXPOSE 9090

HEALTHCHECK --interval=30s --timeout=5s --start-period=10s --retries=3 \
  CMD wget --no-verbose --tries=1 --spider http://localhost:9090/-/healthy || exit 1

USER 10001

ENTRYPOINT ["/bin/prometheus"]
CMD [ \
  "--config.file=/etc/prometheus/prometheus.yml", \
  "--storage.tsdb.path=/tmp/prometheus", \
  "--storage.tsdb.retention.time=30d", \
  "--web.enable-lifecycle" \
]