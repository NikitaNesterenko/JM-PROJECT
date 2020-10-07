package jm.stockx;

import jm.stockx.dto.RoleDto;
import jm.stockx.entity.Brand;
import jm.stockx.entity.Role;

import java.util.List;
import java.util.Set;

public interface RoleService {
    Set<Role> getAll();
    RoleDto get(Long id);
    void create(Role role);
    void delete(Long id);
    void update(Role role);
    RoleDto getByRoleName(String roleName);
    boolean isRoleExist(Long id);
    Role getRole(String name);
}
