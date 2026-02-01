package in.scalive.repo;

import org.springframework.data.jpa.repository.JpaRepository;

import in.scalive.model.UsersDet;
import java.util.List;
import java.util.Optional;


public interface UsersDetailsRepository extends JpaRepository<UsersDet, Integer> {

	 Optional<UsersDet> findByEmail(String email);
	
}

