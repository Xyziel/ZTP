package ztp.shelter.model.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.relational.core.sql.In;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import ztp.shelter.model.entity.Role;
import ztp.shelter.model.entity.User;

import javax.transaction.Transactional;
import java.util.List;


@Repository
public interface UserRepo extends JpaRepository<User,Integer>
{


    User findUserByEmail(String email);
    User findUserByIdUser(Integer idUser);
    void deleteById(Integer integer);

    @Query(value = "SELECT * FROM users where role_id != 3",nativeQuery = true)
    List<User> findAllWithoutAdminRole();

    @Query(value="UPDATE users set password=:password where email=:email", nativeQuery = true)
    @Modifying
    @Transactional
    void updateUserPasswordByEmail(@Param("password") String password, @Param("email") String email);

    @Modifying
    @Transactional
    void removeUserByIdUser(Integer idUser);

    //    @Query(value = "Select * from users where users.email=:email", nativeQuery = true)
//    public User findUserByEmail(@Param("email") String email);

//    public User findUserByEmail(String email);



}
