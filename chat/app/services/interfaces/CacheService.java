package services.interfaces;


public interface CacheService {
    
    public Object get(String key);
    public void set(String key, Object object);
    public void set(String key, Object object, int expiration);
    public void remove(String key);
}
