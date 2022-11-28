package classUtilities;

import pageObject.HomePage;
import pageObject.Login;
import pageObject.MyAccount;
import pageObject.Registration;
import pageObject.Shop;

public class Initializer extends BaseClass{
	
	
//	public static Faker faker; 
//	public static FakeValuesService fake;
	public static HomePage hp;
	public static Login lg;
	public static Registration re;
	public static Shop sp;
	public static MyAccount my;
	
	public static void initialize() {
		
		
//		faker = new Faker();
//		fake = new FakeValuesService(new Locale("en-GB"), new RandomService());
		hp = new HomePage();
		lg = new Login();
		re = new Registration();
		sp = new Shop();
		my = new MyAccount();
		
	}

}
