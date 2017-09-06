import java.io.FileInputStream;

/**
 * Created by Ding on 9/5/2017.
 */
public class ConvertToHex {
    public static void main(String args[]) throws Exception {
//        FileInputStream fis = new FileInputStream("C:\\Hist\\20170825_error_code\\plan.org");



        String test = "½á»é£¨2018£©";
//        String test = "结";

//        String unicode = string2Unicode(test);
//        String unicode = GBK2Unicode(test);

//        System.out.println(unicode);

//        String string = unicodeToUtf8(unicode);
//        String string = unicode2String(unicode);
//        String string = GBK2Unicode(test);
//        System.out.println(string);
    }

    /**
     * 字符串转换unicode
     */
//    public static String string2Unicode(String string) {
//
//        StringBuffer unicode = new StringBuffer();
//
//        for (int i = 0; i < string.length(); i++) {
//
//            // 取出每一个字符
//            char c = string.charAt(i);
//                // 转换为unicode
//                unicode.append(" " + Integer.toHexString(c));
//                char c1 = string.charAt(++i);
//                unicode.append(Integer.toHexString(c1));
//        }
//        return unicode.toString();
//    }

    /**
     * unicode 转字符串
     */
    public static String unicode2String(String unicode) {

        StringBuffer string = new StringBuffer();

        String[] hex = unicode.split("\\\\u");

        for (int i = 1; i < hex.length; i++) {

            // 转换出每一个代码点
            int data = Integer.parseInt(hex[i], 16);

            // 追加成string
            string.append((char) data);
        }
        return string.toString();
    }

    /**
     *
     * @param dataStr
     * @return String
     */

    public static String unicode2GBK(String dataStr) {
        int index = 0;
        StringBuffer buffer = new StringBuffer();

        int li_len = dataStr.length();
        while (index < li_len) {
            if (index >= li_len - 1
                    || !"\\u".equals(dataStr.substring(index, index + 2))) {
                buffer.append(dataStr.charAt(index));

                index++;
                continue;
            }

            String charStr = "";
            charStr = dataStr.substring(index + 2, index + 6);

            char letter = (char) Integer.parseInt(charStr, 16);

            buffer.append(letter);
            index += 6;
        }

        return buffer.toString();
    }

    /**
     *
     * @param str
     * @return String
     */

    public static String GBK2Unicode(String str) {
        StringBuffer result = new StringBuffer();
        for (int i = 0; i < str.length(); i++) {
            char chr1 = (char) str.charAt(i);

            if (!isNeedConvert(chr1)) {
                result.append(chr1);
                continue;
            }

            result.append("\\u" + Integer.toHexString((int) chr1));
        }

        return result.toString();
    }

    public static boolean isNeedConvert(char para) {
        return ((para & (0x00FF)) != para);
    }

    /**
     *
     * @param theString
     * @return String
     */
    public static String unicodeToUtf8(String theString) {
        char aChar;
        int len = theString.length();
        StringBuffer outBuffer = new StringBuffer(len);
        for (int x = 0; x < len;) {
            aChar = theString.charAt(x++);
            if (aChar == '\\') {
                aChar = theString.charAt(x++);
                if (aChar == 'u') {
                    // Read the xxxx
                    int value = 0;
                    for (int i = 0; i < 4; i++) {
                        aChar = theString.charAt(x++);
                        switch (aChar) {
                            case '0':
                            case '1':
                            case '2':
                            case '3':
                            case '4':
                            case '5':
                            case '6':
                            case '7':
                            case '8':
                            case '9':
                                value = (value << 4) + aChar - '0';
                                break;
                            case 'a':
                            case 'b':
                            case 'c':
                            case 'd':
                            case 'e':
                            case 'f':
                                value = (value << 4) + 10 + aChar - 'a';
                                break;
                            case 'A':
                            case 'B':
                            case 'C':
                            case 'D':
                            case 'E':
                            case 'F':
                                value = (value << 4) + 10 + aChar - 'A';
                                break;
                            default:
                                throw new IllegalArgumentException(
                                        "Malformed   \\uxxxx   encoding.");
                        }
                    }
                    outBuffer.append((char) value);
                } else {
                    if (aChar == 't')
                        aChar = '\t';
                    else if (aChar == 'r')
                        aChar = '\r';
                    else if (aChar == 'n')
                        aChar = '\n';
                    else if (aChar == 'f')
                        aChar = '\f';
                    outBuffer.append(aChar);
                }
            } else
                outBuffer.append(aChar);
        }
        return outBuffer.toString();
    }

}
