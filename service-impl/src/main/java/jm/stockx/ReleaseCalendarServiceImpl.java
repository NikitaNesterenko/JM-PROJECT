package jm.stockx;

import jm.stockx.api.dao.ReleaseCalendarDAO;
import jm.stockx.dto.releaseCalendar.ReleaseCalendarDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ReleaseCalendarServiceImpl implements ReleaseCalendarService {

    private final ReleaseCalendarDAO releaseCalendarDAO;

    @Autowired
    public ReleaseCalendarServiceImpl(ReleaseCalendarDAO releaseCalendarDAO) {
        this.releaseCalendarDAO = releaseCalendarDAO;
    }

    @Override
    public List<ReleaseCalendarDto> getSixImmediateRealizes() {
        return releaseCalendarDAO.getSixImmediateRealizes();
    }
}
