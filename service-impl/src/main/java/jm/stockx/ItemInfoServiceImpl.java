package jm.stockx;

import jm.stockx.entity.ItemInfo;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class ItemInfoServiceImpl implements ItemInfoService {

    public ItemInfo getItemInfoByItemId(Long ItemId) {
        return null;
    }
}
