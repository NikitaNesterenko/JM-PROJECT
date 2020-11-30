package jm.stockx.rest_controller;

import jm.stockx.NotificationInfoService;
import jm.stockx.entity.NotificationInfo;
import jm.stockx.util.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
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

    @PostMapping("/change")
    public Response<?> changeNotificationInfo(@RequestParam(name = "nameField") String nameField, @RequestParam(name = "userId") String userId, @RequestParam(name = "state") String state) {
        Long userIdN = Long.valueOf(userId);
        boolean stateN = Boolean.parseBoolean(state);
        notificationInfoService.updateField(userIdN, nameField, stateN);
        return Response.ok().build();
    }


}
