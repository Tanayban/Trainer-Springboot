package demo.bean;

import jakarta.persistence.Entity;
import jakarta.persistence.PrimaryKeyJoinColumn;
import jakarta.persistence.Table;
import lombok.Data;

@Entity
@PrimaryKeyJoinColumn
@Data
@Table(name = "Admin")
public class Admin extends User {

}
