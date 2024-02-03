package dog.topia.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import dog.topia.entity.Coach;

public interface CoachDao extends JpaRepository<Coach, Long> {

}
