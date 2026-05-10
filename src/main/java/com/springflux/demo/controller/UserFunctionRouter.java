package com.springflux.demo.controller;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.server.RequestPredicates;
import org.springframework.web.reactive.function.server.RouterFunction;
import org.springframework.web.reactive.function.server.RouterFunctions;
import org.springframework.web.reactive.function.server.ServerResponse;

import com.springflux.demo.service.UserComponent;

@Configuration
public class UserFunctionRouter {

  @Bean
  public RouterFunction<ServerResponse> route(UserComponent handler) {
    return RouterFunctions.route(
        RequestPredicates.GET("/users"),
        handler::allUsers);
  }

}
