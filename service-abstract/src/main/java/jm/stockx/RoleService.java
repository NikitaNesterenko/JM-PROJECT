package jm.stockx;

import jm.stockx.entity.Role;

import java.util.List;

public interface RoleService {
    List<Role> getAll();
    Role get(Long id);
    void create(Role role);
    void delete(Long id);
    void update(Role role);
}