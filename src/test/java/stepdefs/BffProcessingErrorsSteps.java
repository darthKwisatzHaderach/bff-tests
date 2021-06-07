package stepdefs;

import com.github.tomakehurst.wiremock.http.Fault;
import com.google.inject.Inject;
import enums.HttpMethod;
import enums.Source;
import enums.UrlPattern;
import clients.BffClient;
import helpers.WiremockHelper;
import io.cucumber.java.ru.Допустим;
import io.cucumber.java.ru.Когда;
import io.cucumber.java.ru.Тогда;
import objects.responses.ProductErrorResponse;
import objects.requests.ProductRequest;
import org.testng.Assert;

public class BffProcessingErrorsSteps {

    @Inject
    private World world;

    @Допустим("в случае ошибки на запрос {string} {string} сервис возвращает код состояния {int}")
    public void в_случае_ошибки_на_запрос_сервис_возвращает_код_состояния(String httpMethod, String urlPattern, Integer statusCode) {
        WiremockHelper.setMock(HttpMethod.valueOf(httpMethod), UrlPattern.valueOf(urlPattern), statusCode, "Server error");
    }

    @Допустим("при запросе {string} {string} происходит разрыв соединения")
    public void при_запросе_происходит_разрыв_соединения(String httpMethod, String urlPattern) {
        WiremockHelper.setMockWithFault(HttpMethod.valueOf(httpMethod), UrlPattern.valueOf(urlPattern), Fault.CONNECTION_RESET_BY_PEER);
    }

    @Допустим("при запросе {string} {string} сервис возвращает код состояния {int}, но некорректный ответ")
    public void при_запросе_сервис_возвращает_код_состояния_но_некорректный_ответ(String httpMethod, String urlPattern, Integer statusCode) {
        WiremockHelper.setMock(HttpMethod.valueOf(httpMethod), UrlPattern.valueOf(urlPattern), statusCode, "{text}");
    }

    @Когда("клиент выполняет запрос на агрегацию информации о продукте")
    public void клиент_выполняет_запрос_на_агрегацию_информации_о_продукте() {
        world.productRequest = new ProductRequest("700110", Source.OFFLINE);
    }

    @Тогда("происходит обработка ошибки и формирование ответа с кодом состояния {int}, кодом {int} и сообщением {string}")
    public void происходит_обработка_ошибки_и_формирование_ответа_с_кодом_состояния_кодом_и_сообщением(Integer statusCode, Integer errorCode, String errorMessage) {
        world.productErrorResponse = BffClient.postRequest(UrlPattern.PRODUCT, statusCode, world.productRequest, ProductErrorResponse.class);
        Assert.assertEquals(world.productErrorResponse.getCode(), errorCode);
        Assert.assertEquals(world.productErrorResponse.getMessage(), errorMessage);
    }
}
