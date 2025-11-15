package App;


import Model.*;
import Services.*;
import Exception.*;

import java.util.Scanner;

public class RestaurantManagementApp {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        MenuService menuService = new MenuService();
        CustomerService customerService = new CustomerService();
        OrderService orderService = new OrderService();

        int choice;
        do {
            System.out.println("\n=== Restaurant Management System ===");
            System.out.println("1. Menu Management");
            System.out.println("2. Customer Management");
            System.out.println("3. Order Management");
            System.out.println("4. Exit");
            System.out.print("Enter choice: ");
            choice = sc.nextInt();

            switch (choice) {
                case 1: System.out.println("Manage Menu..."); break;
                case 2: System.out.println("Manage Customers..."); break;
                case 3: System.out.println("Manage Orders..."); break;
                case 4: System.out.println("Exiting..."); break;
                default: System.out.println("Invalid choice!");
            }
        } while (choice != 4);

        sc.close();
    }
}

