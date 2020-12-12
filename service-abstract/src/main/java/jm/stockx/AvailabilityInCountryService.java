package jm.stockx;

import jm.stockx.entity.AvailabilityInCountry;

import java.util.List;

public interface AvailabilityInCountryService {

    AvailabilityInCountry getById(Long id);

    void add(AvailabilityInCountry availabilityInCountry);

    List<AvailabilityInCountry> getAll();

}
