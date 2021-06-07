package stepdefs;

import com.github.tomakehurst.wiremock.client.WireMock;
import com.google.inject.Inject;
import enums.HttpMethod;
import enums.Source;
import enums.UrlPattern;
import clients.BffClient;
import helpers.WiremockHelper;
import io.cucumber.java.ru.Когда;
import io.cucumber.java.ru.Пусть;
import io.cucumber.java.ru.Тогда;
import objects.requests.ProductInfoRequest;
import objects.requests.ProductPriceRequest;
import objects.requests.ProductRequest;
import objects.requests.ProductStockInfoRequest;
import objects.responses.ProductResponse;
import org.testng.Assert;

import static com.github.tomakehurst.wiremock.client.WireMock.reset;

public class BffProcessingRequestSteps {

    @Inject
    private World world;

    @Пусть("есть внешние сервисы для корректной работы BFF")
    public void есть_внешние_сервисы_для_корректной_работы_bff() {
        WireMock.configureFor(WiremockHelper.host, WiremockHelper.port);
        reset();

        WiremockHelper.setMock(HttpMethod.POST, UrlPattern.INFO, 200, world.productInfoRequest, world.productInfoResponse);
        WiremockHelper.setMock(HttpMethod.POST, UrlPattern.PRICE, 200, world.productPriceRequest, world.productPriceResponse);
        WiremockHelper.setMock(HttpMethod.POST, UrlPattern.STOCK, 200, world.productStockInfoRequest, world.productStockInfoResponse);
    }

    @Когда("клиент выполняет запрос на агрегацию информации о продукте для источника {string}")
    public void клиент_выполняет_запрос_на_агрегацию_информации_о_продукте_для_источника(String source) {
        ProductRequest productRequest = new ProductRequest("700110", Source.valueOf(source));
        world.productResponse = BffClient.postRequest(UrlPattern.PRODUCT, 200, productRequest, ProductResponse.class);
    }

    @Тогда("BFF возвращает ответ с полным набором полей")
    public void bff_возвращает_ответ_с_полным_набором_полей() {
        ProductResponse expectedProductResponse = new ProductResponse(
                world.productInfoResponse.getTitle(),
                world.productInfoResponse.getDescription(),
                world.productInfoResponse.getWeight(),
                world.productInfoResponse.getHeight(),
                world.productInfoResponse.getLength(),
                world.productInfoResponse.getWidth(),
                world.productPriceResponse.getPrice(),
                world.productPriceResponse.getCurrency(),
                world.productStockInfoResponse.getAvailableStock(),
                world.productStockInfoResponse.getRow(),
                world.productStockInfoResponse.getShell()
        );

        Assert.assertEquals(world.productResponse, expectedProductResponse);
    }

    @Тогда("BFF возвращает ответ со стандартным набором полей")
    public void bff_возвращает_ответ_со_стандартным_набором_полей() {
        ProductResponse expectedProductResponse = new ProductResponse(
                world.productInfoResponse.getTitle(),
                world.productInfoResponse.getDescription(),
                world.productInfoResponse.getWeight(),
                world.productInfoResponse.getHeight(),
                world.productInfoResponse.getLength(),
                world.productInfoResponse.getWidth(),
                world.productPriceResponse.getPrice(),
                world.productPriceResponse.getCurrency()
        );

        Assert.assertEquals(world.productResponse, expectedProductResponse);
    }

    @Тогда("BFF возвращает ответ с минимальным набором полей")
    public void bff_возвращает_ответ_с_минимальным_набором_полей() {
        ProductResponse expectedProductResponse = new ProductResponse(
                world.productInfoResponse.getTitle(),
                world.productPriceResponse.getPrice(),
                world.productPriceResponse.getCurrency()
        );

        Assert.assertEquals(world.productResponse, expectedProductResponse);
    }
}
