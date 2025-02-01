package sharingcalender.front.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Repeatable;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
@Repeatable(RedirectByExceptionContainer.class)
public @interface RedirectByException {

    Class<? extends Exception> exception();

    String title();

    String redirect();

}
