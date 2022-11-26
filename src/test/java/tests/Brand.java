package tests;

public class Brand implements Comparable<Brand> {
    private String name;
    private Integer amount;

    public Brand(String name, Integer amount) {
        this.name = name;
        this.amount = amount;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getAmount() {
        return amount;
    }

    public void setAmount(Integer amount) {
        this.amount = amount;
    }

    @Override
    public int compareTo(Brand o) {
        return -amount.compareTo(o.amount);
    }
}
