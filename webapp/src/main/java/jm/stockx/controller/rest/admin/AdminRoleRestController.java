package jm.stockx.controller.rest.admin;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import jm.stockx.RoleService;
import jm.stockx.dto.RolePutDto;
import jm.stockx.entity.Role;
import jm.stockx.util.Response;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/rest/api/admin/role")
@Tag(name = "role", description = "Role API")
@Slf4j
public class AdminRoleRestController {
    private final RoleService roleService;

    @Autowired
    public AdminRoleRestController(RoleService roleService) {
        this.roleService = roleService;
    }


    @PostMapping
    @Operation(
            operationId = "createRole",
            summary = "Create role",
            responses = {
                    @ApiResponse(responseCode = "200",
                            content = @Content(
                                    mediaType = "application/json",
                                    schema = @Schema(implementation = Role.class)
                            ),
                            description = "OK: role created"
                    ),
                    @ApiResponse(responseCode = "400", description = "BAD_REQUEST: role was not created")
            })
    public Response<?> createRole(@RequestBody Role role) {
        String roleName = role.getRoleName();
        if (roleService.isRoleExist(role.getId())) {
            log.warn("Роль {} уже существует в базе", roleName);
            return Response.error(HttpStatus.BAD_REQUEST, "This role already exists in database");
        }
        roleService.create(role);
        log.info("Роль {} успешно создан", roleName);
        return Response.ok(role);
    }

    @PutMapping
    @Operation(
            operationId = "updateRole",
            summary = "Update role",
            responses = {
                    @ApiResponse(responseCode = "200",
                            content = @Content(
                                    mediaType = "application/json",
                                    schema = @Schema(implementation = Role.class)
                            ),
                            description = "OK: role updated successfully"
                    ),
                    @ApiResponse(responseCode = "400", description = "BAD_REQUEST: unable to update role")
            })
    public Response<?> updateRole(@RequestBody RolePutDto rolePutDto) {
        String roleName = rolePutDto.getName();
        if (roleService.isRoleExist(rolePutDto.getId())) {
            Role roleUpdate = new Role(rolePutDto.getId(), rolePutDto.getName());
            roleService.update(roleUpdate);
            log.info("Роль {} успешно обновлен", roleName);
            return Response.ok(rolePutDto);
        }
        log.warn("Роль {} в базе не найден", roleName);
        return Response.error(HttpStatus.BAD_REQUEST, "Role not found");
    }

    @DeleteMapping(value = "/{id}")
    @Operation(
            operationId = "deleteRole",
            summary = "Delete role",
            responses = {
                    @ApiResponse(responseCode = "200", description = "OK: role deleted successfully"),
                    @ApiResponse(responseCode = "400", description = "BAD_REQUEST: no role with such id")
            }
    )
    public Response<?> deleteRole(@PathVariable Long id) {
        if (roleService.isRoleExist(id)) {
            roleService.delete(id);
            log.info("Роль с id = {} успешно удалена", id);
            return Response.ok().build();
        }
        log.warn("Роль с id = {} в базе не найдена", id);
        return Response.error(HttpStatus.BAD_REQUEST, "Role not found");
    }
}
