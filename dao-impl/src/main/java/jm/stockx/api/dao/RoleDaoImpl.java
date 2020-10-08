package jm.stockx.api.dao;

import jm.stockx.dto.security.role.RoleDto;
import jm.stockx.entity.Role;
import org.springframework.stereotype.Repository;

@Repository
public class RoleDaoImpl extends AbstractDAO<Role, Long> implements RoleDAO {

    @Override
    public RoleDto getRoleDtoByRoleName(String name) {
        return entityManager.createQuery("" +
                "SELECT NEW jm.stockx.dto.security.role.RoleDto(" +
                "r.id, " +
                "r.roleName)" +
                "FROM Role AS r " +
                "WHERE r.roleName =: name", RoleDto.class)
                .setParameter("name", name)
                .getSingleResult();
    }

    @Override
    public RoleDto getRoleDtoByRoleId(Long id) {
        return entityManager.createQuery("" +
                "SELECT NEW jm.stockx.dto.security.role.RoleDto(" +
                "r.id, " +
                "r.roleName)" +
                "FROM Role AS r " +
                "WHERE r.id =: id", RoleDto.class)
                .setParameter("id", id)
                .getSingleResult();
    }

    @Override
    public Role getRoleByName(String name) {
        return entityManager.createQuery("" +
                "FROM Role AS r WHERE r.roleName =: name", Role.class)
                .setParameter("name", name)
                .getSingleResult();
    }

}
