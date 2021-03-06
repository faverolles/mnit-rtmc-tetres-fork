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

package common.infra;

import java.util.List;

/**
 *
 * @author Chongmyung Park <chongmyung.park@gmail.com>
 */
public class Meter extends InfraObject {
    public String label;
    public String rnode_name;
    public Integer max_wait;
    public Integer storage;
    public Float lat;
    public Float lon;    
    public List<String> bypass;
    public List<String> green;
    public List<String> merge;
    public List<String> passage;
    public List<String> queue;
}
