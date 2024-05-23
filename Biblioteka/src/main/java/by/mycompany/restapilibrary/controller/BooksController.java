package by.mycompany.restapilibrary.controller;

import by.mycompany.restapilibrary.model.Books;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import by.mycompany.restapilibrary.repository.BooksRepository;
import by.mycompany.restapilibrary.service.UserService;

import java.util.List;

@Controller
@RequiredArgsConstructor
public class BooksController {

    private final BooksRepository booksRepository;
    private final UserService userService;

    @GetMapping("/books")
    public String books(Model model) {
        model.addAttribute("booksList", booksRepository.findAll());
        model.addAttribute("isAdmin", userService.isAdmin());
        long count = booksRepository.count();
        System.out.println("Books count: " + count);
        model.addAttribute("booksCount", count);
        return "books";
    }



    @GetMapping("/books/create")
    public String createBooks(Model model) {
        model.addAttribute("books", new Books());
        return "createBook";
    }

    @PostMapping("/books/create")
    public String createBooks(@ModelAttribute Books books) {
        booksRepository.save(books);
        return "redirect:/books";
    }

    @GetMapping("/books/delete")
    public String showDeleteForm() {
        return "selectDeleteBook";
    }

    @PostMapping("/books/delete")
    public String deleteBook(@RequestParam("id") Long id) {
        booksRepository.deleteById(id);
        return "redirect:/books";
    }

    @GetMapping("/books/update")
    public String showUpdateForm(Model model) {
        return "selectUpdateBook";
    }


    @GetMapping("/books/update/{id}")
    public String showUpdateForm(@PathVariable("id") Long id, Model model) {
        Books book = booksRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Invalid book Id:" + id));
        model.addAttribute("book", book);
        return "updateBook";
    }

    @PostMapping("/books/update/{id}")
    public String updateBook(@PathVariable("id") Long id, @ModelAttribute("book") Books book) {
        book.setId(id);
        booksRepository.save(book);
        return "redirect:/books";
    }
    @GetMapping("/books/search")
    public String searchBooksByTitle(@RequestParam("title") String title, Model model) {
        List<Books> booksWithTitle = booksRepository.findByTitleContaining(title);
        model.addAttribute("booksList", booksWithTitle);
        return "books";
    }
    @GetMapping("/books/count")
    public String countBooks(Model model) {
        long count = booksRepository.count();
        model.addAttribute("booksCount", count);
        return "books"; // Имя вашего шаблона HTML
    }

}