package jm.stockx.api.dao;

import jm.stockx.entity.Role;

import java.util.List;

public interface RoleDAO {
    List<Role> getAll();
    void add(Role role);
    Role getById(Long id);
    void deleteById(Long id);
    Role merge(Role role);
}
