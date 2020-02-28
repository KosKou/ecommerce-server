package com.koskou.demo.controller;

import com.koskou.demo.entity.User;
import com.koskou.demo.service.user.UserService;
import com.koskou.demo.servicedto.AddUserRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RestController
@RequiredArgsConstructor
@RequestMapping(value = "/api/users")
public class UserController {

    private final UserService userService;

    @GetMapping(
            value = "/fill"
    )
    public String fillUsers(){
        userService.addUser(AddUserRequest.builder().email("user@gmail.com").password("123456").build());
        userService.addUser(AddUserRequest.builder().email("admin@gmail.com").password("123456").build());
        return "Successfully created";
    }

    //CREATE
    @PostMapping(
            consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = {MediaType.APPLICATION_STREAM_JSON_VALUE}
    )
    public Mono<User> createUser(@RequestBody AddUserRequest addUserRequest){
        return userService.addUser(addUserRequest);
    }

    //RETRIEVE
    @GetMapping(
            produces = {MediaType.APPLICATION_STREAM_JSON_VALUE}
    )
    public Flux<User> retrieveAllUsers(){
        return userService.retrieveAllUsers();
    }

    //UPDATE


    //DELETE
    @DeleteMapping(
            value = "/{userId}",
            produces = {MediaType.APPLICATION_STREAM_JSON_VALUE}
    )
    public Mono<User> deleteUser(@PathVariable(value = "userId") String userId){
        return userService.deleteUserById(userId);
    }
}
