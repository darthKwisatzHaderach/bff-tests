package stepdefs;

import com.google.inject.Guice;
import com.google.inject.Injector;
import io.cucumber.guice.ScenarioScoped;
import objects.requests.ProductInfoRequest;
import objects.requests.ProductPriceRequest;
import objects.requests.ProductStockInfoRequest;
import objects.responses.ProductErrorResponse;
import objects.responses.ProductInfoResponse;
import objects.responses.ProductPriceResponse;
import objects.requests.ProductRequest;
import objects.responses.ProductResponse;
import objects.responses.ProductStockInfoResponse;

@ScenarioScoped
public class World {

    public ProductRequest productRequest;
    public ProductInfoResponse productInfoResponse;
    public ProductPriceResponse productPriceResponse;
    public ProductStockInfoResponse productStockInfoResponse;
    public ProductResponse productResponse;
    public ProductErrorResponse productErrorResponse;
    public ProductInfoRequest productInfoRequest;
    public ProductPriceRequest productPriceRequest;
    public ProductStockInfoRequest productStockInfoRequest;

    public World() {
        Injector injector = Guice.createInjector();

        this.productRequest = injector.getInstance(ProductRequest.class);
        this.productInfoResponse = injector.getInstance(ProductInfoResponse.class);
        this.productPriceResponse = injector.getInstance(ProductPriceResponse.class);
        this.productStockInfoResponse = injector.getInstance(ProductStockInfoResponse.class);
        this.productResponse = injector.getInstance(ProductResponse.class);
        this.productErrorResponse = injector.getInstance(ProductErrorResponse.class);
        this.productInfoRequest = injector.getInstance(ProductInfoRequest.class);
        this.productPriceRequest = injector.getInstance(ProductPriceRequest.class);
        this.productStockInfoRequest = injector.getInstance(ProductStockInfoRequest.class);
    }
}
