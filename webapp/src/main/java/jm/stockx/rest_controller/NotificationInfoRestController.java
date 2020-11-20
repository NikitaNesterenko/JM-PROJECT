package jm.stockx.rest_controller;

import jm.stockx.NotificationInfoService;
import jm.stockx.entity.NotificationInfo;
import jm.stockx.util.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/notification")
public class NotificationInfoRestController {

    private final NotificationInfoService notificationInfoService;
    private NotificationInfo notificationInfo;

    @Autowired
    public NotificationInfoRestController(NotificationInfoService notificationInfoService) {
        this.notificationInfoService = notificationInfoService;
    }

    @GetMapping("/change")
    public Response<?> changeNotificationInfo(@RequestParam(name = "nameField") String nameField, @RequestParam(name="userId") Long userId, @RequestParam(name="state") boolean state){
            notificationInfoService.updateField(userId, nameField, state);
        return Response.ok().build();
    }
}
