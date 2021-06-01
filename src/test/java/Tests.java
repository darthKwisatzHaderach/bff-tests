import com.github.tomakehurst.wiremock.client.WireMock;
import com.google.gson.Gson;
import org.testng.annotations.Test;

import static com.github.tomakehurst.wiremock.client.WireMock.get;
import static com.github.tomakehurst.wiremock.client.WireMock.ok;
import static com.github.tomakehurst.wiremock.client.WireMock.reset;
import static com.github.tomakehurst.wiremock.client.WireMock.stubFor;

public class Tests {

    @Test
    public void test() {
        Gson gson = new Gson();
        WireMock.configureFor("localhost", 8443);
        reset();

        ProductInfo productInfo = new ProductInfo();

        stubFor(get("/product?productId=700110")
                .willReturn(ok()
                        .withHeader("Content-Type", "text/xml")
                        .withBody(gson.toJson(productInfo))));

        ProductPrice productPrice = new ProductPrice();

        stubFor(get("/price?productId=700110")
                .willReturn(ok()
                        .withHeader("Content-Type", "text/xml")
                        .withBody(gson.toJson(productPrice))));


        ProductStockInfo productStockInfo = new ProductStockInfo();

        stubFor(get("/stock?productId=700110")
                .willReturn(ok()
                        .withHeader("Content-Type", "text/xml")
                        .withBody(gson.toJson(productStockInfo))));
    }
}
