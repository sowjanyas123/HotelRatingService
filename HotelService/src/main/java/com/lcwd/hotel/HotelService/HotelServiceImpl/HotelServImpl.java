package com.lcwd.hotel.HotelService.HotelServiceImpl;


import com.lcwd.hotel.HotelService.Exception.ResourceNotFoundException;
import com.lcwd.hotel.HotelService.HotelEntity.Hotel;
import com.lcwd.hotel.HotelService.HotelRepositary.HotelRepo;
import com.lcwd.hotel.HotelService.HotelService.HotelService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class HotelServImpl implements HotelService {

    @Autowired
    private HotelRepo hotelRep;
    @Override
    public Hotel create(Hotel hotel) {
       String HotelId= UUID.randomUUID().toString();
       hotel.setId(HotelId);
        return hotelRep.save(hotel);
    }

    @Override
    public List<Hotel> getAll() {
       return hotelRep.findAll();
    }

    @Override
    public Hotel getById(String id) {
      return   hotelRep.findById(id).orElseThrow(()->new ResourceNotFoundException("Hotel  with given id is not found !!!"+id));
    }
}
