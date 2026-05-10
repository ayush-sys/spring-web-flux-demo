package com.springflux.demo.controller;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.reactive.function.server.RequestPredicates;
import org.springframework.web.reactive.function.server.RouterFunction;
import org.springframework.web.reactive.function.server.RouterFunctions;
import org.springframework.web.reactive.function.server.ServerResponse;

import com.springflux.demo.service.UserHandler;

@Configuration
public class UserRouter {

  @Bean
  public RouterFunction<ServerResponse> route(UserHandler handler) {
    return RouterFunctions.route(
        RequestPredicates.GET("/users"),
        handler::allUsers);
  }

}
