package hotel.rest.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import hotel.rest.models.Chambre;

public interface ChambreRepository extends JpaRepository<Chambre, Long> {

}
