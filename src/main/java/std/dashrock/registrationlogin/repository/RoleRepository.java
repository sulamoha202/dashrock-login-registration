package std.dashrock.registrationlogin.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import std.dashrock.registrationlogin.entity.Role;


@Repository
public interface RoleRepository extends JpaRepository<Role, Long> {
	Role findByName(String name);
}
