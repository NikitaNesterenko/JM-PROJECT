package jm.stockx.api.dao;

import jm.stockx.dto.SupportQuestionDto;
import jm.stockx.entity.SupportQuestion;
import org.springframework.stereotype.Repository;

@Repository
public class SupportQuestionDaoImpl extends AbstractDAO<SupportQuestion, Long> implements SupportQuestionDAO {

    @Override
    public SupportQuestionDto getSupportQuestionDtoById(Long id) {
        return entityManager.createQuery("" +
                "SELECT NEW jm.stockx.dto.SupportQuestionDto(" +
                "sq.id," +
                "sq.text," +
                "sq.orderNumber," +
                "sq.dateTime)" +
                "FROM SupportQuestion AS sq " +
                "WHERE id =: id", SupportQuestionDto.class)
                .setParameter("id", id)
                .getSingleResult();
    }
}
