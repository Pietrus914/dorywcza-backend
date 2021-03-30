package com.example.dorywcza.util;

import lombok.Data;

@Data
public class AddressDTO {

    private Long id;
    private String street;

    public AddressDTO(Address address){
        this.id = address.getId();
        this.street = address.getStreet();
    }
}
