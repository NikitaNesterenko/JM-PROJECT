package jm.stockx.api.dao;

import jm.stockx.dto.realizecalendar.RealizeCalendarDto;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;

@Repository
public class RealizeCalendarDaoImpl extends AbstractDAO<RealizeCalendarDto, Long> implements RealizeCalendarDAO {
    @Override
    public List<RealizeCalendarDto> getSixImmediateRealizes() {
        return entityManager.createQuery("SELECT NEW jm.stockx.dto.realizecalendar.RealizeCalendarDto(inf) " +
                "FROM ItemInfo inf " +
                "WHERE inf.releaseDate >= :date")
                .setParameter("date", LocalDate.now())
                .setMaxResults(6)
                .getResultList();
    }
}
