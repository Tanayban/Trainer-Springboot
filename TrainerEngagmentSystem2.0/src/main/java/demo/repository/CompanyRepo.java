package demo.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import demo.bean.Company;

public interface CompanyRepo extends JpaRepository<Company, String> {
	Company findByemail(String email);

}
