import lombok.Data;

@Data
public class ProductPrice {
    private Double price;
    private Currency currency;

    public ProductPrice() {
        this.price = 5.0;
        this.currency = Currency.RUR;
    }
}
