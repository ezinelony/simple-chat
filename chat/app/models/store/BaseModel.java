package models.store;

import com.avaje.ebean.Model;
import com.avaje.ebean.annotation.CreatedTimestamp;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import org.joda.time.DateTime;
import play.data.format.Formats;

import javax.persistence.Column;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;
import java.io.Serializable;
import java.util.UUID;

@JsonInclude(JsonInclude.Include.NON_EMPTY)
@MappedSuperclass
public abstract class BaseModel extends Model implements Serializable{
    @Id
    protected UUID id;


    @Column(name = "created_at")
    @CreatedTimestamp
    @Formats.DateTime(pattern = "yyyy-mm-ddThh:mm:ss.nnnnnn+|-hh:mm")
    protected DateTime createdAt;

    @Column(name = "is_deleted")
    protected Boolean deleted = false;
    
    
    public UUID getId(){
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    @JsonIgnore
    public Boolean isDeleted() {
        return deleted;
    }

    public void setDeleted(Boolean delete) {
        this.deleted = delete;
    }

    public DateTime getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(DateTime createdAt) {
        this.createdAt = createdAt;
    }

}
