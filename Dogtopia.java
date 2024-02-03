package dog.topia.entity;
import java.util.HashSet;
import java.util.Set;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.OneToMany;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

@Entity
@Data
public class Dogtopia {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long dogtopia_id;
	private String dogtopia_address;
	private String dogtopia_city;
	private String dogtopia_state;
	private String dogtopia_zip;
	private String dogtopia_phone;
	
	@ManyToMany(cascade = CascadeType.PERSIST)
	@JoinTable(name = "dogtopia_coach", joinColumns = @JoinColumn(name = "dogtopia_id"), 
	inverseJoinColumns = @JoinColumn(name = "coach_id"))
	@EqualsAndHashCode.Exclude
	@ToString.Exclude
	
	private Set<Coach> coach = new HashSet<>();
	
	@OneToMany(mappedBy = "dogtopia", cascade = CascadeType.ALL, orphanRemoval = true)
	@EqualsAndHashCode.Exclude
	@ToString.Exclude
	private Set<Dog> dog = new HashSet<>();

	
}
