package jm.stockx;

import jm.stockx.dto.releaseCalendar.ReleaseCalendarDto;

import java.util.List;

public interface ReleaseCalendarService {
    List<ReleaseCalendarDto> getSixImmediateRealizes();
}
