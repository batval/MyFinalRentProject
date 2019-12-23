package com.batval.myrent.converter;

import com.batval.myrent.model.CarType;
import com.batval.myrent.service.CarService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

/**
 * A converter class used in views to map id's to actual carType objects.
 * Implementation of {@link Converter} interface
 *
 * @author Baturo Valery
 * @version 1.0
 */
@Component
public class CarToCarTypeConverter implements Converter<Object, CarType> {

    @Autowired
    CarService carService;
    /**
     * Gets CarType
     * @see org.springframework.core.convert.converter.Converter#convert(java.lang.Object)
     */
    public CarType convert(Object element) {
        String carTypeToString = (String) element;

        if (carTypeToString.contains("-")) {
            carTypeToString = carTypeToString.substring(0, carTypeToString.indexOf("-")).replaceAll("\\s+", "");
        }

        Integer id = Integer.parseInt(carTypeToString);
        CarType carType = carService.findCarTypeById(id);
        return carType;
    }

}