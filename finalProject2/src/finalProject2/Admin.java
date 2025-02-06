package finalProject2;
import java.util.*;
import java.io.*;
public class Admin  implements Print{
	
    public Admin() {
	
    }
   
Scanner sc=new Scanner(System.in);
public static  ArrayList<Item> itemslist=new ArrayList<>();
public static ArrayList<Client>  clientslist=Client.clientslist;

public void fillwithItems(){
	String path="C:\\Users\\user123\\Desktop\\item1.csv";
	String splitCsvby=",";
	try {
		BufferedReader reader=new BufferedReader(new FileReader(path));
	
		String line="";
		
		while((line=reader.readLine())!=null) {
			 String[] splitArray=new String[5];
	         splitArray=line.split(splitCsvby);
	         
		Item i=new Item(splitArray[0],Double.parseDouble(splitArray[1]),Integer.parseInt(splitArray[2]),splitArray[3],Integer.parseInt(splitArray[4]));
		 itemslist.add(i);
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


  
/*btektoub data bl csv byerja3 btesta3mil fillwith ta tsir l data objects*/
public void createItem() {
	try {
		String path="C:\\Users\\user123\\Desktop\\item1.csv";
		String line1="";
		BufferedReader reader=new BufferedReader(new FileReader(path));
	    String[] values1;
	String name="";
	int quantity=0;
	double price=0;
	String cat="";
	boolean doesNotExist=true;    
		System.out.println("Enter the name of the item you want to create:");
	name=sc.nextLine();
	
	while((line1 = reader.readLine()) != null) {
		values1 = line1.split(",");
		if(name.equalsIgnoreCase(values1[0])) {
			System.out.println("This item already exists");
			doesNotExist=false;
			break;
		}
	
	}
	if(doesNotExist) {
		do {
	System.out.println("Enter the price of the item you want to create:");
	price=sc.nextDouble();
	sc.nextLine();
		}while(price<=0);
		
		do {
	System.out.println("Enter the quantity of the item you want to create:");
	quantity=sc.nextInt();
		}while(quantity<=0);
		
	sc.nextLine();
	do {
	System.out.println("Enter the cat of the item you want to create('p'for pills---'h'for hygiene---'e' for equipments---'c'for creme):");
	cat=sc.nextLine();
	}while(!cat.equalsIgnoreCase("p")&&!cat.equalsIgnoreCase("h")&&!cat.equalsIgnoreCase("e")&&!cat.equalsIgnoreCase("c"));

	String[] values=new String[5];
		String line="";
		BufferedWriter writer = new BufferedWriter(new FileWriter(path, true));
		/*because we had a problem when in the same program we would create a a new item the arraylist couldn't save it bc the fill with is used outside the loop in the main*/
	    Item i=new Item(name,price,quantity,cat,0);
	    itemslist.add(i);
	    
		values[0]=name;
		values[1]=price+"";
		values[2]=Integer.toString(quantity);
		values[3]=cat;
		values[4]=0+"";
		 line = String.join(",", values);
			writer.write(line + "\n");
			reader.close();
		writer.close();
	}
	} catch (IOException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
	//fillwithItems();
	
}
 








public void addQuantity() {
    //fillwithItems();
	boolean doesExist=false;
	String path="C:\\Users\\user123\\Desktop\\item1.csv";
	int quantity;
    String name;
    System.out.println("Enter the name of the item you want to add quantity to:");
    name=sc.nextLine();
    
    for(Item i:itemslist) {
    	if(i.getName().equalsIgnoreCase(name)) {
    		int cpy=i.getQuantity();
    		do {
    		System.out.println("Enter the quantity:");
    		quantity=sc.nextInt();
    		}while(quantity<=0);
    		sc.nextLine();
    		cpy+=quantity;
            i.setQuantity(cpy);
            doesExist=true;
    	}
    	
    }
    if(doesExist==false) {
		System.out.println("this item does not exist go back and choose \"Create Items\"");
	}
    
    
	try {
		BufferedWriter writer = new BufferedWriter(new FileWriter(path, false));
		BufferedReader reader=new BufferedReader(new FileReader(path));
		String[] values=new String[5];
		String line="";
	    
		 for(Item i:itemslist) {
			 values[0]=i.getName();
			 values[1]=Double.toString(i.getPrice());
			 values[2]=Integer.toString(i.getQuantity());
			 values[3]=i.getCat();
			 values[4]=Integer.toString(i.getSales());
			 line = String.join(",", values);
				writer.write(line + "\n");
		 }
		reader.close();
		writer.close();
		
		
	} catch (IOException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
}
	
	
	
	
	
	public void removeItem() {
		//fillwithItems();
		boolean doesExist=false;
		String path="C:\\Users\\user123\\Desktop\\item1.csv";
		int quantity;
	    String name;
	    System.out.println("Enter the name of the item you want to delete:");
	    name=sc.nextLine();
	    
	    for(Item i:itemslist) {
	    	if(i.getName().equalsIgnoreCase(name)) {
	    		itemslist.remove(i);
	            doesExist=true;
	            /*we used break because of the iteration rule*/
	            break;
	    	}
	    	
	    }
	    if(doesExist==false) {
			System.out.println("this item does not exist go back and choose \"Create Items\"");
		}
	    
	    
		try {
			BufferedWriter writer = new BufferedWriter(new FileWriter(path, false));
			BufferedReader reader=new BufferedReader(new FileReader(path));
			String[] values=new String[5];
			String line="";
		    
			 for(Item i:itemslist) {
				 values[0]=i.getName();
				 values[1]=Double.toString(i.getPrice());
				 values[2]=Integer.toString(i.getQuantity());
				 values[3]=i.getCat();
				 values[4]=Integer.toString(i.getSales());
				 line = String.join(",", values);
					writer.write(line + "\n");
			 }
			reader.close();
			writer.close();
			
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}
	
	
	
	
	public void editPrice() {
		//fillwithItems();
		boolean doesExist=false;
		String path="C:\\Users\\user123\\Desktop\\item1.csv";
		double price;
	    String name;
	    System.out.println("Enter the name of the item you want to edit it's price:");
	    name=sc.nextLine();
	    
	    for(Item i:itemslist) {
	    	if(i.getName().equalsIgnoreCase(name)) {
	    	do {
	    		System.out.println("Enter the price:");
	    		price=sc.nextDouble();
	    	}while(price<=0);
	    	sc.nextLine();
	    		i.setPrice(price);
	            doesExist=true;
	    	}
	    	
	    }
	    if(doesExist==false) {
			System.out.println("this item does not exist go back and choose \"Create Items\"");
		}
	    
	    
		try {
			BufferedWriter writer = new BufferedWriter(new FileWriter(path, false));
			BufferedReader reader=new BufferedReader(new FileReader(path));
			String[] values=new String[5];
			String line="";
		    
			 for(Item i:itemslist) {
				 values[0]=i.getName();
				 values[1]=Double.toString(i.getPrice());
				 values[2]=Integer.toString(i.getQuantity());
				 values[3]=i.getCat();
				 values[4]=Integer.toString(i.getSales());
				 line = String.join(",", values);
					writer.write(line + "\n");
			 }
			reader.close();
			writer.close();
			
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	
	
	public void printCat() {
		
		String cat;
		do {
		System.out.println("Enter the Letter of the category you want to have access to:\np-for pills\ne-for equipments\nh-for hygiene:");
		cat=sc.nextLine();
		}while(!cat.equalsIgnoreCase("p")&&!cat.equalsIgnoreCase("h")&&!cat.equalsIgnoreCase("e"));
		try{
			String path="C:\\Users\\user123\\Desktop\\item1.csv";
			String line1="";
			BufferedReader reader=new BufferedReader(new FileReader(path));
		    String[] values1;
		    System.out.println("Name---------Price---------Quantity---------Category---------Sales\n");
		    while((line1 = reader.readLine()) != null) {
				values1 = line1.split(",");
				if(cat.equalsIgnoreCase(values1[3])) {
					System.out.println(values1[0]+"---------"+values1[1]+"$---------"+values1[2]+" in stock---------"+values1[3]+"---------"+values1[4]);
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
	
	
	
	
	
	
	
	public void createEmployee() {
		String name,position;
		double salary;
		int age;
		int nbspaces;
		do {
			nbspaces=0;
		System.out.println("Enter the name of the employee you want to create:");
		name=sc.nextLine();
		for(int i=0;i<name.length();i++) {
			if(name.charAt(i)==' ') {
			nbspaces++;
			}
		}
		
		}while(nbspaces>0);
		do {
			System.out.println("Enter the age of the employee you want to create:");
			age=sc.nextInt();
			sc.nextLine();
		}while(age<18);
		
		do {
			System.out.println("Enter the position of the employee you want to create(pharmacist or cashier):");
			position=sc.nextLine();
		}while(!position.equalsIgnoreCase("pharmacist")&&!position.equalsIgnoreCase("cashier"));
		
		do {
			System.out.println("Enter the salary of the employee you want to create:");
			salary=sc.nextDouble();
		}while(salary<=0);
		sc.nextLine();
		Employee e=new Employee(name,age,position,salary,0.0);
		employeelist.add(e);
		
		try {
			String path="C:\\Users\\user123\\Desktop\\Employee.csv";
			e.GenerateId(path);
			System.out.println("His is will be:"+e.getId());

			BufferedWriter writer = new BufferedWriter(new FileWriter(path, true));
			String line="";
		
			String values[]=new String[6];
		    values[0]=name;
		    values[1]=age+"";
			values[2]=e.getId();
			values[3]=position;
			values[4]=salary+"";
			values[5]=e.getReceived()+"";
			
			 line = String.join(",", values);
				writer.write(line + "\n");
				
			writer.close();
		}catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
	}

	
	
	
	
	
	
	
	
	
		
public static ArrayList<Employee> employeelist=new ArrayList<>();

public static void fillwithEmployees(){
	String path="C:\\Users\\user123\\Desktop\\Employee.csv";
	String splitCsvby=",";
	try {
		BufferedReader reader=new BufferedReader(new FileReader(path));
		/*So it skips the first line in the csv where the attributes are written*/
		String line="";
		
		while((line=reader.readLine())!=null) {
			 String[] splitArray=new String[6];
	         splitArray=line.split(splitCsvby);
		Employee e=new Employee(splitArray[0],Integer.parseInt(splitArray[1]),splitArray[3],Double.parseDouble(splitArray[4]),Double.parseDouble(splitArray[5]));
       e.setId(splitArray[2]);
		employeelist.add(e);
		
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





public void displayEmployees() {
	
	try{
		String path="C:\\\\Users\\\\user123\\\\Desktop\\\\Employee.csv";
		String line1="";
		BufferedReader reader=new BufferedReader(new FileReader(path));
	    String[] values1;
	    System.out.println("Name---------Age---------Id---------Position---------Salary---------Received\n");
	    while((line1 = reader.readLine()) != null) {
			values1 = line1.split(",");
				System.out.println(values1[0]+"---------"+values1[1]+"---------"+values1[2]+" ---------"+values1[3]+"---------"+values1[4]+"---------"+values1[5]);
			
		
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





public void editPosition() {
	boolean doesExist=true;
	
	// fillwithEmployees();
	 
	String id,position;
	
	System.out.println("Enter the id of the employee you want to change position:");
	id=sc.nextLine();
	
	
	for(Employee e:employeelist) {
		if(e.getId().equalsIgnoreCase(id)) {
			System.out.println("His current position is:"+e.getPosition());			
			do {
			System.out.println("Enter his new position:");
			position=sc.nextLine();
			}while(!position.equalsIgnoreCase("pharmacist")&&!position.equalsIgnoreCase("cashier"));
			e.setPosition(position);
			doesExist=false;
			break;
			
		}
	}
	
	if(doesExist) {
		System.out.println("This id doesn't exist");
	}
	
	
	
	try {
		String path="C:\\\\Users\\\\user123\\\\Desktop\\\\Employee.csv";
		BufferedWriter writer = new BufferedWriter(new FileWriter(path, false));
		String[] values=new String[6];
		String line="";
	    
		 for(Employee e:employeelist) {
			 values[0]=e.getName();
			 values[1]=Integer.toString(e.getAge());
			 values[2]=e.getId();
			 values[3]=e.getPosition();
			 values[4]=e.getSalary()+"";
			 values[5]=e.getReceived()+"";
			 line = String.join(",", values);
				writer.write(line + "\n");
		 }
		writer.close();
		
		
	} catch (IOException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
	
}




public void editSalary() {
	boolean doesExist=true;
	
	// fillwithEmployees();
	 
	String id;
	double salary;
	
	System.out.println("Enter the id of the employee you want to change salary:");
	id=sc.nextLine();
	
	
	for(Employee e:employeelist) {
		if(e.getId().equalsIgnoreCase(id)) {
			System.out.println("His current Salary is:"+e.getSalary()+"$");			
			do {
			System.out.println("Enter his new salary:");
			salary=sc.nextDouble();
			}while(salary<=0);
			sc.nextLine();
            e.setSalary(salary);
			doesExist=false;
			break;
			
		}
	}
	
	if(doesExist) {
		System.out.println("This id doesn't exist");
	}
	
	
	
	try {
		String path="C:\\\\Users\\\\user123\\\\Desktop\\\\Employee.csv";
		BufferedWriter writer = new BufferedWriter(new FileWriter(path, false));
		String[] values=new String[6];
		String line="";
	    
		 for(Employee e:employeelist) {
			 values[0]=e.getName();
			 values[1]=Integer.toString(e.getAge());
			 values[2]=e.getId();
			 values[3]=e.getPosition();
			 values[4]=e.getSalary()+"";
			 values[5]=e.getReceived()+"";
			 line = String.join(",", values);
				writer.write(line + "\n");
		 }
		writer.close();
		
		
	} catch (IOException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
	
}








public void giveSalary() {
	boolean doesExist=true;
	
	// fillwithEmployees();
	 
	String id;
	double salary;
	
	System.out.println("Enter the id of the employee you want to give salary:");
	id=sc.nextLine();
	
	
	for(Employee e:employeelist) {
		if(e.getId().equalsIgnoreCase(id)) {
			if(e.getReceived()==0) {
				e.setReceived(1);
				System.out.println("the salary:"+e.getSalary()+"$ has been successfully sent!");
			}
			else System.out.println("his salary has already been sent!");
			doesExist=false;
			break;
			
		}
	}
	
	if(doesExist) {
		System.out.println("This id doesn't exist");
	}
	
	
	
	try {
		String path="C:\\\\Users\\\\user123\\\\Desktop\\\\Employee.csv";
		BufferedWriter writer = new BufferedWriter(new FileWriter(path, false));
		String[] values=new String[6];
		String line="";
	    
		 for(Employee e:employeelist) {
			 values[0]=e.getName();
			 values[1]=Integer.toString(e.getAge());
			 values[2]=e.getId();
			 values[3]=e.getPosition();
			 values[4]=e.getSalary()+"";
			 values[5]=e.getReceived()+"";
			 line = String.join(",", values);
				writer.write(line + "\n");
		 }
		writer.close();
		
		
	} catch (IOException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
	
}




public void removeEmployee() {
	//fillwithItems();
	boolean doesExist=false;
	String path="C:\\Users\\user123\\Desktop\\Employee.csv";
    String id;
    System.out.println("Enter the id of the employee you want to delete:");
    id=sc.nextLine();
    
    for(Employee e:employeelist) {
    	if(e.getId().equals(id)) {
    		employeelist.remove(e);
            doesExist=true;
            /*we used break because of the iteration rule*/
            break;
    	}
    	
    }
    if(doesExist==false) {
		System.out.println("id not found");
	}
    
    
	try {
		BufferedWriter writer = new BufferedWriter(new FileWriter(path, false));
		BufferedReader reader=new BufferedReader(new FileReader(path));
		String[] values=new String[6];
		String line="";
	    
		for(Employee e:employeelist) {
			 values[0]=e.getName();
			 values[1]=Integer.toString(e.getAge());
			 values[2]=e.getId();
			 values[3]=e.getPosition();
			 values[4]=Double.toString(e.getSalary());
			 values[5]=Double.toString(e.getReceived());

			 line = String.join(",", values);
				writer.write(line + "\n");
		 }
		reader.close();
		writer.close();
		
		
	} catch (IOException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}

}




public void printClient() {
	try{
		String path="C:\\Users\\user123\\Desktop\\data.csv";
		String line1="";
		BufferedReader reader=new BufferedReader(new FileReader(path));
	    String[] values1;
	    System.out.println("Name---------age---------id---------item---------quantity---------price\n");
	    while((line1 = reader.readLine()) != null) {
			values1 = line1.split(",");
			
				System.out.println(values1[0]+"---------"+values1[1]+"y.o---------"+values1[2]+"---------"+values1[3]+"---------"+values1[4]+"---------"+values1[5]+"$");
			
		
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




}
