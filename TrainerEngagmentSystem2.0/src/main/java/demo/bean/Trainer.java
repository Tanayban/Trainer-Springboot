package demo.bean;

import java.sql.Date;
import java.util.List;
import java.util.Set;

import jakarta.persistence.Entity;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.PrimaryKeyJoinColumn;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Entity
@PrimaryKeyJoinColumn
@Getter
@Setter
@NoArgsConstructor
@ToString
@Table(name = "Trainer")
public class Trainer extends User {

	String name;
	String photo;
	String gender;
	Date birthdate;
	String about;
	String resume;
	String skills;
	String experience;
	String education;
	long phonenumber;
	String location;

	@ManyToMany
	@JoinTable
	private List<Jobopening> jo;
}
