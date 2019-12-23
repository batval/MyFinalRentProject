package com.batval.myrent.model;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

/**
 * Class describing  of the persistent logins
 * @author Baturo Valery
 * @version 1.0
 */
@Getter
@Setter
@Entity
@Table(name = "PERSISTENT_LOGINS")
public class PersistentLogin implements Serializable {
    /**
     *Series of token
     *
     */
    @Id
    private String series;
    /**
     *User name
     *
     */
    @Column(name = "USERNAME", unique = true, nullable = false)
    private String username;
    /**
     *Token
     *
     */
    @Column(name = "TOKEN", unique = true, nullable = false)
    private String token;
    /**
     *Date of last used
     *
     */
    @Temporal(TemporalType.TIMESTAMP)
    private Date last_used;

}
