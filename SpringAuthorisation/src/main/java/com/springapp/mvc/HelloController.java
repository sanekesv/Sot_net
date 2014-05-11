package com.springapp.mvc;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;

import static com.springapp.mvc.DatabaseWorker.AddImageOnDataBase;
import static com.springapp.mvc.DatabaseWorker.AddMessageToDB;

@Controller
public class HelloController {
    public static HttpSession session = null;

    @RequestMapping(value = "/", method = RequestMethod.GET)
    public String printWelcome(HttpServletRequest request, ModelMap model) {
        session = request.getSession();
        return "redirect:/login";
    }

    @RequestMapping(value = "/logout", method = RequestMethod.GET)
    public String getLogout(HttpServletRequest request){
        session.invalidate();
        return "redirect:/";
    }

    @RequestMapping(value = "/login", method = RequestMethod.GET)
    public String printPage() {
        return "login";
    }

    @RequestMapping(value = "/login", method = RequestMethod.POST)
    public String login(@RequestParam String username, @RequestParam String password, ModelMap map) {
        int id = DatabaseWorker.isValid(username, password);
        if (id >= 0) {
            session.setAttribute("login", username);
            return "redirect:/user?login=" + username;
        } else {
            map.addAttribute("error", "ERROR");
            return "login";
        }
    }

    static void logined(HttpSession sess) throws myException {
        if (!(sess != null && sess.getAttribute("login") != null)) {
            throw new myException();
        }
    }

    @RequestMapping(value = "/registration", method = RequestMethod.GET)
    public String registrationPage() {
        return "registration";
    }

    @RequestMapping(value = "/registration", method = RequestMethod.POST)
    public String registrationOnSystem(@RequestParam String login,
                                       @RequestParam String password,
                                       @RequestParam String repassword,
                                       ModelMap model) {
        AboutUser user = new AboutUser();
        if (password.equals(repassword)&&login.length()>1&&password.length()>1) {
            user.setPassword(password);
            user.setUsername(login);
            DatabaseWorker.registrated(user);
            session.setAttribute("login", login);
            return "redirect:/user?login=" + login;
        } else {
            model.addAttribute("errorregistr", "Passwords doesn't equals. Or... Your username or password is null :(");
            return "/registration";
        }

    }

    public static final String IMG_PATH = "C:/Users/Александр/IdeaProjects/SpringAuthorisation/src/main/webapp/WEB-INF/img/";
    public static final String IMG_PATH1 = "C:/Users/Александр/IdeaProjects/SpringAuthorisation/target/SpringAuthorisation/WEB-INF/img/";


    @RequestMapping(value = "/uploadFile", method = RequestMethod.POST)
    public String uploadFileHandler(@RequestParam("file") MultipartFile file, HttpServletRequest request) {

        if (!file.isEmpty()) {
            try {
                byte[] bytes = file.getBytes();

                int i = file.getOriginalFilename().lastIndexOf(".")+1;
//                Creating the directory to store file
               // System.out.println(IMG_PATH + session.getAttribute("login") + "." + file.getOriginalFilename().substring(i));
//                Create the file on server
                File serverFile = new File(IMG_PATH + session.getAttribute("login") + "." + file.getOriginalFilename().substring(i));
                File dirFile = new File(IMG_PATH1 + session.getAttribute("login") + "." + file.getOriginalFilename().substring(i));
                BufferedOutputStream stream = new BufferedOutputStream(
                        new FileOutputStream(serverFile));
                BufferedOutputStream stream1 = new BufferedOutputStream(
                        new FileOutputStream(dirFile));
                stream.write(bytes);
                stream1.write(bytes);
                stream.close();
                stream1.close();

                AddImageOnDataBase(session.getAttribute("login") + "." + file.getOriginalFilename().substring(i), (String)session.getAttribute("login"));
                System.out.println(request.getRealPath("/"));

                return "redirect:/user?login="+session.getAttribute("login");
            } catch (Exception e) {
                return "/";
            }
        } else {
            return "/";
        }
    }

    @RequestMapping(value = "/addmessage", method = RequestMethod.GET)
    public static String getAddMessage(){
        return "dialogue";
    }

    @RequestMapping(value = "/addmessage", method = RequestMethod.POST)
    public static String postAddMessage(@RequestParam String message){

        AddMessageToDB((String)session.getAttribute("login"), (String)session.getAttribute("messageto"), message);
        return "redirect:/dialogue?to="+session.getAttribute("messageto");
    }

}