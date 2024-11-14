package zxf.jackson.json.unicode;

public class CodePointTests {
    static final String U_CHECK = "✅";

    public static void main(String[] args) {
        System.out.println(U_CHECK + "=u" + Integer.toHexString(U_CHECK.toCharArray()[0]));

        int codePoint1 = '\u2705';
        char codePoint2 = '\u2705';
        int codePoint3 = 0x2705;
        char codePoint4 = 0x2705;
        System.out.println(U_CHECK + "=" + new String(Character.toChars(codePoint1)));
        System.out.println(U_CHECK + "=" + codePoint2);
        System.out.println(U_CHECK + "=" + new String(Character.toChars(codePoint3)));
        System.out.println(U_CHECK + "=" + codePoint4);
    }
}
