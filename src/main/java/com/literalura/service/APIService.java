package com.literalura.service;

import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

public class APIService {

    private final HttpClient client = HttpClient.newHttpClient();

    public String buscarLibro(String titulo) {
        try {
            String url = "https://gutendex.com/books/?search=" + titulo.replace(" ", "+");
            HttpRequest request = HttpRequest.newBuilder()
                    .uri(URI.create(url))
                    .GET()
                    .build();

            HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());
            return response.body();

        } catch (Exception e) {
            System.out.println("Error al consultar la API: " + e.getMessage());
            return null;
        }
    }
}