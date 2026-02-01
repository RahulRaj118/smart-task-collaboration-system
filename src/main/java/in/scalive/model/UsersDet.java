package in.scalive.model;

import java.util.List;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.validation.constraints.Email;

@Entity
public class UsersDet {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	private String name;
	@Column(unique =  true)
	@Email
	private String email;
	
	private String password;
	
	private String role="USER";
	
	
	@OneToMany(mappedBy = "user")
	private List<TaskAssignment> assignments;
	
	public UsersDet() {
		
	}

	public UsersDet(String name, @Email String email, String role) {
	
		this.name = name;
		this.email = email;
		this.role = role;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getRole() {
		return role;
	}

	public void setRole(String role) {
	
		this.role = role.toUpperCase();
	}

	
	
	
	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}
	
	
	
	

	public List<TaskAssignment> getAssignments() {
		return assignments;
	}

	public void setAssignments(List<TaskAssignment> assignments) {
		this.assignments = assignments;
	}

	@Override
	public String toString() {
		return "UsersDet [id=" + id + ", name=" + name + ", email=" + email + ", role=" + role + "]";
	}
	
	
	
	
	

}
