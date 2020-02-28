package com.koskou.demo.service.user;

import com.koskou.demo.entity.Role;
import com.koskou.demo.entity.User;
import com.koskou.demo.repository.RoleRepository;
import com.koskou.demo.repository.StatusRepository;
import com.koskou.demo.repository.UserRepository;
import com.koskou.demo.servicedto.AddUserRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.ArrayList;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;

    private final StatusRepository statusRepository;

    private final RoleRepository roleRepository;

    @Override
    public Mono<User> addUser(AddUserRequest addUserRequest) {
        return addUserToRepository(addUserRequest);
    }

    private Mono<User> addUserToRepository(AddUserRequest addUserRequest){
        return userRepository.findByEmail(addUserRequest.getEmail())
                .switchIfEmpty(userRepository.save(toUser(addUserRequest)));
    }

    //TODO: Find a better way to implement this shit please
    private User toUser(AddUserRequest addUserRequest){
        ArrayList<String> roles = new ArrayList<>();
        roles.add("USER");
        User user = User.builder()
                        .email(addUserRequest.getEmail())
                        .password(addUserRequest.getPassword())
                        .username(addUserRequest.getEmail())
                        .roles(roles)
                        .status("ACTIVE")
                        .build();
        return user;
    }


    @Override
    public Flux<User> retrieveAllUsers() {
        return userRepository.findAll();
    }

    @Override
    public Mono<User> retrieveUserById(String id) {
        return userRepository.findById(id).switchIfEmpty(Mono.empty());
    }
    //TODO: implement update UserService
    @Override
    public Mono<User> updateUser() {
        return null;
    }

    @Override
    public Mono<User> deleteUserById(String id) {
        return userRepository.findById(id)
                .switchIfEmpty(Mono.empty())
                .map(user -> changeUserStatus(user, "INACTIVE"))
                .doOnSuccess(userRepository::save);
    }

    private User changeUserStatus(User user, String status){
        user.setStatus(status);
        return user;
    }


}
