package br.com.rafaelshayashi.library.controller;

import br.com.rafaelshayashi.library.controller.request.LibraryBranchRequest;
import br.com.rafaelshayashi.library.controller.response.LibraryResponse;
import br.com.rafaelshayashi.library.service.LibraryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/branches")
public class LibraryBranchController {

    private final LibraryService service;

    @Autowired
    public LibraryBranchController(LibraryService service) {
        this.service = service;
    }

    @PostMapping
    public ResponseEntity<LibraryResponse> create(@RequestBody LibraryBranchRequest request, UriComponentsBuilder builder) {
        LibraryResponse response = LibraryResponse.of(service.create(request));
        var uri = builder.path("/libraries/{uuid}").buildAndExpand(response.getUuid()).toUri();
        return ResponseEntity.created(uri).body(response);
    }

    @GetMapping
    public Page<LibraryResponse> list(Pageable pageable) {
        List<LibraryResponse> libraries = service.list(pageable)
                .stream()
                .map(LibraryResponse::new)
                .collect(Collectors.toList());
        return new PageImpl<>(libraries);
    }
}
