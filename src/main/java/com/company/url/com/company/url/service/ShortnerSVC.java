package com.company.url.com.company.url.service;

import com.company.url.entity.ExpiryUpdateRequest;
import com.company.url.entity.Url;
import com.company.url.entity.UrlUpdateRequest;
import com.company.url.repository.UrlRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;
import java.net.URI;
import java.time.LocalDateTime;
import java.util.Optional;
import java.util.UUID;

@Service
public class ShortnerSVC {

    @Autowired
    private UrlRepository urlRepository;
    private String generateShortUrl() {
        return "http://localhost:8080/" + UUID.randomUUID().toString().substring(0, 10);
    }

    public ResponseEntity<String> shortenUrl(String destinationUrl) {
        String shortUrl;
        do {
            shortUrl = generateShortUrl();
        } while (urlRepository.findByShortUrl(shortUrl).isPresent());
        Url url = new Url(shortUrl, destinationUrl, LocalDateTime.now().plusMonths(10));
        System.out.println(url);
        urlRepository.save(url);
        return new ResponseEntity<>(shortUrl,HttpStatus.CREATED);
    }

    public ResponseEntity<Boolean> updateShortUrl( UrlUpdateRequest request) {
        Optional<Url> urlOptional = urlRepository.findByShortUrl(request.getShortUrl());
        if (urlOptional.isPresent()) {
            Url url = urlOptional.get();
            url.setDestinationUrl(request.getDestinationUrl());
            urlRepository.save(url);
            return new ResponseEntity<>(true,HttpStatus.OK);
        } else {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Short URL not found");
        }
    }
    public ResponseEntity<String> getDestinationUrl( String shortUrl) {
        Url url = urlRepository.findByShortUrl(shortUrl)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Short URL not found"));
        return new ResponseEntity<>(url.getDestinationUrl(),HttpStatus.FOUND);
    }
    public ResponseEntity<Boolean> updateExpiry( ExpiryUpdateRequest request) {
        Optional<Url> urlOptional = urlRepository.findByShortUrl(request.getShortUrl());
        if (urlOptional.isPresent()) {
            Url url = urlOptional.get();
            url.setExpiresAt(url.getExpiresAt().plusDays(request.getDaysToAdd()));
            urlRepository.save(url);
            return new ResponseEntity<>(true,HttpStatus.OK);
        } else {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Short URL not found");
        }
    }
    public ResponseEntity<String> redirectToFullUrl( String shortenString) {
        shortenString="http://localhost:8080/"+shortenString;
        Url url = urlRepository.findByShortUrl(shortenString)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Short URL not found"));

        HttpHeaders headers = new HttpHeaders();
        headers.setLocation(URI.create(url.getDestinationUrl()));
        return new ResponseEntity<>(headers, HttpStatus.FOUND);
    }
}
