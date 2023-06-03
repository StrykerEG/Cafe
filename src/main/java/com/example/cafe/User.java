package com.example.cafe;

public class User
{
    private String username;
    private String password;
    private double balance;
    public User(String username, String password,double balance)
    {
        this.username = username;
        this.password = password;
        this.balance = balance;
    }

    public User(Double balance) {
        setBalance(balance);
    }
    User(String name )
    {
        username = name;
    }

    public User() {

    }

    double getBalance()
    {
        return balance;
    }

    public void setBalance(double balance)
    {
        this.balance = balance;
    }

    public String getUsername()
    {
        return username;
    }

    public String getPassword()
    {
        return password;
    }

    public void setUsername(String username)
    {
        this.username = username;
    }

    public void setPassword(String password)
    {
        this.password = password;
    }

    public boolean authenticate(String username, String password)
    {
        return this.username.equals(username) && this.password.equals(password);
    }


}

