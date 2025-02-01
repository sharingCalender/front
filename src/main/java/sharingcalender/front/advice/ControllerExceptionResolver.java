package sharingcalender.front.advice;

import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.exception.ExceptionUtils;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.ModelAndView;
import sharingcalender.front.exception.RedirectPageException;

@ControllerAdvice
@Slf4j
public class ControllerExceptionResolver {

    @ExceptionHandler(RedirectPageException.class)
    public ModelAndView redirectToErrorPage(RedirectPageException e) {
        ModelAndView modelAndView = new ModelAndView("/error/redirectErrorPage");
        modelAndView.addObject("redirect", e.getRedirect());
        modelAndView.addObject("title", e.getTitle());
        modelAndView.addObject("errorMessage", ExceptionUtils.getRootCauseMessage(e));

        return modelAndView;
    }
}
