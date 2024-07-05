package org.example.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.example.model.User;
import org.example.service.UserService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.sql.SQLException;

/**
 * Controller for user management.
 */
//@Slf4j
@Controller
@RequestMapping("/user")
@RequiredArgsConstructor
public class UserController {
    private final UserService userService;

    /**
     * Displays the user creation page.
     * @return name of the JSP-page for creating a user
     */
    @GetMapping("/create")
    public String createUserGet(User user, Model model) {
        model.addAttribute("user", user);

        return "create_user";
    }

    /**
     * Processes a Post-request to create a user.
     * @param user user to be created
     * @return redirect to user creation page or user information page
     * @throws SQLException if an error occurs when creating a user
     */
    @PostMapping("/create")
    public String createUserPost(User user) throws SQLException {
        String redirectUrl;

        if (user.getEmail() == null || user.getEmail().isEmpty()
                || user.getLogin() == null || user.getLogin().isEmpty()
                || user.getEmail().length() > 25 || user.getLogin().length() > 20) {
            redirectUrl = "redirect:/user/create";
        } else {
            userService.createUser(user);
            redirectUrl = "redirect:/user/get?id=" + user.getId();
        }

        return redirectUrl;
    }

    /**
     * Displays information about the user.
     * @param id    user ID
     * @param model model containing user information
     * @return name of the JSP-page with user information
     * @throws SQLException if an error occurs while retrieving user information
     */
    @GetMapping("/get")
    public String getUserGet(@RequestParam Integer id, Model model) throws SQLException {
        User user = userService.getUser(id);
        model.addAttribute("user", user);

        return "user_info";
    }

    /**
     * Displays the user login change page.
     * @return name of the JSP-page for changing the user login
     */
    @GetMapping("/change-login")
    public String changeLoginGet(User user, Model model) {
        model.addAttribute("user", user);

        return "change_user_login";
    }

    /**
     * Processes a Post-request to change the user login.
     * @param user user whose data will be updated
     * @return redirection to the user login change page or user information page
     * @throws SQLException if an error occurs when updating user data
     */
    @PostMapping("/change-login")
    public String changeLoginPost(User user) throws SQLException {
        String redirectUrl;

        if (user.getEmail() == null || user.getEmail().isEmpty()
                || user.getLogin() == null || user.getLogin().isEmpty()
                || user.getLogin().length() > 20) {
            redirectUrl = "redirect:/user/change-login";
        } else {
            userService.updateUser(user.getId(), user.getEmail(), user.getLogin());
            redirectUrl = "redirect:/user/get?id=" + user.getId() + "&login=" + user.getLogin();
        }

        return redirectUrl;
    }

    /**
     * Displays the user deletion page.
     * @return name of the JSP-page for deleting a user
     */
    @GetMapping("/delete")
    public String deleteUserGet(User user, Model model) {
        model.addAttribute("user", user);

        return "delete_user";
    }

    /**
     * Processes a Post-request to delete a user.
     * @param id user ID to be deleted
     * @return redirect to user deletion page
     * @throws SQLException if an error occurs when deleting a user
     */
    @PostMapping("/delete")
    public String deleteUserPost(@RequestParam Integer id) throws SQLException {
        String redirectUrl;

        if (id == null) {
            redirectUrl = "redirect:/user/delete";
        } else {
            userService.deleteUser(id);
            redirectUrl = "redirect:/user/delete";
        }

        return redirectUrl;
    }
}
