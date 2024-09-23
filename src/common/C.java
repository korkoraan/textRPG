package common;

/** Console utility functions**/
public class C {
    public static void log(String str) {
        System.out.println("|::| " + str);
    }

    public static String name(Object obj) {
       return obj.getClass().getSimpleName();
    }
}
