package br.com.rafaelshayashi.library.repository;

import br.com.rafaelshayashi.library.model.BookInstance;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BookInstanceRepository extends JpaRepository<BookInstance, Long> {
}
