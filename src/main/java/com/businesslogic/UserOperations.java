package com.businesslogic;


import com.dataaccess.EntityDataAccessManager;
import com.exception.DataNotFoundException;
import com.model.UserSummary;
import com.model.entities.UserAddress;
import com.model.entities.UserEntity;
import com.model.enums.UserType;

import javax.inject.Inject;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

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

    //Filter By First Name
    public List<UserEntity> getUserByFirstName(String name){
        List<UserEntity> ue = super.findAll();
        List<UserEntity> userEntitiesFiltered = ue.stream().filter(u -> u.getFirstName().compareTo(name) == 0).collect(Collectors.toList());

        return userEntitiesFiltered;
    }

    //Filter By Last Name
    public List<UserEntity> getUserByLastName(String name){
        List<UserEntity> ue = super.findAll();
        List<UserEntity> userEntitiesFiltered = ue.stream().filter(u -> u.getLastName().compareTo(name) == 0).collect(Collectors.toList());

        return userEntitiesFiltered;
    }

    //Filter By Type
    public List<UserEntity> getUserByType(UserType type){
        List<UserEntity> ue = super.findAll();
        List<UserEntity> userEntitiesFiltered = ue.stream().filter(u -> u.getUserType() == type).collect(Collectors.toList());

        return userEntitiesFiltered;
    }

    /* Finds the User by Id*/
    @Override
    public UserEntity find(Object id) {
        UserEntity ue = super.find(id);

        if(ue == null) throw new DataNotFoundException("Unable to find the User with given id");

        return super.find(id);
    }

    /* Creates the User*/
    @Override
    public void create(UserEntity entity) {
        Set<UserAddress> userAddresses = new HashSet<>();
        if(!entity.getUserAddresses().isEmpty()){
            userAddresses = entity.getUserAddresses();
            entity.setUserAddresses(null);
        }
        super.create(entity);
        if(!userAddresses.isEmpty()){
            for(UserAddress ua:userAddresses){
                ua.setUserEntity(entity);
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

        if(userEntityList == null) throw new DataNotFoundException("User List is not Empty");

        return userEntityList;
    }

}
