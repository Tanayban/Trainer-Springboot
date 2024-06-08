package demo.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import demo.bean.Admin;

public interface AdminRepo extends JpaRepository<Admin, String> {

}
