import java.util.*;
import java.io.*;
import adminclass.Admin;
public class CafeManagementSystem{
    static int length,itqty;
    static double total;
    static String name,itemsOrdered;
    static int code;
    static double price;
    static int quantity;
    private static double total1;
    private static String username;

    public static void main(String[]args) throws FileNotFoundException {
        // main class for user input
         int pass1,itcode,upqty,length;
         int uid1,x,mobnum;
         String n1,nameuser,n2;
         double price;
        System.out.println("\n\nWelcome to our cafe!\n----------------------------------------------------------------------\nPress 1 to Login as an Admin\nPress 2 to Login as User and place order");

        Scanner sc= new Scanner(System.in);
        int adm_usr = sc.nextInt();
        Admin Admin1= new Admin(123455,55678);
        if (adm_usr==1){
            System.out.println("enter UID and PASSWORD");
            uid1 = sc.nextInt();
            pass1 = sc.nextInt();
                if(uid1==Admin1.uid && pass1==Admin1.pass){
                    System.out.println("Would you like to update inventory?\n Press 1 for yes\n Press 2 for no");
                    int invupdat = sc.nextInt();
                    if(invupdat==1){
                            do {
                                System.out.println("Enter updated quantity and prices for items with name");
                                System.out.println("Enter item name");

                                n1 = sc.next();
                                System.out.println("enter item code");
                                itcode = sc.nextInt();
                                System.out.println("enter updated price");
                                price = sc.nextDouble();
                                System.out.println("enter updated quantity");
                                upqty = sc.nextInt();

                                if(n1.length()==11){}
                                else{
                                    int lth=n1.length();

                                    for(int lth2= 11-lth;lth2==0;lth2--){
                                        n1=n1+"  ";
                                    }
                                }
                                try {
                                    FileWriter Writer = new FileWriter("inventory.txt", true);
                                    //DataOutputStream Writer = new DataOutputStream(new FileOutputStream("inventory.txt"));

                                    Writer.append(n1);
                                    Writer.append(",");
                                    Writer.write(String.valueOf(itcode));
                                    Writer.write(",");
                                    Writer.write(String.valueOf(price));
                                    Writer.write(",");
                                    Writer.write(String.valueOf(upqty));
                                    Writer.write("\n");
                                    Writer.close();
                                    System.out.println("Successfully updated.");
                                } catch (IOException e) {
                                    System.out.println("An error has occurred.");
                                    e.printStackTrace();
                                }
                                System.out.println("would you like to add more items?\n press 1 for yes\n press 2 for no");
                                x= sc.nextInt();
                            }while(x<2);
                        System.out.println("Inventory updated successfully!\n Exiting program now.......");
                    }

                    else{
                        System.out.println("Logging out of admin account and going to menu......\n");
                        displaymenu();
                    }
                }
                else{
                    System.out.println("Wrong uid and password. Program exiting.....");
                }


            }
        else{
            displaymenu();
            System.out.println("would you like to place order?");
            System.out.println("Press 1 for Yes\n Press 2 to Exit");
            int order= sc.nextInt();
            if (order==1){
               userlog();
            }
            else{
                System.out.println("We are sorry to have you leave!\nPlease visit again!");
            }
        }

        }

    private static void userlog() throws FileNotFoundException {
        System.out.println("Press 1 to login as an existing user\n Press 2 to create a new account");
        Scanner sc2= new Scanner(System.in);
        int uidlog = sc2.nextInt();
        if(uidlog==1){
            loginuser();
        }
        else{
            newuser();
        }
    }
  /*  static void loginuser() throws FileNotFoundException {
        System.out.println("Enter your User ID");
        Scanner sc3= new Scanner(System.in);
        int code;
        int UserID= sc3.nextInt();
        boolean found = false;
        String username;
        String userfiles ="users.txt";
        File obj = new File(userfiles);
        Scanner readuser = new Scanner(obj);
        while(readuser.hasNextLine()) {
        try{
            String line = readuser.nextLine();
            String[] parts = line.split(",");
            code = Integer.parseInt(parts[1]);
            if (UserID == code) {
                found = true;
                username = parts[0];
                System.out.println("Welcome" + username + "!");
                placeOrder();
            }
            else {
                System.out.println("You don't seem to have an account! please create a new account to continue!\nRedirecting now........");
                newuser();
            }
        }

        catch(InputMismatchException exc) {
            System.out.println("You seem to be entering something other than numbers! please try again!");
        }
        }



    }
*/
  static void loginuser() throws FileNotFoundException {
      System.out.println("Enter your User ID");
      Scanner sc3 = new Scanner(System.in);
      String UserID = sc3.nextLine();
      boolean found = false;
      username = "";
      String userfiles = "users.txt";
      File obj = new File(userfiles);
      Scanner readuser = new Scanner(obj);
      while (readuser.hasNextLine()) {
          String line = readuser.nextLine();
          String[] parts = line.split(",");
          String code = parts[1];
          if (UserID.equals(code)) {
              found = true;
              username = parts[0];
              System.out.println("Welcome " + username + "!");
              placeOrder();
          }
      }
      if (!found) {
          System.out.println("You don't seem to have an account! Please create a new account to continue!\nRedirecting now........");
          newuser();
      }
  }


