package akaza.hamza.com.service;

import java.util.List;

import org.springframework.stereotype.Service;

import akaza.hamza.com.entity.AppRole;
import akaza.hamza.com.repository.AppRoleRepository;

@Service
public class AppRoleService {
	private AppRoleRepository repo;

	public AppRoleService(AppRoleRepository repo) {
		this.repo = repo;
	}

	public void addRole(String name) {
		AppRole role=new AppRole(name);
		this.repo.save(role);
		
	}

	public void deleteRole(long id) {

		this.repo.deleteById(id);

	}

	public AppRole findbyName(String name) {
		return this.repo.findByName(name);
	}

	public List<AppRole> getAll() {
		return this.repo.findAll();
	}

	public void getbyId(long id) {
		this.repo.deleteById(id);
	}

}
