package demo.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import demo.bean.Hr;

public interface HrRepo extends JpaRepository<Hr, String> {

}
