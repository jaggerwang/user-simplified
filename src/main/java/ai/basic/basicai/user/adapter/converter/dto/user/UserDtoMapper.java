package ai.basic.basicai.user.adapter.converter.dto.user;

import ai.basic.basicai.user.adapter.converter.MapStructConfig;
import ai.basic.basicai.user.adapter.dto.UserDTO;
import ai.basic.basicai.user.entity.UserBO;
import org.mapstruct.InheritInverseConfiguration;
import org.mapstruct.Mapper;
import org.mapstruct.extensions.spring.DelegatingConverter;
import org.springframework.core.convert.converter.Converter;

@Mapper(config = MapStructConfig.class)
public interface UserDtoMapper extends Converter<UserBO, UserDTO> {

    UserDTO convert(UserBO userBO);

    @InheritInverseConfiguration
    @DelegatingConverter
    UserBO invertConvert(UserDTO userDTO);

}
