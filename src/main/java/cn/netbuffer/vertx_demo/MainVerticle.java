package cn.netbuffer.vertx_demo;

import io.netty.util.internal.StringUtil;
import io.vertx.core.AbstractVerticle;
import io.vertx.core.Promise;

public class MainVerticle extends AbstractVerticle {

  @Override
  public void start(Promise<Void> startPromise) throws Exception {
    String portStr = System.getenv("VERTX_DEMO_PORT");
    if (StringUtil.isNullOrEmpty(portStr)) {
      System.out.println("please set VERTX_DEMO_PORT to environment");
      System.exit(0);
      return;
    }
    Integer port = Integer.parseInt(portStr);
    vertx.createHttpServer().requestHandler(req -> {
      req.response()
        .putHeader("content-type", "text/plain")
        .end("Hello from Vert.x!");
    }).listen(port, http -> {
      if (http.succeeded()) {
        startPromise.complete();
        System.out.println("HTTP server started on port " + port);
      } else {
        startPromise.fail(http.cause());
      }
    });
  }

}
