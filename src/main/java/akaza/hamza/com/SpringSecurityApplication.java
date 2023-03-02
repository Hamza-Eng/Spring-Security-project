package akaza.hamza.com;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.context.PropertyPlaceholderAutoConfiguration;
import org.springframework.context.annotation.Bean;

import akaza.hamza.com.entity.AppUser;
import akaza.hamza.com.repository.AppRoleRepository;
import akaza.hamza.com.repository.AppUSerRepository;
import akaza.hamza.com.service.AppRoleService;
import akaza.hamza.com.service.AppUserService;

@SpringBootApplication(exclude = PropertyPlaceholderAutoConfiguration.class)
public class SpringSecurityApplication {

	public static void main(String[] args) {
		SpringApplication.run(SpringSecurityApplication.class, args);
	}

	@Bean
	CommandLineRunner start(AppRoleService roleService, AppUserService userService, AppRoleRepository appRoleRepository,
			AppUSerRepository appUSerRepository) {
		return args -> {

			userService.createOrUpdate(AppUser.builder().username("hamza").build());
			userService.createOrUpdate(AppUser.builder().username("salah").build());
			userService.createOrUpdate(AppUser.builder().username("mohamed").build());
			userService.createOrUpdate(AppUser.builder().username("youssef").build());

			roleService.addRole("ADMIN");
			roleService.addRole("USER");
			roleService.addRole("CUSTOMER_MANAGER");
			roleService.addRole("PRODUCT_MANAGER");
			roleService.addRole("BILLS_MANAGER");

			userService.addRoleToUser("ADMIN", "hamza");
			userService.addRoleToUser("USER", "salah");
			userService.addRoleToUser("USER", "mohamed");
			userService.addRoleToUser("PRODUCT_MANAGER", "youssef");
			System.out.println(appRoleRepository.findAll());
			System.out.println(appUSerRepository.findAll());
			System.out.println("hello from hamza");
		};
	}

//	@Override
//	public void run(String... args) throws Exception {
//		roleService.addRole("admin");
//		System.out.println("hello from hamza");
//		
//	}

}
