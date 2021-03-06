/*
 * Copyright (C) 2018 NATSRL @ UMD (University Minnesota Duluth)
 * 
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program.  If not, see <http://www.gnu.org/licenses/>.
 */
package common.pyticas.types;

import java.util.Calendar;
import java.util.Date;

/**
 *
 * @author Chongmyung Park <chongmyung.park@gmail.com>
 */
public class PyTICASDateTime {
    String __type__ = "datetime";
    Integer year;
    Integer month;
    Integer day;
    Integer hour;
    Integer minute;
    Integer second;
    Integer microsecond;
    String tzinfo;
    
    public Date getDateTime() {
        Calendar c = Calendar.getInstance();
        c.set(year, month, day, hour, minute, second);
        return c.getTime();
    }
}