    static int x;
    static List<String[]> orderedItems = new ArrayList<String[]>();

    /*static void placeOrder() {
        Scanner scanner2 = new Scanner(System.in);
        int itcode, itqty;

        do {
            try {
                String filePath = "inventory.txt";
                File file = new File(filePath);
                Scanner scanner = new Scanner(file);

                boolean found = false;

                System.out.print("Enter the code of the item you would like to order: ");

                itcode = scanner2.nextInt();// clear buffer

                while (scanner.hasNextLine()) {
                    String line = scanner.nextLine();
                    String[] parts = line.split(",");

                    code = Integer.parseInt(parts[1]);

                    if (code == itcode) {
                        found = true;
                        name = parts[0];
                        price = Double.parseDouble(parts[2]);
                        quantity = Integer.parseInt(parts[3]);
                        System.out.println("Item name: " + name);
                        System.out.printf("Price per unit: %.2f\n", price);
                        System.out.println("Available quantity: " + quantity);

                        System.out.print("Enter the quantity of item you would like to order: ");
                        itqty = scanner2.nextInt();

                        if (itqty <= quantity) {
                            double itemTotal = price * itqty;
                            double gst = itemTotal * 0.18;
                            quantity -= itqty;
                            updateInventory(code, quantity);
                            String[] itemDetails = {name, String.valueOf(itqty), String.valueOf(price), String.format("%.2f", gst), String.format("%.2f", itemTotal)};
                            orderedItems.add(itemDetails);
                        } else {
                            System.out.println("Sorry, the amount you have ordered seems to be above the available stock!");
                        }
                        break;
                    }
                }
                if (!found) {
                    System.out.println("Sorry, the item you have ordered does not exist! please try again!");
                }
            } catch (FileNotFoundException e) {
                System.out.println("Error: inventory file not found!");
            } catch (Exception e) {
                System.out.println("Error: invalid input!");
                scanner2.nextLine(); // clear buffer
            }
        } while (scanner2.next().equalsIgnoreCase("Y"));

        bill();
    }
    private static void bill() {
        try {
            double gst = total * 0.18;
            StringBuilder billDetails = new StringBuilder();
            billDetails.append("Bill for ").append(username).append(":\n");
            billDetails.append("--------------------------------------------------------------------\n");
            double totalCost = 0.0;
            double totalGST = 0.0;

            for (int i = 0; i < orderedItems.size(); i++) {
                String name = orderedItems.get(i).getName();
                int qty = orderedItems.get(i).getQuantity();
                double price = orderedItems.get(i).getPrice();
                double itemTotal = price * qty;
                double itemGST = itemTotal * 0.18;

                billDetails.append("Item Name       :  ").append(name).append("\n");
                billDetails.append("Quantity Ordered:  ").append(qty).append("\n");
                billDetails.append("Price per Unit  :  ").append(price).append("\n");
                billDetails.append("GST @18%        :  ").append(itemGST).append("\n");
                billDetails.append("Price before GST:  ").append(itemTotal).append("\n\n");

                totalCost += itemTotal;
                totalGST += itemGST;
            }

            billDetails.append("Total cost      :  ").append(totalCost).append("\n");
            billDetails.append("Total GST @18%  :  ").append(totalGST).append("\n");
            billDetails.append("Grand Total     :  ").append(totalCost + totalGST).append("\n");
            System.out.println("Thank you for your order!");

            FileWriter userbill = new FileWriter(username + ".txt", true);
            userbill.write(billDetails.toString());
            userbill.close();
            File userbillread = new File(username + ".txt");
            String billprint = "";

            FileReader fl = new FileReader(userbillread);
            BufferedReader bf = new BufferedReader(fl);
            while ((billprint = bf.readLine()) != null) {
                System.out.println(billprint);
            }
            fl.close();
            userbillread.delete();
            userbillread.createNewFile();

            // reset the total and itemsOrdered lists for the next order
            total = 0.0;
            orderedItems.clear();

        } catch (FileNotFoundException fnf) {
            System.out.println("Some error occurred, please try again later!");
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }*/

