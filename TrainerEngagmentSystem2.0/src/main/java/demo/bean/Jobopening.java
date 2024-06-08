package demo.bean;

import java.util.List;
import java.util.Set;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.Table;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Entity
@Table(name = "Job_Opening")
@Getter
@Setter
@NoArgsConstructor
@ToString
public class Jobopening {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	long jobId;
	String companyname;
	String location;
	String position;
	String experience;
	String skills;
	String description;

	@ManyToMany
	@JoinTable
	private List<Hr> hr;

	@ManyToMany(mappedBy = "jo")
	private Set<Trainer> tr;

	@Override
	public String toString() {
		return "Jobopening [jobId=" + jobId + ", companyname=" + companyname + ", location=" + location + ", position="
				+ position + ", experience=" + experience + ", skills=" + skills + ", description=" + description + "]";
	}
	
	
	
}
