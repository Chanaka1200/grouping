FROM grafana/grafana-oss:11.4.0

# Provisioning configs
COPY grafana-datasources.yml /etc/grafana/provisioning/datasources/datasources.yml
COPY grafana-dashboards.yml /etc/grafana/provisioning/dashboards/dashboards.yml
COPY claude-code-dashboard.json /var/lib/grafana/dashboards/claude-code-dashboard.json

ENV GF_SECURITY_ADMIN_PASSWORD=admin
ENV GF_USERS_ALLOW_SIGN_UP=false
ENV GF_FEATURE_TOGGLES_ENABLE=logsSampleInExplore
ENV GF_DASHBOARDS_DEFAULT_HOME_DASHBOARD_PATH=/var/lib/grafana/dashboards/claude-code-dashboard.json

EXPOSE 3000

HEALTHCHECK --interval=30s --timeout=5s --start-period=30s --retries=3 \
  CMD wget --no-verbose --tries=1 --spider http://localhost:3000/api/health || exit 1

USER 472
