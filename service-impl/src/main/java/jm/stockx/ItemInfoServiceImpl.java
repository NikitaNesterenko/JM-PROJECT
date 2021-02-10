package jm.stockx;

import jm.stockx.api.dao.ItemInfoDAO;
import jm.stockx.api.dao.ItemSizeDAO;
import jm.stockx.dto.SizeInfoDto;
import jm.stockx.dto.iteminfo.*;
import jm.stockx.dto.page.PageDto;
import jm.stockx.entity.Brand;
import jm.stockx.entity.ItemInfo;
import jm.stockx.entity.ItemSize;
import jm.stockx.enums.ItemCategory;
import org.joda.money.Money;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;

@Service
@Transactional
public class ItemInfoServiceImpl implements ItemInfoService {

    private final ItemInfoDAO itemInfoDAO;
    private final ItemSizeDAO itemSizeDAO;

    @Autowired
    public ItemInfoServiceImpl(ItemInfoDAO itemInfoDAO, ItemSizeDAO itemSizeDAO) {
        this.itemInfoDAO = itemInfoDAO;
        this.itemSizeDAO = itemSizeDAO;
    }

    @Override
    public List<ItemInfo> getAllNews() {
        return itemInfoDAO.getAll();
    }

    @Override
    public ItemInfo get(Long id) {
        return itemInfoDAO.getById(id);
    }

    @Override
    public void create(ItemInfo itemInfo) {
        itemInfoDAO.add(itemInfo);
    }

    @Override
    public void update(ItemInfo itemInfo) {
        itemInfoDAO.update(itemInfo);
    }

    @Override
    public void delete(Long id) {
        itemInfoDAO.deleteById(id);
    }

    @Override
    public ItemInfo getItemInfoByItemId(Long itemId) {
        return itemInfoDAO.getItemInfoByItemId(itemId);
    }

    @Override
    public ItemInfo getItemInfoByItemName(String itemName) {
        return itemInfoDAO.getItemInfoByItemName(itemName);
    }

    @Override
    public List<ReleaseItemInfoDto> getReleaseItemDtoByPeriod(LocalDateTime beginningPeriod, LocalDateTime endPeriod) {
        return itemInfoDAO.getReleaseItemDtoByPeriod(beginningPeriod, endPeriod);
    }

    @Override
    public void updateItemImageUrl(Long id, String url) {
        itemInfoDAO.updateItemImageUrl(id, url);
    }

    @Override
    public List<NotReleaseItemInfoDto> getNotReleasedItem() {
        return itemInfoDAO.getNotReleasedItem();
    }

    @Override
    public List<NotReleaseItemInfoDto> getNotReleasedItemsByBrand(Brand brand) {
        return itemInfoDAO.getNotReleasedItemsByBrand(brand);
    }

    @Override
    public PageDto<ItemInfoDto> searchItem(String search, Integer page, Integer size) {
        List<ItemInfoDto> foundItems = itemInfoDAO.searchItem(search, page, size);
        int sizeItems = foundItems.size();
        int totalEntitiesCount = foundItems.size();
        int pageCount = totalEntitiesCount / size;
        return new PageDto<>(sizeItems, page, pageCount,
                size, foundItems);
    }

    @Override
    public ItemInfoDto getItemInfoDtoByItemName(String name) {
        return itemInfoDAO.getItemInfoDtoByItemName(name);
    }

    @Override
    public ItemInfoDto getItemInfoDtoByItemId(Long id) {
        return itemInfoDAO.getItemInfoDtoByItemId(id);
    }

    @Override
    public List<ItemInfoDto> getItemInfoDtoByColor(String itemColors) {
        return itemInfoDAO.getItemInfoDtoByColor(itemColors);
    }

    @Override
    public List<ItemInfoDto> getMostPopularItemByBrandName(String name) {
        return itemInfoDAO.getMostPopularItemByBrandName(name);
    }

    @Override
    public List<ItemInfoDto> getMostPopularItemByStyleId(Long id, int topLimit) {
        return itemInfoDAO.getMostPopularItemByStyleId(id, topLimit);
    }

    @Override
    public List<ItemInfoCardDto> getItemInfoCardDtoByItemCategory(ItemCategory category) {
        return itemInfoDAO.getItemInfoCardDtoByItemCategory(category);
    }

    @Override
    public List<ItemInfoCardDto> getItemInfoCardDtoMorePrice(Money price) {
        return itemInfoDAO.getItemInfoCardDtoMorePrice(price);
    }

    @Override
    public List<ItemSearchDto> getItemSearchDtoBySearch(String search) {
        return itemInfoDAO.getItemSearchDtoBySearch(search);
    }

    public SizeInfoDto getItemInfoDtoByIdAndSize(Long itemId, String itemSize) {
        ItemSize sizeFromDb = itemSizeDAO.findOneBySizeName(itemSize);
        return itemInfoDAO.getItemInfoDtoByIdAndSize(itemId, sizeFromDb);
    }

    @Override
    public List<ItemInfoDto> getAllItemInfoDto(){
        return itemInfoDAO.getAllItemInfoDto();
    }

    @Override
    public List<ItemInfoDtoDecimal> getAllItemInfoDtoDecimal() {
        return itemInfoDAO.getAllItemInfoDtoDecimal();
    }

    @Override
    public ItemInfoDtoDecimal getItemInfoDtoDec(Long id){
        return itemInfoDAO.getItemInfoDecByItemId(id);
    }

}
