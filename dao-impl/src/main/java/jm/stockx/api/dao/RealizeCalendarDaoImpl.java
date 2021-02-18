package jm.stockx.api.dao;

import jm.stockx.dto.realizeCalendar.RealizeCalendarDto;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;

@Repository
public class RealizeCalendarDaoImpl extends AbstractDAO<RealizeCalendarDto, Long> implements RealizeCalendarDAO {
    @Override
    public List<RealizeCalendarDto> getSixImmediateRealizes() {
        return entityManager.createQuery(
                "SELECT NEW jm.stockx.dto.realizeCalendar.RealizeCalendarDto(inf) " +
                        "FROM ItemInfo inf " +
                        "WHERE inf.releaseDate >= :date " +
                        "ORDER BY inf.releaseDate DESC ", RealizeCalendarDto.class)
                .setParameter("date", LocalDate.of(2018, 1, 1))
                .setMaxResults(6)
                .getResultList();
    }
}