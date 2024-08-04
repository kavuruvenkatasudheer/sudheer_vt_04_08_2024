package com.company.url.controller;

import com.company.url.com.company.url.service.ShortnerSVC;
import com.company.url.entity.ExpiryUpdateRequest;
import com.company.url.entity.Url;
import com.company.url.entity.UrlUpdateRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.beans.factory.annotation.Autowired;


@RestController
public class UrlShortenerController {

    @Autowired
    ShortnerSVC shortnerSVC;

    @PostMapping("/shorten-url")
    public ResponseEntity<String> shortenUrl(@RequestBody Url url) {
        return shortnerSVC.shortenUrl(url.getDestinationUrl());
    }

    @PostMapping("/update-short-url")
    public ResponseEntity<Boolean> updateShortUrl(@RequestBody UrlUpdateRequest request) {
        return shortnerSVC.updateShortUrl(request);
    }

    @GetMapping("/get-destination-url")
    public ResponseEntity<String> getDestinationUrl(@RequestBody Url url) {
       return shortnerSVC.getDestinationUrl(url.getShortUrl());
    }

    @PostMapping("/update-expiry")
    public ResponseEntity<Boolean> updateExpiry(@RequestBody ExpiryUpdateRequest request) {
        return shortnerSVC.updateExpiry(request);
    }

    @GetMapping("/{shortenString}")
    public ResponseEntity<String> redirectToFullUrl(@PathVariable String shortenString) {
       return shortnerSVC.redirectToFullUrl(shortenString);
    }

}

