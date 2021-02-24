package jm.stockx.api.dao;

import jm.stockx.entity.Role;

public interface RoleDAO extends GenericDao<Role, Long> {
    Role getRoleByRoleName(String name);

    Role getRoleByRoleId(Long id);

    Role getRoleByName(String name);
}
