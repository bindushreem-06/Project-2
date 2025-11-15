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

        int mainChoice;
        do {
            System.out.println("\n=== Restaurant Management System ===");
            System.out.println("1. Menu Management");
            System.out.println("2. Customer Management");
            System.out.println("3. Order Management");
            System.out.println("4. Exit");
            System.out.print("Enter choice: ");
            mainChoice = sc.nextInt();

            switch (mainChoice) {
                case 1:
                    menuManagement(sc, menuService);
                    break;
                case 2:
                    customerManagement(sc, customerService);
                    break;
                case 3:
                    orderManagement(sc, orderService, menuService);
                    break;
                case 4:
                    System.out.println("Exiting...");
                    break;
                default:
                    System.out.println("Invalid choice!");
            }
        } while (mainChoice != 4);

        sc.close();
    }

    // ---------------- MENU MANAGEMENT ----------------
    private static void menuManagement(Scanner sc, MenuService menuService) {
        int choice;
        do {
            System.out.println("\n--- Menu Item Management ---");
            System.out.println("1. Add Menu Item");
            System.out.println("2. View Menu Items");
            System.out.println("3. Update Menu Item");
            System.out.println("4. Delete Menu Item");
            System.out.println("5. Back");
            System.out.print("Enter choice: ");
            choice = sc.nextInt();

            try {
                switch (choice) {
                    case 1:
                        System.out.print("Enter ID: ");
                        int id = sc.nextInt();
                        sc.nextLine(); // buffer clear
                        System.out.print("Enter Name: ");
                        String name = sc.nextLine();
                        System.out.print("Enter Price: ");
                        double price = sc.nextDouble();
                        sc.nextLine();
                        System.out.print("Enter Category: ");
                        String category = sc.nextLine();
                        menuService.addMenuItem(new MenuItem(id, name, price, category));
                        System.out.println("Menu item added!");
                        break;

                    case 2:
                        System.out.println("--- Menu Items ---");
                        menuService.getAllMenuItems().forEach(System.out::println);
                        break;

                    case 3:
                        System.out.print("Enter ID to update: ");
                        int uid = sc.nextInt();
                        MenuItem item = menuService.findById(uid);
                        sc.nextLine();
                        System.out.print("New Name: ");
                        item.setName(sc.nextLine());
                        System.out.print("New Price: ");
                        item.setPrice(sc.nextDouble());
                        sc.nextLine();
                        System.out.print("New Category: ");
                        item.setCategory(sc.nextLine());
                        System.out.println("Updated successfully!");
                        break;

                    case 4:
                        System.out.print("Enter ID to delete: ");
                        int did = sc.nextInt();
                        menuService.deleteMenuItem(did);
                        System.out.println("Deleted successfully!");
                        break;

                    case 5:
                        return;

                    default:
                        System.out.println("Invalid choice!");
                }
            } catch (Exception e) {
                System.out.println("Error: " + e.getMessage());
            }
        } while (choice != 5);
    }

    // ---------------- CUSTOMER MANAGEMENT ----------------
    private static void customerManagement(Scanner sc, CustomerService service) {
        int choice;
        do {
            System.out.println("\n--- Customer Management ---");
            System.out.println("1. Add Customer");
            System.out.println("2. View All Customers");
            System.out.println("3. Update Customer");
            System.out.println("4. Delete Customer");
            System.out.println("5. Back");
            System.out.print("Enter choice: ");
            choice = sc.nextInt();

            try {
                switch (choice) {
                    case 1:
                        System.out.print("Enter ID: ");
                        int id = sc.nextInt();
                        sc.nextLine();
                        System.out.print("Enter Name: ");
                        String name = sc.nextLine();
                        System.out.print("Enter Phone: ");
                        String phone = sc.nextLine();
                        service.addCustomer(new Customer(id, name, phone));
                        System.out.println("Customer added!");
                        break;

                    case 2:
                        System.out.println("--- Customers ---");
                        service.getAllCustomers().values().forEach(System.out::println);
                        break;

                    case 3:
                        System.out.print("Enter ID to update: ");
                        int uid = sc.nextInt();
                        sc.nextLine();
                        System.out.print("New Name: ");
                        String newName = sc.nextLine();
                        System.out.print("New Phone: ");
                        String newPhone = sc.nextLine();
                        service.updateCustomer(uid, newName, newPhone);
                        System.out.println("Updated successfully!");
                        break;

                    case 4:
                        System.out.print("Enter ID to delete: ");
                        int d = sc.nextInt();
                        service.deleteCustomer(d);
                        System.out.println("Customer removed!");
                        break;

                    case 5:
                        return;

                    default:
                        System.out.println("Invalid choice!");
                }
            } catch (Exception e) {
                System.out.println("Error: " + e.getMessage());
            }
        } while (choice != 5);
    }

    // ---------------- ORDER MANAGEMENT ----------------
    private static void orderManagement(Scanner sc, OrderService orderService, MenuService menuService) {
        int choice;
        do {
            System.out.println("\n--- Order Management ---");
            System.out.println("1. Create Order");
            System.out.println("2. View Orders");
            System.out.println("3. Add Item to Order");
            System.out.println("4. Delete Order");
            System.out.println("5. Back");
            System.out.print("Enter choice: ");
            choice = sc.nextInt();

            try {
                switch (choice) {
                    case 1:
                        System.out.print("Enter Order ID: ");
                        int oid = sc.nextInt();
                        System.out.print("Enter Customer ID: ");
                        int cid = sc.nextInt();
                        orderService.createOrder(new Order(oid, cid));
                        System.out.println("Order created.");
                        break;

                    case 2:
                        orderService.getAllOrders().values().forEach(o -> {
                            System.out.println(o);
                            o.getItems().forEach(i -> System.out.println("   -> " + i));
                        });
                        break;

                    case 3:
                        System.out.print("Enter Order ID: ");
                        int oId = sc.nextInt();
                        System.out.print("Enter Menu Item ID: ");
                        int itemId = sc.nextInt();
                        MenuItem m = menuService.findById(itemId);
                        orderService.addItemToOrder(oId, m);
                        System.out.println("Item added to order.");
                        break;

                    case 4:
                        System.out.print("Enter Order ID to delete: ");
                        int delId = sc.nextInt();
                        orderService.deleteOrder(delId);
                        System.out.println("Order deleted.");
                        break;

                    case 5:
                        return;

                    default:
                        System.out.println("Invalid choice!");
                }
            } catch (Exception e) {
                System.out.println("Error: " + e.getMessage());
            }
        } while (choice != 5);
    }
}
