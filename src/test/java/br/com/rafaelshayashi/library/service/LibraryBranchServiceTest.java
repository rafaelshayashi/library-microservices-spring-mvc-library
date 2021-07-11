package br.com.rafaelshayashi.library.service;

import br.com.rafaelshayashi.library.controller.request.AddressRequest;
import br.com.rafaelshayashi.library.controller.request.LibraryBranchRequest;
import br.com.rafaelshayashi.library.exception.ResourceAlreadyExistsException;
import br.com.rafaelshayashi.library.model.Address;
import br.com.rafaelshayashi.library.model.LibraryBranch;
import br.com.rafaelshayashi.library.repository.LibraryBranchRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentMatchers;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.Optional;
import java.util.UUID;

@ExtendWith(SpringExtension.class)
@SpringBootTest(classes = LibraryServiceImpl.class)
class LibraryBranchServiceTest {

    @MockBean
    private LibraryBranchRepository repository;

    @Autowired
    private LibraryService service;

    @Test
    @DisplayName("Should create a library")
    void should_create_a_library() {
        // getting mock objects and mock requests
        LibraryBranch libraryMock = getLibraryBranchMock();
        LibraryBranchRequest libraryRequest = getLibraryBranchRequest();

        // Setting up mockito
        Mockito.doReturn(Optional.empty()).when(repository).findByName(ArgumentMatchers.any(String.class));
        Mockito.doReturn(libraryMock).when(repository).save(ArgumentMatchers.any(LibraryBranch.class));

        // Calling the service
        LibraryBranch library = service.create(libraryRequest);

        // Assertions
        Assertions.assertEquals("Biblioteca mario de andrade", library.getName());
    }

    @Test
    @DisplayName("Try to create a already existing library")
    void try_to_create_a_already_existing_library() {
        // getting mock objects and mock requests
        LibraryBranchRequest libraryRequest = getLibraryBranchRequest();
        LibraryBranch libraryMock = getLibraryBranchMock();

        // Setting up mockito
        Mockito.doReturn(Optional.of(libraryMock)).when(repository).findByName(ArgumentMatchers.any(String.class));

        // Assertions
        Assertions.assertThrows(ResourceAlreadyExistsException.class, () -> service.create(libraryRequest));
    }

    @Test
    @DisplayName("Service - Should find a library by uuid")
    void should_find_a_library_by_uuid() {
        LibraryBranch libraryBranchMock = getLibraryBranchMock();

        Mockito.doReturn(Optional.of(libraryBranchMock)).when(repository).findByUuid(ArgumentMatchers.any(UUID.class));

        Optional<LibraryBranch> libraryBranch = service.find(UUID.randomUUID());

        Assertions.assertTrue(libraryBranch.isPresent());
        Assertions.assertEquals(libraryBranchMock.getName(), libraryBranch.get().getName());
    }

    private LibraryBranchRequest getLibraryBranchRequest() {
        LibraryBranchRequest libraryRequest = new LibraryBranchRequest();
        libraryRequest.setName("Biblioteca mario de andrade");
        AddressRequest addressRequest = new AddressRequest();
        addressRequest.setStreet("Rua da Consolação, 94");
        addressRequest.setState("São Paulo");
        addressRequest.setCountry("Brasil");
        addressRequest.setZipCode("01302-000");
        libraryRequest.setAddress(addressRequest);
        return libraryRequest;
    }

    private LibraryBranch getLibraryBranchMock() {
        Address addressMock = Address.builder()
                .street("Rua da Consolação, 94")
                .state("São Paulo")
                .country("Brasil")
                .zipCode("01302-000")
                .build();

        return LibraryBranch.builder()
                .name("Biblioteca mario de andrade")
                .address(addressMock)
                .build();
    }
}