    static void placeOrder() {
        Scanner scanner2 = new Scanner(System.in);
        int itcode, itqty;

            do{
            try {
                String filePath = "inventory.txt";
                File file = new File(filePath);
                Scanner scanner = new Scanner(file);

                boolean found = false;

                System.out.print("Enter the code of the item you would like to order: ");

                itcode = scanner2.nextInt();// clear buffer

                while (scanner.hasNextLine()) {
                    String line = scanner.nextLine();
                    String[] parts = line.split(",");

                    code = Integer.parseInt(parts[1]);

                    if (code == itcode) {
                        found = true;
                        name = parts[0];
                        price = Double.parseDouble(parts[2]);
                        quantity = Integer.parseInt(parts[3]);
                        System.out.println("Item name: " + name);
                        System.out.printf("Price per unit: %.2f\n", price);
                        System.out.println("Available quantity: " + quantity);

                        System.out.print("Enter the quantity of item you would like to order: ");
                        itqty = scanner2.nextInt();
                        int itqty2= itqty;



                        if (itqty <= quantity) {
                            total = price * itqty;
                            double total1= total;
                            quantity -= itqty;
                            updateInventory(code, quantity);
            FileWriter userbill = new FileWriter(username+".txt",true);
            double itemaddtotal =+total;
        double gst= itemaddtotal*0.18;
            userbill.write("Bill for "+username+":\n");
            userbill.write("--------------------------------------------------------------------\n");
            userbill.write("Items ordered:  "+name+"\n");
            userbill.write("Quantity of "+name+" Ordered:  "+String.valueOf(itqty)+"\n");
            userbill.write("Price of "+name+" per Unit:  "+String.valueOf(price)+"\n");
            userbill.write("GST @18%        :  "+ gst+"\n");
            userbill.write("Price before GST:  "+total+"\n");
            userbill.write("Final cost      :  "+(total+gst+"\n"));
        userbill.close();


                        } else {
                            System.out.println("Sorry, the amount you have ordered seems to be above the available stock!");
                        }
                        break;
                    }
                }
                if (!found) {
                    System.out.println("Sorry, the item you have ordered does not exist! please try again!");
                }
            } catch (FileNotFoundException e) {
                System.out.println("Error: inventory file not found!");
            } catch (Exception e) {
                System.out.println("Error: invalid input!");
                scanner2.nextLine(); // clear buffer
            }


                System.out.print("Would you like to order another item? (Y for yes/Any other key for Bill): ");
            }
            while (scanner2.next().equalsIgnoreCase("Y"));
        bill();

    }

   /* private static void bill() {
        try {Scanner scanner2= new Scanner(System.in);
            double gst = total * 0.18;
            StringBuilder billDetails = new StringBuilder();
            billDetails.append("Bill for ").append(username).append(":\n");
            billDetails.append("--------------------------------------------------------------------\n");
            double totalCost = 0.0;
            double totalGST = 0.0;

                billDetails.append("Items ordered:  ").append(name).append("\n");
                billDetails.append("Quantity of ").append(name).append(" Ordered:  ").append(String.valueOf(total/price)).append("\n");
                billDetails.append("Price of ").append(name).append(" per Unit:  ").append(String.valueOf(price)).append("\n");
                billDetails.append("GST @18%        :  ").append(gst).append("\n");
                double itemTotal = total;
                billDetails.append("Price before GST:  ").append(total).append("\n");
                billDetails.append("Final cost      :  ").append(itemTotal).append("\n\n");
                totalCost += itemTotal;
                totalGST += gst;




            billDetails.append("Total cost      :  ").append(totalCost).append("\n");
            billDetails.append("Total GST @18%  :  ").append(totalGST).append("\n");
            billDetails.append("Grand Total     :  ").append(totalCost + totalGST).append("\n");
            System.out.println("Thank you for your order!");

            FileWriter userbill = new FileWriter(username + ".txt", true);
            userbill.write(billDetails.toString());
            userbill.close();
            File userbillread = new File(username + ".txt");
            String billprint = "";

            FileReader fl = new FileReader(userbillread);
            BufferedReader bf = new BufferedReader(fl);
            while ((billprint = bf.readLine()) != null) {
                System.out.println(billprint);
            }
            fl.close();
            userbillread.delete();
            userbillread.createNewFile();

        } catch (FileNotFoundException fnf) {
            System.out.println("Some error occurred, please try again later!");
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
    */

