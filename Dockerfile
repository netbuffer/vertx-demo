FROM vertx/vertx4:4.3.2
ENV VERTICLE_NAME cn.netbuffer.vertx_demo.MainVerticle
ENV VERTICLE_FILE target/vertx-demo-v1.0.0-fat.jar
ENV VERTICLE_HOME /usr/verticles
ENV VERTX_DEMO_PORT=8080
EXPOSE $VERTX_DEMO_PORT
COPY $VERTICLE_FILE $VERTICLE_HOME/
WORKDIR $VERTICLE_HOME
ENTRYPOINT ["sh", "-c"]
CMD ["exec vertx run $VERTICLE_NAME -cp $VERTICLE_HOME/*"]