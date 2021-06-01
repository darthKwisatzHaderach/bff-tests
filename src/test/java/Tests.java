import com.github.tomakehurst.wiremock.client.WireMock;
import enums.HttpMethod;
import helpers.WiremockHelper;
import objects.ProductInfo;
import objects.ProductPrice;
import objects.ProductStockInfo;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import static com.github.tomakehurst.wiremock.client.WireMock.reset;

public class Tests {

    @BeforeClass
    public void beforeClass() {
        WireMock.configureFor("localhost", 8443);
        reset();
    }

    @Test
    public void test() {
        ProductInfo productInfo = new ProductInfo();
        WiremockHelper.setMock(HttpMethod.POST, "/info", productInfo);

        ProductPrice productPrice = new ProductPrice();
        WiremockHelper.setMock(HttpMethod.POST, "/price", productPrice);

        ProductStockInfo productStockInfo = new ProductStockInfo();
        WiremockHelper.setMock(HttpMethod.POST, "/stock", productStockInfo);
    }
}
