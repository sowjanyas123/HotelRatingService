package com.lcwd.RatingService.RatingService.Service;

import com.lcwd.RatingService.RatingService.Entity.Rating;

import java.util.List;

public interface RatingService {

    Rating createRate(Rating rating);
    List<Rating> getAll();
    List<Rating> getRateByUserId(String userId);
    List<Rating> getRateByHotelId(String hotelId);

}
