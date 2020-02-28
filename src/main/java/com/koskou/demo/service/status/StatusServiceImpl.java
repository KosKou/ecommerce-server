package com.koskou.demo.service.status;

import com.koskou.demo.entity.Status;
import com.koskou.demo.repository.StatusRepository;
import com.koskou.demo.servicedto.AddStatusRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
@RequiredArgsConstructor
public class StatusServiceImpl implements StatusService{

    private final StatusRepository statusRepository;


    @Override
    public Mono<Status> addStatus(AddStatusRequest addStatusRequest) {
        return addStatusToRepository(addStatusRequest);
    }

    private Mono<Status> addStatusToRepository(AddStatusRequest addStatusRequest) {
        return statusRepository.findByDescription(addStatusRequest.getDescription())
                .switchIfEmpty(statusRepository.save(toStatus(addStatusRequest)));
    }

    private Status toStatus(AddStatusRequest addStatusRequest) {
        return Status.builder()
                .description(addStatusRequest.getDescription())
                .build();
    }

    @Override
    public Flux<Status> retrieveAllStatus() {
        return statusRepository.findAll();
    }

    @Override
    public Mono<Status> updateStatus() {
        return null;
    }

    @Override
    public Mono<Status> deleteStatus(String statusId) {
        return null;
    }
}
