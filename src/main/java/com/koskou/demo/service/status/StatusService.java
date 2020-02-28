package com.koskou.demo.service.status;

import com.koskou.demo.entity.Status;
import com.koskou.demo.servicedto.AddStatusRequest;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface StatusService {

    //CREATE
    Mono<Status> addStatus(AddStatusRequest addStatusRequest);

    //RETRIEVE
    Flux<Status> retrieveAllStatus();

    //UPDATE
    Mono<Status> updateStatus();

    //DELETE
    Mono<Status> deleteStatus(String statusId);

}
