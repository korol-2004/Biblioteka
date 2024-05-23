package by.mycompany.restapilibrary.config;

import by.mycompany.restapilibrary.model.User;
import by.mycompany.restapilibrary.repository.UserRepository;
import by.mycompany.restapilibrary.service.UserService;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class DatabaseInitializer {

    @Autowired
    private UserService userRoleService;

    @Autowired
    private UserRepository userRoleRepository;

    public boolean checkIfUserIsAdmin() {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        return auth.getAuthorities().stream()
                .anyMatch(a -> a.getAuthority().equals("ROLE_ADMIN"));
    }
    @Bean
    public CommandLineRunner initDatabase() {
        return args -> {
            if (userRoleRepository.findByUsername("user") == null){
                User user = new User();
                user.setUsername("user");
                user.setPassword("1111");
                user.setRole("ROLE_USER");
                userRoleService.saveUser(user);
            }

            if (userRoleRepository.findByUsername("admin") == null){
                User admin = new User();
                admin.setUsername("admin");
                admin.setPassword("admin");
                admin.setRole("ROLE_ADMIN");
                userRoleService.saveUser(admin);
            }
        };
    }
}
