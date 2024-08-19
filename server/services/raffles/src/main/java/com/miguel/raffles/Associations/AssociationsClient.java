package com.miguel.raffles.Associations;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.Optional;

@FeignClient(
        name = "associations-client",
        url = "${application.config.associations-url}"
)
public interface AssociationsClient {

    @GetMapping("/{id}")
    Optional<AssociationResponse> findById(@PathVariable("id") Integer id);

}
