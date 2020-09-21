package jm.stockx.api.dao;

import jm.stockx.dto.RoleDto;
import jm.stockx.entity.Role;

public interface RoleDAO extends GenericDao<Role, Long> {
    RoleDto getRoleDtoByName(String name);

    RoleDto getRoleDtoById(Long id);

    Role getRoleByName(String name);
}
