package br.com.rafaelshayashi.library.service;


import br.com.rafaelshayashi.library.controller.request.BookInstanceRequest;
import br.com.rafaelshayashi.library.model.BookInstance;

public interface BookInstanceService {

    BookInstance create(BookInstanceRequest request);
}
