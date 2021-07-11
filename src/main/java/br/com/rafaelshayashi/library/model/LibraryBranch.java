package br.com.rafaelshayashi.library.model;


import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.util.UUID;

@Entity
public class LibraryBranch {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private UUID uuid;
    @NotEmpty
    private String name;
    @Embedded
    @NotNull
    private Address address;

    public LibraryBranch() {
    }

    public LibraryBranch(String name, Address address) {
        this.uuid = UUID.randomUUID();
        this.name = name;
        this.address = address;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public UUID getUuid() {
        return uuid;
    }

    public void setUuid(UUID uuid) {
        this.uuid = uuid;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Address getAddress() {
        return address;
    }

    public void setAddress(Address address) {
        this.address = address;
    }

    public static LibraryBranchBuilder builder(){
        return new LibraryBranchBuilder();
    }

    public static class LibraryBranchBuilder {

        private String name;
        private Address address;

        public LibraryBranchBuilder name(String name){
            this.name = name;
            return this;
        }

        public LibraryBranchBuilder address(Address address){
            this.address = address;
            return this;
        }

        public LibraryBranch build() {
            return new LibraryBranch(name, address);
        }
    }
}
