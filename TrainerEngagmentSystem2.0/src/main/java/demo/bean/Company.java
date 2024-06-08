package demo.bean;

import java.util.List;
import java.util.Set;

import jakarta.persistence.Entity;
import jakarta.persistence.OneToMany;
import jakarta.persistence.PrimaryKeyJoinColumn;
import jakarta.persistence.Table;
import lombok.Data;

@Entity
@PrimaryKeyJoinColumn
@Data
@Table(name = "Company")
public class Company extends User {

	String companyName;
	String ownerName;
	String address;
	String state;
	String country;
	long contactNo;
	String description;
	String companylocation;

	@OneToMany(mappedBy = "company")
	private Set<Hr> hr;
}
