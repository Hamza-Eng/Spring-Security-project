package akaza.hamza.com.entity;


import java.util.ArrayList;
import java.util.List;

import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.Table;
import lombok.Data;

@Entity
@Table
@Data
public class AppUser {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;
	private String username;
	@ManyToMany(fetch = FetchType.EAGER)
	List<AppRole> list = new ArrayList<>();

	public AppUser(String username) {
		this.username = username;
	}
	public AppUser() {
		// TODO Auto-generated constructor stub
	}

}
