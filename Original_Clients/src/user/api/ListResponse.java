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
package user.api;

import common.pyticas.responses.Response;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Chongmyung Park <chongmyung.park@gmail.com>
 */
public class ListResponse<T> extends Response {
    
    public class ListObject<T> {
        public List<T> list = new ArrayList<>();
    }
    public ListObject<T> obj = new ListObject<>();
    
    public ListResponse() {

    }

}
