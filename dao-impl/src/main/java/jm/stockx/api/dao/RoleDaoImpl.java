package jm.stockx.api.dao;

import jm.stockx.entity.Role;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Repository
@Transactional
public class RoleDaoImpl extends AbstractDAO<Role, Long> implements RoleDAO {

    @Override
    public Optional<Role> getByName(String name) {
        Role role = entityManager.createQuery(
                "FROM Role " +
                "WHERE name = :roleName", Role.class)
                .setParameter("roleName", name)
                .getSingleResult();
        return Optional.of(role);
    }

}
