package com.lptstore;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/laptops")
public class LaptopController {

    @GetMapping("/{id}")
    public String showLaptop(@PathVariable int id, Model model) {

        String name = "";
        String processor = "";
        String ram = "";
        String graphics = "";
        int price = 0;  // numeric price
        String image = "";

        switch (id) {
            case 1: name="Asus Zenbook 14"; processor="i5 13th Gen"; ram="16GB"; graphics="Intel Iris"; price=75000; break;
            case 2: name="Asus Vivobook 15"; processor="i5 12th Gen"; ram="8GB"; graphics="Intel UHD"; price=55000; break;
            case 3: name="Vivobook OLED"; processor="i5"; ram="8GB"; graphics="Intel"; price=60000; break;
            case 4: name="VivoBook 14"; processor="i3"; ram="8GB"; graphics="Intel"; price=45000; break;
            case 5: name="ROG Flow X16"; processor="Ryzen 9"; ram="16GB"; graphics="RTX 3060"; price=150000; break;
            case 6: name="TUF F15"; processor="i7"; ram="16GB"; graphics="RTX 3050"; price=70000; break;
            case 7: name="VivoBook S14"; processor="i5"; ram="8GB"; graphics="Intel"; price=50000; break;
            case 8: name="VivoBook 16X"; processor="i5"; ram="16GB"; graphics="Intel"; price=65000; break;
            case 9: name="Chromebook Flip"; processor="MediaTek"; ram="4GB"; graphics="Integrated"; price=25000; break;
            case 10: name="ROG Flow X13"; processor="Ryzen 7"; ram="16GB"; graphics="RTX 3050"; price=120000; break;
            case 11: name="VivoBook Ultra K15"; processor="i5"; ram="8GB"; graphics="Intel"; price=58000; break;
            case 12: name="ZenBook Pro Duo"; processor="i7"; ram="16GB"; graphics="RTX"; price=180000; break;
            case 13: name="TUF Dash F15"; processor="i7"; ram="16GB"; graphics="RTX 3060"; price=95000; break;
            case 14: name="VivoBook Go 14"; processor="Ryzen 5"; ram="8GB"; graphics="Integrated"; price=48000; break;
            case 15: name="ExpertBook P2"; processor="i5"; ram="8GB"; graphics="Intel"; price=52000; break;
            case 16: name="ZenBook Flip 13"; processor="i7"; ram="16GB"; graphics="Intel"; price=105000; break;
            case 17: name="ROG Strix Scar 15"; processor="i9"; ram="32GB"; graphics="RTX 3080"; price=220000; break;
            case 18: name="VivoBook 14X"; processor="i5"; ram="8GB"; graphics="Intel"; price=57000; break;
            case 19: name="Chromebook C223"; processor="Celeron"; ram="4GB"; graphics="Integrated"; price=22000; break;
            case 20: name="ROG Zephyrus M16"; processor="i9"; ram="16GB"; graphics="RTX 4070"; price=200000; break;
            default: return "redirect:/"; // invalid ID
        }

        // Image fix: specific files
        if (id == 6) image = "/images/laptop6.png";
        else if (id == 11) image = "/images/laptop11.webp";
        else if (id == 20) image = "/images/laptop20.avif";
        else image = "/images/laptop" + id + ".jpg";

        model.addAttribute("id", id);
        model.addAttribute("name", name);
        model.addAttribute("processor", processor);
        model.addAttribute("ram", ram);
        model.addAttribute("graphics", graphics);
        model.addAttribute("price", price); // numeric
        model.addAttribute("image", image);

        return "laptop-detail";
    }
}