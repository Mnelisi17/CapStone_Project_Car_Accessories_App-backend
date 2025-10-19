package za.co.cput.util;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.time.Duration;
import java.util.Map;

public class ApiClient {

    private final HttpClient httpClient;
    private final String apiKey;
    private final String baseUrl;

    public ApiClient(String baseUrl, String apiKey) {
        this.httpClient = HttpClient.newBuilder()
                .connectTimeout(Duration.ofSeconds(30))
                .build();
        this.apiKey = apiKey;
        this.baseUrl = baseUrl;
    }

    public HttpResponse<String> post(String endpoint, String jsonBody) throws IOException, InterruptedException {
        return makeSignedRequest("POST", endpoint, jsonBody, null);
    }

    public HttpResponse<String> get(String endpoint) throws IOException, InterruptedException {
        return makeSignedRequest("GET", endpoint, null, null);
    }

    public HttpResponse<String> put(String endpoint, String jsonBody) throws IOException, InterruptedException {
        return makeSignedRequest("PUT", endpoint, jsonBody, null);
    }

    public HttpResponse<String> delete(String endpoint) throws IOException, InterruptedException {
        return makeSignedRequest("DELETE", endpoint, null, null);
    }

    private HttpResponse<String> makeSignedRequest(String method, String endpoint,
                                                   String body, Map<String, String> queryParams)
            throws IOException, InterruptedException {


        HttpRequest.Builder requestBuilder = HttpRequest.newBuilder()
                .uri(URI.create(baseUrl + endpoint))
                .header("Content-Type", "application/json")
                .header("Authorization", "Bearer " + apiKey)
                .timeout(Duration.ofSeconds(30));


        switch (method.toUpperCase()) {
            case "GET":
                requestBuilder.GET();
                break;
            case "POST":
                requestBuilder.POST(HttpRequest.BodyPublishers.ofString(body != null ? body : ""));
                break;
            case "PUT":
                requestBuilder.PUT(HttpRequest.BodyPublishers.ofString(body != null ? body : ""));
                break;
            case "DELETE":
                requestBuilder.DELETE();
                break;
            default:
                throw new IllegalArgumentException("Unsupported HTTP method: " + method);
        }

        HttpRequest request = requestBuilder.build();
        return httpClient.send(request, HttpResponse.BodyHandlers.ofString());
    }

    public static void main(String[] args) {
        try {
            ApiClient client = new ApiClient("http://localhost:8080", "valid-api-key");

            String orderJson = "{\"customerId\":\"123\",\"items\":[{\"productId\":\"456\",\"quantity\":2}]}";
            HttpResponse<String> response = client.post("/api/order/create", orderJson);

            System.out.println("Response Code: " + response.statusCode());
            System.out.println("Response Body: " + response.body());

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}