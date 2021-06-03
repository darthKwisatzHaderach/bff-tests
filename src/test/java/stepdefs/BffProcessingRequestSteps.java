package stepdefs;

import com.github.tomakehurst.wiremock.client.WireMock;
import com.google.inject.Inject;
import enums.HttpMethod;
import enums.Source;
import enums.UrlPattern;
import helpers.BffClient;
import helpers.WiremockHelper;
import io.cucumber.java.ru.Когда;
import io.cucumber.java.ru.Пусть;
import io.cucumber.java.ru.Тогда;
import objects.ProductRequest;
import objects.ProductResponse;
import org.testng.Assert;

import static com.github.tomakehurst.wiremock.client.WireMock.reset;

public class BffProcessingRequestSteps {

    @Inject
    private World world;

    @Пусть("есть внешние сервисы для корректной работы BFF")
    public void есть_внешние_сервисы_для_корректной_работы_bff() {
        WireMock.configureFor(WiremockHelper.host, WiremockHelper.port);
        reset();

        WiremockHelper.setMock(HttpMethod.POST, UrlPattern.INFO, world.productInfo);
        WiremockHelper.setMock(HttpMethod.POST, UrlPattern.PRICE, world.productPrice);
        WiremockHelper.setMock(HttpMethod.POST, UrlPattern.STOCK, world.productStockInfo);
    }

    @Когда("клиент выполняет запрос на агрегацию информации о продукте для источника {string}")
    public void клиент_выполняет_запрос_на_агрегацию_информации_о_продукте_для_источника(String source) {
        ProductRequest productRequest = new ProductRequest("700110", Source.valueOf(source));
        world.productResponse = BffClient.postRequest(UrlPattern.PRODUCT, productRequest, ProductResponse.class);
    }

    @Тогда("BFF возвращает ответ с полным набором полей")
    public void bff_возвращает_ответ_с_полным_набором_полей() {
        ProductResponse expectedProductResponse = new ProductResponse(
                world.productInfo.getTitle(),
                world.productInfo.getDescription(),
                world.productInfo.getWeight(),
                world.productInfo.getHeight(),
                world.productInfo.getLength(),
                world.productInfo.getWidth(),
                world.productPrice.getPrice(),
                world.productPrice.getCurrency(),
                world.productStockInfo.getAvailableStock(),
                world.productStockInfo.getRow(),
                world.productStockInfo.getShell()
        );

        Assert.assertEquals(world.productResponse, expectedProductResponse);
    }

    @Тогда("BFF возвращает ответ со стандартным набором полей")
    public void bff_возвращает_ответ_со_стандартным_набором_полей() {
        ProductResponse expectedProductResponse = new ProductResponse(
                world.productInfo.getTitle(),
                world.productInfo.getDescription(),
                world.productInfo.getWeight(),
                world.productInfo.getHeight(),
                world.productInfo.getLength(),
                world.productInfo.getWidth(),
                world.productPrice.getPrice(),
                world.productPrice.getCurrency()
        );

        Assert.assertEquals(world.productResponse, expectedProductResponse);
    }

    @Тогда("BFF возвращает ответ с минимальным набором полей")
    public void bff_возвращает_ответ_с_минимальным_набором_полей() {
        ProductResponse expectedProductResponse = new ProductResponse(
                world.productInfo.getTitle(),
                world.productPrice.getPrice(),
                world.productPrice.getCurrency()
        );

        Assert.assertEquals(world.productResponse, expectedProductResponse);
    }
}
