package com.springapp.mvc;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpSession;
import java.util.LinkedList;

import static com.springapp.mvc.DatabaseWorker.*;

/**
 * Created by Александр on 28.04.14.
 */
@Controller
public class MyPageController {
    private static HttpSession session;

    @RequestMapping(value = "/user*", method = RequestMethod.GET)
    public String myPage(@RequestParam String login, ModelMap map) {
        session = HelloController.session;
        try {
            HelloController.logined(session);
        } catch (myException e) {
            return "redirect:/";
        }
        if (session.getAttribute("login").equals(login)) {
            session.setAttribute("myPage", "true");
        } else
            session.setAttribute("myPage", "nottrue");
        session.setAttribute("thisUser", login);
        session.setAttribute("infoThisUser", getInfoAboutUser(login));
        session.setAttribute("pageImage", GettingImagePage(login));
        return "userPage";

    }

    @RequestMapping(value = "/alluser", method = RequestMethod.GET)
    public String allUserPage(ModelMap map) {
        session = HelloController.session;
        try {
            HelloController.logined(session);
        } catch (myException e) {
            return "redirect:/";
        }
        MyLinkedList aboutUser = DatabaseWorker.getUzas();
        session.setAttribute("allUser", aboutUser);
        map.addAttribute("uzas", aboutUser);
        return "user";

    }

    @RequestMapping(value = "/dialogue*", method = RequestMethod.GET)
    public String getDialogue(@RequestParam String to) {
        session = HelloController.session;
        try {
            HelloController.logined(session);
        } catch (myException e) {
            return "redirect:/";
        }
        LinkedList<StructureMessage> list = getMessagesFromDB((String) session.getAttribute("login"), to);
        session.setAttribute("messageto", to);

        session.setAttribute("messages", list);
        return "dialogue";

    }

    @RequestMapping(value = "/editprofile", method = RequestMethod.GET)
    public String getEditProfile() {
        session = HelloController.session;
        try {
            HelloController.logined(session);
        } catch (myException e) {
            return "redirect:/";
        }
        return "edit";
    }

    @RequestMapping(value = "/setinfo", method = RequestMethod.GET)
    public String getSetInfo() {
        session = HelloController.session;
        try {
            HelloController.logined(session);
        } catch (myException e) {
            return "redirect:/";
        }
        return "edit";
    }

    @RequestMapping(value = "/setinfo", method = RequestMethod.POST)
    public String postSetInfo(@RequestParam String firstname,
                              @RequestParam String lastname,
                              @RequestParam String bdate,
                              @RequestParam String hobby) {
        System.out.println(firstname);
        SetInfoAboutUser((String) session.getAttribute("login"), firstname, lastname, bdate, hobby);
        return "redirect:/user?login=" + session.getAttribute("login");
    }
}
