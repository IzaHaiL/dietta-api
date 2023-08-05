//package com.sugadev.userservice.Repository;
//
//import com.sugadev.userservice.Model.UserRole;
//import org.junit.Test;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
//import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
//import org.springframework.test.annotation.Rollback;
//
//import java.util.List;
//
//import static org.junit.Assert.assertEquals;
//
//@DataJpaTest
//@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
//@Rollback(false)
//public class UserRoleRepositoryTest {
//    @Autowired
//    private RoleRepository repo;
//
//    @Test
//    public void testCreateRoles() {
//        UserRole admin = new UserRole("ROLE_ADMIN");
//        UserRole user = new UserRole("ROLE_USER");
//
//        repo.saveAll(List.of(admin,user));
//
//        long count = repo.count();
//        assertEquals(2, count);
//    }
//
//}
