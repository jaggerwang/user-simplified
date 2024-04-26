package ai.basic.basicai.user.util.bean.mapper;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class CarDTO {

    private String make;
    private int seatCount;
    private String type;

}
