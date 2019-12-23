package com.batval.myrent.dao.impl;

import com.batval.myrent.dao.AbstractDao;
import com.batval.myrent.dao.CarTypeDao;
import com.batval.myrent.model.CarType;
import org.hibernate.Criteria;
import org.springframework.stereotype.Repository;

import java.util.List;


/**
 * Class describing the behavior of the CarType DAO extend of {@link AbstractDao} class
 * Implementation of {@link CarTypeDao} interface
 *
 * @author Baturo Valery
 * @version 1.0
 */
@Repository("carTypeDao")
public class CarTypeDaoImpl extends AbstractDao<Integer, CarType> implements CarTypeDao {

    /**
     * Get all  car
     */
    public List<CarType> getAllCarType() {
        Criteria criteria = createEntityCriteria();
        criteria.setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY);
        List<CarType> carTypes = (List<CarType>) criteria.list();
        return carTypes;
    }

    /**
     * Search type of the  car  by ID
     *
     * @param id car number
     */
    public CarType findById(int id) {
        return getByKey(id);
    }
}
