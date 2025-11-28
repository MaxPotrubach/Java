import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Data
@NoArgsConstructor
public class User {
    private Cart cart = new Cart();
    private List<Order> orderHistory = new ArrayList<>();

    public void addOrder(Order order) {
        orderHistory.add(order);
    }
}