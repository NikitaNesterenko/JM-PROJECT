package jm.stockx;

import jm.stockx.api.dao.ItemForPortfolioDaoImpl;
import jm.stockx.entity.ItemForPortfolio;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class ItemForPortfolioServiceImpl implements ItemForPortfolioService{

    private ItemForPortfolioDaoImpl itemForPortfolioDao;

    @Autowired
    public ItemForPortfolioServiceImpl(ItemForPortfolioDaoImpl itemForPortfolioDao) {
        this.itemForPortfolioDao = itemForPortfolioDao;
    }

    @Override
    public ItemForPortfolio getById(Long id) {
        return itemForPortfolioDao.getById(id);
    }

    @Override
    @Transactional
    public void add(ItemForPortfolio itemForPortfolio) {
          itemForPortfolioDao.add(itemForPortfolio);
    }

    @Override
    @Transactional
    public void deleteById(Long id) {
        itemForPortfolioDao.deleteById(id);
    }

    @Override
    public List<ItemForPortfolio> findAllByUserPortfolioId(Long id) {
        return null;
    }
}
