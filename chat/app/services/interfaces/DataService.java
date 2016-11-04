package services.interfaces;


import exceptions.RecordNotFoundException;
import models.store.BaseModel;
import java.util.UUID;


public interface DataService<T extends BaseModel> {
    
    public T  create(T t) throws Exception;
    public T find(UUID id) throws RecordNotFoundException;

}
