package finalProject2;
import java.io.*;
import java.util.*;
public class Person {
	protected	String name,id;
	protected int age ;
	Random rnd=new Random();
public Person(String name,int age) {
     setName(name);
     setAge(age);
 }
public Person() {
	
}

public void setName(String name) {
	
	int nbspaces=0;
	for(int i=0;i<name.length();i++) {
		if(name.charAt(i)==' ') {
			nbspaces++;
		}
	}
	if(nbspaces==0) {
		this.name=name;
	}
	else this.name="Invalid";
}

public String getName() {
	return this.name;
}


public void setAge(int age) {
	if(age>=18) {
		this.age=age;
	}
	else this.age=18;
}

public int getAge() {
	return this.age;
}

public void GenerateId(String path) {
	int cpy=0;
	
	try {
		BufferedReader reader=new BufferedReader(new FileReader(path));
		String line="";
		String values[];
		cpy=rnd.nextInt(999999-100000+1)+100001;
		while((line = reader.readLine()) != null) {
			values = line.split(",");
           do {
			cpy=rnd.nextInt(999999-100000+1)+100001;
           }while(cpy==Integer.parseInt(values[2].substring(3,8)));
			
		}
		reader.close();
		
	} catch (IOException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
	
	this.id=this.name.substring(0, 1)+""+this.age+""+cpy;
}

public String getId() {
	return this.id;
}

public void setId(String id) {
this.id=id;
}
}