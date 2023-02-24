package akaza.hamza.com.service;

import java.util.List;

import org.springframework.stereotype.Service;

import akaza.hamza.com.entity.AppRole;
import akaza.hamza.com.entity.AppUser;
import akaza.hamza.com.repository.AppRoleRepository;
import akaza.hamza.com.repository.AppUSerRepository;

@Service
public class AppUserService {
	private AppUSerRepository repo;
	private AppRoleRepository RoleRepo;

	public AppUserService(AppUSerRepository repo, AppRoleRepository roleRepo) {
		this.repo = repo;
		this.RoleRepo = roleRepo;
	}

	public void createOrUpdate(AppUser user) {
		this.repo.save(user);
	}

	public void addRoleToUser(String Role, String username) {

		AppUser user = repo.findByUsername(username);
		AppRole appRole = RoleRepo.findByName(Role);
		user.getList().add(appRole);
		repo.save(user);

	}

	public void findByName(String name) {
		this.repo.findByUsername(name);

	}

	public AppUser loadUserByUserName(String name) {
		return this.repo.findByUsername(name);

	}

	public List<AppUser> listUsers() {
		return this.repo.findAll();
	}

}
