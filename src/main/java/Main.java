import java.util.Arrays;
import java.util.List;
import java.util.Scanner;


public class Main {
    public static void main(String[] args) {

        Category electronics = new Category(1, "Електроніка");
        Category smartphones = new Category(2, "Смартфони");
        Category accessories = new Category(3, "Аксесуари");

        Product product1 = new Product(1, "Ноутбук", 19999.99, "Потужний ноутбук для роботи та ігор", electronics);
        Product product2 = new Product(2, "Смартфон", 12999.50, "Смартфон з великим екраном", smartphones);
        Product product3 = new Product(3, "Навушники", 2499.00, "Бездротові навушники", accessories);

        List<Product> allProducts = Arrays.asList(product1, product2, product3);

        User user = new User();
        Scanner scanner = new Scanner(System.in);

        while (true) {
            System.out.println("""
                    
                    Виберіть опцію:
                    1 - Переглянути список товарів
                    2 - Додати товар до кошика
                    3 - Переглянути кошик
                    4 - Видалити товар з кошика
                    5 - Пошук товарів
                    6 - Зробити замовлення
                    7 - Переглянути історію замовлень
                    0 - Вийти
                    """);

            int choice = scanner.nextInt();

            switch (choice) {

                case 1:
                    allProducts.forEach(System.out::println);
                    break;

                case 2:
                    System.out.println("Введіть ID товару:");
                    int addId = scanner.nextInt();
                    allProducts.stream()
                            .filter(p -> p.getId() == addId)
                            .findFirst()
                            .ifPresentOrElse(
                                    user.getCart()::addProduct,
                                    () -> System.out.println("Товар не знайдено")
                            );
                    break;

                case 3:
                    System.out.println(user.getCart());
                    break;

                case 4:
                    System.out.println("Введіть ID товару для видалення:");
                    int removeId = scanner.nextInt();
                    if (user.getCart().removeProductById(removeId))
                        System.out.println("Товар видалено.");
                    else
                        System.out.println("Товар не знайдено.");
                    break;

                case 5:
                    scanner.nextLine(); // очищаємо буфер
                    System.out.println("Введіть назву або категорію для пошуку:");
                    String query = scanner.nextLine().toLowerCase();

                    List<Product> foundProducts = allProducts.stream()
                            .filter(p -> p.getName().toLowerCase().contains(query)
                                    || p.getCategory().getName().toLowerCase().contains(query))
                            .toList();

                    if (foundProducts.isEmpty()) {
                        System.out.println("Нічого не знайдено.");
                    } else {
                        foundProducts.forEach(System.out::println);
                    }
                    break;

                case 6:
                    if (user.getCart().getProducts().isEmpty()) {
                        System.out.println("Кошик порожній.");
                        break;
                    }

                    Order order = new Order(user.getCart());
                    user.addOrder(order);
                    user.getCart().clear();

                    System.out.println("Замовлення оформлено:");
                    System.out.println(order);
                    break;

                case 7:
                    if (user.getOrderHistory().isEmpty()) {
                        System.out.println("Історія замовлень порожня.");
                    } else {
                        user.getOrderHistory().forEach(o -> {
                            System.out.println(o);
                            System.out.println("---------------------------");
                        });
                    }
                    break;

                case 0:
                    System.out.println("Дякуємо, що використовували наш магазин!");
                    return;

                default:
                    System.out.println("Невідома опція.");
            }
        }
    }
}