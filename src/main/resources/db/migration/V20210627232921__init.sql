CREATE TABLE IF NOT EXISTS library_branch
(
    id       BIGSERIAL PRIMARY KEY,
    uuid     UUID         NOT NULL,
    name     VARCHAR(255) NOT NULL UNIQUE,
    street   VARCHAR(255),
    state    VARCHAR(255),
    country  VARCHAR(255),
    zip_code VARCHAR(50)
);

CREATE TABLE IF NOT EXISTS book_instance
(
    id         BIGSERIAL PRIMARY KEY,
    uuid       UUID NOT NULL,
    book_uuid  UUID NOT NULL,
    library_id BIGINT REFERENCES library_branch (id)
)