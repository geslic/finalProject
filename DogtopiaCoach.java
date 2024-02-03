package dog.topia.controller.model;

import dog.topia.entity.Coach;
import lombok.Data;
import lombok.NoArgsConstructor;




@Data
@NoArgsConstructor
public class DogtopiaCoach {
	private Long coach_id;
	private String coach_first_name;
	private String coach_last_name;
	private String coach_email;
	
	// Constructor that takes a Customer object
	public DogtopiaCoach(Coach coach) {
		coach_id = coach.getCoach_id();
		coach_first_name = coach.getCoach_first_name();
		coach_last_name = coach.getCoach_last_name();
		coach_email = coach.getCoach_email();
	}
	
}
