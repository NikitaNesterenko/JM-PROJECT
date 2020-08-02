package jm.stockx.api.dao;

import jm.stockx.entity.Role;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public class RoleDaoImpl extends AbstractDAO<Role, Long> implements RoleDAO {

    @Override
    public Optional<Role> getByName(String name) {
        String query = "" +
                "FROM Role AS r " +
                "WHERE r.roleName = :roleName";
        Role role = entityManager.createQuery(query, Role.class)
                .setParameter("roleName", name)
                .getSingleResult();

        return Optional.of(role);
    }
}
