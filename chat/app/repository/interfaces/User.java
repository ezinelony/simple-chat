package repository.interfaces;


import exceptions.RecordNotFoundException;
import repository.RelationalStore;

public abstract class User extends RelationalStore<models.store.User> {
    
    abstract public models.store.User findByUsername(String username) throws RecordNotFoundException;

}