package ru.mycompany.restapinews.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import ru.mycompany.restapinews.model.Users;
import ru.mycompany.restapinews.model.Books;
import ru.mycompany.restapinews.model.Orders;
import ru.mycompany.restapinews.repository.UsersRepository;
import ru.mycompany.restapinews.repository.BooksRepository;
import ru.mycompany.restapinews.repository.OrdersRepository;

import java.util.Optional;

@Controller
@RequiredArgsConstructor
public class MyController {

    private final UsersRepository usersRepository;
    private final BooksRepository booksRepository;
    private final OrdersRepository ordersRepository;

    public MyController() {
        usersRepository = null;
        booksRepository = null;
        ordersRepository = null;
    }

    // Users controllers
    @GetMapping("/users")
    public String users(Model model) {
        model.addAttribute("usersList", usersRepository.findAll());
        return "users";
    }

    @GetMapping("/users/create")
    public String createUsers(Model model) {
        model.addAttribute("users", new Users());
        return "createUsers";
    }

    @PostMapping("/users/create")
    public String createUsers(@ModelAttribute Users users) {
        usersRepository.save(users);
        return "redirect:/users";
    }

    // Books controllers
    @GetMapping("/books")
    public String books(Model model) {
        model.addAttribute("booksList", booksRepository.findAll());
        return "books";
    }

    @GetMapping("/books/create")
    public String createBooks(Model model) {
        model.addAttribute("books", new Books());
        return "createBooks";
    }

    @PostMapping("/books/create")
    public String createBooks(@ModelAttribute Books books) {
        booksRepository.save(books);
        return "redirect:/books";
    }

    // Orders controllers
    @GetMapping("/orders")
    public String orders(Model model) {
        model.addAttribute("ordersList", ordersRepository.findAll());
        return "orders";
    }

    @GetMapping("/orders/create")
    public String createOrders(Model model) {
        model.addAttribute("orders", new Orders());
        return "createOrders";
    }

    @PostMapping("/orders/create")
    public String createOrders(@ModelAttribute Orders orders) {
        ordersRepository.save(orders);
        return "redirect:/orders";
    }
}
