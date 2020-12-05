package jm.stockx.api.dao;

import jm.stockx.dto.releaseCalendar.ReleaseCalendarDto;

import java.util.List;

public interface ReleaseCalendarDAO {
    List<ReleaseCalendarDto> getSixImmediateRealizes();
}
