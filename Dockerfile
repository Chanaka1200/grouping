FROM grafana/otel-lgtm:1.4.0

# Grafana UI
EXPOSE 3000
# OTLP gRPC receiver
EXPOSE 4317
# OTLP HTTP receiver
EXPOSE 4318

# Health check against Grafana
HEALTHCHECK --interval=30s --timeout=5s --start-period=30s --retries=3 \
  CMD wget --no-verbose --tries=1 --spider http://localhost:3000/api/health || exit 1
