package jm.stockx;

import jm.stockx.entity.Role;

import java.util.List;

public interface RoleService {
    List<Role> getAll();
    Role getRoleByRoleId(Long id);
    void create(Role role);
    void delete(Long id);
    void update(Role role);
    Role getRoleByRoleName(String roleName);
    boolean isRoleExist(Long id);
    Role getRole(String name);
}
