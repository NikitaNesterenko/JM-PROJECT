package jm.stockx;

import jm.stockx.entity.ItemForPortfolio;

import java.util.List;

public interface ItemForPortfolioService {
    ItemForPortfolio getById(Long id);
    void add(ItemForPortfolio itemForPortfolio);
    void deleteById(Long id);
    List<ItemForPortfolio> findAllByUserPortfolioId(Long id);
}