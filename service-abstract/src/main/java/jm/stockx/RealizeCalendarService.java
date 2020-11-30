package jm.stockx;

import jm.stockx.dto.realizeCalendar.RealizeCalendarDto;

import java.util.List;

public interface RealizeCalendarService {
    List<RealizeCalendarDto> getSixImmediateRealizes();
}
