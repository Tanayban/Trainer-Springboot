package demo.bean;

import java.util.Set;

import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.PrimaryKeyJoinColumn;
import jakarta.persistence.Table;
import lombok.Data;

@Entity
@PrimaryKeyJoinColumn
@Data
@Table(name = "Hr")
public class Hr extends User {

	String name;
	String experience;
	String education;

	@ManyToOne
	@JoinColumn(name = "company_email", referencedColumnName = "email")
	private Company company;

	@ManyToMany(mappedBy = "hr")
	private Set<Jobopening> jb;
}
