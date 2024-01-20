package com.girlkun.services;
import com.girlkun.database.GirlkunDB;
import com.girlkun.jdbc.daos.PlayerDAO;
import com.girlkun.models.item.Item;

import com.girlkun.models.player.NPoint;
import com.girlkun.models.player.Pet;
import com.girlkun.models.player.Player;
import com.girlkun.server.Client;
import com.girlkun.utils.Util;
import java.awt.Point;

public class OpenPowerService {

    public static final int COST_SPEED_OPEN_LIMIT_POWER = 500000000;

    private static OpenPowerService i;

    private OpenPowerService() {

    }

    public static OpenPowerService gI() {
        if (i == null) {
            i = new OpenPowerService();
        }
        return i;
    }

    public boolean openPowerBasic(Player player) {
        byte curLimit = player.nPoint.limitPower;
        if (curLimit < NPoint.MAX_LIMIT) {
            if (!player.itemTime.isOpenPower && player.nPoint.canOpenPower()) {
                player.itemTime.isOpenPower = true;
                player.itemTime.lastTimeOpenPower = System.currentTimeMillis();
                ItemTimeService.gI().sendAllItemTime(player);
                return true;
            } else {
                Service.gI().sendThongBao(player, "Sức mạnh của bạn không đủ để thực hiện");
                return false;
            }
        } else {
            Service.gI().sendThongBao(player, "Sức mạnh của bạn đã đạt tới mức tối đa");
            return false;
        }
    }
    
    public boolean chuyenSinh(Player player) {
        if (InventoryServiceNew.gI().getCountEmptyBag(player) <= 0)  {
            Service.gI().sendThongBao(player, "Hành trang không đủ chỗ trống"); 
            } else {
        if (player.nPoint.power >= 500000000000L) {
            int hongngoc = 0;
        //    int soluong = 0;
            Item linhhon = null;
            Item fire = null;
            Item water = null;
            Item wood = null;
            Item metal = null;
            Item tv = null;
            linhhon = InventoryServiceNew.gI().findItemBag(player, 2157);
            fire = InventoryServiceNew.gI().findItemBag(player, 2153);
            water = InventoryServiceNew.gI().findItemBag(player, 2154);
            wood = InventoryServiceNew.gI().findItemBag(player, 2155);
            metal = InventoryServiceNew.gI().findItemBag(player, 2156);
            metal = InventoryServiceNew.gI().findItemBag(player, 457);
            hongngoc = 1000;
        //    soluong = 199;
            player.inventory.ruby -= hongngoc;
            InventoryServiceNew.gI().subQuantityItemsBag(player, linhhon, 99);
            InventoryServiceNew.gI().subQuantityItemsBag(player, fire, 99);
            InventoryServiceNew.gI().subQuantityItemsBag(player, water, 99);
            InventoryServiceNew.gI().subQuantityItemsBag(player, wood, 99);
            InventoryServiceNew.gI().subQuantityItemsBag(player, metal, 99);
            InventoryServiceNew.gI().subQuantityItemsBag(player, tv, 99);
            player.nPoint.power -= (player.nPoint.power - 2000);
            player.chuyenSinh++;
            player.nPoint.hpg += 50000;
            player.nPoint.dameg += 2000;
            player.nPoint.mpg += 50000;
            Service.getInstance().point(player);
            Client.gI().kickSession(player.getSession());
        if (player.nPoint.power < 179999999999L) {
            }          
        if (!player.isPet) {
            Service.gI().sendThongBao(player, "Giới hạn sức mạnh của bạn đã được reset");
        } else {
            Service.gI().sendThongBao(((Pet) player).master, "Giới hạn sức mạnh của đệ tử đã được reset");
        }
            return true; 
        } else {
            if (!player.isPet) {
                Service.gI().sendThongBao(player, "Bạn không đủ điều kiện để chuyển sinh");
            } else {
                Service.gI().sendThongBao(((Pet) player).master, "Bạn không đủ điều kiện để chuyển sinh");
            }
            return false;
        }
        }
        return true;
    }
    
