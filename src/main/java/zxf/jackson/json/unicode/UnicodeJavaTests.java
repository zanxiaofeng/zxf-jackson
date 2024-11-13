package zxf.jackson.json.unicode;


import org.springframework.util.Assert;

import java.util.Objects;

public class UnicodeJavaTests {
    static final String U_CHECK = "✅"; // U+2705
    static final String U_STRONG = "强"; // U+5F3A
    static final String U_A = "A"; // U+0041

    public static void main(String[] args) {
        String check1 = "\u2705";
        System.out.println(check1);
        Assert.isTrue(U_CHECK.equals(check1));

        String strong1 = "\u5F3A";
        System.out.println(strong1);
        Assert.isTrue(U_STRONG.equals(strong1));

        String a1 = "\u0041";
        System.out.println(a1);
        Assert.isTrue(U_A.equals(a1));

        String check2 = "\\u" + "2705";
        System.out.println(check2);
        Assert.isTrue("\\u2705".equals(check2));
        Assert.isTrue(!U_CHECK.equals(check2));

        String strong2 = "\\u" + "5F3A";
        System.out.println(strong2);
        Assert.isTrue("\\u5F3A".equals(strong2));
        Assert.isTrue(!U_STRONG.equals(strong2));

        String a2 = "\\u" + "0041";
        System.out.println(a2);
        Assert.isTrue("\\u0041".equals(a2));
        Assert.isTrue(!U_A.equals(a2));


        Assert.isTrue(U_A.equals(stringFromCodePointHex("0041")));
        Assert.isTrue(U_CHECK.equals(stringFromCodePointHex("2705")));
        Assert.isTrue(U_STRONG.equals(stringFromCodePointHex("5F3A")));
    }

    static String stringFromCodePointHex(String codePointHex) {
        int codePoint = Integer.parseInt(codePointHex, 16);
        // For Java 11 and later versions: return Character.toString(codePoint)
        char[] chars = Character.toChars(codePoint);
        return String.valueOf(chars);
    }
}
