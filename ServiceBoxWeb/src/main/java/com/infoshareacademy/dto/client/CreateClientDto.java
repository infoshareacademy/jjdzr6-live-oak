package com.infoshareacademy.dto.client;

import com.infoshareacademy.entity.client.Address;
import com.infoshareacademy.entity.client.Client;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;
import java.io.Serializable;

/**
 * A DTO for the {@link Client} entity
 */
@Getter
@Setter
@NoArgsConstructor
public class CreateClientDto {
    @NotBlank(message = "To pole jest wymagane")
    private String name;
    private AddressDto address;
    @Pattern(regexp="(^$|\\d{10})", message = "Niepoprawny NIP")
    private String nip;
    @NotBlank(message = "To pole jest wymagane")
    @Pattern(regexp="(^$|\\d{9})", message = "Niepoprawny numer telefonu")
    private String phoneNumber;
    @NotBlank(message = "To pole jest wymagane")
    @Email
    private String email;
    private boolean allowNotifications;

    /**
     * A DTO for the {@link Address} entity
     */
    @Getter
    @Setter
    @NoArgsConstructor
    public static class AddressDto {
        private String street;
        private String houseNumber;
        private String flatNumber;
        @Pattern(regexp="(^$|\\d{2}-\\d{3})", message = "Podaj kod pocztowy w formacie xx-xxx")
        private String zipCode;
        private String city;
    }

    public Client toClient() {
        Address clientAddress = null;

        if (this.address != null) {
            clientAddress = new Address(
                    this.address.street,
                    this.address.houseNumber,
                    this.address.flatNumber,
                    this.address.zipCode,
                    this.address.city
            );
        }

        return new Client(
                this.name,
                clientAddress,
                this.nip,
                this.phoneNumber,
                this.email,
                this.allowNotifications
        );
    }
}