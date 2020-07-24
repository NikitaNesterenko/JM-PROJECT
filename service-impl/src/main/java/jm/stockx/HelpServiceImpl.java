package jm.stockx;

import jm.stockx.api.dao.QuestionDAO;
import jm.stockx.dto.CallbackFormDto;
import jm.stockx.entity.SupportQuestion;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
public class HelpServiceImpl implements HelpService {

    private final MailService mailService;
    private final QuestionDAO questionDAO;

    @Autowired
    public HelpServiceImpl(MailService mailService, QuestionDAO questionDAO) {
        this.mailService = mailService;
        this.questionDAO = questionDAO;
    }

    @Override
    public void askQuestionByUser(CallbackFormDto callbackFormDto) {

        SupportQuestion supportQuestion = new SupportQuestion();
        supportQuestion.setDateTime(LocalDateTime.now());
        supportQuestion.setOrderNumber(callbackFormDto.getPrimaryOrderNumber());
        supportQuestion.setText(callbackFormDto.getDescription());
        questionDAO.add(supportQuestion);

        mailService.sendSimpleMessage("email@javamentor.ru", "Question from client",
                "user email is" + callbackFormDto.getEmail()
                        + "type" + callbackFormDto.getType()
                        + "subType" + callbackFormDto.getSubType()
                        + "primary order number" + callbackFormDto.getPrimaryOrderNumber());
    }
}
