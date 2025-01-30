package sharingcalender.front.threadlocal;

public final class AuthorizationTokenHolder {

    private static final ThreadLocal<String> tokenHolder = new InheritableThreadLocal<>();
    // threadLocal 은 스레드마다 별도의 저장공간을 가지므로 static 으로 선언해도 스레드간의 공유가 되지 않는다.

    public static void setToken(String token) {
        tokenHolder.set(token);

    }

    public static String getToken() {
        return tokenHolder.get();
    }

    public static void free() {
        tokenHolder.remove();
    }


}
