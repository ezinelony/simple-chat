package repository;


import com.avaje.ebean.Ebean;
import exceptions.RecordConflictException;
import exceptions.RecordNotFoundException;
import models.store.BaseModel;
import repository.interfaces.Store;
import java.util.UUID;


public abstract class RelationalStore<T extends BaseModel> implements Store<T> {

    @Override
    public T create (T t) throws Exception {

        try {
            Ebean.save(t);
            return find(t.getId());
        } catch(Throwable pe){
            if(pe.getMessage().contains("violation")) {
                throw new RecordConflictException();
            } else {
                throw new Exception();
            }
        }
    }

    
    @Override
    public T find(UUID id) throws RecordNotFoundException {
        T e = Ebean.find(entityClass())
                .where()
                .eq("id", id)
                .findUnique();
        ensureEntityExists(e);

        return e;
        
    }

    abstract public Class<T> entityClass();
    
    public void ensureEntityExists(T m) throws RecordNotFoundException {
        if( m == null )
            throw new  RecordNotFoundException();

    }
}
