package giovannighirardelli;

import com.github.javafaker.Faker;
import giovannighirardelli.entities.Customer;
import giovannighirardelli.entities.Order;
import giovannighirardelli.entities.Product;

import java.time.LocalDate;
import java.util.*;
import java.util.function.Supplier;
import java.util.stream.Collectors;

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

        List<Product> prodottiList = new ArrayList<>();
        for (int i = 0; i < 50; i++) {
            prodottiList.add(productSupplier.get());
        }

        //        CUSTOMERS

        Supplier<Integer> randomTier = () -> {
            Random random = new Random();
            return random.nextInt(1, 5);

        };
        Supplier<String> randomName = () -> {
            Faker faker = new Faker();
            return faker.elderScrolls().firstName();
        };

        Supplier<Customer> customerSupplier = () ->


                new Customer(randomName.get(), randomTier.get());
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


        Supplier<Order> orderSupplier = () -> new Order("Consegnato", randomOrderDate.get(), randomDeliveryDate.get(), prodottiList, customer.get(1));
        List<Order> orderList = new ArrayList<>();
        for (int i = 0; i < 40; i++) {
            orderList.add(orderSupplier.get());

        }


//        ESERCIZIO1
        System.out.println("ESERCIZIO1");
        Map<String, List<Order>> clientOrder = orderList.stream().collect(Collectors.groupingBy(order -> order.getCustomer().getName()));
        clientOrder.forEach((nome, listaOrdini) -> System.out.println("Cliente " + nome + "Ha ordinato" + listaOrdini));

//        ESERCIZIO2

        System.out.println("ESERCIZIO2");
        Map<Customer, Double> spesaCliente = orderList.stream().collect(Collectors.groupingBy(order -> order.getCustomer(),
                Collectors.summingDouble(order -> order.getProducts().stream().mapToDouble(prodotti -> prodotti.getPrice()).sum())));
        spesaCliente.forEach((nome, sommaProdotti) -> System.out.println("il cliente " + nome + "ha speso " + sommaProdotti));

//      ESERCIZIO3
        System.out.println("ESERCIZIO3");
        List<Product> moreExpensive = prodottiList.stream().sorted(Comparator.comparingDouble(Product::getPrice).reversed()).limit(5).toList();
        moreExpensive.forEach(product -> System.out.println(product));

//        ESERCIZIO4
        System.out.println("ESERCIZIO4");
        Map<Long, Double> mediaImporti = orderList.stream().collect(Collectors.groupingBy(order ->
                order.getId(), Collectors.averagingDouble(order -> order.getProducts().stream().mapToDouble(prodotti -> prodotti.getPrice()).sum())));

        mediaImporti.forEach((ordine, media) -> System.out.println("L'ordine " + ordine + "Ha una spesa media di " + media));

//        ESERCIZIO5
        System.out.println("ESERCIZIO5");
        Map<String, Double> prezziCategorie = prodottiList.stream().collect(Collectors.groupingBy(product -> product.getCategory(), Collectors.summingDouble(product -> product.getPrice())));
        prezziCategorie.forEach((nome, prezzo) -> System.out.println("La categoria " + nome + "ha dei prodotti con un prezzo medio di: "));
//        List<Product> prezziCategorie = prodottiList.stream().sorted(Comparator.comparing(product -> product.getCategory())).toList();
//        prezziCategorie.forEach(product -> System.out.println("gli oggetti della categoria " + product));
    }
}