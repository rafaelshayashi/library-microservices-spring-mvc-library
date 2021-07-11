package br.com.rafaelshayashi.library.service;

import br.com.rafaelshayashi.library.client.response.BookResponse;
import br.com.rafaelshayashi.library.client.CatalogueBook;
import br.com.rafaelshayashi.library.controller.request.BookInstanceRequest;
import br.com.rafaelshayashi.library.model.BookInstance;
import br.com.rafaelshayashi.library.model.LibraryBranch;
import br.com.rafaelshayashi.library.repository.BookInstanceRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.Optional;
import java.util.UUID;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.doReturn;

@ExtendWith(SpringExtension.class)
@SpringBootTest
class BookInstanceServiceTest {

    @MockBean
    private BookInstanceRepository repository;

    @MockBean
    private CatalogueBook catalogueBook;

    @MockBean
    private LibraryService libraryService;

    @Autowired
    private BookInstanceService service;

    @Test
    @DisplayName("Should create a book instance")
    void should_create_a_book_instance() {
        BookResponse bookResponseMock = new BookResponse();
        bookResponseMock.setTitle("Effective java");
        bookResponseMock.setUuid("c5208b8d-51c4-413d-8663-c6fdb94fe5c7");
        doReturn(Optional.of(bookResponseMock)).when(catalogueBook).detailsBook(any(UUID.class));

        LibraryBranch libraryMock = LibraryBranch.builder().name("The New York Public Library").build();
        BookInstance bookInstanceMock = BookInstance.builder().book(UUID.randomUUID()).library(libraryMock).build();

        doReturn(bookInstanceMock).when(repository).save(any());
        doReturn(Optional.of(libraryMock)).when(libraryService).find(any());

        BookInstanceRequest request = new BookInstanceRequest();
        request.setBookUuid("c5208b8d-51c4-413d-8663-c6fdb94fe5c7");
        request.setLibraryUuid("03e9ffcc-2191-4dd1-8d05-86976a5f431d");

        BookInstance bookInstance = service.create(request);

        // TODO improve this test
        Assertions.assertEquals("The New York Public Library", bookInstance.getLibrary().getName());

    }

}
