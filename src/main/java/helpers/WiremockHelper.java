package helpers;

import com.github.tomakehurst.wiremock.client.MappingBuilder;
import com.github.tomakehurst.wiremock.http.Fault;
import com.google.gson.Gson;
import enums.HttpMethod;
import enums.UrlPattern;

import static com.github.tomakehurst.wiremock.client.WireMock.aResponse;
import static com.github.tomakehurst.wiremock.client.WireMock.equalToJson;
import static com.github.tomakehurst.wiremock.client.WireMock.get;
import static com.github.tomakehurst.wiremock.client.WireMock.post;
import static com.github.tomakehurst.wiremock.client.WireMock.stubFor;

public class WiremockHelper {

    public static final String host = "localhost";
    public static final int port = 8443;

    public static void setMock(HttpMethod method, UrlPattern urlPattern, int statusCode, Object requestBody, Object responseBody) {
        Gson gson = new Gson();

        MappingBuilder mappingBuilder = createMappingBuilder(method, urlPattern)
                .withRequestBody(equalToJson(gson.toJson(requestBody)))
                .willReturn(aResponse()
                        .withStatus(statusCode)
                        .withHeader("Content-Type", "application/json")
                        .withBody(gson.toJson(responseBody)));

        stubFor(mappingBuilder);
    }

    public static void setMock(HttpMethod method, UrlPattern urlPattern, int statusCode, Object body) {
        Gson gson = new Gson();

        MappingBuilder mappingBuilder = createMappingBuilder(method, urlPattern)
                .willReturn(aResponse()
                        .withStatus(statusCode)
                        .withHeader("Content-Type", "application/json")
                        .withBody(gson.toJson(body)));

        stubFor(mappingBuilder);
    }

    public static void setMockWithFault(HttpMethod method, UrlPattern urlPattern, Fault fault) {
        MappingBuilder mappingBuilder = createMappingBuilder(method, urlPattern)
                .willReturn(aResponse().withFault(fault));

        stubFor(mappingBuilder);
    }

    private static MappingBuilder createMappingBuilder(HttpMethod method, UrlPattern urlPattern) {
        MappingBuilder mappingBuilder = null;

        switch (method) {
            case POST:
                mappingBuilder = post(urlPattern.pattern);
                break;
            case GET:
                mappingBuilder = get(urlPattern.pattern);
        }

        return mappingBuilder;
    }
}

