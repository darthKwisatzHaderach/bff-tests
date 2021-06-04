import com.github.tomakehurst.wiremock.client.WireMock;
import enums.HttpMethod;
import enums.Source;
import enums.UrlPattern;
import clients.BffClient;
import helpers.WiremockHelper;
import objects.ProductInfo;
import objects.ProductPrice;
import objects.ProductRequest;
import objects.ProductResponse;
import objects.ProductStockInfo;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import static com.github.tomakehurst.wiremock.client.WireMock.reset;

public class Tests {

    @BeforeClass
    public void beforeClass() {
        WireMock.configureFor(WiremockHelper.host, WiremockHelper.port);
        reset();
    }

    @Test
    public void checkAllResponseFieldsForOfflineSourceTest() {
        ProductRequest productRequest = new ProductRequest("700110", Source.OFFLINE);
        ProductInfo productInfo = new ProductInfo();
        ProductPrice productPrice = new ProductPrice();
        ProductStockInfo productStockInfo = new ProductStockInfo();

        WiremockHelper.setMock(HttpMethod.POST, UrlPattern.INFO, 200, productInfo);
        WiremockHelper.setMock(HttpMethod.POST, UrlPattern.PRICE, 200, productPrice);
        WiremockHelper.setMock(HttpMethod.POST, UrlPattern.STOCK, 200, productStockInfo);

        ProductResponse productResponse = new ProductResponse(
                productInfo.getTitle(),
                productInfo.getDescription(),
                productInfo.getWeight(),
                productInfo.getHeight(),
                productInfo.getLength(),
                productInfo.getWidth(),
                productPrice.getPrice(),
                productPrice.getCurrency(),
                productStockInfo.getAvailableStock(),
                productStockInfo.getRow(),
                productStockInfo.getShell()
        );

        ProductResponse result = BffClient.postRequest(UrlPattern.PRODUCT, 200, productRequest, ProductResponse.class);

        Assert.assertEquals(result, productResponse);
    }

    @Test
    public void checkAllResponseFieldsForMobileSourceTest() {
        ProductRequest productRequest = new ProductRequest("700110", Source.MOBILE);
        ProductInfo productInfo = new ProductInfo();
        ProductPrice productPrice = new ProductPrice();
        ProductStockInfo productStockInfo = new ProductStockInfo();

        WiremockHelper.setMock(HttpMethod.POST, UrlPattern.INFO, 200, productInfo);
        WiremockHelper.setMock(HttpMethod.POST, UrlPattern.PRICE, 200, productPrice);
        WiremockHelper.setMock(HttpMethod.POST, UrlPattern.STOCK, 200, productStockInfo);

        ProductResponse productResponse = new ProductResponse(
                productInfo.getTitle(),
                productPrice.getPrice(),
                productPrice.getCurrency()
        );

        ProductResponse result = BffClient.postRequest(UrlPattern.PRODUCT, 200, productRequest, ProductResponse.class);

        Assert.assertEquals(result, productResponse);
    }

    @Test
    public void checkAllResponseFieldsForDesktopSourceTest() {
        ProductRequest productRequest = new ProductRequest("700110", Source.DESKTOP);
        ProductInfo productInfo = new ProductInfo();
        ProductPrice productPrice = new ProductPrice();
        ProductStockInfo productStockInfo = new ProductStockInfo();

        WiremockHelper.setMock(HttpMethod.POST, UrlPattern.INFO, 200, productInfo);
        WiremockHelper.setMock(HttpMethod.POST, UrlPattern.PRICE, 200, productPrice);
        WiremockHelper.setMock(HttpMethod.POST, UrlPattern.STOCK, 200, productStockInfo);

        ProductResponse productResponse = new ProductResponse(
                productInfo.getTitle(),
                productInfo.getDescription(),
                productInfo.getWeight(),
                productInfo.getHeight(),
                productInfo.getLength(),
                productInfo.getWidth(),
                productPrice.getPrice(),
                productPrice.getCurrency()
        );

        ProductResponse result = BffClient.postRequest(UrlPattern.PRODUCT, 200, productRequest, ProductResponse.class);

        Assert.assertEquals(result, productResponse);
    }
}
