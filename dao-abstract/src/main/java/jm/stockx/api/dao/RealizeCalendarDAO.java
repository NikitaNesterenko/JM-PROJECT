package jm.stockx.api.dao;

import jm.stockx.dto.realizeCalendar.RealizeCalendarDto;

import java.util.List;

public interface RealizeCalendarDAO {
    List<RealizeCalendarDto> getSixImmediateRealizes();
}
