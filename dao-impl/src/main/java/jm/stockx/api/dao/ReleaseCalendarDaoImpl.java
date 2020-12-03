package jm.stockx.api.dao;

import jm.stockx.dto.releaseCalendar.ReleaseCalendarDto;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;

@Repository
public class ReleaseCalendarDaoImpl extends AbstractDAO<ReleaseCalendarDto, Long> implements ReleaseCalendarDAO {
    @Override
    public List<ReleaseCalendarDto> getSixImmediateRealizes() {
        return entityManager.createQuery("SELECT NEW jm.stockx.dto.releaseCalendar.ReleaseCalendarDto(inf) " +
                "FROM ItemInfo inf " +
                "WHERE inf.releaseDate >= :date")
                .setParameter("date", LocalDate.now())
                .setMaxResults(6)
                .getResultList();
    }
}
