import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.GregorianCalendar;
import jcifs.ACE;
import jcifs.smb.NtlmPasswordAuthenticator;
import jcifs.smb.SmbFile;
import net.eiroca.ext.library.smb.LibSmb;

public class SMBCheck {

  static final String TYPES[] = {
      "TYPE_COMM",
      "TYPE_FILESYSTEM",
      "TYPE_NAMED_PIPE",
      "TYPE_PRINTER",
      "TYPE_SERVER",
      "TYPE_SHARE",
      "TYPE_WORKGROUP"
  };

  public static void main(final String argv[]) throws Exception {
    ACE[] security;
    final SimpleDateFormat sdf = new SimpleDateFormat("MM/dd/yy hh:mm:ss a");
    final GregorianCalendar cal = new GregorianCalendar();
    SmbFile f = null;
    if (argv.length == 1) {
      f = LibSmb.build(argv[0], LibSmb.getCIFSContext());
    }
    else if (argv.length == 4) {
      final NtlmPasswordAuthenticator a = new NtlmPasswordAuthenticator(argv[1], argv[2], argv[3]);
      System.out.println("            user: " + a.toString());
      f = LibSmb.build(argv[0], a);
    }
    else {
      System.err.println("usage: " + SMBCheck.class.getCanonicalName() + " <url> [<domain> <user> <password>]");
      System.exit(1);
    }

    sdf.setCalendar(cal);

    System.out.println("             URL: " + f.getURL());
    System.out.println("         getName: " + f.getName());
    System.out.println("          length: " + f.length());
    System.out.println(" getLastModified: " + sdf.format(new Date(f.getLastModified())));
    System.out.println("        isHidden: " + f.isHidden());
    System.out.println("          isFile: " + f.isFile());
    System.out.println("     isDirectory: " + f.isDirectory());
    System.out.println("        hashCode: " + f.hashCode());
    System.out.println("      getUncPath: " + f.getUncPath());
    System.out.println("         getType: " + SMBCheck.TYPES[f.getType()]);
    System.out.println("        getShare: " + f.getShare());
    System.out.println("       getServer: " + f.getServer());
    System.out.println("         getPath: " + f.getPath());
    System.out.println("       getParent: " + f.getParent());
    System.out.println("    lastModified: " + sdf.format(new Date(f.lastModified())));
    System.out.println("getDiskFreeSpace: " + f.getDiskFreeSpace());
    System.out.println("         getDate: " + sdf.format(new Date(f.getDate())));
    System.out.println("getCanonicalPath: " + f.getCanonicalPath());
    System.out.println("          exists: " + f.exists());
    System.out.println("         canRead: " + f.canRead());
    System.out.println("        canWrite: " + f.canWrite());
    System.out.println("      getDfsPath: " + f.getDfsPath());
    security = f.getSecurity(true);
    if (security != null) {
      System.out.println("        Security:");
      for (final ACE element : security) {
        if (element != null) {
          System.out.println(element.toString());
        }
      }
    }
    security = f.getShareSecurity(true);
    if (security != null) {
      System.out.println("      Share Perm:");
      for (final ACE element : security) {
        if (element != null) {
          System.out.println(element.toString());
        }
      }
    }
  }

}
