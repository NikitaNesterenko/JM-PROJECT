package jm.stockx.api.dao;

import jm.stockx.entity.ShoeSize;
import jm.stockx.entity.Style;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public class ShoeSizeDaoImpl extends AbstractDAO<ShoeSize, Long> implements ShoeSizeDAO {
    @Override
    public Optional<ShoeSize> getByName(String name) {
        return Optional.empty();
    }
}
