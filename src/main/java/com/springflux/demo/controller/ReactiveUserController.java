package com.springflux.demo.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.springflux.demo.model.User;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;

import java.time.Duration;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

import org.springframework.http.MediaType;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("/api")
@Tag(name = "User Management", description = "Operations related to users")
public class ReactiveUserController {

  DateTimeFormatter timeFormatter = DateTimeFormatter.ofPattern("HH:mm:ss");

  @Operation(summary = "Get a user by ID", description = "Returns a single user wrapped in a Mono")
  @GetMapping("/user/{id}")
  public Mono<User> getUserById(@PathVariable String id) {
    return Mono.just(new User(id, "Ayush", LocalTime.now().format(timeFormatter)));
  }

  @Operation(summary = "Stream all users", description = "Returns a Flux stream of users")
  @GetMapping(value = "/users", produces = MediaType.TEXT_EVENT_STREAM_VALUE)
  public Flux<User> getAllUsers() {
    return Flux.just(new User("1", "Ayush", LocalTime.now().format(timeFormatter)),
        new User("2", "John", LocalTime.now().format(timeFormatter)),
        new User("3", "Jane", LocalTime.now().format(timeFormatter)));
  }

  @Operation(summary = "Stream all users", description = "Streams user data every 2 seconds using Server-Sent Events")
  @GetMapping(value = "/streamUsers", produces = MediaType.TEXT_EVENT_STREAM_VALUE)
  public Flux<User> streamUserData() {

    List<User> users = List.of(
        new User("1", "Ayush", ""),
        new User("2", "John", ""),
        new User("3", "Jane", ""),
        new User("4", "Rahul", ""),
        new User("5", "Anita", ""),
        new User("6", "Rohit", ""),
        new User("7", "Sneha", ""),
        new User("8", "Amit", ""),
        new User("9", "Priya", ""),
        new User("10", "Karan", ""));

    return Flux.fromIterable(users)
        .map(user -> {
          String currentTime = LocalTime.now().format(timeFormatter);
          return new User(user.getId(), user.getName(), currentTime);
        })
        .delayElements(Duration.ofSeconds(2));
  }
}
