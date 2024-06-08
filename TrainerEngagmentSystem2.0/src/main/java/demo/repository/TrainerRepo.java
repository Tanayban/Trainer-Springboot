package demo.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import demo.bean.Trainer;

public interface TrainerRepo extends JpaRepository<Trainer, String> {

}
