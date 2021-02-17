package jm.stockx.api.dao;

import jm.stockx.entity.Role;
import org.springframework.stereotype.Repository;

@Repository
public class RoleDaoImpl extends AbstractDAO<Role, Long> implements RoleDAO {

    @Override
    public Role getRoleByRoleName(String name) {
        return entityManager.createQuery("" +
                "SELECT NEW jm.stockx.dto.security.role.RoleDto(" +
                "r.id, " +
                "r.roleName)" +
                "FROM Role AS r " +
                "WHERE r.roleName =: name", Role.class)
                .setParameter("name", name)
                .getSingleResult();
    }

    @Override
    public Role getRoleByRoleId(Long id) {
        return entityManager.createQuery("" +
                "SELECT NEW jm.stockx.dto.security.role.RoleDto(" +
                "r.id, " +
                "r.roleName)" +
                "FROM Role AS r " +
                "WHERE r.id =: id", Role.class)
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
