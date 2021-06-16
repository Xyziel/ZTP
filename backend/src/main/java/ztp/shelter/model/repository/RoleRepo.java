package ztp.shelter.model.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ztp.shelter.model.entity.Role;

@Repository
public interface RoleRepo extends JpaRepository<Role,Integer>
{
    Role findRoleByIdRole(Integer roleId);
    Role findRoleByName(String name);
}
