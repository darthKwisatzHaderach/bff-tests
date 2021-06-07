import com.github.tomakehurst.wiremock.client.WireMock;
import enums.HttpMethod;
import enums.Source;
import enums.UrlPattern;
import clients.BffClient;
import helpers.WiremockHelper;
import objects.responses.ProductInfoResponse;
import objects.responses.ProductPriceResponse;
import objects.requests.ProductRequest;
import objects.responses.ProductResponse;
import objects.responses.ProductStockInfoResponse;
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
        ProductInfoResponse productInfoResponse = new ProductInfoResponse();
        ProductPriceResponse productPriceResponse = new ProductPriceResponse();
        ProductStockInfoResponse productStockInfoResponse = new ProductStockInfoResponse();

        WiremockHelper.setMock(HttpMethod.POST, UrlPattern.INFO, 200, productInfoResponse);
        WiremockHelper.setMock(HttpMethod.POST, UrlPattern.PRICE, 200, productPriceResponse);
        WiremockHelper.setMock(HttpMethod.POST, UrlPattern.STOCK, 200, productStockInfoResponse);

        ProductResponse productResponse = new ProductResponse(
                productInfoResponse.getTitle(),
                productInfoResponse.getDescription(),
                productInfoResponse.getWeight(),
                productInfoResponse.getHeight(),
                productInfoResponse.getLength(),
                productInfoResponse.getWidth(),
                productPriceResponse.getPrice(),
                productPriceResponse.getCurrency(),
                productStockInfoResponse.getAvailableStock(),
                productStockInfoResponse.getRow(),
                productStockInfoResponse.getShell()
        );

        ProductResponse result = BffClient.postRequest(UrlPattern.PRODUCT, 200, productRequest, ProductResponse.class);

        Assert.assertEquals(result, productResponse);
    }

    @Test
    public void checkAllResponseFieldsForMobileSourceTest() {
        ProductRequest productRequest = new ProductRequest("700110", Source.MOBILE);
        ProductInfoResponse productInfoResponse = new ProductInfoResponse();
        ProductPriceResponse productPriceResponse = new ProductPriceResponse();
        ProductStockInfoResponse productStockInfoResponse = new ProductStockInfoResponse();

        WiremockHelper.setMock(HttpMethod.POST, UrlPattern.INFO, 200, productInfoResponse);
        WiremockHelper.setMock(HttpMethod.POST, UrlPattern.PRICE, 200, productPriceResponse);
        WiremockHelper.setMock(HttpMethod.POST, UrlPattern.STOCK, 200, productStockInfoResponse);

        ProductResponse productResponse = new ProductResponse(
                productInfoResponse.getTitle(),
                productPriceResponse.getPrice(),
                productPriceResponse.getCurrency()
        );

        ProductResponse result = BffClient.postRequest(UrlPattern.PRODUCT, 200, productRequest, ProductResponse.class);

        Assert.assertEquals(result, productResponse);
    }

    @Test
    public void checkAllResponseFieldsForDesktopSourceTest() {
        ProductRequest productRequest = new ProductRequest("700110", Source.DESKTOP);
        ProductInfoResponse productInfoResponse = new ProductInfoResponse();
        ProductPriceResponse productPriceResponse = new ProductPriceResponse();
        ProductStockInfoResponse productStockInfoResponse = new ProductStockInfoResponse();

        WiremockHelper.setMock(HttpMethod.POST, UrlPattern.INFO, 200, productInfoResponse);
        WiremockHelper.setMock(HttpMethod.POST, UrlPattern.PRICE, 200, productPriceResponse);
        WiremockHelper.setMock(HttpMethod.POST, UrlPattern.STOCK, 200, productStockInfoResponse);

        ProductResponse productResponse = new ProductResponse(
                productInfoResponse.getTitle(),
                productInfoResponse.getDescription(),
                productInfoResponse.getWeight(),
                productInfoResponse.getHeight(),
                productInfoResponse.getLength(),
                productInfoResponse.getWidth(),
                productPriceResponse.getPrice(),
                productPriceResponse.getCurrency()
        );

        ProductResponse result = BffClient.postRequest(UrlPattern.PRODUCT, 200, productRequest, ProductResponse.class);

        Assert.assertEquals(result, productResponse);
    }
}
