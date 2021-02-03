package jm.stockx;

import jm.stockx.dto.realizecalendar.RealizeCalendarDto;

import java.util.List;

public interface RealizeCalendarService {
    List<RealizeCalendarDto> getSixImmediateRealizes();
}
