package ai.basic.basicai.user.util.json.mapper;

import com.fasterxml.jackson.annotation.JsonSubTypes;
import com.fasterxml.jackson.annotation.JsonTypeInfo;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class Zoo<T> {
    public T animals;

    public Zoo(T animals) {
        this.animals = animals;
    }

    @Data
    @NoArgsConstructor
    @JsonTypeInfo(use = JsonTypeInfo.Id.NAME, include = JsonTypeInfo.As.PROPERTY)
    @JsonSubTypes({
            @JsonSubTypes.Type(value = Dog.class, name = "dog"),
            @JsonSubTypes.Type(value = Cat.class, name = "cat")
    })
    public static class Animal {
        public String name;

        public Animal(String name) {
            this.name = name;
        }
    }

    @EqualsAndHashCode(callSuper = true)
    @Data
    @NoArgsConstructor
    public static class Dog extends Animal {
        public double barkVolume;

        public Dog(String name, double barkVolume) {
            super(name);
            this.barkVolume = barkVolume;
        }
    }

    @EqualsAndHashCode(callSuper = true)
    @Data
    @NoArgsConstructor
    public static class Cat extends Animal {
        public boolean likesCream;
        public int lives;

        public Cat(String name, boolean likesCream, int lives) {
            super(name);
            this.likesCream = likesCream;
            this.lives = lives;
        }
    }
}