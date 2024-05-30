package giovannighirardelli;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.function.Supplier;

public class Application {

    public static void main(String[] args) {


        //        PRODOTTI

        Supplier<Double> randomPrice = () -> {
            Random random = new Random();
            return random.nextDouble(1, 2000);

        };
        String[] categorie = {"Boys", "Baby", "Books", "Games", "Home"};
        Supplier<String> randomCategory = () -> {
            int index = new Random().nextInt(categorie.length);
            return categorie[index];
        };

        Supplier<Product> productSupplier = () -> new Product("Prodotto", randomCategory.get(), randomPrice.get());

        List<Product> prodotti = new ArrayList<>();
        for (int i = 0; i < 50; i++) {
            prodotti.add(productSupplier.get());
        }

        //        CUSTOMERS

        Supplier<Integer> randomTier = () -> {
            Random random = new Random();
            return random.nextInt(1, 5);

        };

        Supplier<Customer> customerSupplier = () -> new Customer("Customer", randomTier.get());
        List<Customer> customer = new ArrayList<>();
        for (int i = 0; i < 10; i++) {
            customer.add(customerSupplier.get());
        }
        System.out.println(customer);

        //      ORDINI

        Supplier<LocalDate> randomOrderDate = () -> {
            Random random = new Random();
            Random random1 = new Random();
            Random random2 = new Random();

            return LocalDate.of(random.nextInt(2020, 2023), random1.nextInt(1, 11), random2.nextInt(1, 28));


        };
        Supplier<LocalDate> randomDeliveryDate = () -> {
            Random random = new Random();
            Random random1 = new Random();
            Random random2 = new Random();

            return LocalDate.of(random.nextInt(2020, 2023), random1.nextInt(1, 11), random2.nextInt(1, 28));


        };


        Supplier<Order> orderSupplier = () -> new Order("Consegnato", randomOrderDate.get(), randomDeliveryDate.get(), prodotti, customer.get(1));
        List<Order> order = new ArrayList<>();
        for (int i = 0; i < 40; i++) {
            order.add(orderSupplier.get());

        }

    }
}
