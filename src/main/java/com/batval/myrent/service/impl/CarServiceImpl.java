package com.batval.myrent.service.impl;

import com.batval.myrent.dao.CarDao;
import com.batval.myrent.dao.CarTypeDao;
import com.batval.myrent.dao.UserDao;
import com.batval.myrent.model.Car;
import com.batval.myrent.model.CarType;
import com.batval.myrent.model.User;
import com.batval.myrent.service.CarService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;


/**
 * Class describing the behavior of the Car
 * Implementation of {@link CarService} interface
 *
 * @author Baturo Valery
 * @version 1.0
 */
@Service
@Transactional
public class CarServiceImpl implements CarService {

    /**
     * Event Logger
     */
    private static final Logger logger = LoggerFactory.getLogger(CarServiceImpl.class);
    /**
     *Work with CarDao
     *
     */
    @Autowired
    CarDao carDao;
    /**
     *Work with CarTypeDao
     *
     */
    @Autowired
    CarTypeDao carTypeDao;

    /**
     *Work with UserDao
     *
     */
    @Autowired
    UserDao userDao;

    /**
     * Save car
     *
     * @param car to save
     * @param sso owned
     */
    @Override
    public void saveCar(Car car, String sso) {
        car.setRegNo(car.getRegNo().toUpperCase());
        carDao.save(car);
        User user = userDao.findBySSO(sso);
        user.getOwnedCars().add(car);
        logger.info("Save car to database: "+car.toString());

    }
    /**
     * Find all  car
     *
     */
    @Override
    public List<Car> findAllCars() {
        logger.info("Find all cars");
        return carDao.findAllCars();
    }
    /**
     * Delete  car by registration number
     *
     * @param regNo car registration number
     */
    @Override
    public void deleteCarByRegNo(String regNo) {
        carDao.deleteByRegNo(regNo);
        logger.info("Delete car: "+regNo);
    }
    /**
     * Get all types of cars
     *
     */
    @Override
    public List<CarType> findAllCarType() {
        logger.info("Get all car type");
        return carTypeDao.getAllCarType();
    }

    /**
     * Search type of the  car  by ID
     *
     * @param id car number
     */
    @Override
    public CarType findCarTypeById(int id) {
        logger.info("Delete car: "+id);
        return carTypeDao.findById(id);
    }
    /**
     * Search  car by ID
     *
     * @param id car number
     */
    @Override
    public Car findCarById(int id) {
        logger.info("Fing car by id:"+id);
        return carDao.findById(id);
    }
    /**
     * Search  car by registration number
     *
     * @param regNo car registration number
     */
    @Override
    public Car findCarByRegNo(String regNo)
    {
        logger.info("Fing car by gerNo:"+regNo);
        return carDao.findByRegNo(regNo);
    }
    /**
     * Check for uniqueness of the car
     *
     * @param id car
     * @param regNo registration number
     */
    @Override
    public boolean isRegNoUnique(Integer id, String regNo) {
        logger.info("Check is regNo:"+regNo+" uniq");
        return carDao.checkIfNotExists(regNo);
    }
    /**
     * Rent  car
     *
     * @param car car for rent
     * @param SSO user numbers
     */
    @Override
    public void rentCar(Car car, String SSO) {
        Car entity = carDao.findByRegNo(car.getRegNo());
        User user = userDao.findBySSO(SSO);
        user.getCars().add(entity);
        update(entity, car);
        logger.info("User: "+user+" rent car: "+car);
    }
    /**
     * Mark that the car is free
     *
     * @param car free car
     * @param SSO user numbers
     */
    @Override
    public void freeCar(Car car, String SSO) {
        Car entity = carDao.findByRegNo(car.getRegNo());
        User user = userDao.findBySSO(SSO);
        user.getCars().remove(entity);
        update(entity, car);
        logger.info("User: "+user+" rent car: "+car);
    }

    /**
     * Check  car by registration number
     *
     * @param regNo car registration number
     */
    @Override
    public boolean checkIfExists(String regNo) {
        return !carDao.checkIfNotExists(regNo);
    }
    /**
     * Find all owner cars
     *
     * @param sso owned
     */
    public List<Car> findUserCars(String sso) {
        User user = userDao.findBySSO(sso);
        logger.info("Search cars for user:"+user);
        return (List) new ArrayList(user.getOwnedCars());
    }

    /**
     * Update  car
     *
     * @param entity car for database
     * @param car update
     */
    private void update(Car entity, Car car) {
        entity.setRegNo(car.getRegNo());
        entity.setYear(car.getYear());
        entity.setAvailable(false);
        entity.setCarType(car.getCarType());
        entity.setStartDate(car.getStartDate());
        entity.setReturnDate(car.getReturnDate());
        logger.info("Update car:"+car);
    }
}
