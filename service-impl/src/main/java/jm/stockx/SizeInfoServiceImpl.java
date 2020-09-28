package jm.stockx;

import jm.stockx.api.dao.SizeInfoDao;
import jm.stockx.entity.SizeInfo;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class SizeInfoServiceImpl implements SizeInfoService {

    private final SizeInfoDao sizeInfoDao;

    public SizeInfoServiceImpl(SizeInfoDao sizeInfoDao) {
        this.sizeInfoDao = sizeInfoDao;
    }

    @Override
    public SizeInfo get(Long id) {
        return null;
    }
}
