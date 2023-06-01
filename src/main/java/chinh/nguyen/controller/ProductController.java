package chinh.nguyen.controller;

import chinh.nguyen.model.Cart;
import chinh.nguyen.model.Product;
import chinh.nguyen.service.IProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.bind.support.SessionStatus;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.Optional;

@Controller
@SessionAttributes("cart")
public class ProductController {
    @Autowired
    private IProductService productService;

    @ModelAttribute("cart")
    public Cart setupCart() {
        return new Cart();
    }

    @GetMapping("/")
    public ModelAndView showShop() {
        ModelAndView modelAndView = new ModelAndView("/shop");
        modelAndView.addObject("products", productService.findAll());
        return modelAndView;
    }

    @GetMapping("/add/{id}")
    public String addToCart(@PathVariable Long id, @ModelAttribute Cart cart, @RequestParam("action") String action) {
        Optional<Product> productOptional = productService.findById(id);
        if (!productOptional.isPresent()) {
            return "/error.404";
        }
        if (action.equals("increase")) {
            cart.addProduct(productOptional.get());
            return "redirect:/shopping-cart";
        } else if (action.equals("decrease")) {
            cart.removeProduct(productOptional.get());
            return "redirect:/shopping-cart";
        }

        cart.addProduct(productOptional.get());
        return "redirect:/";
    }
    @GetMapping("/checkOut")
    public String checkOut(SessionStatus sessionStatus){
        sessionStatus.setComplete();
        return "redirect:/";
    }

}
