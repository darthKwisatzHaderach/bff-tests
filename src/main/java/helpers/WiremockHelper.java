package helpers;

import com.github.tomakehurst.wiremock.client.MappingBuilder;
import com.google.gson.Gson;
import enums.HttpMethod;

import static com.github.tomakehurst.wiremock.client.WireMock.aResponse;
import static com.github.tomakehurst.wiremock.client.WireMock.get;
import static com.github.tomakehurst.wiremock.client.WireMock.stubFor;

public class WiremockHelper {

    public static void setMock(HttpMethod method, String endpoint, Object body) {
        Gson gson = new Gson();
        MappingBuilder mappingBuilder = null;

        switch (method) {
            case GET:
                mappingBuilder = get(endpoint);
                break;
        }

        mappingBuilder.willReturn(aResponse()
                .withStatus(200)
                .withHeader("Content-Type", "application/json")
                .withBody(gson.toJson(body)));

        stubFor(mappingBuilder);
    }
}

