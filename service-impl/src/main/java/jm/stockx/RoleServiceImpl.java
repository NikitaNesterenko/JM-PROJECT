package jm.stockx;

import jm.stockx.api.dao.RoleDAO;
import jm.stockx.dto.RoleDto;
import jm.stockx.entity.Role;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Set;

@Service
@Transactional
public class RoleServiceImpl implements RoleService {

    private final RoleDAO roleDAO;

    @Autowired
    public RoleServiceImpl(RoleDAO roleDAO) {
        this.roleDAO = roleDAO;
    }

    @Override
    public Set<Role> getAll() {
        return roleDAO.getAll();
    }

    @Override
    public RoleDto get(Long id) {
        return roleDAO.getRoleDtoById(id);
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
    public RoleDto getByRoleName(String roleName) {
        return roleDAO.getRoleDtoByName(roleName);
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
