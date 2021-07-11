package br.com.rafaelshayashi.library.exception;

public class ResourceNotExistsException extends RuntimeException {

    private final String resourceUuid;

    public ResourceNotExistsException(String resourceUuid) {
        this.resourceUuid = resourceUuid;
    }

    public String getResourceUuid() {
        return resourceUuid;
    }
}
