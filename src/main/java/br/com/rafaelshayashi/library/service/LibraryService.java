package br.com.rafaelshayashi.library.service;

import br.com.rafaelshayashi.library.controller.request.LibraryBranchRequest;
import br.com.rafaelshayashi.library.model.LibraryBranch;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Optional;
import java.util.UUID;

public interface LibraryService {

    LibraryBranch create(LibraryBranchRequest request);

    Optional<LibraryBranch> find(UUID bookUuid);

    Page<LibraryBranch> list(Pageable pageable);
}
