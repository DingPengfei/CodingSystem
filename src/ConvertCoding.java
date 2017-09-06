import java.io.*;

/**
 * Created by Ding on 9/5/2017.
 */
public class ConvertCoding {
    public static void main(String args[]) throws Exception {

//        FileInputStream fis = new FileInputStream("C:\\Hist\\20170825_error_code\\plan.org");
//        BufferedInputStream bis = new BufferedInputStream(fis);
//        String line = bis.toString();
//        String test = "½á»é£¨2018£©";
//        String test = "结";
//        String unicode = string2Unicode(test);
//        String unicode = GBK2Unicode(test);
//        System.out.println(unicode);
//        System.out.println(GBK2Chinese("b2e9"));
//        String fileGBKCode = readFile();
//        GBK2Chinese(fileGBKCode);

    }


//    public static String readFile() {
//        FileInputStream input = null;
//        String unicode = "";
//        try {
//            input = new FileInputStream("C:\\Hist\\20170825_error_code\\plan.org");
//            byte[] buffer = new byte[20];
//            while (true) {
//                int len = input.read(buffer);
//                if (len == -1) {
//                    break;
//                }
//                String str = new String(buffer, 0, len);
//                unicode = unicode + string2Unicode(str);
//            }
//            return unicode;
//        } catch (Exception e) {
//            throw new RuntimeException(e);
//        } finally {
//            try {
//                input.close();
//            } catch (Exception e) {
//                throw new RuntimeException(e);
//            }
//        }
//    }

//    public static String string2Unicode(String string) {
//
//        StringBuffer unicode = new StringBuffer();
//        for (int i = 0; i < string.length(); i++) {
//            // 取出每一个字符
//            char c = string.charAt(i);
//            // 转换为unicode
//            if (c > 127) {
//             unicode.append(Integer.toHexString(c));
//            }
//            else if (c == 13) {
//                continue;
//            } else if (c < 16 && c != 13) {
//                unicode.append("000" + Integer.toHexString(c));
//            } else {
//                unicode.append("00" + Integer.toHexString(c));
//            }
//        }
//        return unicode.toString();
//    }

    public static void GBK2Chinese(String s) {
        for (int i = 0; i < s.length()+1; i+=4) {
           System.out.print(GBK2ChieseOnebyOne(s.substring(i,i+4)));
        }
    }

    public static String GBK2ChieseOnebyOne(String s) {
        byte[] buf = new byte[2];
        buf[0] = (byte)Integer.parseInt( s.substring(0,2), 16 );
        buf[1] = (byte)Integer.parseInt( s.substring(2,4), 16 );
        try {
            return new String(buf,"GBK");
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
            return "";
        }
    }
}
