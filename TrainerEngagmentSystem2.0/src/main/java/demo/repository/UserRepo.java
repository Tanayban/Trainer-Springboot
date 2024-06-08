package demo.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import demo.bean.Jobopening;
import demo.bean.User;

@Repository
public interface UserRepo extends JpaRepository<User, String> {

	public User findByEmailAndPassword(String email, String password);

	public static Optional<Jobopening> findByEmail(String email) {
		// TODO Auto-generated method stub
		return null;
	}
}
