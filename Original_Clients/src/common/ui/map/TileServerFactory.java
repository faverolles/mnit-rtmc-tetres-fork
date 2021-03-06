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
package common.ui.map;

import common.config.Config;
import common.infra.Infra;
import common.util.FileHelper;
import org.jdesktop.swingx.mapviewer.TileFactory;

/**
 *
 * @author Chongmyung Park <chongmyung.park@gmail.com>
 */
public class TileServerFactory {

    public static MapProvider defaultProvider = MapProvider.TMC;

    public static TileFactory getTileFactory() {
        return Config.getMapProvider().getProvider().getTileFactory();
    }

    public static TileFactory getTileFactory(MapProvider provider) {
        return provider.getProvider().getTileFactory();
    }
    //deletes the local Maps
    public static void clearCachedMap() {
        String mapDir = Infra.getInstance().getPath("map");
        FileHelper.deleteOldFiles(24*7, mapDir);
    }
}
