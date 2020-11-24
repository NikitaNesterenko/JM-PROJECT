package jm.stockx.api.dao;

import jm.stockx.entity.NotificationInfo;

public interface NotificationInfoDAO extends GenericDao<NotificationInfo, Long>{
    void updateField(Long userId, String nameField, boolean state);

}
