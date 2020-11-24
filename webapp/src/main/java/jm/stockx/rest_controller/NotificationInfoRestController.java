package jm.stockx.rest_controller;

import jm.stockx.NotificationInfoService;
import jm.stockx.entity.NotificationInfo;
import jm.stockx.util.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

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
    public Response<?> changeNotificationInfo(@RequestParam(name = "nameField") String nameField, @RequestParam(name="userId") String userId, @RequestParam(name="state") String state){
        Long userIdN =     Long.valueOf(userId);
        boolean stateN = Boolean.parseBoolean(state);
        notificationInfoService.updateField(userIdN, nameField, stateN);
        return Response.ok().build();
    }

    @PostMapping("/change1")
    public Response<?> changeNotificationInfo1(@RequestBody Map<String, String> json){
        String nameField = json.get("nameField");
        Long userId = Long.valueOf(json.get("userId"));
        boolean state = Boolean.parseBoolean(json.get("state"));
        notificationInfoService.updateField(userId, nameField, state);
        return Response.ok().build();
    }


}
