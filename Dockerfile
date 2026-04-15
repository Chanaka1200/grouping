FROM grafana/otel-lgtm:1.4.0

EXPOSE 3000
EXPOSE 4317
EXPOSE 4318

HEALTHCHECK --interval=30s --timeout=5s --start-period=30s --retries=3 \
  CMD wget --no-verbose --tries=1 --spider http://localhost:3000/api/health || exit 1

USER 10014