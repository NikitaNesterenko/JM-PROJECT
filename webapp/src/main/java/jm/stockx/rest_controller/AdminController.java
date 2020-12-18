package jm.stockx.rest_controller;

import jm.stockx.ItemAdminDtoException;
import jm.stockx.ItemAdminService;
import jm.stockx.MailService;
import jm.stockx.dto.item.ItemDtoAdmin;
import jm.stockx.util.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/admin")
public class AdminController {
    private ItemAdminService itemAdminService;
    private MailService mailService;

    @Autowired
    public AdminController(ItemAdminService itemAdminService, MailService mailService) {
        this.mailService = mailService;
        this.itemAdminService = itemAdminService;
    }

    @PostMapping("/add/item")
    public Response<?> addItem(@RequestBody ItemDtoAdmin itemDtoAdmin) throws ItemAdminDtoException {
        itemAdminService.addAdminItemInfo(itemDtoAdmin);
        return Response.ok().build();
    }

    @PostMapping("/add/listitem")
    public Response<?> addListItem(@RequestBody List<ItemDtoAdmin> listItemDtoAdmin) throws ItemAdminDtoException {
        itemAdminService.addAdminListItemInfo(listItemDtoAdmin);
        return Response.ok().build();
    }


}
