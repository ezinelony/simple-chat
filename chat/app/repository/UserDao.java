package repository;


import com.avaje.ebean.Ebean;
import exceptions.RecordNotFoundException;
import models.store.User;


public class UserDao extends repository.interfaces.User {

    public User findByUsername(String username) throws RecordNotFoundException {
        User user = Ebean.find(User.class)
                .where()
                .eq("username", username)
                .findUnique();
        
        ensureEntityExists(user);
        return user;
    }

    public Class<User> entityClass() {
        return User.class;
    }
}
