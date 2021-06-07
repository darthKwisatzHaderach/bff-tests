package objects.responses;

import enums.Currency;
import lombok.Data;

@Data
public class ProductPriceResponse {
    private Double price;
    private Currency currency;

    public ProductPriceResponse() {
        this.price = 5.0;
        this.currency = Currency.RUR;
    }
}
