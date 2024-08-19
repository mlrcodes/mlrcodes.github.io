package com.miguel.raffles.Associations;

import org.springframework.stereotype.Service;

@Service
public class AssociationMapper {

    public AssociationResponse fromAssociation(Association association) {
        return AssociationResponse.builder()
                .id(association.getId())
                .name(association.getName())
                .email(association.getEmail())
                .phoneNumber(association.getPhoneNumber())
                .city(association.getCity())
                .zipCode(association.getZipCode())
                .build();
    }

}
