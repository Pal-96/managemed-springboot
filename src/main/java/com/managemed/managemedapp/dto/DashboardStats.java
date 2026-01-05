package com.managemed.managemedapp.dto;


public class DashboardStats {
    private final int customers;
    private final int totalSales;
    private final int products;

    public DashboardStats(int customers, int totalSales, int products) {
        this.customers = customers;
        this.totalSales = totalSales;
        this.products = products;
    }

    public int getCustomers() {
        return customers;
    }

    public double getTotalSales() {
        return totalSales;
    }

    public int getProducts() {
        return products;
    }
}
