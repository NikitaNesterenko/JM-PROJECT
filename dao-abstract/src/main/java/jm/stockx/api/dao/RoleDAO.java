package jm.stockx.api.dao;

import jm.stockx.entity.Role;

import java.util.Optional;

public interface RoleDAO extends GenericDao<Role, Long> {
    Optional<Role> getByName(String name);
}
