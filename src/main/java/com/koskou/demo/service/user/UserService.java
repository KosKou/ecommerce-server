package com.koskou.demo.service.user;

import com.koskou.demo.entity.User;
import com.koskou.demo.servicedto.AddUserRequest;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface UserService {

    //CREATE
    Mono<User> addUser(AddUserRequest addUserRequest);

    //RETRIEVE
    Flux<User> retrieveAllUsers();
    Mono<User> retrieveUserById(String id);

    //UPDATE
    Mono<User> updateUser();

    //DELETE
    Mono<User> deleteUserById(String id);
}
