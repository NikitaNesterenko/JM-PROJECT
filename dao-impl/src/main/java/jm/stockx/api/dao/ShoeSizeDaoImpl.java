package jm.stockx.api.dao;

import jm.stockx.dto.ShoeSizeDto;
import jm.stockx.entity.ShoeSize;
import jm.stockx.enums.ShoeSizeTypes;
import org.springframework.stereotype.Repository;

@Repository
public class ShoeSizeDaoImpl extends AbstractDAO<ShoeSize, Long> implements ShoeSizeDAO {

    @Override
    public ShoeSizeDto getByName(String name) {
        for (ShoeSizeTypes type : ShoeSizeTypes.values()) {
            if (type.toString().equals(name)) {
                ShoeSize shoeSize = entityManager.createQuery("" +
                        "FROM ShoeSizeDto AS shoeSize" +
                        "WERE shoeSize.typename = :typename", ShoeSize.class)
                        .setParameter("typename", ShoeSizeTypes.valueOf(name))
                        .getSingleResult();

                return new ShoeSizeDto(shoeSize);
            }
        }
        return null;
    }

    @Override
    public ShoeSizeDto getShoeSizeDtoById(Long id) {
        return entityManager.createQuery("" +
                "SELECT NEW jm.stockx.dto.ShoeSizeDto(" +
                "ss.id," +
                "ss.size," +
                "ss.sizeTypes)" +
                "FROM ShoeSize AS ss " +
                "WHERE ss.id =: id", ShoeSizeDto.class)
                .setParameter("id", id)
                .getSingleResult();
    }
}
