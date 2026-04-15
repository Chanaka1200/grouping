FROM --platform=linux/amd64 grafana/grafana-oss:11.4.0

COPY grafana-datasources.yml /etc/grafana/provisioning/datasources/datasources.yml
COPY grafana-dashboards.yml /etc/grafana/provisioning/dashboards/dashboards.yml
COPY claude-code-dashboard.json /var/lib/grafana/dashboards/claude-code-dashboard.json

RUN mkdir -p /var/lib/grafana/dashboards && \
    chown -R 10001:10001 /var/lib/grafana /etc/grafana

ENV GF_SECURITY_ADMIN_PASSWORD=admin
ENV GF_USERS_ALLOW_SIGN_UP=false
ENV GF_FEATURE_TOGGLES_ENABLE=logsSampleInExplore
ENV GF_DASHBOARDS_DEFAULT_HOME_DASHBOARD_PATH=/var/lib/grafana/dashboards/claude-code-dashboard.json
ENV GF_PATHS_DATA=/tmp/grafana
ENV GF_PATHS_LOGS=/tmp/grafana/logs

EXPOSE 3000

HEALTHCHECK --interval=30s --timeout=5s --start-period=30s --retries=3 \
  CMD wget --no-verbose --tries=1 --spider http://localhost:3000/api/health || exit 1

USER 10001