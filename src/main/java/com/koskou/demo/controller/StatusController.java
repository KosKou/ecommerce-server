package com.koskou.demo.controller;

import com.koskou.demo.entity.Status;
import com.koskou.demo.service.status.StatusService;
import com.koskou.demo.servicedto.AddStatusRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RestController
@Transactional
@RequestMapping(value = "/api/status")
@RequiredArgsConstructor
public class StatusController {

    private final StatusService statusService;


    //Initialize
    @GetMapping(
            value = "/fill"
    )
    public String fillStatus(){
        statusService.addStatus(new AddStatusRequest("ACTIVE"));
        statusService.addStatus(new AddStatusRequest("INACTIVE"));
        return "Successfully created";
    }

    @PostMapping(
            consumes = {MediaType.APPLICATION_JSON_VALUE},
            produces = {MediaType.APPLICATION_STREAM_JSON_VALUE}
    )
    public Mono<Status> createStatus(@RequestBody AddStatusRequest addStatusRequest){
        return statusService.addStatus(addStatusRequest);
    }

    @GetMapping(
            produces = {MediaType.APPLICATION_STREAM_JSON_VALUE}
    )
    public Flux<Status> retrieveAllStatus(){
        return statusService.retrieveAllStatus();
    }

}
