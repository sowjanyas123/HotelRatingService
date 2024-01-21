package com.lcwd.user.service.ServiceImpl;

import com.lcwd.user.service.External.service.HotelService;
import com.lcwd.user.service.Repositary.UserRepositary;
import com.lcwd.user.service.Services.UserService;
import com.lcwd.user.service.entity.Hotel;
import com.lcwd.user.service.entity.Rating;
import com.lcwd.user.service.entity.User;
import com.lcwd.user.service.exception.ResourceNotFoundException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
public class UserServiceImpl implements UserService {
    @Autowired
    private UserRepositary userRepo;
    @Autowired
    private RestTemplate restTemplate;
    @Autowired
            private HotelService hotelService;
    Logger logger= LoggerFactory.getLogger(UserServiceImpl.class);
    @Override
    public User saveUser(User user) {

        String randomUserId=UUID.randomUUID().toString();
        user.setUserId(randomUserId);
        return userRepo.save(user);
    }

    @Override
    public List<User> getAllUser() {
        return userRepo.findAll();
    }

    @Override
    public User getUserById(String userId) {
        //get user from database with the help of repo
        User userGet=userRepo.findById(userId).orElseThrow(()->new ResourceNotFoundException("user with give id is not found  on server!!! :"+ userId));
    //fetch rating of above  user from  RATING SERVICE
//http://localhost:8083/ratings/User/2a56eead-710f-4914-89c7-3de93aaea238
        Rating[] ratingOfUser=restTemplate.getForObject("http://RATING-SERVICE/ratings/User/"+ userGet.getUserId(), Rating[].class);
      logger.info("{}",
              ratingOfUser);
      List<Rating>  ratings= Arrays.stream(ratingOfUser).toList();
List<Rating> ratingsList=ratings.stream().map(rating->{
//api call to hotel service to get hotel
    //http://localhost:8082/hotel/c64fe1fa-2ce3-4d45-a7e4-2ad52faa057c
//    ResponseEntity<Hotel> forEntity=restTemplate.getForEntity("http://HOTEL-SERVICE/hotel/"+rating.getHotelId(), Hotel.class);
Hotel hotel=hotelService.getHotel(rating.getHotelId());
  //  Hotel hotel=forEntity.getBody();
//logger.info("response status code : {}",forEntity.getStatusCode());
rating.setHotel(hotel);
    //set the hotel to rating
    //return the rating
    return  rating;
}).collect(Collectors.toList());
userGet.setRatings(ratings  );

        return userGet;
    }

    @Override
    public void DeleteUserById(String userId) {


    userRepo.deleteById(userId);
    }


    @Override
    public User updateUser(User user, String UserId) {
        String randomUserId=UUID.randomUUID().toString();
        user.setUserId(randomUserId);
        User userUpdate=userRepo.findById(UserId).orElseThrow(()->new ResourceNotFoundException("user with give id is not found  on server!!! :"+ UserId));
   userUpdate.setName(user.getName());
  userUpdate.setAbout(user.getAbout());
   userUpdate.setEmail(user.getEmail());


  return userRepo.save(user);
    }
}
