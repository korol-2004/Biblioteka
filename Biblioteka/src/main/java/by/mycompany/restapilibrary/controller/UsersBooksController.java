package by.mycompany.restapilibrary.controller;

import by.mycompany.restapilibrary.model.Books;
import by.mycompany.restapilibrary.model.Users;
import by.mycompany.restapilibrary.model.UsersBooks;
import by.mycompany.restapilibrary.repository.BooksRepository;
import by.mycompany.restapilibrary.repository.UsersBooksRepository;
import by.mycompany.restapilibrary.repository.UsersRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;
import java.util.stream.Collectors;

@Controller
public class UsersBooksController {

    @Autowired
    private UsersRepository usersRepository;

    @Autowired
    private BooksRepository booksRepository;

    @Autowired
    private UsersBooksRepository usersBooksRepository;

    @GetMapping("/usersbooks")
    public String getUsersBooks(Model model) {
        model.addAttribute("usersBooks", usersBooksRepository.findAll());
        return "usersbooks";
    }

    @GetMapping("/addusersbook")
    public String addUsersBookForm(Model model) {
        List<Users> usersList = usersRepository.findAll();
        List<Books> booksList = booksRepository.findAll();
        model.addAttribute("users", usersList);
        model.addAttribute("books", booksList);
        model.addAttribute("usersBook", new UsersBooks());
        return "addusersbook";
    }
    @PostMapping("/addusersbook")
    public String addUsersBook(@ModelAttribute UsersBooks usersBook, Model model) {
        // Проверяем, существует ли уже такая связь
        List<UsersBooks> existingUsersBooks = usersBooksRepository.findByUserIdAndBookId(usersBook.getUserId(), usersBook.getBookId());
        if (existingUsersBooks.isEmpty()) {
            // Если связи не существует, сохраняем новую
            usersBooksRepository.save(usersBook);
            return "redirect:/usersbooks"; // Перенаправляем пользователя на страницу со списком
        } else {
            // Если связь уже существует, возвращаем форму с сообщением об ошибке
            model.addAttribute("error", "Такая связь уже существует!");
            return "addusersbook"; // Возвращаем пользователя на форму добавления
        }
    }

    @GetMapping("/deleteusersbook/{id}")
    public String deleteUsersBook(@PathVariable Long id) {
        // Проверяем, существует ли связь с таким идентификатором
        boolean exists = usersBooksRepository.existsById(id);
        if (exists) {
            // Если связь существует, удаляем её
            usersBooksRepository.deleteById(id);
        }
        // Перенаправляем пользователя на страницу со списком всех связей
        return "redirect:/usersbooks";
    }


    @GetMapping("/selectuser")
    public String selectUser(Model model) {
        List<Users> usersList = usersRepository.findAll();
        model.addAttribute("users", usersList);
        return "selectuser";
    }

    @GetMapping("/infouserbooks/{userId}")
    public String getUserBooks(@PathVariable Long userId, Model model) {
        List<UsersBooks> usersBooksList = usersBooksRepository.findByUserId(userId);
        List<Long> bookIds = usersBooksList.stream()
                .map(UsersBooks::getBookId)
                .collect(Collectors.toList());
        List<Books> booksList = booksRepository.findAllById(bookIds);
        model.addAttribute("books", booksList);
        return "infouserbooks"; // имя вашего HTML-шаблона
    }





}
