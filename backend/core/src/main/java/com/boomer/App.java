package com.boomer;

import io.vertx.core.AbstractVerticle;
import io.vertx.ext.web.Router;
import io.vertx.ext.web.RoutingContext;

public class App extends AbstractVerticle {

    @Override
    public void start() {
        Router r = Router.router(this.vertx);
        r.get("/").handler(this::ping);

        this.vertx.createHttpServer()
                .requestHandler(r)
                .listen(8080);

    }

    public void ping(final RoutingContext rc){
        rc.response().end("Learning Advance visual programming");
    }

}
