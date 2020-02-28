package com.koskou.demo.service.role;

import com.koskou.demo.entity.Role;
import com.koskou.demo.entity.Status;
import com.koskou.demo.repository.RoleRepository;
import com.koskou.demo.repository.StatusRepository;
import com.koskou.demo.servicedto.AddRoleRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
@Transactional
@RequiredArgsConstructor
public class RoleServiceImpl implements RoleService{

    private final RoleRepository roleRepository;

    private final StatusRepository statusRepository;


    @Override
    public Mono<Role> addRole(AddRoleRequest addRoleRequest) {
        return addRoleToRepository(addRoleRequest);
    }

    private Mono<Role> addRoleToRepository(AddRoleRequest addRoleRequest) {
        return roleRepository.findByName(addRoleRequest.getName())
                .switchIfEmpty(roleRepository.save(toRole(addRoleRequest)));
    }

    private Role toRole(AddRoleRequest addRoleRequest){
        Role role = Role.builder()
                        .name(addRoleRequest.getName())
                        .status("ACTIVE")
                        .build();
        return role;
    }

    @Override
    public Flux<Role> retrieveAllRoles() {
        return roleRepository.findAll();
    }

    @Override
    public Mono<Role> retrieveRoleById(String id) {
        return roleRepository.findById(id)
                .switchIfEmpty(Mono.empty());
    }

    //TODO: implement update RoleService
    @Override
    public Mono<Role> updateRole() {
        return null;
    }

    @Override
    public Mono<Role> deleteRoleById(String id) {
        return roleRepository.findById(id)
                .switchIfEmpty(Mono.empty())
                .doOnSuccess(role -> changeRoleStatus(role, "INACTIVE"));
    }

    private Role changeRoleStatus(Role role, String status){
        role.setStatus(status);
        return role;
    }
}
