package com.businesslogic;


import com.dataaccess.EntityDataAccessManager;
import com.exception.DataNotFoundException;
import com.model.UserSummary;
import com.model.entities.UserAddress;
import com.model.entities.UserEntity;
import com.model.enums.UserType;


import java.util.*;


public class UserOperations extends EntityDataAccessManager<UserEntity> {
    private  UserAddressOperations userAddressOps;
    public UserOperations(){
        super(UserEntity.class);
        userAddressOps = new UserAddressOperations();
    }


    //Results can be Cached to reduce the Database hits
    public List<UserSummary> getUserSummary(){
        List<UserEntity> userEntities = super.findAll();
        List<UserSummary> userSummaries = new ArrayList<>();

        for(UserEntity ue:userEntities){
            UserSummary userSummary = new UserSummary();

            userSummary.setDateOfBirth(ue.getDateOfBirth());
            userSummary.setFirstName(ue.getFirstName());
            userSummary.setLastName(ue.getLastName());
            userSummary.setId(ue.getId());

            userSummaries.add(userSummary);
        }

        if(userSummaries.isEmpty()) throw new DataNotFoundException("No Users in the Organization");

        return userSummaries;
    }

    /* Finds the User by Id*/
    @Override
    public UserEntity find(Object id) {
        UserEntity ue = super.find(id);

        if(ue == null) throw new DataNotFoundException("Unable to find the User with given id");

        return super.find(id);
    }

    /* Creates the User*/
    /*
        If the User Address is Passed
        Store them and create address independently
        Since Unique ID will be created after the user created in DB
    */

    @Override
    public void create(UserEntity entity) {
        Set<UserAddress> userAddresses = new HashSet<>();
        if(!Objects.isNull(entity.getUserAddresses()) && !entity.getUserAddresses().isEmpty()){
            userAddresses = entity.getUserAddresses();
            entity.setUserAddresses(null);
        }
        super.create(entity);
        if(!userAddresses.isEmpty()){
            for(UserAddress ua:userAddresses){
                ua.setUserId(entity.getId());
                userAddressOps.create(ua);
            }
        }
    }

    /* Update the user information*/
    @Override
    public void update(UserEntity entity){
        super.update(entity);
    }

    /* Removes the User by Id*/
    public void removeUserById(int id){
        UserEntity ue = super.find(id);
        if(ue == null) throw new DataNotFoundException("User does not exist with given id");
        super.remove(ue);
    }

    /* Get all the Users */
    @Override
    public List<UserEntity> findAll(){
        List<UserEntity> userEntityList = super.findAll();

        if(userEntityList == null) throw new DataNotFoundException("User List is Empty");

        return userEntityList;
    }

    /*
        Use HashMap to send column names and their values
     */
    public List<UserEntity> getUsersByFilter(Optional<String> firstName,Optional<String> lastName, Optional<UserType> userType){
        Map<String,Object> map = new HashMap<>();

        if (firstName.isPresent() && (firstName.get().length() > 0)) {
            map.put("firstName", firstName.get());
        }

        if (lastName.isPresent() && (lastName.get().length() > 0)) {
            map.put("lastName", lastName.get());
        }

        if (userType.isPresent()) {
            map.put("userType", userType.get());
        }

        List<UserEntity> userEntityList = super.filter(map);

        if(userEntityList == null) throw new DataNotFoundException("User List is Empty");

        return userEntityList;
    }
}
