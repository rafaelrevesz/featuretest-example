package com.example.featuretest.core;

import lombok.SneakyThrows;

import java.net.URI;

public class UriTools {
    private UriTools() {}

    @SneakyThrows
    public static URI withPath(URI uri, String path) {
        return new URI(uri.getScheme() + "://" + uri.getHost() + ":" + uri.getPort() + path);
    }
}
