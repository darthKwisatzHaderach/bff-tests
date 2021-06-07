package objects.requests;

import lombok.Data;

@Data
public class ProductInfoRequest {
    private String productId;

    public ProductInfoRequest() {
        this.productId = "700110";
    }
}
