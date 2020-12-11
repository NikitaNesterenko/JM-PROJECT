package jm.stockx.rest_controller;

import jm.stockx.RealizeCalendarService;
import jm.stockx.util.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/rest/api/realizes")
public class RealizeCalendarRestController {

    private final RealizeCalendarService realizeCalendarService;

    @Autowired
    public RealizeCalendarRestController(RealizeCalendarService realizeCalendarService) {

        this.realizeCalendarService = realizeCalendarService;

    }

    @GetMapping("/get")
    public Response<?> getRealizes() {

        return Response.ok(HttpStatus.OK, realizeCalendarService.getSixImmediateRealizes());

    }

}
