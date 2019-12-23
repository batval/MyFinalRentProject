package com.batval.myrent.model;

import lombok.Getter;
import lombok.Setter;
import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.NotEmpty;

import javax.persistence.*;
import javax.validation.constraints.Pattern;
import java.io.Serializable;
import java.util.List;
import java.util.Set;

/**
 * Class describing  of the Car
 *
 * @author Baturo Valery
 * @version 1.0
 */

@Getter
@Setter
@Entity
@Table(name = "CAR")
public class Car implements Serializable {
    /**
     *Id of car
     *
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "CAR_ID", unique = true, nullable = false)
    private long id;
    /**
     *Registration number of car
     *
     */
    @NotEmpty
    @Column(name = "REG_NO", nullable = false, unique = true)
    @Length(min = 5, max = 8)
    private String regNo;
    /**
     *Year of issue
     *
     */
    @NotEmpty
    @Column(name = "YEAR", nullable = false)
    @Pattern(regexp = "^[1][9][9][0-9]$|^[2][0][0-9]{2}$")
    private String year;
    /**
     *Available of the car
     *
     */
    @Column(name = "AVAILABLE", nullable = false)
    private boolean available = true;

    /**
     *Start day rent
     *
     */
    @Column(name = "START_DATE")
    private String startDate;

    /**
     *Return day rent
     *
     */
    @Column(name = "RETURN_DATE")
    private String returnDate;
    /**
     *Id of car types
     *
     */
    @OneToOne
    @JoinColumn(name = "CAR_TYPE_ID")
    private CarType carType;
    /**
     *Owned of car
     *
     */
    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(name = "OWNED_CAR",
            joinColumns = {@JoinColumn(name = "CAR_ID")},
            inverseJoinColumns = {@JoinColumn(name = "USER_ID")})
    private Set<User> users;
    /**
     *Reservation
     *
     */
    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(name = "RESERVATION",
            joinColumns = {@JoinColumn(name = "CAR_ID")},
            inverseJoinColumns = {@JoinColumn(name = "USER_ID")})
    private List<User> rentUsers;

    /**
     *Comparison
     *
     */
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Car car = (Car) o;

        if (id != car.id) return false;
        if (regNo != null ? !regNo.equals(car.regNo) : car.regNo != null) return false;
        if (year != null ? !year.equals(car.year) : car.year != null) return false;
        return (carType != null ? !carType.equals(car.carType) : car.carType != null);
    }

    /**
     *Get hashCode
     *
     */
    @Override
    public int hashCode() {
        int result = (int) (id ^ (id >>> 32));
        result = 31 * result + (regNo != null ? regNo.hashCode() : 0);
        result = 31 * result + (year != null ? year.hashCode() : 0);
        result = 31 * result + (carType != null ? carType.hashCode() : 0);
        return result;
    }

    /**
     *Print car
     *
     */
    @Override
    public String toString() {
        return getRegNo();
    }
}
