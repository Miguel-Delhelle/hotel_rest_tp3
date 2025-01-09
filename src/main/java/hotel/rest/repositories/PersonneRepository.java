package hotel.rest.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import hotel.rest.models.Personne;

public interface PersonneRepository extends JpaRepository<Personne, Long> {

}
