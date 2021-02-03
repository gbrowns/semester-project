package com.boomer;

import io.vertx.core.AbstractVerticle;
import io.vertx.ext.web.Router;

public class App extends AbstractVerticle {

    @Override
    public void start() {
        Router r;
        r = Router.router(this.vertx);
        r.get("/").handler(this::piit ng);

        this.vertx.createHttpServer()
                .routerHandler(r)
                .listener(8080);

    }

    public void ping(final RoutingContext rc){
        rc.response().end("Learning Advance visual programming");
    }

}
