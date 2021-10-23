import java.text.MessageFormat;
import org.apache.commons.codec.binary.Base64;

public class Main {

  public static void main(final String[] args) {
    final Base64 base64 = new Base64();
    if (args.length > 0) {
      for (int i = 0; i < args.length; i++) {
        String text = args[i];
        final String encodedVersion = new String(base64.encode(text.getBytes()));
        final String decodedVersion = new String(base64.decode(text.getBytes()));
        System.out.println(MessageFormat.format("{0} Encoded Version is {1}", text, encodedVersion));
        System.out.println(MessageFormat.format("{0} Decoded version is {1}", text, decodedVersion));
      }
    }
  }
}
