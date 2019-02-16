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
package net.eiroca.crmadapter.httpinterface.client;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import org.apache.http.NameValuePair;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.message.BasicNameValuePair;
import net.eiroca.crmadapter.datamodel.ServiceIncident;
import net.eiroca.crmadapter.httpinterface.HTTPMapping;
import net.eiroca.crmadapter.process.ResultCode;
import net.eiroca.ext.library.http.HttpClientHelper;

public class HTTPUtils {

  public static int sendPost(final String URL, final String action, final ServiceIncident si) {
    int resCode;
    HttpPost post;
    CloseableHttpClient httpclient;
    try {
      // Prepare HTTP post
      final List<NameValuePair> postParams = new ArrayList<>();
      postParams.add(new BasicNameValuePair(HTTPMapping.PRM_ACTION, action));
      postParams.add(new BasicNameValuePair(HTTPMapping.PRM_SERVICEINCIDENTID, si.getServiceIncidentID()));
      postParams.add(new BasicNameValuePair(HTTPMapping.PRM_SERVICEINCIDENTSTATE, Integer.toString(si.getState())));
      postParams.add(new BasicNameValuePair(HTTPMapping.PRM_SERVICEINCIDENTDATA, si.getData()));

      post = new HttpPost(URL);
      post.setEntity(new UrlEncodedFormEntity(postParams));
      // Get HTTP client
      httpclient = HttpClientHelper.getHttpClient(null);
    }
    catch (final Exception e) {
      resCode = ResultCode.KO_HTTPCALL;
      return resCode;
    }
    try (CloseableHttpResponse response = httpclient.execute(post)) {
      final int result = response.getStatusLine().getStatusCode();
      if (result == 200) {
        resCode = ResultCode.OK;
      }
      else {
        resCode = ResultCode.KO_HTTPRESPONSE;
      }
    }
    catch (final IOException e) {
      resCode = ResultCode.KO_HTTPCALL;
    }
    try {
      httpclient.close();
    }
    catch (final IOException e) {
    }
    return resCode;
  }
}
