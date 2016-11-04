package services;


import exceptions.RecordNotFoundException;
import models.store.User;
import services.interfaces.CacheService;
import services.interfaces.UserService;
import javax.inject.Inject;
import java.util.UUID;


public class StandardUserService implements UserService {
    
    private repository.interfaces.User userStore;
    private CacheService cacheService;
    private static String cacheKey = "_user_";
    
    @Inject
    public StandardUserService(repository.interfaces.User userStore, CacheService cacheService) {
        this.userStore = userStore;
        this.cacheService = cacheService;
    }
    
    @Override
    public User create(User user) throws Exception {
        User u =  userStore.create(user);
        cacheUser(u);
        return u;
    }
    
    @Override
    public User find(UUID id) throws RecordNotFoundException {
        if(cacheService.get(cacheKey+id) == null){
            User u = userStore.find(id);
            cacheUser(u);
            return u;
        } else {
            return (User)cacheService.get(cacheKey+id);
        }
    }

    @Override
    public User find(String username) throws RecordNotFoundException {
        if(cacheService.get(cacheKey+username) == null){
            User u = userStore.findByUsername(username);
            cacheUser(u);
            return u;
        } else {
            return (User)cacheService.get(cacheKey+username);
        }
    }
    
    private void cacheUser(User u) {
        cacheService.set(cacheKey+u.getId(), u);
        cacheService.set(cacheKey+u.getUsername(), u);
    }
}
