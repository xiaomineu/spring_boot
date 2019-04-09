package com.zm.blog.web.admin;

import com.zm.blog.Inner.type;
import com.zm.blog.service.TypeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.validation.Valid;


@Controller
@RequestMapping("/admin")
public class TypeController {

    @Autowired
    private TypeService typeService;

    @GetMapping("/types")
    public String types(@PageableDefault(size = 10, sort = {"id"}, direction = Sort.Direction.DESC)
                                Pageable pageable, Model model) {
        model.addAttribute("page", typeService.listType(pageable));
        return "admin/types";
    }

    @GetMapping("/types/input")
    public String input(Model model)//跳转过程中,传递type校验
    {
        model.addAttribute("type",new type());
        return "/admin/types-input";
    }

    @GetMapping("/types/{id}/input")
    public String editInput(@PathVariable Long id, Model model)
    {
        model.addAttribute("type",typeService.getType(id));
        return "admin/types-input";
    }

    @PostMapping("/types")
    public String post(@Valid type _type, BindingResult result,RedirectAttributes attributes)
    {
        type type1=typeService.getTypeByName(_type.getName());
        if(type1!=null)
        {
            result.rejectValue("name","nameError","不能添加重复分类");
        }
        if(result.hasErrors())
        {
            return "admin/types-input";
        }
        type t=typeService.saveType(_type);
        if(t==null)
        {
            attributes.addFlashAttribute("message","操作失败");
        }
        else
        {
            attributes.addFlashAttribute("message","操作成功");
        }
        return "redirect:/admin/types";
    }

    @PostMapping("/types/{id}")
    public String editPost(@Valid type _type, BindingResult result,@PathVariable Long id,RedirectAttributes attributes)
    {
        type type1=typeService.getTypeByName(_type.getName());
        if(type1!=null)
        {
            result.rejectValue("name","nameError","不能添加重复分类");
        }
        if(result.hasErrors())
        {
            return "admin/types-input";
        }
        type t=typeService.updateType(id,_type);
        if(t==null)
        {
            attributes.addFlashAttribute("message","更新失败");
        }
        else
        {
            attributes.addFlashAttribute("message","更新成功");
        }
        return "redirect:/admin/types";
    }

    @GetMapping("types/{id}/delete")
    public String delete(@PathVariable Long id,RedirectAttributes attributes)
    {
        typeService.deleteType(id);
        attributes.addFlashAttribute("message","删除成功");
        return "redirect:/admin/types";
    }

}
