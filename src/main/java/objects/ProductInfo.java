package objects;

import lombok.Data;

@Data
public class ProductInfo {
    private String title;
    private String description;
    private Double weight;
    private Double height;
    private Double length;
    private Double width;

    public ProductInfo() {
        this.title = "Title";
        this.description = "Description";
        this.weight = 3.0;
        this.height = 0.1;
        this.length = 0.1;
        this.width = 0.1;
    }
}
