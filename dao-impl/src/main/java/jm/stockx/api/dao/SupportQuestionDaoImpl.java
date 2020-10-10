package jm.stockx.api.dao;

import jm.stockx.dto.support.SupportQuestionDto;
import jm.stockx.entity.SupportQuestion;
import org.springframework.stereotype.Repository;

@Repository
public class SupportQuestionDaoImpl extends AbstractDAO<SupportQuestion, Long> implements SupportQuestionDAO {

    @Override
    public SupportQuestionDto getSupportQuestionDtoBySupportQuestionId(Long id) {
        return entityManager.createQuery("" +
                "SELECT NEW jm.stockx.dto.support.SupportQuestionDto(" +
                "sq.id," +
                "sq.text," +
                "sq.orderNumber," +
                "sq.dateTime)" +
                "FROM SupportQuestion AS sq " +
                "WHERE sq.id =: id", SupportQuestionDto.class)
                .setParameter("id", id)
                .getSingleResult();
    }
}
