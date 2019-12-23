package com.batval.myrent.model;

import lombok.Getter;
import lombok.Setter;
import org.hibernate.validator.constraints.NotEmpty;

import javax.persistence.*;

/**
 * Class describing  of the Car type
 *
 * @author Baturo Valery
 * @version 1.0
 */
@Getter
@Setter
@Entity
@Table(name = "CAR_TYPE")
public class CarType {
    /**
     *Id of car type
     *
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "CAR_TYPE_ID", unique = true, nullable = false)
    private int id;
    /**
     *Mark of car
     *
     */
    @NotEmpty
    @Column(name = "MARK", nullable = false)
    private String mark;
    /**
     *Model of car
     *
     */
    @NotEmpty
    @Column(name = "MODEL", nullable = false)
    private String model;
    /**
     *Print type of car
     *
     */
    @Override
    public String toString() {
        return id + " - " + mark + " " + model;
    }
}
