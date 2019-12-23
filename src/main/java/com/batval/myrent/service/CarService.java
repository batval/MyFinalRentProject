package com.batval.myrent.service;

import com.batval.myrent.model.Car;
import com.batval.myrent.model.CarType;

import java.util.List;


/**
 * Interface describing the behavior of the Car
 *
 * @author Baturo Valery
 * @version 1.0
 */
public interface CarService {

    /**
     * Save car
     *
     * @param car to save
     * @param sso owned
     */
    void saveCar(Car car, String sso);

    /**
     * Find all  car
     *
     */
    List<Car> findAllCars();
    /**
     * Delete  car by registration number
     *
     * @param regNo car registration number
     */
    void deleteCarByRegNo(String regNo);
    /**
     * Get all types of cars
     *
     */
    List<CarType> findAllCarType();
    /**
     * Find all owner cars
     *
     * @param sso owned
     */
    List<Car> findUserCars(String sso);

    /**
     * Check for uniqueness of the car
     *
     * @param id car
     * @param regNo registration number
     */
    boolean isRegNoUnique(Integer id, String regNo);

    /**
     * Search type of the  car  by ID
     *
     * @param id car number
     */
    CarType findCarTypeById(int id);
    /**
     * Search  car by ID
     *
     * @param id car number
     */
    Car findCarById(int id);
    /**
     * Search  car by registration number
     *
     * @param regNo car registration number
     */
    Car findCarByRegNo(String regNo);
    /**
     * Rent  car
     *
     * @param car car for rent
     * @param SSO user numbers
     */
    void rentCar(Car car, String SSO);
    /**
     * Mark that the car is free
     *
     * @param car free car
     * @param SSO user numbers
     */
    void freeCar(Car car, String SSO);
    /**
     * Check  car by registration number
     *
     * @param regNo car registration number
     */
    boolean checkIfExists(String regNo);

}
