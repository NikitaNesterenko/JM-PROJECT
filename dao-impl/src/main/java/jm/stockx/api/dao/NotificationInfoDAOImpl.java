package jm.stockx.api.dao;

import jm.stockx.entity.NotificationInfo;
import org.springframework.stereotype.Repository;

@Repository
public class NotificationInfoDAOImpl extends AbstractDAO<NotificationInfo, Long> implements NotificationInfoDAO {
    @Override
    public void updateField(Long userId, String nameField) {
        entityManager.createNativeQuery("" +
                "UPDATE notification_info  SET "  + nameField + " = NOT " + nameField +
                " WHERE user_id = :userId")
                .setParameter("userId", userId)
                .executeUpdate();
    }
}
