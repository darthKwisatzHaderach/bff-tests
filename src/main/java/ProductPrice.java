import lombok.Data;

@Data
public class ProductPrice {
    private Double price;
    private Currency currency;

    public void ProjectPrice() {
        this.price = 5.0;
        this.currency = Currency.RUR;
    }
}
