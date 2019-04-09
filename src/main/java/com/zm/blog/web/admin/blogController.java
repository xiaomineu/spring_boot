package com.zm.blog.web.admin;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/admin")
public class blogController {

    @GetMapping("/blog")
    public String blogs()
    {
        return "admin/blogs";
    }
}
