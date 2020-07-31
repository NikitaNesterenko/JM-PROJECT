package jm.stockx.api.dao;

import jm.stockx.entity.SupportQuestion;
import org.springframework.stereotype.Repository;

@Repository
public class QuestionDaoImpl extends AbstractDAO<SupportQuestion, Long> implements QuestionDAO {
}
