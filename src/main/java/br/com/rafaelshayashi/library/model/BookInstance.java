package br.com.rafaelshayashi.library.model;

import br.com.rafaelshayashi.library.controller.response.BookInstanceResponse;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.UUID;

@Entity
public class BookInstance {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @NotNull
    private UUID uuid;
    private UUID bookUuid;
    @ManyToOne
    private LibraryBranch library;

    public BookInstance() {
    }

    public BookInstance(UUID bookUuid, LibraryBranch library) {
        this.uuid = UUID.randomUUID();
        this.bookUuid = bookUuid;
        this.library = library;
    }

    public static BookInstanceBuilder builder() {
        return new BookInstanceBuilder();
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

    public UUID getBookUuid() {
        return bookUuid;
    }

    public void setBookUuid(UUID bookUuid) {
        this.bookUuid = bookUuid;
    }

    public LibraryBranch getLibrary() {
        return library;
    }

    public void setLibrary(LibraryBranch library) {
        this.library = library;
    }

    public BookInstanceResponse toResponse() {
        return BookInstanceResponse.of(this);
    }

    public static class BookInstanceBuilder {
        private UUID bookUuid;
        private LibraryBranch library;

        public BookInstanceBuilder book(UUID bookUuid) {
            this.bookUuid = bookUuid;
            return this;
        }

        public BookInstanceBuilder book(String bookUuid){
            this.bookUuid = UUID.fromString(bookUuid);
            return this;
        }

        public BookInstanceBuilder library(LibraryBranch library) {
            this.library = library;
            return this;
        }

        public BookInstance build() {
            return new BookInstance(bookUuid, library);
        }
    }
}
