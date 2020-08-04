package jm.stockx.api.dao;

import jm.stockx.dto.SupportQuestionDto;
import jm.stockx.entity.SupportQuestion;

public interface SupportQuestionDAO extends GenericDao<SupportQuestion, Long>{
    SupportQuestionDto getSupportQuestionDtoById(Long id);
}
