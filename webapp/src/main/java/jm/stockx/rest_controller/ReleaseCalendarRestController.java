package jm.stockx.rest_controller;

import jm.stockx.ReleaseCalendarService;
import jm.stockx.dto.releaseCalendar.ReleaseCalendarDto;
import jm.stockx.util.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/rest/api/realizes")
public class ReleaseCalendarRestController {

    private final ReleaseCalendarService releaseCalendarService;

    @Autowired
    public ReleaseCalendarRestController(ReleaseCalendarService releaseCalendarService) {

        this.releaseCalendarService = releaseCalendarService;

    }

    @GetMapping("/get")
    public Response<List<ReleaseCalendarDto>> getRealizes() {

        return Response.ok(HttpStatus.OK, releaseCalendarService.getSixImmediateRealizes());

    }

}
