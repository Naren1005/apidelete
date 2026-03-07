package payload;

import java.util.Random;

import com.github.javafaker.Faker;

import pojo.productpojo;

public class payload {
	private static final Faker faker=new Faker();
	private static final String categories[]= {"electroincs","furniture","cloting","books","beauty"}; 
	private static final Random random=new Random();
	//product
	public static productpojo productPaylod()
	{
		String name=faker.commerce().productName();
		double price= Double.parseDouble(faker.commerce().price());
		String description=faker.lorem().sentence();
		String category=categories[random.nextInt(categories.length)];
		String image="https://i.pravatar.cc/100";
		
		return new productpojo(name,description,category,image,price);
		//added a coomment
		
	}
	
	//user
	
	//cart
	
	//login
	

}
