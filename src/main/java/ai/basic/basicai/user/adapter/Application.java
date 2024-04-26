package ai.basic.basicai.user.adapter;

import ai.basic.basicai.user.adapter.dto.UserDTO;
import ai.basic.basicai.user.entity.UserBO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.WebApplicationType;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.core.convert.ConversionService;

/**
 * @author Jagger Wang
 */
@Slf4j
@SpringBootApplication(scanBasePackages = "ai.basic.basicai.user.adapter")
public class Application {

	public static void main(String[] args) {
		var app = new SpringApplication(Application.class);
		app.setWebApplicationType(WebApplicationType.NONE);
		var context = app.run(args);

		var conversionService = context.getBean(ConversionService.class);
		var userBo = UserBO.builder()
				.id(1L)
				.username("jaggerwang")
				.build();
		log.info("UserDTO: {}", conversionService.convert(userBo, UserDTO.class));
	}

}
