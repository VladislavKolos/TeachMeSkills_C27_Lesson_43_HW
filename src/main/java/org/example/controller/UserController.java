package org.example.controller;

import lombok.RequiredArgsConstructor;
import org.example.model.User;
import org.example.service.UserService;
import org.example.validator.UserValidator;
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
    private final UserValidator userValidator;

    /**
     * Displays the user creation page.
     *
     * @return name of the JSP-page for creating a user
     */
    @GetMapping("/create")
    public String createUserGet(User user, Model model) {
        model.addAttribute("user", user);

        return "createUser";
    }

    /**
     * Processes a Post-request to create a user.
     *
     * @param user user to be created
     * @return redirect to user creation page or user information page
     * @throws SQLException if an error occurs when creating a user
     */
    @PostMapping("/create")
    public String createUserPost(User user) throws SQLException {
        String url;

        if (userValidator.isNotValid(user)) {
            url = "createUser";
        } else {
            userService.createUser(user);
            url = "redirect:/user/get?id=" + user.getId();
        }

        return url;
    }

    /**
     * Displays information about the user.
     *
     * @param id    user ID
     * @param model model containing user information
     * @return name of the JSP-page with user information
     * @throws SQLException if an error occurs while retrieving user information
     */
    @GetMapping("/get")
    public String getUserGet(@RequestParam Integer id, Model model) throws SQLException {
        User user = userService.getUser(id);
        model.addAttribute("user", user);

        return "userInfo";
    }

    /**
     * Displays the user login change page.
     *
     * @return name of the JSP-page for changing the user login
     */
    @GetMapping("/change-login")
    public String changeLoginGet(User user, Model model) {
        model.addAttribute("user", user);

        return "changeUserLogin";
    }

    /**
     * Processes a Post-request to change the user login.
     *
     * @param user user whose data will be updated
     * @return redirection to the user login change page or user information page
     * @throws SQLException if an error occurs when updating user data
     */
    @PostMapping("/change-login")
    public String changeLoginPost(User user) throws SQLException {
        String url;

        if (userValidator.isNotValid(user)) {
            url = "changeUserLogin";
        } else {
            userService.updateUser(user.getId(), user.getEmail(), user.getLogin());
            url = "redirect:/user/get?id=" + user.getId() + "&login=" + user.getLogin();
        }

        return url;
    }

    /**
     * Displays the user deletion page.
     *
     * @return name of the JSP-page for deleting a user
     */
    @GetMapping("/delete")
    public String deleteUserGet(User user, Model model) {
        model.addAttribute("user", user);

        return "deleteUser";
    }

    /**
     * Processes a Post-request to delete a user.
     *
     * @param id user ID to be deleted
     * @return redirect to user deletion page
     * @throws SQLException if an error occurs when deleting a user
     */
    @PostMapping("/delete")
    public String deleteUserPost(@RequestParam Integer id) throws SQLException {
        String url;

        if (id == null) {
            url = "deleteUser";
        } else {
            userService.deleteUser(id);
            url = "redirect:/user/delete";
        }

        return url;
    }
}
