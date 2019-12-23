package com.batval.myrent.model;

import org.junit.Test;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import static org.junit.Assert.*;

public class CarTest {



    @Test
    public void setId() {
        Car mockedCar=mock(Car.class);
        mockedCar.setId(1);
        verify(mockedCar).setId(1);
    }

    @Test
    public void setRegNo() {
        Car mockedCar=mock(Car.class);
        mockedCar.setRegNo("0000AA1");
        verify(mockedCar).setRegNo("0000AA1");
    }

    @Test
    public void setYear() {
        Car mockedCar=mock(Car.class);
        mockedCar.setYear("2020");
        verify(mockedCar).setYear("2020");
    }

    @Test
    public void setAvailable() {
        Car mockedCar=mock(Car.class);
        mockedCar.setAvailable(true);
        verify(mockedCar).setAvailable(true);
    }

    @Test
    public void setStartDate() {
        Car mockedCar=mock(Car.class);
        mockedCar.setStartDate("2019-12-25");
        verify(mockedCar).setStartDate("2019-12-25");
    }

    @Test
    public void setReturnDate() {
        Car mockedCar=mock(Car.class);
        mockedCar.setReturnDate("2019-12-26");
        verify(mockedCar).setReturnDate("2019-12-26");
    }


    @Test
    public void getId() {
        Car mockedCar=mock(Car.class);
        when(mockedCar.getId()).thenReturn(1L);
        assertEquals(mockedCar.getId(),1);
    }

    @Test
    public void getRegNo() {
        Car mockedCar=mock(Car.class);
        when(mockedCar.getRegNo()).thenReturn("0001AA1");
        assertEquals(mockedCar.getRegNo(),"0001AA1");
    }

    @Test
    public void getYear() {
        Car mockedCar=mock(Car.class);
        when(mockedCar.getYear()).thenReturn("2020");
        assertEquals(mockedCar.getYear(),"2020");
    }

    @Test
    public void isAvailable() {
        Car mockedCar=mock(Car.class);
        when(mockedCar.isAvailable()).thenReturn(false);
        assertEquals(mockedCar.isAvailable(),false);
    }

    @Test
    public void getStartDate() {
        Car mockedCar=mock(Car.class);
        when(mockedCar.getStartDate()).thenReturn("2019-12-25");
        assertEquals(mockedCar.getStartDate(),"2019-12-25");
    }

    @Test
    public void getReturnDate() {
        Car mockedCar=mock(Car.class);
        when(mockedCar.getReturnDate()).thenReturn("2019-12-26");
        assertEquals(mockedCar.getReturnDate(),"2019-12-26");
    }

}