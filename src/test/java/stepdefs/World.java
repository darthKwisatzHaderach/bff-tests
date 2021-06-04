package stepdefs;

import com.google.inject.Guice;
import com.google.inject.Injector;
import io.cucumber.guice.ScenarioScoped;
import objects.ProductErrorResponse;
import objects.ProductInfo;
import objects.ProductPrice;
import objects.ProductRequest;
import objects.ProductResponse;
import objects.ProductStockInfo;

@ScenarioScoped
public class World {

    public ProductRequest productRequest;
    public ProductInfo productInfo;
    public ProductPrice productPrice;
    public ProductStockInfo productStockInfo;
    public ProductResponse productResponse;
    public ProductErrorResponse productErrorResponse;

    public World() {
        Injector injector = Guice.createInjector();

        this.productRequest = injector.getInstance(ProductRequest.class);
        this.productInfo = injector.getInstance(ProductInfo.class);
        this.productPrice = injector.getInstance(ProductPrice.class);
        this.productStockInfo = injector.getInstance(ProductStockInfo.class);
        this.productResponse = injector.getInstance(ProductResponse.class);
        this.productErrorResponse = injector.getInstance(ProductErrorResponse.class);
    }
}
