package sharingcalender.front.advice;


import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.stereotype.Component;
import sharingcalender.front.annotation.RedirectByException;
import sharingcalender.front.exception.RedirectPageException;

@Aspect
@Component
public class RedirectByExceptionAdvice {

    @Pointcut("@annotation(sharingcalender.front.annotation.RedirectByException)")
    public void redirectPointcut() {}

    @Pointcut("@annotation(sharingcalender.front.annotation.RedirectByExceptionContainer)")
    public void redirectContainerPointcut() {}

    @Around("redirectPointcut() || redirectContainerPointcut()")
    public Object redirectAdvice(ProceedingJoinPoint joinPoint) throws Throwable {

        try {
            return joinPoint.proceed();
        } catch (Exception e) {

            MethodSignature signature = (MethodSignature) joinPoint.getSignature();

            RedirectByException[] methodAnnotation = signature.getMethod()
                .getAnnotationsByType(RedirectByException.class);

            for (RedirectByException redirectByException : methodAnnotation) {
                Class<? extends Exception>[] exception = redirectByException.exception();
                for (Class<? extends Exception> clazz : exception) {
                    if (clazz.isAssignableFrom(e.getClass())) {
                        throw new RedirectPageException(redirectByException.redirect(),
                            redirectByException.title(), e);
                    }
                }
            }

            throw e;
        }
    }
}
