package com.girlkun.models.map.ConDuongRanDoc;


import com.girlkun.models.boss.list_boss.ConDuongRanDoc.Nappa;
import com.girlkun.models.boss.list_boss.doanh_trai.NinjaTim;
import com.girlkun.models.item.Item;
import com.girlkun.models.boss.list_boss.KhiGasHuyDiet.Hatchiyack;
import com.girlkun.models.boss.list_boss.ConDuongRanDoc.Saibamen;
import com.girlkun.models.boss.list_boss.ConDuongRanDoc.Saibamen2;
import com.girlkun.models.boss.list_boss.ConDuongRanDoc.Saibamen3;
import com.girlkun.models.boss.list_boss.ConDuongRanDoc.Saibamen4;
import com.girlkun.models.boss.list_boss.ConDuongRanDoc.Saibamen5;
import com.girlkun.models.boss.list_boss.ConDuongRanDoc.Saibamen6;
import com.girlkun.models.boss.list_boss.ConDuongRanDoc.Vegeta;
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
public class ConDuongRanDocService {

    private static ConDuongRanDocService i;

    private ConDuongRanDocService() {

    }

    public static ConDuongRanDocService gI() {
        if (i == null) {
            i = new ConDuongRanDocService();
        }
        return i;
    }
    
    public void openConDuongRanDoc(Player player, byte level) {
        if (level >= 1 && level <= 110) {
            if (player.clan != null && player.clan.ConDuongRanDoc == null) {
                Item item = InventoryServiceNew.gI().findItemBag(player, 2128);
                if (item != null && item.quantity > 0) {
                    ConDuongRanDoc conDuongRanDoc = null;
                    for (ConDuongRanDoc cdrd : ConDuongRanDoc.CON_DUONG_RAN_DOCS) {
                        if (!cdrd.isOpened) {
                            conDuongRanDoc = cdrd;
                            break;
                        }
                    }
                    if (conDuongRanDoc != null) {
                        InventoryServiceNew.gI().subQuantityItemsBag(player, item, 1);
                        InventoryServiceNew.gI().sendItemBags(player);
                        conDuongRanDoc.openConDuongRanDoc(player, player.clan, level);
                        try {
                            long bossDamage = (20 * level);
                            long bossMaxHealth = (2 * level);
                            bossDamage = Math.min(bossDamage, 200000000L);
                            bossMaxHealth = Math.min(bossMaxHealth, 2000000000L);
                            
                            Nappa boss = new Nappa(
                                    player.clan.ConDuongRanDoc.getMapById(144),
                                    player.clan.ConDuongRanDoc.level,
                                    (int) bossDamage,
                                    (int) bossMaxHealth
                            ); 
                            Saibamen boss1  = new Saibamen(
                                    player.clan.ConDuongRanDoc.getMapById(144),
                                    player.clan.ConDuongRanDoc.level,
                                    (int) bossDamage,
                                    (int) bossMaxHealth
                            );   
                            Saibamen2 boss2 = new Saibamen2(
                                    player.clan.ConDuongRanDoc.getMapById(144),
                                    player.clan.ConDuongRanDoc.level,
                                    (int) bossDamage,
                                    (int) bossMaxHealth
                            );
                            Saibamen3 boss3 = new Saibamen3(
                                    player.clan.ConDuongRanDoc.getMapById(144),
                                    player.clan.ConDuongRanDoc.level,
                                    (int) bossDamage,
                                    (int) bossMaxHealth
                            );
                            Saibamen4 boss4 = new Saibamen4(
                                    player.clan.ConDuongRanDoc.getMapById(144),
                                    player.clan.ConDuongRanDoc.level,
                                    (int) bossDamage,
                                    (int) bossMaxHealth
                            );
                            Saibamen5 boss5 = new Saibamen5(
                                    player.clan.ConDuongRanDoc.getMapById(144),
                                    player.clan.ConDuongRanDoc.level,
                                    (int) bossDamage,
                                    (int) bossMaxHealth
                            );
                            Saibamen6 boss6 = new Saibamen6(
                                    player.clan.ConDuongRanDoc.getMapById(144),
                                    player.clan.ConDuongRanDoc.level,
                                    (int) bossDamage,
                                    (int) bossMaxHealth
                            );
                            Vegeta boss7 = new Vegeta(
                                    player.clan.ConDuongRanDoc.getMapById(144),
                                    player.clan.ConDuongRanDoc.level,
                                    (int) bossDamage,
                                    (int) bossMaxHealth
                            );
                        } catch (Exception exception) {
                            Logger.logException(ConDuongRanDocService.class, exception, "Error initializing boss");
                        }
                    } else {
                        Service.getInstance().sendThongBao(player, "Con Đường Rắn Độc Đã Đầy, vui lòng quay lại sau");
                    }
                } else {
                    Service.getInstance().sendThongBao(player, "Yêu cầu có vé truy nã Cđrđ ");
                }
            } else {
                Service.getInstance().sendThongBao(player, "Không thể thực hiện");
            }
        } else {
            Service.getInstance().sendThongBao(player, "Không thể thực hiện");
        }
    }
}
