package br.com.rafaelshayashi.library.controller.request;

import br.com.rafaelshayashi.library.model.BookInstance;
import br.com.rafaelshayashi.library.model.LibraryBranch;

import javax.validation.constraints.NotEmpty;

public class BookInstanceRequest {

    @NotEmpty
    private String bookUuid;
    @NotEmpty
    private String libraryUuid;

    public String getBookUuid() {
        return bookUuid;
    }

    public void setBookUuid(String bookUuid) {
        this.bookUuid = bookUuid;
    }

    public String getLibraryUuid() {
        return libraryUuid;
    }

    public void setLibraryUuid(String libraryUuid) {
        this.libraryUuid = libraryUuid;
    }

    public BookInstance toModel(String bookUuid, LibraryBranch library) {
        return BookInstance.builder().book(bookUuid).library(library).build();
    }
}
