FROM java:openjdk-8-jre-alpine
COPY ./api/target/$app_artifact_id-$app_version.jar /app/
COPY ./start.sh /app/
EXPOSE $app_prod_port
ENTRYPOINT cd /app/ && sh ./start.sh
