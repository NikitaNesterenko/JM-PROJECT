package jm.stockx;

import jm.stockx.entity.NotificationInfo;

public interface NotificationInfoService {

    void createNotificationInfo(NotificationInfo notificationInfo);
    void updateField(Long userId, String nameField, boolean state);
    NotificationInfo getNotificationInfoById(Long userId);





}
