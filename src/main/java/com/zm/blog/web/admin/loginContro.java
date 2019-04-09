package com.zm.blog.web.admin;

import com.zm.blog.Inner.User;
import com.zm.blog.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpSession;

@Controller
@RequestMapping("/admin")//通过配置使下面的映射都在/admin下;
public class loginContro {

    @Autowired
    private UserService userService;

    @GetMapping
    public String loginPage()
    {
        return "admin/login";
    }

    @PostMapping("/login")
    public String login(@RequestParam String username,
                        @RequestParam String password,
                        HttpSession session,
                        RedirectAttributes attributes)
    {
        User user=userService.checkUser(username,password);
        if(user!=null)
        {
            user.setPassword(null);
            session.setAttribute("user",user);
            return "admin/index";
        }
        else
        {
            attributes.addFlashAttribute("message","用户名和方法错误");
            return "redirect:/admin";
        }
    }

    @GetMapping("/logout")
    public String logout(HttpSession session)
    {
        session.removeAttribute("user");
        return "redirect:/admin";
    }

}
