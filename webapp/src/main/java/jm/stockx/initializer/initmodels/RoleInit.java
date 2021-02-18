package jm.stockx.initializer.initmodels;

import jm.stockx.RoleService;
import jm.stockx.entity.Role;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.Arrays;
import java.util.List;

/**
 * Класс для создания ролей в рамках DataInitializer.
 */
@Component
@RequiredArgsConstructor
public class RoleInit {
    private final RoleService roleService;

    public void initializeRoles() {
        List<Role> roleListForCreation = createRolesForInitialization();
        roleListForCreation.forEach(roleService::create);
    }

    private List<Role> createRolesForInitialization() {
        return Arrays.asList(
                new Role("ROLE_ADMIN"),
                new Role("ROLE_USER"));
    }
}