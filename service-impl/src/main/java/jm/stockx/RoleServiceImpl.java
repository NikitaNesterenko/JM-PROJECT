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
    public Role get(Long id) {
        return roleDAO.getById(id);
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
    public Role getByRoleName(String roleName) {
        return roleDAO.getByName(roleName).orElse(null);
    }

    @Override
    public Role getRoleByName(String name) {
        return roleDAO.getByName(name).orElse(null);
    }

    @Override
    public boolean isRoleExist(Long id) {
        return roleDAO.doesItExistEntity(id);
    }
}
