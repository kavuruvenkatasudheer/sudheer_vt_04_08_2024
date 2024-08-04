CREATE TABLE url (
    id BIGINT NOT NULL GENERATED ALWAYS AS IDENTITY PRIMARY KEY ,
    short_url VARCHAR(30) UNIQUE NOT NULL,
    destination_url TEXT NOT NULL,
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    expires_at TIMESTAMP
);

CREATE INDEX idx_short_url ON url (short_url);

--insert into url (id, short_url, destination_url) values (1, 'abc@tcs.com', 'tcs@abc.com');