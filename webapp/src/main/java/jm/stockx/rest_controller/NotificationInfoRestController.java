package jm.stockx.rest_controller;

import jm.stockx.NotificationInfoService;
import jm.stockx.dto.notification.NotificationInfoDTO;
import jm.stockx.util.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/notification")
public class NotificationInfoRestController {
    private final NotificationInfoService notificationInfoService;

    @Autowired
    public NotificationInfoRestController(NotificationInfoService notificationInfoService) {
        this.notificationInfoService = notificationInfoService;
    }

    @PostMapping("/change")
    public Response<Void> changeNotificationInfo(@RequestBody NotificationInfoDTO notificationInfoDTO) {
        notificationInfoService.updateField(notificationInfoDTO.getUserId(), notificationInfoDTO.getName());
        return Response.ok().build();
    }
}