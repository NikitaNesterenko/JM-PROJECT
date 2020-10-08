package jm.stockx.api.dao;

import jm.stockx.dto.support.SupportQuestionDto;
import jm.stockx.entity.SupportQuestion;

public interface SupportQuestionDAO extends GenericDao<SupportQuestion, Long>{
    SupportQuestionDto getSupportQuestionDtoBySupportQuestionId(Long id);
}
