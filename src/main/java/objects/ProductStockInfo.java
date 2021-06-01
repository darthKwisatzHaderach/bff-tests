package objects;

import lombok.Data;

@Data
public class ProductStockInfo {
    private Double availableStock;
    private Integer row;
    private Integer shell;

    public ProductStockInfo() {
        this.availableStock = 20.0;
        this.row = 1;
        this.shell = 3;
    }
}
