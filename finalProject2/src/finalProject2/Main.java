package finalProject2;
import java.util.*;
public class Main {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		 Scanner sc=new Scanner(System.in);
		  Admin a=new Admin();
		  String id,password;
		  ArrayList<Employee> employeelist=Admin.employeelist;
		  ArrayList<Client>  clientslist=Client.clientslist;
		  a.fillwithItems();
		  a.fillwithEmployees();
		  int choice1,choice2;
		  boolean exists=true;
		  Client c1=new Client();
		  c1.fillwithClients();
		  do {
		   System.out.println("Enter you type:\n1-Admin\n2-Employee\n3-Client\n0-to exit:");
		   choice1=sc.nextInt();
		   sc.nextLine();
		   if(choice1==1) {


		    System.out.println("Enter the password:");
		    password=sc.nextLine();

		    if(password.equals("admin123")) {

		     do {
		      System.out.println("Choose the function that you want to use:\n1-Create new item\n2-Add quantity\n3-Edit prices\n4-Remove items\n5-Print items of a category\n6-Create employee\n7-Edit Employee position\n8-Edit Employee Salary\n9-Give an Employee salary\n10-Display Employees\n11-Remove Employee\n12-Display Clients\n0-exit");
		      choice2=sc.nextInt();
		      if(choice2==1)
		       a.createItem();
		      if(choice2==2)
		       a.addQuantity();
		      if(choice2==3)
		       a.editPrice();
		      if(choice2==4)
		       a.removeItem();
		      if(choice2==5) {
		       a.printCat();
		      }
		      if(choice2==6) {
		       a.createEmployee();
		      }
		      if(choice2==7) {
		       a.editPosition();
		      }
		      if(choice2==8) {
		       a.editSalary();
		      }
		      if(choice2==9) {
		       a.giveSalary();
		      }
		      if(choice2==10) {
		       a.displayEmployees();
		      }
		      if(choice2==11) {
		       a.removeEmployee();
		      }
		      if(choice2==12) {
		       a.printClient();
		      }
		      if(choice2==0) {
		       break;
		      }
		     }while(choice2!=0);
		    }
		    else System.out.println("Incorrect password");
		   }

		   if(choice1==2) {
		    do {
		     System.out.println("Enter your id:");
		     id=sc.nextLine();

		     System.out.println("1-received salary\n2-view information\n0-to exist");
		     choice2=sc.nextInt();

		     sc.nextLine();
		     if(choice2==1) {
		      for(Employee e:employeelist) {
		       if(e.getId().equals(id)) {
		        e.receiveSalary(e);
		        exists=false;
		       }
		      }
		      if(exists) {
		       System.out.println("id not found");
		      }
		     }
		     if(choice2==2) {
		      for(Employee e:employeelist) {
		       if(e.getId().equals(id)) {
		        System.out.println("Name---------Age---------Id---------Position---------Salary---------Received");
		        System.out.println(e);
		        exists=false;
		       }
		      }
		      if(exists) {
		       System.out.println("id not found");
		      }
		     }
		     if(choice2==0) {
		      break;
		     }
		    }while(choice2!=0);
		   }

		   if(choice1==3) {


		    do {
		     do {
		      System.out.println("\nDo you have an account?\n1-yes\n2-no\n0-exit ");
		      choice2=sc.nextInt();
		      sc.nextLine();
		     }while(choice2!=1&&choice2!=2&&choice2!=0);

		     if(choice2==1) {
		      boolean exist=true;
		      String clientId;
		      System.out.println("Enter your id:");
		      clientId=sc.nextLine();
		      for(Client c7:clientslist) {
		       if(c7.getId().equals(clientId)) {
		        System.out.println("Welcome back");
		        int choice3;
		        do {
		         System.out.println("1-buy items\n2-display info\n0-exit");
		         choice3=sc.nextInt();
		         sc.nextLine();
		         if(choice3==1) {
		          for(Client c: clientslist) {
		           if(c.getId().equals(clientId)) {
		            c.buyItems();
		            break;
		           }
		          }
		         }
		         if(choice3==2) {
		        	 for(Client c: clientslist) {
				           if(c.getId().equals(clientId)) {
				            c.printClient();
				            break;
				           }
		         }
		         }
		        }while(choice3!=0);
		        exist=false;
		        break;
		       }
		      }
		      if(exist) {
		       System.out.println("this id doesn't exist");

		      }

		     }

		     if(choice2==2) {
		      String name;
		      int age;
		      int nbspaces;
		      do {
		    	 nbspaces=0;
		      System.out.println("Enter your name:");
		      name=sc.nextLine();
		      for(int i=0;i<name.length();i++) {
		    	  if(name.charAt(i)==' ') {
		    		  nbspaces++;
		    	  }
		      }
		      }while(nbspaces>0);
		      do {
		      System.out.println("Enter your age:");
		      age=sc.nextInt();
		      sc.nextLine();
		      }while(age<=0);
		      Client c2=new Client(name,age);
		      clientslist.add(c2);
		      System.out.println("Your id will be:"+c2.getId());
		      c2.buyItems();
		     }

		    }while(choice2!=0);
		   }


		  }while(choice1!=0);








		 }

		}
