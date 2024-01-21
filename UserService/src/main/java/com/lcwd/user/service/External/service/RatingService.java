package com.lcwd.user.service.External.service;

import com.lcwd.user.service.entity.Rating;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;

@FeignClient(name="RATING-SERVICE")
public interface RatingService {
   @PostMapping("/ratings")
    public ResponseEntity<Rating> createRating(Rating Values);
@PutMapping("/ratings/{ratingId}")
   public ResponseEntity<Rating> updateRating(@PathVariable ("ratingId")String ratingId, Rating rating);

@DeleteMapping("/ratings/{ratingId}")
    public void deleteRating(@PathVariable String ratingId);

}
