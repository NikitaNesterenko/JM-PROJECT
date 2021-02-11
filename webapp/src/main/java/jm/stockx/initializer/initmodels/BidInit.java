package jm.stockx.initializer.initmodels;

import jm.stockx.BidException;
import jm.stockx.BidService;
import jm.stockx.ItemInfoService;
import jm.stockx.dto.bid.BidPostDto;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import org.springframework.stereotype.Component;

import java.util.Arrays;
import java.util.List;

/**
 * Класс для создания вещи в рамках DataInitializer.
 */
@Component
@RequiredArgsConstructor
public class BidInit {
    private final ItemInfoService itemInfoService;
    private final BidService bidService;

    public void initializeBids() {
        List<BidPostDto> itemsForCreation = createBidsForInitialization();
        itemsForCreation.forEach(this::accept);
    }

    @SneakyThrows
    private List<BidPostDto> createBidsForInitialization() {
        BidPostDto adidasYeezyBoost380MistBid = BidPostDto.builder()
                .id(1L)
                .price(itemInfoService.get(1L).getHighestBid().toString())
                .success(true)
                .itemInfoId(1L)
                .userId(4L)
                .build();

        BidPostDto converseAllStar70sHiKithxCocaColaBid = BidPostDto.builder()
                .id(2L)
                .price(itemInfoService.get(2L).getHighestBid().toString())
                .success(true)
                .itemInfoId(2L)
                .userId(1L)
                .build();

        BidPostDto louisVuittonDonKanyeRedBid = BidPostDto.builder()
                .id(3L)
                .price(itemInfoService.get(6L).getHighestBid().toString())
                .success(true)
                .itemInfoId(6L)
                .userId(1L)
                .build();

        BidPostDto newBalance990v3JJJJoundBid = BidPostDto.builder()
                .id(4L)
                .price(itemInfoService.get(7L).getHighestBid().toString())
                .success(true)
                .itemInfoId(7L)
                .userId(2L)
                .build();

        BidPostDto sauconyAzuraBodegaLucky13Bid = BidPostDto.builder()
                .id(5L)
                .price(itemInfoService.get(9L).getHighestBid().toString())
                .success(true)
                .itemInfoId(9L)
                .userId(3L)
                .build();

        return Arrays.asList(
                adidasYeezyBoost380MistBid,
                converseAllStar70sHiKithxCocaColaBid,
                louisVuittonDonKanyeRedBid,
                newBalance990v3JJJJoundBid,
                sauconyAzuraBodegaLucky13Bid
        );
    }

    private void accept(BidPostDto bidPostDto) {
        try {
            bidService.placeBid(bidPostDto);
        } catch (BidException e) {
            e.printStackTrace();
        }
    }
}