package br.com.rafaelshayashi.library.controller;

import br.com.rafaelshayashi.library.controller.request.BookInstanceRequest;
import br.com.rafaelshayashi.library.controller.response.BookInstanceResponse;
import br.com.rafaelshayashi.library.service.BookInstanceService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

import javax.validation.Valid;

@RestController
@RequestMapping("/books/instances")
public class BookInstanceController {

    private final BookInstanceService service;

    public BookInstanceController(BookInstanceService service) {
        this.service = service;
    }

    @PostMapping
    public ResponseEntity<BookInstanceResponse> create(@RequestBody @Valid BookInstanceRequest request,
                                                       UriComponentsBuilder builder) {

        BookInstanceResponse response = service.create(request).toResponse();
        var uri = builder.path("/books/instances/{uuid}").buildAndExpand(response.getUuid()).toUri();
        return ResponseEntity.created(uri).body(response);
    }
}
