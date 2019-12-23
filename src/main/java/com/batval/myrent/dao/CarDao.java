package com.batval.myrent.dao;

import com.batval.myrent.model.Car;

import java.util.List;



/**
 * Interface describing the behavior of the Car DAO
 *
 * @author Baturo Valery
 * @version 1.0
 */

public interface CarDao {

    /**
     * Search  car by ID
     *
     * @param id car number
     */
    Car findById(int id);

    /**
     * Search  car by registration number
     *
     * @param regNo car registration number
     */
    Car findByRegNo(String regNo);

    /**
     * Save car
     *
     * @param car to save
     */
    void save(Car car);

    /**
     * Delete  car by registration number
     *
     * @param regNo car registration number
     */
    void deleteByRegNo(String regNo);

    /**
     * Find all  car
     *
     */
    List<Car> findAllCars();

    /**
     * Check  car by registration number
     *
     * @param regNo car registration number
     */
    boolean checkIfNotExists(String regNo);

}
