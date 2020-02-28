package com.koskou.demo.service.role;

import com.koskou.demo.entity.Role;
import com.koskou.demo.servicedto.AddRoleRequest;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface RoleService {

    //CREATE
    Mono<Role> addRole(AddRoleRequest addRoleRequest);

    //RETRIEVE
    Flux<Role> retrieveAllRoles();
    Mono<Role> retrieveRoleById(String id);

    //UPDATE
    Mono<Role> updateRole();

    //DELETE
    Mono<Role> deleteRoleById(String id);
}
