package jm.stockx.api.dao;

import jm.stockx.dto.security.role.RoleDto;
import jm.stockx.entity.Role;

public interface RoleDAO extends GenericDao<Role, Long> {
    RoleDto getRoleDtoByRoleName(String name);

    RoleDto getRoleDtoByRoleId(Long id);

    Role getRoleByName(String name);
}
