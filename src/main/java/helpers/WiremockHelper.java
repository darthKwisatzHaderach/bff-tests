package helpers;

import com.github.tomakehurst.wiremock.client.MappingBuilder;
import com.google.gson.Gson;
import enums.HttpMethod;
import enums.UrlPattern;
import org.apache.http.client.utils.URIBuilder;

import java.net.URISyntaxException;
import java.util.Map;

import static com.github.tomakehurst.wiremock.client.WireMock.aResponse;
import static com.github.tomakehurst.wiremock.client.WireMock.get;
import static com.github.tomakehurst.wiremock.client.WireMock.stubFor;

public class WiremockHelper {

    public static void setMock(HttpMethod method, UrlPattern urlPattern, Map<String, String> queryParams, Object body) {
        Gson gson = new Gson();
        MappingBuilder mappingBuilder = null;

        switch (method) {
            case GET:
                mappingBuilder = get(constructURI(urlPattern, queryParams));
                break;
        }

        mappingBuilder.willReturn(aResponse()
                .withStatus(200)
                .withHeader("Content-Type", "application/json")
                .withBody(gson.toJson(body)));

        stubFor(mappingBuilder);
    }

    private static String constructURI(UrlPattern urlPattern, Map<String, String> queryParams) {

        URIBuilder uriBuilder = null;

        try {
            uriBuilder = new URIBuilder(urlPattern.pattern);
        } catch (URISyntaxException e) {
            e.printStackTrace();
        }

        for (Map.Entry<String, String> entry : queryParams.entrySet()) {
            uriBuilder.addParameter(entry.getKey(), entry.getValue());
        }

        return uriBuilder.toString();
    }
}

