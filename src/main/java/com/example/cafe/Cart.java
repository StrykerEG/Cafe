package com.example.cafe;
import java.util.ArrayList;
import java.util.List;
public class Cart
{
    private List<CoffeeItem> items;
    double total ;
    public Cart()
    {

        this.items = new ArrayList<>();

    }


    public void addItem(CoffeeItem item)
    {
        items.add(item);
    }

    public void removeItem(CoffeeItem item)
    {

        items.remove(item);
    }

    public void updateQuantity(CoffeeItem item, int quantity)
    {
        item.setQuantity(quantity);
    }

    public double calculateTotal() {
        double total = 0;
        for (CoffeeItem item : items) {
            total += item.getTotalPrice();
        }
        return total;
    }
        //print every item in the array list
    public void printItems() {
        for (CoffeeItem item : items) {
            System.out.println(item.getName() + " - $" + item.getPrice());
        }
    }

    public List<CoffeeItem> getItems()
    {
        return new ArrayList<>(items);
    }

    public String HowManyItems() {
        double total = 0;
        for (CoffeeItem item : items) {
            total += 1;
        }
        return String.valueOf(total);
    }
}


