package jm.stockx.api.dao;

import jm.stockx.entity.NotificationInfo;
import org.springframework.stereotype.Repository;

@Repository
public class NotificationInfoDAOImpl extends AbstractDAO<NotificationInfo, Long> implements NotificationInfoDAO{
    @Override
    public void updateField(Long userId, String nameField, boolean state) {
                entityManager.createQuery("" +
                "UPDATE NotificationInfo notificationInfo SET " +
                "notificationInfo." + nameField + " = :state " +
                "WHERE notificationInfo.user.id = :userId")
                .setParameter("state", state)
                .setParameter("userId", userId)
                .executeUpdate();
    }
}
