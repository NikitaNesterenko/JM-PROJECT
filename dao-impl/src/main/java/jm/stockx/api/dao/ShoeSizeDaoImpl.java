package jm.stockx.api.dao;

import jm.stockx.dto.ShoeSizeDto;
import jm.stockx.entity.ShoeSize;
import jm.stockx.enums.ShoeSizeTypes;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class ShoeSizeDaoImpl extends AbstractDAO<ShoeSize, Long> implements ShoeSizeDAO {

    @Override
    public ShoeSizeDto getShoeSizeDtoByName(String name) {
        for (ShoeSizeTypes type : ShoeSizeTypes.values()) {
            if (type.toString().equals(name)) {
                return entityManager.createQuery("" +
                        "SELECT NEW jm.stockx.dto.ShoeSizeDto(" +
                        "ss.id," +
                        "ss.size," +
                        "ss.sizeTypes)" +
                        "FROM ShoeSize AS ss " +
                        "WHERE ss.sizeTypes =: typename", ShoeSizeDto.class)
                        .setParameter("typename", ShoeSizeTypes.valueOf(name))
                        .getSingleResult();
            }
        }
        return entityManager.createQuery("SELECT count(ss) FROM ShoeSize AS ss WHERE ss.sizeTypes = : name", ShoeSizeDto.class)
                .setParameter("name", name)
                .setMaxResults(1)
                .getSingleResult();
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

    @Override
    public List<ShoeSize> getShoeSizeDtoBySizeType(ShoeSizeTypes sizeType) {
        return entityManager.createQuery(
                "FROM ShoeSize AS ss WHERE ss.sizeTypes =: type",
                ShoeSize.class)
                .setParameter("type", sizeType)
                .getResultList();
    }
}
