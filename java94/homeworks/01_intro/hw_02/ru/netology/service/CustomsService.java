package ru.netology.service;

public class CustomsService {
    public static final double WEIGHT_TAX = 100;
    
    public static double calculateCustoms(int price, int weight) {
        return price / 100 + weight * WEIGHT_TAX;
    }     
}
