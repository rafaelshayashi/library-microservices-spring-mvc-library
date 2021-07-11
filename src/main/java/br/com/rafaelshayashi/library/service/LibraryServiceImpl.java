package br.com.rafaelshayashi.library.service;

import br.com.rafaelshayashi.library.controller.request.LibraryBranchRequest;
import br.com.rafaelshayashi.library.exception.ResourceAlreadyExistsException;
import br.com.rafaelshayashi.library.model.LibraryBranch;
import br.com.rafaelshayashi.library.repository.LibraryBranchRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.UUID;

@Service
public class LibraryServiceImpl implements LibraryService {

    private final LibraryBranchRepository repository;

    @Autowired
    public LibraryServiceImpl(LibraryBranchRepository repository) {
        this.repository = repository;
    }

    @Override
    public LibraryBranch create(LibraryBranchRequest request) {
        if (repository.findByName(request.getName()).isPresent()) {
            throw new ResourceAlreadyExistsException();
        }
        return repository.save(request.toModel());
    }

    @Override
    public Optional<LibraryBranch> find(UUID bookUuid) {
        return repository.findByUuid(bookUuid);
    }

    @Override
    public Page<LibraryBranch> list(Pageable pageable) {
        return repository.findAll(pageable);
    }
}
