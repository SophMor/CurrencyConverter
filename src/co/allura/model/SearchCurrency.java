package co.allura.model;


import com.google.gson.Gson;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

public class SearchCurrency {
    public CurrencyRecord currency(String currencyBase, String target){
           URI direccionURI = URI.create("https://v6.exchangerate-api.com/v6/a9c98b8e0b70f46e715db7b0/pair/" +currencyBase+"/"+target);
           HttpClient client = HttpClient.newHttpClient();
           HttpRequest request = HttpRequest.newBuilder()
                   .uri(direccionURI)
                   .build();
        try {
           HttpResponse<String> response = null;
           response = client
                   .send(request, HttpResponse.BodyHandlers.ofString());

            return new Gson().fromJson(response.body(), CurrencyRecord.class);
       } catch (RuntimeException|IOException|InterruptedException e) {
           throw new RuntimeException("Currency not found");
       }

    }

}
