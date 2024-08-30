package web.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import web.models.User;
import web.service.UserService;

@Controller
public class UsersController {

    private final UserService userService;

    @Autowired
    public UsersController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping(value = "/")
    public String showAllUsers(ModelMap model) {
        model.addAttribute("allUsers", userService.getAllUsers());
        return "users";
    }

    @GetMapping("/addNewUser")
    public String addNewUserForm(ModelMap model) {

        model.addAttribute("currentUser", new User());

        return "user-info";
    }

    @GetMapping("/editUser")
    public String editUserForm(@RequestParam("id") Long id, ModelMap model) {

        User user = userService.findUserById(id);

        model.addAttribute("currentUser", user);

        return "user-info";
    }

    @PostMapping()
    public String saveUser(@ModelAttribute("currentUser") User user) {
        userService.saveUser(user);
        return "redirect:/";
    }

    @GetMapping("/deleteUser")
    public String deleteUser(@RequestParam Long id) {
        userService.deleteUser(id);
        return "redirect:/";
    }
}