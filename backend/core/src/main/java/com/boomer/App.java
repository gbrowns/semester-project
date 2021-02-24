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

    public boolean isOfDrivingAge(final int age){
        return age > 18;
    }

    public int sumOneToTen(){
        int sum = 0;
        for (int i = 1; i < 10; i++){
            sum += i;
        }

        return sum;
    }
}
