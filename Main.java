import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Scanner;

public class temp {
    private static final String URL = "jdbc:mysql://localhost:3306/Inventory";
    private static final String USER = "root";
    private static final String PASSWORD = "vaishu,lily@123";

    public static void main(String[] args) {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            System.out.println(e.getMessage());
        }

        Scanner sc = new Scanner(System.in);

        System.out.print("Do you want to Enter New Products(Y/N)? ");
        String input = sc.nextLine();
        if (input.equalsIgnoreCase("Y")) {
            addNewProducts(sc);
        }

        System.out.print("Do you want to Update Information(Y/N): ");
        String upd = sc.next();
        if (upd.equalsIgnoreCase("Y")) {
            updateProduct(sc);
        }

        System.out.print("Do you want to Delete Information(Y/N): ");
        String del = sc.next();
        if (del.equalsIgnoreCase("Y")) {
            deleteProduct(sc);
        }

        System.out.print("Select Option:\n1. Display Details of All the Products in the Inventory \n2. View the Details of the Specific Product \nEnter Your Choice: ");
        int option = sc.nextInt();
        if (option == 1) {
            displayAllProducts();
        } else {
            System.out.print("Enter the ID of the Product to Display its Details from the Inventory: ");
            int pro_id = sc.nextInt();
            displaySpecificProduct(pro_id);
        }

        sc.close();
    }

    private static void addNewProducts(Scanner sc) {
        try (Connection con = DriverManager.getConnection(URL, USER, PASSWORD);
             PreparedStatement ps = con.prepareStatement("INSERT INTO product(name, price, quantity) VALUES (?, ?, ?)")) {

            String choice;
            do {
                System.out.print("\nEnter the name of your product: ");
                String name = sc.next();
                System.out.print("\nEnter the price of your product: ");
                double price = sc.nextDouble();
                System.out.print("\nEnter the quantity of your product: ");
                int quantity = sc.nextInt();
                ps.setString(1, name);
                ps.setDouble(2, price);
                ps.setInt(3, quantity);
                ps.addBatch();

                System.out.print("\nEnter more data(Y/N): ");
                choice = sc.next();
            } while (choice.equalsIgnoreCase("Y"));

            int[] arr = ps.executeBatch();
            for (int i = 0; i < arr.length; i++) {
                System.out.println("Product " + (i + 1) + " added successfully");
            }

        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    private static void updateProduct(Scanner sc) {
        try (Connection con = DriverManager.getConnection(URL, USER, PASSWORD)) {

            System.out.print("1. Name \n2. Price \n3. Quantity \nEnter your choice: ");
            int ch = sc.nextInt();
            String query = null;
            String column = null;

            switch (ch) {
                case 1:
                    System.out.print("Enter New Name of your product: ");
                    column = "name";
                    query = "UPDATE Product SET name = ? WHERE id = ?";
                    break;
                case 2:
                    System.out.print("Enter New Price of your product: ");
                    column = "price";
                    query = "UPDATE Product SET price = ? WHERE id = ?";
                    break;
                case 3:
                    System.out.print("Enter New Quantity of your product: ");
                    column = "quantity";
                    query = "UPDATE Product SET quantity = ? WHERE id = ?";
                    break;
            }

            if (query != null) {
                try (PreparedStatement ps = con.prepareStatement(query)) {
                    System.out.print("Enter id of the Product: ");
                    int id = sc.nextInt();

                    if ("name".equals(column)) {
                        String newValue = sc.next();
                        ps.setString(1, newValue);
                    } else if ("price".equals(column)) {
                        double newValue = sc.nextDouble();
                        ps.setDouble(1, newValue);
                    } else if ("quantity".equals(column)) {
                        int newValue = sc.nextInt();
                        ps.setInt(1, newValue);
                    }
                    ps.setInt(2, id);

                    int rowsAffected = ps.executeUpdate();
                    if (rowsAffected > 0) {
                        System.out.println("Data Updated successfully");
                    } else {
                        System.out.println("Data not updated successfully");
                    }
                }
            }

        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    private static void deleteProduct(Scanner sc) {
        try (Connection con = DriverManager.getConnection(URL, USER, PASSWORD);
             PreparedStatement ps = con.prepareStatement("DELETE FROM Product WHERE id = ?")) {

            System.out.print("Enter id of the Product: ");
            int id = sc.nextInt();
            ps.setInt(1, id);

            int rowsAffected = ps.executeUpdate();
            if (rowsAffected > 0) {
                System.out.println("Product deleted successfully");
            } else {
                System.out.println("Product not found");
            }

        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    private static void displayAllProducts() {
        try (Connection con = DriverManager.getConnection(URL, USER, PASSWORD);
             Statement stat = con.createStatement();
             ResultSet rs = stat.executeQuery("SELECT * FROM Product")) {

            while (rs.next()) {
                int id = rs.getInt("id");
                String name = rs.getString("name");
                double price = rs.getDouble("price");
                int quantity = rs.getInt("quantity");
                System.out.println("ID: " + id + "\nNAME: " + name + "\nPRICE: " + price + "\nQUANTITY: " + quantity);
            }

        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    private static void displaySpecificProduct(int pro_id) {
        try (Connection con = DriverManager.getConnection(URL, USER, PASSWORD);
             PreparedStatement ps = con.prepareStatement("SELECT * FROM Product WHERE id = ?")) {

            ps.setInt(1, pro_id);

            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    int id = rs.getInt("id");
                    String name = rs.getString("name");
                    double price = rs.getDouble("price");
                    int quantity = rs.getInt("quantity");
                    System.out.println("ID: " + id + "\nNAME: " + name + "\nPRICE: " + price + "\nQUANTITY: " + quantity);
                } else {
                    System.out.println("Product not found");
                }
            }

        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }
}
