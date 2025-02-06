package finalProject2;
import java.io.*;
import java.util.*;

public class Client extends Person implements Print {
    private String clientId;

    private String data = "C:\\Users\\user123\\Desktop\\data.csv";
    private String item1 = "C:\\Users\\user123\\Desktop\\item1.csv";
    private double sumPrice;

    private double finalPrice;

    private int count;
    private ArrayList<Item> cart;
    private double totalCost;

    private int quantityToBuy;

    private int clientQty;

    Scanner sc = new Scanner(System.in);

    public static ArrayList<Item> itemslist =Admin.itemslist;

    public Client(String name, int age) {
        super(name, age);
        this.cart = new ArrayList<>();
        this.totalCost = 0.0;
        GenerateId(data);
    }
    public Client() {

    }
    public void setQuantityToBuy(int quantityToBuy) {
        this.quantityToBuy = quantityToBuy;
    }

    public void setFinalPrice(double finalPrice) {
        this.finalPrice = finalPrice;
    }

    public double getFinalPrice() {
        return finalPrice;
    }

    public int getQuantityToBuy() {
        return quantityToBuy;
    }


    public void setName(String name) {
        super.setName(name);
    }

    @Override
    public String getName() {
        return super.getName();
    }


    public double getSumPrice() {
        return sumPrice;
    }

    public void setSumPrice(double sumPrice) {
        this.sumPrice = sumPrice;
    }

    @Override
    public void GenerateId(String path) {
        super.GenerateId(path);
    }

    @Override
    public void setId(String id) {
        super.setId(id);
    }

    @Override
    public String getId() {
        return super.getId();
    }

    
    public void setClientQty(int clientQty) {
        this.clientQty = clientQty;
    }

    public int getClientQty() {
        return clientQty;
    }


    public void buyItems() {
        System.out.println("Available Categories:");
        printCat();

        double sumPrice = 0;
        Item chosenItem = null;
do{
        System.out.print("Name: ");
        String itemName = sc.nextLine();



        for (Item item : itemslist) {
            if (item.getName().equalsIgnoreCase(itemName)) {
                chosenItem = item;
                break;
            }
        }

        if (chosenItem != null) {
            int quantityToBuy;
            do {
                System.out.print("Quantity: ");
                quantityToBuy = sc.nextInt();
                sc.nextLine();

                if (quantityToBuy < chosenItem.getQuantity()) {
                    if (quantityToBuy >= 0) {
                        setQuantityToBuy(quantityToBuy);

                        sumPrice = quantityToBuy * chosenItem.getPrice();
                        setSumPrice(sumPrice);

                        if (quantityToBuy <= chosenItem.getQuantity()) {
                            chosenItem.setQuantity(chosenItem.getQuantity());
                            cart.add(chosenItem);

                            int sales = chosenItem.getSales() + quantityToBuy;
                            chosenItem.setSales(sales);

                            System.out.println("Purchase successful!");

                            reducedQty(chosenItem, quantityToBuy, sales);
                        } else {
                            System.out.println("Sorry, the requested quantity is not available.");
                        }
                    } else {
                        System.out.println("Invalid quantity. Quantity cannot be negative.");
                    }
                } else {
                    System.out.println("No enough quantity in stock");
                }
            } while (quantityToBuy < 0 || quantityToBuy > chosenItem.getQuantity());
        } else {
            System.out.println("Item not found.");
        }
    }while(chosenItem == null);
        System.out.println("Your total is " + getSumPrice());

        savePurchaseDetailsToCSV();

        cart.clear();
    }







