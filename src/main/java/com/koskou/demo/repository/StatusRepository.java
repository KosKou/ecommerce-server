package com.koskou.demo.repository;

import com.koskou.demo.entity.Status;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Mono;

@Repository
public interface StatusRepository extends ReactiveMongoRepository<Status, String> {

    Mono<Status> findByDescription(String description);
}
