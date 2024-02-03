package dog.topia.controller.model;

import java.util.HashSet;
import java.util.Set;

import dog.topia.entity.Coach;
import dog.topia.entity.Dog;
import dog.topia.entity.Dogtopia;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@NoArgsConstructor
public class DogtopiaData {
	private Long dogtopia_id;
	private String dogtopia_address;
	private String dogtopia_city;
	private String dogtopia_state;
	private String dogtopia_zip;
	private String dogtopia_phone;
	
	private Set<DogtopiaCoach> coaches = new HashSet<>();
	private Set<DogtopiaDog> dogs = new HashSet<>();
	
	// Constructor that takes PetStore as a parameter
	public DogtopiaData(Dogtopia dogtopia) {
			
		 // Getters and setters
		this.dogtopia_id = dogtopia.getDogtopia_id();
		this.dogtopia_city = dogtopia.getDogtopia_city();
		this.dogtopia_address = dogtopia.getDogtopia_address();
		this.dogtopia_city = dogtopia.getDogtopia_city();
		this.dogtopia_state = dogtopia.getDogtopia_state();
		this.dogtopia_zip = dogtopia.getDogtopia_zip();
		this.dogtopia_phone = dogtopia.getDogtopia_phone();
		
				
			
		for(Coach coach: dogtopia.getCoach()) {
			coaches.add(new DogtopiaCoach(coach));
			
		}
		
		
		for(Dog dog: dogtopia.getDog()) {
			dogs.add(new DogtopiaDog(dog));
		}
		

	}
}
