package com.batval.myrent.dao;

import com.batval.myrent.model.CarType;

import java.util.List;

/**
 * Interface describing the behavior of the CarType DAO
 *
 * @author Baturo Valery
 * @version 1.0
 */
public interface CarTypeDao {

    /**
     * Get all types of cars
     *
     */
    List<CarType> getAllCarType();

    /**
     * Search type of the  car  by ID
     *
     * @param id car number
     */
    CarType findById(int id);
}
