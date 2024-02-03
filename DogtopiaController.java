package dog.topia.controller.model;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import dog.topia.service.DogtopiaService;
import lombok.extern.slf4j.Slf4j;



@RestController
@RequestMapping("/dogtopia")
@Slf4j
public class DogtopiaController {
	@Autowired
	private DogtopiaService dogtopiaService;

	@PutMapping("/{dogtopia_id}")
	public DogtopiaData updateDogtopia(@PathVariable Long dogtopia_id, @RequestBody
			DogtopiaData dogtopiaData) {
		log.info("Received a PUT request to /dogtopia/{} with data: {}", dogtopia_id, 
		dogtopiaData);
		
		
		dogtopiaData.setDogtopia_id(dogtopia_id);
		
		// Call the saveDogtopia method in the service class
		DogtopiaData updatedDogtopiaData = dogtopiaService.saveDogtopia(dogtopiaData);
		
		return updatedDogtopiaData;
	}
	
	@PostMapping
	public DogtopiaData createDogtopia(@RequestBody DogtopiaData dogtopiaData) {
		log.info("Received a POST request to /dogtopia with data: {}", dogtopiaData);
		
		// Call the service method to save or modify dogtopia data
		DogtopiaData savedDogtopiaData = dogtopiaService.saveDogtopia(dogtopiaData);
		
		
		return savedDogtopiaData;
		}
	
	@PostMapping("/{dogtopia_id}/dog")
	@ResponseStatus(HttpStatus.CREATED)
	public ResponseEntity<DogtopiaDog> addDogToDogtopia(
			@PathVariable Long dogtopia_id,
			@RequestBody DogtopiaDog dog) {
		log.info("Received a POST request to /dogtopia/{}/dog with data: {}",
				dogtopia_id, dog);
		
		// Call the saveDog method in the dogtopia service and return the results
		DogtopiaDog savedDog = dogtopiaService.saveDog(dogtopia_id, dog);
		
		// Return the saved dog along with HTTP status 201 (Created)
		return ResponseEntity.status(HttpStatus.CREATED).body(savedDog);
		}
	
	@PostMapping("/{dogtopia_id}/coach")
	@ResponseStatus(HttpStatus.CREATED)
	public DogtopiaCoach addCoach(
			@PathVariable Long dogtopia_id,
			@RequestBody DogtopiaCoach coach) {
		log.info("Received a POST request to /dogtopia/{}/coach with data: {}",
				dogtopia_id, coach);
		
		return dogtopiaService.saveCoach(dogtopia_id, coach);
	}
	
	@GetMapping("/dogtopias")
	public List<DogtopiaData> retrieveAllDogtopias(){
		
		return dogtopiaService.getAllDogtopias();
	}
	
	@GetMapping("/{dogtopia_id}")
	public DogtopiaData retrieveDogtopia(@PathVariable Long dogtopia_id) {
		
		return dogtopiaService.retrieveDogtopiaById(dogtopia_id);
	}
	@DeleteMapping("/{dogtopia_id}")
	public Map<String, String> deleteDogtopiaById(@PathVariable Long dogtopia_id)
	{
		// Log the request
		System.out.println("Deleting Dogtopia with ID " + dogtopia_id);
		
		// Call the service method to delete dogtopia
		dogtopiaService.deleteDogtopiaById(dogtopia_id);
		
		// Return a deletion successful message
		Map<String, String> response = new HashMap<>();
		response.put("message", "Dogtopia with ID " + dogtopia_id + " successfully deleted.");
		return response;
	
	}
	
}
