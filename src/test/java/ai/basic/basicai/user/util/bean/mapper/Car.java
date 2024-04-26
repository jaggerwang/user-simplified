package ai.basic.basicai.user.util.bean.mapper;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class Car {

    private String make;
    private int numberOfSeats;
    private CarType type;

}
