package jm.stockx;

import jm.stockx.api.dao.RoleDAO;
import jm.stockx.entity.Role;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class RoleServiceImpl implements RoleService {

    private final RoleDAO roleDAO;

    @Autowired
    public RoleServiceImpl(RoleDAO roleDAO) {
        this.roleDAO = roleDAO;
    }

    @Override
    public List<Role> getAll() {
        return roleDAO.getAll();
    }

    @Override
    public Role getRoleByRoleId(Long id) {
        return roleDAO.getRoleByRoleId(id);
    }

    @Override
    public void create(Role role) {
        roleDAO.add(role);
    }

    @Override
    public void delete(Long id) {
        roleDAO.deleteById(id);
    }

    @Override
    public void update(Role role) {
        roleDAO.update(role);
    }

    @Override
    public Role getRoleByRoleName(String roleName) {
        return roleDAO.getRoleByRoleName(roleName);
    }

    @Override
    public boolean isRoleExist(Long id) {
        return roleDAO.doesItExistEntity(id);
    }

    @Override
    public Role getRole(String name) {
        return roleDAO.getRoleByName(name);
    }
}
