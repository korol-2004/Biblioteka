package by.mycompany.restapilibrary.controller;

import by.mycompany.restapilibrary.repository.UsersRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import by.mycompany.restapilibrary.model.Users;
import by.mycompany.restapilibrary.service.UserService;

import java.util.List;

@Controller
@RequiredArgsConstructor
public class UsersController {

    private final UsersRepository usersRepository;
    private final UserService userService;


    @GetMapping("/users")
    public String users(Model model) {
        model.addAttribute("usersList", usersRepository.findAll());
        model.addAttribute("isAdmin", userService.isAdmin());
        long count = usersRepository.count();
        System.out.println("Users count: " + count); // Исправлено на "Users count"
        model.addAttribute("usersCount", count); // Исправлено на "usersCount"
        return "users";
    }

    @GetMapping("/users/create")
    public String createUser(Model model) {
        model.addAttribute("users", new Users());
        return "createUser";
    }

    @PostMapping("/users/create")
    public String createUsers(@ModelAttribute Users users) {
        usersRepository.save(users);
        return "redirect:/users";
    }

    @GetMapping("/users/delete")
    public String showDeleteForm() {
        return "selectDeleteUser";
    }

    @PostMapping("/users/delete")
    public String deleteUser(@RequestParam("id") Long id) {
        usersRepository.deleteById(id);
        return "redirect:/users";
    }

    @GetMapping("/users/update")
    public String showUpdateForm(Model model) {
        return "selectUpdateUser";
    }

    @GetMapping("/users/update/{id}")
    public String showUpdateForm(@PathVariable("id") Long id, Model model) {
        Users user = usersRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Недопустимый идентификатор пользователя: " + id));
        model.addAttribute("user", user);
        return "updateUser";
    }

    @PostMapping("/users/update/{id}")
    public String updateUserInfo(@PathVariable("id") Long id, @ModelAttribute Users updatedUser) {
        Users existingUser = usersRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Недопустимый идентификатор пользователя: " + id));

        // Обновите соответствующие поля (например, username, email и т. д.) в existingUser
        existingUser.setUsername(updatedUser.getUsername());
        existingUser.setEmail(updatedUser.getEmail());
        // Добавьте другие поля по необходимости

        usersRepository.save(existingUser);
        return "redirect:/users";
    }

    @GetMapping("/users/search")
    public String searchUsersByEmail(@RequestParam("email") String email, Model model) {
        List<Users> usersWithEmail = usersRepository.findByEmailContaining(email);
        model.addAttribute("usersList", usersWithEmail);
        return "users";
    }
    @GetMapping("/users/count")
    public String countUsers(Model model) {
        long count = usersRepository.count();
        model.addAttribute("usersCount", count);
        return "users"; // Имя вашего шаблона HTML
    }
}
