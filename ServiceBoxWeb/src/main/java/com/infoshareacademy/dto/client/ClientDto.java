package com.infoshareacademy.dto.client;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;

@NoArgsConstructor
@Getter
@Setter
public class ClientDto {
    private Long id;
    @NotBlank(message = "To pole jest wymagane")
    private String name;
    private String addressStreet = "";
    private String addressHouseNumber = "";
    private String addressFlatNumber = "";
    @Pattern(regexp="(^$|\\d{2}-\\d{3})", message = "Podaj kod pocztowy w formacie xx-xxx")
    private String addressZipCode = "";
    private String addressCity = "";
    @Pattern(regexp="(^$|\\d{10})", message = "Niepoprawny NIP")
    private String nip = "";
    @NotBlank(message = "To pole jest wymagane")
    @Pattern(regexp="(^$|\\d{9})", message = "Niepoprawny numer telefonu")
    private String phoneNumber;
    @NotBlank(message = "To pole jest wymagane")
    private String email;
    private boolean allowNotify;

    public String getAddress() {
        StringBuilder builder = new StringBuilder(addressStreet);
        // add house number
        if (!addressHouseNumber.isBlank()) builder.append(" ").append(addressHouseNumber);

        // add flat number
        if (!addressFlatNumber.isBlank()) builder.append("/").append(addressFlatNumber);

        // add zipcode and city
        if (!addressZipCode.isBlank() && !addressCity.isBlank()) {
            if (!builder.isEmpty()) builder.append(", ");
            builder.append(addressZipCode).append(" ").append(addressCity);
        }

        return builder.toString();
    }
}
