package com.miguel.raffles.Associations;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api/v1/associations")
public class AssociationsController {


    private final AssociationsService service;

    @GetMapping("/{id}")
    public ResponseEntity<AssociationResponse> findById(
            @PathVariable("id") Integer id
    ) {
       return ResponseEntity.ok(service.findById(id));
    }

}
