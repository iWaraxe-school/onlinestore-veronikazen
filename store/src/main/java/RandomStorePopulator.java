import category.Category;
import category.Fruit;
import category.Spice;
import category.Vegetable;
import com.github.javafaker.Faker;
import product.Product;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class RandomStorePopulator {

    public List<Product> fillProductList(String className) {
        Faker faker = new Faker();
        Random random = new Random();
        List <Product> productList = new ArrayList<>();
        if (className.equals(Fruit.class.getSimpleName())) {
            for (int i = 0; i < random.nextInt(5)+1; i++) {
                productList.add(new Product(faker.food().fruit(), random.nextInt(100),
                        (double)(Math.round(random.nextDouble()*10000))/100));
            }
        } else if (className.equals(Vegetable.class.getSimpleName())) {
            for (int i = 0; i < random.nextInt(5)+1; i++) {
                productList.add(new Product(faker.food().vegetable(), random.nextInt(100),
                        (double)(Math.round(random.nextDouble()*10000))/100));
            }
        } else if (className.equals(Spice.class.getSimpleName())) {
            for (int i = 0; i < random.nextInt(5)+1; i++) {
                productList.add(new Product(faker.food().spice(), random.nextInt(100),
                        (double)(Math.round(random.nextDouble()*10000))/100));
            }
        }
        return productList;
    }

    public <T extends Category>  void initProductList (T obj) {
        obj.setProductList(fillProductList(obj.getClass().getSimpleName()));
    }
}