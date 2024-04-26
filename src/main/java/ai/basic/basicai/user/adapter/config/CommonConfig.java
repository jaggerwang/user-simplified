package ai.basic.basicai.user.adapter.config;

import ai.basic.basicai.user.adapter.converter.dto.user.UserDtoMapperImpl;
import org.springframework.boot.convert.ApplicationConversionService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.convert.ConversionService;

@Configuration(proxyBeanMethods = false)
public class CommonConfig {

    @Bean
    public ConversionService conversionService() {
        var conversionService = new ApplicationConversionService();
        conversionService.addConverter(new UserDtoMapperImpl());
        return conversionService;
    }

}
