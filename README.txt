configure service to read properties from external repo:

1. Create folder (config-repo) out of current project and init git inside:
    git init .
    git remote add origin <path to new git repo>
2. Create project/module server-config.
    see server-config for more details
    application.prop change to bootstrap.prop
    bootstrap.properties: add this:
        server.port=8888
        spring.cloud.config.server.git.uri=file://${user.home}/<path to config-repo>
3. Alter existing service:
    Change app.prop to bootstrap.
    Add:
        spring.application.name=<service-name>
        spring.cloud.config.uri=http://localhost:8888
    Move prop to config-repo (to <service-name>.properties)
4. Run servcer-config and after that this service. Go to http://localhost:8888/<service-name>/default to see properties
------------------------------------------------------------------------------------------------------------------------

Refreshing properties:

If prop was changed, do not rerun config-server or service.
Call from terminal!!! curl -d {} http://<service-host-port>/refresh
If many instances of this service are running: curl -d {} http://<service-host-port>/bus/refresh
It will refresh properties
------------------------------------------------------------------------------------------------------------------------