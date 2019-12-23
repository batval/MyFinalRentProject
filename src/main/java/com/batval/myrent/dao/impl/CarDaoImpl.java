package com.batval.myrent.dao.impl;

import com.batval.myrent.dao.AbstractDao;
import com.batval.myrent.dao.CarDao;
import com.batval.myrent.exception.CarNotFoundException;
import com.batval.myrent.model.Car;
import org.hibernate.Criteria;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

/**
 * Class describing the behavior of the Car DAO extend of {@link AbstractDao} class
 * Implementation of {@link CarDao} interface
 *
 * @author Baturo Valery
 * @version 1.0
 */

@Repository("carDao")
public class CarDaoImpl extends AbstractDao<Integer, Car> implements CarDao {

    /**
     * Car registration number
     */
    private static final String REG_NO_PROPERTY = "regNo";

    /**
     * Search  car by ID
     *
     * @param id car number
     */
    @Override
    public Car findById(int id) {
        Optional<Car> optionalCar = Optional.ofNullable(getByKey(id));
        return optionalCar.orElseThrow(() -> new CarNotFoundException(String.valueOf(id)));
    }

    /**
     * Search  car by registration number
     *
     * @param regNo car registration number
     */
    @Override
    public Car findByRegNo(String regNo) {
        Criteria crit = createEntityCriteria();
        crit.add(Restrictions.eq(REG_NO_PROPERTY, regNo));
        Optional<Car> optionalCar = Optional.ofNullable((Car) crit.uniqueResult());
        return optionalCar.orElseThrow(() -> new CarNotFoundException(String.valueOf(regNo)));
    }

    /**
     * Save car
     *
     * @param car to save
     */
    @Override
    public void save(Car car) {
        persist(car);
    }

    /**
     * Delete  car by registration number
     *
     * @param regNo car registration number
     */
    @Override
    public void deleteByRegNo(String regNo) {
        Criteria crit = createEntityCriteria();
        crit.add(Restrictions.eq(REG_NO_PROPERTY, regNo));
        delete((Car) crit.uniqueResult());
    }

    /**
     * Find all  car
     */
    @SuppressWarnings("unchecked")
    @Override
    public List<Car> findAllCars() {
        Criteria criteria = createEntityCriteria();
        criteria.setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY);
        return (List<Car>) criteria.list();
    }

    /**
     * Check  car by registration number
     *
     * @param regNo car registration number
     */
    @Override
    public boolean checkIfNotExists(String regNo) {
        Criteria crit = createEntityCriteria();
        crit.add(Restrictions.eq(REG_NO_PROPERTY, regNo.toUpperCase()));
        return crit.uniqueResult() == null;
    }

}
