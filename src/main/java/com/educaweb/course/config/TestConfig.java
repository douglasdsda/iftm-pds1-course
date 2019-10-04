package com.educaweb.course.config;

import java.time.Instant;
import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import com.educaweb.course.entities.Category;
import com.educaweb.course.entities.Order;
import com.educaweb.course.entities.OrderItem;
import com.educaweb.course.entities.Payment;
import com.educaweb.course.entities.Product;
import com.educaweb.course.entities.Role;
import com.educaweb.course.entities.User;
import com.educaweb.course.entities.enums.OrdersStatus;
import com.educaweb.course.repositories.CategoryRepository;
import com.educaweb.course.repositories.OrderItemRepository;
import com.educaweb.course.repositories.OrderRepository;
import com.educaweb.course.repositories.ProductRepository;
import com.educaweb.course.repositories.RoleRepository;
import com.educaweb.course.repositories.UserRepository;

@Configuration
@Profile("test")
public class TestConfig implements CommandLineRunner {
	
	@Autowired
	private BCryptPasswordEncoder passwordEncode;

	@Autowired
	private UserRepository userRepository;

	@Autowired
	private OrderRepository orderRepository;

	@Autowired
	private CategoryRepository categoryRepository;

	@Autowired
	private ProductRepository productRepository;
	
	@Autowired
	private OrderItemRepository orderItemRepository;
	
	@Autowired
	private RoleRepository roleRepository;

	@Override
	public void run(String... args) throws Exception {

		Category cat1 = new Category(null, "Electronics");
		Category cat2 = new Category(null, "Books");
		Category cat3 = new Category(null, "Coomputers");

		Product p1 = new Product(null, "The Lords of the Rings", "Lorem ispsum doar amet, consectetur.", 90.5, "");
		Product p2 = new Product(null, "Smart tv", "Nulla eu imperdiet purus. maecenas ante.", 2190.0, "");
		Product p3 = new Product(null, "Mac book", "nam eleifed maximum tor.", 1250.5, "");
		Product p4 = new Product(null, "The Lords of the Rings", "Lorem ispsum doar amet, consectetur.", 1200.0, "");
		Product p5 = new Product(null, "Rails ofr dummies.", "cras fringilla.", 100.99, "");
		
		categoryRepository.saveAll(Arrays.asList(cat1, cat2, cat3));
		productRepository.saveAll(Arrays.asList(p1, p2, p3, p4, p5));

		p1.getCategories().add(cat2);
		p2.getCategories().add(cat1);
		p2.getCategories().add(cat3);
		p3.getCategories().add(cat3);
		p4.getCategories().add(cat3);
		p5.getCategories().add(cat2);

		productRepository.saveAll((Arrays.asList(p1, p2, p3, p4, p5)));

		User u1 = new User(null, "Maria Brown", "maria@gmail.com", "988888888", passwordEncode.encode("123456"));
		User u2 = new User(null, "Alex Green", "alex@gmail.com", "977777777", passwordEncode.encode("123456"));

		userRepository.saveAll(Arrays.asList(u1, u2));
		
		Role r1 = new Role(null , "ROLE_CLIENT");
		Role r2 = new Role(null, "ROLE_CLIENT");
		
		roleRepository.saveAll(Arrays.asList(r1, r2));
		
		u1.getRoles().add(r1);
		u2.getRoles().add(r1);
		u2.getRoles().add(r2);
		
		userRepository.saveAll(Arrays.asList(u1, u2));
		

		Order o1 = new Order(null, Instant.parse("2019-06-20T19:53:07Z"), OrdersStatus.PAID, u1);
		Order o2 = new Order(null, Instant.parse("2019-07-21T03:42:10Z"), OrdersStatus.WAITING_PAYMENT, u2);
		Order o3 = new Order(null, Instant.parse("2019-07-22T15:21:22Z"), OrdersStatus.WAITING_PAYMENT, u1);


		orderRepository.saveAll(Arrays.asList(o1, o2, o3));
		
		
		OrderItem oi1 = new OrderItem(o1, p1, 2, p1.getPrice());
		OrderItem oi2 = new OrderItem(o1, p3, 1, p3.getPrice());
		OrderItem oi3 = new OrderItem(o2, p3, 2, p3.getPrice());
		OrderItem oi4 = new OrderItem(o3, p5, 2, p5.getPrice());
		
		orderItemRepository.saveAll(Arrays.asList(oi1,oi2,oi3,oi4));
		
		Payment pay1 = new Payment(null, Instant.parse("2019-06-20T21:53:07Z"), o1);
		o1.setPayment(pay1);
		orderRepository.save(o1);
		
	}

}
