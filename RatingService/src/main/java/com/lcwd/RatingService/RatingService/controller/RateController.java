package com.lcwd.RatingService.RatingService.controller;

import com.lcwd.RatingService.RatingService.Entity.Rating;
import com.lcwd.RatingService.RatingService.Service.RatingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/ratings")
public class RateController {

    @Autowired
    private RatingService rateServ;
@PostMapping
    public ResponseEntity<Rating> createRate(@RequestBody Rating rating){
        return ResponseEntity.status(HttpStatus.CREATED).body(rateServ.createRate(rating));

    }
@GetMapping
    public ResponseEntity<List<Rating>> getAll(){
        return ResponseEntity.ok(rateServ.getAll());
    }

    @GetMapping("/User/{userId}")
    public ResponseEntity<List<Rating>> getRateByUserId(@PathVariable String userId){
        return ResponseEntity.ok(rateServ.getRateByUserId(userId));
    }
    @GetMapping("/Hotel/{hotelId}")
    public ResponseEntity<List<Rating>> getRateByHotelId(@PathVariable String hotelId){
        return ResponseEntity.ok(rateServ.getRateByHotelId(hotelId));
    }

}
