package objects.requests;

import lombok.Data;

@Data
public class ProductPriceRequest {
    private String productId;

    public ProductPriceRequest() {
        this.productId = "700110";
    }
}
