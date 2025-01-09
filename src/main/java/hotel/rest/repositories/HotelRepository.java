package hotel.rest.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import hotel.rest.models.Hotel;

public interface HotelRepository extends JpaRepository<Hotel, Long> {

}
