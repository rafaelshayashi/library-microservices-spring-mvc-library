package br.com.rafaelshayashi.library.controller.response;


import br.com.rafaelshayashi.library.model.Address;
import br.com.rafaelshayashi.library.model.LibraryBranch;

import java.util.UUID;

public class LibraryResponse {

    public final String uuid;
    public final String name;
    public final AddressResponse address;

    public LibraryResponse(LibraryBranch library) {
        this(library.getUuid(), library.getName(), library.getAddress());
    }

    public LibraryResponse(UUID uuid, String name, Address address) {
        this.uuid = uuid.toString();
        this.name = name;
        this.address = AddressResponse.of(address);
    }

    public static LibraryResponse of(LibraryBranch library) {
        return new LibraryResponse(library.getUuid(), library.getName(), library.getAddress());
    }

    public String getUuid() {
        return uuid;
    }

    public String getName() {
        return name;
    }

    public AddressResponse getAddress() {
        return address;
    }
}
