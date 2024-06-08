package demo.bean;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Inheritance;
import jakarta.persistence.InheritanceType;
import jakarta.persistence.Table;
import lombok.Data;

@Entity
@Table(name = "User_info")
@Data
@Inheritance(strategy = InheritanceType.JOINED)
public class User {

	@Id
	private String email;
	private String password;

}