    public void reducedQty(Item chosenItem, int quantityToBuy, int sales) {
        String filePath = item1;
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(filePath, false))) {
            for (Item i : itemslist) {
                String[] values = new String[5];
                if (i.getName().equalsIgnoreCase(chosenItem.getName())) {
                    int remainingQuantity = i.getQuantity() - quantityToBuy;
                    if (remainingQuantity >= 0) {
                        i.setQuantity(remainingQuantity);
                    } else {
                        remainingQuantity = 0;
                        i.setQuantity(remainingQuantity);
                    }
                }
                values[0] = i.getName();
                values[1] = Double.toString(i.getPrice());
                values[2] = Integer.toString(i.getQuantity());
                values[3] = i.getCat();
                values[4] = Integer.toString(i.getSales());

                String line = String.join(",", values);
                writer.write(line + "\n");
            }
        } catch (IOException e) {
            System.out.println("Error writing to CSV file: " + e.getMessage());
        }
    }


    public void savePurchaseDetailsToCSV() {
        String filePath = data;
        List<String> newLines = new ArrayList<>();
        List<String> existingEntries = new ArrayList<>();

        try (BufferedReader reader = new BufferedReader(new FileReader(filePath))) {
            String line;
            while ((line = reader.readLine()) != null) {
                if (line.contains(getId())) {
                    existingEntries.add(line);
                } else {
                    newLines.add(line);
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        // Append new purchase details
        StringBuilder newPurchase = new StringBuilder();
        newPurchase.append(getName()).append(",").append(getAge()).append(",").append(getId()).append(",");
        for (Item item : cart) {
            boolean itemFound = false;
            for (Iterator<String> iterator = existingEntries.iterator(); iterator.hasNext();) {
                String entry = iterator.next();
                if (entry.contains(item.getName())) {
                    // Item already exists in the existing entries, update its quantity
                    String[] parts = entry.split(",");
                    int existingQuantity = Integer.parseInt(parts[4]);
                    int newQuantity = existingQuantity + getQuantityToBuy();
                    double totalPrice = Double.parseDouble(parts[5]) + item.getPrice() * getQuantityToBuy();
                    newPurchase.append(item.getName()).append(",").append(newQuantity).append(",").append(totalPrice).append(",");
                    // Remove the previous entry for this item
                    iterator.remove();
                    itemFound = true;
                    break;
                }
            }
            if (!itemFound) {
                // Item does not exist in existing entries, append it as a new entry
                newPurchase.append(item.getName()).append(",").append(getQuantityToBuy()).append(",").append(item.getPrice() * getQuantityToBuy()).append(",");
            }
        }
        newLines.add(newPurchase.toString());

        // Append remaining existing entries with the same ID
        newLines.addAll(existingEntries);

        try (BufferedWriter writer = new BufferedWriter(new FileWriter(filePath))) {
            for (String line : newLines) {
                writer.write(line + "\n");
            }
            System.out.println("Purchase details saved to: " + filePath);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }







    public static void fillwithItems() {
        String path = "C:\\Users\\user123\\Desktop\\item1.csv";
        String splitCsvby = ",";
        try {
            BufferedReader reader = new BufferedReader(new FileReader(path));
//		/So it skips the first line in the csv where the attributes are written/
            String line = "";

            while ((line = reader.readLine()) != null) {
                String[] splitArray = new String[5];
                splitArray = line.split(splitCsvby);
                Item i = new Item(splitArray[0], Double.parseDouble(splitArray[1]), Integer.parseInt(splitArray[2]), splitArray[3], Integer.parseInt(splitArray[4]));
                itemslist.add(i);

            }
            reader.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (NumberFormatException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }

    @Override
    public void printCat() {
        String cat;
        do {
            System.out.println("Enter the letter of the category you want to have access to:\n" +
                    "p-pills\n" +
                    "e-equipment\n" +
                    "c- creme\n"+
                    "h-hygiene");
            cat = sc.nextLine();
        } while (!cat.equalsIgnoreCase("p") && !cat.equalsIgnoreCase("h") && !cat.equalsIgnoreCase("e") && !cat.equalsIgnoreCase("c"));

        try {
            String path = item1;
            String line1 = "";
            BufferedReader reader = new BufferedReader(new FileReader(path));
            String[] values1;
            while ((line1 = reader.readLine()) != null) {
                values1 = line1.split(",");
                if (cat.equalsIgnoreCase(values1[3])) {
                    System.out.println(values1[0] + "---------" + values1[1] + "$---------" + values1[2] + " in stock---------" + values1[3]);
                }
            }
            reader.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (NumberFormatException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();

        }
    }




    public void printClient() {
        try{
            String path= data;
            String line1="";
            BufferedReader reader=new BufferedReader(new FileReader(path));
            String id;
            System.out.println("Re-enter your id:");
            id=sc.nextLine();
            String[] values1;
            int i=0;
            while((line1 = reader.readLine()) != null) {
                values1 = line1.split(",");
                if(values1[2].equals(id)) {
                    if(i==0) {
                        System.out.println("Name---------age---------id---------item---------quantity---------price\n");
                    }



                    System.out.println(values1[0]+"---------"+values1[1]+"y.o---------"+values1[2]+"---------"+values1[3]+"---------"+values1[4]+"---------"+values1[5]+"$");
                    i++;
                }

            }
            reader.close();
        }catch(FileNotFoundException e) {
            e.printStackTrace();
        } catch (NumberFormatException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
}



    public static ArrayList<Client>  clientslist=new ArrayList<>();
    public void fillwithClients() {
        String filePath = data;
        String splitCsvby=",";
        try {
            BufferedReader reader=new BufferedReader(new FileReader(filePath));

            String line="";

            while((line=reader.readLine())!=null) {
                String[] splitArray=new String[5];
                splitArray=line.split(splitCsvby);

                Client c=new Client(splitArray[0],Integer.parseInt(splitArray[1]));
                c.setId(splitArray[2]);
                clientslist.add(c);
            }
            reader.close();
        }catch(FileNotFoundException e) {
            e.printStackTrace();
        } catch (NumberFormatException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

    }


    public boolean compareID(String id) {
        boolean idExists = false;
        try (BufferedReader reader = new BufferedReader(new FileReader(item1))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] values = line.split(",");
                if (id.equalsIgnoreCase(values[2])) {
                    idExists = true;
                    break; // Exit the loop once the ID is found
                }
            }
        } catch (IOException e) {
            System.out.println("Error reading from CSV file: " + e.getMessage());
        }
        return idExists;
    }




   
}