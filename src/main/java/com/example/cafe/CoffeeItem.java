package com.example.cafe;

public class CoffeeItem
{
    private String name;
    private double price;
    private int quantity;

    public CoffeeItem(String name, double price, int quantity) {
        this.name = name;
        this.price = price;
        this.quantity = quantity;
    }
    CoffeeItem()
    {

    }
    public double getTotalPrice()

    {
        return price * quantity;
    }
    public double setQuantity(int quantity)

    {
        return this.quantity = quantity;
    }

    public String  getName()
    {
        return name;
    }

    public double getPrice()
    {
        return  price ;
    }
}

