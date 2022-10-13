package com.infoshareacademy.dto.client;

import com.infoshareacademy.entity.client.Address;
import com.infoshareacademy.entity.client.Client;
import com.infoshareacademy.dto.user.UserDto;
import lombok.*;

/**
 * A DTO for the {@link Client} entity
 */
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor

public class ClientDto {
    private Long id;
    private UserDto user;
    private String name;
    private AddressDto address;
    private String nip;
    private String phoneNumber;
    private String email;
    private boolean allowNotifications;

    /**
     * A DTO for the {@link Address} entity
     */
    @Getter
    @Setter
    @AllArgsConstructor
    @NoArgsConstructor

    public static class AddressDto {
        private Long id;
        private String street;
        private String houseNumber;
        private String flatNumber;
        private String zipCode;
        private String city;

        @Override
        public String toString() {
            StringBuilder builder = new StringBuilder(street);

            if (!houseNumber.isBlank()) builder.append(" ").append(houseNumber);
            if (!flatNumber.isBlank()) builder.append("/").append(flatNumber);
            if (!zipCode.isBlank() && !city.isBlank()) {
                if (!builder.isEmpty()) builder.append(", ");
                builder.append(zipCode).append(" ").append(city);
            }

            return builder.toString();
        }
    }

    public static ClientDto fromClient(Client client) {
        if (client == null) {
            return null;
        }

        ClientDto.AddressDto addressDto = null;
        if (client.getAddress() != null) {
            Address clientAddress = client.getAddress();
            addressDto = new AddressDto(
                    clientAddress.getId(),
                    clientAddress.getStreet(),
                    clientAddress.getHouseNumber(),
                    clientAddress.getFlatNumber(),
                    clientAddress.getZipCode(),
                    clientAddress.getCity()
            );
        }

        return new ClientDto(
                client.getId(),
                UserDto.fromUser(client.getUser()),
                client.getName(),
                addressDto,
                client.getNip(),
                client.getPhoneNumber(),
                client.getEmail(),
                client.isAllowNotifications()
        );
    }
}