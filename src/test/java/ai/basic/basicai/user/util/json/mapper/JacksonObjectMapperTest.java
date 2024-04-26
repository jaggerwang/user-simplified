package ai.basic.basicai.user.util.json.mapper;

import ai.basic.basicai.client.dto.ApiResult;
import ai.basic.basicai.client.dto.user.UserDTO;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.http.converter.json.Jackson2ObjectMapperBuilder;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(SpringExtension.class)
public class JacksonObjectMapperTest {

    @TestConfiguration
    static class Configuration {

        @Bean
        public ObjectMapper objectMapper() {
            return new Jackson2ObjectMapperBuilder()
                    .featuresToDisable(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS)
                    .build();
        }

    }

    @Autowired
    private ObjectMapper objectMapper;

    @Test
    public void shouldCreateObjectWhenValidJson() throws JsonProcessingException {
        var json = "{ \"code\":\"OK\", \"message\":\"\", \"data\": { \"id\": 1, \"username\": \"jaggerwang\", " +
                "\"password\": null, \"email\": \"wangjiajun@basicfinder.com\", \"nickname\": \"测试\", \"avatarId\": " +
                "89, \"avatarUrl\": null, \"intro\": \"cillum non\", \"status\": \"ACTIVE\", \"createdAt\": " +
                "\"2022-01-12T08:17:07Z\", \"updatedAt\": \"2022-03-07T09:39:31Z\" } }";

        var apiResult = objectMapper.readValue(json, new TypeReference<ApiResult<UserDTO>>() {});

        assertEquals("OK", apiResult.getCode().toString());
        assertEquals(1L, apiResult.getData().getId());
    }

    @Test
    public void shouldCreateObjectWhenJsonDataIsNull() throws JsonProcessingException {
        var json = "{ \"code\":\"OK\", \"message\":\"\", \"data\": null }";

        var apiResult = objectMapper.readValue(json, new TypeReference<ApiResult<UserDTO>>() {});

        assertEquals("OK", apiResult.getCode().toString());
        assertNull(apiResult.getData());
    }

    @Test
    public void shouldCorrectWhenSerializeListOfPolymorphic() throws JsonProcessingException {
        var animals = new ArrayList<Zoo.Animal>();
        animals.add(new Zoo.Dog("dog1", 10));
        animals.add(new Zoo.Cat("cat1", true, 1));
        var zoo = new Zoo(animals);

        var result = objectMapper
                .writerFor(new TypeReference<Zoo<List<Zoo.Animal>>>() {})
                .writeValueAsString(zoo);

        assertTrue(result.contains("\"@type\":\"dog\""));
        assertTrue(result.contains("\"@type\":\"cat\""));
    }

    @Test
    public void shouldCorrectWhenDeserializeListOfPolymorphic() throws JsonProcessingException {
        var result = "{\"animals\":[{\"@type\":\"dog\",\"name\":\"dog1\",\"barkVolume\":10.0},{\"@type\":\"cat\",\"name\":\"cat1\",\"likesCream\":true,\"lives\":1}]}";

        var zoo = objectMapper.readValue(result, new TypeReference<Zoo<List<Zoo.Animal>>>() {});

        assertEquals(zoo.animals.size(), 2);
        assertEquals(zoo.animals.get(0).getClass(), Zoo.Dog.class);
        assertEquals(zoo.animals.get(1).getClass(), Zoo.Cat.class);
    }

}
