package web.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import web.model.User;
import web.service.UserServiceImp;

@Controller
@RequestMapping("/")
public class UserController {

    private final UserServiceImp userService;

    @Autowired
    public UserController(UserServiceImp userService) {
        this.userService = userService;
    }
    @RequestMapping(value = "/",method = RequestMethod.GET)
    public String listUsers(Model model) {
        model.addAttribute("user",new User());
        model.addAttribute("listUser",userService.listUser());
        return "users";
    }
    @GetMapping("/add")
    public String newPerson(@ModelAttribute("user") User user) {
        return "add";
    }
    @PostMapping
    public String addUser(@ModelAttribute("useradd") User user) {
        userService.addUser(user);
        return "redirect:/";
    }
    @DeleteMapping("/{id}")
    public String removeUser(@PathVariable("id") int id) {
        userService.removeUser(id);
        return "redirect:/";
    }
    @GetMapping("/{id}/edit")
    public String edit(@PathVariable("id") int id, Model model) {
        model.addAttribute("user",userService.getUserById(id));
        return "edit";
    }
    @PatchMapping("/{id}")
    public String update(@ModelAttribute("user") User user, @PathVariable("id") int id) {
        userService.updateUser(user,id);
        return "redirect:/";
    }
/*    @RequestMapping(value = "/{id}/edit",method = {RequestMethod.GET, RequestMethod.PATCH})
    public String update(@ModelAttribute("user") User user, @PathVariable("id") int id, Model model) {
        model.addAttribute("user",userService.getUserById(id));
        userService.updateUser(user, id);
        return "edit";
    }*/
}
