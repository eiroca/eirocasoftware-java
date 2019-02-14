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
package net.eiroca.modeller.test;

import java.util.Date;
import net.eiroca.modeller.Event;
import net.eiroca.modeller.Scenario;
import net.eiroca.modeller.SimulationContext;
import net.eiroca.modeller.TimeLineException;

public class TestScenario extends Scenario {

  @Override
  public void setup(final SimulationContext context) {
    try {
      final Date now1 = new Date(100000000);
      final Date now2 = new Date(200000000);
      Event e;
      e = new CheOraE(now1);
      context.getTimeLine().addEvent(e);
      e = new CheOraE(now2);
      context.getTimeLine().addEvent(e);
    }
    catch (final TimeLineException e) {
      e.printStackTrace();
    }
  }

}
