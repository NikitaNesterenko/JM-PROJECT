package jm.stockx.rest_controller;

import jm.stockx.ItemAdminService;
import jm.stockx.MailService;
import jm.stockx.dto.item.ItemDtoAdmin;
import jm.stockx.util.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/admin")
public class AdminController {
    private final ItemAdminService itemAdminService;

    // TODO: Не используется. Потенциально можено убрать.
    private MailService mailService;

    @Autowired
    public AdminController(ItemAdminService itemAdminService, MailService mailService) {
        this.mailService = mailService;
        this.itemAdminService = itemAdminService;
    }

    @PostMapping("/add/item")
    public Response<Void> addItem(@RequestBody ItemDtoAdmin itemDtoAdmin) {
        itemAdminService.addAdminItemInfo(itemDtoAdmin);
        return Response.ok().build();
    }

    @PostMapping("/add/listitem")
    public Response<Void> addListItem(@RequestBody List<ItemDtoAdmin> listItemDtoAdmin) {
        itemAdminService.addAdminListItemInfo(listItemDtoAdmin);
        return Response.ok().build();
    }
}