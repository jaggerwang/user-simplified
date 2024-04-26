package ai.basic.basicai.user.adapter.api.controller;

import org.hamcrest.Matchers;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

@SpringBootTest
@AutoConfigureMockMvc
public class UserControllerTest {

    @Autowired
    private MockMvc mvc;

    @Test
    public void shouldFoundUserWhenIdIsValid() throws Exception {
        var id = 1;
        mvc.perform(MockMvcRequestBuilders.get(String.format("/user/info/%d", id)))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.code", Matchers.is("OK")))
                .andExpect(MockMvcResultMatchers.jsonPath("$.data.id", Matchers.is(id)));
    }

}
