package jm.stockx.controller.rest.user;

import jm.stockx.HelpService;
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

    private final HelpService helpService;

    @Autowired
    public HelpRestController(HelpService helpService) {
        this.helpService = helpService;
    }

    @PostMapping
    public Response<?> askQuestion(@RequestBody CallbackFormDto callbackFormDto) {
        helpService.askQuestionByUser(callbackFormDto);
        return Response.ok(callbackFormDto);
    }
}
