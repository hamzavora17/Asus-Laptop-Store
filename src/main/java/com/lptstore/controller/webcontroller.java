

package com.lptstore.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import com.lptstore.entity.user;
import com.lptstore.service.UserService;

import jakarta.servlet.http.HttpSession;
import jakarta.validation.Valid;
import org.springframework.validation.BindingResult;

@Controller
public class webcontroller {

    private final UserService userService;

    public webcontroller(UserService userService) {
        this.userService = userService;
    }

    // ================= SIGNUP =================
    @GetMapping("/signup")
    public String signup(Model model) {
        model.addAttribute("user", new user());
        return "auth/signup";
    }

    @PostMapping("/signup")
    public String register(@Valid @ModelAttribute user user,
                           BindingResult result,
                           @RequestParam String confirmPassword,
                           Model model) {

        if (result.hasErrors()) return "auth/signup";

        if (!user.getPassword().equals(confirmPassword)) {
            model.addAttribute("error", "Password mismatch");
            return "auth/signup";
        }

        userService.saveUsers(user);
        return "redirect:/signup?success=true";
      
    }

    // ================= LOGIN =================
    @GetMapping("/login")
    public String loginPage(Model model) {
        model.addAttribute("user", new user());
        return "auth/login";
    }

    @PostMapping("/login")
    public String login(@ModelAttribute user user,
                        HttpSession session,
                        Model model) {

        boolean ok = userService.checkLogin(user.getEmail(), user.getPassword());

        if (ok) {
            // IMPORTANT FIX
            session.setAttribute("loginEmail", user.getEmail());
            return "redirect:/index";
        }

        model.addAttribute("error", "Invalid login");
        return "auth/login";
    }

    // ================= INDEX =================
    @GetMapping("/index")
    public String index() {
        return "index";
    }

    // ================= USERS =================
    @GetMapping("/display")
    public String display(Model model) {
        model.addAttribute("userList", userService.getAllUsersList());
        return "showrecords";
    }

    @GetMapping("/deleteUser/{id}")
    public String delete(@PathVariable Long id) {
        userService.deleteUser(id);
        return "redirect:/display";
    }

    @PostMapping("/updateUser")
    public String update(@ModelAttribute user user) {
        userService.saveUsers(user);
        return "redirect:/display";
    }
    @GetMapping("/userUpd/{id}")
       public String editUser(@PathVariable Long id, Model model) {
           model.addAttribute("user", userService.getUserById(id));
          return "updateUser";
      }


    // ================= CHECKOUT =================
    @GetMapping("/checkout")
    public String checkout(@RequestParam int id,
                           @RequestParam int price,
                           @RequestParam String name,
                           @RequestParam(defaultValue = "1") int quantity,
                           Model model) {

        model.addAttribute("id", id);
        model.addAttribute("price", price);
        model.addAttribute("name", name);
        model.addAttribute("quantity", quantity);
        model.addAttribute("totalPrice", price * quantity);

        return "checkout";
    }

    // ================= ORDER (FINAL FIX - NO UNKNOWN) =================
    @PostMapping("/order")
    public String order(@RequestParam int price,
                        @RequestParam String name,
                        @RequestParam int quantity,
                        HttpSession session,
                        Model model) {

        // ✅ LOGIN CHECK
        String email = (String) session.getAttribute("loginEmail");
        if (email == null) {
            return "redirect:/login";
        }

        int total = price * quantity;

        // ORDERS
        Integer orders = (Integer) session.getAttribute("totalOrders");
        if (orders == null) orders = 0;

        // REVENUE
        Integer revenue = (Integer) session.getAttribute("totalRevenue");
        if (revenue == null) revenue = 0;

        session.setAttribute("totalOrders", orders + 1);
        session.setAttribute("totalRevenue", revenue + total);

        // CUSTOMER LIST
        List<String> customers = (List<String>) session.getAttribute("customers");

        if (customers == null) {
            customers = new ArrayList<>();
        }

        // ✅ ADD EMAIL
        customers.add(email); // ya unique ke liye contains check use karo

        session.setAttribute("customers", customers);

        model.addAttribute("orderTotal", total);

        return "success";
    }

    // ================= ADMIN DASHBOARD =================
    @GetMapping("/admin/dashboard")
    public String dashboard(Model model, HttpSession session) {

        model.addAttribute("userList", userService.getAllUsersList());

        model.addAttribute("totalOrders",
                session.getAttribute("totalOrders") != null ? session.getAttribute("totalOrders") : 0);

        model.addAttribute("totalRevenue",
                session.getAttribute("totalRevenue") != null ? session.getAttribute("totalRevenue") : 0);

        model.addAttribute("customerList",
                session.getAttribute("customers") != null ? session.getAttribute("customers") : new ArrayList<>());

        return "admin-dashboard";
    }
    @GetMapping("/admin")
       public String adminDashboard(Model model) {
          model.addAttribute("title", "Admin Dashboard");
           return "admin-dashboard";
     }@GetMapping("/admin/login")
     public String showAdminLogin() {
    	    return "adminLogin";
    	}

    	@PostMapping("/admin/login")
    	public String adminLogin(@RequestParam String username,
    	                         @RequestParam String password,
    	                         HttpSession session,
    	                         Model model) {

    	    if ("admin".equals(username) && "admin@123".equals(password)) {
    	        session.setAttribute("admin", "ADMIN");
    	        return "redirect:/display";
    	    }

    	    model.addAttribute("error", "Incorrect Username or Password");
    	    return "adminLogin";
    	}

    	@GetMapping("/admin/logout")
    	public String logout(HttpSession session) {
    	    session.invalidate();
    	    return "redirect:/admin/login";
    	}
    	@PostMapping("/forgot-password")
    	   @ResponseBody
    	   public String forgotPassword(@RequestParam String email) {
           return userService.resetPassword(email);
    	  }

}