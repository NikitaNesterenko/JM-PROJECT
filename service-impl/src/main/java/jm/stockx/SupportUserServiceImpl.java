package jm.stockx;

import jm.stockx.api.dao.SupportQuestionDAO;
import jm.stockx.dto.support.CallbackFormDto;
import jm.stockx.entity.SupportQuestion;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;

@Service
@Transactional
public class SupportUserServiceImpl implements SupportUserService {

    private final MailService mailService;
    private final SupportQuestionDAO supportQuestionDAO;

    @Autowired
    public SupportUserServiceImpl(MailService mailService, SupportQuestionDAO supportQuestionDAO) {
        this.mailService = mailService;
        this.supportQuestionDAO = supportQuestionDAO;
    }

//    TODO
    @Override
    public void askQuestionByUser(CallbackFormDto callbackFormDto) {

        SupportQuestion supportQuestion = new SupportQuestion();
        supportQuestion.setDateTime(LocalDateTime.now());
        supportQuestion.setOrderNumber(callbackFormDto.getPrimaryOrderNumber());
        supportQuestion.setText(callbackFormDto.getDescription());
        supportQuestionDAO.add(supportQuestion);

        mailService.sendSimpleMessage("email@javamentor.ru", "Question from client",
                "user email is" + callbackFormDto.getEmail()
                        + "type" + callbackFormDto.getType()
                        + "subType" + callbackFormDto.getSubType()
                        + "primary order number" + callbackFormDto.getPrimaryOrderNumber());
    }
}
