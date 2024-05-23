package by.mycompany.restapilibrary.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import by.mycompany.restapilibrary.model.Orders;
import by.mycompany.restapilibrary.repository.OrdersRepository;
import by.mycompany.restapilibrary.service.UserService;

import java.util.List;

@Controller
@RequiredArgsConstructor
public class OrdersController {

    private final OrdersRepository ordersRepository;
    private final UserService userService;

    @GetMapping("/orders")
    public String books(Model model) {
        model.addAttribute("ordersList", ordersRepository.findAll());
        model.addAttribute("isAdmin", userService.isAdmin());
        long count = ordersRepository.count();
        System.out.println("Orders count: " + count);
        model.addAttribute("ordersCount", count);
        return "orders";
    }

    @GetMapping("/orders/create")
    public String createOrders(Model model) {
        model.addAttribute("orders", new Orders());
        return "createOrder";
    }

    @PostMapping("/orders/create")
    public String createOrders(@ModelAttribute Orders orders) {
        ordersRepository.save(orders);
        return "redirect:/orders";
    }

    @GetMapping("/orders/delete")
    public String showDeleteForm() {
        return "selectDeleteOrder";
    }

    @PostMapping("/orders/delete")
    public String deleteOrder(@RequestParam("id") Long id) {
        ordersRepository.deleteById(id);
        return "redirect:/orders";
    }

    @GetMapping("/orders/update")
    public String showUpdateForm(Model model) {
        return "selectUpdateOrder";
    }




    @GetMapping("/orders/update/{id}")
    public String showUpdateForm(@PathVariable("id") Long id, Model model) {
        Orders order = ordersRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Недопустимый идентификатор заказа: " + id));
        model.addAttribute("order", order);
        return "updateOrder";
    }

    @PostMapping("/orders/update/{id}")
    public String updateOrderStatus(@PathVariable("id") Long id, @ModelAttribute Orders updatedOrder) {
        Orders existingOrder = ordersRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Недопустимый идентификатор заказа: " + id));

        // Обновляем статус заказа
        existingOrder.setStatus(updatedOrder.getStatus());
        ordersRepository.save(existingOrder); // Сохраняем изменения

        return "redirect:/orders";
    }
    @GetMapping("/orders/count")
    public String countOrders(Model model) {
        long count = ordersRepository.count();
        model.addAttribute("ordersCount", count);
        return "orders"; // Имя вашего шаблона HTML
    }

    @GetMapping("/orders/search/book/{bookId}")
    public String searchOrdersByBookId(@PathVariable("bookId") Long bookId, Model model) {
        List<Orders> ordersWithBookId = ordersRepository.findByBookId(bookId);
        model.addAttribute("ordersList", ordersWithBookId);
        return "orders"; // Имя вашего шаблона HTML для отображения заказов
    }
}
