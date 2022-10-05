package com.infoshareacademy.dto.client;

import com.infoshareacademy.entity.client.Address;
import com.infoshareacademy.entity.client.Client;
import com.infoshareacademy.entity.user.UserDto;
import lombok.Data;

/**
 * A DTO for the {@link Client} entity
 */
@Data
public class ClientDto {
    private final Long id;
    private final UserDto user;
    private final String name;
    private final AddressDto address;
    private final String nip;
    private final String phoneNumber;
    private final String email;
    private final boolean allowNotifications;

    /**
     * A DTO for the {@link Address} entity
     */
    @Data
    public static class AddressDto {
        private final Long id;
        private final String street;
        private final String houseNumber;
        private final String flatNumber;
        private final String zipCode;
        private final String city;

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