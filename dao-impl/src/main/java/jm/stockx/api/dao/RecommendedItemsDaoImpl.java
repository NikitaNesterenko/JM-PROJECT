package jm.stockx.api.dao;

import jm.stockx.dto.recommendedItem.RecommendedItemDto;
import jm.stockx.entity.ItemInfo;
import jm.stockx.enums.ItemCategory;
import org.springframework.stereotype.Repository;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

@Repository
public class RecommendedItemsDaoImpl extends AbstractDAO<ItemInfo, Long> implements RecommendedItemsDAO {

    @Override
    public List<RecommendedItemDto> get15RecommendedItems(ItemCategory itemCategory) {

        List<Long> idsList = entityManager.createQuery(
                "SELECT info.item.id " +
                        "FROM ItemInfo info " +
                        "WHERE info.itemCategory = :category")
                .setParameter("category", itemCategory)
                .getResultList();

        Map<Long, Long> idsAndQuantities = new HashMap<>();

        idsList.forEach(e -> {
            if (idsAndQuantities.containsKey(e)) {
                idsAndQuantities.put(e, idsAndQuantities.get(e) + 1L);
            } else if (!idsAndQuantities.containsKey(e)) {
                idsAndQuantities.put(e, 1L);
            }
        });

        List<RecommendedItemDto> recommendedItems = new LinkedList<>();

        idsAndQuantities.entrySet().stream()
                .sorted(Map.Entry.<Long, Long>comparingByValue().reversed())
                .limit(15)
                .forEach(e -> {

                    List<RecommendedItemDto> buffer = entityManager.createQuery(
                            "SELECT NEW jm.stockx.dto.recommendedItem.RecommendedItemDto" +
                                    "(info.item.id, info.brand.name, info.item.name, info.itemImageUrl) " +
                                    "FROM ItemInfo info " +
                                    "WHERE info.item.id = :id")
                            .setParameter("id", e.getKey())
                            .setMaxResults(1)
                            .getResultList();

                    recommendedItems.addAll(buffer);

                });

        return recommendedItems;
    }

}