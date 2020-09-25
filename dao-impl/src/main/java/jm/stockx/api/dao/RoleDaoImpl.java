package jm.stockx.api.dao;

import jm.stockx.dto.RoleDto;
import jm.stockx.entity.Role;
import org.springframework.stereotype.Repository;

@Repository
public class RoleDaoImpl extends AbstractDAO<Role, Long> implements RoleDAO {

    @Override
    public RoleDto getByName(String name) {
        Role role = entityManager.createQuery("" +
                "FROM Role AS r " +
                "WHERE r.roleName = :roleName", Role.class)
                .setParameter("roleName", name)
                .getSingleResult();
        return new RoleDto(role);
    }

    @Override
    public RoleDto getRoleDtoById(Long id) {
        return entityManager.createQuery("" +
                "SELECT NEW jm.stockx.dto.RoleDto(" +
                "r.id, " +
                "r.roleName)" +
                "FROM Role AS r " +
                "WHERE id =: id", RoleDto.class)
                .setParameter("id", id)
                .getSingleResult();
    }

}
