package stepdefs;

import com.google.inject.Guice;
import com.google.inject.Injector;
import io.cucumber.guice.ScenarioScoped;
import objects.ProductInfo;
import objects.ProductPrice;
import objects.ProductResponse;
import objects.ProductStockInfo;

@ScenarioScoped
public class World {

    public ProductInfo productInfo;
    public ProductPrice productPrice;
    public ProductStockInfo productStockInfo;
    public ProductResponse productResponse;

    public World() {
        Injector injector = Guice.createInjector();

        this.productInfo = injector.getInstance(ProductInfo.class);
        this.productPrice = injector.getInstance(ProductPrice.class);
        this.productStockInfo = injector.getInstance(ProductStockInfo.class);
        this.productResponse = injector.getInstance(ProductResponse.class);
    }
}
