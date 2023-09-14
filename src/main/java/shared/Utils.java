package shared;

public class Utils {
    public static String charArrayToString(char[] arr) {
        StringBuilder stringBuilder = new StringBuilder();
        for(char ch: arr)
            stringBuilder.append(ch);

        return stringBuilder.toString();
    }
}
