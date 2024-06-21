import java.sql.*;
import java.util.Scanner;
public class Main {
    private static final String url = "jdbc:mysql://localhost:3306/Inventory";
    private static final String user = "root";
    private static final String password = "vaishu,lily@123";

    public static void main(String[] args) {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
        }catch(ClassNotFoundException e) {
            System.out.println(e.getMessage());
        }
// To Add New Items
        try{
            Connection con = DriverManager.getConnection(url, user, password);
            Statement stat = con.createStatement();
            Scanner sc = new Scanner(System.in);
            System.out.print("Do you want to Enter New Products(Y/N)?");
            String input = sc.nextLine();
            if(input.equalsIgnoreCase("Y")) {
                String choice = "Y";
                for (int i = 0; choice.equals("Y"); i++) {
                    System.out.print("\nEnter the name of your product : ");
                    String name = sc.next();
                    System.out.print("\nEnter the price of your product : ");
                    double price = sc.nextDouble();
                    System.out.print("\nEnter the quantity of your product : ");
                    int quantity = sc.nextInt();
                    System.out.print("\nEnter more data(Y/N):");
                    choice = sc.next();
                    String query1 = String.format("INSERT INTO product(name,price,quantity) VALUES('%s',%f,%d)", name, price, quantity);
                    stat.addBatch(query1);
                }
                int[] arr = stat.executeBatch();
                for (int i = 0; i < arr.length; i++) {
                    if (arr[i] == 1) {
                        System.out.println("Product " + i + " added successfully");
                    } else {
                        System.out.println("Product " + i + " not added successfully");
                    }
                }
            }
       }
        catch(SQLException e){
            System.out.println(e.getMessage());
        }

//To update/Modify products
        try{
            System.out.print("Do you want to Update Information(Y/N):");
            String upd = sc.next();
            if(upd.equalsIgnoreCase("Y")) {
                String query2 = " ";
                System.out.print("1. name \n2. price \n3. quantity \nEnter your choice:");
                int ch = sc.nextInt();
                switch(ch) {
                    case 1:
                    {
                        System.out.print("Enter New Name of your product:");
                        String new_name = sc.next();
                        System.out.print("Enter id of the Product:");
                        int id = sc.nextInt();
                        query2 = String.format("UPDATE Product SET name = '%s' where id = %d", new_name, id);
                        break;
                    }
                    case 2: {
                        System.out.print("Enter New Price of your product:");
                        double new_price = sc.nextDouble();
                        System.out.print("Enter id of the Product:");
                        int id = sc.nextInt();
                        query2 = String.format("UPDATE Product SET Price = '%f' where id = %d", new_price, id);
                        break;
                    }
                    case 3: {
                        System.out.print("Enter New Quantity of your product:");
                        int new_quantity = sc.nextInt();
                        System.out.print("Enter id of the Product:");
                        int id = sc.nextInt();
                        query2 = String.format("UPDATE Product SET quantity = %d where id = %d", new_quantity,id);
                        break;
                    }
                }
                int rowsAffected = stat.executeUpdate(query2);
                if(rowsAffected>0){
                    System.out.println("Data Updated successfully");
                }else{
                    System.out.println("Data not updated successfully");
                }
            }
        }
        catch(SQLException e){
            System.out.println(e.getMessage());
        }

 //To delete a product from inventory
        try{
            System.out.print("Do you want to Delete Information(Y/N):");
            String del = sc.next();
            if(del.equalsIgnoreCase("Y")) {
                System.out.print("Enter id of the Product:");
                int id = sc.nextInt();
                String query3 = String.format("Delete from Product where id = %d", id);

                int rowsAffected = stat.executeUpdate(query3);
                if(rowsAffected>0){
                    System.out.println("Data Updated successfully");
                }else{
                    System.out.println("Data not updated successfully");
                }
            }
        }
        catch(SQLException e){
            System.out.println(e.getMessage());
        }

// INVENTORY TRACKING
    try{
          int id = rs.getInt("id");
          String name = rs.getString("name");
          double price = rs.getDouble("price");
          int quantity = rs.getInt("quantity");
          
          System.out.print("Selct Option:\n1. Display Details of All the Products in the Inventory \n2. View the Details of the Specific Product \nEnter Your Choice:");
          int option = sc.nextInt();
          if(option == 1){
             String query4 = "SELECT * FROM PRODUCT";
             ResultSet rs = stat.executeQuery(query4);
             while(rs.next()){     
                System.out.println(" ID:"+id+"\n NAME:"+name+"\n PRICE:"+price+"\n QUANTITY:"+quantity);
            }
          }
        else{
            System.out.print("Enter the ID of the Product to Display its Details from the Inventory: ");
            int pro_id = sc.nextInt();
            String query4 = String.format("SELECT * FROM INVENTORY WHERE id = %d",pro_id); 
            System.out.println(" ID:"+id+"\n NAME:"+name+"\n PRICE:"+price+"\n QUANTITY:"+quantity);
        }
      }
    catch(SQLException e){
            System.out.println(e.getMessage());
        }
    }
}
