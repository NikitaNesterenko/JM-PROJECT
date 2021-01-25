package jm.stockx;

import jm.stockx.api.dao.RecommendedItemsDAO;
import jm.stockx.dto.recommendedItem.RecommendedItemDto;
import jm.stockx.enums.ItemCategory;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class RecommendedItemsServiceImpl implements RecommendedItemsService{

    private final RecommendedItemsDAO recommendedItemsDAO;

    @Autowired
    public RecommendedItemsServiceImpl(RecommendedItemsDAO recommendedItemsDAO) {
        this.recommendedItemsDAO = recommendedItemsDAO;
    }

    @Override
    public List<RecommendedItemDto> get15RecommendedItems(ItemCategory itemCategory) {
        return recommendedItemsDAO.get15RecommendedItems(itemCategory);
    }
}
