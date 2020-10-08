package jm.stockx;

import jm.stockx.dto.security.role.RoleDto;
import jm.stockx.entity.Role;

import java.util.List;

public interface RoleService {
    List<Role> getAll();
    RoleDto getRoleDtoByRoleId(Long id);
    void create(Role role);
    void delete(Long id);
    void update(Role role);
    RoleDto getRoleDtoByRoleName(String roleName);
    boolean isRoleExist(Long id);
    Role getRole(String name);
}
