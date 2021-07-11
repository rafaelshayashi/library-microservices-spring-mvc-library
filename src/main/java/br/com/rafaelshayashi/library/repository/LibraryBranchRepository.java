package br.com.rafaelshayashi.library.repository;

import br.com.rafaelshayashi.library.model.LibraryBranch;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;
import java.util.UUID;

public interface LibraryBranchRepository extends JpaRepository<LibraryBranch, Long> {
    Optional<LibraryBranch> findByUuid(UUID bookUuid);

    Optional<LibraryBranch> findByName(String name);
}
