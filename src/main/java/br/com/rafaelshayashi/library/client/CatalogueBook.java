package br.com.rafaelshayashi.library.client;

import br.com.rafaelshayashi.library.client.response.BookResponse;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.Optional;
import java.util.UUID;

@FeignClient(value = "catalogue", url = "http://localhost:8080")
public interface CatalogueBook {

    @RequestMapping(method = RequestMethod.GET, value = "/books/{uuid}")
    Optional<BookResponse> detailsBook(@PathVariable UUID uuid);
}
