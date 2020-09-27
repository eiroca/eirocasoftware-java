import java.util.Locale;
import java.util.StringTokenizer;
import org.joda.time.DateTime;
import org.joda.time.DateTimeZone;
import org.joda.time.format.DateTimeFormat;
import org.joda.time.format.DateTimeFormatter;

public class JodaDateParser {

  public static String localeToString(final Locale l) {
    return l.getLanguage() + "," + l.getCountry();
  }

  public static Locale stringToLocale(final String s) {
    final StringTokenizer tempStringTokenizer = new StringTokenizer(s, ",");
    if (tempStringTokenizer.hasMoreTokens()) {
      final String l = tempStringTokenizer.nextToken();
      if (tempStringTokenizer.hasMoreTokens()) {
        final String c = tempStringTokenizer.nextToken();
        return new Locale(l, c);
      }
      return new Locale(l);
    }
    return null;
  }

  public static void test(final String val, final String format, final String locale) {
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
    // test("Mar 27, 2017 1:00:56 AM", "MMM dd, yyyy h:mm:ss a", "en");
    JodaDateParser.test("2018-08-29 12:38:22", "yyyy-MM-dd HH:mm:ss", "en");
  }

}
