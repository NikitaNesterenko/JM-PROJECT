package jm.dao;

import jm.api.dao.ItemDAO;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

@Repository
public class ItemDaoImpl implements ItemDAO {

    @PersistenceContext
    EntityManager entityManager;

    @Override
    public void addItemImage(byte[] array) {

    }

    @Override
    public byte[] getItemImage() {
        return new byte[0];
    }
}
