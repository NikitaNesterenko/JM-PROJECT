package jm.stockx;

import jm.stockx.api.dao.RealizeCalendarDAO;
import jm.stockx.dto.realizeCalendar.RealizeCalendarDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RealizeCalendarServiceImpl implements RealizeCalendarService {

    private final RealizeCalendarDAO realizeCalendarDAO;

    @Autowired
    public RealizeCalendarServiceImpl(RealizeCalendarDAO realizeCalendarDAO) {
        this.realizeCalendarDAO = realizeCalendarDAO;
    }

    @Override
    public List<RealizeCalendarDto> getSixImmediateRealizes() {
        return realizeCalendarDAO.getSixImmediateRealizes();
    }
}