    public boolean openPowerSpeed(Player player) {
        if (player.nPoint.limitPower < NPoint.MAX_LIMIT) {
            if (player.nPoint.power >= 17900000000L && player.nPoint.limitPower < 1) {
            player.nPoint.limitPower += 1;
            if (player.nPoint.limitPower > NPoint.MAX_LIMIT) {
                player.nPoint.limitPower = NPoint.MAX_LIMIT;
            }
            if (!player.isPet) {
                Service.getInstance().sendThongBao(player, "Giới hạn sức mạnh của bạn đã được tăng lên 1 bậc");
            } else {
                Service.getInstance().sendThongBao(((Pet) player).master, "Giới hạn sức mạnh của đệ tử đã được tăng lên 1 bậc");
            }
            return true;
            }
            if (player.nPoint.power >= 19900000000L && player.nPoint.limitPower < 2) {
            player.nPoint.limitPower += 1;
            if (player.nPoint.limitPower > NPoint.MAX_LIMIT) {
                player.nPoint.limitPower = NPoint.MAX_LIMIT;
            }
            if (!player.isPet) {
                Service.getInstance().sendThongBao(player, "Giới hạn sức mạnh của bạn đã được tăng lên 1 bậc");
            } else {
                Service.getInstance().sendThongBao(((Pet) player).master, "Giới hạn sức mạnh của đệ tử đã được tăng lên 1 bậc");
            }
            return true;
            }
            if (player.nPoint.power >= 24900000000L && player.nPoint.limitPower < 3) {
            player.nPoint.limitPower += 1;
            if (player.nPoint.limitPower > NPoint.MAX_LIMIT) {
                player.nPoint.limitPower = NPoint.MAX_LIMIT;
            }
            if (!player.isPet) {
                Service.getInstance().sendThongBao(player, "Giới hạn sức mạnh của bạn đã được tăng lên 1 bậc");
            } else {
                Service.getInstance().sendThongBao(((Pet) player).master, "Giới hạn sức mạnh của đệ tử đã được tăng lên 1 bậc");
            }
            return true;
            }
            if (player.nPoint.power >= 29900000000L && player.nPoint.limitPower < 4) {
            player.nPoint.limitPower += 1;
            if (player.nPoint.limitPower > NPoint.MAX_LIMIT) {
                player.nPoint.limitPower = NPoint.MAX_LIMIT;
            }
            if (!player.isPet) {
                Service.getInstance().sendThongBao(player, "Giới hạn sức mạnh của bạn đã được tăng lên 1 bậc");
            } else {
                Service.getInstance().sendThongBao(((Pet) player).master, "Giới hạn sức mạnh của đệ tử đã được tăng lên 1 bậc");
            }
            return true;
            }
            if (player.nPoint.power >= 39900000000L && player.nPoint.limitPower < 5) {
            player.nPoint.limitPower += 1;
            if (player.nPoint.limitPower > NPoint.MAX_LIMIT) {
                player.nPoint.limitPower = NPoint.MAX_LIMIT;
            }
            if (!player.isPet) {
                Service.getInstance().sendThongBao(player, "Giới hạn sức mạnh của bạn đã được tăng lên 1 bậc");
            } else {
                Service.getInstance().sendThongBao(((Pet) player).master, "Giới hạn sức mạnh của đệ tử đã được tăng lên 1 bậc");
            }
            return true;
            }
            if (player.nPoint.power >= 50000000000L && player.nPoint.limitPower < 6) {
            player.nPoint.limitPower += 1;
            if (player.nPoint.limitPower > NPoint.MAX_LIMIT) {
                player.nPoint.limitPower = NPoint.MAX_LIMIT;
            }
            if (!player.isPet) {
                Service.getInstance().sendThongBao(player, "Giới hạn sức mạnh của bạn đã được tăng lên 1 bậc");
            } else {
                Service.getInstance().sendThongBao(((Pet) player).master, "Giới hạn sức mạnh của đệ tử đã được tăng lên 1 bậc");
            }
            return true;
            }
            if (player.nPoint.power >= 70000000000L && player.nPoint.limitPower < 7) {
            player.nPoint.limitPower += 1;
            if (player.nPoint.limitPower > NPoint.MAX_LIMIT) {
                player.nPoint.limitPower = NPoint.MAX_LIMIT;
            }
            if (!player.isPet) {
                Service.getInstance().sendThongBao(player, "Giới hạn sức mạnh của bạn đã được tăng lên 1 bậc");
            } else {
                Service.getInstance().sendThongBao(((Pet) player).master, "Giới hạn sức mạnh của đệ tử đã được tăng lên 1 bậc");
            }
            return true;
            }
            if (player.nPoint.power >= 90000000000L && player.nPoint.limitPower < 8) {
            player.nPoint.limitPower += 1;
            if (player.nPoint.limitPower > NPoint.MAX_LIMIT) {
                player.nPoint.limitPower = NPoint.MAX_LIMIT;
            }
            if (!player.isPet) {
                Service.getInstance().sendThongBao(player, "Giới hạn sức mạnh của bạn đã được tăng lên 1 bậc");
            } else {
                Service.getInstance().sendThongBao(((Pet) player).master, "Giới hạn sức mạnh của đệ tử đã được tăng lên 1 bậc");
            }
            return true;
            }
            if (player.nPoint.power >= 120000000000L && player.nPoint.limitPower < 9) {
            player.nPoint.limitPower += 1;
            if (player.nPoint.limitPower > NPoint.MAX_LIMIT) {
                player.nPoint.limitPower = NPoint.MAX_LIMIT;
            }
            if (!player.isPet) {
                Service.getInstance().sendThongBao(player, "Giới hạn sức mạnh của bạn đã được tăng lên 1 bậc");
            } else {
                Service.getInstance().sendThongBao(((Pet) player).master, "Giới hạn sức mạnh của đệ tử đã được tăng lên 1 bậc");
            }
            return true;
            }
            if (player.nPoint.power >= 170000000000L && player.nPoint.limitPower < 10) {
            player.nPoint.limitPower += 1;
            if (player.nPoint.limitPower > NPoint.MAX_LIMIT) {
                player.nPoint.limitPower = NPoint.MAX_LIMIT;
            }
            if (!player.isPet) {
                Service.getInstance().sendThongBao(player, "Giới hạn sức mạnh của bạn đã được tăng lên 1 bậc");
            } else {
                Service.getInstance().sendThongBao(((Pet) player).master, "Giới hạn sức mạnh của đệ tử đã được tăng lên 1 bậc");
            }
            return true;
            }
            if (player.nPoint.power >= 17000000000L && player.nPoint.limitPower < 11) {
            player.nPoint.limitPower += 1;
            if (player.nPoint.limitPower > NPoint.MAX_LIMIT) {
                player.nPoint.limitPower = NPoint.MAX_LIMIT;
            }
            if (!player.isPet) {
                Service.getInstance().sendThongBao(player, "Giới hạn sức mạnh của bạn đã được tăng lên 1 bậc");
            } else {
                Service.getInstance().sendThongBao(((Pet) player).master, "Giới hạn sức mạnh của đệ tử đã được tăng lên 1 bậc");
            }
            return true;
            }
            if (player.nPoint.power >= 17000000000L && player.nPoint.limitPower < 12) {
            player.nPoint.limitPower += 1;
            if (player.nPoint.limitPower > NPoint.MAX_LIMIT) {
                player.nPoint.limitPower = NPoint.MAX_LIMIT;
            }
            if (!player.isPet) {
                Service.getInstance().sendThongBao(player, "Giới hạn sức mạnh của bạn đã được tăng lên 1 bậc");
            } else {
                Service.getInstance().sendThongBao(((Pet) player).master, "Giới hạn sức mạnh của đệ tử đã được tăng lên 1 bậc");
            }
            return true;
            }
            if (player.nPoint.power >= 17000000000L && player.nPoint.limitPower < 13) {
            player.nPoint.limitPower += 1;
            if (player.nPoint.limitPower > NPoint.MAX_LIMIT) {
                player.nPoint.limitPower = NPoint.MAX_LIMIT;
            }
            if (!player.isPet) {
                Service.getInstance().sendThongBao(player, "Giới hạn sức mạnh của bạn đã được tăng lên 1 bậc");
            } else {
                Service.getInstance().sendThongBao(((Pet) player).master, "Giới hạn sức mạnh của đệ tử đã được tăng lên 1 bậc");
            }
            return true;
            }
            if (player.nPoint.power >= 17000000000L && player.nPoint.limitPower < 14) {
            player.nPoint.limitPower += 1;
            if (player.nPoint.limitPower > NPoint.MAX_LIMIT) {
                player.nPoint.limitPower = NPoint.MAX_LIMIT;
            }
            if (!player.isPet) {
                Service.getInstance().sendThongBao(player, "Giới hạn sức mạnh của bạn đã được tăng lên 1 bậc");
            } else {
                Service.getInstance().sendThongBao(((Pet) player).master, "Giới hạn sức mạnh của đệ tử đã được tăng lên 1 bậc");
            }
            return true;
            }
            if (player.nPoint.power >= 17000000000L && player.nPoint.limitPower < 15) {
            player.nPoint.limitPower += 1;
            if (player.nPoint.limitPower > NPoint.MAX_LIMIT) {
                player.nPoint.limitPower = NPoint.MAX_LIMIT;
            }
            if (!player.isPet) {
                Service.getInstance().sendThongBao(player, "Giới hạn sức mạnh của bạn đã được tăng lên 1 bậc");
            } else {
                Service.getInstance().sendThongBao(((Pet) player).master, "Giới hạn sức mạnh của đệ tử đã được tăng lên 1 bậc");
            }
            return true;
            }
            if (player.nPoint.power >= 17000000000L && player.nPoint.limitPower < 16) {
            player.nPoint.limitPower += 1;
            if (player.nPoint.limitPower > NPoint.MAX_LIMIT) {
                player.nPoint.limitPower = NPoint.MAX_LIMIT;
            }
            if (!player.isPet) {
                Service.getInstance().sendThongBao(player, "Giới hạn sức mạnh của bạn đã được tăng lên 1 bậc");
            } else {
                Service.getInstance().sendThongBao(((Pet) player).master, "Giới hạn sức mạnh của đệ tử đã được tăng lên 1 bậc");
            }
            return true;
            }
            if (player.nPoint.power >= 17000000000L && player.nPoint.limitPower < 17) {
            player.nPoint.limitPower += 1;
            if (player.nPoint.limitPower > NPoint.MAX_LIMIT) {
                player.nPoint.limitPower = NPoint.MAX_LIMIT;
            }
            if (!player.isPet) {
                Service.getInstance().sendThongBao(player, "Giới hạn sức mạnh của bạn đã được tăng lên 1 bậc");
            } else {
                Service.getInstance().sendThongBao(((Pet) player).master, "Giới hạn sức mạnh của đệ tử đã được tăng lên 1 bậc");
            }
            return true;
            }
            if (player.nPoint.power >= 17000000000L && player.nPoint.limitPower < 18) {
            player.nPoint.limitPower += 1;
            if (player.nPoint.limitPower > NPoint.MAX_LIMIT) {
                player.nPoint.limitPower = NPoint.MAX_LIMIT;
            }
            if (!player.isPet) {
                Service.getInstance().sendThongBao(player, "Giới hạn sức mạnh của bạn đã được tăng lên 1 bậc");
            } else {
                Service.getInstance().sendThongBao(((Pet) player).master, "Giới hạn sức mạnh của đệ tử đã được tăng lên 1 bậc");
            }
            return true;
            }
            if (player.nPoint.power >= 17000000000L && player.nPoint.limitPower < 19) {
            player.nPoint.limitPower += 1;
            if (player.nPoint.limitPower > NPoint.MAX_LIMIT) {
                player.nPoint.limitPower = NPoint.MAX_LIMIT;
            }
            if (!player.isPet) {
                Service.getInstance().sendThongBao(player, "Giới hạn sức mạnh của bạn đã được tăng lên 1 bậc");
            } else {
                Service.getInstance().sendThongBao(((Pet) player).master, "Giới hạn sức mạnh của đệ tử đã được tăng lên 1 bậc");
            }
            return true;
            }
            if (player.nPoint.power >= 17000000000L && player.nPoint.limitPower < 20) {
            player.nPoint.limitPower += 1;
            if (player.nPoint.limitPower > NPoint.MAX_LIMIT) {
                player.nPoint.limitPower = NPoint.MAX_LIMIT;
            }
            if (!player.isPet) {
                Service.getInstance().sendThongBao(player, "Giới hạn sức mạnh của bạn đã được tăng lên 1 bậc");
            } else {
                Service.getInstance().sendThongBao(((Pet) player).master, "Giới hạn sức mạnh của đệ tử đã được tăng lên 1 bậc");
            }
            return true;
            } else {
                if (!player.isPet) {
                    Service.getInstance().sendThongBao(player, "Sức mạnh của bạn không đủ để thực hiện");
                } else {
                    Service.getInstance().sendThongBao(((Pet) player).master, "Sức mạnh của đệ tử không đủ để thực hiện");
                }
                return false;
            }
        } else {
            if (!player.isPet) {
                Service.getInstance().sendThongBao(player, "Sức mạnh của bạn đã đạt tới mức tối đa");
            } else {
                Service.getInstance().sendThongBao(((Pet) player).master, "Sức mạnh của đệ tử đã đạt tới mức tối đa");
            }
            return false;
            
        }
    }

}
