import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import jcifs.smb.NtlmPasswordAuthenticator;
import jcifs.smb.SmbFile;
import net.eiroca.ext.library.smb.LibSmb;

public class SMBCopy {

  public static void main(final String argv[]) throws Exception {
    SmbFile src = null;
    String srcName = null;
    String dstName = null;
    boolean ok = false;
    NtlmPasswordAuthenticator a = null;
    if (argv.length > 0) {
      srcName = argv[0];
      ok = true;
      if (argv.length > 1) {
        dstName = argv[1];
        if (argv.length > 2) {
          ok = false;
          if (argv.length == 4) {
            a = getPrincipal(argv, 1);
            dstName = null;
            ok = true;
          }
          else if (argv.length == 5) {
            a = getPrincipal(argv, 2);
            ok = true;
          }
        }
      }
    }
    if (!ok) {
      System.err.println("usage: " + SMBCopy.class.getCanonicalName() + " <url> [dest] [<domain> <user> <password>]");
      System.exit(1);
    }
    src = (a != null) ? LibSmb.build(srcName, a) : LibSmb.build(srcName, LibSmb.getCIFSContext());
    if (src.isFile()) {
      if (dstName == null) dstName = src.getName();
      Path dest = Paths.get(dstName);
      System.out.println(src.getURL() + " -> " + dest);
      try (InputStream in = src.getInputStream()) {
        Files.copy(in, dest);
      }
      try {
        src.close();
      }
      catch (Exception e) {
      }
    }
  }

  private static NtlmPasswordAuthenticator getPrincipal(String[] argv, int i) {
    return new NtlmPasswordAuthenticator(argv[i], argv[i + 1], argv[i + 2]);
  }

}
