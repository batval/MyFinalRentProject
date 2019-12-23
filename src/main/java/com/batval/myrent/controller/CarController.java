package com.batval.myrent.controller;

import com.batval.myrent.model.Car;
import com.batval.myrent.service.CarService;
import com.batval.myrent.service.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import javax.validation.Valid;
import java.util.Locale;


/**
 * Car controller
 *
 * @author Baturo Valery
 * @version 1.0
 */
@Controller
@RequestMapping("/")
public class CarController {

    /**
     * Event Logger
     */
    private static final Logger logger = LoggerFactory.getLogger(CarController.class);

    private static final String LOGGED_USER = "loggedinuser";
    private static final String SUCCESS = "success";
    private static final String REGISTRATION_SUCCESS = "registrationsuccess";
    private static final String RENT_CAR = "rentcar";
    private static final String CAR = "car";
    private static final String CARS = "cars";
    private static final String CAR_TYPES = "carTypes";
    private static final String REGISTER_CAR = "registercar";
    private static final String FIND_CAR = "findcar";

    /**
     * Car service
     */
    @Autowired
    CarService carService;
    /**
     * User service
     */
    @Autowired
    UserService userService;
    /**
     * Message source
     */
    @Autowired
    MessageSource messageSource;

    /**
     * Access to the environment
     */
    @Autowired
    private Environment env;
    /**
     * Method handles delete car redirect.
     */
    @RequestMapping(value = {"/delete-car-{id}"}, method = RequestMethod.GET)
    public String deleteCar(@PathVariable String id, ModelMap model) {
        carService.deleteCarByRegNo(id);
        model.addAttribute(SUCCESS, env.getProperty("car.deleted.successfully"));
        logger.info("Car with id:"+id+" deleted");
        return REGISTRATION_SUCCESS;
    }
    /**
     * Method handles rent car redirect. (Get all car)
     */
    @RequestMapping(value = {"/rent-car-{regNo}"}, method = RequestMethod.GET)
    public String rentCar(@PathVariable String regNo, ModelMap model) {
        Car car = carService.findCarByRegNo(regNo);
        model.addAttribute(CAR, car);
        return RENT_CAR;
    }
    /**
     * Method handles rent car redirect.
     */
    @RequestMapping(value = {"/rent-car-{regNo}"}, method = RequestMethod.POST)
    public String saveRentCar(@Valid Car car, BindingResult result, ModelMap model) {
        carService.rentCar(car, userService.getPrincipal());
        model.addAttribute(SUCCESS, "Car " + car.getRegNo() + " rented successfully");
        logger.info("Car:"+car+" rented");
        return REGISTRATION_SUCCESS;
    }
    /**
     * Method handles new car redirect. (get form)
     */
    @RequestMapping(value = {"/newcar"}, method = RequestMethod.GET)
    public String newCar(ModelMap model) {
        Car car = new Car();
        model.addAttribute(CAR, car);
        model.addAttribute(CAR_TYPES, carService.findAllCarType());
        return REGISTER_CAR;
    }
    /**
     * Method handles new car redirect. (get form)
     */
    @RequestMapping(value = {"/newcar"}, method = RequestMethod.POST)
    public String saveCar(@Valid Car car, BindingResult result, ModelMap model) {

        if (result.hasErrors()) {
            return REGISTER_CAR;
        }
        if (!carService.isRegNoUnique((int) car.getId(), car.getRegNo())) {
            FieldError ssoError = new FieldError(CAR, "regNo", messageSource.getMessage("non.unique.regNo", new String[]{car.getRegNo()}, Locale.getDefault()));
            result.addError(ssoError);
            return REGISTER_CAR;
        }
        carService.saveCar(car, userService.getPrincipal());
        model.addAttribute(SUCCESS, "Car " + car.getRegNo() + " registered successfully");
        model.addAttribute(LOGGED_USER, userService.getPrincipal());
        logger.info("Car:"+car+" registered");
        return REGISTRATION_SUCCESS;
    }
    /**
     * Method handles  car redirect. (get form all cars)
     */
    @RequestMapping(value = {"/cars"}, method = RequestMethod.GET)
    public String listUsers(@RequestParam(value = "regNo", required = false) String regNo, ModelMap model) {
        if (regNo != null && carService.checkIfExists(regNo)) {
            model.addAttribute(FIND_CAR, carService.findCarByRegNo(regNo));
            logger.info("Search car by:"+regNo);
        }
        model.addAttribute(LOGGED_USER, userService.getPrincipal());
        model.addAttribute(CARS, carService.findAllCars());
        return CARS;
    }

}
