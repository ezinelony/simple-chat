package services;


import play.cache.CacheApi;
import services.interfaces.CacheService;
import javax.inject.Inject;
import javax.inject.Singleton;


@Singleton
public class StandardCacheService implements CacheService {
    
    @Singleton
    private final CacheApi cacheApi;

    @Inject
    public StandardCacheService(CacheApi cacheApi) {
        this.cacheApi = cacheApi;
    }

    @Override
    public Object get(String key) {
        return cacheApi.get(key);
    }

    @Override
    public void set(String key, Object object) {
        cacheApi.set(key, object);
    }

    @Override
    public void set(String key, Object object, int expiration) {
        cacheApi.set(key, object, expiration);
    }
    
    @Override
    public void remove(String key) {
        cacheApi.remove(key);
    }
}
