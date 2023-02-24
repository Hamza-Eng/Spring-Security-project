package akaza.hamza.com.repository;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import akaza.hamza.com.entity.AppUser;

@Repository
public interface AppUSerRepository extends JpaRepository<AppUser, Long> {
	AppUser findByUsername(String name);
}
