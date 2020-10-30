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
package net.eiroca.crmadapter.process;

import java.util.HashMap;

public class ResultCode {

  public static final int OK = 0;
  public static final int KO = 1;
  public static final int KO_WRONGREQUEST = 2;
  public static final int KO_SERVICEINCIDENTID = 3;
  public static final int KO_SERVICEINCIDENT = 4;
  public static final int KO_HTTPCALL = 5;
  public static final int KO_HTTPRESPONSE = 6;

  private static final HashMap<Integer, String> errDesc = new HashMap<>();
  static {
    ResultCode.errDesc.put(new Integer(ResultCode.OK), "OK");
    ResultCode.errDesc.put(new Integer(ResultCode.KO), "KO");
    ResultCode.errDesc.put(new Integer(ResultCode.KO_WRONGREQUEST), "KO_WRONGREQUEST");
    ResultCode.errDesc.put(new Integer(ResultCode.KO_SERVICEINCIDENTID), "KO_SERVICEINCIDENTID");
    ResultCode.errDesc.put(new Integer(ResultCode.KO_SERVICEINCIDENT), "KO_SERVICEINCIDENT");
    ResultCode.errDesc.put(new Integer(ResultCode.KO_HTTPCALL), "KO_HTTPCALL");
    ResultCode.errDesc.put(new Integer(ResultCode.KO_HTTPRESPONSE), "KO_HTTPRESPONSE");
  }

  public static String decodeError(final int resCode) {
    String desc = ResultCode.errDesc.get(new Integer(resCode));
    if (desc == null) {
      desc = "Unknown Error";
    }
    return desc;
  }

}
