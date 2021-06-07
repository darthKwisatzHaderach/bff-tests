package objects.responses;

import lombok.Data;

@Data
public class ProductInfoResponse {
    private String title;
    private String description;
    private Double weight;
    private Double height;
    private Double length;
    private Double width;

    public ProductInfoResponse() {
        this.title = "Title";
        this.description = "Description";
        this.weight = 3.0;
        this.height = 0.1;
        this.length = 0.1;
        this.width = 0.1;
    }
}
