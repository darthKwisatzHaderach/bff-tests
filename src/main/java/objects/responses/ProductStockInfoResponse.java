package objects.responses;

import lombok.Data;

@Data
public class ProductStockInfoResponse {
    private Double availableStock;
    private Integer row;
    private Integer shell;

    public ProductStockInfoResponse() {
        this.availableStock = 20.0;
        this.row = 1;
        this.shell = 3;
    }
}
