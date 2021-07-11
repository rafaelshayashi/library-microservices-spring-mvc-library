package br.com.rafaelshayashi.library.controller.response;

import br.com.rafaelshayashi.library.model.Address;

public class AddressResponse {

    private final String street;
    private final String state;
    private final String country;
    private final String zipCode;

    public AddressResponse(String street, String state, String country, String zipCode) {
        this.street = street;
        this.state = state;
        this.country = country;
        this.zipCode = zipCode;
    }

    public static AddressResponse of(Address address) {
        if (address == null) {
            return new AddressResponse(null, null, null, null);
        }
        return new AddressResponse(address.getStreet(), address.getState(), address.getCountry(), address.getZipCode());
    }

    public String getStreet() {
        return street;
    }

    public String getState() {
        return state;
    }

    public String getCountry() {
        return country;
    }

    public String getZipCode() {
        return zipCode;
    }
}
