package helpers;

import org.apache.commons.lang3.builder.CompareToBuilder;
import products.Product;

import java.lang.reflect.Field;
import java.util.Comparator;
import java.util.Map;

public class ProductComparator implements Comparator<Product> {

    public Map<String, String> sortCriteria;

    public ProductComparator(Map sortCriteria) {
        this.sortCriteria = sortCriteria;
    }

    @Override
    public int compare(Product p1, Product p2) {
        CompareToBuilder compareToBuilder = new CompareToBuilder();
        for (Map.Entry<String, String> sortCriteriaItem : sortCriteria.entrySet()) {
            try {
                if (sortCriteria.get(sortCriteriaItem.getKey()).equalsIgnoreCase("asc")) {
                    compareToBuilder.append(getSortProperty(p1, sortCriteriaItem.getKey()),
                            getSortProperty(p2, sortCriteriaItem.getKey()));
                }
                else {
                    compareToBuilder.append(getSortProperty(p2, sortCriteriaItem.getKey()),
                            getSortProperty(p1, sortCriteriaItem.getKey()));
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return compareToBuilder.toComparison();
    }

    public static String getSortProperty(Product product, String property) throws NoSuchFieldException,
            IllegalAccessException {
        Field field = product.getClass().getDeclaredField(property);
        field.setAccessible(true);
        return field.get(product).toString();
    }
}
