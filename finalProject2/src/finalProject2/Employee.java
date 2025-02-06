package finalProject2;
import java.util.*;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;

public class Employee extends Person {
 ArrayList<Employee> employeelist=Admin.employeelist;
 Scanner sc=new Scanner(System.in);
private String name,position;
private double salary;
private double received;
	public Employee(String name,int age,String position,double salary,double received) {
		super(name,age);
		
		setPosition(position);
		setSalary(salary);
		setReceived(received);
	}
	public Employee() {
		
	}
	public void GenerateId(String path) {
		path="C:\\Users\\user123\\Desktop\\Employee.csv";
		super.GenerateId(path);
	}
	public String getId() {
		return super.getId();
	}
	
	public void setPosition(String position) {
		if(position.equalsIgnoreCase("Pharmacist")||position.equalsIgnoreCase("Cashier")) {
			this.position=position;
		}
		else this.position="Invalid position";
	}
	
	public String getPosition() {
		return position;
	}
	
	public void setSalary(double Salary) {
		if(Salary>0) {
			this.salary=Salary;
		}
		else this.salary=0;
	}
	
	public Double getSalary() {
		return this.salary;
	}
	
	public void setReceived(double received) {
		this.received=received;
	}
	
	public  double getReceived() {
		return received;
	}
	
	public void setName(String name) {
		super.setName(name);
	}
	
	public String getName() {
		return super.getName();
	}
	
	public void setId(String id) {
		super.setId(id);
		}
	public String toString() {
		
		return getName()+"---------"+age+"y.o---------"+getId()+"---------"+position+"---------"+getSalary()+"$---------"+received;
	}
	
	
	
	
	public void receiveSalary(Employee e1) {
		
	if(e1.getReceived()==1) {
		System.out.println("his salary of:"+e1.getSalary()+"$ has been given by the admin");
	}
	else System.out.println("his salary of:"+e1.getSalary()+"$ has Not been given by the admin");
	
	}
	
	
}
