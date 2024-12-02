import org.joda.time.DateTime;
import org.joda.time.DateTimeZone;
import org.joda.time.format.DateTimeFormat;
import org.joda.time.format.DateTimeFormatter;

public class JodaDateParser {

  public static void test(final String val, final String format) {
    final DateTimeFormatter formatter = DateTimeFormat.forPattern(format);
    System.out.println("Date Str : " + val);
    System.out.println("Formatter: " + formatter);
    DateTime dt = formatter.parseDateTime(val);
    System.out.println("DateTime : " + dt);
    System.out.println("timestamp: " + dt.getMillis());
    dt = dt.withZoneRetainFields(DateTimeZone.forID("GMT"));
    System.out.println("DateTime : " + dt);
    System.out.println("timestamp: " + dt.getMillis());
  }

  public static void main(final String[] args) {
    if (args.length != 2) {
      System.err.println("usage: " + JodaDateParser.class.getCanonicalName() + " string pattern");
      System.exit(1);
    }
    String date = args[0];
    String pattern = args[1];
    JodaDateParser.test(date, pattern);
  }

}
