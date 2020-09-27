import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.Locale;
import java.util.StringTokenizer;

public class JavaDateParser {

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
    final DateTimeFormatter formatter = DateTimeFormatter.ofPattern(format, JavaDateParser.stringToLocale(locale)).withZone(ZoneId.systemDefault());
    System.out.println("Date Str : " + val);
    System.out.println("Formatter: " + formatter);
    ZonedDateTime dt = null;
    try {
      dt = ZonedDateTime.parse(val, formatter);
    }
    catch (final DateTimeParseException e) {
      int s = e.getErrorIndex() - 2;
      int end = s + 5;
      if (s < 0) {
        s = 0;
      }
      if (end > val.length()) {
        end = val.length();
      }
      System.out.println("Errore a: " + val.substring(s, end));
      // TODO Auto-generated catch block
      e.printStackTrace();
    }
    System.out.println("DateTime : " + dt);
    System.out.println("timestamp: " + dt.toInstant().toEpochMilli());
  }

  public static void main(final String[] args) {
    JavaDateParser.test("Wed Mar 08 05:11:05 2017", "eee MMM dd HH:mm:ss yyyy", "en");
    JavaDateParser.test("2017-03-09T13:04:32.124+01:00", "yyyy-MM-dd'T'HH:mm:ss.SSSXXX", "en");
    JavaDateParser.test("2017-03-09T13:04:32.124+02:00", "yyyy-MM-dd'T'HH:mm:ss.SSSXXX", "en");
  }

}
