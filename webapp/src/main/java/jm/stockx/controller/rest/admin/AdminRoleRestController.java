package jm.stockx.controller.rest.admin;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import jm.stockx.RoleService;
import jm.stockx.entity.Role;
import jm.stockx.util.Response;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

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
        if (roleService.getRoleByName(roleName) != null) {
            log.warn("Роль {} уже существует в базе", roleName);
            return Response.error(HttpStatus.BAD_REQUEST,"This role already exists in database");
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
    public Response<?> updateRole(@RequestBody Role role) {
        String roleName = role.getRoleName();
        if (roleService.getRoleByName(roleName) == null) {
            log.warn("Роль {} в базе не найден", roleName);
            return Response.error(HttpStatus.BAD_REQUEST,"Role not found");
        }
        roleService.update(role);
        log.info("Роль {} успешно обновлен", roleName);
        return Response.ok(role);
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
    public Response<?> deleteRole(@PathVariable("id") Long id) {
        if (roleService.get(id) == null) {
            log.warn("Роль с id = {} в базе не найден", id);
            return Response.error(HttpStatus.BAD_REQUEST,"Role not found");
        }
        roleService.delete(id);
        log.info("Роль с id = {} успешно удален", id);
        return Response.ok().build();
    }
}
