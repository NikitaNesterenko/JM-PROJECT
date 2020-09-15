package jm.stockx.api.dao;

import jm.stockx.dto.ShoeSizeDto;
import jm.stockx.entity.ShoeSize;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public class ShoeSizeDaoImpl extends AbstractDAO<ShoeSize, Long> implements ShoeSizeDAO {

    @Override
    public Optional<ShoeSize> getByName(String name) {
        return Optional.empty();
    }

    @Override
    public ShoeSizeDto getShoeSizeDtoById(Long id) {
        return entityManager.createQuery("" +
                "SELECT NEW jm.stockx.dto.ShoeSizeDto(" +
                "ss.id," +
                "ss.size," +
                "ss.sizeTypes)" +
                "FROM ShoeSize AS ss left join fetch ss.items WHERE ss.id = :id", ShoeSizeDto.class)
                .setParameter("id", id)
                .getSingleResult();
    }
}
