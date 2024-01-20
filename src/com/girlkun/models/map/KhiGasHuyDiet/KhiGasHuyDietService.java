package com.girlkun.models.map.KhiGasHuyDiet;

import com.girlkun.models.item.Item;
import com.girlkun.models.boss.list_boss.KhiGasHuyDiet.Hatchiyack;
import com.girlkun.models.boss.list_boss.KhiGasHuyDiet.Hatchiyack1;
import com.girlkun.models.boss.list_boss.KhiGasHuyDiet.Hatchiyack2;
import com.girlkun.models.boss.list_boss.KhiGasHuyDiet.Hatchiyack3;
import com.girlkun.models.map.KhiGasHuyDiet.KhiGasHuyDiet;
import static com.girlkun.models.map.KhiGasHuyDiet.KhiGasHuyDiet.TIME_KHI_GA_HUY_DIET;
import com.girlkun.models.map.Zone;
import com.girlkun.models.mob.Mob;
import com.girlkun.models.player.Player;
import com.girlkun.services.InventoryServiceNew;
import com.girlkun.services.MapService;
import com.girlkun.services.Service;
import com.girlkun.services.func.ChangeMapService;
import com.girlkun.utils.Logger;
import com.girlkun.utils.Util;
import java.util.List;

/**
 *
 * @author BTH
 *
 */
public class KhiGasHuyDietService {

    private static KhiGasHuyDietService i;

    private KhiGasHuyDietService() {

    }

    public static KhiGasHuyDietService gI() {
        if (i == null) {
            i = new KhiGasHuyDietService();
        }
        return i;
    }
    
    public void openKhiGaHuyDiet(Player player, byte level) {
        if (level >= 1 && level <= 110) {
            if (player.clan != null && player.clan.KhiGaHuyDiet == null) {
                Item item = InventoryServiceNew.gI().findItemBag(player, 2127);
                if (item != null && item.quantity > 0) {
                    KhiGasHuyDiet khiGaHuyDiet = null;
                    for (KhiGasHuyDiet kghd : KhiGasHuyDiet.KHI_GA_HUY_DIETS) {
                        if (!kghd.isOpened) {
                            khiGaHuyDiet = kghd;
                            break;
                        }
                    }
                    if (khiGaHuyDiet != null) {
                        InventoryServiceNew.gI().subQuantityItemsBag(player, item, 1);
                        InventoryServiceNew.gI().sendItemBags(player);
                        khiGaHuyDiet.openKhiGaHuyDiet(player, player.clan, level);
                        try {
                            long totalDame = 0;
                            long totalHp = 0;
                            for (Player play : player.clan.membersInGame) {
                                totalDame += play.nPoint.dame;
                                totalHp += play.nPoint.hpMax;
                            }
                            long dame = (totalHp / 20) * (level);
                            long hp = (totalDame * 4) * (level);
                            if (dame >= 2000000000L) {
                                dame = 2000000000L;
                            }
                            if (hp >= 2000000000L) {
                                hp = 2000000000L;
                            }
                            new Hatchiyack(player.clan.KhiGaHuyDiet.getMapById(150), player.clan.KhiGaHuyDiet.level, (int) dame, (int) hp);
                            new Hatchiyack1(player.clan.KhiGaHuyDiet.getMapById(150), player.clan.KhiGaHuyDiet.level, (int) dame, (int) hp);
                            new Hatchiyack2(player.clan.KhiGaHuyDiet.getMapById(150), player.clan.KhiGaHuyDiet.level, (int) dame, (int) hp);
                            new Hatchiyack3(player.clan.KhiGaHuyDiet.getMapById(150), player.clan.KhiGaHuyDiet.level, (int) dame, (int) hp);
                        } catch (Exception exception) {
                            Logger.logException(KhiGasHuyDietService.class, exception, "Error initializing boss");
                        }
                    } else {
                        Service.getInstance().sendThongBao(player, "Khí Gas đã đầy, vui lòng quay lại sau");
                    }
                } else {
                    Service.getInstance().sendThongBao(player, "Yêu cầu có vé truy nã khí gas");
                }
            } else {
                Service.getInstance().sendThongBao(player, "Không thể thực hiện");
            }
        } else {
            Service.getInstance().sendThongBao(player, "Không thể thực hiện");
        }
    }
}
    
 
