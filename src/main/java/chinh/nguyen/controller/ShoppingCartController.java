package chinh.nguyen.controller;

import chinh.nguyen.model.Cart;
import chinh.nguyen.model.Product;
import chinh.nguyen.service.IProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.SessionAttribute;
import org.springframework.web.servlet.ModelAndView;

import java.util.Optional;

@Controller
public class ShoppingCartController {
    @Autowired
    private IProductService productService;
    @ModelAttribute("cart")
    public Cart setupCart(){
        return new Cart();
    }

    @GetMapping("/shopping-cart")
    public ModelAndView showCart (@SessionAttribute("cart") Cart cart){
        ModelAndView modelAndView = new ModelAndView("/cart");
        modelAndView.addObject("cart",cart);
        return modelAndView;
    }
    @GetMapping("/detail/{id}")
    public String detail(@PathVariable("id")Long id, Model model){
        Optional<Product> product = productService.findById(id);
        model.addAttribute("p",product.get());
        return "/detail";
    }

}
