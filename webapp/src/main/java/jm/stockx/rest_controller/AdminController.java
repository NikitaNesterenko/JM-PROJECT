package jm.stockx.rest_controller;

import jm.stockx.FileStorageService;
import jm.stockx.ItemAdminService;
import jm.stockx.dto.item.ItemDtoAdmin;
import jm.stockx.util.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;


import java.util.List;

@RestController
@RequestMapping("/api/admin")
public class AdminController {
    private final ItemAdminService itemAdminService;

    private final FileStorageService fileStorageService;

    @Autowired
    public AdminController(ItemAdminService itemAdminService, FileStorageService fileStorageService) {
        this.itemAdminService = itemAdminService;
        this.fileStorageService = fileStorageService;
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

    @PostMapping("/util")
    public String uploadMainPicture(MultipartFile file) {
        return fileStorageService.storeFile(file);
    }
}