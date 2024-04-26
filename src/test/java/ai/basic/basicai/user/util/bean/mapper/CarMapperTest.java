package ai.basic.basicai.user.util.bean.mapper;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import static org.assertj.core.api.Assertions.assertThat;

@ExtendWith(SpringExtension.class)
public class CarMapperTest {

    @Test
    public void shouldMapCarToCarDto() {
        var car = new Car("Morris", 5, CarType.SEDAN);
        var carDTO = CarMapper.INSTANCE.carToCarDTO(car);
        assertThat(carDTO).isNotNull();
        assertThat(carDTO.getMake()).isEqualTo("Morris");
        assertThat(carDTO.getSeatCount()).isEqualTo(5);
        assertThat(carDTO.getType()).isEqualTo("SEDAN");
    }

}
