package akaza.hamza.com.Controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import akaza.hamza.com.repository.AppRoleRepository;
import akaza.hamza.com.repository.AppUSerRepository;
import akaza.hamza.com.service.AppRoleService;
import akaza.hamza.com.service.AppUserService;

@RestController
@CrossOrigin
@RequestMapping("/api")
public class Controller {
	private AppRoleService roleService;
	private AppUserService userService;
	AppRoleRepository roleRepository;
	AppUSerRepository appUSerRepository;

	public Controller(AppRoleService roleService, AppUserService userService, AppRoleRepository roleRepository,
			AppUSerRepository appUSerRepository) {
		this.roleService = roleService;
		this.userService = userService;
		this.roleRepository = roleRepository;
		this.appUSerRepository = appUSerRepository;
	}

	@GetMapping("/addRoleToUser/{role}/{user}")
	private void AddRoleToUser(@PathVariable(value = "role") String role, @PathVariable(value = "user") String user) {

		this.userService.addRoleToUser(role, user);
		System.out.println(role + " " + user);
	}

	@GetMapping("/getUserRoles/{user}")
	@PreAuthorize("hasAuthority('ROLE_ADMIN')")
	private void getUserRole(@PathVariable(value = "user") String user) {
		this.userService.loadUserByUserName(user);
	}

	@GetMapping("/ping")
	private ResponseEntity<String>  ping() {
		return new ResponseEntity<String>("ping pong",HttpStatus.OK);
//		System.out.println("hello from hamza");
	}

	@GetMapping("/user")
	@PreAuthorize("hasRole('USER')")
	private String UserPart() {
		System.out.println("hello from hamza");
		return "ping pong from user section";
	}

	@GetMapping("/admin")
	@PreAuthorize("hasRole('ADMIN')")
	private String AdminPart() {
		System.out.println("hello from hamza");
		return "ping pong from admin section";
	}
}
