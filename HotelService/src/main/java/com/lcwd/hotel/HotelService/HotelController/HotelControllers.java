package com.lcwd.hotel.HotelService.HotelController;

import com.lcwd.hotel.HotelService.HotelEntity.Hotel;
import com.lcwd.hotel.HotelService.HotelService.HotelService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/hotel")


public class HotelControllers {
    @Autowired
    private HotelService hotelServ;
@PostMapping ("/")
public ResponseEntity<Hotel> createHotel(@RequestBody Hotel hotel){
    Hotel hotels=hotelServ.create(hotel);
    return new ResponseEntity<Hotel>(hotels, HttpStatus.CREATED);

}
@GetMapping("/{id}")
public  ResponseEntity<Hotel> GetByid(@PathVariable String id){
   Hotel hotel= hotelServ.getById(id);
   return new ResponseEntity<Hotel>(hotel,HttpStatus.OK);
}
    @GetMapping("/All")

public ResponseEntity<List<Hotel>> GetAll(){
   return  ResponseEntity.ok(hotelServ.getAll());

}



}


