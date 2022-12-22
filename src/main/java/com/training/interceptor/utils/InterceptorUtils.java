package com.training.interceptor.utils;

import io.grpc.Metadata;

import java.util.Optional;

public class InterceptorUtils {
    protected static final String REQUEST_ID_HEADER = "id";

    protected Optional<String> getRequestIdHeader(Metadata headers) {
        return Optional.ofNullable(headers)
                .map(h -> h.get(Metadata.Key.of(REQUEST_ID_HEADER, Metadata.ASCII_STRING_MARSHALLER)));
    }

    protected void putRequestIdHeader(Metadata incomingHeaders, String insertedHeader) {
        incomingHeaders.put(Metadata.Key.of(REQUEST_ID_HEADER, Metadata.ASCII_STRING_MARSHALLER), insertedHeader);
    }
}
