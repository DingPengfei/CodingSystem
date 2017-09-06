import java.io.FileInputStream;
import java.io.UnsupportedEncodingException;

/**
 * Created by Ding on 9/6/2017.
 */
public class GibberishRecover {

    public static void main(String args[]) {
        gibbershRecover();
    }

    public static void gibbershRecover() {
        FileInputStream input = null;
        StringBuffer chinese = new StringBuffer();
        try {
            input = new FileInputStream("C:\\Hist\\20170825_error_code\\project.org");
            //increase the number when error occurs
            byte[] buffer = new byte[100000];
            while (true) {
                int len = input.read(buffer);
                if (len == -1) {
                    break;
                }
                String str = new String(buffer, 0, len);
                string2Chinese(str);
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        } finally {
            try {
                input.close();
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        }
    }

    public static void string2Chinese(String s) {
        StringBuffer ch = new StringBuffer();
        for (int i = 0; i < s.length(); i++) {
            char c = s.charAt(i);
            if (c > 127) {
                byte[] buf = new byte[2];
                buf[0] = (byte)c;
                buf[1] = (byte)s.charAt(++i);
                try {
                    ch.append(new StringBuffer(new String(buf, "GBK")));
                } catch (UnsupportedEncodingException e) {
                    e.printStackTrace();
                }
            } else if (c == 13) {
                continue;
            } else  {
                byte[] buf = new byte[1];
                buf[0] = (byte)c;
                try {
                    ch.append(new StringBuffer(new String(buf, "GBK")));
                } catch (UnsupportedEncodingException e) {
                    e.printStackTrace();
                }
            }
        }
        System.out.print(ch);
    }

}
