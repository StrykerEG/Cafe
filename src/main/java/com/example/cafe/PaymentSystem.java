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
        this.payment = 0;
        this.charge = 0 ;
    }
    //constructor overload
    public PaymentSystem(int payment,int charge)
    {
        this.payment = payment;
        this.charge = charge;

    }

    public double getCharge()
    {
        return charge;
    }//getting all charge as double result

    public double getPayment()
    {
        return payment;
    }//getting all payments as double result

    public double calculateCharge()
    {
        return user.getBalance()-getPayment();
    }


    public boolean Valdis()
    {
        return user.getBalance() > payment ;
    }//if user has money more than the payment

}
