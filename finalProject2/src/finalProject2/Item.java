package finalProject2;

public class Item {
public String name;
public int quantity,sales;
public double price;

public String cat;

public Item(String name,double price,int quantity,String cat,int sales) {
	setName(name);
	setQuantity(quantity);
	setPrice(price);
	this.sales=sales;
	setCat(cat);
}

public void setName(String name) {
	this.name=name;
}
public void setQuantity(int quantity) {
	if(quantity>0) {
	this.quantity=quantity;
	}
	else this.quantity=0;
}
public void setPrice(double price) {
	if(price>0) {
	this.price=price;
	}
	else this.price=0;
}

public void setSales(int sales) {
	if(sales>0) {
		this.sales=sales;
		}
		else this.sales=0;
}

public void setCat(String cat) {
	this.cat=cat;
}

     public String getName() {
	 return this.name;
    }

    public Double getPrice() {
	return this.price;
    }
    
    public int getQuantity() {
	return this.quantity;
    }
    
    public String getCat() {
    	return this.cat;
    }
    public int getSales() {
    	return this.sales;
    }
public String toString() {
	return name+"---------"+price+"$---------"+quantity+"---------"+cat+"---------"+sales;
}

}
