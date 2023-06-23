package id.co.indivara.jdt12.university.models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.MappedSuperclass;
import javax.persistence.PrePersist;
import javax.persistence.PreUpdate;
import java.util.Date;

@MappedSuperclass
@Getter
@Setter
public class BaseEntity {
    @JsonIgnore
    private Date createdDate;
    @JsonIgnore
    private Date lastUpdateDate;

    @PrePersist
    private void onCreate(){
        this.createdDate = new Date();
        this.lastUpdateDate = this.createdDate;
    }

    @PreUpdate
    private void onUpdate(){
        this.lastUpdateDate = new Date();
    }
}
