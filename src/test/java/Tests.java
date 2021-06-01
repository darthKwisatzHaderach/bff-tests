import com.github.tomakehurst.wiremock.client.WireMock;
import org.testng.annotations.Test;

import static com.github.tomakehurst.wiremock.client.WireMock.get;
import static com.github.tomakehurst.wiremock.client.WireMock.ok;
import static com.github.tomakehurst.wiremock.client.WireMock.stubFor;

public class Tests {

    @Test
    public void test() {
        WireMock.configureFor("localhost", 8443);

        stubFor(get("/product?productId=700110")
                .willReturn(ok()
                        .withHeader("Content-Type", "text/xml")
                        .withBody("{\n" +
                                "\t\"title\": \"title\",\n" +
                                "\t\"description\": \"description\",\n" +
                                "\t\"weight\": 3,\n" +
                                "\t\"height\": 0.1,\n" +
                                "\t\"length\": 0.1,\n" +
                                "\t\"width\": 0.1\n" +
                                "}")));

/*
        stubFor(get("/product?productId=700110")
                .willReturn(aResponse().withFault(Fault.CONNECTION_RESET_BY_PEER)));

        stubFor(get("/product?productId=700110")
                .willReturn(ok()
                        .withHeader("Content-Type", "text/xml")
                        .withBody("123")));

        stubFor(get("/product?productId=700110")
                .willReturn(notFound()));
*/

        stubFor(get("/price?productId=700110")
                .willReturn(ok()
                        .withHeader("Content-Type", "text/xml")
                        .withBody("{\n" +
                                "\t\"price\": 5,\n" +
                                "\t\"currency\": \"RUR\"\n" +
                                "}")));

/*
        stubFor(get("/price?productId=700110")
                .willReturn(aResponse().withFault(Fault.CONNECTION_RESET_BY_PEER)));

        stubFor(get("/price?productId=700110")
                .willReturn(ok()
                        .withHeader("Content-Type", "text/xml")
                        .withBody("123")));

        stubFor(get("/price?productId=700110")
                .willReturn(notFound()));
*/

        stubFor(get("/stock?productId=700110")
                .willReturn(ok()
                        .withHeader("Content-Type", "text/xml")
                        .withBody("{\n" +
                                "\t\"availableStock\": 20,\n" +
                                "\t\"row\": 1,\n" +
                                "\t\"shell\": 3\n" +
                                "}")));

/*
        stubFor(get("/stock?productId=700110")
                .willReturn(aResponse().withFault(Fault.CONNECTION_RESET_BY_PEER)));

        stubFor(get("/stock?productId=700110")
                .willReturn(ok()
                        .withHeader("Content-Type", "text/xml")
                        .withBody("123")));

        stubFor(get("/stock?productId=700110")
                .willReturn(notFound()));
*/
    }
}
