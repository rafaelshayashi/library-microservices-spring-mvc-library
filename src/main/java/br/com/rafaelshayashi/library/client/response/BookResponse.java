package br.com.rafaelshayashi.library.client.response;

import br.com.rafaelshayashi.library.client.response.BookValueResponse;

public class BookResponse {

    private String uuid;
    private String title;
    private String subTitle;
    private BookValueResponse value;
    private String description;
    private String isbn;

    public String getUuid() {
        return uuid;
    }

    public void setUuid(String uuid) {
        this.uuid = uuid;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getSubTitle() {
        return subTitle;
    }

    public void setSubTitle(String subTitle) {
        this.subTitle = subTitle;
    }

    public BookValueResponse getValue() {
        return value;
    }

    public void setValue(BookValueResponse value) {
        this.value = value;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getIsbn() {
        return isbn;
    }

    public void setIsbn(String isbn) {
        this.isbn = isbn;
    }
}
