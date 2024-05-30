package giovannighirardelli;

import java.util.Random;

public class Customer {
    private Long id;
    private String name;
    private Integer tier;

    public Customer(String name, Integer tier) {
        Random rndm = new Random();
        this.id = rndm.nextLong(100000, 100000000);
        this.name = name + " " + this.id;
        this.tier = tier;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getTier() {
        return tier;
    }

    public void setTier(Integer tier) {
        this.tier = tier;
    }

    @Override
    public String toString() {
        return "Customer{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", tier=" + tier +
                '}';
    }
}

