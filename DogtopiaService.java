package dog.topia.service;

import java.util.LinkedList;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Objects;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import dog.topia.controller.model.DogtopiaCoach;
import dog.topia.controller.model.DogtopiaData;
import dog.topia.controller.model.DogtopiaDog;
import dog.topia.dao.CoachDao;
import dog.topia.dao.DogDao;
import dog.topia.dao.DogtopiaDao;
import dog.topia.entity.Coach;
import dog.topia.entity.Dog;
import dog.topia.entity.Dogtopia;


@Service
public class DogtopiaService {
	@Autowired
	private DogtopiaDao dogtopiaDao;
	
	@Autowired
	private DogDao dogDao;
	
	@Autowired
	private CoachDao coachDao;
	
	
	@Transactional(readOnly = false)
	private Dogtopia findDogtopiaById(Long dogtopia_id) {
		
		return dogtopiaDao.findById(dogtopia_id)
				.orElseThrow(() -> new NoSuchElementException(
						"Dogtopia with ID=" + dogtopia_id + " does not exist."));
	}
	
	@Transactional(readOnly = false)
	public DogtopiaData saveDogtopia(DogtopiaData dogtopiaData) {
	
		Long dogtopia_id = dogtopiaData.getDogtopia_id();
		Dogtopia dogtopia = findOrCreateDogtopia(dogtopia_id);
		copyDogtopiafields(dogtopia, dogtopiaData);
		return new DogtopiaData(dogtopiaDao.save(dogtopia));
	}
	
	@Transactional(readOnly = true)
	public DogtopiaData retrieveDogtopiaById(Long dogtopia_id) {
		Dogtopia dogtopia = findDogtopiaById(dogtopia_id);
		
		return new DogtopiaData(dogtopia);
	}
	
	@Transactional(readOnly = false)
	public Dogtopia findOrCreateDogtopia(Long dogtopia_id) {
		
		Dogtopia dogtopia;
		if (Objects.isNull(dogtopia_id)) {
			dogtopia = new Dogtopia();
		} else {
			dogtopia = findDogtopiaById(dogtopia_id);
		}
		return dogtopia;
		
	}
	
	@Transactional(readOnly = false)
	private void copyDogtopiafields(Dogtopia dogtopia, DogtopiaData dogtopiaData) {
		dogtopia.setDogtopia_id(dogtopiaData.getDogtopia_id());
		dogtopia.setDogtopia_address(dogtopiaData.getDogtopia_address());
		dogtopia.setDogtopia_city(dogtopiaData.getDogtopia_city());
		dogtopia.setDogtopia_state(dogtopiaData.getDogtopia_state());
		dogtopia.setDogtopia_zip(dogtopiaData.getDogtopia_zip());
		dogtopia.setDogtopia_phone(dogtopiaData.getDogtopia_phone());
	}
	
	public Dog findDogById(Long dogtopia_id, Long dog_id) {
		
		Dog dog = dogDao.findById(dog_id)
				.orElseThrow(() -> new NoSuchElementException(
						"Dog with ID=" + dog_id + " does not exist."));
		
		if (dog.getDogtopia().getDogtopia_id() != dogtopia_id) {
			throw new IllegalArgumentException("Dog with ID=" + dog_id + 
					" is not employed at store with ID=" + dogtopia_id);
		}
		
		return dog;
	}
	
	public Dog findOrCreateDog(Long dogtopia_id, Long dog_id) {
		
		Dog dog;
		
		if (Objects.isNull(dog_id)) {
			dog = new Dog();
		} else {
			dog = findDogById(dogtopia_id, dog_id);
		}
		
		return dog;
	}
	
	private void copyDogFields(Dog dog, DogtopiaDog dogtopiaDog) {
		dog.setDog_id(dogtopiaDog.getDog_id());
		dog.setDog_name(dogtopiaDog.getDog_name());
		dog.setDog_breed(dogtopiaDog.getDog_breed());		
	}
	
	public DogtopiaDog saveDog(Long dogtopia_id, DogtopiaDog dogtopiaDog) {
		
		Dogtopia dogtopia = findDogtopiaById(dogtopia_id);
		
		Long dog_id = dogtopiaDog.getDog_id();
		Dog dog = findOrCreateDog(dogtopia_id, dog_id);
		
		copyDogFields(dog, dogtopiaDog);
		dog.setDogtopia(dogtopia);
		
		// add dog to dogtopia
		dogtopia.getDog().add(dog);
		dog.setDogtopia(dogtopia);	
		return new DogtopiaDog(dogDao.save(dog));
	}
	
	@Transactional(readOnly = true)
	public List<DogtopiaData> getAllDogtopias() {
		List<Dogtopia> dogtopias = dogtopiaDao.findAll();
		List<DogtopiaData> dogtopiaDataList = new LinkedList<>();
		
		for(Dogtopia dogtopia : dogtopias) {
			DogtopiaData ddd = new DogtopiaData(dogtopia);
			
			
									
			dogtopiaDataList.add(ddd);
		}
		
		return dogtopiaDataList;
	}
	
	@Transactional(readOnly = false)
	public DogtopiaCoach saveCoach(Long dogtopia_id, DogtopiaCoach dogtopiaCoach) {

	Dogtopia dogtopia = findDogtopiaById(dogtopia_id);
		
		Long coach_id = dogtopiaCoach.getCoach_id();
		Coach coach = findOrCreateCoaches(dogtopia_id, coach_id);
		
		copyCoachFields(coach, dogtopiaCoach);
		coach.getDogtopia().add(dogtopia);
		
	
		dogtopia.getCoach().add(coach);
		
		return new DogtopiaCoach(coachDao.save(coach));
	}

	private void copyCoachFields(Coach coach, DogtopiaCoach dogtopiaCoach) {
		coach.setCoach_id(dogtopiaCoach.getCoach_id());		
		coach.setCoach_first_name(dogtopiaCoach.getCoach_first_name());		
		coach.setCoach_last_name(dogtopiaCoach.getCoach_last_name());
		coach.setCoach_email(dogtopiaCoach.getCoach_email());
		
	}

	private Coach findOrCreateCoaches(Long dogtopia_id, Long coach_id) {
		
		Coach coach;
		if (Objects.isNull(coach_id)) {
			coach = new Coach();
		}else {
			coach = findCoachById(dogtopia_id, coach_id);
		}
		return coach;
	}

	private Coach findCoachById(Long dogtopia_id, Long coach_id) {
		
		Coach coach = coachDao.findById(coach_id)
				.orElseThrow(() -> new NoSuchElementException(
						"Coach with ID=" + coach_id + " does not exist."));
		
		return coach;
	}
	
	@Transactional(readOnly = false)
	public void deleteDogtopiaById(Long dogtopia_id) {
		Dogtopia dogtopia = findDogtopiaById(dogtopia_id);
		dogtopiaDao.delete(dogtopia);
	}
	
}
