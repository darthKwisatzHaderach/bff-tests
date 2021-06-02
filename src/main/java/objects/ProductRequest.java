package objects;

import enums.Source;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class ProductRequest {
    private String productId;
    private Source source;
}
