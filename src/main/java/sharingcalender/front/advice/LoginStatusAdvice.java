package sharingcalender.front.advice;


import jakarta.servlet.http.HttpServletRequest;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ModelAttribute;

@ControllerAdvice
public class LoginStatusAdvice {

    @ModelAttribute
    public void setLoginStatus(HttpServletRequest request, Model model) {

        boolean isLoggedIn = (boolean) request.getAttribute("isLoggedIn");

        model.addAttribute("isLoggedIn", isLoggedIn);

    }
}
