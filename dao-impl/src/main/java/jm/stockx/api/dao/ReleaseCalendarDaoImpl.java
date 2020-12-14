package jm.stockx.api.dao;

import jm.stockx.dto.releaseCalendar.ReleaseCalendarDto;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;

@Repository
public class ReleaseCalendarDaoImpl extends AbstractDAO<ReleaseCalendarDto, Long> implements ReleaseCalendarDAO {
/*    @Override
    public List<ReleaseCalendarDto> getSixImmediateReleases() {
        return entityManager.createQuery("SELECT NEW jm.stockx.dto.releaseCalendar.ReleaseCalendarDto(inf) " +
                "FROM ItemInfo inf " +
                "WHERE inf.releaseDate >= :date")
                .setParameter("date", LocalDate.now())
                .setMaxResults(6)
                .getResultList();
    }*/

    @Override
    public List<ReleaseCalendarDto> getSixImmediateReleases() {
        return entityManager.createQuery("SELECT NEW jm.stockx.dto.releaseCalendar.ReleaseCalendarDto(item.id, " +
                "inf.releaseDate, item.name, inf.itemImageUrl, inf.lowestAsk) " +
                "FROM ItemInfo inf " +
                "JOIN Item item ON inf.item = item " +
                "WHERE inf.releaseDate >= :date")
                .setParameter("date", LocalDate.now())
                .setMaxResults(6)
                .getResultList();
    }
}
