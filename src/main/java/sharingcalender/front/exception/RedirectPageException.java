package sharingcalender.front.exception;

import lombok.Getter;

@Getter
public class RedirectPageException extends RuntimeException {

    private final String redirect;
    private final String title;

    public RedirectPageException(String redirect, String title,Throwable e) {
        super(e);
        this.redirect = redirect;
        this.title = title;
    }

}
