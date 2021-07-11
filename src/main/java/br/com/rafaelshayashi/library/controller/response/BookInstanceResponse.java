package br.com.rafaelshayashi.library.controller.response;

import br.com.rafaelshayashi.library.model.BookInstance;

public class BookInstanceResponse {

    private final String uuid;
    private final String bookUuid;
    private final LibraryResponse library;

    public BookInstanceResponse(BookInstance bookInstance) {
        this.uuid = bookInstance.getUuid().toString();
        this.bookUuid = bookInstance.getBookUuid().toString();
        this.library = LibraryResponse.of(bookInstance.getLibrary());
    }

    public static BookInstanceResponse of(BookInstance bookInstance) {
        return new BookInstanceResponse(bookInstance);
    }

    public String getUuid() {
        return uuid;
    }

    public String getBookUuid() {
        return bookUuid;
    }

    public LibraryResponse getLibrary() {
        return library;
    }
}
