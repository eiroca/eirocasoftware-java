/**
 *
 * Copyright (C) 2001-2019 eIrOcA (eNrIcO Croce & sImOnA Burzio) - AGPL >= 3.0
 *
 * This program is free software: you can redistribute it and/or modify it under the terms of the
 * GNU Affero General Public License as published by the Free Software Foundation, either version 3
 * of the License, or (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful, but WITHOUT ANY WARRANTY; without
 * even the implied warranty of MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. See the GNU
 * Affero General Public License for more details.
 *
 * You should have received a copy of the GNU Affero General Public License along with this program.
 * If not, see <http://www.gnu.org/licenses/>.
 *
 **/
package net.eiroca.crmadapter.datamodel;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Properties;
import net.eiroca.crmadapter.httpinterface.HTTPMapping;

public class StorageHelper {

  private static final String null2blank(final String x) {
    if (x == null) { return ""; }
    return x;
  }

  public static ServiceIncident load(final String serviceIncidentID) {
    //TODO Da implementare il load dallo strato di persistenza
    ServiceIncident si = null;
    try {
      final FileInputStream fi = new FileInputStream("SI_" + serviceIncidentID + ".prop");
      final Properties p = new Properties();
      p.load(fi);
      final String ID = (String)p.get(HTTPMapping.PRM_SERVICEINCIDENTID);
      if (ID != null) {
        si = new ServiceIncident();
        si.setServiceIncidentID(ID);
        try {
          si.setState(Integer.parseInt((String)p.get(HTTPMapping.PRM_SERVICEINCIDENTSTATE)));
        }
        catch (final NumberFormatException ex) {
          si.setState(0);
        }
        si.setData((String)p.get(HTTPMapping.PRM_SERVICEINCIDENTDATA));
      }
    }
    catch (final IOException ex) {
    }
    return si;
  }

  public static boolean store(final ServiceIncident si) {
    //TODO Da implementare lo store verso lo strato di persistenza
    final String ID = si.getServiceIncidentID();
    try {
      final FileOutputStream fo = new FileOutputStream("SI_" + ID + ".prop");
      final Properties p = new Properties();
      p.put(HTTPMapping.PRM_SERVICEINCIDENTID, StorageHelper.null2blank(si.getServiceIncidentID()));
      p.put(HTTPMapping.PRM_SERVICEINCIDENTSTATE, StorageHelper.null2blank(Integer.toString(si.getState())));
      p.put(HTTPMapping.PRM_SERVICEINCIDENTDATA, StorageHelper.null2blank(si.getData()));
      p.store(fo, null);
      fo.close();
    }
    catch (final IOException ex) {
      return false;
    }
    return true;
  }

}
