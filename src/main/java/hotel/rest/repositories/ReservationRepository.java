package hotel.rest.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import hotel.rest.models.Reservation;

public interface ReservationRepository extends JpaRepository<Reservation, Long> {

}
