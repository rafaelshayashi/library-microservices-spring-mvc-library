package br.com.rafaelshayashi.library.controller.request;

import br.com.rafaelshayashi.library.model.LibraryBranch;

import javax.validation.constraints.NotEmpty;

public class LibraryBranchRequest {

    @NotEmpty
    private String name;
    private AddressRequest address;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public AddressRequest getAddress() {
        return address;
    }

    public void setAddress(AddressRequest address) {
        this.address = address;
    }

    public LibraryBranch toModel() {
        return LibraryBranch.builder().name(name).address(address.toModel()).build();
    }
}
