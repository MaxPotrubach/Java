import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Data
@NoArgsConstructor
public class Cart {

    private List<Product> products = new ArrayList<>();

    public void addProduct(Product product) {
        products.add(product);
    }

    public boolean removeProductById(int id) {
        return products.removeIf(p -> p.getId() == id);
    }

    public void clear() {
        products.clear();
    }

    public double getTotalPrice() {
        return products.stream()
                .mapToDouble(Product::getPrice)
                .sum();
    }

    public List<Product> getProducts() {
        return new ArrayList<>(products);
    }

    @Override
    public String toString() {
        if (products.isEmpty()) return "Кошик порожній.";

        StringBuilder sb = new StringBuilder("Кошик містить:\n");
        for (Product product : products) {
            sb.append(product).append("\n");
        }
        sb.append("Загальна вартість: ").append(getTotalPrice());
        return sb.toString();
    }
}