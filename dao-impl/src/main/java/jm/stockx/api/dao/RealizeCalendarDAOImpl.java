package jm.stockx.api.dao;

import jm.stockx.dto.realizeCalendar.RealizeCalendarDTO;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;

@Repository
public class RealizeCalendarDAOImpl extends AbstractDAO<RealizeCalendarDTO, Long> implements RealizeCalendarDAO {
    @Override
    public List<RealizeCalendarDTO> getSixImmediateRealizes() {
        return entityManager.createQuery("SELECT NEW jm.stockx.dto.realizeCalendar.RealizeCalendarDTO(inf) " +
                "FROM ItemInfo " +
                "AS inf " +
                "WHERE inf.releaseDate >= :date")
                .setParameter("date", LocalDate.now())
                .setMaxResults(6)
                .getResultList();
    }
}
