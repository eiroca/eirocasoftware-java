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
package net.eiroca.modeller.network.test;

import net.eiroca.modeller.Scenario;
import net.eiroca.modeller.SimulationContext;
import net.eiroca.modeller.TimeLine;
import net.eiroca.modeller.TimeLineException;
import net.eiroca.modeller.network.HTTPTransaction;

public class HTTPTestScenario extends Scenario {

  HTTPTransaction transaction = new HTTPTransaction("http://A/B");

  @Override
  public void cleanup(final SimulationContext context) {
    context.sendMessage("URL " + transaction.req.host + transaction.req.path + " elapsed: " + transaction.getFullElapsed());
  }

  @Override
  public void setup(final SimulationContext context) {
    try {
      final TimeLine tl = context.getTimeLine();
      tl.addEvent(transaction);
    }
    catch (final TimeLineException e) {
      e.printStackTrace();
    }
  }

}
