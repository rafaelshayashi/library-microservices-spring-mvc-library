package br.com.rafaelshayashi.library.service;

import br.com.rafaelshayashi.library.client.CatalogueBook;
import br.com.rafaelshayashi.library.controller.request.BookInstanceRequest;
import br.com.rafaelshayashi.library.exception.ResourceNotExistsException;
import br.com.rafaelshayashi.library.model.BookInstance;
import br.com.rafaelshayashi.library.repository.BookInstanceRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class BookInstanceServiceImpl implements BookInstanceService {

    private final BookInstanceRepository repository;
    private final CatalogueBook bookService;
    private final LibraryService libraryService;

    @Autowired
    public BookInstanceServiceImpl(BookInstanceRepository repository, CatalogueBook bookService, LibraryService libraryService) {
        this.repository = repository;
        this.bookService = bookService;
        this.libraryService = libraryService;
    }

    @Override
    public BookInstance create(BookInstanceRequest request) {
        var bookResponse = bookService
                .detailsBook(UUID.fromString(request.getBookUuid()))
                .orElseThrow(() -> new ResourceNotExistsException(request.getBookUuid()));

        var libraryBranch = libraryService
                .find(UUID.fromString(request.getLibraryUuid()))
                .orElseThrow(() -> new ResourceNotExistsException(request.getLibraryUuid()));

        var bookInstance = request.toModel(bookResponse.getUuid(), libraryBranch);
        return repository.save(bookInstance);
    }
}
