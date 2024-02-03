package dog.topia.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import dog.topia.entity.Dog;

public interface DogDao extends JpaRepository<Dog, Long> {

}
