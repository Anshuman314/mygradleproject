package com.example.cart;

public class Main {
    public static void main(String[] args) {
        Cart cart = new Cart();
        Item item1 = new Item("Laptop", 999.99);
        Item item2 = new Item("Smartphone", 499.99);

        cart.addItem(item1);
        cart.addItem(item2);

        System.out.println(cart);
        System.out.println("Total: $" + cart.getTotal());
    }
}
