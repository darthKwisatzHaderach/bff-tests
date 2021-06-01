import com.github.tomakehurst.wiremock.client.WireMock;
import enums.HttpMethod;
import enums.UrlPattern;
import helpers.WiremockHelper;
import objects.ProductInfo;
import objects.ProductPrice;
import objects.ProductStockInfo;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.util.HashMap;
import java.util.Map;

import static com.github.tomakehurst.wiremock.client.WireMock.reset;

public class Tests {

    @BeforeClass
    public void beforeClass() {
        WireMock.configureFor("localhost", 8443);
        reset();
    }

    @Test
    public void test() {
        Map<String, String> queryParams = new HashMap<>();
        queryParams.put("productId", "700110");

        ProductInfo productInfo = new ProductInfo();
        WiremockHelper.setMock(HttpMethod.GET, UrlPattern.PRODUCT, queryParams, productInfo);

        ProductPrice productPrice = new ProductPrice();
        WiremockHelper.setMock(HttpMethod.GET, UrlPattern.PRICE, queryParams, productPrice);

        ProductStockInfo productStockInfo = new ProductStockInfo();
        WiremockHelper.setMock(HttpMethod.GET, UrlPattern.STOCK, queryParams, productStockInfo);
    }
}
