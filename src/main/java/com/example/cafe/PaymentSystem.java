package com.example.cafe;

public class PaymentSystem
{
    User user = new User(1000.00);
    Cart cart = new Cart();
    private double payment =cart.calculateTotal() ;
    private double charge ;


    public PaymentSystem(double payment,double charge)
    {
        this.payment = payment;
        this.charge = charge;

    }

    public PaymentSystem() {

    }

    public double getCharge()
    {
        return charge;
    }

    public double getPayment()
    {
        return payment;
    }

    public double calculateCharge()
    {
        return user.getBalance()-getPayment();
    }


    public boolean Valdis()
    {
        return user.getBalance() > payment ;
    }

}
