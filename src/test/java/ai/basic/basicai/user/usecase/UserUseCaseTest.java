package ai.basic.basicai.user.usecase;

import ai.basic.basicai.user.adapter.Application;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest(classes = Application.class)
public class UserUseCaseTest {

    @Autowired
    private UserUseCase userUsecase;

    @Test
    public void shouldFoundUserWhenIdIsValid() throws Exception {
        var id = 1L;
        var userBo = userUsecase.info(id);
        assertThat(userBo).isPresent();
        assertThat(userBo.get().getId()).isEqualTo(id);
    }

}
