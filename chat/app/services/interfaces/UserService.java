package services.interfaces;


import exceptions.RecordNotFoundException;
import models.store.User;


public interface UserService extends DataService<User> {
    public  User find(String username) throws RecordNotFoundException;
}
