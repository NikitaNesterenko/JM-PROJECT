package jm.stockx;

import jm.stockx.entity.NotificationInfo;

public interface NotificationInfoService {
    void createNotificationInfo(NotificationInfo notificationInfo);

    void updateField(Long userId, String nameField);

    NotificationInfo getNotificationInfoById(Long userId);
}
