package com.batval.myrent.model;

import lombok.Getter;
import lombok.Setter;
import org.hibernate.validator.constraints.Email;
import org.hibernate.validator.constraints.NotEmpty;

import javax.persistence.*;
import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

/**
 * Class describing  of the User
 *
 * @author Baturo Valery
 * @version 1.0
 */
@Getter
@Setter
@Entity
@Table(name = "USER")
public class User implements Serializable {
    /**
     *Id of user
     *
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    /**
     * Single Sign-On id
     */
    @NotEmpty
    @Column(name = "SSO_ID", unique = true, nullable = false)
    private String ssoId;
    /**
     * Password of user
     */
    @Column(name = "PASSWORD", nullable = false)
    @NotEmpty
    private String password;
    /**
     * First name
     */
    @NotEmpty
    @Column(name = "FIRST_NAME", nullable = false)
    private String firstName;
    /**
     * Last name
     */
    @NotEmpty
    @Column(name = "LAST_NAME", nullable = false)
    private String lastName;
    /**
     * User's email
     */
    @NotEmpty
    @Email
    @Column(name = "EMAIL", nullable = false)
    private String email;
    /**
     * User's picture
     */
    @Lob
    @Column(name = "PICTURE")
    private byte[] picture;
    /**
     * User's profile
     */
    @NotEmpty
    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(name = "USERS_PROFILES",
            joinColumns = {@JoinColumn(name = "USER_ID")},
            inverseJoinColumns = {@JoinColumn(name = "USER_PROFILE_ID")})
    private Set<UserProfile> userProfiles = new HashSet<>();
    /**
     * Reservation car
     */
    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(name = "RESERVATION",
            joinColumns = {@JoinColumn(name = "USER_ID")},
            inverseJoinColumns = {@JoinColumn(name = "CAR_ID")})
    private Set<Car> cars = new HashSet<>();
    /**
     * Owned car
     */
    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(name = "OWNED_CAR",
            joinColumns = {@JoinColumn(name = "USER_ID")},
            inverseJoinColumns = {@JoinColumn(name = "CAR_ID")})
    private Set<Car> ownedCars = new HashSet<>();
    /**
     *Comparison
     *
     */
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        User user = (User) o;

        if (id != null ? !id.equals(user.id) : user.id != null) return false;
        if (ssoId != null ? !ssoId.equals(user.ssoId) : user.ssoId != null) return false;
        if (password != null ? !password.equals(user.password) : user.password != null) return false;
        if (firstName != null ? !firstName.equals(user.firstName) : user.firstName != null) return false;
        if (lastName != null ? !lastName.equals(user.lastName) : user.lastName != null) return false;
        if (email != null ? !email.equals(user.email) : user.email != null) return false;
        if (cars != null ? !cars.equals(user.cars) : user.cars != null) return false;
        return ownedCars != null ? ownedCars.equals(user.ownedCars) : user.ownedCars == null;
    }
    /**
     *Get hashCode
     *
     */
    @Override
    public int hashCode() {
        int result = id != null ? id.hashCode() : 0;
        result = 31 * result + (ssoId != null ? ssoId.hashCode() : 0);
        result = 31 * result + (password != null ? password.hashCode() : 0);
        result = 31 * result + (firstName != null ? firstName.hashCode() : 0);
        result = 31 * result + (lastName != null ? lastName.hashCode() : 0);
        result = 31 * result + (email != null ? email.hashCode() : 0);
        return result;
    }
    /**
     *Print user
     *
     */
    @Override
    public String toString() {
        return "User [id=" + id + ", ssoId=" + ssoId + ", password=" + password
                + ", firstName=" + firstName + ", lastName=" + lastName
                + ", email=" + email + "]";
    }

}
