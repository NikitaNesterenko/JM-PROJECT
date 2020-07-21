package jm.stockx;

import jm.stockx.entity.Role;

import java.util.List;

public interface RoleService {
    List<Role> getAllRoles();
    Role getRoleById(Long id);
    void createRole(Role role);
    void deleteRole(Long id);
    void updateRole(Role role);
}
