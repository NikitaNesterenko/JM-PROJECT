package jm.stockx.api.dao;

import jm.stockx.dto.realizeCalendar.RealizeCalendarDTO;

import java.util.List;

public interface RealizeCalendarDAO {
    List<RealizeCalendarDTO> getSixImmediateRealizes();
}
