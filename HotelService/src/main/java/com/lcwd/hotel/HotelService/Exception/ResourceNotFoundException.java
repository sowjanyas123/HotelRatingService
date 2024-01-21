package com.lcwd.hotel.HotelService.Exception;

public class ResourceNotFoundException extends  RuntimeException {

    public ResourceNotFoundException(){
        super("Resource Not Fount on Server !!");
    }

    public ResourceNotFoundException(String message){
        super(message);
    }
}


