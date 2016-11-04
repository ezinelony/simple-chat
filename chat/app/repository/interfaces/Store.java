package repository.interfaces;


import exceptions.RecordNotFoundException;
import models.store.BaseModel;

import java.util.UUID;

public interface Store<T extends BaseModel> {
    
    public T create(T model) throws Exception;
    public T find(UUID id) throws RecordNotFoundException;

}
