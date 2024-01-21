package com.lcwd.hotel.HotelService.HotelService;

import com.lcwd.hotel.HotelService.HotelEntity.Hotel;

import java.util.List;

public interface HotelService {

    Hotel create(Hotel hotel);
    List<Hotel> getAll();
    Hotel getById(String id);

}
