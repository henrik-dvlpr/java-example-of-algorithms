package de.github.henrik_dvlpr.test_data;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import de.github.henrik_dvlpr.Main;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpRequest;
import java.util.List;

import static com.fasterxml.jackson.databind.DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES;
import static java.net.http.HttpClient.newHttpClient;
import static java.net.http.HttpResponse.BodyHandlers.ofString;
import static java.nio.charset.Charset.defaultCharset;

public class FindUniversities {
    public List<University> find() throws IOException, InterruptedException {
        final var getRequest = HttpRequest
                .newBuilder()
                .GET()
                .uri(URI.create("http://universities.hipolabs.com/search?country=United+States"))
                .header("Accept", "application/json")
                .build();
        final var response = newHttpClient().send(getRequest, ofString(defaultCharset()));
        return new ObjectMapper()
                .configure(FAIL_ON_UNKNOWN_PROPERTIES, false)
                .readValue(response.body(), new TypeReference<>() {
                });
    }
}
