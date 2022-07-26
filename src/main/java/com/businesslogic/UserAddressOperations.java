package com.businesslogic;

import com.dataaccess.EntityDataAccessManager;
import com.exception.DataNotFoundException;
import com.model.entities.UserAddress;

import java.util.List;


public class UserAddressOperations extends EntityDataAccessManager<UserAddress> {
    public UserAddressOperations(){
        super(UserAddress.class);
    }

    @Override
    public void create(UserAddress entity){
        super.create(entity);
    }

    //Gets the id of the user and filters the records with userId
    //Fetches the Corresponding addresses
    public List<UserAddress> findByUser(int id){
        List<UserAddress> ua = super.findBy("userId",id);

        if(ua == null || ua.isEmpty()) throw new DataNotFoundException("Unable to find the UserAddress with given id");

        return ua;
    }
}
