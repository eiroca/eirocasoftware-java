import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.Locale;
import java.util.StringTokenizer;

public class Main {

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
    Locale l = Main.stringToLocale(locale);
    ZoneId zid = ZoneId.systemDefault();
    final DateTimeFormatter formatter = DateTimeFormatter.ofPattern(format, l).withZone(zid);
    System.out.println("Date Str : " + val);
    System.out.println("Pattern  : " + format);
    System.out.println("Locale   : " + l);
    System.out.println("ZoneID   : " + zid);
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
      System.out.println("Error   @: " + val.substring(s, end));
      e.printStackTrace();
    }
    System.out.println("DateTime : " + dt);
    System.out.println("timestamp: " + dt.toInstant().toEpochMilli());
  }

  public static void main(final String[] args) {
    if ((args.length != 2) && (args.length != 3)) {
      System.err.println("usage: " + Main.class.getCanonicalName() + " string pattern [locale]");
      System.exit(1);
    }
    String date = args[0];
    String pattern = args[1];
    String locale = (args.length == 3) ? args[2] : "en";
    Main.test(date, pattern, locale);
  }

}
