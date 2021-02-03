package com.boomer;

import io.vertx.core.AbstractVerticle;

public class app extends AbstractVerticle {

    @Override
    public void start() {
        Router r;
        r = Router.route(this.vertx);
        r.get("/").handler(this::ping);

        this.vertx.createHttpServer()
                .routerHandler(r)
                .listener(8080);

    }

    public void ping(final RoutingContext rc){
        rc.response().end("Learning Advance visual programming");
    }

}
