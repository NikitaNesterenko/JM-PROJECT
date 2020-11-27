package jm.stockx.rest_controller;

import jm.stockx.RealizeCalendarService;
import jm.stockx.dto.realizeCalendar.RealizeCalendarDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/resttest/api/realizes")
public class RealizeCalendarRESTController {

    private final RealizeCalendarService realizeCalendarService;

    @Autowired
    public RealizeCalendarRESTController(RealizeCalendarService realizeCalendarService) {
        this.realizeCalendarService = realizeCalendarService;
    }

    @GetMapping("/get")
    public ResponseEntity<List<RealizeCalendarDTO>> getRealizes() {
        return new ResponseEntity<>(realizeCalendarService.getSixImmediateRealizes(), HttpStatus.OK);
    }

}
