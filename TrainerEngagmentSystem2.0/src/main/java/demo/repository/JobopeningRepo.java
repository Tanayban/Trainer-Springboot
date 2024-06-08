package demo.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import demo.bean.Jobopening;

public interface JobopeningRepo extends JpaRepository<Jobopening, Long> {

	List<Jobopening> findByHr_Email(String email);
	
//	List<Jobopening> findByTrainer_Email(String email);
	
}
