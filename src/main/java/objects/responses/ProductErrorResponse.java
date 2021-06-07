package objects.responses;

import lombok.Data;

@Data
public class ProductErrorResponse {
    public Integer code;
    public String message;
}
