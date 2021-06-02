import com.github.tomakehurst.wiremock.client.WireMock;
import enums.HttpMethod;
import enums.Source;
import enums.UrlPattern;
import helpers.WiremockHelper;
import objects.ProductInfo;
import objects.ProductPrice;
import objects.ProductRequest;
import objects.ProductStockInfo;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import static com.github.tomakehurst.wiremock.client.WireMock.reset;
import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;

public class Tests {

    @BeforeClass
    public void beforeClass() {
        WireMock.configureFor("localhost", 8443);
        reset();
    }

    @Test
    public void checkAllResponseFieldsForOfflineSourceTest() {
        ProductInfo productInfo = new ProductInfo();
        WiremockHelper.setMock(HttpMethod.POST, UrlPattern.INFO, productInfo);

        ProductPrice productPrice = new ProductPrice();
        WiremockHelper.setMock(HttpMethod.POST, UrlPattern.PRICE, productPrice);

        ProductStockInfo productStockInfo = new ProductStockInfo();
        WiremockHelper.setMock(HttpMethod.POST, UrlPattern.STOCK, productStockInfo);

        ProductRequest productRequest = new ProductRequest();
        productRequest.setProductId("700110");
        productRequest.setSource(Source.OFFLINE);

        given()
                .body(productRequest)
                .when()
                .post("http://localhost:8000/v1/product")
                .then()
                .body("title", equalTo("Title"))
                .body("description", equalTo("Description"))
                .body("weight", equalTo(3.0F))
                .body("height", equalTo(0.1F))
                .body("length", equalTo(0.1F))
                .body("width", equalTo(0.1F))
                .body("price", equalTo(5.0F))
                .body("currency", equalTo("RUR"))
                .body("availableStock", equalTo(20.0F))
                .body("row", equalTo(1))
                .body("shell", equalTo(3));
    }
}
