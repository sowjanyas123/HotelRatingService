package com.lcwd.RatingService.RatingService.ServiceImpl;

import com.lcwd.RatingService.RatingService.Entity.Rating;
import com.lcwd.RatingService.RatingService.Repos.RatingRepo;
import com.lcwd.RatingService.RatingService.Service.RatingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class RatingServiceImpl  implements RatingService {
    @Autowired
    private RatingRepo rateRep;
    @Override
    public Rating createRate(Rating rating) {
        String ratingId= UUID.randomUUID().toString();
        rating.setRatingId(ratingId);
       return rateRep.save(rating);
    }

    @Override
    public List<Rating> getAll() {
return rateRep.findAll();    }

    @Override
    public List<Rating> getRateByUserId(String userId) {
        return rateRep.findByUserId(userId);
    }

    @Override
    public List<Rating> getRateByHotelId(String hotelId) {
        return rateRep.findByHotelId(hotelId);
    }
}
