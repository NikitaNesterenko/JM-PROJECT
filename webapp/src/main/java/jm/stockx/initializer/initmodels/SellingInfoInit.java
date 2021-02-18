package jm.stockx.initializer.initmodels;

import jm.stockx.ItemInfoService;
import jm.stockx.SellingInfoService;
import jm.stockx.UserService;
import jm.stockx.entity.SellingInfo;
import jm.stockx.enums.Status;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.List;

/**
 * Класс для создания информации о продажах в рамках DataInitializer.
 */
@Component
@RequiredArgsConstructor
public class SellingInfoInit {
    public static final String ANDREW_RYAN_RAPTURE_US = "andrew.ryan@rapture.us";
    public static final String SASHA_PUSHKIN_GMAIL_COM = "sasha.pushkin@gmail.com";
    public static final String VASYA_PUPKIN_EMAIL_COM = "vasya.pupkin@email.com";
    private final UserService userService;
    private final ItemInfoService itemInfoService;
    private final SellingInfoService sellingInfoService;

//    TODO
//    public void initializeSellingInfos() {
//        List<SellingInfo> sellingInfosForCreation = createSellingInfosForInitialization();
//        sellingInfosForCreation.forEach(sellingInfoService::create);
//    }

//    @SneakyThrows
//    private List<SellingInfo> createSellingInfosForInitialization() {
//        SellingInfo adidasYeezyBoost380MistSI1 = SellingInfo.builder()
//                .user(userService.getUserByEmail("admin@mail.ru"))
//                .itemInfo(itemInfoService.get(1L))
//                .orderNumber(1L)
//                .orderDate(LocalDateTime.of(2021, 2, 10, 12, 20))
//                .status(Status.DELIVERED)
//                .price(itemInfoService.get(1L).getPrice())
//                .build();
//
//        SellingInfo adidasYeezyBoost380MistSI2 = SellingInfo.builder()
//                .user(userService.getUserByEmail(ANDREW_RYAN_RAPTURE_US))
//                .itemInfo(itemInfoService.get(1L))
//                .orderNumber(2L)
//                .orderDate(LocalDateTime.of(2021, 2, 11, 12, 20))
//                .status(Status.DELIVERED)
//                .price(itemInfoService.get(1L).getPrice())
//                .build();
//
//        SellingInfo adidasYeezyBoost380MistSI3 = SellingInfo.builder()
//                .user(userService.getUserByEmail(SASHA_PUSHKIN_GMAIL_COM))
//                .itemInfo(itemInfoService.get(1L))
//                .orderNumber(3L)
//                .orderDate(LocalDateTime.of(2021, 2, 11, 12, 20))
//                .status(Status.DELIVERED)
//                .price(itemInfoService.get(1L).getPrice())
//                .build();
//
//        SellingInfo adidasYeezyBoost380MistSI4 = SellingInfo.builder()
//                .user(userService.getUserByEmail(VASYA_PUPKIN_EMAIL_COM))
//                .itemInfo(itemInfoService.get(1L))
//                .orderNumber(4L)
//                .orderDate(LocalDateTime.of(2021, 2, 7, 12, 20))
//                .status(Status.DELIVERED)
//                .price(itemInfoService.get(1L).getPrice())
//                .build();
//
//        SellingInfo converseAllStar70sHiKithxCocaColaSI1 = SellingInfo.builder()
//                .user(userService.getUserByEmail(ANDREW_RYAN_RAPTURE_US))
//                .itemInfo(itemInfoService.get(2L))
//                .orderNumber(5L)
//                .orderDate(LocalDateTime.of(2021, 1, 11, 12, 20))
//                .status(Status.DELIVERED)
//                .price(itemInfoService.get(2L).getPrice())
//                .build();
//
//
//        SellingInfo converseAllStar70sHiKithxCocaColaSI2 = SellingInfo.builder()
//                .user(userService.getUserByEmail(SASHA_PUSHKIN_GMAIL_COM))
//                .itemInfo(itemInfoService.get(2L))
//                .orderNumber(6L)
//                .orderDate(LocalDateTime.of(2021, 1, 12, 12, 20))
//                .status(Status.DELIVERED)
//                .price(itemInfoService.get(2L).getPrice())
//                .build();
//
//        SellingInfo converseAllStar70sHiKithxCocaColaSI3 = SellingInfo.builder()
//                .user(userService.getUserByEmail(VASYA_PUPKIN_EMAIL_COM))
//                .itemInfo(itemInfoService.get(2L))
//                .orderNumber(7L)
//                .orderDate(LocalDateTime.of(2021, 1, 1, 12, 20))
//                .status(Status.DELIVERED)
//                .price(itemInfoService.get(2L).getPrice())
//                .build();
//
//        SellingInfo jordan1RetroHighSI1 = SellingInfo.builder()
//                .user(userService.getUserByEmail(VASYA_PUPKIN_EMAIL_COM))
//                .itemInfo(itemInfoService.get(3L))
//                .orderNumber(7L)
//                .orderDate(LocalDateTime.of(2021, 1, 1, 12, 20))
//                .status(Status.DELIVERED)
//                .price(itemInfoService.get(3L).getPrice())
//                .build();
//
//        SellingInfo jordan1RetroHighSI2 = SellingInfo.builder()
//                .user(userService.getUserByEmail(SASHA_PUSHKIN_GMAIL_COM))
//                .itemInfo(itemInfoService.get(3L))
//                .orderNumber(8L)
//                .orderDate(LocalDateTime.of(2021, 1, 2, 12, 20))
//                .status(Status.DELIVERED)
//                .price(itemInfoService.get(3L).getPrice())
//                .build();
//
//        SellingInfo louisVuittonDonKanyeRedSI1 = SellingInfo.builder()
//                .user(userService.getUserByEmail(SASHA_PUSHKIN_GMAIL_COM))
//                .itemInfo(itemInfoService.get(6L))
//                .orderNumber(9L)
//                .orderDate(LocalDateTime.of(2021, 1, 4, 12, 20))
//                .status(Status.DELIVERED)
//                .price(itemInfoService.get(6L).getPrice())
//                .build();
//
//        SellingInfo louisVuittonDonKanyeRedSI2 = SellingInfo.builder()
//                .user(userService.getUserByEmail(ANDREW_RYAN_RAPTURE_US))
//                .itemInfo(itemInfoService.get(6L))
//                .orderNumber(10L)
//                .orderDate(LocalDateTime.of(2021, 1, 4, 12, 20))
//                .status(Status.DELIVERED)
//                .price(itemInfoService.get(6L).getPrice())
//                .build();
//
//        SellingInfo newBalance990v3JJJJoundSI1 = SellingInfo.builder()
//                .user(userService.getUserByEmail(ANDREW_RYAN_RAPTURE_US))
//                .itemInfo(itemInfoService.get(7L))
//                .orderNumber(11L)
//                .orderDate(LocalDateTime.of(2021, 1, 5, 12, 20))
//                .status(Status.DELIVERED)
//                .price(itemInfoService.get(7L).getPrice())
//                .build();
//
//        SellingInfo sauconyAzuraBodegaLucky13SI1 = SellingInfo.builder()
//                .user(userService.getUserByEmail(ANDREW_RYAN_RAPTURE_US))
//                .itemInfo(itemInfoService.get(9L))
//                .orderNumber(11L)
//                .orderDate(LocalDateTime.of(2021, 1, 10, 12, 20))
//                .status(Status.DELIVERED)
//                .price(itemInfoService.get(9L).getPrice())
//                .build();
//
//        return List.of(
//                adidasYeezyBoost380MistSI1,
//                adidasYeezyBoost380MistSI2,
//                adidasYeezyBoost380MistSI3,
//                adidasYeezyBoost380MistSI4,
//                converseAllStar70sHiKithxCocaColaSI1,
//                converseAllStar70sHiKithxCocaColaSI2,
//                converseAllStar70sHiKithxCocaColaSI3,
//                jordan1RetroHighSI1,
//                jordan1RetroHighSI2,
//                louisVuittonDonKanyeRedSI1,
//                louisVuittonDonKanyeRedSI2,
//                newBalance990v3JJJJoundSI1,
//                sauconyAzuraBodegaLucky13SI1
//        );
//    }
}