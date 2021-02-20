package jm.stockx.api.dao;

import jm.stockx.dto.ItemSizeDto;
import jm.stockx.dto.shoesize.ShoeSizeDto;
import jm.stockx.entity.ItemSize;
import jm.stockx.enums.ItemSizeTypes;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class ItemSizeDaoImpl extends AbstractDAO<ItemSize, Long> implements ItemSizeDAO {

    @Override
    public ShoeSizeDto getShoeSizeDtoByShoeSizeName(String name) {
        for (ItemSizeTypes type : ItemSizeTypes.values()) {
            if (type.toString().equals(name)) {
                return entityManager.createQuery("" +
                        "SELECT NEW jm.stockx.dto.shoesize.ShoeSizeDto(" +
                        "ss.id," +
                        "ss.size," +
                        "ss.sizeTypes)" +
                        "FROM ItemSize AS ss " +
                        "WHERE ss.sizeTypes =: typename", ShoeSizeDto.class)
                        .setParameter("typename", ItemSizeTypes.valueOf(name))
                        .getSingleResult();
            }
        }
        return entityManager.createQuery("SELECT count(ss) FROM ItemSize AS ss WHERE ss.sizeTypes = : name", ShoeSizeDto.class)
                .setParameter("name", name)
                .setMaxResults(1)
                .getSingleResult();
    }

    @Override
    public ShoeSizeDto getShoeSizeDtoByShoeSizeId(Long id) {
        return entityManager.createQuery("" +
                "SELECT NEW jm.stockx.dto.shoesize.ShoeSizeDto(" +
                "ss.id," +
                "ss.size," +
                "ss.sizeTypes)" +
                "FROM ItemSize AS ss " +
                "WHERE ss.id =: id", ShoeSizeDto.class)
                .setParameter("id", id)
                .getSingleResult();
    }

    @Override
    public List<ItemSizeDto> getShoeSizeDtoByShoeSizeType(ItemSizeTypes sizeType) {
        return entityManager.createQuery(
                "FROM ShoeSize AS ss WHERE ss.sizeTypes =: sizeType",
                ItemSizeDto.class)
                .setParameter("sizeType", sizeType)
                .getResultList();
    }

    public ItemSizeDto findOneDtoBySizeName(String itemSize) {
        return entityManager.createQuery("" +
                        "SELECT itemSize " +
                        "FROM ItemSize itemSize WHERE itemSize.size = :itemSize"
                , ItemSizeDto.class)
                .setParameter("itemSize", itemSize)
                .getSingleResult();
    }

    public ItemSize findOneBySizeName(String itemSize) {
        return entityManager.createQuery("" +
                        "SELECT itemSize " +
                        "FROM ItemSize itemSize WHERE itemSize.size = :itemSize"
                , ItemSize.class)
                .setParameter("itemSize", itemSize)
                .getSingleResult();
    }
}
