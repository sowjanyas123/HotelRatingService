package com.lcwd.hotel.HotelService.HotelRepositary;

import com.lcwd.hotel.HotelService.HotelEntity.Hotel;
import org.springframework.data.jpa.repository.JpaRepository;

public interface HotelRepo extends JpaRepository<Hotel,String> {
}
