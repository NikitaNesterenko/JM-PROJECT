package jm.stockx;

import jm.stockx.api.dao.NotificationInfoDAO;
import jm.stockx.entity.NotificationInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class NotificationInfoServiceImpl implements NotificationInfoService {

    private final NotificationInfoDAO notificationInfoDAO;

    @Autowired
    public NotificationInfoServiceImpl(NotificationInfoDAO notificationInfoDAO) {
        this.notificationInfoDAO = notificationInfoDAO;
    }


    @Override
    public void createNotificationInfo(NotificationInfo notificationInfo) {

        notificationInfoDAO.add(notificationInfo);
    }

    @Override
    public void updateField(Long userId, String nameField) {

        notificationInfoDAO.updateField(userId, nameField);
    }

    @Override
    public NotificationInfo getNotificationInfoById(Long userId) {

        return notificationInfoDAO.getById(userId);
    }

}
