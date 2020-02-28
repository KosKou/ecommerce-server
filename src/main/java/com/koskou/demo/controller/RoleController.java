package com.koskou.demo.controller;

import com.koskou.demo.entity.Role;
import com.koskou.demo.service.role.RoleService;
import com.koskou.demo.servicedto.AddRoleRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RestController
@RequiredArgsConstructor
@RequestMapping(value = "/api/roles")
public class RoleController {

    private final RoleService roleService;

    //FILL
    @GetMapping(
            value = "/fill"
    )
    public String fillRoles(){
        roleService.addRole(new AddRoleRequest("USER"));
        roleService.addRole(new AddRoleRequest("ADMIN"));
        return "Successfully created";
    }
    //CREATE
    @PostMapping(
            consumes = {MediaType.APPLICATION_JSON_VALUE},
            produces = {MediaType.APPLICATION_STREAM_JSON_VALUE}
    )
    public Mono<Role> createRole(@RequestBody AddRoleRequest addRoleRequest){
        return roleService.addRole(addRoleRequest);
    }

    //RETRIEVE
    @GetMapping(
        produces = {MediaType.APPLICATION_STREAM_JSON_VALUE}
    )
    public Flux<Role> retrieveAllRoles(){
        return roleService.retrieveAllRoles();
    }

    //UPDATE

    //DELETE
    @DeleteMapping(
            value = "/{roleId}",
            produces = {MediaType.APPLICATION_STREAM_JSON_VALUE}
    )
    public Mono<Role> deleteRole(@PathVariable(value = "roleId") String roleId){
        return roleService.deleteRoleById(roleId);
    }
}
