package br.com.rafaelshayashi.library.model;

import javax.persistence.Embeddable;

@Embeddable
public class Address {

    private String street;
    private String state;
    private String country;
    private String zipCode;

    public Address() {
    }

    public Address(String street, String state, String country, String zipCode) {
        this.street = street;
        this.state = state;
        this.country = country;
        this.zipCode = zipCode;
    }

    public String getStreet() {
        return street;
    }

    public void setStreet(String street) {
        this.street = street;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getZipCode() {
        return zipCode;
    }

    public void setZipCode(String zipCode) {
        this.zipCode = zipCode;
    }

    public static AddressBuilder builder(){
        return new AddressBuilder();
    }

    public static class AddressBuilder {
        private String street;
        private String state;
        private String country;
        private String zipCode;

        public AddressBuilder street(String street) {
            this.street = street;
            return this;
        }

        public AddressBuilder state(String state) {
            this.state = state;
            return this;
        }

        public AddressBuilder country(String country) {
            this.country = country;
            return this;
        }

        public AddressBuilder zipCode(String zipCode) {
            this.zipCode = zipCode;
            return this;
        }

        public Address build() {
            return new Address(street, state, country, zipCode);
        }
    }
}
