package com.carignano.nasa_exercise.helper;

import org.jspecify.annotations.NullMarked;
import org.springframework.http.HttpRequest;
import org.springframework.http.client.support.HttpRequestWrapper;

import java.net.URI;

public class ClientHttpRequestWrapper extends HttpRequestWrapper {

    public ClientHttpRequestWrapper(HttpRequest request) {
        super(request);
    }

    public static ClientHttpRequestWrapper replaceUri(HttpRequest request, URI newUri) {
        return new ClientHttpRequestWrapper(request) {
            @Override
            @NullMarked
            public URI getURI() {
                return newUri;
            }
        };
    }
}