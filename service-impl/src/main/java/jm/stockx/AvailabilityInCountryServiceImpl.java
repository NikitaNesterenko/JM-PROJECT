package jm.stockx;

import jm.stockx.api.dao.AvailabilityInCountryDAO;
import jm.stockx.entity.AvailabilityInCountry;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
@Transactional
public class AvailabilityInCountryServiceImpl implements AvailabilityInCountryService {

    private final AvailabilityInCountryDAO availabilityInCountryDAO;

    @Autowired
    public AvailabilityInCountryServiceImpl(AvailabilityInCountryDAO dao) {
        availabilityInCountryDAO = dao;
    }

    @Transactional
    @Override
    public AvailabilityInCountry getById(Long id) {
        return availabilityInCountryDAO.getById(id);
    }

    @Transactional
    @Override
    public void add(AvailabilityInCountry availabilityInCountry) {
        availabilityInCountryDAO.add(availabilityInCountry);
    }

    @Transactional
    @Override
    public List<AvailabilityInCountry> getAll() {
        return availabilityInCountryDAO.getAll();
    }
}
