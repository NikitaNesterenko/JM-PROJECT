package jm.stockx;

import jm.stockx.dto.realizeCalendar.RealizeCalendarDTO;

import java.util.List;

public interface RealizeCalendarService {
    List<RealizeCalendarDTO> getSixImmediateRealizes();
}
