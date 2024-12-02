import java.text.MessageFormat;
import org.apache.commons.codec.binary.Base64;

public class Main {

  public static void main(final String[] args) {
    final Base64 base64 = new Base64();
    if (args.length < 1) {
      System.err.println("usage: " + Main.class.getCanonicalName() + " [encode|decode] str1 ...");
      System.exit(1);
    }
    int start;
    boolean encode;
    if (args.length > 1) {
      final String mode = args[0].toLowerCase();
      encode = mode.startsWith("e");
      start = 1;
    }
    else {
      final String txt = args[0].toLowerCase();
      encode = !txt.endsWith("=");
      start = 0;
    }
    for (int i = start; i < args.length; i++) {
      final String text = args[i];
      if (encode) {
        final String encodedVersion = new String(base64.encode(text.getBytes()));
        System.out.println(MessageFormat.format("{0} encoded version is {1}", text, encodedVersion));
      }
      else {
        final String decodedVersion = new String(base64.decode(text.getBytes()));
        System.out.println(MessageFormat.format("{0} decoded version is {1}", text, decodedVersion));
      }
    }
  }
}
