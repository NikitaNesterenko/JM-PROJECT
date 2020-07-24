package jm.stockx.controller.rest.user;

import jm.stockx.SupportUserService;
import jm.stockx.dto.CallbackFormDto;
import jm.stockx.util.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/rest/api/user/help/contactSupport")
public class HelpRestController {

    private final SupportUserService supportUserService;

    @Autowired
    public HelpRestController(SupportUserService supportUserService) {
        this.supportUserService = supportUserService;
    }

    @PostMapping
    public Response<?> askQuestion(@RequestBody CallbackFormDto callbackFormDto) {
        supportUserService.askQuestionByUser(callbackFormDto);
        return Response.ok(callbackFormDto);
    }
}
