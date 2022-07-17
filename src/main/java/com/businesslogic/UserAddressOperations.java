package com.businesslogic;

import com.dataaccess.EntityDataAccessManager;
import com.exception.DataNotFoundException;
import com.model.entities.UserAddress;


public class UserAddressOperations extends EntityDataAccessManager<UserAddress> {
    public UserAddressOperations(){
        super(UserAddress.class);
    }

    @Override
    public void create(UserAddress entity){
        super.create(entity);
    }

    @Override
    public UserAddress find(Object id){
        UserAddress ua = super.find(id);

        if(ua == null) throw new DataNotFoundException("Unable to find the UserAddress with given id");

        return ua;
    }
}
