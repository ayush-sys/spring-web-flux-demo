package com.springflux.demo.service;

import java.time.LocalDateTime;

import org.springframework.cglib.core.Local;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.server.ServerRequest;
import org.springframework.web.reactive.function.server.ServerResponse;

import com.springflux.demo.model.User;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Component
public class UserComponent {

  public Mono<ServerResponse> allUsers(ServerRequest request) {

    Flux<User> users = Flux.just(
        new User("1", "Ayush", LocalDateTime.now().toString()),
        new User("2", "John", LocalDateTime.now().toString()),
        new User("3", "Jane", LocalDateTime.now().toString()));

    return ServerResponse.ok()
        .contentType(MediaType.APPLICATION_JSON)
        .body(users, User.class);
  }
}