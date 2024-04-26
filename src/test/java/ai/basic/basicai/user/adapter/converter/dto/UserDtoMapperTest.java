package ai.basic.basicai.user.adapter.converter.dto;

import ai.basic.basicai.client.dto.user.UserDTO;
import ai.basic.basicai.user.adapter.Application;
import ai.basic.basicai.user.entity.UserBO;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.core.convert.ConversionService;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest(classes = Application.class)
public class UserDtoMapperTest {

    @Autowired
    private ConversionService conversionService;

    @Test
    public void shouldMapBoToDto() {
        var userBo = UserBO.builder()
                .id(1L)
                .username("jaggerwang")
                .build();
        var userDto = conversionService.convert(userBo, UserDTO.class);
        assertThat(userDto).isNotNull();
        assertThat(userDto.getId()).isEqualTo(userBo.getId());
        assertThat(userDto.getUsername()).isEqualTo(userBo.getUsername());
    }

    @Test
    public void shouldMapDtoToBo() {
        var userDto = UserDTO.builder()
                .id(1L)
                .username("jaggerwang")
                .build();
        var userBo = conversionService.convert(userDto, UserBO.class);
        assertThat(userBo).isNotNull();
        assertThat(userBo.getId()).isEqualTo(userDto.getId());
        assertThat(userBo.getUsername()).isEqualTo(userDto.getUsername());
    }

}
