package store;

import categories.Category;
import categories.Fruit;
import categories.Spice;
import categories.Vegetable;
import com.github.javafaker.Faker;
import java.util.*;

public class RandomStorePopulator {

    private final Faker faker;

    public RandomStorePopulator() {
        faker = new Faker();
    }

    public String fillName(Class<? extends Category> categoryName) {
        String fakerProductName = null;
        if (categoryName==Fruit.class) {
            fakerProductName = faker.food().fruit();
        } else if (categoryName==Vegetable.class) {
            fakerProductName = faker.food().vegetable();
        } else if (categoryName==Spice.class) {
            fakerProductName = faker.food().spice();
        }
        return fakerProductName;
    }

    public int fillRate() {
        Random random = new Random();
        return random.nextInt(100);
    }

    public double fillPrice() {
        Random random = new Random();
        return (double)(Math.round(random.nextDouble()*10000))/100;
    }
}