package dog.topia.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import dog.topia.entity.Dogtopia;

public interface DogtopiaDao extends JpaRepository<Dogtopia, Long> {

}
