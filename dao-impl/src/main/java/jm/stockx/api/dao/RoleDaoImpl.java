package jm.stockx.api.dao;

import jm.stockx.entity.Role;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
@Transactional
public class RoleDaoImpl extends AbstractDAO<Role, Long> implements RoleDAO {
}
