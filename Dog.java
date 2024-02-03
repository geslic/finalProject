package dog.topia.entity;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

@Entity
@Data
public class Dog {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long dog_id;
	private String dog_name;
	private String dog_breed;
	
	@ManyToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "dogtopia_id")
	@EqualsAndHashCode.Exclude
	@ToString.Exclude
	private Dogtopia dogtopia = new Dogtopia();
}
