package com.miguel.raffles.Associations;

import com.miguel.raffles.Exceptions.CustomExceptions.AssociationNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class AssociationsService {

    private final AssociationsRepository repository;

    private final AssociationMapper mapper;

    public AssociationResponse findById(Integer id) {
        return mapper.fromAssociation(
                repository.findById(id)
                        .orElseThrow(() -> new AssociationNotFoundException("Association not found"))
        );
    }
}
