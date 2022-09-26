package com.infoshareacademy.dto.client;

import com.infoshareacademy.entity.client.Address;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor

public class ClientDto {
    private Long id;
    private String name;
    private String address;
    private String nip;
    private String phoneNumber;
    private String email;
    private boolean allowNotify;
}
