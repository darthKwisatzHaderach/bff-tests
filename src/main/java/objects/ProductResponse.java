package objects;

import enums.Currency;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ProductResponse {
    private String title;
    private String description;
    private Double weight;
    private Double height;
    private Double length;
    private Double width;
    private Double price;
    private Currency currency;
    private Double availableStock;
    private Integer row;
    private Integer shell;

    public ProductResponse(String title, Double price, Currency currency) {
        this.title = title;
        this.price = price;
        this.currency = currency;
    }

    public ProductResponse(
            String title,
            String description,
            Double weight,
            Double height,
            Double length,
            Double width,
            Double price,
            Currency currency) {
        this.title = title;
        this.description = description;
        this.weight = weight;
        this.height = height;
        this.length = length;
        this.width = width;
        this.price = price;
        this.currency = currency;
    }
}
