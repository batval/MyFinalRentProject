package com.batval.myrent.model;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.io.Serializable;

/**
 * Class describing  of the User profile
 *
 * @author Baturo Valery
 * @version 1.0
 */
@Getter
@Setter
@Entity
@Table(name = "PROFILE")
public class UserProfile implements Serializable {
    /**
     *Id of user profile
     *
     */
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Integer id;
    /**
     *Type of user profile
     *
     */
    @Column(name = "TYPE", length = 15, unique = true, nullable = false)
    private String type = UserProfileType.USER.getUserProfileType();
    /**
     *Get hashCode
     *
     */
    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((id == null) ? 0 : id.hashCode());
        result = prime * result + ((type == null) ? 0 : type.hashCode());
        return result;
    }
    /**
     *Comparison
     *
     */
    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (!(obj instanceof UserProfile))
            return false;
        UserProfile other = (UserProfile) obj;
        if (id == null) {
            if (other.id != null)
                return false;
        } else if (!id.equals(other.id))
            return false;
        if (type == null) {
            if (other.type != null)
                return false;
        } else if (!type.equals(other.type))
            return false;
        return true;
    }
    /**
     *Print user profile
     *
     */
    @Override
    public String toString() {
        return "UserProfile [id=" + id + ", type=" + type + "]";
    }


}
