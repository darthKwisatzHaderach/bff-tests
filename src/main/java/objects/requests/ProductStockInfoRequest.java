package objects.requests;

import lombok.Data;

@Data
public class ProductStockInfoRequest {
    private String productId;

    public ProductStockInfoRequest() {
        this.productId = "700110";
    }
}