    private static void bill(){  try{
            File userbillread = new File(username+".txt");
            String billprint= "";

        FileReader fl=new FileReader(userbillread);
        BufferedReader bf=new BufferedReader(fl);
        while((billprint=bf.readLine())!=null){
            System.out.println(billprint);
        }
        fl.close();
            userbillread.delete();
            userbillread.createNewFile();

            //close file here


        // System.out.println("Cost:"+total);
       // System.out.println("GST:"+gst);
        //System.out.println(total1);
        //System.out.printf("Total cost: %.2f\n", (total+gst));
        System.out.println("Thank you for your order!");

    }catch(FileNotFoundException fnf){
        System.out.println("Some error occured, please try again later!");
    } catch (IOException e) {
        throw new RuntimeException(e);
    }
    }


    static void updateInventory(int code, int quantity) throws Exception {
        File inputFile = new File("inventory.txt");
        File tempFile = new File("temp.txt");
        PrintWriter writer = new PrintWriter(new FileWriter(tempFile));
        Scanner scanner = new Scanner(inputFile);

        while (scanner.hasNextLine()) {
            String line = scanner.nextLine();
            String[] parts = line.split(",");
            int itemCode = Integer.parseInt(parts[1]);
            if (itemCode == code) {
                parts[3] = Integer.toString(quantity);
                line = String.join(",", parts);
            }
            writer.println(line);
        }

        writer.close();
        scanner.close();
        inputFile.delete();
        tempFile.renameTo(inputFile);
    }
/*static void placeorder(){
        do{
            try{
                String filePath = "inventory.txt";

                // Create a File object
                File file = new File(filePath);


                    // Create a Scanner object to read from the file
                    Scanner scanner = new Scanner(file);

                    // Define variables to store the data
                    String name;
                    int code;
                    double price;
                    int quantity;
                System.out.println("Enter the code of the item you would like to order:  ");
                Scanner scanner2 = new Scanner(System.in);
                int itcode= scanner2.nextInt();
                while (scanner.hasNextLine()) {
                    String line = scanner.nextLine();
                    String[] parts = line.split(",");

                    code = Integer.parseInt(parts[1]);

                    if (code == itcode) {
                        // Get the name of the item
                        name = parts[0];
                        System.out.println("Item name: " + name);
                        break;
                    }
                }
                    /*switch (itcode){
                        case 1:
                            System.out.println("How much coffee would you like to have?");
                            int itqtyorder1= scanner.nextInt();

                        case 2:
                            System.out.println("How much coffee would you like to have?");
                            int itqtyorder2= scanner.nextInt();
                        case 3:
                            System.out.println("How many bred would you like to have?");
                            int itqtyorder3= scanner.nextInt();
                        case 4:
                            System.out.println("How many latte's would you like to have?");
                            int itqtyorder4= scanner.nextInt();
                        case 5:
                            System.out.println("How many crackers would you like to have?");
                            int itqtyorder5= scanner.nextInt();
                    }


                try{
        System.out.println("Enter the quantity of item you would like to order:  ");
        int itqty= scanner.nextInt();
    }
                catch (Exception g){
        System.out.println("Sorry, the amount you have ordered seems to be above the available stock!");
    }
}
            catch (Exception h){
                    System.out.println("Sorry, the item you have ordered does not exist! please try again!");

                    }
                    }while(x>1);
                    }
*/
static void newuser() throws FileNotFoundException {
        System.out.println("Enter your mobile number:");
        Scanner sc3 = new Scanner(System.in);
        long mobnum= sc3.nextLong();
        System.out.println("Enter your name:");
        String nameuser = sc3.next();
        try {
            FileWriter uidlist = new FileWriter("users.txt", true);
            uidlist.append(nameuser);
            uidlist.append(",");
            uidlist.append(String.valueOf(mobnum));
            uidlist.append(",\n");
            uidlist.close();
        }
        catch(Exception f){
            System.out.println("Some error occured");
        }
        userlog();
    }
    private static void displaymenu() {
        System.out.println("        Menu for today:");
        System.out.println("--------------------------------------------------------------------------------------------");
        try {System.out.println("Item name  |  item code  |   price    |  available qty |\n---------------------------------------------------------------------");
            File Obj = new File("inventory.txt");
            Scanner Reader = new Scanner(Obj);
            while (Reader.hasNextLine()) {
                String data = Reader.nextLine();
                String[] array = data.split(",");

                for(int i=0; i<array.length;i++){
                    System.out.print(array[i]+"    |    ");
                    if(i==3){
                        String[] pricearr= new String[]{array[i]};
                        length++;
                    }
                    else{}
                }
                System.out.println("\n----------------------------------------------------------------------");
            }
            Reader.close();
        }
        catch (FileNotFoundException e) {
            System.out.println("An error has occurred.");
            e.printStackTrace();
        }
    }
}
