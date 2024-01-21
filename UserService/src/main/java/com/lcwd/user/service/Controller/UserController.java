package com.lcwd.user.service.Controller;

import com.lcwd.user.service.Services.UserService;
import com.lcwd.user.service.entity.User;
import com.lcwd.user.service.payload.ApiResponse;
import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import io.github.resilience4j.ratelimiter.annotation.RateLimiter;
import io.github.resilience4j.retry.annotation.Retry;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/users")
public class UserController {

    @Autowired
    private UserService userServ;

 Logger logger =LoggerFactory.getLogger(UserController.class);
    @PostMapping

    //create
    public ResponseEntity<User> createUser(@RequestBody User user) {
User user1=userServ.saveUser(user);
return ResponseEntity.status(HttpStatus.CREATED).body(user1);


    }
    int retryCount=1;
    @GetMapping("/{userId}")
//    @CircuitBreaker(name="ratingHotelBreaker",fallbackMethod = "ratingHotelFallback")
//    @Retry(name="ratingHotelService",fallbackMethod = "ratingHotelFallback")
    @RateLimiter(name="userRateLimiter",fallbackMethod = "ratingHotelFallback")
    public ResponseEntity<User> getUserByiD( @PathVariable String  userId) {
        logger.info("Get Single User Handler :UserController");
        logger.info("Retry count : {}",retryCount);
        retryCount++;
      User user=  userServ.getUserById(userId);
        return ResponseEntity.ok(user);
}
//creating fall back method for circuit breaker


    public ResponseEntity<User> ratingHotelFallback(String userId,Exception ex){
//    logger.info("{Fallback is executed because service is down :",ex.getMessage());

  User user=  User.builder()
            .email("dummy@gmail.com")
            .Name("Dummy")
            .about("This user is created dummy because some service is down").UserId("12345").build();
    return new ResponseEntity<>(user,HttpStatus.OK);
    }

@GetMapping("/getAll")
    public  ResponseEntity<List<User>> GetUsers(){

        return ResponseEntity.ok(userServ.getAllUser());
}

@PutMapping("/{userId}")
    public ResponseEntity<User> UpdateUser(@RequestBody User user,@PathVariable String  userId){

    User Updates=userServ.updateUser(user,userId);
    return ResponseEntity.ok(Updates);

}

@DeleteMapping("/{UserId}")
    public  ResponseEntity<ApiResponse> DeleteUser(@PathVariable String UserId){
    userServ.DeleteUserById(UserId);
    return  ResponseEntity.ok(new ApiResponse("user deleted successfully",true));

}



}



