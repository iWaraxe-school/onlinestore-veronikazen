import category.Fruit;
import category.Spice;
import category.Vegetable;

public class StoreApp {

    public static void main(String[] args) {
        Fruit fruit = new Fruit(Fruit.class.getSimpleName());
        Vegetable vegetable = new Vegetable(Vegetable.class.getSimpleName());
        Spice spice = new Spice(Spice.class.getSimpleName());
        RandomStorePopulator randomStorePopulator = new RandomStorePopulator();
        randomStorePopulator.initProductList(fruit);
        randomStorePopulator.initProductList(vegetable);
        randomStorePopulator.initProductList(spice);
        System.out.println(fruit);
        System.out.println(vegetable);
        System.out.println(spice);
    }
}
