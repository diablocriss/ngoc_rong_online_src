package com.girlkun.models.npc;

import com.girlkun.consts.ConstMap;
import com.girlkun.models.boss.list_boss.nappa.Kuku;
import com.girlkun.server.ServerManager;
import com.girlkun.server.io.MySession;
import com.girlkun.services.*;
import com.girlkun.consts.ConstNpc;
import com.girlkun.consts.ConstPlayer;
import com.girlkun.consts.ConstTask;
import com.girlkun.database.GirlkunDB;
import com.girlkun.jdbc.daos.PlayerDAO;
import com.girlkun.kygui.ShopKyGuiService;
import com.girlkun.models.boss.Boss;
import com.girlkun.models.boss.BossData;
import com.girlkun.models.boss.BossID;
import com.girlkun.models.boss.BossManager;
import com.girlkun.models.boss.list_boss.FamVang;
import com.girlkun.models.boss.list_boss.NhanBan;
import com.girlkun.models.boss.list_boss.NguoiChoi;
import com.girlkun.models.clan.Clan;
import com.girlkun.models.clan.ClanMember;

import java.util.HashMap;
import java.util.List;

import com.girlkun.services.func.ChangeMapService;
import com.girlkun.services.func.SummonDragon;
import static com.girlkun.services.func.SummonDragon.SHENRON_1_STAR_WISHES_1;
import static com.girlkun.services.func.SummonDragon.SHENRON_1_STAR_WISHES_2;
import static com.girlkun.services.func.SummonDragon.SHENRON_SAY;
import com.girlkun.models.player.Player;
import com.girlkun.models.item.Item;
import com.girlkun.models.item.Item.ItemOption;
import com.girlkun.models.map.Map;
import com.girlkun.models.map.Zone;
import com.girlkun.models.map.blackball.BlackBallWar;
import com.girlkun.models.map.MapMaBu.MapMaBu;
import com.girlkun.models.map.DaiHoiVoThuat23.MartialCongressService;
import com.girlkun.models.map.DoanhTraiDocNhan.DoanhTrai;
import com.girlkun.models.map.DoanhTraiDocNhan.DoanhTraiService;
import com.girlkun.models.map.BanDoKhoBau.BanDoKhoBau;
import com.girlkun.models.map.BanDoKhoBau.BanDoKhoBauService;
import com.girlkun.models.map.ConDuongRanDoc.ConDuongRanDoc;
import com.girlkun.models.map.ConDuongRanDoc.ConDuongRanDocService;
import com.girlkun.models.map.KhiGasHuyDiet.KhiGasHuyDiet;
import com.girlkun.models.map.KhiGasHuyDiet.KhiGasHuyDietService;
import com.girlkun.models.player.Inventory;
import com.girlkun.models.player.NPoint;
import com.girlkun.models.matches.PVPService;
import com.girlkun.models.matches.pvp.DaiHoiVoThuat;
import com.girlkun.models.matches.pvp.DaiHoiVoThuatService;
import com.girlkun.models.shop.ShopServiceNew;
import com.girlkun.models.skill.Skill;
import com.girlkun.server.Client;
import com.girlkun.server.Maintenance;
import com.girlkun.server.Manager;
import com.girlkun.services.func.CombineServiceNew;
import com.girlkun.services.func.Input;
import com.girlkun.services.func.LuckyRound;
import com.girlkun.services.func.TopService;
import com.girlkun.utils.Logger;
import com.girlkun.utils.TimeUtil;
import com.girlkun.utils.Util;
import java.util.ArrayList;
import com.girlkun.services.func.ChonAiDay;
import com.barcoll.MaQuaTang.MaQuaTangManager;
import com.girlkun.models.map.Mapchichi.Map22h;
import com.girlkun.models.map.Mapmabu2h.mabu2h;
//import static com.girlkun.services.func.CombineServiceNew.kh_Hd;
//import static com.girlkun.services.func.CombineServiceNew.kh_Tl;
//import static com.girlkun.services.func.CombineServiceNew.kh_Ts;
import com.girlkun.services.func.*;
import static com.girlkun.services.func.CombineServiceNew.NANG_CAP_SKH_VIP;
import java.util.logging.Level;
import java.util.Timer;
import java.util.TimerTask;
import java.util.logging.Level;

public class NpcFactory {

    private static final int COST_HD = 50000000;

    private static boolean nhanVang = false;
    private static boolean nhanDeTu = false;

    //playerid - object
    public static final java.util.Map<Long, Object> PLAYERID_OBJECT = new HashMap<Long, Object>();

    private static Npc onggianoel(int mapId, int status, int cx, int cy, int tempId, int avatar) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    public static Npc GhiDanh(int mapId, int status, int cx, int cy, int tempId, int avartar) {
        return new Npc(mapId, status, cx, cy, tempId, avartar) {
            String[] menuselect = new String[]{};

            @Override
            public void openBaseMenu(Player pl) {
                if (canOpenNpc(pl)) {
                    if (this.mapId == 52) {
                        createOtherMenu(pl, 0, DaiHoiVoThuatService.gI(DaiHoiVoThuat.gI().getDaiHoiNow()).Giai(pl), "Thông tin\nChi tiết", DaiHoiVoThuatService.gI(DaiHoiVoThuat.gI().getDaiHoiNow()).CanReg(pl) ? "Đăng ký" : "OK", "Đại Hội\nVõ Thuật\nLần thứ\n23");
                    } else if (this.mapId == 129) {
                        int goldchallenge = pl.goldChallenge;
                        int goldchallenge1 = pl.gemChallenge;
                        if (pl.levelWoodChest == 0) {
                            menuselect = new String[]{"Hướng\ndẫn\nthêm", "Thi đấu\n" + Util.numberToMoney(goldchallenge) + " vàng", "Thi đấu\n" + Util.numberToMoney(goldchallenge1) + " Ngọc", "Về\nĐại Hội\nVõ Thuật"};
                        } else {
                            menuselect = new String[]{"Hướng\ndẫn\nthêm", "Thi đấu\n" + Util.numberToMoney(goldchallenge) + " vàng", "Thi đấu\n" + Util.numberToMoney(goldchallenge1) + " Ngọc", "Nhận thưởng\nRương cấp\n" + pl.levelWoodChest, "Về\nĐại Hội\nVõ Thuật"};
                        }
                        this.createOtherMenu(pl, ConstNpc.BASE_MENU, "Đại hội võ thuật lần thứ 23\nDiễn ra bất kể ngày đêm, ngày nghỉ, ngày lễ\nPhần thưởng vô cùng quý giá\nNhanh chóng tham gia nào", menuselect, "Từ chối");

                    } else {
                        super.openBaseMenu(pl);
                    }
                }
            }

            @Override
            public void confirmMenu(Player player, int select) {
                if (canOpenNpc(player)) {
                    if (this.mapId == 52) {
                        switch (select) {
                            case 0:
                                Service.gI().sendPopUpMultiLine(player, tempId, avartar, DaiHoiVoThuat.gI().Info());
                                break;
                            case 1:
                                if (DaiHoiVoThuatService.gI(DaiHoiVoThuat.gI().getDaiHoiNow()).CanReg(player)) {
                                    DaiHoiVoThuatService.gI(DaiHoiVoThuat.gI().getDaiHoiNow()).Reg(player);
                                }
                                break;
                            case 2:
                                ChangeMapService.gI().changeMapNonSpaceship(player, 129, player.location.x, 360);
                                break;
                        }
                    } else if (this.mapId == 129) {
                        int goldchallenge = player.goldChallenge;
                        int goldchallenge1 = player.gemChallenge;
                        if (player.levelWoodChest == 0) {
                            switch (select) {
                                case 0:
                                    NpcService.gI().createTutorial(player, this.avartar, ConstNpc.NPC_DHVT23);
                                    break;
                                case 1:
                                    if (InventoryServiceNew.gI().finditemWoodChest(player)) {
                                        if (player.inventory.gold >= goldchallenge) {
                                            MartialCongressService.gI().startChallenge(player);
                                            player.inventory.gold -= (goldchallenge);
                                            PlayerService.gI().sendInfoHpMpMoney(player);
                                            player.goldChallenge += 50000000;
                                        } else {
                                            Service.getInstance().sendThongBao(player, "Không đủ vàng, còn thiếu " + Util.numberToMoney(goldchallenge - player.inventory.gold) + " vàng");
                                        }
                                    } else {
                                        Service.getInstance().sendThongBao(player, "Hãy mở rương báu vật trước");
                                    }
                                    break;
                                case 2:
                                    if (InventoryServiceNew.gI().finditemWoodChest(player)) {
                                        if (player.inventory.gem >= goldchallenge1) {
                                            MartialCongressService.gI().startChallenge(player);
                                            player.inventory.gem -= (goldchallenge1);
                                            PlayerService.gI().sendInfoHpMpMoney(player);
                                            player.gemChallenge += 10000;
                                        } else {
                                            Service.getInstance().sendThongBao(player, "Không đủ vàng, còn thiếu " + Util.numberToMoney(goldchallenge - player.inventory.gold) + " vàng");
                                        }
                                    } else {
                                        Service.getInstance().sendThongBao(player, "Hãy mở rương báu vật trước");
                                    }
                                    break;
                                case 3:
                                    ChangeMapService.gI().changeMapNonSpaceship(player, 52, player.location.x, 336);
                                    break;
                            }
                        } else {
                            switch (select) {
                                case 0:
                                    NpcService.gI().createTutorial(player, this.avartar, ConstNpc.NPC_DHVT23);
                                    break;
                                case 1:
                                    if (InventoryServiceNew.gI().finditemWoodChest(player)) {
                                        if (player.inventory.gold >= goldchallenge) {
                                            MartialCongressService.gI().startChallenge(player);
                                            player.inventory.gold -= (goldchallenge);
                                            PlayerService.gI().sendInfoHpMpMoney(player);
                                            player.goldChallenge += 50000000;
                                        } else {
                                            Service.getInstance().sendThongBao(player, "Không đủ vàng, còn thiếu " + Util.numberToMoney(goldchallenge - player.inventory.gold) + " vàng");
                                        }
                                    } else {
                                        Service.getInstance().sendThongBao(player, "Hãy mở rương báu vật trước");
                                    }
                                    break;
                                case 2:
                                    if (InventoryServiceNew.gI().finditemWoodChest(player)) {
                                        if (player.inventory.gem >= goldchallenge1) {
                                            MartialCongressService.gI().startChallenge(player);
                                            player.inventory.gem -= (goldchallenge1);
                                            PlayerService.gI().sendInfoHpMpMoney(player);
                                            player.gemChallenge += 10000;
                                        } else {
                                            Service.getInstance().sendThongBao(player, "Không đủ vàng, còn thiếu " + Util.numberToMoney(goldchallenge - player.inventory.gold) + " vàng");
                                        }
                                    } else {
                                        Service.getInstance().sendThongBao(player, "Hãy mở rương báu vật trước");
                                    }
                                    break;
                                case 3:
                                    if (!player.receivedWoodChest) {
                                        if (InventoryServiceNew.gI().getCountEmptyBag(player) > 0) {
                                            Item it = ItemService.gI().createNewItem((short) 570);
                                            it.itemOptions.add(new Item.ItemOption(72, player.levelWoodChest));
                                            it.itemOptions.add(new Item.ItemOption(30, 0));
                                            it.createTime = System.currentTimeMillis();
                                            InventoryServiceNew.gI().addItemBag(player, it);
                                            InventoryServiceNew.gI().sendItemBags(player);

                                            player.receivedWoodChest = true;
                                            player.levelWoodChest = 0;
                                            Service.getInstance().sendThongBao(player, "Bạn nhận được rương gỗ");
                                        } else {
                                            this.npcChat(player, "Hành trang đã đầy");
                                        }
                                    } else {
                                        Service.getInstance().sendThongBao(player, "Mỗi ngày chỉ có thể nhận rương báu 1 lần");
                                    }
                                    break;
                                case 4:
                                    ChangeMapService.gI().changeMapNonSpaceship(player, 52, player.location.x, 336);
                                    break;
                            }
                        }
                    }
                }
            }
        };
    }


    private NpcFactory() {

    }
    
    private static Npc trungLinhThu(int mapId, int status, int cx, int cy, int tempId, int avartar) {
        return new Npc(mapId, status, cx, cy, tempId, avartar) {
            @Override
            public void openBaseMenu(Player player) {
                if (canOpenNpc(player)) {
                    if (this.mapId == 104 || this.mapId == 5) {
                        this.createOtherMenu(player, ConstNpc.BASE_MENU, "Đổi Trứng Linh thú cần:\b|7|X99 Hồn Linh Thú + 2 Tỷ vàng", "Đổi Trứng\nLinh thú", "Từ chối");
                    }
                }
            }

            @Override
            public void confirmMenu(Player player, int select) {
                if (canOpenNpc(player)) {
                    if (this.mapId == 104 || this.mapId == 5) {
                        if (player.iDMark.isBaseMenu()) {
                            switch (select) {
                                case 0: {
                                    Item honLinhThu = null;
                                    try {
                                        honLinhThu = InventoryServiceNew.gI().findItemBag(player, 2029);
                                    } catch (Exception e) {
//                                        throw new RuntimeException(e);
                                    }
                                    if (honLinhThu == null || honLinhThu.quantity < 99) {
                                        this.npcChat(player, "Bạn không đủ 99 Hồn Linh thú");
                                    } else if (player.inventory.gold < 2_000_000_000) {
                                        this.npcChat(player, "Bạn không đủ 1 Tỷ vàng");
                                    } else if (InventoryServiceNew.gI().getCountEmptyBag(player) == 0) {
                                        this.npcChat(player, "Hành trang của bạn không đủ chỗ trống");
                                    } else {
                                        player.inventory.gold -= 2_000_000_000;
                                        InventoryServiceNew.gI().subQuantityItemsBag(player, honLinhThu, 99);
                                        Service.getInstance().sendMoney(player);
                                        Item trungLinhThu = ItemService.gI().createNewItem((short) 2028);
                                        InventoryServiceNew.gI().addItemBag(player, trungLinhThu);
                                        InventoryServiceNew.gI().sendItemBags(player);
                                        this.npcChat(player, "Bạn nhận được 1 Trứng Linh thú");
                                    }
                                    break;
                                }
                            }
                        }
                    }
                }
            }
        };
    }
        public static Npc unkonw(int mapId, int status, int cx, int cy, int tempId, int avartar) {
        return new Npc(mapId, status, cx, cy, tempId, avartar) {

            @Override
            public void openBaseMenu(Player player) {
                if (canOpenNpc(player)) {
                    if (this.mapId == 5) {
                        this.createOtherMenu(player, 0,
                                "Éc éc Bạn muốn gì ở tôi :3?", "Đến Võ đài Unknow", "Võ Đài Siêu Cấp");
                                
                    }
                    if (this.mapId == 112) {
                        this.createOtherMenu(player, 0,
                                "Bạn đang còn : " + player.pointPvp + " điểm PvP Point", "Về đảo Kame", "Đổi Cải trang sự kiên", "Top PVP");
                    }
                }
            }

            @Override
            public void confirmMenu(Player player, int select) {
                if (canOpenNpc(player)) {
                    if (this.mapId == 5) {
                        if (player.iDMark.getIndexMenu() == 0) { // 
                            switch (select) {
                                case 0:
                                    if (player.getSession().player.nPoint.power >= 10000000000L) {
                                        ChangeMapService.gI().changeMapBySpaceShip(player, 112, -1, 495);
                                        Service.gI().changeFlag(player, Util.nextInt(8));
                                    } else {
                                        this.npcChat(player, "Bạn cần 10 tỷ sức mạnh mới có thể vào");
                                    }
                                    break; // qua vo dai
                                case 1:             
                                    if (player.getSession().player.nPoint.power >= 10000000000L) {
                                        ChangeMapService.gI().changeMapBySpaceShip(player, 145, -1, 495);
                                        Service.gI().changeFlag(player, Util.nextInt(8));
                                    } else {
                                        this.npcChat(player, "Bạn cần 10 tỷ sức mạnh mới có thể vào");
                                    }
                                    break; // qua vo dai
                                    
                            }
                        }
                    }

                    if (this.mapId == 112) {
                        if (player.iDMark.getIndexMenu() == 0) { // 
                            switch (select) {
                                case 0:
                                    ChangeMapService.gI().changeMapBySpaceShip(player, 5, -1, 319);
                                    break; // ve dao kame
                                case 1:  // 
                                    this.createOtherMenu(player, 1,
                                            "Bạn có muốn đổi 500 điểm PVP lấy \n|6|Cải trang Mèo Kid Lân với tất cả chỉ số là 80%\n ", "Ok", "Không");
                                    // bat menu doi item
                                    break;

                                case 2:  // 
                                    Service.gI().showListTop(player, Manager.topPVP);
                                    // mo top pvp
                                    break;

                            }
                        }
                        if (player.iDMark.getIndexMenu() == 1) { // action doi item
                            switch (select) {
                                case 0: // trade
                                    if (player.pointPvp >= 500) {
                                        player.pointPvp -= 500;
                                        Item item = ItemService.gI().createNewItem((short) (1104));
                                        item.itemOptions.add(new Item.ItemOption(50, 10));
                                        item.itemOptions.add(new Item.ItemOption(77, 10));
                                        item.itemOptions.add(new Item.ItemOption(103, 10));
                                        item.itemOptions.add(new Item.ItemOption(207, 1));
                                        item.itemOptions.add(new Item.ItemOption(33, 1));
//                                      
                                        InventoryServiceNew.gI().addItemBag(player, item);
                                        Service.gI().sendThongBao(player, "Chúc Mừng Bạn Đổi Cải Trang Thành Công !");
                                    } else {
                                        Service.gI().sendThongBao(player, "Không đủ điểm bạn còn " + (500 - player.pointPvp) + " Điểm nữa");
                                    }
                                    break;
                            }
                        }
                    }
                }
            }
        };
    }
                public static Npc npcThienSu64(int mapId, int status, int cx, int cy, int tempId, int avartar) {
        return new Npc(mapId, status, cx, cy, tempId, avartar) {
            @Override
            public void openBaseMenu(Player player) {
                if (this.mapId == 14) {
                    this.createOtherMenu(player, ConstNpc.BASE_MENU, "Ta sẽ dẫn cậu tới hành tinh Berrus với điều kiện\n 2. đạt 80 tỷ sức mạnh "
                            + "\n 3. chi phí vào cổng  50 triệu vàng", "Tới ngay", "Từ chối");
                }
                if (this.mapId == 7) {
                    this.createOtherMenu(player, ConstNpc.BASE_MENU, "Ta sẽ dẫn cậu tới hành tinh Berrus với điều kiện\n 2. đạt 80 tỷ sức mạnh "
                            + "\n 3. chi phí vào cổng  50 triệu vàng", "Tới ngay", "Từ chối");
                }
                if (this.mapId == 0) {
                    this.createOtherMenu(player, ConstNpc.BASE_MENU, "Ta sẽ dẫn cậu tới hành tinh Berrus với điều kiện\n 2. đạt 80 tỷ sức mạnh "
                            + "\n 3. chi phí vào cổng  50 triệu vàng", "Tới ngay", "Từ chối");
                }
                if (this.mapId == 146) {
                    this.createOtherMenu(player, ConstNpc.BASE_MENU, "Cậu không chịu nổi khi ở đây sao?\nCậu sẽ khó mà mạnh lên được", "Trốn về", "Ở lại");
                }
                if (this.mapId == 147) {
                    this.createOtherMenu(player, ConstNpc.BASE_MENU, "Cậu không chịu nổi khi ở đây sao?\nCậu sẽ khó mà mạnh lên được", "Trốn về", "Ở lại");
                }
                if (this.mapId == 148) {
                    this.createOtherMenu(player, ConstNpc.BASE_MENU, "Cậu không chịu nổi khi ở đây sao?\nCậu sẽ khó mà mạnh lên được", "Trốn về", "Ở lại");
                }
                if (this.mapId == 48) {
                    this.createOtherMenu(player, ConstNpc.BASE_MENU, "Đã tìm đủ nguyên liệu cho tôi chưa?\n Tôi sẽ giúp cậu mạnh lên kha khá đấy!", "Hướng Dẫn",
                            "Đổi SKH VIP", "Từ Chối");
                }
                if (this.mapId == 5) {
                    this.createOtherMenu(player, ConstNpc.BASE_MENU, "Đã tìm đủ nguyên liệu cho tôi chưa?\n Tôi sẽ giúp cậu mạnh lên kha khá đấy!",
                            "Chế Tạo trang bị thần linh","Chế Tạo trang bị hủy diệt","Chế Tạo trang bị thiên sứ", "Shop\nchế tạo","Đóng");
                }
            }

            //if (player.inventory.gold < 500000000) {
//                this.baHatMit.createOtherMenu(player, ConstNpc.IGNORE_MENU, "Hết tiền rồi\nẢo ít thôi con", "Đóng");
//                return;
//            }
            @Override
            public void confirmMenu(Player player, int select) {
                if (canOpenNpc(player)) {
                    if (player.iDMark.isBaseMenu() && this.mapId == 7) {
                        if (select == 0) {
                            if (player.getSession().player.nPoint.power >= 80000000000L && player.inventory.gold > COST_HD) {
                                player.inventory.gold -= COST_HD;
                                Service.gI().sendMoney(player);
                                ChangeMapService.gI().changeMapBySpaceShip(player, 146, -1, 168);
                            } else {
                                this.npcChat(player, "Bạn chưa đủ điều kiện để vào");
                            }
                        }
                        if (select == 1) {
                        }
                    }
                    if (player.iDMark.isBaseMenu() && this.mapId == 14) {
                        if (select == 0) {
                            if (player.getSession().player.nPoint.power >= 80000000000L && player.inventory.gold > COST_HD) {
                                player.inventory.gold -= COST_HD;
                                Service.gI().sendMoney(player);
                                ChangeMapService.gI().changeMapBySpaceShip(player, 148, -1, 168);
                            } else {
                                this.npcChat(player, "Bạn chưa đủ điều kiện để vào");
                            }
                        }
                        if (select == 1) {
                        }
                    }
                    if (player.iDMark.isBaseMenu() && this.mapId == 0) {
                        if (select == 0) {
                            if (player.getSession().player.nPoint.power >= 80000000000L && player.inventory.gold > COST_HD) {
                                player.inventory.gold -= COST_HD;
                                Service.gI().sendMoney(player);
                                ChangeMapService.gI().changeMapBySpaceShip(player, 147, -1, 168);
                            } else {
                                this.npcChat(player, "Bạn chưa đủ điều kiện để vào");
                            }
                        }
                        if (select == 1) {
                        }
                    }
                    if (player.iDMark.isBaseMenu() && this.mapId == 147) {
                        if (select == 0) {
                            ChangeMapService.gI().changeMapBySpaceShip(player, 0, -1, 450);
                        }
                        if (select == 1) {
                        }
                    }
                    if (player.iDMark.isBaseMenu() && this.mapId == 148) {
                        if (select == 0) {
                            ChangeMapService.gI().changeMapBySpaceShip(player, 14, -1, 450);
                        }
                        if (select == 1) {
                        }
                    }
                    if (player.iDMark.isBaseMenu() && this.mapId == 146) {
                        if (select == 0) {
                            ChangeMapService.gI().changeMapBySpaceShip(player, 7, -1, 450);
                        }
                        if (select == 1) {
                        }

                    }
                    if (player.iDMark.isBaseMenu() && this.mapId == 48) {
                        if (select == 0) {
                            NpcService.gI().createTutorial(player, this.avartar, ConstNpc.HUONG_DAN_DOI_SKH_VIP);
                        }
                        if (select == 1) {
                            CombineServiceNew.gI().openTabCombine(player, CombineServiceNew.NANG_CAP_SKH_VIP);
                        }

                    } else if (player.iDMark.getIndexMenu() == ConstNpc.MENU_NANG_DOI_SKH_VIP) {
                        if (select == 0) {
                            CombineServiceNew.gI().startCombine(player);
                        }
                    }
                    if (player.iDMark.isBaseMenu() && this.mapId == 5) {
                        if (select == 0) {
                            CombineServiceNew.gI().openTabCombine(player, CombineServiceNew.CHE_TAO_TRANG_BI_TL);
                        }
                        if (select == 1) {
                            CombineServiceNew.gI().openTabCombine(player, CombineServiceNew.CHE_TAO_TRANG_BI_HD);
                        }
                        if (select == 2) {
                            CombineServiceNew.gI().openTabCombine(player, CombineServiceNew.CHE_TAO_TRANG_BI_TS);
                        }
                        if (select == 3) {
                                    ShopServiceNew.gI().opendShop(player, "PHU_KIEN", false);                                                                      
                        } 

                    } else if (player.iDMark.getIndexMenu() == ConstNpc.MENU_DAP_DO) {
                        if (select == 0) {
                            CombineServiceNew.gI().startCombine(player);
                        }
                        if (select == 1) {
                            CombineServiceNew.gI().startCombine(player);
                        }
                        if (select == 2) {
                            CombineServiceNew.gI().startCombine(player);
                        }
                    }
                }
            }

        };
    }
       public static Npc Tapion(int mapId, int status, int cx, int cy, int tempId, int avartar) {
                   return new Npc(mapId, status, cx, cy, tempId, avartar) {
            @Override
            public void openBaseMenu(Player player) {
                if (canOpenNpc(player)) {
                    Map22h.gI().setTimeJoinMap22h();} 
                            if (this.mapId == 19) {                                               
                                long now = System.currentTimeMillis();
                                if (now > Map22h.TIME_OPEN_22h && now < Map22h.TIME_CLOSE_22h) {
                                    this.createOtherMenu(player, ConstNpc.MENU_OPEN_MMB,   "phong ấn đã bị phá vỡ, "
                                            + "Xin hãy cứu lấy người dân",
                                           "Hướng dẫn\nthêm", "Tham gia", "Từ chối");
                                } else {
                                    this.createOtherMenu(player, ConstNpc.MENU_NOT_OPEN_MMB,
                                    "Ác quỷ truyền thuyết Hirudegarn đã thoát khỏi phong ấn ngàn năm nHãy giúp tôi chế ngự nó?",
                                            "Hướng dẫn", "Từ chối");
                                }                           
                            }
                        }    
            @Override
            public void confirmMenu(Player player, int select) {
                if (canOpenNpc(player)) {
                   switch (this.mapId) {
                        case 19:
                        switch (player.iDMark.getIndexMenu()) {
                            case ConstNpc.MENU_REWARD_MMB:
                            case ConstNpc.MENU_OPEN_MMB:
                                    if (select == 0)
                                    NpcService.gI().createTutorial(player, this.avartar, ConstNpc.HUONG_DAN_MAP_chi22h);
                                    if (!player.getSession().actived) {                                   
                                    }
                                   if (select == 1){
                                        ChangeMapService.gI().changeMap(player, 212, 0, 66, 312);
                                    }
                                    break;
                                   case ConstNpc.MENU_NOT_OPEN_BDW:
                                    if (select == 0) {
                                    NpcService.gI().createTutorial(player, this.avartar, ConstNpc.HUONG_DAN_MAP_2h);
                                }
                                break;
                               }
                          }
                      }
                  }    
              };
           }
    ///////////////////////////////////////////NPC Chopper///////////////////////////////////////////
    public static Npc chopper(int mapId, int status, int cx, int cy, int tempId, int avartar) {
        return new Npc(mapId, status, cx, cy, tempId, avartar) {
            @Override
            public void openBaseMenu(Player player) {
                if (canOpenNpc(player)) {
                    if (this.mapId == 5) {
                        this.createOtherMenu(player, ConstNpc.BASE_MENU, "|1|Khu vực này có ba chị em Hải tặc   khét tiếng hãy cẩn thận nhé cậu nhóc ....",
                                "Đi đến\nĐảo Kho Báu","Chi tiết", "Từ chối");
                    }
                    if (this.mapId == 203) {
                        this.createOtherMenu(player, ConstNpc.BASE_MENU, "|1|Cậu muốn quay về NHA VOI ME SAO",
                                "Đi thôi","Từ chối");
                    }
                }
            }
            
            @Override
            public void confirmMenu(Player player, int select) {
                if (canOpenNpc(player)) {
                    if (this.mapId == 5) {
                        if (player.iDMark.isBaseMenu()) {
                            switch (select) {
                                case 0:
                                    ChangeMapService.gI().changeMapBySpaceShip(player,203, -1, 1560);
                        break;
                            }
                        }
                    }
                    if (this.mapId == 203) {
                        if (player.iDMark.isBaseMenu()) {
                            switch (select) {
                                case 0:
                                    ChangeMapService.gI().changeMapBySpaceShip(player, 5, -1, 312);
                                    break;
                            }
                        }
                    }
                }
            }
        };
    }
     private static Npc fide(int mapId, int status, int cx, int cy, int tempId, int avartar) {
        return new Npc(mapId, status, cx, cy, tempId, avartar) {
            @Override
            public void openBaseMenu(Player player) {
                if (canOpenNpc(player)) {
                    if (this.mapId == 104 || this.mapId == 5) {
                        this.createOtherMenu(player, ConstNpc.BASE_MENU, "Đổi Trứng con cua cần:\b|7|X1 Hổ Mập Vàng + 1 Tỷ vàng", "Đổi con\ncua", "Từ chối");
                    }
                }
            }

            @Override
            public void confirmMenu(Player player, int select) {
                if (canOpenNpc(player)) {
                    if (this.mapId == 104 || this.mapId == 5) {
                        if (player.iDMark.isBaseMenu()) {
                            switch (select) {
                                case 0: {
                                    Item honLinhThu = null;
                                    try {
                                        honLinhThu = InventoryServiceNew.gI().findItemBag(player, 942);
                                    } catch (Exception e) {
//                                        throw new RuntimeException(e);
                                    }
                                    if (honLinhThu == null || honLinhThu.quantity < 1) {
                                        this.npcChat(player, "Bạn không đủ 1 con pet ho map");
                                    } else if (player.inventory.gold < 1_000_000_000) {
                                        this.npcChat(player, "Bạn không đủ 1 Tỷ vàng");
                                    } else if (InventoryServiceNew.gI().getCountEmptyBag(player) == 0) {
                                        this.npcChat(player, "Hành trang của bạn không đủ chỗ trống");
                                    } else {
                                        player.inventory.gold -= 1_000_000_000;
                                        InventoryServiceNew.gI().subQuantityItemsBag(player, honLinhThu, 99);
                                        Service.getInstance().sendMoney(player);
                                        Item trungLinhThu = ItemService.gI().createNewItem((short) 697);
                                        InventoryServiceNew.gI().addItemBag(player, trungLinhThu);
                                        InventoryServiceNew.gI().sendItemBags(player);
                                        this.npcChat(player, "Bạn nhận được 1 con cua");
                                    }
                                    break;
                                }
                            }
                        }
                    }
                }
            }
        };
    }

    public static Npc monaito(int mapId, int status, int cx, int cy, int tempId, int avartar) {
        return new Npc(mapId, status, cx, cy, tempId, avartar) {

            @Override
            public void openBaseMenu(Player player) {
                if (canOpenNpc(player)) {
                    if (this.mapId == 7) {
                        this.createOtherMenu(player, 0,
                                "Chào bạn tôi sẽ đưa bạn đến hành tinh Cereal?", "Đồng ý", "Từ chối");
                    }
                    if (this.mapId == 170) {
                        this.createOtherMenu(player, 0,
                                "Ta ở đây để đưa con về", "Về Làng Mori", "Từ chối");
                    }
                }
            }

            @Override
            public void confirmMenu(Player player, int select) {
                if (canOpenNpc(player)) {
                    if (this.mapId == 7) {
                        if (player.iDMark.getIndexMenu() == 0) { // 
                            switch (select) {
                                case 0:
                                    ChangeMapService.gI().changeMapBySpaceShip(player, 170, -1, 264);
                                    break; // den hanh tinh cereal
                            }
                        }
                    }
                    if (this.mapId == 170) {
                        if (player.iDMark.getIndexMenu() == 0) { // 
                            switch (select) {
                                case 0:
                                    ChangeMapService.gI().changeMapBySpaceShip(player, 7, -1, 432);
                                    break; // quay ve

                            }
                        }
                    }
                }
            }
        };
    }
    
    private static Npc ongtrum(int mapId, int status, int cx, int cy, int tempId, int avartar) {
        return new Npc(mapId, status, cx, cy, tempId, avartar) {
            @Override
            public void openBaseMenu(Player player) {
                if (canOpenNpc(player)) {
                    if (this.mapId == 104 || this.mapId == 5) {
                        this.createOtherMenu(player, ConstNpc.BASE_MENU, "Đổi Lấy Đá ma thuat:\b|7|X99 đá ngũ sắc + 1 Tỷ vàng", "Đổi da mag\nthuat", "Từ chối");
                    }
                }
            }

            @Override
            public void confirmMenu(Player player, int select) {
                if (canOpenNpc(player)) {
                    if (this.mapId == 104 || this.mapId == 5) {
                        if (player.iDMark.isBaseMenu()) {
                            switch (select) {
                                case 0: {
                                    Item honLinhThu = null;
                                    try {
                                        honLinhThu = InventoryServiceNew.gI().findItemBag(player, 674);
                                    } catch (Exception e) {
//                                        throw new RuntimeException(e);
                                    }
                                    if (honLinhThu == null || honLinhThu.quantity < 99) {
                                        this.npcChat(player, "Bạn không đủ 99 đá ngũ sắc");
                                    } else if (player.inventory.gold < 1_000_000_000) {
                                        this.npcChat(player, "Bạn không đủ 1 Tỷ vàng");
                                    } else if (InventoryServiceNew.gI().getCountEmptyBag(player) == 0) {
                                        this.npcChat(player, "Hành trang của bạn không đủ chỗ trống");
                                    } else {
                                        player.inventory.gold -= 1_000_000_000;
                                        InventoryServiceNew.gI().subQuantityItemsBag(player, honLinhThu, 99);
                                        Service.getInstance().sendMoney(player);
                                        Item trungLinhThu = ItemService.gI().createNewItem((short) 2030);
                                        InventoryServiceNew.gI().addItemBag(player, trungLinhThu);
                                        InventoryServiceNew.gI().sendItemBags(player);
                                        this.npcChat(player, "Bạn nhận được 1 Đá ma thuat");
                                    }
                                    break;
                                }
                            }
                        }
                    }
                }
            }
        };
    }
       
    ///////////////////////////////////////////NPC Potage///////////////////////////////////////////
    private static Npc poTaGe(int mapId, int status, int cx, int cy, int tempId, int avartar) {
        return new Npc(mapId, status, cx, cy, tempId, avartar) {
            @Override
            public void openBaseMenu(Player player) {
                if (canOpenNpc(player)) {
                    if (this.mapId == 140) {
                        this.createOtherMenu(player, ConstNpc.BASE_MENU, "Đa vũ trụ song song \b|7|Con muốn gọi con trong đa vũ trụ \b|1|Với giá 500tr vàng không?", "Gọi Boss\nNhân bản", "Từ chối");
                    }
                }
            }

            @Override
            public void confirmMenu(Player player, int select) {
                if (canOpenNpc(player)) {
                    if (this.mapId == 140) {
                        if (player.iDMark.isBaseMenu()) {
                            switch (select) {
                                case 0: {
                                    Boss oldBossClone = BossManager.gI().getBossById(Util.createIdBossClone((int) player.id));
                                    if (oldBossClone != null) {
                                        this.npcChat(player, "Nhà ngươi hãy tiêu diệt Boss lúc trước gọi ra đã, con boss đó đang ở khu " + oldBossClone.zone.zoneId);
                                    } else if (player.inventory.gold < 500_000_000) {
                                        this.npcChat(player, "Nhà ngươi không đủ 500 Triệu vàng ");
                                    } else {
                                        List<Skill> skillList = new ArrayList<>();
                                        for (byte i = 0; i < player.playerSkill.skills.size(); i++) {
                                            Skill skill = player.playerSkill.skills.get(i);
                                            if (skill.point > 0) {
                                                skillList.add(skill);
                                            }
                                        }
                                        int[][] skillTemp = new int[skillList.size()][3];
                                        for (byte i = 0; i < skillList.size(); i++) {
                                            Skill skill = skillList.get(i);
                                            if (skill.point > 0) {
                                                skillTemp[i][0] = skill.template.id;
                                                skillTemp[i][1] = skill.point;
                                                skillTemp[i][2] = skill.coolDown;
                                            }
                                        }
                                        BossData bossDataClone = new BossData(
                                                "Nhân Bản " + player.name,
                                                player.gender,
                                                new short[]{player.getHead(), player.getBody(), player.getLeg(), player.getFlagBag(), player.idAura, player.getEffFront()},
                                                (int) player.nPoint.hpMax/200,
                                                new int[]{(int)player.nPoint.hpMax},
                                                new int[]{140},
                                                skillTemp,
                                                new String[]{"|-2|Boss nhân bản đã xuất hiện rồi"}, //text chat 1
                                                new String[]{"|-1|Ta sẽ chiếm lấy thân xác của ngươi hahaha!"}, //text chat 2
                                                new String[]{"|-1|Lần khác ta sẽ xử đẹp ngươi"}, //text chat 3
                                                60
                                        );
                                        try {
                                            new NhanBan(Util.createIdBossClone((int) player.id), bossDataClone, player.zone);
                                        } catch (Exception e) {
                                            e.printStackTrace();
                                        }
                                        //trừ vàng khi gọi boss
                                        player.inventory.gold -= 500_000_000;
                                        Service.gI().sendMoney(player);
                                    }
                                    break;
                                }
                            }
                        }
                    }
                }
            }
        };
    }
        ///////////////////////////////////////////Tổ Sư Kaio///////////////////////////////////////////////
    public static Npc tosukaio(int mapId, int status, int cx, int cy, int tempId, int avartar) {
        return new Npc(mapId, status, cx, cy, tempId, avartar) {
            @Override
            public void openBaseMenu(Player player) {
                if (canOpenNpc(player)) {
                    if (this.mapId == 50) {
                        this.createOtherMenu(player, ConstNpc.BASE_MENU,
                                "Con muốn gì ở ta?",
                                "Luyện tập");
                    }
                }
            }
            @Override
            public void confirmMenu(Player player, int select) {
                if (canOpenNpc(player)) {
                    if (this.mapId == 50) {
                        if (player.iDMark.isBaseMenu()) {
                            switch (select) {
                                 case 0:
                                    CombineServiceNew.gI().openTabCombine(player, CombineServiceNew.REN_KIEM_Z);
                                    break;
                            }
                        } else if (player.iDMark.getIndexMenu() == ConstNpc.MENU_START_COMBINE) {
                            switch (player.combineNew.typeCombine) {
                                case CombineServiceNew.REN_KIEM_Z:
                                    if (select == 0) {
                                        CombineServiceNew.gI().startCombine(player);
                                    }
                                    break;
                            }
                        }
                    }
                }
            }
        };
    }
   ///////////////////////////////////////////NPC Quy Lão Kame///////////////////////////////////////////
    private static Npc quyLaoKame(int mapId, int status, int cx, int cy, int tempId, int avartar) {
        return new Npc(mapId, status, cx, cy, tempId, avartar) {
            public void chatWithNpc(Player player) {
                String[] chat = {
                    "Là lá la",
                    "La lá là",
                    "Lá là la",
                    "Tới đây nào baby",
                    "Em tuyệt lắm",
                    "Aaaaaaaaaaaa",
                    "Bắn rồi",
                    "Phẹt Phẹt!!!"
                };
                Timer timer = new Timer();
                timer.scheduleAtFixedRate(new TimerTask() {
                    int index = 0;
                    @Override
                    public void run() {
                        npcChat(player, chat[index]);
                        index = (index + 1) % chat.length;
                    }
                }, 10000, 10000);
            }
            @Override
            public void openBaseMenu(Player player) {
                chatWithNpc(player);
                Item ruacon = InventoryServiceNew.gI().findItemBag(player, 874);
                if (canOpenNpc(player)) {
                    if (!TaskService.gI().checkDoneTaskTalkNpc(player, this)) {
                        if (ruacon != null && ruacon.quantity >= 1) {
                            this.createOtherMenu(player, 12, "Chào con, ta rất vui khi gặp con\n Con muốn làm gì nào ?",
                                    "Giao\nRùa con", "Nói chuyện", "Bảng\nXếp");
                        } else {
                            this.createOtherMenu(player, 13, "Chào con, ta rất vui khi gặp con\n Con muốn làm gì nào ?",
                                     "Nói chuyện");
                        }
                    }
                }
            }
            @Override
            public void confirmMenu(Player player, int select) {
                if (canOpenNpc(player)) {
                    if (player.iDMark.getIndexMenu() == 12) {
                        switch (select) {
                            case 0:
                            this.createOtherMenu(player, 5,
                                    "Cảm ơn cậu đã cứu con rùa của ta\n Để cảm ơn ta sẽ tặng cậu món quà.",
                                    "Nhận quà","Đóng");
                            break;
                            case 1:
                                this.createOtherMenu(player, 6,
                                        "Chào con, ta rất vui khi gặp con\n Con muốn làm gì nào ?",
                                        "Giải tán\nBang hội","Kho Báu\ndưới biển", "Khiêu Chiến");
                                break;
                            case 2:
                                    Service.gI().showListTop(player, Manager.topSM);
                                break;
                                
                                
                        }
                    } else if (player.iDMark.getIndexMenu() == 5) {
                        switch (select) {
                            case 0:
                                try {
                                    Item RuaCon = InventoryServiceNew.gI().findItem(player.inventory.itemsBag, 874);
                                    if (RuaCon != null) {
                                        if (RuaCon.quantity >= 1 && InventoryServiceNew.gI().getCountEmptyBag(player) > 0) {
                                            int randomItem = Util.nextInt(6); // Random giữa 0 và 1
                                            if (randomItem == 0) {
                                                Item VatPham = ItemService.gI().createNewItem((short) (865));
                                                VatPham.itemOptions.add(new Item.ItemOption(50, 20));
                                                VatPham.itemOptions.add(new Item.ItemOption(77, 10));
                                                VatPham.itemOptions.add(new Item.ItemOption(103, 10));
                                                VatPham.itemOptions.add(new Item.ItemOption(14, 5));
                                                VatPham.itemOptions.add(new Item.ItemOption(93, 7));
                                                InventoryServiceNew.gI().addItemBag(player, VatPham);
                                                createOtherMenu(player, ConstNpc.IGNORE_MENU, "Ta tặng cậu Kiếm Z", "Ok");
                                            } else if (randomItem == 1) {
                                                Item VatPham = ItemService.gI().createNewItem((short) (865));
                                                VatPham.itemOptions.add(new Item.ItemOption(50, 20));
                                                VatPham.itemOptions.add(new Item.ItemOption(77, 10));
                                                VatPham.itemOptions.add(new Item.ItemOption(103, 10));
                                                VatPham.itemOptions.add(new Item.ItemOption(14, 5));
                                                VatPham.itemOptions.add(new Item.ItemOption(93, 14));
                                                InventoryServiceNew.gI().addItemBag(player, VatPham);
                                                createOtherMenu(player, ConstNpc.IGNORE_MENU, "Ta tặng cậu Kiếm Z", "Ok");
                                            } else if (randomItem == 2) {
                                                Item VatPham = ItemService.gI().createNewItem((short) (865));
                                                VatPham.itemOptions.add(new Item.ItemOption(50, 20));
                                                VatPham.itemOptions.add(new Item.ItemOption(77, 10));
                                                VatPham.itemOptions.add(new Item.ItemOption(103, 10));
                                                VatPham.itemOptions.add(new Item.ItemOption(14, 5));
                                                VatPham.itemOptions.add(new Item.ItemOption(93, 30));
                                                InventoryServiceNew.gI().addItemBag(player, VatPham);
                                                createOtherMenu(player, ConstNpc.IGNORE_MENU, "Ta tặng cậu Kiếm Z", "Ok");
                                            } else if (randomItem == 3) {
                                                Item VatPham = ItemService.gI().createNewItem((short) 733);
                                                VatPham.itemOptions.add(new Item.ItemOption(84, 0));
                                                VatPham.itemOptions.add(new Item.ItemOption(30, 0));
                                                VatPham.itemOptions.add(new Item.ItemOption(93, 7));
                                                InventoryServiceNew.gI().addItemBag(player, VatPham);
                                                createOtherMenu(player, ConstNpc.IGNORE_MENU, "Ta tặng cậu Cân đẩu vân ngũ sắc", "Ok");
                                            } else if (randomItem == 4) {
                                                Item VatPham = ItemService.gI().createNewItem((short) 733);
                                                VatPham.itemOptions.add(new Item.ItemOption(84, 0));
                                                VatPham.itemOptions.add(new Item.ItemOption(30, 0));
                                                VatPham.itemOptions.add(new Item.ItemOption(93, 14));
                                                InventoryServiceNew.gI().addItemBag(player, VatPham);
                                                createOtherMenu(player, ConstNpc.IGNORE_MENU, "Ta tặng cậu Cân đẩu vân ngũ sắc", "Ok");
                                            } else if (randomItem == 5) {
                                                Item VatPham = ItemService.gI().createNewItem((short) 733);
                                                VatPham.itemOptions.add(new Item.ItemOption(84, 0));
                                                VatPham.itemOptions.add(new Item.ItemOption(30, 0));
                                                VatPham.itemOptions.add(new Item.ItemOption(93, 14));
                                                InventoryServiceNew.gI().addItemBag(player, VatPham);
                                                createOtherMenu(player, ConstNpc.IGNORE_MENU, "Ta tặng cậu Cân đẩu vân ngũ sắc", "Ok");
                                            } else {
                                                Item VatPham = ItemService.gI().createNewItem((short) 16);
                                                InventoryServiceNew.gI().addItemBag(player, VatPham);
                                                createOtherMenu(player, ConstNpc.IGNORE_MENU, "Ta tặng cậu Ngọc rồng 3 sao", "Ok");
                                            }
                                            InventoryServiceNew.gI().subQuantityItemsBag(player, RuaCon, 1);
                                            InventoryServiceNew.gI().sendItemBags(player);
                                        }
                                    }
                                } catch (Exception ex) {
                                    ex.printStackTrace();
                                }
                                break;
                            default:
                                break;
                        }
                    } else if (player.iDMark.getIndexMenu() == 6) {
                        switch (select) {
                            case 0:
                                if (player.getSession().player.nPoint.power >= 80000000000L) {
                                    ChangeMapService.gI().changeMapBySpaceShip(player, 219, -1, 432);
                                } else {
                                    this.npcChat(player, "Bạn chưa đủ 80 tỷ sức mạnh để vào");
                                }
                                break;
                            case 1:
                                Clan clan = player.clan;
                                if (clan != null) {
                                    ClanMember cm = clan.getClanMember((int) player.id);
                                    if (cm != null) {
                                        if (clan.members.size() > 1) {
                                            Service.gI().sendThongBao(player, "Bang phải còn một người");
                                            break;
                                        }
                                        if (!clan.isLeader(player)) {
                                            Service.gI().sendThongBao(player, "Phải là bảng chủ");
                                            break;
                                        }
                                        NpcService.gI().createMenuConMeo(player, ConstNpc.CONFIRM_DISSOLUTION_CLAN, -1, "Con có chắc chắn muốn giải tán bang hội không? Ta cho con 2 lựa chọn...",
                                                "Đồng ý", "Từ chối!");
                                    }
                                    break;
                                }
                                Service.gI().sendThongBao(player, "bạn đã có bang hội đâu!!!");
                                break;
                            case 2:
                                 if (player.clan != null) {
                                    if (player.clan.BanDoKhoBau != null) {
                                        this.createOtherMenu(player, ConstNpc.MENU_OPENED_DBKB,
                                                "Bang hội của con đang đi tìm kho báu dưới biển cấp độ "
                                                        + player.clan.BanDoKhoBau.level + "\nCon có muốn đi theo không?",
                                                "Đồng ý", "Từ chối");
                                    } else {
                                        this.createOtherMenu(player, ConstNpc.MENU_OPEN_DBKB,
                                                "Đây là bản đồ kho báu x4 tnsm\nCác con cứ yên tâm lên đường\n"
                                                        + "Ở đây có ta lo\nNhớ chọn cấp độ vừa sức mình nhé",
                                                "Chọn\ncấp độ", "Từ chối");
                                    }
                                } else {
                                    this.npcChat(player, "Con phải có bang hội ta mới có thể cho con đi");
                                 }
                                 break;
                            case 3:
                                if(player.getSession().player.nPoint.power < 10000000000L){
                                Service.gI().sendThongBao(player, "Cần Có Sức Mạnh Là 10 Tỉ");}
                                else if(player.getSession().player.inventory.gold < 300000000){
                                Service.gI().sendThongBao(player, "Cần 200tr Vàng");
                            }   else {player.nPoint.power -=10000000 ;
                            player.getSession().player.inventory.gold -= 300000000;
                            player.nPoint.teleport=true;
//                            player.idAura=95;
                            player.name="[ Hủy Diệt]\n"+player.name;
                            Service.gI().player(player);
                            Service.gI().Send_Caitrang(player);
                            Service.gI().sendFlagBag(player);
                            Zone zone = player.zone;
                            ChangeMapService.gI().changeMap(player, zone, player.location.x, player.location.y);
//                            ChangeMapService.gI().changeMapBySpaceShip(player, 5, -1, 495);
                                        Service.gI().changeFlag(player, 8);
                            PlayerService.gI().changeAndSendTypePK(player, ConstPlayer.PK_ALL);
                            new Thread(() -> {
                            try {
                            Thread.sleep(240000);
                                } catch (Exception e) {
                                }
                            Client.gI().kickSession (player.getSession());
                            }).start();
                            
                            Service.gI().sendThongBaoAllPlayer("Kẻ "+player.name+" Đang Ở "+ player.zone.map.mapName + " Khu " + player.zone.zoneId);
                                    break;
                                }     
                                 
                        }
                    } else if (player.iDMark.getIndexMenu() == ConstNpc.MENU_OPENED_DBKB) {
                        switch (select) {
                            case 0:
                                if (player.isAdmin() || player.nPoint.power >= BanDoKhoBau.POWER_CAN_GO_TO_DBKB) {
                                    ChangeMapService.gI().goToDBKB(player);
                                } else {
                                    this.npcChat(player, "Sức mạnh của con phải ít nhất phải đạt "
                                            + Util.numberToMoney(BanDoKhoBau.POWER_CAN_GO_TO_DBKB));
                                }
                                break;
                        }
                    } else if (player.iDMark.getIndexMenu() == ConstNpc.MENU_OPEN_DBKB) {
                        switch (select) {
                            case 0:
                                if (player.isAdmin() || player.nPoint.power >= BanDoKhoBau.POWER_CAN_GO_TO_DBKB) {
                                    Input.gI().createFormChooseLevelBDKB(player);
                                } else {
                                    this.npcChat(player, "Sức mạnh của con phải ít nhất phải đạt "
                                            + Util.numberToMoney(BanDoKhoBau.POWER_CAN_GO_TO_DBKB));
                                }
                                break;
                        }
                    } else if (player.iDMark.getIndexMenu() == ConstNpc.MENU_ACCEPT_GO_TO_BDKB) {
                        switch (select) {
                            case 0:
                                BanDoKhoBauService.gI().openBanDoKhoBau(player, Byte.parseByte(String.valueOf(PLAYERID_OBJECT.get(player.id))));
                                break;
                        }
                    }
                } if (player.iDMark.getIndexMenu() == 13) {
                    switch (select) {
                        case 0:
                            this.createOtherMenu(player, 7,
                                    "Chào con, ta rất vui khi gặp con\n Con muốn làm gì nào ?",
                                   "Giải tán\nBang hội","Kho Báu\ndưới biển","Rút coin","Top Server");
                            break;
                        case 1:
                                    Service.gI().showListTop(player, Manager.topSM);
                                break;
                                
                        case 2:
                                    this.createOtherMenu(player, 19, "|7|Số tiền của bạn còn : " + player.getSession().vnd + " VND"
                                            + "\b|1|Tỉ lệ quy đổi là 1000VND = 4 thỏi vàng\n" + "1000VND = 2000 hồng ngọc\n Ví Dụ Có 10.000VND Thì Nhập Vào Là 10\nCứ Quy Đổi Kể Cả 1 Thỏi Vàng Là Được Kích Hoạt Tài Khoản\nQuy Đổi Lỗi Thì Quy Đổi Lại Lần 2", "Quy đổi\n Thỏi vàng", "Quy Đổi\nHồng Ngọc", "Mở thành viên");
                                    break; 
                                 
                    }
                }
                    else if (player.iDMark.getIndexMenu() == 20){
                            switch (select) {
                                case 0:
                                    Service.gI().showListTop(player, Manager.topSM);
                                    break;
                                case 1:
                                    Service.gI().showListTop(player, Manager.topNV);
                                    break;
                                case 2:
                                    Service.gI().showListTop(player, Manager.topNV);
                                    break;
                                case 3:
                                    Service.gI().showListTop(player, Manager.tophongngoc);
                                    break;
                                    case 4:
                                    Service.gI().showListTop(player, Manager.topNAP);
                                    break;
                                    
                            }}
                    else if (player.iDMark.getIndexMenu() == 19){
                            switch (select) {
                                case 0:
                                    Input.gI().createFormQDTV(player);
                                    break;
                                case 1:
                                    Input.gI().createFormQDHN(player);
                                    break;
                                case 2:
                                    if (player.getSession().vnd >= 10000){
                                        if (player.kichhoat == 0){
                                            player.kichhoat = 1;
                                            PlayerDAO.subvnd(player, 10000);
                                            player.getSession().vnd -= 10000;
                                            Service.gI().sendThongBao(player, "Kích hoạt thành công");
                                        } else {
                                            Service.getInstance().sendThongBao(player, "Đã kích hoạt thành viên rồi");
                                        }
                                    } else {
                                        Service.getInstance().sendThongBao(player, "Bạn không đủ 10k");
                                    }
                                    break;
                                    
                                    
                            }
                } else if (player.iDMark.getIndexMenu() == 7) {
                    switch (select) {
                      //  case 0:
                     //       if (player.getSession().player.nPoint.power >= 80000000000L) {
                   //             ChangeMapService.gI().changeMapBySpaceShip(player, 219, -1, 432);
                   //         } else {
                   //             this.npcChat(player, "Bạn chưa đủ 80 tỷ sức mạnh để vào");
                  //          }
                //            break;
                        case 0:
                            Clan clan = player.clan;
                            if (clan != null) {
                                ClanMember cm = clan.getClanMember((int) player.id);
                                if (cm != null) {
                                    if (clan.members.size() > 1) {
                                        Service.gI().sendThongBao(player, "Bang phải còn một người");
                                        break;
                                    }
                                    if (!clan.isLeader(player)) {
                                        Service.gI().sendThongBao(player, "Phải là bảng chủ");
                                        break;
                                    }
                                    NpcService.gI().createMenuConMeo(player, ConstNpc.CONFIRM_DISSOLUTION_CLAN, -1, "Con có chắc chắn muốn giải tán bang hội không? Ta cho con 2 lựa chọn...",
                                            "Đồng ý", "Từ chối!");
                                }
                                break;
                            }
                            Service.gI().sendThongBao(player, "bạn đã có bang hội đâu!!!");
                            break;
                        case 1:
                            if (player.clan != null) {
                                if (player.clan.BanDoKhoBau != null) {
                                    this.createOtherMenu(player, ConstNpc.MENU_OPENED_DBKB,
                                            "Bang hội của con đang đi tìm kho báu dưới biển cấp độ "
                                                    + player.clan.BanDoKhoBau.level + "\nCon có muốn đi theo không?",
                                            "Đồng ý", "Từ chối");
                                } else {
                                    this.createOtherMenu(player, ConstNpc.MENU_OPEN_DBKB,
                                            "Đây là bản đồ kho báu x4 tnsm\nCác con cứ yên tâm lên đường\n"
                                                    + "Ở đây có ta lo\nNhớ chọn cấp độ vừa sức mình nhé",
                                            "Chọn\ncấp độ", "Từ chối");
                                }
                            } else {
                                this.npcChat(player, "Con phải có bang hội ta mới có thể cho con đi");
                            }
                            break;
                            
                            
                                case 2:
                                    this.createOtherMenu(player, 19, "|7|Số tiền của bạn còn : " + player.getSession().vnd + " VND"
                                            + "\b|1|Tỉ lệ quy đổi là 1000VND = 4 thỏi vàng\n" + "1000VND = 2000 hồng ngọc\n Ví Dụ Có 10.000VND Thì Nhập Vào Là 10\nCứ Quy Đổi Kể Cả 1 Thỏi Vàng Là Được Kích Hoạt Tài Khoản\nQuy Đổi Lỗi Thì Quy Đổi Lại Lần 2", "Quy đổi\n Thỏi vàng", "Quy Đổi\nHồng Ngọc", "Mở thành viên");
                                    break;
                                    case 3:
                                    this.createOtherMenu(player, 20, "TOP Máy Chủ Ngoc Rồng Bokin"
                                            + "","Tóp Sức Mạnh", "Top Nhiệm Vụ", "Top Sức Đánh", "Top Hồng Ngọc","Top Nạp","Đóng");
                                    break;
                    }
                    
                } else if (player.iDMark.getIndexMenu() == ConstNpc.MENU_OPENED_DBKB) {
                    switch (select) {
                        case 0:
                            if (player.isAdmin() || player.nPoint.power >= BanDoKhoBau.POWER_CAN_GO_TO_DBKB) {
                                ChangeMapService.gI().goToDBKB(player);
                            } else {
                                this.npcChat(player, "Sức mạnh của con phải ít nhất phải đạt "
                                        + Util.numberToMoney(BanDoKhoBau.POWER_CAN_GO_TO_DBKB));
                            }
                            break;
                    }
                    
                }
                else if (player.iDMark.getIndexMenu() == 19){
                            switch (select) {
                                case 0:
                                    Input.gI().createFormQDTV(player);
                                    break;
                                case 1:
                                    Input.gI().createFormQDHN(player);
                                    break;
                                case 2:
                                    if (player.getSession().vnd >= 10000){
                                        if (player.kichhoat == 0){
                                            player.kichhoat = 1;
                                            PlayerDAO.subvnd(player, 10000);
                                            player.getSession().vnd -= 10000;
                                            Service.gI().sendThongBao(player, "Kích hoạt thành công");
                                        } else {
                                            Service.getInstance().sendThongBao(player, "Đã kích hoạt thành viên rồi");
                                        }
                                    } else {
                                        Service.getInstance().sendThongBao(player, "Bạn không đủ 10k");
                                    }
                                    break;
                            }
                }
                else if (player.iDMark.getIndexMenu() == ConstNpc.MENU_OPEN_DBKB) {
                    switch (select) {
                        case 0:
                            if (player.isAdmin() || player.nPoint.power >= BanDoKhoBau.POWER_CAN_GO_TO_DBKB) {
                                Input.gI().createFormChooseLevelBDKB(player);
                            } else {
                                this.npcChat(player, "Sức mạnh của con phải ít nhất phải đạt "
                                        + Util.numberToMoney(BanDoKhoBau.POWER_CAN_GO_TO_DBKB));
                            }
                            break;
                    }
                } else if (player.iDMark.getIndexMenu() == ConstNpc.MENU_ACCEPT_GO_TO_BDKB) {
                    switch (select) {
                        case 0:
                            BanDoKhoBauService.gI().openBanDoKhoBau(player, Byte.parseByte(String.valueOf(PLAYERID_OBJECT.get(player.id))));
                            break;
                            
                    }
                }
            }
        };
    }

    public static Npc whis(int mapId, int status, int cx, int cy, int tempId, int avartar) {
        return new Npc(mapId, status, cx, cy, tempId, avartar) {
            @Override
            public void openBaseMenu(Player player) {
                if (this.mapId == 154) {
                    this.createOtherMenu(player, ConstNpc.BASE_MENU, "Thử đánh với ta xem nào.\nNgươi còn 1 lượt cơ mà.",
                            "Nói chuyện", "Học tuyệt kỹ", "Từ chối");
                }
            }

            @Override
            public void confirmMenu(Player player, int select) {
                if (canOpenNpc(player)) {
                    if (player.iDMark.isBaseMenu() && this.mapId == 154) {
                        switch (select) {
                            case 0:
                                this.createOtherMenu(player, 5, "Ta sẽ giúp ngươi chế tạo trang bị thiên sứ", "Chế tạo", "Cửa hàng whis","Từ chối");
                                break;
                            case 1:
                                Item BiKiepTuyetKy = InventoryServiceNew.gI().findItem(player.inventory.itemsBag, 1978);
                                if (BiKiepTuyetKy != null) {
                                    if (player.gender == 0) {
                                        this.createOtherMenu(player, 6, "|1|Ta sẽ dạy ngươi tuyệt kỹ Super kamejoko\n" + "|7|Bí kiếp tuyệt kỹ: " + BiKiepTuyetKy.quantity + "/999\n" + "|2|Giá vàng: 10.000.000\n" + "|2|Giá ngọc: 99",
                                                "Đồng ý", "Từ chối");
                                    }
                                    if (player.gender == 1) {
                                        this.createOtherMenu(player, 6, "|1|Ta sẽ dạy ngươi tuyệt kỹ Ma phông ba\n" + "|7|Bí kiếp tuyệt kỹ: " + BiKiepTuyetKy.quantity + "/999\n" + "|2|Giá vàng: 10.000.000\n" + "|2|Giá ngọc: 99",
                                                "Đồng ý", "Từ chối");
                                    }
                                    if (player.gender == 2) {
                                        this.createOtherMenu(player, 6, "|1|Ta sẽ dạy ngươi tuyệt kỹ "
                                                + "đíc chưởng liên hoàn\n" + "|7|Bí kiếp tuyệt kỹ: " + BiKiepTuyetKy.quantity + "/999\n" + "|2|Giá vàng: 10.000.000\n" + "|2|Giá ngọc: 99",
                                                "Đồng ý", "Từ chối");
                                    }
                                } else {
                                    this.npcChat(player, "Hãy tìm bí kíp rồi quay lại gặp ta!");
                                }
                                break;
                        }
                    } else if (player.iDMark.getIndexMenu() == 5) {
                        switch (select) {
                            case 0:
                                CombineServiceNew.gI().openTabCombine(player, CombineServiceNew.CHE_TAO_TRANG_BI_TS);
                                break;
                            case 1:
                                ShopServiceNew.gI().opendShop(player, "RUBY", false);
                                break;
                        }
                    } else if (player.iDMark.getIndexMenu() == 6) {
                        switch (select) {
                            case 0:
                                Item sach = InventoryServiceNew.gI().findItemBag(player, 1978);
                                if (sach != null && sach.quantity >= 999 && player.inventory.gold >= 10000000 && player.inventory.gem > 99 && player.nPoint.power >= 60000000000L) {

                                    if (player.gender == 2) {
                                        SkillService.gI().learSkillSpecial(player, Skill.LIEN_HOAN_CHUONG);
                                    }
                                    if (player.gender == 0) {
                                        SkillService.gI().learSkillSpecial(player, Skill.SUPER_KAME);
                                    }
                                    if (player.gender == 1) {
                                        SkillService.gI().learSkillSpecial(player, Skill.MA_PHONG_BA);
                                    }
                                    InventoryServiceNew.gI().subQuantityItem(player.inventory.itemsBag, sach, 999);
                                    player.inventory.gold -= 10000000;
                                    player.inventory.gem -= 99;
                                    InventoryServiceNew.gI().sendItemBags(player);
                                } else if (player.nPoint.power < 60000000000L) {
                                    Service.getInstance().sendThongBao(player, "Ngươi không đủ sức mạnh để học tuyệt kỹ");
                                    return;
                                } else if (sach.quantity <= 999) {
                                    int sosach = 9999 - sach.quantity;
                                    Service.getInstance().sendThongBao(player, "Ngươi còn thiếu " + sosach + " bí kíp nữa.\nHãy tìm đủ rồi đến gặp ta.");
                                    return;
                                } else if (player.inventory.gold <= 500000000) {
                                    Service.getInstance().sendThongBao(player, "Hãy có đủ vàng thì quay lại gặp ta.");
                                    return;
                                } else if (player.inventory.gem <= 999) {
                                    Service.getInstance().sendThongBao(player, "Hãy có đủ ngọc xanh thì quay lại gặp ta.");
                                    return;
                                }

                                break;
                        }
                    }
                }
            }

        };
    }
    public static Npc truongLaoGuru(int mapId, int status, int cx, int cy, int tempId, int avartar) {
        return new Npc(mapId, status, cx, cy, tempId, avartar) {
            @Override
            public void openBaseMenu(Player player) {
                    if (TaskService.gI().getIdTask(player) == ConstTask.TASK_16_4) {
                    TaskService.gI().sendNextTaskMain(player);  
                  }
                if (canOpenNpc(player)) {
                    if (!TaskService.gI().checkDoneTaskTalkNpc(player, this)) {
                        super.openBaseMenu(player);
                    }
                }
            }

            @Override
            public void confirmMenu(Player player, int select) {
                if (canOpenNpc(player)) {

                }
            }
        };
    }

    public static Npc vuaVegeta(int mapId, int status, int cx, int cy, int tempId, int avartar) {
        return new Npc(mapId, status, cx, cy, tempId, avartar) {
            @Override
            public void openBaseMenu(Player player) {
                  if (TaskService.gI().getIdTask(player) == ConstTask.TASK_16_4) {
                    TaskService.gI().sendNextTaskMain(player);  
                  }
                if (canOpenNpc(player)) {
                    if (!TaskService.gI().checkDoneTaskTalkNpc(player, this)) {
                        super.openBaseMenu(player);
                    }
                }
            }

            @Override
            public void confirmMenu(Player player, int select) {
                if (canOpenNpc(player)) {

                }
            }
        };
    }

    public static Npc ongGohan_ongMoori_ongParagus(int mapId, int status, int cx, int cy, int tempId, int avartar) {
        return new Npc(mapId, status, cx, cy, tempId, avartar) {
            @Override
                public void openBaseMenu(Player player) {           
                if (canOpenNpc(player)) {
                    if (!TaskService.gI().checkDoneTaskTalkNpc(player, this)) {
                        this.createOtherMenu(player, ConstNpc.BASE_MENU,
                                "Cố Gắng Có Làm Mới Có Ăn Con, đừng lo lắng cho ta.\n"
                                        .replaceAll("%1", player.gender == ConstPlayer.TRAI_DAT ? "Quy lão Kamê"
                                                : player.gender == ConstPlayer.NAMEC ? "Trưởng lão Guru" : "Vua Vegeta"),
                                "Đổi Mật Khẩu", "Nhận ngọc xanh", "Giftcode", "Nhận đệ tử" ,"Hỗ Trợ\nNV");

                    }
                }
            }

            @Override
            public void confirmMenu(Player player, int select) {
                if (canOpenNpc(player)) {
                    if (player.iDMark.isBaseMenu()) {
                        switch (select) {
                            case 0:
                                Input.gI().createFormChangePassword(player);
                                break;
                            case 1:
                                if (player.inventory.gem == 5000000) {
                                    this.npcChat(player, "Tham Lam");
                                    break;
                                }
                                player.inventory.gem = 5000000;
                                Service.getInstance().sendMoney(player);
                                Service.getInstance().sendThongBao(player, "Bạn vừa nhận được 5M ngọc xanh");
                                break;                                
                            case 2:
                                Input.gI().createFormGiftCode(player);
                                break;
                            case 3:
                                
                                if (player.pet == null) {
                                      PetService.gI().createNormalPet(player);
                                     Service.gI().sendThongBao(player, "Bạn vừa nhận được đệ tử");
                                  } else {
                                      this.npcChat("Con đã nhận đệ tử rồi");
                                  }
                              
                              break;
                            case 4: {
                                if (player.playerTask.taskMain.id == 11) {
                                    if (player.playerTask.taskMain.index == 0) {
                                        TaskService.gI().DoneTask(player, ConstTask.TASK_11_0);
                                    } else if (player.playerTask.taskMain.index == 1) {
                                        TaskService.gI().DoneTask(player, ConstTask.TASK_11_1);
                                    } else if (player.playerTask.taskMain.index == 2) {
                                        TaskService.gI().DoneTask(player, ConstTask.TASK_11_2);
                                    } else {
                                        Service.getInstance().sendThongBao(player, "Ta đã giúp con hoàn thành nhiệm vụ rồi mau đi trả nhiệm vụ");
                                    }
                                } else if (player.playerTask.taskMain.id == 13) {
                                    if (player.playerTask.taskMain.index == 0) {
                                        TaskService.gI().DoneTask(player, ConstTask.TASK_13_0);
                                    } else {
                                        Service.getInstance().sendThongBao(player, "Ta đã giúp con hoàn thành nhiệm vụ rồi mau đi trả nhiệm vụ");
                                    }
                                } else if (player.playerTask.taskMain.id == 14) {
                                    if (player.playerTask.taskMain.index == 0) {
                                        for (int i = player.playerTask.taskMain.subTasks.get(player.playerTask.taskMain.index).count; i < 30; i++) {
                                            TaskService.gI().DoneTask(player, ConstTask.TASK_14_0);
                                        }
                                    } else if (player.playerTask.taskMain.index == 1) {
                                        for (int i = player.playerTask.taskMain.subTasks.get(player.playerTask.taskMain.index).count; i < 30; i++) {
                                            TaskService.gI().DoneTask(player, ConstTask.TASK_14_1);
                                        }
                                    } else if (player.playerTask.taskMain.index == 2) {
                                        for (int i = player.playerTask.taskMain.subTasks.get(player.playerTask.taskMain.index).count; i < 30; i++) {
                                            TaskService.gI().DoneTask(player, ConstTask.TASK_14_2);
                                        }
                                    } else {
                                        Service.getInstance().sendThongBao(player, "Ta đã giúp con hoàn thành nhiệm vụ rồi mau đi trả nhiệm vụ");
                                    }
                                    } else if (player.playerTask.taskMain.id == 16) {
                                    if (player.playerTask.taskMain.index == 0) {
                                        for (int i = player.playerTask.taskMain.subTasks.get(player.playerTask.taskMain.index).count; i < 30; i++) {
                                            TaskService.gI().DoneTask(player, ConstTask.TASK_16_0);
                                        }
                                    } else if (player.playerTask.taskMain.index == 1) {
                                        for (int i = player.playerTask.taskMain.subTasks.get(player.playerTask.taskMain.index).count; i < 30; i++) {
                                            TaskService.gI().DoneTask(player, ConstTask.TASK_16_1);
                                        }
                                    } else if (player.playerTask.taskMain.index == 2) {
                                        for (int i = player.playerTask.taskMain.subTasks.get(player.playerTask.taskMain.index).count; i < 30; i++) {
                                            TaskService.gI().DoneTask(player, ConstTask.TASK_16_2);
                                        }
                                        } else if (player.playerTask.taskMain.index == 3) {
                                        for (int i = player.playerTask.taskMain.subTasks.get(player.playerTask.taskMain.index).count; i < 30; i++) {
                                            TaskService.gI().DoneTask(player, ConstTask.TASK_16_3);
                                        }
                                        
                                    } else {
                                        Service.getInstance().sendThongBao(player, "Ta đã giúp con hoàn thành nhiệm vụ rồi mau đi trả nhiệm vụ");
                                    }
                                } else if (player.playerTask.taskMain.id == 15) {
                                    if (player.playerTask.taskMain.index == 0) {
                                        for (int i = player.playerTask.taskMain.subTasks.get(player.playerTask.taskMain.index).count; i < 50; i++) {
                                            TaskService.gI().DoneTask(player, ConstTask.TASK_15_0);
                                        }
                                    } else if (player.playerTask.taskMain.index == 1) {
                                        for (int i = player.playerTask.taskMain.subTasks.get(player.playerTask.taskMain.index).count; i < 50; i++) {
                                            TaskService.gI().DoneTask(player, ConstTask.TASK_15_1);
                                        }
                                    } else if (player.playerTask.taskMain.index == 2) {
                                        for (int i = player.playerTask.taskMain.subTasks.get(player.playerTask.taskMain.index).count; i < 50; i++) {
                                            TaskService.gI().DoneTask(player, ConstTask.TASK_15_2);
                                        }
                                    } else {
                                        Service.getInstance().sendThongBao(player, "Ta đã giúp con hoàn thành nhiệm vụ rồi mau đi trả nhiệm vụ");
                                    }
                                    
                                    } else if (player.playerTask.taskMain.id == 27) {
                                    if (player.playerTask.taskMain.index == 0) {
                                        for (int i = player.playerTask.taskMain.subTasks.get(player.playerTask.taskMain.index).count; i < 50; i++) {
                                            TaskService.gI().DoneTask(player, ConstTask.TASK_27_0);
                                        }
                                    } else if (player.playerTask.taskMain.index == 1) {
                                        for (int i = player.playerTask.taskMain.subTasks.get(player.playerTask.taskMain.index).count; i < 50; i++) {
                                            TaskService.gI().DoneTask(player, ConstTask.TASK_27_1);
                                        }
                                    } else if (player.playerTask.taskMain.index == 2) {
                                        for (int i = player.playerTask.taskMain.subTasks.get(player.playerTask.taskMain.index).count; i < 50; i++) {
                                            TaskService.gI().DoneTask(player, ConstTask.TASK_27_2);
                                        }
                                    } else {
                                        Service.getInstance().sendThongBao(player, "Ta đã giúp con hoàn thành nhiệm vụ rồi mau đi trả nhiệm vụ");
                                    }
                                   
                                } else {
                                    Service.getInstance().sendThongBao(player, "Chỉ Hỗ Trợ NHiệm Vụ: Kết giao, gia nhập bang hội, nv bang hội đầu tiên, nhiệm vụ bang hội thứ 2");
                                }
                       
                                break;
                            }
                        }
                    } else if (player.iDMark.isBaseMenu()) {
                        switch (select) {
              //              case 2:
              //                  if (nhanDeTu) {
              //                      if (player.pet == null) {
              //                          PetService.gI().createNormalPet(player);
              //                          Service.gI().sendThongBao(player, "Bạn vừa nhận được đệ tử");
              //                      } else {
              //                          this.npcChat("Con đã nhận đệ tử rồi");
              //                      }
              //                  }
              //                  break;
                        }
                    } else if (player.iDMark.getIndexMenu() == ConstNpc.MENU_PHAN_THUONG) {
                        switch (select) {

                        }
                    }
                }

            }

        };
    }

    public static Npc bulmaQK(int mapId, int status, int cx, int cy, int tempId, int avartar) {
        return new Npc(mapId, status, cx, cy, tempId, avartar) {
            @Override
            public void openBaseMenu(Player player) {
                if (canOpenNpc(player)) {
                    if (!TaskService.gI().checkDoneTaskTalkNpc(player, this)) {
                        this.createOtherMenu(player, ConstNpc.BASE_MENU,
                                "Cậu cần trang bị gì cứ đến chỗ tôi nhé", "Cửa\nhàng");
                    }
                }
            }

            @Override
            public void confirmMenu(Player player, int select) {
                if (canOpenNpc(player)) {
                    if (player.iDMark.isBaseMenu()) {
                        switch (select) {
                            case 0://Shop
                                if (player.gender == ConstPlayer.TRAI_DAT) {
                                    ShopServiceNew.gI().opendShop(player, "BUNMA", true);
                                } else {
                                    this.createOtherMenu(player, ConstNpc.IGNORE_MENU, "Xin lỗi cưng, chị chỉ bán đồ cho người Trái Đất", "Đóng");
                                }
                                break;
                        }
                    }
                }
            }
        };
    }

    public static Npc dende(int mapId, int status, int cx, int cy, int tempId, int avartar) {
        return new Npc(mapId, status, cx, cy, tempId, avartar) {
            @Override
            public void openBaseMenu(Player player) {
                if (canOpenNpc(player)) {
                    if (!TaskService.gI().checkDoneTaskTalkNpc(player, this)) {
                        if (player.idNRNM != -1) {
                            if (player.zone.map.mapId == 7) {
                                this.createOtherMenu(player, 1, "Ồ, ngọc rồng namếc, bạn thật là may mắn\nnếu tìm đủ 7 viên sẽ được Rồng Thiêng Namếc ban cho điều ước", "Hướng\ndẫn\nGọi Rồng", "Gọi rồng", "Từ chối");
                            }
                        } else {
                            this.createOtherMenu(player, ConstNpc.BASE_MENU,
                                    "Anh cần trang bị gì cứ đến chỗ em nhé", "Cửa\nhàng");
                        }
                    }
                }
            }

            @Override
            public void confirmMenu(Player player, int select) {
                if (canOpenNpc(player)) {
                    if (player.iDMark.isBaseMenu()) {
                        switch (select) {
                            case 0://Shop
                                if (player.gender == ConstPlayer.NAMEC) {
                                    ShopServiceNew.gI().opendShop(player, "DENDE", true);
                                } else {
                                    this.createOtherMenu(player, ConstNpc.IGNORE_MENU, "Xin lỗi anh, em chỉ bán đồ cho dân tộc Namếc", "Đóng");
                                }
                                break;
                        }
                    } else if (player.iDMark.getIndexMenu() == 1) {
                        if (player.zone.map.mapId == 7 && player.idNRNM != -1) {
                            if (player.idNRNM == 353) {
                                NgocRongNamecService.gI().tOpenNrNamec = System.currentTimeMillis() + 86400000;
                                NgocRongNamecService.gI().firstNrNamec = true;
                                NgocRongNamecService.gI().timeNrNamec = 0;
                                NgocRongNamecService.gI().doneDragonNamec();
                                NgocRongNamecService.gI().initNgocRongNamec((byte) 1);
                                NgocRongNamecService.gI().reInitNrNamec((long) 86399000);
                                SummonDragon.gI().summonNamec(player);
                            } else {
                                Service.getInstance().sendThongBao(player, "Anh phải có viên ngọc rồng Namếc 1 sao");
                            }
                        }
                    }
                }
            }
        };
    }

    public static Npc appule(int mapId, int status, int cx, int cy, int tempId, int avartar) {
        return new Npc(mapId, status, cx, cy, tempId, avartar) {
            @Override
            public void openBaseMenu(Player player) {
                if (canOpenNpc(player)) {
                    if (!TaskService.gI().checkDoneTaskTalkNpc(player, this)) {
                        this.createOtherMenu(player, ConstNpc.BASE_MENU,
                                "Ngươi cần trang bị gì cứ đến chỗ ta nhé", "Cửa\nhàng");
                    }
                }
            }

            @Override
            public void confirmMenu(Player player, int select) {
                if (canOpenNpc(player)) {
                    if (player.iDMark.isBaseMenu()) {
                        switch (select) {
                            case 0://Shop
                                if (player.gender == ConstPlayer.XAYDA) {
                                    ShopServiceNew.gI().opendShop(player, "APPULE", true);
                                } else {
                                    this.createOtherMenu(player, ConstNpc.IGNORE_MENU, "Về hành tinh hạ đẳng của ngươi mà mua đồ cùi nhé. Tại đây ta chỉ bán đồ cho người Xayda thôi", "Đóng");
                                }
                                break;
                        }
                    }
                }
            }
        };
    }

    public static Npc drDrief(int mapId, int status, int cx, int cy, int tempId, int avartar) {
        return new Npc(mapId, status, cx, cy, tempId, avartar) {
            @Override
            public void openBaseMenu(Player pl) {
                if (canOpenNpc(pl)) {
                    if (this.mapId == 84) {
                        this.createOtherMenu(pl, ConstNpc.BASE_MENU,
                                "Tàu Vũ Trụ của ta có thể đưa cậu đến hành tinh khác chỉ trong 3 giây. Cậu muốn đi đâu?",
                                pl.gender == ConstPlayer.TRAI_DAT ? "Đến\nTrái Đất" : pl.gender == ConstPlayer.NAMEC ? "Đến\nNamếc" : "Đến\nXayda" );
               //     } else if (!TaskService.gI().checkDoneTaskTalkNpc(pl, this)) {
               //         if (pl.playerTask.taskMain.id == 7) {
               //             NpcService.gI().createTutorial(pl, this.avartar, "Hãy lên đường cứu đứa bé nhà tôi\n"
               //                     + "Chắc bây giờ nó đang sợ hãi lắm rồi");
                        } else {
                            this.createOtherMenu(pl, ConstNpc.BASE_MENU,
                                    "Tàu Vũ Trụ của ta có thể đưa cậu đến hành tinh khác chỉ trong 3 giây. Cậu muốn đi đâu?",
                                    "Đến\nNamếc", "Đến\nXayda", "Siêu thị","VamChar");
                        }
                    
                }
            }
            
            @Override
            public void confirmMenu(Player player, int select) {
                if (canOpenNpc(player)) {
                    if (this.mapId == 84) {
                        ChangeMapService.gI().changeMapBySpaceShip(player, player.gender + 24, -1, -1);
                    } else if (player.iDMark.isBaseMenu()) {
                        switch (select) {
                            case 0:
                                ChangeMapService.gI().changeMapBySpaceShip(player, 25, -1, -1);
                                break;
                            case 1:
                                ChangeMapService.gI().changeMapBySpaceShip(player, 26, -1, -1);
                                break;
                            case 2:
                                ChangeMapService.gI().changeMapBySpaceShip(player, 84, -1, -1);
                                break;
                            case 3:
                                if (player.getSession().player.nPoint.power >= 80000000000L) {
                                     ChangeMapService.gI().changeMapBySpaceShip(player, 217, -1, 124);
                                } else {
                                    this.npcChat(player, "Bạn chưa đủ 80 tỷ sức mạnh để vào");
                                }
                                break;   
                        }
                    }
                }
            }
        };
    }
    public static Npc npc92(int mapId, int status, int cx, int cy, int tempId, int avartar) {
        return new Npc(mapId, status, cx, cy, tempId, avartar) {
            @Override
            public void openBaseMenu(Player player) {
                if (canOpenNpc(player)) {
                    createOtherMenu(player, ConstNpc.BASE_MENU,
                            "Ngài Muốn Làm Gì?",
                            "Quay Về", "Từ chối");

                
                
                }
            }

            @Override
            public void confirmMenu(Player player, int select) {
                if (canOpenNpc(player)) {
                    if (this.mapId == 217) {
                        if (player.iDMark.isBaseMenu()) {
                            switch (select) {
                                case 0:
                                    ChangeMapService.gI().changeMapBySpaceShip(player, 5, -1, -1);
                                    break;
                        }
                    }
                }
            }
        }
      };
    }
    public static Npc cargo(int mapId, int status, int cx, int cy, int tempId, int avartar) {
        return new Npc(mapId, status, cx, cy, tempId, avartar) {
            @Override
            public void openBaseMenu(Player pl) {
                if (canOpenNpc(pl)) {
                    if (!TaskService.gI().checkDoneTaskTalkNpc(pl, this)) {
                        if (pl.playerTask.taskMain.id == 7) {
                            NpcService.gI().createTutorial(pl, this.avartar, "Hãy lên đường cứu đứa bé nhà tôi\n"
                                    + "Chắc bây giờ nó đang sợ hãi lắm rồi");
                        } else {
                            this.createOtherMenu(pl, ConstNpc.BASE_MENU,
                                    "Tàu Vũ Trụ của ta có thể đưa cậu đến hành tinh khác chỉ trong 3 giây. Cậu muốn đi đâu?",
                                    "Đến\nTrái Đất", "Đến\nXayda", "Siêu thị","VamChar");
                        }
                    }
                }
            }

            @Override
            public void confirmMenu(Player player, int select) {
                if (canOpenNpc(player)) {
                    if (player.iDMark.isBaseMenu()) {
                        switch (select) {
                            case 0:
                                ChangeMapService.gI().changeMapBySpaceShip(player, 24, -1, -1);
                                break;
                            case 1:
                                ChangeMapService.gI().changeMapBySpaceShip(player, 26, -1, -1);
                                break;
                            case 2:
                                ChangeMapService.gI().changeMapBySpaceShip(player, 84, -1, -1);
                                break;
                            case 3:
                                if (player.getSession().player.nPoint.power >= 80000000000L) {
                                     ChangeMapService.gI().changeMapBySpaceShip(player, 217, -1, 124);
                                } else {
                                    this.npcChat(player, "Bạn chưa đủ 80 tỷ sức mạnh để vào");
                                }
                                break;  
                        }
                    }
                }
            }
        };
    }

    public static Npc cui(int mapId, int status, int cx, int cy, int tempId, int avartar) {
        return new Npc(mapId, status, cx, cy, tempId, avartar) {

            private final int COST_FIND_BOSS = 50000000;

            @Override
            public void openBaseMenu(Player pl) {
                if (canOpenNpc(pl)) {
                    if (!TaskService.gI().checkDoneTaskTalkNpc(pl, this)) {
                        if (pl.playerTask.taskMain.id == 7) {
                            NpcService.gI().createTutorial(pl, this.avartar, "Hãy lên đường cứu đứa bé nhà tôi\n"
                                    + "Chắc bây giờ nó đang sợ hãi lắm rồi");
                        } else {
                            if (this.mapId == 19) {

                                int taskId = TaskService.gI().getIdTask(pl);
                                switch (taskId) {
                                    case ConstTask.TASK_19_0:
                                        this.createOtherMenu(pl, ConstNpc.MENU_FIND_KUKU,
                                                "Đội quân của Fide đang ở Thung lũng Nappa, ta sẽ đưa ngươi đến đó",
                                                "Đến chỗ\nKuku\n(" + Util.numberToMoney(COST_FIND_BOSS) + " vàng)", "Đến Cold", "Đến\nNappa", "Từ chối");
                                        break;
                                    case ConstTask.TASK_19_1:
                                        this.createOtherMenu(pl, ConstNpc.MENU_FIND_MAP_DAU_DINH,
                                                "Đội quân của Fide đang ở Thung lũng Nappa, ta sẽ đưa ngươi đến đó",
                                                "Đến chỗ\nMập đầu đinh\n(" + Util.numberToMoney(COST_FIND_BOSS) + " vàng)", "Đến Cold", "Đến\nNappa", "Từ chối");
                                        break;
                                    case ConstTask.TASK_19_2:
                                        this.createOtherMenu(pl, ConstNpc.MENU_FIND_RAMBO,
                                                "Đội quân của Fide đang ở Thung lũng Nappa, ta sẽ đưa ngươi đến đó",
                                                "Đến chỗ\nRambo\n(" + Util.numberToMoney(COST_FIND_BOSS) + " vàng)", "Đến Cold", "Đến\nNappa", "Từ chối");
                                        break;
                                    default:
                                        this.createOtherMenu(pl, ConstNpc.BASE_MENU,
                                                "Đội quân của Fide đang ở Thung lũng Nappa, ta sẽ đưa ngươi đến đó",
                                                "Đến Cold", "Đến\nNappa", "Từ chối");

                                        break;
                                }
                            } else if (this.mapId == 68) {
                                this.createOtherMenu(pl, ConstNpc.BASE_MENU,
                                        "Ngươi muốn về Thành Phố Vegeta", "Đồng ý", "Từ chối");
                            } else {
                                this.createOtherMenu(pl, ConstNpc.BASE_MENU,
                                        "Tàu vũ trụ Xayda sử dụng công nghệ mới nhất, "
                                        + "có thể đưa ngươi đi bất kỳ đâu, chỉ cần trả tiền là được.",
                                        "Đến\nTrái Đất", "Đến\nNamếc", "Siêu thị","VamChar");
                            }
                        }
                    }
                }
            }

            @Override
            public void confirmMenu(Player player, int select) {
                if (canOpenNpc(player)) {
                    if (this.mapId == 26) {
                        if (player.iDMark.isBaseMenu()) {
                            switch (select) {
                                case 0:
                                    ChangeMapService.gI().changeMapBySpaceShip(player, 24, -1, -1);
                                    break;
                                case 1:
                                    ChangeMapService.gI().changeMapBySpaceShip(player, 25, -1, -1);
                                    break;
                                case 2:
                                    ChangeMapService.gI().changeMapBySpaceShip(player, 84, -1, -1);
                                    break;
                                case 3:
                                if (player.getSession().player.nPoint.power >= 80000000000L) {
                                     ChangeMapService.gI().changeMapBySpaceShip(player, 217, -1, 124);
                                } else {
                                    this.npcChat(player, "Bạn chưa đủ 80 tỷ sức mạnh để vào");
                                }
                                break;  
                            }
                        }
                    }
                    if (this.mapId == 19) {
                        if (player.iDMark.isBaseMenu()) {
                            switch (select) {
                                case 0:
                                    ChangeMapService.gI().changeMapBySpaceShip(player, 109, -1, 295);
                                    break;
                                case 1:
                                    ChangeMapService.gI().changeMapBySpaceShip(player, 68, -1, 90);
                                    break;
                            }
                        } else if (player.iDMark.getIndexMenu() == ConstNpc.MENU_FIND_KUKU) {
                            switch (select) {
                                case 0:
                                    Boss boss = BossManager.gI().getBossById(BossID.KUKU);
                                    if (boss != null || !boss.isDie()) {
                                        if (player.inventory.gold >= COST_FIND_BOSS) {
                                            Zone z = MapService.gI().getMapCanJoin(player, boss.zone.map.mapId, boss.zone.zoneId);
                                            if (z != null && z.getNumOfPlayers() < z.maxPlayer) {
                                                player.inventory.gold -= COST_FIND_BOSS;
                                                ChangeMapService.gI().changeMap(player, boss.zone, boss.location.x, boss.location.y);
                                                Service.getInstance().sendMoney(player);
                                            } else {
                                                Service.getInstance().sendThongBao(player, "Khu vực đang full.");
                                            }
                                        } else {
                                            Service.getInstance().sendThongBao(player, "Không đủ vàng, còn thiếu "
                                                    + Util.numberToMoney(COST_FIND_BOSS - player.inventory.gold) + " vàng");
                                        }
                                        break;
                                    } else {
                                        Service.getInstance().sendThongBao(player, "Chết rồi ba...");
                                    }
                                    Service.getInstance().sendThongBao(player, "Chết rồi ba...");
                                    break;
                                case 1:
                                    ChangeMapService.gI().changeMapBySpaceShip(player, 109, -1, 295);
                                    break;
                                case 2:
                                    ChangeMapService.gI().changeMapBySpaceShip(player, 68, -1, 90);
                                    break;
                            }
                        } else if (player.iDMark.getIndexMenu() == ConstNpc.MENU_FIND_MAP_DAU_DINH) {
                            switch (select) {
                                case 0:
                                    Boss boss = BossManager.gI().getBossById(BossID.MAP_DAU_DINH);
                                    if (boss != null && !boss.isDie()) {
                                        if (player.inventory.gold >= COST_FIND_BOSS) {
                                            Zone z = MapService.gI().getMapCanJoin(player, boss.zone.map.mapId, boss.zone.zoneId);
                                            if (z.getNumOfPlayers() < z.maxPlayer) {
                                                player.inventory.gold -= COST_FIND_BOSS;
                                                ChangeMapService.gI().changeMap(player, boss.zone, boss.location.x, boss.location.y);
                                                Service.getInstance().sendMoney(player);
                                            } else {
                                                Service.getInstance().sendThongBao(player, "Khu vực đang full.");
                                            }
                                        } else {
                                            Service.getInstance().sendThongBao(player, "Không đủ vàng, còn thiếu "
                                                    + Util.numberToMoney(COST_FIND_BOSS - player.inventory.gold) + " vàng");
                                        }
                                        break;
                                    }
                                    Service.getInstance().sendThongBao(player, "Chết rồi ba...");
                                    break;
                                case 1:
                                    ChangeMapService.gI().changeMapBySpaceShip(player, 109, -1, 295);
                                    break;
                                case 2:
                                    ChangeMapService.gI().changeMapBySpaceShip(player, 68, -1, 90);
                                    break;
                            }
                        } else if (player.iDMark.getIndexMenu() == ConstNpc.MENU_FIND_RAMBO) {
                            switch (select) {
                                case 0:
                                    Boss boss = BossManager.gI().getBossById(BossID.RAMBO);
                                    if (boss != null && !boss.isDie() || player != null) {
                                        if (player.inventory.gold >= COST_FIND_BOSS) {
                                            Zone z = MapService.gI().getMapCanJoin(player, boss.zone.map.mapId, boss.zone.zoneId);
                                            if (z.getNumOfPlayers() < z.maxPlayer) {
                                                player.inventory.gold -= COST_FIND_BOSS;
                                                ChangeMapService.gI().changeMap(player, boss.zone, boss.location.x, boss.location.y);
                                                Service.getInstance().sendMoney(player);
                                            } else {
                                                Service.getInstance().sendThongBao(player, "Khu vực đang full.");
                                            }
                                        } else {
                                            Service.getInstance().sendThongBao(player, "Không đủ vàng, còn thiếu "
                                                    + Util.numberToMoney(COST_FIND_BOSS - player.inventory.gold) + " vàng");
                                        }
                                        break;
                                    } else {
                                        Service.getInstance().sendThongBao(player, "Chết rồi ba...");
                                    }
                                    Service.getInstance().sendThongBao(player, "Chết rồi ba...");
                                    break;
                                case 1:
                                    ChangeMapService.gI().changeMapBySpaceShip(player, 109, -1, 295);
                                    break;
                                case 2:
                                    ChangeMapService.gI().changeMapBySpaceShip(player, 68, -1, 90);
                                    break;
                            }
                        }
                    }
                    if (this.mapId == 68) {
                        if (player.iDMark.isBaseMenu()) {
                            switch (select) {
                                case 0:
                                    ChangeMapService.gI().changeMapBySpaceShip(player, 19, -1, 1100);
                                    break;
                            }
                        }
                    }
                }
            }
        };
    }
    public static Npc Kichi(int mapId, int status, int cx, int cy, int tempId, int avartar) {
        return new Npc(mapId, status, cx, cy, tempId, avartar) {
                public void openBaseMenu(Player player) {
                if (canOpenNpc(player)) {
                    if (this.mapId == 5) {
                        this.createOtherMenu(player, ConstNpc.BASE_MENU, "|7|Ngươi cần gì ở ta!!\b|1|ta sẽ tặng ngươi những\ngì ngươi muốn với điều kiện....", "Đổi PET", "Đổi linh thú","Đổi Đeo lưng","Đóng");
                    }
                }
            }
            @Override
            public void confirmMenu(Player player, int select) {
                if (canOpenNpc(player)) {
                    if (this.mapId == 5) {
                        if (player.iDMark.isBaseMenu()) {
                            switch (select) {
                                case 0: {  // pet
                                    Item voso = null;
                                    Item vooc = null;
                                    try {
                                        voso = InventoryServiceNew.gI().findItemBag(player, 2137);
                                        vooc = InventoryServiceNew.gI().findItemBag(player, 2138);
                                    } catch (Exception e) {
//                                        throw new RuntimeException(e);
                                    }
                                    if (voso == null || voso.quantity < 99 || vooc == null || vooc.quantity < 99) {
                                        this.npcChat(player, "|1|Bạn không đủ x99 Vỏ sò và x99 vỏ ốc");
                                //    if (vooc == null || vooc.quantity < 99) {
                                //        this.npcChat(player, "Bạn không đủ x99 vỏ ốc");    
                                    } else if (player.inventory.gold < 500_000_000) {
                                        this.npcChat(player, "|1|Bạn không đủ 500 triệu vàng");
                                    } else if (InventoryServiceNew.gI().getCountEmptyBag(player) == 0) {
                                        this.npcChat(player, "|1|Hành trang của bạn không đủ chỗ trống");
                                    } else {
                                        player.inventory.gold -= 500_000_000;
                                        InventoryServiceNew.gI().subQuantityItemsBag(player, voso, 99);
                                        InventoryServiceNew.gI().subQuantityItemsBag(player, vooc, 99);
                                        Service.getInstance().sendMoney(player);
                                        Item PETrandom = ItemService.gI().createNewItem((short) 2145);
                                        InventoryServiceNew.gI().addItemBag(player, PETrandom);
                                        InventoryServiceNew.gI().sendItemBags(player);
                                        this.npcChat(player, "|1|Bạn nhận được 1 Túi pet");
                                    }
                                    break;
                                    }
                                
                                case 1: {   // linh thú
                                    Item voso = null;
                                    Item vooc = null;
                                    try {
                                        voso = InventoryServiceNew.gI().findItemBag(player, 2141);
                                        vooc = InventoryServiceNew.gI().findItemBag(player, 2142);
                                    } catch (Exception e) {
//                                        throw new RuntimeException(e);
                                    }
                                    if (voso == null || voso.quantity < 99 || vooc == null || vooc.quantity < 99) {
                                        this.npcChat(player, "|1|Bạn không đủ x99 Hạt giống và x99 Lông linh thú");
                                //    if (vooc == null || vooc.quantity < 99) {
                                //        this.npcChat(player, "Bạn không đủ x99 Lông linh thú");    
                                    } else if (player.inventory.gold < 500_000_000) {
                                        this.npcChat(player, "|1|Bạn không đủ 500 triệu vàng");
                                    } else if (InventoryServiceNew.gI().getCountEmptyBag(player) == 0) {
                                        this.npcChat(player, "|1|Hành trang của bạn không đủ chỗ trống");
                                    } else {
                                        player.inventory.gold -= 500_000_000;
                                        InventoryServiceNew.gI().subQuantityItemsBag(player, voso, 99);
                                        InventoryServiceNew.gI().subQuantityItemsBag(player, vooc, 99);
                                        Service.getInstance().sendMoney(player);
                                        Item PETrandom = ItemService.gI().createNewItem((short) 1999);
                                        InventoryServiceNew.gI().addItemBag(player, PETrandom);
                                        InventoryServiceNew.gI().sendItemBags(player);
                                        this.npcChat(player, "|1|Bạn nhận được 1 Túi quà linh thú");
                                    }
                                    break;
                                }
                                
                                case 2: {// đổi đeo lưng
                                    Item voso = null;
                                    Item vooc = null;
                                    try {
                                        voso = InventoryServiceNew.gI().findItemBag(player, 2139);
                                        vooc = InventoryServiceNew.gI().findItemBag(player, 2140);
                                    } catch (Exception e) {
//                                        throw new RuntimeException(e);
                                    }
                                    if (voso == null || voso.quantity < 99 || vooc == null || vooc.quantity < 99) {
                                        this.npcChat(player, "|1|Bạn không đủ x99 da thú và x99 Lông thú");
                                //    if (vooc == null || vooc.quantity < 99) {
                                //        this.npcChat(player, "Bạn không đủ x99 Lông thú");    
                                    } else if (player.inventory.gold < 500_000_000) {
                                        this.npcChat(player, "|1|Bạn không đủ 500 triệu vàng");
                                    } else if (InventoryServiceNew.gI().getCountEmptyBag(player) == 0) {
                                        this.npcChat(player, "|1|Hành trang của bạn không đủ chỗ trống");
                                    } else {
                                        player.inventory.gold -= 500_000_000;
                                        InventoryServiceNew.gI().subQuantityItemsBag(player, voso, 99);
                                        InventoryServiceNew.gI().subQuantityItemsBag(player, vooc, 99);
                                        Service.getInstance().sendMoney(player);
                                        Item PETrandom = ItemService.gI().createNewItem((short) 1998);
                                        InventoryServiceNew.gI().addItemBag(player, PETrandom);
                                        InventoryServiceNew.gI().sendItemBags(player);
                                        this.npcChat(player, "|1|Bạn nhận được 1 Túi quà đeo lưng");
                                    }
                                    break;
                                }
                                
                                
                                
                            }
                        }
                    }
                }
            }
        };
    }
    public static Npc Anime(int mapId, int status, int cx, int cy, int tempId, int avartar) {
        return new Npc(mapId, status, cx, cy, tempId, avartar) {
                public void openBaseMenu(Player player) {
                if (canOpenNpc(player)) {
                    if (this.mapId == 5) {
                        this.createOtherMenu(player, ConstNpc.BASE_MENU, "|7|Ngươi cần gì ở ta!!\b|1|ta sẽ tặng ngươi những\ngì ngươi muốn với điều kiện....", "Đổi Thời Trang","Đóng");
                    }
                }
            }
            @Override
            public void confirmMenu(Player player, int select) {
                if (canOpenNpc(player)) {
                    if (this.mapId == 5) {
                        if (player.iDMark.isBaseMenu()) {
                            switch (select) {
                                case 0: {  // pet
                                    Item voso = null;
                                    Item vooc = null;
                                    Item voso1 = null;
                                    Item vooc2 = null;
                                    try {
                                        voso = InventoryServiceNew.gI().findItemBag(player, 2148);
                                        vooc = InventoryServiceNew.gI().findItemBag(player, 2149);
                                        voso1 = InventoryServiceNew.gI().findItemBag(player, 2150);
                                        vooc2 = InventoryServiceNew.gI().findItemBag(player, 2151);
                                    } catch (Exception e) {
//                                        throw new RuntimeException(e);
                                    }
                                    if (voso == null || voso.quantity < 1 || vooc == null || vooc.quantity < 99 || voso1 == null || voso1.quantity < 99 || vooc2 == null || vooc2.quantity < 99) {
                                        this.npcChat(player, "|1|Bạn không đủ x1 ngọc thạch, x99 Cánh thiên thần + x99 Áo choàng kháng phép + x99 Trượng phép thuật");
                                //    if (vooc == null || vooc.quantity < 99) {
                                //        this.npcChat(player, "Bạn không đủ x99 vỏ ốc");    
                                    } else if (player.inventory.gold < 2_000_000_000) {
                                        this.npcChat(player, "|1|Bạn không đủ 2 Tỷ vàng");
                                    } else if (InventoryServiceNew.gI().getCountEmptyBag(player) == 0) {
                                        this.npcChat(player, "|1|Hành trang của bạn không đủ chỗ trống");
                                    } else {
                                        player.inventory.gold -= 500_000_000;
                                        InventoryServiceNew.gI().subQuantityItemsBag(player, voso, 1);
                                        InventoryServiceNew.gI().subQuantityItemsBag(player, vooc, 99);
                                        InventoryServiceNew.gI().subQuantityItemsBag(player, voso1, 99);
                                        InventoryServiceNew.gI().subQuantityItemsBag(player, vooc2, 99);
                                        Service.getInstance().sendMoney(player);
                                        Item PETrandom = ItemService.gI().createNewItem((short) 2152);
                                        InventoryServiceNew.gI().addItemBag(player, PETrandom);
                                        InventoryServiceNew.gI().sendItemBags(player);
                                        this.npcChat(player, "|1|Bạn nhận được 1 Túi Thời trang Anime");
                                    }
                                    break;
                                    }
                                
                                
                                
                                
                                
                            }
                        }
                    }
                }
            }
        };
    }

    public static Npc santa(int mapId, int status, int cx, int cy, int tempId, int avartar) {
        return new Npc(mapId, status, cx, cy, tempId, avartar) {
            @Override
            public void openBaseMenu(Player player) {
                if (canOpenNpc(player)) {
                    createOtherMenu(player, ConstNpc.BASE_MENU,
                            "Xin chào, ta có một số vật phẩm đặt biệt cậu có muốn xem không!!! để qua được map up mảnh cần 80 tỷ sức mạnh + 50 triệu vàng",
                            "Cửa hàng","Khiêu chiến","Map\nUp Mảnh Vỡ");//,"Phụ kiện", "Vật phẩm");
                }
            }

            @Override
            public void confirmMenu(Player player, int select) {
                if (canOpenNpc(player)) {
                    if (this.mapId == 5 || this.mapId == 13 || this.mapId == 20) {
                        if (player.iDMark.isBaseMenu()) {
                            switch (select) {
                                case 0: //shop
                                    ShopServiceNew.gI().opendShop(player, "SANTA", false);
                                    break;
                            //    case 1:
                            //        ShopServiceNew.gI().opendShop(player, "CAI_TRANG", false);                                                                      
                            //        break;
                             //   case 2:
                              //      ShopServiceNew.gI().opendShop(player, "PHU_KIEN", false);                                                                      
                              //      break;
                                    case 1:
                                if(player.getSession().player.nPoint.power < 10000000000L){
                                Service.gI().sendThongBao(player, "Cần Có Sức Mạnh Là 10 Tỉ");}
                                else if(player.getSession().player.inventory.gold < 300000000){
                                Service.gI().sendThongBao(player, "Cần 300tr Vàng");
                            }   else {player.nPoint.power -=10000000000L ;
                            player.getSession().player.inventory.gold -= 300000000;
                            player.nPoint.teleport=true;
                            player.idAura=95;
                            player.name="[ Hủy Diệt]\n"+player.name;
                            Service.gI().player(player);
                            Service.gI().Send_Caitrang(player);
                            Service.gI().sendFlagBag(player);
                            Zone zone = player.zone;
                            ChangeMapService.gI().changeMap(player, zone, player.location.x, player.location.y);
//                            ChangeMapService.gI().changeMapBySpaceShip(player, 5, -1, 495);
                                        Service.gI().changeFlag(player, 8);
                            PlayerService.gI().changeAndSendTypePK(player, ConstPlayer.PK_ALL);
                            new Thread(() -> {
                            try {
                            Thread.sleep(240000);
                                } catch (Exception e) {
                                }
                            Client.gI().kickSession (player.getSession());
                            }).start();
                            
                            Service.gI().sendThongBaoAllPlayer("Kẻ "+player.name+" Đang Ở "+ player.zone.map.mapName + " Khu " + player.zone.zoneId);
                                    break;
                                }
                             case 2:
                                 if (player.getSession().player.nPoint.power >= 80000000000L && player.inventory.gold > COST_HD) {
                                player.inventory.gold -= COST_HD;
                                Service.gI().sendMoney(player);
                                    ChangeMapService.gI().changeMapBySpaceShip(player, 156, -1, 90);
                                    } else {
                                this.npcChat(player, "Bạn chưa đủ 80 tỷ sức mạnh, 50 triệu vàng");
                                    break;   
                                 }
                            
                            //    case 3:
                            //        ShopServiceNew.gI().opendShop(player, "VAT_PHAM", false);                                                                      
                            //        break;
                            //    case 9:
                            //        ShopServiceNew.gI().opendShop(player, "SANTA_EVENT", false);                                                                      
                            //        break;
                            //    case 4:
                            //        this.createOtherMenu(player, 1, "|7|Số tiền của bạn còn : " + player.getSession().vnd + " VND\n"
                            //                + "Tỉ lệ quy đổi là 1000VND = 4 thỏi vàng\n" + "1000VND = 2000 hồng ngọc\n Ví Dụ Có 10.000VND Thì Nhập Vào Là 10\nCứ Quy Đổi Kể Cả 1 Thỏi Vàng Là Được Kích Hoạt Tài Khoản\nQuy Đổi Lỗi Thì Quy Đổi Lại Lần 2", "Quy đổi\n Thỏi vàng", "Quy Đổi\nHồng Ngọc", "Mở thành viên");
                            //        break;
                            }
                        }
                        else if (player.iDMark.getIndexMenu() == 1){
                            switch (select) {
                                case 0:
                                    Input.gI().createFormQDTV(player);
                                    break;
                                case 1:
                                    Input.gI().createFormQDHN(player);
                                    break;
                                case 2:
                                    if (player.getSession().vnd >= 20000){
                                        if (player.kichhoat == 0){
                                            player.kichhoat = 1;
                                            PlayerDAO.subvnd(player, 20000);
                                            player.getSession().vnd -= 20000;
                                            Service.gI().sendThongBao(player, "Kích hoạt thành công");
                                        } else {
                                            Service.getInstance().sendThongBao(player, "Đã kích hoạt thành viên rồi");
                                        }
                                    } else {
                                        Service.getInstance().sendThongBao(player, "Bạn không đủ 20k");
                                    }
                                    break;
                            }
                        }
                    }
                }
            }
        };
    }

    public static Npc uron(int mapId, int status, int cx, int cy, int tempId, int avartar) {
        return new Npc(mapId, status, cx, cy, tempId, avartar) {
            @Override
            public void openBaseMenu(Player pl) {
                if (canOpenNpc(pl)) {
                    ShopServiceNew.gI().opendShop(pl, "URON", false);
                }
            }

            @Override
            public void confirmMenu(Player player, int select) {
                if (canOpenNpc(player)) {

                }
            }
        };
    }

    public static Npc baHatMit(int mapId, int status, int cx, int cy, int tempId, int avartar) {
        return new Npc(mapId, status, cx, cy, tempId, avartar) {
            @Override
            public void openBaseMenu(Player player) {
                if (canOpenNpc(player)) {
                    if (this.mapId == 5) {
                        this.createOtherMenu(player, ConstNpc.BASE_MENU,
                                "Ngươi tìm ta có việc gì?",
                                "Ép sao\ntrang bị", "Pha lê\nhóa\ntrang bị", "Nâng cấp\nThần Linh");//,"Nâng cấp\nHủy Diệt","Nâng cấp\nThiên Sứ");
                    } else if (this.mapId == 121) {
                        this.createOtherMenu(player, ConstNpc.BASE_MENU,
                                "Ngươi tìm ta có việc gì?",
                                "Về đảo\nrùa");

                    } else {

                        this.createOtherMenu(player, ConstNpc.BASE_MENU,
                                "Ngươi tìm ta có việc gì?",
                                "Cửa hàng\nBùa", "Nâng cấp\nVật phẩm", "Nhập\nNgọc Rồng",
                                "Nâng cấp\nBông tai\nPorata cấp2", "Nâng cấp\nBông tai\nPorata cấp3","Nâng cấp\nBông tai\nPorata cấp4","Nâng cấp\nBông tai\nPorata cấp5","Mở chỉ số\nBông tai\nPorata Cấp5","Tiệm đá");
                    }
                }
            }

            @Override
            public void confirmMenu(Player player, int select) {
                if (canOpenNpc(player)) {
                    if (this.mapId == 5) {
                        if (player.iDMark.isBaseMenu()) {
                            switch (select) {
                                case 0:
                                    CombineServiceNew.gI().openTabCombine(player, CombineServiceNew.EP_SAO_TRANG_BI);
                                    break;
                                case 1:
                                    CombineServiceNew.gI().openTabCombine(player, CombineServiceNew.PHA_LE_HOA_TRANG_BI);
                                    break;
                                case 2:
                                    CombineServiceNew.gI().openTabCombine(player, CombineServiceNew.NANG_CAP_SKH_VIP);
                                    break;
                                case 3:
                                    CombineServiceNew.gI().openTabCombine(player, CombineServiceNew.NANG_CAP_SKH_VIPhd);
                                    break;
                                case 4:
                                    CombineServiceNew.gI().openTabCombine(player, CombineServiceNew.NANG_CAP_SKH_VIPts);
                                    break;    
                            //    case 2:
                            //        CombineServiceNew.gI().openTabCombine(player, CombineServiceNew.kh_Tl);
                            //        break;
                            //        case 3:
                            //    CombineServiceNew.gI().openTabCombine(player, CombineServiceNew.kh_Hd);
                            //        break;
                            //        case 4:
                            //    CombineServiceNew.gI().openTabCombine(player, CombineServiceNew.kh_Ts);
                            //        break;
                            }
                        } else if (player.iDMark.getIndexMenu() == ConstNpc.MENU_START_COMBINE) {
                            switch (player.combineNew.typeCombine) {
                                case CombineServiceNew.EP_SAO_TRANG_BI:
                                case CombineServiceNew.PHA_LE_HOA_TRANG_BI:
                                case CombineServiceNew.CHUYEN_HOA_TRANG_BI:
                                case CombineServiceNew.NANG_CAP_SKH_VIP:
                                    case CombineServiceNew.NANG_CAP_SKH_VIPhd:
                                        case CombineServiceNew.NANG_CAP_SKH_VIPts:
                                case CombineServiceNew.kh_Tl:
                                case CombineServiceNew.kh_Hd:
                                case CombineServiceNew.kh_Ts:
                                    switch (select) {
                                        case 0:
                                            if (player.combineNew.typeCombine == CombineServiceNew.PHA_LE_HOA_TRANG_BI) {
                                                player.combineNew.quantities = 1;
                                            }
                                            break;
                                        case 1:
                                            if (player.combineNew.typeCombine == CombineServiceNew.PHA_LE_HOA_TRANG_BI) {
                                                player.combineNew.quantities = 10;
                                            }
                                            break;
                                        case 2:
                                            if (player.combineNew.typeCombine == CombineServiceNew.PHA_LE_HOA_TRANG_BI) {
                                                player.combineNew.quantities = 100;
                                            }
                                            break;      
                                    }
                                        CombineServiceNew.gI().startCombine(player);
                            }
                        } else if (player.iDMark.getIndexMenu() == ConstNpc.MENU_DAP_DO_KICH_HOAT) {
                            if (select == 0) {
                                CombineServiceNew.gI().startCombine(player);
                            }
                        } else if (player.iDMark.getIndexMenu() == ConstNpc.MENU_NANG_DOI_SKH_VIP) {
                            if (select == 0) {
                                CombineServiceNew.gI().startCombine(player);
                            }
                        }
                    } else if (this.mapId == 112) {
                        if (player.iDMark.isBaseMenu()) {
                            switch (select) {
                                case 0:
                                    ChangeMapService.gI().changeMapBySpaceShip(player, 5, -1, 1156);
                                    break;
                            }
                        }
                    } else if (this.mapId == 42 || this.mapId == 43 || this.mapId == 44 || this.mapId == 84) {
                        if (player.iDMark.isBaseMenu()) {
                            switch (select) {
                                case 0: //shop bùa
                                    createOtherMenu(player, ConstNpc.MENU_OPTION_SHOP_BUA,
                                            "Bùa của ta rất lợi hại, nhìn ngươi yếu đuối thế này, chắc muốn mua bùa để "
                                            + "mạnh mẽ à, mua không ta bán cho, xài rồi lại thích cho mà xem.",
                                            "Bùa\n1 giờ", "Bùa\n8 giờ", "Bùa\n1 tháng", "Đóng");
                                    break;
                                case 1://nâng cấp vật phẩm
                                    CombineServiceNew.gI().openTabCombine(player, CombineServiceNew.NANG_CAP_VAT_PHAM);
                                    break;
                                case 2://nhập ngọc rồng
                                    CombineServiceNew.gI().openTabCombine(player, CombineServiceNew.NHAP_NGOC_RONG);
                                    break;    
                                case 3: //nâng cấp bông tai
                                    CombineServiceNew.gI().openTabCombine(player, CombineServiceNew.NANG_CAP_BONG_TAI);
                                    break;
                                
                                    case 4: //
                                    CombineServiceNew.gI().openTabCombine(player, CombineServiceNew.NANG_CAP_BONG_TAI_CAP3);
                                    break;
                                    case 5: //
                                    CombineServiceNew.gI().openTabCombine(player, CombineServiceNew.NANG_CAP_BONG_TAI_CAP4);
                                    break;
                                    case 6: //
                                    CombineServiceNew.gI().openTabCombine(player, CombineServiceNew.NANG_CAP_BONG_TAI_CAP5);
                                    break;
                            case 7: //
                                    CombineServiceNew.gI().openTabCombine(player, CombineServiceNew.MO_CHI_SO_BONG_TAI);
                                    break;  
                            case 8:
                            ShopServiceNew.gI().opendShop(player, "VAT_PHAM", false);                                                                      
                                    break;        
                                    
                                    
                                
                            }
                        } else if (player.iDMark.getIndexMenu() == ConstNpc.MENU_OPTION_SHOP_BUA) {
                            switch (select) {
                                case 0:
                                    ShopServiceNew.gI().opendShop(player, "BUA_1H", true);
                                    break;
                                case 1:
                                    ShopServiceNew.gI().opendShop(player, "BUA_8H", true);
                                    break;
                                case 2:
                                    ShopServiceNew.gI().opendShop(player, "BUA_1M", true);
                                    break;
                            }
                        } else if (player.iDMark.getIndexMenu() == ConstNpc.MENU_START_COMBINE) {
                            switch (player.combineNew.typeCombine) {
                                case CombineServiceNew.NANG_CAP_VAT_PHAM:
                                case CombineServiceNew.NANG_CAP_BONG_TAI:
                                    case CombineServiceNew.NANG_CAP_BONG_TAI_CAP3:
                                        case CombineServiceNew.NANG_CAP_BONG_TAI_CAP4:
                                            case CombineServiceNew.NANG_CAP_BONG_TAI_CAP5:
                                case CombineServiceNew.MO_CHI_SO_BONG_TAI:
                                case CombineServiceNew.NHAP_NGOC_RONG:            
                                    if (select == 0) {
                                        CombineServiceNew.gI().startCombine(player);
                                    }
                                    break;
                            }                          
                        }
                    }
                }
            }
        };
    }


    public static Npc ruongDo(int mapId, int status, int cx, int cy, int tempId, int avartar) {
        return new Npc(mapId, status, cx, cy, tempId, avartar) {

            @Override
            public void openBaseMenu(Player player) {
                if (canOpenNpc(player)) {
                    InventoryServiceNew.gI().sendItemBox(player);
                }
            }

            @Override
            public void confirmMenu(Player player, int select) {
                if (canOpenNpc(player)) {

                }
            }
        };
    }

    public static Npc duongtank(int mapId, int status, int cx, int cy, int tempId, int avartar) {
        return new Npc(mapId, status, cx, cy, tempId, avartar) {

            @Override
            public void openBaseMenu(Player player) {
                if (canOpenNpc(player)) {
                    if (mapId == 0) {
                        this.createOtherMenu(player, 0, "Ngũ Hàng Sơn x2 Tnsm\nHỗ trợ cho Ae Từ\b|1|80 Tỷ SM Trở Lên", "Vào Ngay","Đóng");
                    }
                    if (mapId == 122) {
                        this.createOtherMenu(player, 0, "Bạn Muốn Quay Trở Lại Làng Ảru?", "OK", "Từ chối");

                    }
                    if (mapId == 124) {
                        this.createOtherMenu(player, 0, "Xia xia thua phùa\b|7|Thí chủ đang có: " + player.NguHanhSonPoint + " điểm ngũ hành sơn\b|1|Thí chủ muốn đổi cải trang x4 chưởng ko?", "Âu kê", "Top Ngu Hanh Son", "No");
                    }
                }
            }

            @Override
            public void confirmMenu(Player player, int select) {
                if (canOpenNpc(player)) {
                         if (select == 0) {
                            if (mapId == 0) {
                                ChangeMapService.gI().changeMapInYard(player, 122, -1, 174);
                            }
                            if (mapId == 122) {
                                ChangeMapService.gI().changeMapInYard(player, 0, -1, 469);
                            }
                            if (mapId == 124) {
                               switch (select) {
                                   case 0:
                                    if (player.NguHanhSonPoint >= 500) {
                                        player.NguHanhSonPoint -= 500;
                                        Item item = ItemService.gI().createNewItem((short) (711));
                                        item.itemOptions.add(new Item.ItemOption(50, 1));
                                        item.itemOptions.add(new Item.ItemOption(77, 1));
                                        item.itemOptions.add(new Item.ItemOption(103, 1));
                                        item.itemOptions.add(new Item.ItemOption(207, 0));
                                        item.itemOptions.add(new Item.ItemOption(33, 0));                                      
                                        InventoryServiceNew.gI().addItemBag(player, item);
                                        Service.gI().sendThongBao(player, "Chúc Mừng Bạn Đổi Vật Phẩm Thành Công !");
                                    } else {
                                        Service.gI().sendThongBao(player, "Không đủ điểm, bạn còn " + (500 - player.pointPvp) + " điểm nữa");          
                                    }
                                   if (select == 1) {
                                        Service.gI().showListTop(player, Manager.topNHS);
                                        break;
                            }
                        }
                    }
                }
            }
        }
     };
  }
    

        public static Npc thodaika(int mapId, int status, int cx, int cy, int tempId, int avartar) {
        return new Npc(mapId, status, cx, cy, tempId, avartar) {
            @Override
            public void openBaseMenu(Player player) {
                if (canOpenNpc(player)) {
                    createOtherMenu(player, ConstNpc.BASE_MENU,
                            "Đưa cho ta thỏi vàng và ngươi sẽ mua đc oto\nĐây không phải chẵn lẻ tài xỉu đâu=)))",
                            "Xỉu", "Tài");
                }
            }

            @Override
            public void confirmMenu(Player player, int select) {
                if (canOpenNpc(player)) {
                    if (this.mapId == 5) {
                        if (player.iDMark.isBaseMenu()) {
                            switch (select) {
                                case 0:
                                    Input.gI().TAI(player);
                                    break;
                                case 1:
                                    Input.gI().XIU(player);
                                    break;

                            }
                        }
                    }
                }
            }
        };
        
        }
    
    public static Npc blackgoku(int mapId, int status, int cx, int cy, int tempId, int avartar) {
        return new Npc(mapId, status, cx, cy, tempId, avartar) {

            @Override
            public void openBaseMenu(Player player) {
                if (canOpenNpc(player)) {
                    if (mapId == 170 || mapId == 5) {
                        this.createOtherMenu(player, 0, "Map Fam \nHỗ trợ cho Ae Từ\b|1|80 Tỷ SM?", "OK", "Oéo");
                    }
                    if (mapId == 123) {
                        this.createOtherMenu(player, 0, "Bạn Muốn Quay Trở Lại Làng Ảru?", "OK", "Từ chối");

                    }
                    if (mapId == 122) {
                        this.createOtherMenu(player, 0, "Xia xia thua phùa\b|7|Thí chủ đang có: " + player.NguHanhSonPoint + " điểm ngũ hành sơn\b|1|Thí chủ muốn đổi cải trang x4 chưởng ko?", "Âu kê", "Top Ngu Hanh Son", "No");
                    }
                }
            }

            @Override
            public void confirmMenu(Player player, int select) {
                if (canOpenNpc(player)) {
                    switch (select) {
                        case 0:
                            if (mapId == 170|| mapId == 5) {
                                if (player.nPoint.power <= 80000000000L) {
                                    Service.getInstance().sendThongBao(player, "Sức mạnh bạn không phù hợp để qua map!");
                                    return;
                                }
                                ChangeMapService.gI().changeMapInYard(player, 171, -1, -1);
                            }
                            if (mapId == 123) {
                                ChangeMapService.gI().changeMapInYard(player, 0, -1, -1);
                            }
                            if (mapId == 122) {
                                if (select == 0) {
                                    if (player.NguHanhSonPoint >= 500) {
                                        player.NguHanhSonPoint -= 500;
                                        Item item = ItemService.gI().createNewItem((short) (711));
                                        item.itemOptions.add(new Item.ItemOption(49, 8));
                                        item.itemOptions.add(new Item.ItemOption(77, 8));
                                        item.itemOptions.add(new Item.ItemOption(103, 5));
                                        item.itemOptions.add(new Item.ItemOption(207, 0));
                                        item.itemOptions.add(new Item.ItemOption(33, 0));
//                                      
                                        InventoryServiceNew.gI().addItemBag(player, item);
                                        Service.getInstance().sendThongBao(player, "Chúc Mừng Bạn Đổi Vật Phẩm Thành Công !");
                                    } else {
                                        Service.getInstance().sendThongBao(player, "Không đủ điểm, bạn còn " + (500 - player.pointPvp) + " điểm nữa");
                                    }
                                } else if (select == 1) {
                                    Util.showListTop(player, (byte) 4
                                    );
                                }
                            }
                            break;
                    }
                }
            }
        };
    }

    public static Npc dauThan(int mapId, int status, int cx, int cy, int tempId, int avartar) {
        return new Npc(mapId, status, cx, cy, tempId, avartar) {
            @Override
            public void openBaseMenu(Player player) {
                if (canOpenNpc(player)) {
                    player.magicTree.openMenuTree();
                }
            }

            @Override
            public void confirmMenu(Player player, int select) {
                if (canOpenNpc(player)) {
                    TaskService.gI().checkDoneTaskConfirmMenuNpc(player, this, (byte) select);
                    switch (player.iDMark.getIndexMenu()) {
                        case ConstNpc.MAGIC_TREE_NON_UPGRADE_LEFT_PEA:
                            if (select == 0) {
                                player.magicTree.harvestPea();
                            } else if (select == 1) {
                                if (player.magicTree.level == 10) {
                                    player.magicTree.fastRespawnPea();
                                } else {
                                    player.magicTree.showConfirmUpgradeMagicTree();
                                }
                            } else if (select == 2) {
                                player.magicTree.fastRespawnPea();
                            }
                            break;
                        case ConstNpc.MAGIC_TREE_NON_UPGRADE_FULL_PEA:
                            if (select == 0) {
                                player.magicTree.harvestPea();
                            } else if (select == 1) {
                                player.magicTree.showConfirmUpgradeMagicTree();
                            }
                            break;
                        case ConstNpc.MAGIC_TREE_CONFIRM_UPGRADE:
                            if (select == 0) {
                                player.magicTree.upgradeMagicTree();
                            }
                            break;
                        case ConstNpc.MAGIC_TREE_UPGRADE:
                            if (select == 0) {
                                player.magicTree.fastUpgradeMagicTree();
                            } else if (select == 1) {
                                player.magicTree.showConfirmUnuppgradeMagicTree();
                            }
                            break;
                        case ConstNpc.MAGIC_TREE_CONFIRM_UNUPGRADE:
                            if (select == 0) {
                                player.magicTree.unupgradeMagicTree();
                            }
                            break;
                    }
                }
            }
        };
    }

    public static Npc calick(int mapId, int status, int cx, int cy, int tempId, int avartar) {
        return new Npc(mapId, status, cx, cy, tempId, avartar) {
            private final byte COUNT_CHANGE = 50;
            private int count;

            private void changeMap() {
                if (this.mapId != 102) {
                    count++;
                    if (this.count >= COUNT_CHANGE) {
                        count = 0;
                        this.map.npcs.remove(this);
                        Map map = MapService.gI().getMapForCalich();
                        if (map != null) {
                            this.mapId = map.mapId;
                            this.cx = Util.nextInt(100, map.mapWidth - 100);
                            this.cy = map.yPhysicInTop(this.cx, 0);
                            this.map = map;
                            this.map.npcs.add(this);
                        }
                    }
                }
            }

            @Override
            public void openBaseMenu(Player player) {
                player.iDMark.setIndexMenu(ConstNpc.BASE_MENU);
                if (TaskService.gI().getIdTask(player) < ConstTask.TASK_20_0) {
                    Service.gI().hideWaitDialog(player);
                    Service.gI().sendThongBao(player, "Không thể thực hiện");
                    return;
                }
                if (this.mapId != player.zone.map.mapId) {
                    Service.gI().sendThongBao(player, "Calích đã rời khỏi map!");
                    Service.gI().hideWaitDialog(player);
                    return;
                }
                if (!TaskService.gI().checkDoneTaskTalkNpc(player, this)) {
                    if (this.mapId == 102) {
                        this.createOtherMenu(player, ConstNpc.BASE_MENU,
                                "Chào chú, cháu có thể giúp gì? Muốn qua tương lai cần làm xong nhiệm vụ đưa thuốc trợ tim cho quy lão",
                                "Kể\nChuyện", "Quay về\nQuá khứ");
                    } else {
                        this.createOtherMenu(player, ConstNpc.BASE_MENU,
                                "Chào chú, cháu có thể giúp gì? Muốn qua tương lai cần làm xong nhiệm vụ đưa thuốc trợ tim cho quy lão", "Kể\nChuyện", "Đi đến\nTương lai", "Từ chối");
                    }
                }
            }

            @Override
            public void confirmMenu(Player player, int select) {
                if (this.mapId == 102) {
                    if (player.iDMark.isBaseMenu()) {
                        if (select == 0) {
                            //kể chuyện
                            NpcService.gI().createTutorial(player, this.avartar, ConstNpc.CALICK_KE_CHUYEN);
                        } else if (select == 1) {
                            //về quá khứ
                            ChangeMapService.gI().goToQuaKhu(player);
                        }
                    }
                } else if (player.iDMark.isBaseMenu()) {
                    if (select == 0) {
                        //kể chuyện
                        NpcService.gI().createTutorial(player, this.avartar, ConstNpc.CALICK_KE_CHUYEN);
                    } else if (select == 1) {
                        //đến tương lai
                        changeMap();
                        if (TaskService.gI().getIdTask(player) >= ConstTask.TASK_24_3) {
                            Service.gI().sendThongBao(player, "Vui lòng hoàn thành nhiệm vụ trước khi tới đây!");
                     
                    
                    
                            ChangeMapService.gI().goToTuongLai(player);
                        }
                    } else {
                        Service.gI().sendThongBao(player, "Không thể thực hiện");
                    }
                }
            }
        };
    }

    public static Npc jaco(int mapId, int status, int cx, int cy, int tempId, int avartar) {
        return new Npc(mapId, status, cx, cy, tempId, avartar) {
            @Override
            public void openBaseMenu(Player player) {
                if (canOpenNpc(player)) {
                    if (this.mapId == 24 || this.mapId == 25 || this.mapId == 26 || this.mapId == 5) {
                        this.createOtherMenu(player, ConstNpc.BASE_MENU,
                                "Gô Tên, Calich và Monaka đang gặp chuyện ở hành tinh Potaufeu \n Hãy đến đó ngay", "Đến \nPotaufeu");
                    } else if (this.mapId == 139) {
                        this.createOtherMenu(player, ConstNpc.BASE_MENU,
                                "Người muốn trở về?", "Quay về", "Từ chối");
                    }
                }
            }

            @Override
            public void confirmMenu(Player player, int select) {
                if (canOpenNpc(player)) {
                    if (this.mapId == 24 || this.mapId == 25 || this.mapId == 26 || this.mapId == 5) {
                        if (player.iDMark.isBaseMenu()) {
                            if (select == 0) {
                                //đến potaufeu
                                ChangeMapService.gI().goToPotaufeu(player);
                            }
                        }
                    } else if (this.mapId == 139) {
                        if (player.iDMark.isBaseMenu()) {
                            switch (select) {
                                //về trạm vũ trụ
                                case 0:
                                    ChangeMapService.gI().changeMapBySpaceShip(player, 24 + player.gender, -1, -1);
                                    break;
                            }
                        }
                    }
                }
            }
        };
    }
    public static Npc unkn(int mapId, int status, int cx, int cy, int tempId, int avartar) {
        return new Npc(mapId, status, cx, cy, tempId, avartar) {

            @Override
            public void openBaseMenu(Player player) {
                if (canOpenNpc(player)) {
                    if (mapId == 0 || mapId == 5) {
                        this.createOtherMenu(player, 0, "Làng Quái Vật x100 Tnsm\nHỗ trợ cho Ae Từ\b|1| 80 Tỷ SM?", "OK", "Oéo");
                    }
                    if (mapId == 169) {
                        this.createOtherMenu(player, 0, "Bạn Muốn Quay Trở Lại Làng Ảru?", "OK", "Từ chối");

                    }
                    if (mapId == 169) {
                        this.createOtherMenu(player, 0, "Xia xia thua phùa\b|7|Thí chủ đang có: " + player.NguHanhSonPoint + " điểm ngũ hành sơn\b|1|Thí chủ muốn đổi cải trang x4 chưởng ko?", "Âu kê", "Top Ngu Hanh Son", "No");
                    }
                }
            }

            @Override
            public void confirmMenu(Player player, int select) {
                if (canOpenNpc(player)) {
                    switch (select) {
                        case 0:
                            if (mapId == 169 || mapId == 5) {
                                if (player.nPoint.power <= 80000000000L) {
                                    Service.getInstance().sendThongBao(player, "Sức mạnh bạn không phù hợp để qua map!");
                                    return;
                                }
                                ChangeMapService.gI().changeMapInYard(player, 169, -1, -1);
                            }
                            if (mapId == 169) {
                                ChangeMapService.gI().changeMapInYard(player, 0, -1, -1);
                            }
                            if (mapId == 169) {
                                if (select == 0) {
                                    if (player.NguHanhSonPoint >= 500) {
                                        player.NguHanhSonPoint -= 500;
                                        Item item = ItemService.gI().createNewItem((short) (711));
                                        item.itemOptions.add(new Item.ItemOption(50, 1));
                                        item.itemOptions.add(new Item.ItemOption(77, 1));
                                        item.itemOptions.add(new Item.ItemOption(103, 1));
                                        item.itemOptions.add(new Item.ItemOption(207, 0));
                                        item.itemOptions.add(new Item.ItemOption(33, 0));
//                                      
                                        InventoryServiceNew.gI().addItemBag(player, item);
                                        Service.getInstance().sendThongBao(player, "Chúc Mừng Bạn Đổi Vật Phẩm Thành Công !");
                                    } else {
                                        Service.getInstance().sendThongBao(player, "Không đủ điểm, bạn còn " + (500 - player.pointPvp) + " điểm nữa");
                                    }
                                } else if (select == 1) {
                                    Util.showListTop(player, (byte) 4
                                    );
                                }
                            }
                            break;
                    }
                }
            }
        };
    }

    public static Npc npclytieunuong54(int mapId, int status, int cx, int cy, int tempId, int avartar) {
        return new Npc(mapId, status, cx, cy, tempId, avartar) {
            @Override
            public void openBaseMenu(Player player) {
                createOtherMenu(player, 0, "Trò chơi Chọn ai đây đang được diễn ra, nếu bạn tin tưởng mình đang tràn đầy may mắn thì có thể tham gia thử", "Thể lệ", "Chọn\nThỏi vàng");
            }

            @Override
            public void confirmMenu(Player pl, int select) {
                if (canOpenNpc(pl)) {
                    String time = ((ChonAiDay.gI().lastTimeEnd - System.currentTimeMillis()) / 1000) + " giây";
                    if (pl.iDMark.getIndexMenu() == 0) {
                        if (select == 0) {
                            createOtherMenu(pl, ConstNpc.IGNORE_MENU, "Thời gian giữa các giải là 5 phút\nKhi hết giờ, hệ thống sẽ ngẫu nhiên chọn ra 1 người may mắn.\nLưu ý: Số thỏi vàng nhận được sẽ bị nhà cái lụm đi 5%!Trong quá trình diễn ra khi đặt cược nếu thoát game mọi phần đặt đều sẽ bị hủy", "Ok");
                        } else if (select == 1) {
                            createOtherMenu(pl, 1, "Tổng giải thường: " + ChonAiDay.gI().goldNormar + " thỏi vàng, cơ hội trúng của bạn là: " + pl.percentGold(0) + "%\nTổng giải VIP: " + ChonAiDay.gI().goldVip + " thỏi vàng, cơ hội trúng của bạn là: " + pl.percentGold(1) + "%\nSố thỏi vàng đặt thường: " + pl.goldNormar + "\nSố thỏi vàng đặt VIP: " + pl.goldVIP + "\n Thời gian còn lại: " + time, "Cập nhập", "Thường\n20 thỏi\nvàng", "VIP\n200 thỏi\nvàng", "Đóng");
                        }
                    } else if (pl.iDMark.getIndexMenu() == 1) {
                        if (((ChonAiDay.gI().lastTimeEnd - System.currentTimeMillis()) / 1000) > 0) {
                            switch (select) {
                                case 0:
                                    createOtherMenu(pl, 1, "Tổng giải thường: " + ChonAiDay.gI().goldNormar + " thỏi vàng, cơ hội trúng của bạn là: " + pl.percentGold(0) + "%\nTổng giải VIP: " + ChonAiDay.gI().goldVip + " thỏi vàng, cơ hội trúng của bạn là: " + pl.percentGold(1) + "%\nSố thỏi vàng đặt thường: " + pl.goldNormar + "\nSố thỏi vàng đặt VIP: " + pl.goldVIP + "\n Thời gian còn lại: " + time, "Cập nhập", "Thường\n20 thỏi\nvàng", "VIP\n200 thỏi\nvàng", "Đóng");
                                    break;
                                case 1: {
                                    if (InventoryServiceNew.gI().findItemBag(pl, 457).isNotNullItem() && InventoryServiceNew.gI().findItemBag(pl, 457).quantity >= 20) {
                                        InventoryServiceNew.gI().subQuantityItemsBag(pl, InventoryServiceNew.gI().findItemBag(pl, 457), 20);
                                        InventoryServiceNew.gI().sendItemBags(pl);
                                        pl.goldNormar += 20;
                                        ChonAiDay.gI().goldNormar += 20;
                                        ChonAiDay.gI().addPlayerNormar(pl);
                                        createOtherMenu(pl, 1, "Tổng giải thường: " + ChonAiDay.gI().goldNormar + " thỏi vàng, cơ hội trúng của bạn là: " + pl.percentGold(0) + "%\nTổng giải VIP: " + ChonAiDay.gI().goldVip + " thỏi vàng, cơ hội trúng của bạn là: " + pl.percentGold(1) + "%\nSố thỏi vàng đặt thường: " + pl.goldNormar + "\nSố thỏi vàng đặt VIP: " + pl.goldVIP + "\n Thời gian còn lại: " + time, "Cập nhập", "Thường\n20 thỏi\nvàng", "VIP\n200 thỏi\nvàng", "Đóng");
                                    } else {
                                        Service.getInstance().sendThongBao(pl, "Bạn không đủ thỏi vàng");
                                    }
                                }
                                break;
                                case 2: {
                                    if (InventoryServiceNew.gI().findItemBag(pl, 457).isNotNullItem() && InventoryServiceNew.gI().findItemBag(pl, 457).quantity >= 200) {
                                        InventoryServiceNew.gI().subQuantityItemsBag(pl, InventoryServiceNew.gI().findItemBag(pl, 457), 200);
                                        InventoryServiceNew.gI().sendItemBags(pl);
                                        pl.goldVIP += 200;
                                        ChonAiDay.gI().goldVip += 200;
                                        ChonAiDay.gI().addPlayerVIP(pl);
                                        createOtherMenu(pl, 1, "Tổng giải thường: " + ChonAiDay.gI().goldNormar + " thỏi vàng, cơ hội trúng của bạn là: " + pl.percentGold(0) + "%\nTổng giải VIP: " + ChonAiDay.gI().goldVip + " thỏi vàng, cơ hội trúng của bạn là: " + pl.percentGold(1) + "%\nSố thỏi vàng đặt thường: " + pl.goldNormar + "\nSố thỏi vàng đặt VIP: " + pl.goldVIP + "\n Thời gian còn lại: " + time, "Cập nhập", "Thường\n20 thỏi\nvàng", "VIP\n200 thỏi\nvàng", "Đóng");
                                    } else {
                                        Service.getInstance().sendThongBao(pl, "Bạn không đủ thỏi vàng");
                                    }
                                }
                                break;
                            }
                        }
                    }
                }
            }
        };
    }

    public static Npc thoren(int mapId, int status, int cx, int cy, int tempId, int avartar) {
        return new Npc(mapId, status, cx, cy, tempId, avartar) {
            @Override
            public void openBaseMenu(Player player) {
                if (this.mapId == 5) {
                    this.createOtherMenu(player, ConstNpc.BASE_MENU,
                            "Bạn cần đổi gì?", "Đổi đồ\nHủy Diệt\nTrái Đất", "Đổi đồ\nHuy Diệt\nNamek", "Đổi Đồ\nHủy Diệt\nxayda", "Đổi Đồ\nThiên Sứ\nTrái Đất", "Đổi Đồ\nThiên Sứ\nNamek", "Đổi Đồ\nThiên Sú\nXayda");
                }
            }

            @Override
            public void confirmMenu(Player player, int select) {
                if (canOpenNpc(player)) {
                    if (this.mapId == 5) {
                        if (player.iDMark.isBaseMenu()) {
                            switch (select) {
                                case 0:
                                    this.createOtherMenu(player, 1,
                                            "Bạn muốn đổi 1 món đồ thần linh \nTrái đất cùng loại và x30 đá ngũ sắc \n|6|Để đổi lấy 1 món đồ húy diệt có tý lệ ra SKH ko", "Áo\nHúy Diệt", "Quần\nHúy Diệt", "Găng\nDúy Diệt", "Giày\nHúy Diệt", "Nhẫn\nHúy Diệt", "Thôi Khỏi");
                                    break;
                                case 1:
                                    this.createOtherMenu(player, 2,
                                            "Bạn muốn đổi 1 món đồ thần linh \nNamek cùng loại và x30 đá ngũ sắc \n|6|Để đổi lấy 1 món đồ húy diệt có tý lệ ra SKH ko", "Áo\nHúy Diệt", "Quần\nHúy Diệt", "Găng\nDúy Diệt", "Giày\nHúy Diệt", "Nhẫn\nHúy Diệt", "Thôi Khỏi");
                                    break;
                                case 2:
                                    this.createOtherMenu(player, 3,
                                            "Bạn muốn đổi 1 món đồ thần linh \nXayda cùng loại và x30 đá ngũ sắc \n|6|Để đổi lấy 1 món đồ húy diệt có tý lệ ra SKH ko", "Áo\nHúy Diệt", "Quần\nHúy Diệt", "Găng\nDúy Diệt", "Giày\nHúy Diệt", "Nhẫn\nHúy Diệt", "Thôi Khỏi");
                                    break;
                                case 3:
                                    this.createOtherMenu(player, 4,
                                            "Bạn muốn đổi 1 món đồ húy diệt \nTrái đất cùng loại và x99 đá ngũ sắc \n|6|Để đổi lấy 1 món đồ thiên sứ có tý lệ rà SKH ko", "Áo\nThiên sứ", "Quần\nThiên sứ", "Găng\nThiên sứ", "Giày\nThiên Sứ", "Nhẫn\nThiên Sứ", "Từ Chối");
                                    break;
                                case 4:
                                    this.createOtherMenu(player, 5,
                                            "Bạn muốn đổi 1 món đồ húy diệt \nNamek cùng loại và x99 đá ngũ sắc \n|6|Để đổi lấy 1 món đồ thiên sứ có tý lệ rà SKH ko", "Áo\nThiên sứ", "Quần\nThiên sứ", "Găng\nThiên sứ", "Giày\nThiên Sứ", "Nhẫn\nThiên Sứ", "Từ Chối");
                                    break;
                                case 5:
                                    this.createOtherMenu(player, 6,
                                            "Bạn muốn đổi 1 món đồ húy diệt \nXayda cùng loại và x99 đá ngũ sắc \n|6|Để đổi lấy 1 món đồ thiên sứ có tý lệ rà SKH ko", "Áo\nThiên sứ", "Quần\nThiên sứ", "Găng\nThiên sứ", "Giày\nThiên Sứ", "Nhẫn\nThiên Sứ", "Từ Chối");
                                    break;
                            }
                        } else if (player.iDMark.getIndexMenu() == 1) { // action đổi dồ húy diệt
                            switch (select) {
                                case 0: // trade
                                try {
                                    Item dns = InventoryServiceNew.gI().findItem(player.inventory.itemsBag, 674);
                                    Item tl = InventoryServiceNew.gI().findItem(player.inventory.itemsBag, 555);
                                    int soLuong = 0;
                                    if (dns != null) {
                                        soLuong = dns.quantity;
                                    }
                                    for (int i = 0; i < 12; i++) {
                                        Item thl = InventoryServiceNew.gI().findItem(player.inventory.itemsBag, 555 + i);

                                        if (InventoryServiceNew.gI().isExistItemBag(player, 555 + i) && soLuong >= 30) {
                                            InventoryServiceNew.gI().subQuantityItemsBag(player, dns, 30);
                                            InventoryServiceNew.gI().subQuantityItemsBag(player, thl, 1);
                                            CombineServiceNew.gI().GetTrangBiKichHoathuydiet(player, 650 + i);
                                            this.npcChat(player, "Chuyển Hóa Thành Công!");

                                            break;
                                        } else {
                                            this.npcChat(player, "Yêu cầu cần Áo Thần linh trái đất + x30 Đá Ngũ Sắc!");
                                        }

                                    }
                                } catch (Exception e) {

                                }
                                break;
                                case 1: // trade
                                    try {
                                    Item dns = InventoryServiceNew.gI().findItem(player.inventory.itemsBag, 674);
                                    Item tl = InventoryServiceNew.gI().findItem(player.inventory.itemsBag, 556);
                                    int soLuong = 0;
                                    if (dns != null) {
                                        soLuong = dns.quantity;
                                    }
                                    for (int i = 0; i < 12; i++) {
                                        Item thl = InventoryServiceNew.gI().findItem(player.inventory.itemsBag, 556 + i);

                                        if (InventoryServiceNew.gI().isExistItemBag(player, 556 + i) && soLuong >= 30) {
                                            InventoryServiceNew.gI().subQuantityItemsBag(player, dns, 30);
                                            InventoryServiceNew.gI().subQuantityItemsBag(player, thl, 1);
                                            CombineServiceNew.gI().GetTrangBiKichHoathuydiet(player, 651 + i);
                                            this.npcChat(player, "Chuyển Hóa Thành Công!");

                                            break;
                                        } else {
                                            this.npcChat(player, "Yêu cầu cần Quần Thần linh trái đất + x30 Đá Ngũ Sắc!");
                                        }

                                    }
                                } catch (Exception e) {

                                }
                                break;
                                case 2: // trade
                                    try {
                                    Item dns = InventoryServiceNew.gI().findItem(player.inventory.itemsBag, 674);
                                    Item tl = InventoryServiceNew.gI().findItem(player.inventory.itemsBag, 562);
                                    int soLuong = 0;
                                    if (dns != null) {
                                        soLuong = dns.quantity;
                                    }
                                    for (int i = 0; i < 12; i++) {
                                        Item thl = InventoryServiceNew.gI().findItem(player.inventory.itemsBag, 562 + i);

                                        if (InventoryServiceNew.gI().isExistItemBag(player, 562 + i) && soLuong >= 30) {
                                            InventoryServiceNew.gI().subQuantityItemsBag(player, dns, 30);
                                            InventoryServiceNew.gI().subQuantityItemsBag(player, thl, 1);
                                            CombineServiceNew.gI().GetTrangBiKichHoathuydiet(player, 657 + i);
                                            this.npcChat(player, "Chuyển Hóa Thành Công!");

                                            break;
                                        } else {
                                            this.npcChat(player, "Yêu cầu cần Găng Thần linh trái đất + x30 Đá Ngũ Sắc!");
                                        }

                                    }
                                } catch (Exception e) {

                                }
                                break;
                                case 3: // trade
                                    try {
                                    Item dns = InventoryServiceNew.gI().findItem(player.inventory.itemsBag, 674);
                                    Item tl = InventoryServiceNew.gI().findItem(player.inventory.itemsBag, 563);
                                    int soLuong = 0;
                                    if (dns != null) {
                                        soLuong = dns.quantity;
                                    }
                                    for (int i = 0; i < 12; i++) {
                                        Item thl = InventoryServiceNew.gI().findItem(player.inventory.itemsBag, 563 + i);

                                        if (InventoryServiceNew.gI().isExistItemBag(player, 563 + i) && soLuong >= 30) {
                                            InventoryServiceNew.gI().subQuantityItemsBag(player, dns, 30);
                                            InventoryServiceNew.gI().subQuantityItemsBag(player, thl, 1);
                                            CombineServiceNew.gI().GetTrangBiKichHoathuydiet(player, 658 + i);
                                            this.npcChat(player, "Chuyển Hóa Thành Công!");

                                            break;
                                        } else {
                                            this.npcChat(player, "Yêu cầu cần Giày Thần linh trái đất + x30 Đá Ngũ Sắc!");
                                        }

                                    }
                                } catch (Exception e) {

                                }
                                break;
                                case 4: // trade
                                    try {
                                    Item dns = InventoryServiceNew.gI().findItem(player.inventory.itemsBag, 674);
                                    Item tl = InventoryServiceNew.gI().findItem(player.inventory.itemsBag, 561);
                                    int soLuong = 0;
                                    if (dns != null) {
                                        soLuong = dns.quantity;
                                    }
                                    for (int i = 0; i < 12; i++) {
                                        Item thl = InventoryServiceNew.gI().findItem(player.inventory.itemsBag, 561 + i);

                                        if (InventoryServiceNew.gI().isExistItemBag(player, 561 + i) && soLuong >= 30) {
                                            InventoryServiceNew.gI().subQuantityItemsBag(player, dns, 30);
                                            InventoryServiceNew.gI().subQuantityItemsBag(player, thl, 1);
                                            CombineServiceNew.gI().GetTrangBiKichHoathuydiet(player, 656 + i);
                                            this.npcChat(player, "Chuyển Hóa Thành Công!");
                                            break;
                                        } else {
                                            this.npcChat(player, "Yêu cầu cần Nhận Thần linh trái đất + x30 Đá Ngũ Sắc!");
                                        }
                                    }
                                } catch (Exception e) {

                                }
                                break;
                                case 5: // canel
                                    break;
                            }
                        } else if (player.iDMark.getIndexMenu() == 2) { // action đổi dồ húy diệt
                            switch (select) {
                                case 0: // trade
                                try {
                                    Item dns = InventoryServiceNew.gI().findItem(player.inventory.itemsBag, 674);
                                    Item tl = InventoryServiceNew.gI().findItem(player.inventory.itemsBag, 557);
                                    int soLuong = 0;
                                    if (dns != null) {
                                        soLuong = dns.quantity;
                                    }
                                    for (int i = 0; i < 12; i++) {
                                        Item thl = InventoryServiceNew.gI().findItem(player.inventory.itemsBag, 557 + i);

                                        if (InventoryServiceNew.gI().isExistItemBag(player, 557 + i) && soLuong >= 30) {
                                            InventoryServiceNew.gI().subQuantityItemsBag(player, dns, 30);
                                            InventoryServiceNew.gI().subQuantityItemsBag(player, thl, 1);
                                            CombineServiceNew.gI().GetTrangBiKichHoathuydiet(player, 650 + i);
                                            this.npcChat(player, "Chuyển Hóa Thành Công!");

                                            break;
                                        } else {
                                            this.npcChat(player, "Yêu cầu cần Áo Thần linh namec + x30 Đá Ngũ Sắc!");
                                        }

                                    }
                                } catch (Exception e) {

                                }
                                break;
                                case 1: // trade
                                    try {
                                    Item dns = InventoryServiceNew.gI().findItem(player.inventory.itemsBag, 674);
                                    Item tl = InventoryServiceNew.gI().findItem(player.inventory.itemsBag, 558);
                                    int soLuong = 0;
                                    if (dns != null) {
                                        soLuong = dns.quantity;
                                    }
                                    for (int i = 0; i < 12; i++) {
                                        Item thl = InventoryServiceNew.gI().findItem(player.inventory.itemsBag, 558 + i);

                                        if (InventoryServiceNew.gI().isExistItemBag(player, 558 + i) && soLuong >= 30) {
                                            InventoryServiceNew.gI().subQuantityItemsBag(player, dns, 30);
                                            InventoryServiceNew.gI().subQuantityItemsBag(player, thl, 1);
                                            CombineServiceNew.gI().GetTrangBiKichHoathuydiet(player, 651 + i);
                                            this.npcChat(player, "Chuyển Hóa Thành Công!");

                                            break;
                                        } else {
                                            this.npcChat(player, "Yêu cầu cần Quần Thần linh namec + x30 Đá Ngũ Sắc!");
                                        }

                                    }
                                } catch (Exception e) {

                                }
                                break;
                                case 2: // trade
                                    try {
                                    Item dns = InventoryServiceNew.gI().findItem(player.inventory.itemsBag, 674);
                                    Item tl = InventoryServiceNew.gI().findItem(player.inventory.itemsBag, 564);
                                    int soLuong = 0;
                                    if (dns != null) {
                                        soLuong = dns.quantity;
                                    }
                                    for (int i = 0; i < 12; i++) {
                                        Item thl = InventoryServiceNew.gI().findItem(player.inventory.itemsBag, 564 + i);

                                        if (InventoryServiceNew.gI().isExistItemBag(player, 564 + i) && soLuong >= 30) {
                                            InventoryServiceNew.gI().subQuantityItemsBag(player, dns, 30);
                                            InventoryServiceNew.gI().subQuantityItemsBag(player, thl, 1);
                                            CombineServiceNew.gI().GetTrangBiKichHoathuydiet(player, 657 + i);
                                            this.npcChat(player, "Chuyển Hóa Thành Công!");

                                            break;
                                        } else {
                                            this.npcChat(player, "Yêu cầu cần Găng Thần linh namec + x30 Đá Ngũ Sắc!");
                                        }

                                    }
                                } catch (Exception e) {

                                }
                                break;
                                case 3: // trade
                                    try {
                                    Item dns = InventoryServiceNew.gI().findItem(player.inventory.itemsBag, 674);
                                    Item tl = InventoryServiceNew.gI().findItem(player.inventory.itemsBag, 565);
                                    int soLuong = 0;
                                    if (dns != null) {
                                        soLuong = dns.quantity;
                                    }
                                    for (int i = 0; i < 12; i++) {
                                        Item thl = InventoryServiceNew.gI().findItem(player.inventory.itemsBag, 565 + i);

                                        if (InventoryServiceNew.gI().isExistItemBag(player, 565 + i) && soLuong >= 30) {
                                            InventoryServiceNew.gI().subQuantityItemsBag(player, dns, 30);
                                            InventoryServiceNew.gI().subQuantityItemsBag(player, thl, 1);
                                            CombineServiceNew.gI().GetTrangBiKichHoathuydiet(player, 658 + i);
                                            this.npcChat(player, "Chuyển Hóa Thành Công!");

                                            break;
                                        } else {
                                            this.npcChat(player, "Yêu cầu cần Giày Thần linh namec + x30 Đá Ngũ Sắc!");
                                        }

                                    }
                                } catch (Exception e) {

                                }
                                break;
                                case 4: // trade
                                    try {
                                    Item dns = InventoryServiceNew.gI().findItem(player.inventory.itemsBag, 674);
                                    Item tl = InventoryServiceNew.gI().findItem(player.inventory.itemsBag, 561);
                                    int soLuong = 0;
                                    if (dns != null) {
                                        soLuong = dns.quantity;
                                    }
                                    for (int i = 0; i < 12; i++) {
                                        Item thl = InventoryServiceNew.gI().findItem(player.inventory.itemsBag, 561 + i);

                                        if (InventoryServiceNew.gI().isExistItemBag(player, 561 + i) && soLuong >= 30) {
                                            InventoryServiceNew.gI().subQuantityItemsBag(player, dns, 30);
                                            InventoryServiceNew.gI().subQuantityItemsBag(player, thl, 1);
                                            CombineServiceNew.gI().GetTrangBiKichHoathuydiet(player, 656 + i);
                                            this.npcChat(player, "Chuyển Hóa Thành Công!");
                                            break;
                                        } else {
                                            this.npcChat(player, "Yêu cầu cần Nhận Thần linh namec + x30 Đá Ngũ Sắc!");
                                        }
                                    }
                                } catch (Exception e) {

                                }
                                break;
                                case 5: // canel
                                    break;
                            }
                        } else if (player.iDMark.getIndexMenu() == 3) { // action đổi dồ húy diệt
                            switch (select) {
                                case 0: // trade
                                try {
                                    Item dns = InventoryServiceNew.gI().findItem(player.inventory.itemsBag, 674);
                                    Item tl = InventoryServiceNew.gI().findItem(player.inventory.itemsBag, 559);
                                    int soLuong = 0;
                                    if (dns != null) {
                                        soLuong = dns.quantity;
                                    }
                                    for (int i = 0; i < 12; i++) {
                                        Item thl = InventoryServiceNew.gI().findItem(player.inventory.itemsBag, 559 + i);

                                        if (InventoryServiceNew.gI().isExistItemBag(player, 559 + i) && soLuong >= 30) {
                                            InventoryServiceNew.gI().subQuantityItemsBag(player, dns, 30);
                                            InventoryServiceNew.gI().subQuantityItemsBag(player, thl, 1);
                                            CombineServiceNew.gI().GetTrangBiKichHoathuydiet(player, 650 + i);
                                            this.npcChat(player, "Chuyển Hóa Thành Công!");

                                            break;
                                        } else {
                                            this.npcChat(player, "Yêu cầu cần Áo Thần linh xayda + x30 Đá Ngũ Sắc!");
                                        }

                                    }
                                } catch (Exception e) {

                                }
                                break;
                                case 1: // trade
                                    try {
                                    Item dns = InventoryServiceNew.gI().findItem(player.inventory.itemsBag, 674);
                                    Item tl = InventoryServiceNew.gI().findItem(player.inventory.itemsBag, 560);
                                    int soLuong = 0;
                                    if (dns != null) {
                                        soLuong = dns.quantity;
                                    }
                                    for (int i = 0; i < 12; i++) {
                                        Item thl = InventoryServiceNew.gI().findItem(player.inventory.itemsBag, 560 + i);

                                        if (InventoryServiceNew.gI().isExistItemBag(player, 560 + i) && soLuong >= 30) {
                                            InventoryServiceNew.gI().subQuantityItemsBag(player, dns, 30);
                                            InventoryServiceNew.gI().subQuantityItemsBag(player, thl, 1);
                                            CombineServiceNew.gI().GetTrangBiKichHoathuydiet(player, 651 + i);
                                            this.npcChat(player, "Chuyển Hóa Thành Công!");

                                            break;
                                        } else {
                                            this.npcChat(player, "Yêu cầu cần Quần Thần linh xayda + x30 Đá Ngũ Sắc!");
                                        }

                                    }
                                } catch (Exception e) {

                                }
                                break;
                                case 2: // trade
                                    try {
                                    Item dns = InventoryServiceNew.gI().findItem(player.inventory.itemsBag, 674);
                                    Item tl = InventoryServiceNew.gI().findItem(player.inventory.itemsBag, 566);
                                    int soLuong = 0;
                                    if (dns != null) {
                                        soLuong = dns.quantity;
                                    }
                                    for (int i = 0; i < 12; i++) {
                                        Item thl = InventoryServiceNew.gI().findItem(player.inventory.itemsBag, 566 + i);

                                        if (InventoryServiceNew.gI().isExistItemBag(player, 566 + i) && soLuong >= 30) {
                                            InventoryServiceNew.gI().subQuantityItemsBag(player, dns, 30);
                                            InventoryServiceNew.gI().subQuantityItemsBag(player, thl, 1);
                                            CombineServiceNew.gI().GetTrangBiKichHoathuydiet(player, 657 + i);
                                            this.npcChat(player, "Chuyển Hóa Thành Công!");

                                            break;
                                        } else {
                                            this.npcChat(player, "Yêu cầu cần Găng Thần linh xayda + x30 Đá Ngũ Sắc!");
                                        }

                                    }
                                } catch (Exception e) {

                                }
                                break;
                                case 3: // trade
                                    try {
                                    Item dns = InventoryServiceNew.gI().findItem(player.inventory.itemsBag, 674);
                                    Item tl = InventoryServiceNew.gI().findItem(player.inventory.itemsBag, 567);
                                    int soLuong = 0;
                                    if (dns != null) {
                                        soLuong = dns.quantity;
                                    }
                                    for (int i = 0; i < 12; i++) {
                                        Item thl = InventoryServiceNew.gI().findItem(player.inventory.itemsBag, 567 + i);

                                        if (InventoryServiceNew.gI().isExistItemBag(player, 567 + i) && soLuong >= 30) {
                                            InventoryServiceNew.gI().subQuantityItemsBag(player, dns, 30);
                                            InventoryServiceNew.gI().subQuantityItemsBag(player, thl, 1);
                                            CombineServiceNew.gI().GetTrangBiKichHoathuydiet(player, 658 + i);
                                            this.npcChat(player, "Chuyển Hóa Thành Công!");

                                            break;
                                        } else {
                                            this.npcChat(player, "Yêu cầu cần Giày Thần linh xayda + x30 Đá Ngũ Sắc!");
                                        }

                                    }
                                } catch (Exception e) {

                                }
                                break;
                                case 4: // trade
                                    try {
                                    Item dns = InventoryServiceNew.gI().findItem(player.inventory.itemsBag, 674);
                                    Item tl = InventoryServiceNew.gI().findItem(player.inventory.itemsBag, 561);
                                    int soLuong = 0;
                                    if (dns != null) {
                                        soLuong = dns.quantity;
                                    }
                                    for (int i = 0; i < 12; i++) {
                                        Item thl = InventoryServiceNew.gI().findItem(player.inventory.itemsBag, 561 + i);

                                        if (InventoryServiceNew.gI().isExistItemBag(player, 561 + i) && soLuong >= 30) {
                                            InventoryServiceNew.gI().subQuantityItemsBag(player, dns, 30);
                                            InventoryServiceNew.gI().subQuantityItemsBag(player, thl, 1);
                                            CombineServiceNew.gI().GetTrangBiKichHoathuydiet(player, 656 + i);
                                            this.npcChat(player, "Chuyển Hóa Thành Công!");
                                            break;
                                        } else {
                                            this.npcChat(player, "Yêu cầu cần Nhận Thần linh xayde + x30 Đá Ngũ Sắc!");
                                        }
                                    }
                                } catch (Exception e) {

                                }
                                break;
                                case 5: // canel
                                    break;
                            }
                        } else if (player.iDMark.getIndexMenu() == 4) { // action đổi dồ thiên sứ
                            switch (select) {
                                case 0: // trade
                                try {
                                    Item dns = InventoryServiceNew.gI().findItem(player.inventory.itemsBag, 674);
                                    Item tl = InventoryServiceNew.gI().findItem(player.inventory.itemsBag, 650);
                                    int soLuong = 0;
                                    if (dns != null) {
                                        soLuong = dns.quantity;
                                    }
                                    for (int i = 0; i < 12; i++) {
                                        Item thl = InventoryServiceNew.gI().findItem(player.inventory.itemsBag, 650 + i);

                                        if (InventoryServiceNew.gI().isExistItemBag(player, 650 + i) && soLuong >= 99) {
                                            InventoryServiceNew.gI().subQuantityItemsBag(player, dns, 99);
                                            InventoryServiceNew.gI().subQuantityItemsBag(player, thl, 1);
                                            CombineServiceNew.gI().GetTrangBiKichHoatthiensu(player, 1048 + i);
                                            this.npcChat(player, "Chuyển Hóa Thành Công!");

                                            break;
                                        } else {
                                            this.npcChat(player, "Yêu cầu cần Áo húy diệt trái đất + x99 Đá Ngũ Sắc!");
                                        }

                                    }
                                } catch (Exception e) {

                                }
                                break;
                                case 1: // trade
                                    try {
                                    Item dns = InventoryServiceNew.gI().findItem(player.inventory.itemsBag, 674);
                                    Item tl = InventoryServiceNew.gI().findItem(player.inventory.itemsBag, 651);
                                    int soLuong = 0;
                                    if (dns != null) {
                                        soLuong = dns.quantity;
                                    }
                                    for (int i = 0; i < 12; i++) {
                                        Item thl = InventoryServiceNew.gI().findItem(player.inventory.itemsBag, 651 + i);

                                        if (InventoryServiceNew.gI().isExistItemBag(player, 651 + i) && soLuong >= 99) {
                                            InventoryServiceNew.gI().subQuantityItemsBag(player, dns, 99);
                                            InventoryServiceNew.gI().subQuantityItemsBag(player, thl, 1);
                                            CombineServiceNew.gI().GetTrangBiKichHoatthiensu(player, 1051 + i);
                                            this.npcChat(player, "Chuyển Hóa Thành Công!");

                                            break;
                                        } else {
                                            this.npcChat(player, "Yêu cầu cần Quần húy diệt trái đất + x99 Đá Ngũ Sắc!");
                                        }

                                    }
                                } catch (Exception e) {

                                }
                                break;
                                case 2: // trade
                                    try {
                                    Item dns = InventoryServiceNew.gI().findItem(player.inventory.itemsBag, 674);
                                    Item tl = InventoryServiceNew.gI().findItem(player.inventory.itemsBag, 657);
                                    int soLuong = 0;
                                    if (dns != null) {
                                        soLuong = dns.quantity;
                                    }
                                    for (int i = 0; i < 12; i++) {
                                        Item thl = InventoryServiceNew.gI().findItem(player.inventory.itemsBag, 657 + i);

                                        if (InventoryServiceNew.gI().isExistItemBag(player, 657 + i) && soLuong >= 99) {
                                            InventoryServiceNew.gI().subQuantityItemsBag(player, dns, 99);
                                            InventoryServiceNew.gI().subQuantityItemsBag(player, thl, 1);
                                            CombineServiceNew.gI().GetTrangBiKichHoatthiensu(player, 1054 + i);
                                            this.npcChat(player, "Chuyển Hóa Thành Công!");

                                            break;
                                        } else {
                                            this.npcChat(player, "Yêu cầu cần Găng húy diệt trái đất + x99 Đá Ngũ Sắc!");
                                        }

                                    }
                                } catch (Exception e) {

                                }
                                break;
                                case 3: // trade
                                    try {
                                    Item dns = InventoryServiceNew.gI().findItem(player.inventory.itemsBag, 674);
                                    Item tl = InventoryServiceNew.gI().findItem(player.inventory.itemsBag, 658);
                                    int soLuong = 0;
                                    if (dns != null) {
                                        soLuong = dns.quantity;
                                    }
                                    for (int i = 0; i < 12; i++) {
                                        Item thl = InventoryServiceNew.gI().findItem(player.inventory.itemsBag, 658 + i);

                                        if (InventoryServiceNew.gI().isExistItemBag(player, 658 + i) && soLuong >= 99) {
                                            InventoryServiceNew.gI().subQuantityItemsBag(player, dns, 99);
                                            InventoryServiceNew.gI().subQuantityItemsBag(player, thl, 1);
                                            CombineServiceNew.gI().GetTrangBiKichHoatthiensu(player, 1057 + i);
                                            this.npcChat(player, "Chuyển Hóa Thành Công!");

                                            break;
                                        } else {
                                            this.npcChat(player, "Yêu cầu cần Giày húy diệt trái đất + x99 Đá Ngũ Sắc!");
                                        }

                                    }
                                } catch (Exception e) {

                                }
                                break;
                                case 4: // trade
                                    try {
                                    Item dns = InventoryServiceNew.gI().findItem(player.inventory.itemsBag, 674);
                                    Item tl = InventoryServiceNew.gI().findItem(player.inventory.itemsBag, 656);
                                    int soLuong = 0;
                                    if (dns != null) {
                                        soLuong = dns.quantity;
                                    }
                                    for (int i = 0; i < 12; i++) {
                                        Item thl = InventoryServiceNew.gI().findItem(player.inventory.itemsBag, 656 + i);

                                        if (InventoryServiceNew.gI().isExistItemBag(player, 656 + i) && soLuong >= 99) {
                                            InventoryServiceNew.gI().subQuantityItemsBag(player, dns, 99);
                                            InventoryServiceNew.gI().subQuantityItemsBag(player, thl, 1);
                                            CombineServiceNew.gI().GetTrangBiKichHoatthiensu(player, 1060 + i);
                                            this.npcChat(player, "Chuyển Hóa Thành Công!");

                                            break;
                                        } else {
                                            this.npcChat(player, "Yêu cầu cần nhận húy diệt trái đất + x99 Đá Ngũ Sắc!");
                                        }

                                    }
                                } catch (Exception e) {

                                }
                                break;
                                case 5: // canel
                                    break;
                            }
                        } else if (player.iDMark.getIndexMenu() == 5) { // action đổi dồ thiên sứ
                            switch (select) {
                                case 0: // trade
                                try {
                                    Item dns = InventoryServiceNew.gI().findItem(player.inventory.itemsBag, 674);
                                    Item tl = InventoryServiceNew.gI().findItem(player.inventory.itemsBag, 652);
                                    int soLuong = 0;
                                    if (dns != null) {
                                        soLuong = dns.quantity;
                                    }
                                    for (int i = 0; i < 12; i++) {
                                        Item thl = InventoryServiceNew.gI().findItem(player.inventory.itemsBag, 652 + i);

                                        if (InventoryServiceNew.gI().isExistItemBag(player, 652 + i) && soLuong >= 99) {
                                            InventoryServiceNew.gI().subQuantityItemsBag(player, dns, 99);
                                            InventoryServiceNew.gI().subQuantityItemsBag(player, thl, 1);
                                            CombineServiceNew.gI().GetTrangBiKichHoatthiensu(player, 1049 + i);
                                            this.npcChat(player, "Chuyển Hóa Thành Công!");

                                            break;
                                        } else {
                                            this.npcChat(player, "Yêu cầu cần Áo húy diệt namec + x99 Đá Ngũ Sắc!");
                                        }

                                    }
                                } catch (Exception e) {

                                }
                                break;
                                case 1: // trade
                                    try {
                                    Item dns = InventoryServiceNew.gI().findItem(player.inventory.itemsBag, 674);
                                    Item tl = InventoryServiceNew.gI().findItem(player.inventory.itemsBag, 653);
                                    int soLuong = 0;
                                    if (dns != null) {
                                        soLuong = dns.quantity;
                                    }
                                    for (int i = 0; i < 12; i++) {
                                        Item thl = InventoryServiceNew.gI().findItem(player.inventory.itemsBag, 653 + i);

                                        if (InventoryServiceNew.gI().isExistItemBag(player, 653 + i) && soLuong >= 99) {
                                            InventoryServiceNew.gI().subQuantityItemsBag(player, dns, 99);
                                            InventoryServiceNew.gI().subQuantityItemsBag(player, thl, 1);
                                            CombineServiceNew.gI().GetTrangBiKichHoatthiensu(player, 1052 + i);
                                            this.npcChat(player, "Chuyển Hóa Thành Công!");

                                            break;
                                        } else {
                                            this.npcChat(player, "Yêu cầu cần Quần húy diệt namec + x99 Đá Ngũ Sắc!");
                                        }

                                    }
                                } catch (Exception e) {

                                }
                                break;
                                case 2: // trade
                                    try {
                                    Item dns = InventoryServiceNew.gI().findItem(player.inventory.itemsBag, 674);
                                    Item tl = InventoryServiceNew.gI().findItem(player.inventory.itemsBag, 659);
                                    int soLuong = 0;
                                    if (dns != null) {
                                        soLuong = dns.quantity;
                                    }
                                    for (int i = 0; i < 12; i++) {
                                        Item thl = InventoryServiceNew.gI().findItem(player.inventory.itemsBag, 659 + i);

                                        if (InventoryServiceNew.gI().isExistItemBag(player, 659 + i) && soLuong >= 99) {
                                            InventoryServiceNew.gI().subQuantityItemsBag(player, dns, 99);
                                            InventoryServiceNew.gI().subQuantityItemsBag(player, thl, 1);
                                            CombineServiceNew.gI().GetTrangBiKichHoatthiensu(player, 1055 + i);
                                            this.npcChat(player, "Chuyển Hóa Thành Công!");

                                            break;
                                        } else {
                                            this.npcChat(player, "Yêu cầu cần Găng húy diệt namec + x99 Đá Ngũ Sắc!");
                                        }

                                    }
                                } catch (Exception e) {

                                }
                                break;
                                case 3: // trade
                                    try {
                                    Item dns = InventoryServiceNew.gI().findItem(player.inventory.itemsBag, 674);
                                    Item tl = InventoryServiceNew.gI().findItem(player.inventory.itemsBag, 660);
                                    int soLuong = 0;
                                    if (dns != null) {
                                        soLuong = dns.quantity;
                                    }
                                    for (int i = 0; i < 12; i++) {
                                        Item thl = InventoryServiceNew.gI().findItem(player.inventory.itemsBag, 660 + i);

                                        if (InventoryServiceNew.gI().isExistItemBag(player, 660 + i) && soLuong >= 99) {
                                            InventoryServiceNew.gI().subQuantityItemsBag(player, dns, 99);
                                            InventoryServiceNew.gI().subQuantityItemsBag(player, thl, 1);
                                            CombineServiceNew.gI().GetTrangBiKichHoatthiensu(player, 1058 + i);
                                            this.npcChat(player, "Chuyển Hóa Thành Công!");

                                            break;
                                        } else {
                                            this.npcChat(player, "Yêu cầu cần Giày húy diệt namec + x99 Đá Ngũ Sắc!");
                                        }

                                    }
                                } catch (Exception e) {

                                }
                                break;
                                case 4: // trade
                                    try {
                                    Item dns = InventoryServiceNew.gI().findItem(player.inventory.itemsBag, 674);
                                    Item tl = InventoryServiceNew.gI().findItem(player.inventory.itemsBag, 656);
                                    int soLuong = 0;
                                    if (dns != null) {
                                        soLuong = dns.quantity;
                                    }
                                    for (int i = 0; i < 12; i++) {
                                        Item thl = InventoryServiceNew.gI().findItem(player.inventory.itemsBag, 656 + i);

                                        if (InventoryServiceNew.gI().isExistItemBag(player, 656 + i) && soLuong >= 99) {
                                            InventoryServiceNew.gI().subQuantityItemsBag(player, dns, 99);
                                            InventoryServiceNew.gI().subQuantityItemsBag(player, thl, 1);
                                            CombineServiceNew.gI().GetTrangBiKichHoatthiensu(player, 1061 + i);
                                            this.npcChat(player, "Chuyển Hóa Thành Công!");

                                            break;
                                        } else {
                                            this.npcChat(player, "Yêu cầu cần nhận húy diệt namec + x99 Đá Ngũ Sắc!");
                                        }

                                    }
                                } catch (Exception e) {

                                }
                                break;
                                case 5: // canel
                                    break;
                            }
                        } else if (player.iDMark.getIndexMenu() == 6) { // action đổi dồ thiên sứ
                            switch (select) {
                                case 0: // trade
                                try {
                                    Item dns = InventoryServiceNew.gI().findItem(player.inventory.itemsBag, 674);
                                    Item tl = InventoryServiceNew.gI().findItem(player.inventory.itemsBag, 654);
                                    int soLuong = 0;
                                    if (dns != null) {
                                        soLuong = dns.quantity;
                                    }
                                    for (int i = 0; i < 12; i++) {
                                        Item thl = InventoryServiceNew.gI().findItem(player.inventory.itemsBag, 654 + i);

                                        if (InventoryServiceNew.gI().isExistItemBag(player, 654 + i) && soLuong >= 99) {
                                            InventoryServiceNew.gI().subQuantityItemsBag(player, dns, 99);
                                            InventoryServiceNew.gI().subQuantityItemsBag(player, thl, 1);
                                            CombineServiceNew.gI().GetTrangBiKichHoatthiensu(player, 1050 + i);
                                            this.npcChat(player, "Chuyển Hóa Thành Công!");

                                            break;
                                        } else {
                                            this.npcChat(player, "Yêu cầu cần Áo húy diệt xayda + x99 Đá Ngũ Sắc!");
                                        }

                                    }
                                } catch (Exception e) {

                                }
                                break;
                                case 1: // trade
                                    try {
                                    Item dns = InventoryServiceNew.gI().findItem(player.inventory.itemsBag, 674);
                                    Item tl = InventoryServiceNew.gI().findItem(player.inventory.itemsBag, 655);
                                    int soLuong = 0;
                                    if (dns != null) {
                                        soLuong = dns.quantity;
                                    }
                                    for (int i = 0; i < 12; i++) {
                                        Item thl = InventoryServiceNew.gI().findItem(player.inventory.itemsBag, 655 + i);

                                        if (InventoryServiceNew.gI().isExistItemBag(player, 655 + i) && soLuong >= 99) {
                                            InventoryServiceNew.gI().subQuantityItemsBag(player, dns, 99);
                                            InventoryServiceNew.gI().subQuantityItemsBag(player, thl, 1);
                                            CombineServiceNew.gI().GetTrangBiKichHoatthiensu(player, 1053 + i);
                                            this.npcChat(player, "Chuyển Hóa Thành Công!");

                                            break;
                                        } else {
                                            this.npcChat(player, "Yêu cầu cần Quần húy diệt xayda + x99 Đá Ngũ Sắc!");
                                        }

                                    }
                                } catch (Exception e) {

                                }
                                break;
                                case 2: // trade
                                    try {
                                    Item dns = InventoryServiceNew.gI().findItem(player.inventory.itemsBag, 674);
                                    Item tl = InventoryServiceNew.gI().findItem(player.inventory.itemsBag, 661);
                                    int soLuong = 0;
                                    if (dns != null) {
                                        soLuong = dns.quantity;
                                    }
                                    for (int i = 0; i < 12; i++) {
                                        Item thl = InventoryServiceNew.gI().findItem(player.inventory.itemsBag, 661 + i);

                                        if (InventoryServiceNew.gI().isExistItemBag(player, 661 + i) && soLuong >= 99) {
                                            InventoryServiceNew.gI().subQuantityItemsBag(player, dns, 99);
                                            InventoryServiceNew.gI().subQuantityItemsBag(player, thl, 1);
                                            CombineServiceNew.gI().GetTrangBiKichHoatthiensu(player, 1056 + i);
                                            this.npcChat(player, "Chuyển Hóa Thành Công!");

                                            break;
                                        } else {
                                            this.npcChat(player, "Yêu cầu cần Găng húy diệt xayda + x99 Đá Ngũ Sắc!");
                                        }

                                    }
                                } catch (Exception e) {

                                }
                                break;
                                case 3: // trade
                                    try {
                                    Item dns = InventoryServiceNew.gI().findItem(player.inventory.itemsBag, 674);
                                    Item tl = InventoryServiceNew.gI().findItem(player.inventory.itemsBag, 662);
                                    int soLuong = 0;
                                    if (dns != null) {
                                        soLuong = dns.quantity;
                                    }
                                    for (int i = 0; i < 12; i++) {
                                        Item thl = InventoryServiceNew.gI().findItem(player.inventory.itemsBag, 662 + i);

                                        if (InventoryServiceNew.gI().isExistItemBag(player, 662 + i) && soLuong >= 99) {
                                            InventoryServiceNew.gI().subQuantityItemsBag(player, dns, 99);
                                            InventoryServiceNew.gI().subQuantityItemsBag(player, thl, 1);
                                            CombineServiceNew.gI().GetTrangBiKichHoatthiensu(player, 1059 + i);
                                            this.npcChat(player, "Chuyển Hóa Thành Công!");

                                            break;
                                        } else {
                                            this.npcChat(player, "Yêu cầu cần Giày húy diệt xayda + x99 Đá Ngũ Sắc!");
                                        }

                                    }
                                } catch (Exception e) {

                                }
                                break;
                                case 4: // trade
                                    try {
                                    Item dns = InventoryServiceNew.gI().findItem(player.inventory.itemsBag, 674);
                                    Item tl = InventoryServiceNew.gI().findItem(player.inventory.itemsBag, 656);
                                    int soLuong = 0;
                                    if (dns != null) {
                                        soLuong = dns.quantity;
                                    }
                                    for (int i = 0; i < 12; i++) {
                                        Item thl = InventoryServiceNew.gI().findItem(player.inventory.itemsBag, 656 + i);

                                        if (InventoryServiceNew.gI().isExistItemBag(player, 656 + i) && soLuong >= 99) {
                                            InventoryServiceNew.gI().subQuantityItemsBag(player, dns, 99);
                                            InventoryServiceNew.gI().subQuantityItemsBag(player, thl, 1);
                                            CombineServiceNew.gI().GetTrangBiKichHoatthiensu(player, 1062 + i);
                                            this.npcChat(player, "Chuyển Hóa Thành Công!");

                                            break;
                                        } else {
                                            this.npcChat(player, "Yêu cầu cần nhận húy diệt xayda + x99 Đá Ngũ Sắc!");
                                        }

                                    }
                                } catch (Exception e) {

                                }
                                break;
                                case 5: // canel
                                    break;
                            }
                        }
                    }
                }
            }
        };
    }

    public static Npc thuongDe(int mapId, int status, int cx, int cy, int tempId, int avartar) {
        return new Npc(mapId, status, cx, cy, tempId, avartar) {

            @Override
            public void openBaseMenu(Player player) {
                if (canOpenNpc(player)) {
                    if (this.mapId == 45) {
                        this.createOtherMenu(player, ConstNpc.BASE_MENU,
                                "Con muốn làm gì nào", "Đến Kaio");// "Quay số\nmay mắn");
                    }
                    if (this.mapId == 141) {
                        this.createOtherMenu(player, ConstNpc.BASE_MENU, "ở đây rất nguy hiểm con có muốn đi tiếp khon??",
                                  "Đồng ý", "Từ chối");
                    }
                    if (this.mapId == 129) {
                        this.createOtherMenu(player, 0,
                                "Con muốn gì nào?", "Quay ve");
                    }
                }
            }
            @Override
            public void confirmMenu(Player player, int select) {
                if (canOpenNpc(player)) {
                    if (this.mapId == 141) { 
                       switch (select) {
                            case 0: // quay ve
                                ChangeMapService.gI().changeMapBySpaceShip(player, 144, -1, 354);
                                break;
                            }
                        }
                    }
                    if (this.mapId == 129) {
                        switch (select) {
                            case 0: // quay ve
                                ChangeMapService.gI().changeMapBySpaceShip(player, 0, -1, 354);
                                break;
                        }
                    }
                    if (this.mapId == 45) {
                        if (player.iDMark.isBaseMenu()) {
                            switch (select) {
                                case 0:
                                    ChangeMapService.gI().changeMapBySpaceShip(player, 48, -1, 354);
                                    break;
                                case 1:
                                    this.createOtherMenu(player, ConstNpc.MENU_CHOOSE_LUCKY_ROUND,
                                            "Con muốn làm gì nào?", "Quay bằng\nvàng",
                                            "Rương phụ\n("
                                            + (player.inventory.itemsBoxCrackBall.size()
                                            - InventoryServiceNew.gI().getCountEmptyListItem(player.inventory.itemsBoxCrackBall))
                                            + " món)",
                                            "Xóa hết\ntrong rương", "Đóng");
                                    break;
                            }
                        } else if (player.iDMark.getIndexMenu() == ConstNpc.MENU_CHOOSE_LUCKY_ROUND) {
                            switch (select) {
                                case 0:
                                    LuckyRound.gI().openCrackBallUI(player, LuckyRound.USING_GOLD);
                                    break;
                                case 1:
                                    ShopServiceNew.gI().opendShop(player, "ITEMS_LUCKY_ROUND", true);
                                    break;
                                case 2:
                                    NpcService.gI().createMenuConMeo(player,
                                            ConstNpc.CONFIRM_REMOVE_ALL_ITEM_LUCKY_ROUND, this.avartar,
                                            "Con có chắc muốn xóa hết vật phẩm trong rương phụ? Sau khi xóa "
                                            + "sẽ không thể khôi phục!",
                                            "Đồng ý", "Hủy bỏ");
                                    break;
                            }
                        }
                    }

                }           
        };
    }
    
     public static Npc robotsiucap(int mapId, int status, int cx, int cy, int tempId, int avartar) {
        return new Npc(mapId, status, cx, cy, tempId, avartar) {

            @Override
            public void openBaseMenu(Player player) {
                if (canOpenNpc(player)) {
                    if (this.mapId == 45) {
                        this.createOtherMenu(player, ConstNpc.BASE_MENU,
                                "Con muốn làm gì nào", "Đến Kaio", "Quay số\nmay mắn");
                    }
                    if (this.mapId == 169) {
                        this.createOtherMenu(player, 0,
                                "\b|3|Con muốn gì nào?\nCon đang còn : " + player.pointPvp + " \b|7|điểm Đánh Boss", "Đến Khu Boss", "Đổi Cải trang sự kiên", "Top Đánh Boss");
                    }
                    if (this.mapId == 129) {
                        this.createOtherMenu(player, 0,
                                "Con muốn gì nào?", "Quay ve");
                    }
                }
            }

            @Override
            public void confirmMenu(Player player, int select) {
                if (canOpenNpc(player)) {
                    if (this.mapId == 169) {
                        if (player.iDMark.getIndexMenu() == 0) { // 
                            switch (select) {
                                case 0:
                                    ChangeMapService.gI().changeMapBySpaceShip(player, 170, -1, 354);
                                    Service.getInstance().changeFlag(player, Util.nextInt(8));
                                    break; // qua dhvt
                                case 1:  // 
                                    this.createOtherMenu(player, 1,
                                            "Bạn có muốn đổi 500 điểm PVP lấy \n|6|Cải trang Mèo Kid Lân với tất cả chỉ số là 80%\n ", "Ok", "Tu choi");
                                    // bat menu doi item
                                    break;

                                case 2:  // 
                                    Util.showListTop(player, (byte) 3);
                                    // mo top pvp
                                    break;

                            }
                        }
                        if (player.iDMark.getIndexMenu() == 1) { // action doi item
                            switch (select) {
                                case 0: // trade
                                    if (player.pointPvp >= 500) {
                                        player.pointPvp -= 500;
                                        Item item = ItemService.gI().createNewItem((short) (1104));
                                        item.itemOptions.add(new Item.ItemOption(49, 80));
                                        item.itemOptions.add(new Item.ItemOption(77, 80));
                                        item.itemOptions.add(new Item.ItemOption(103, 50));
                                        item.itemOptions.add(new Item.ItemOption(207, 0));
                                        item.itemOptions.add(new Item.ItemOption(33, 0));
//                                      
                                        InventoryServiceNew.gI().addItemBag(player, item);
                                        Service.getInstance().sendThongBao(player, "Chúc Mừng Bạn Đổi Cải Trang Thành Công !");
                                    } else {
                                        Service.getInstance().sendThongBao(player, "Không đủ điểm bạn còn " + (500 - player.pointPvp) + " Điểm nữa");
                                    }
                                    break;
                            }
                        }
                    }
                    if (this.mapId == 129) {
                        switch (select) {
                            case 0: // quay ve
                                ChangeMapService.gI().changeMapBySpaceShip(player, 0, -1, 354);
                                break;
                        }
                    }
                    if (this.mapId == 45) {
                        if (player.iDMark.isBaseMenu()) {
                            switch (select) {
                                case 0:
                                    ChangeMapService.gI().changeMapBySpaceShip(player, 48, -1, 354);
                                    break;
                                case 1:
                                    this.createOtherMenu(player, ConstNpc.MENU_CHOOSE_LUCKY_ROUND,
                                            "Con muốn làm gì nào?", "Quay bằng\nvàng",
                                            "Rương phụ\n("
                                            + (player.inventory.itemsBoxCrackBall.size()
                                            - InventoryServiceNew.gI().getCountEmptyListItem(player.inventory.itemsBoxCrackBall))
                                            + " món)",
                                            "Xóa hết\ntrong rương", "Đóng");
                                    break;
                            }
                        } else if (player.iDMark.getIndexMenu() == ConstNpc.MENU_CHOOSE_LUCKY_ROUND) {
                            switch (select) {
                                case 0:
                                    LuckyRound.gI().openCrackBallUI(player, LuckyRound.USING_GOLD);
                                    break;
                                case 1:
                                    ShopServiceNew.gI().opendShop(player, "ITEMS_LUCKY_ROUND", true);
                                    break;
                                case 2:
                                    NpcService.gI().createMenuConMeo(player,
                                            ConstNpc.CONFIRM_REMOVE_ALL_ITEM_LUCKY_ROUND, this.avartar,
                                            "Con có chắc muốn xóa hết vật phẩm trong rương phụ? Sau khi xóa "
                                            + "sẽ không thể khôi phục!",
                                            "Đồng ý", "Hủy bỏ");
                                    break;
                            }
                        }
                    }

                }
            }
        };
    }

    public static Npc thanVuTru(int mapId, int status, int cx, int cy, int tempId, int avartar) {
        return new Npc(mapId, status, cx, cy, tempId, avartar) {
            @Override
            public void openBaseMenu(Player player) {
                if (canOpenNpc(player)) {
                    if (this.mapId == 48) {
                        this.createOtherMenu(player, ConstNpc.BASE_MENU,
                                "Con muốn làm gì nào?", "Di chuyển");
                    }
                }
            }

            @Override
            public void confirmMenu(Player player, int select) {
                if (canOpenNpc(player)) {
                    if (this.mapId == 48) {
                        if (player.iDMark.isBaseMenu()) {
                            switch (select) {
                                case 0:
                                    this.createOtherMenu(player, ConstNpc.MENU_DI_CHUYEN,
                                            "Con muốn đi đâu?", "Về\nthần điện", "Thánh địa\nKaio", "Con\nđường\nrắn độc", "Từ chối");
                                    break;
                            }
                        } else if (player.iDMark.getIndexMenu() == ConstNpc.MENU_DI_CHUYEN) {
                            switch (select) {
                                case 0:
                                    ChangeMapService.gI().changeMapBySpaceShip(player, 45, -1, 354);
                                    break;
                                case 1:
                                    ChangeMapService.gI().changeMap(player, 50, -1, 318, 336);
                                    break;
                                case 2:
                                    if (player.clan != null) {
                                        if (player.clan.ConDuongRanDoc != null) {
                                            this.createOtherMenu(player, ConstNpc.MENU_OPENED_CDRD,
                                                    "Bang hội của con đang đi con đường rắn độc cấp độ "
                                                    + player.clan.ConDuongRanDoc.level + "\nCon có muốn đi theo không?",
                                                    "Đồng ý", "Từ chối");
                                        } else {
                                            this.createOtherMenu(player, ConstNpc.MENU_OPEN_CDRD,
                                                    "Đây là Con đường rắn độc \nCác con cứ yên tâm lên đường\n"
                                                    + "Ở đây có ta lo\nNhớ chọn cấp độ vừa sức mình nhé",
                                                    "Chọn\ncấp độ", "Từ chối");
                                        }
                                    } else {
                                        this.npcChat(player, "Con phải có bang hội ta mới có thể cho con đi");
                                    }
                                    break;
                            }
                        } else if (player.iDMark.getIndexMenu() == ConstNpc.MENU_OPENED_CDRD) {
                            switch (select) {
                                case 0:
                                    if (player.isAdmin() || player.nPoint.power >= ConDuongRanDoc.POWER_CAN_GO_TO_CDRD) {
                                        ChangeMapService.gI().goToCDRD(player);
                                    } else {
                                        Service.gI().sendThongBao(player, "Không đủ sức mạnh yêu cầu");
                                    }
                                    if (player.clan.haveGoneConDuongRanDoc) {
                                        createOtherMenu(player, ConstNpc.IGNORE_MENU,
                                                "Bang hội của ngươi đã đi con đường rắn độc lúc " + TimeUtil.formatTime(player.clan.lastTimeOpenConDuongRanDoc, "HH:mm:ss") + " hôm nay. Người mở\n"
                                                + "(" + player.clan.playerOpenDoanhTrai + "). Hẹn ngươi quay lại vào ngày mai", "OK", "Hướng\ndẫn\nthêm");
                                        return;
                                    } else if (player.clanMember.getNumDateFromJoinTimeToToday() < 2) {
                                        Service.gI().sendThongBao(player, "Yêu cầu tham gia bang hội trên 2 ngày!");
                                    } else {
                                        this.npcChat(player, "Sức mạnh của con phải ít nhất phải đạt "
                                                + Util.numberToMoney(ConDuongRanDoc.POWER_CAN_GO_TO_CDRD));
                                    }
                                    break;

                            }
                        } else if (player.iDMark.getIndexMenu() == ConstNpc.MENU_OPEN_CDRD) {
                            switch (select) {
                                case 0:
                                    if (player.isAdmin() || player.nPoint.power >= ConDuongRanDoc.POWER_CAN_GO_TO_CDRD) {
                                        Input.gI().createFormChooseLevelCDRD(player);
                                    } else {
                                        this.npcChat(player, "Sức mạnh của con phải ít nhất phải đạt "
                                                + Util.numberToMoney(ConDuongRanDoc.POWER_CAN_GO_TO_CDRD));
                                    }
                                    break;
                            }

                        } else if (player.iDMark.getIndexMenu() == ConstNpc.MENU_ACCEPT_GO_TO_CDRD) {
                            switch (select) {
                                case 0:
                                    ConDuongRanDocService.gI().openConDuongRanDoc(player, Byte.parseByte(String.valueOf(PLAYERID_OBJECT.get(player.id))));
                                    break;
                            }
                        }
                    }
                }
            }

        };
    }

    public static Npc giuma(int mapId, int status, int cx, int cy, int tempId, int avartar) {
        return new Npc(mapId, status, cx, cy, tempId, avartar) {
            @Override
            public void openBaseMenu(Player player) {
                if (canOpenNpc(player)) {
                    if (this.mapId == 6 || this.mapId == 25 || this.mapId == 26) {
                        this.createOtherMenu(player, ConstNpc.BASE_MENU,
                                "Gô Tên, Calich và Monaka đang gặp chuyện ở hành tinh Potaufeu \n Hãy đến đó ngay", "Đến \nPotaufeu");
                    } else if (this.mapId == 139) {
                        this.createOtherMenu(player, ConstNpc.BASE_MENU,
                                "Người muốn trở về?", "Quay về", "Từ chối");
                    }
                }
            }

            @Override
            public void confirmMenu(Player player, int select) {
                if (canOpenNpc(player)) {
                    if (this.mapId == 24 || this.mapId == 25 || this.mapId == 26) {
                        if (player.iDMark.isBaseMenu()) {
                            if (select == 0) {
                                //đến potaufeu
                                ChangeMapService.gI().goToPotaufeu(player);
                            }
                        }
                    } else if (this.mapId == 139) {
                        if (player.iDMark.isBaseMenu()) {
                            switch (select) {
                                //về trạm vũ trụ
                                case 0:
                                    ChangeMapService.gI().changeMapBySpaceShip(player, 24 + player.gender, -1, -1);
                                    break;
                            }
                        }
                    }
                }
            }
        };
    }

    public static Npc osin(int mapId, int status, int cx, int cy, int tempId, int avartar) {
        return new Npc(mapId, status, cx, cy, tempId, avartar) {
            @Override
            public void openBaseMenu(Player player) {
                if (canOpenNpc(player)) {
                    if (this.mapId == 50) {
                        this.createOtherMenu(player, ConstNpc.BASE_MENU, "Ta có thể giúp gì cho ngươi ?",
                                "Đến\nKaio", "Đến\nhành tinh\nBill", "Từ chối");
                   } else if (this.mapId == 154) {
                        this.createOtherMenu(player, ConstNpc.BASE_MENU, "Ngươi muốn đi về Trái Đất hay đi đến Thung Lũng Hủy Diệt?",
                                "Về thánh địa","Đến\n Thung Lũng Hủy Diệt", "Đến\n Hành tinh\nNgục tù", "Đến\n Cánh Đồng\nThiên Sứ" ,"Hướng dẫn");
                   } else if (this.mapId == 208) {
                        this.createOtherMenu(player, ConstNpc.BASE_MENU, "Ta có thể giúp ngươi trở về Vùng Đất của các Vị Thần ?",
                                "Quay về\n Vùng Đất của Thần", "Tạm biệt");
                   } else if (this.mapId == 218) {
                        this.createOtherMenu(player, ConstNpc.BASE_MENU, "Ta có thể giúp ngươi trở về Vùng Đất của các Vị Thần ?",
                                "Quay về\n Vùng Đất của Thần", "Tạm biệt");
                   } else if (this.mapId == 155) {
                        this.createOtherMenu(player, ConstNpc.BASE_MENU, "Ta có thể giúp ngươi trở về Vùng Đất của các Vị Thần ?",
                                "Quay về\n Vùng Đất của Thần", "Tạm biệt");
                   } else if (this.mapId == 52) {
                        try {
                            MapMaBu.gI().setTimeJoinMapMaBu();
                            if (this.mapId == 52) {
                                long now = System.currentTimeMillis();
                                if (now > MapMaBu.TIME_OPEN_MABU && now < MapMaBu.TIME_CLOSE_MABU) {
                                    this.createOtherMenu(player, ConstNpc.MENU_OPEN_MMB, "Đại chiến Ma Bư đã mở, "
                                            + "ngươi có muốn tham gia không?",
                                            "Hướng dẫn\nthêm", "Tham gia", "Từ chối");
                                } else {
                                    this.createOtherMenu(player, ConstNpc.MENU_NOT_OPEN_MMB,
                                            "Ta có thể giúp gì cho ngươi?", "Hướng dẫn", "Từ chối");
                                }

                            }
                        } catch (Exception ex) {
                            Logger.error("Lỗi mở menu osin");
                        }

                    } else if (this.mapId >= 114 && this.mapId < 120 && this.mapId != 116) {
                        if (player.fightMabu.pointMabu >= player.fightMabu.POINT_MAX) {
                            this.createOtherMenu(player, ConstNpc.GO_UPSTAIRS_MENU, "Ta có thể giúp gì cho ngươi ?",
                                    "Lên Tầng!", "Quay về", "Từ chối");
                        } else {
                            this.createOtherMenu(player, ConstNpc.BASE_MENU, "Ta có thể giúp gì cho ngươi ?",
                                    "Quay về", "Từ chối");
                        }
                    } else if (this.mapId == 120) {
                        this.createOtherMenu(player, ConstNpc.BASE_MENU, "Ta có thể giúp gì cho ngươi ?",
                                "Quay về", "Từ chối");
                    } else {
                        super.openBaseMenu(player);
                    }
                }
            }

            @Override
            public void confirmMenu(Player player, int select) {
                if (canOpenNpc(player)) {
                    if (this.mapId == 50) {
                        if (player.iDMark.isBaseMenu()) {
                            switch (select) {
                                case 0:
                                    ChangeMapService.gI().changeMap(player, 48, -1, 354, 240);
                                    break;
                                case 1:
                                    ChangeMapService.gI().changeMap(player, 154, -1, 200, 792);
                                    break;
                            }
                        }
                    } else if (this.mapId == 154) {
                        if (player.iDMark.isBaseMenu()) {
                            switch (select) {
                                case 0:
                                    ChangeMapService.gI().changeMap(player, 50, -1, 318, 336);
                                    break;
                               case 1:
                                    ChangeMapService.gI().changeMap(player, 208, -1, 181, 144);
                                    break;
                                case 2:
                                    ChangeMapService.gI().changeMap(player, 155, -1, 166, 792);
                                    break;
                                case 3:
                                    ChangeMapService.gI().changeMap(player, 218, -1, 70, 792);
                                    break;
                            }
                        }
                    } else if (this.mapId == 208) {
                        if (player.iDMark.isBaseMenu()) {
                            if (select == 0) {
                                ChangeMapService.gI().changeMap(player, 154, -1, 780, 312);
                            }
                        }
                    } else if (this.mapId == 218) {
                        if (player.iDMark.isBaseMenu()) {
                            if (select == 0) {
                                ChangeMapService.gI().changeMap(player, 154, -1, 780, 312);
                            }
                        }
                    } else if (this.mapId == 155) {
                        if (player.iDMark.isBaseMenu()) {
                            if (select == 0) {
                                ChangeMapService.gI().changeMap(player, 154, -1, 780, 312);
                            }
                        }
                    } else if (this.mapId == 52) {
                        switch (player.iDMark.getIndexMenu()) {
                            case ConstNpc.MENU_REWARD_MMB:
                                break;
                            case ConstNpc.MENU_OPEN_MMB:
                                if (select == 0) {
                                    NpcService.gI().createTutorial(player, this.avartar, ConstNpc.HUONG_DAN_MAP_MA_BU);
                                } else if (select == 1) {
                                    ChangeMapService.gI().changeMap(player, 114, -1, 318, 336);
                                }
                                break;
                            case ConstNpc.MENU_NOT_OPEN_BDW:
                                if (select == 0) {
                                    NpcService.gI().createTutorial(player, this.avartar, ConstNpc.HUONG_DAN_MAP_MA_BU);
                                }
                                break;
                        }
                    } else if (this.mapId >= 114 && this.mapId < 120 && this.mapId != 116) {
                        if (player.iDMark.getIndexMenu() == ConstNpc.GO_UPSTAIRS_MENU) {
                            if (select == 0) {
                                player.fightMabu.clear();
                                ChangeMapService.gI().changeMap(player, this.map.mapIdNextMabu((short) this.mapId), -1, this.cx, this.cy);
                            } else if (select == 1) {
                                ChangeMapService.gI().changeMapBySpaceShip(player, player.gender + 21, 0, -1);
                            }
                        } else {
                            if (select == 0) {
                                ChangeMapService.gI().changeMapBySpaceShip(player, player.gender + 21, 0, -1);
                            }
                        }
                    } else if (this.mapId == 120) {
                        if (player.iDMark.getIndexMenu() == ConstNpc.BASE_MENU) {
                            if (select == 0) {
                                ChangeMapService.gI().changeMapBySpaceShip(player, player.gender + 21, 0, -1);
                            }
                        }
                    }
                }
            }
        };
    }
           public static Npc kibit(int mapId, int status, int cx, int cy, int tempId, int avartar) {
                   return new Npc(mapId, status, cx, cy, tempId, avartar) {
            @Override
            public void openBaseMenu(Player player) {
                if (canOpenNpc(player)) {
                    mabu2h.gI().setTimeJoinmabu2h();} 
                            if (this.mapId == 52) {                                               
                                long now = System.currentTimeMillis();
                                if (now > mabu2h.TIME_OPEN_2h && now < mabu2h.TIME_CLOSE_2h) {
                                    this.createOtherMenu(player, ConstNpc.MENU_OPEN_MMB,   "Ma Bư đã hồi sinh hãy giải cứu Piccolo",
                                           "Hướng dẫn\nthêm", "Tham gia", "Từ chối");
                                } else {
                                    this.createOtherMenu(player, ConstNpc.MENU_NOT_OPEN_MMB,
                                        "Xin hãy cứu lấy người dân",
                                             "Hướng dẫn", "Từ chối");
                                }                           
                            }
                        }    
            @Override
            public void confirmMenu(Player player, int select) {
                if (canOpenNpc(player)) {
                   switch (this.mapId) {
                        case 52:
                        switch (player.iDMark.getIndexMenu()) {
                            case ConstNpc.MENU_REWARD_MMB:
                            case ConstNpc.MENU_OPEN_MMB:
                                    if (select == 0)
                                    NpcService.gI().createTutorial(player, this.avartar, ConstNpc.HUONG_DAN_MAP_2h);
                                    if (!player.getSession().actived) {                                   
                                    }
                                   if (select == 1){
                                        ChangeMapService.gI().changeMap(player, 127, 0, 66, 312);
                                        break;
                                                            }
                                break;
                            case ConstNpc.MENU_NOT_OPEN_BDW:
                                if (select == 0) {
                                    NpcService.gI().createTutorial(player, this.avartar, ConstNpc.HUONG_DAN_MAP_2h);
                                }
                                break;
                               }
                          }
                      }
                  }    
              };
           }
    public static Npc linhCanh(int mapId, int status, int cx, int cy, int tempId, int avartar) {
        return new Npc(mapId, status, cx, cy, tempId, avartar) {
            @Override
            public void openBaseMenu(Player player) {
                if (canOpenNpc(player)) {
                    if (player.clan == null) {
                        this.createOtherMenu(player, ConstNpc.IGNORE_MENU,
                                "Chỉ tiếp các bang hội, miễn tiếp khách vãng lai", "Đóng");
                        return;
                    }
                    if (player.clan.getMembers().size() < DoanhTrai.N_PLAYER_CLAN) {
                        this.createOtherMenu(player, ConstNpc.IGNORE_MENU,
                                "Bang hội phải có ít nhất 5 thành viên mới có thể mở", "Đóng");
                        return;
                    }
                    if (player.clan.doanhTrai != null) {
                        createOtherMenu(player, ConstNpc.MENU_OPENED_DOANH_TRAI,
                                "Bang hội của ngươi đang đánh trại độc nhãn\n"
                                + "Thời gian còn lại là "
                                + TimeUtil.getMinLeft(player.clan.timeOpenDoanhTrai, DoanhTrai.TIME_DOANH_TRAI / 1000)
                                + " phút. Ngươi có muốn tham gia không?",
                                "Tham gia", "Không", "Hướng\ndẫn\nthêm");
                        return;
                    }
                    int nPlSameClan = 0;
                    for (Player pl : player.zone.getPlayers()) {
                        if (!pl.equals(player) && pl.clan != null
                                && pl.clan.equals(player.clan) && pl.location.x >= 1285
                                && pl.location.x <= 1645) {
                            nPlSameClan++;
                        }
                    }
                    if (nPlSameClan < DoanhTrai.N_PLAYER_MAP) {
                        createOtherMenu(player, ConstNpc.IGNORE_MENU,
                                "Ngươi phải có ít nhất " + DoanhTrai.N_PLAYER_MAP + " đồng đội cùng bang đứng gần mới có thể\nvào\n"
                                + "tuy nhiên ta khuyên ngươi nên đi cùng với 3-4 người để khỏi chết.\n"
                                + "Hahaha.", "OK", "Hướng\ndẫn\nthêm");
                        return;
                    }
                    if (player.clanMember.getNumDateFromJoinTimeToToday() < 0) {
                        createOtherMenu(player, ConstNpc.IGNORE_MENU,
                                "Doanh trại chỉ cho phép những người ở trong bang trên 0 ngày. Hẹn ngươi quay lại vào lúc khác",
                                "OK", "Hướng\ndẫn\nthêm");
                        return;
                    }
                    if (player.clan.haveGoneDoanhTrai) {
                        createOtherMenu(player, ConstNpc.IGNORE_MENU,
                                "Bang hội của ngươi đã đi trại lúc " + TimeUtil.formatTime(player.clan.lastTimeOpenDoanhTrai, "HH:mm:ss") + " hôm nay. Người mở\n"
                                + "(" + player.clan.playerOpenDoanhTrai + "). Hẹn ngươi quay lại vào ngày mai", "OK", "Hướng\ndẫn\nthêm");
                        return;
                    }
                    createOtherMenu(player, ConstNpc.MENU_JOIN_DOANH_TRAI,
                            "Hôm nay bang hội của ngươi chưa vào trại lần nào. Ngươi có muốn vào\n"
                            + "không?\nĐể vào, ta khuyên ngươi nên có 3-4 người cùng bang đi cùng",
                            "Vào\n(miễn phí)", "Không", "Hướng\ndẫn\nthêm");
                }
            }
            @Override
            public void confirmMenu(Player player, int select) {
                if (canOpenNpc(player)) {
                    switch (player.iDMark.getIndexMenu()) {
                        case ConstNpc.MENU_JOIN_DOANH_TRAI:
                            if (select == 0) {
                                DoanhTraiService.gI().opendoanhtrai(player);
                            } else if (select == 2) {
                                NpcService.gI().createTutorial(player, this.avartar, ConstNpc.HUONG_DAN_DOANH_TRAI);
                            }
                            break;
                        case ConstNpc.IGNORE_MENU:
                            if (select == 1) {
                                NpcService.gI().createTutorial(player, this.avartar, ConstNpc.HUONG_DAN_DOANH_TRAI);
                            }
                            break;
                        case ConstNpc.MENU_OPENED_DOANH_TRAI:
                            if (select == 0) {
                                ChangeMapService.gI().changeMapInYard(player, 53, player.clan.doanhTrai.id, 60);
                            }
                            break;
                    }
                }
            }
        };
    }
    private static Npc mrpopo(int mapId, int status, int cx, int cy, int tempId, int avartar) {
        return new Npc(mapId, status, cx, cy, tempId, avartar) {
            @Override
            public void openBaseMenu(Player player) {
                if (canOpenNpc(player)) {
                        if (player.getSession().is_gift_box) {
//                            this.createOtherMenu(player, ConstNpc.BASE_MENU, "Chào con, con muốn ta giúp gì nào?", "Giải tán bang hội", "Nhận quà\nđền bù");
                        } else {
                            this.createOtherMenu(player, ConstNpc.BASE_MENU, "Thượng Đế vừa phát hiện 1 loại khí đang âm thầm\nhủy diệt mọi mầm sống trên Trái Đất,\nnó được gọi là Destron Gas.\nTa sẽ đưa các cậu đến nơi ấy, các cậu sẵn sàng chưa?","Thông tin chi tiết","Top 100 bang hội","OK","Từ chối");
                        }
                }
            }

            @Override
            public void confirmMenu(Player player, int select) {
                if (canOpenNpc(player)) {
                    if (player.iDMark.isBaseMenu()) {
                        switch (select) {
                            case 0:
                                return;
                            case 1:
                                return;
                            case 2:
                              if (player.clan != null) {
                                    if (player.clan.KhiGaHuyDiet != null) {
                                        this.createOtherMenu(player, ConstNpc.MENU_OPENED_KGHD,
                                                "Bang hội của con đang đi khí ga hủy diệt cấp độ "
                                                        + player.clan.KhiGaHuyDiet.level + "\nCon có muốn đi theo không?",
                                                "Đồng ý", "Từ chối");
                                    } else {

                                        this.createOtherMenu(player, ConstNpc.MENU_OPEN_KGHD,
                                                "Đây là khí ga hủy diệt \nCác con cứ yên tâm lên đường\n"
                                                        + "Ở đây có ta lo\nNhớ chọn cấp độ vừa sức mình nhé",
                                                "Chọn\ncấp độ", "Từ chối");
                                    }
                                } else {
                                    this.npcChat(player, "Con phải có bang hội ta mới có thể cho con đi");
                                }
                                break;
                            case 3:
                                return;
                        }
                    } else if (player.iDMark.getIndexMenu() == ConstNpc.MENU_OPENED_KGHD) {
                        switch (select) {
                            case 0:
                                if (player.isAdmin() || player.nPoint.power >= KhiGasHuyDiet.POWER_CAN_GO_TO_KGHD) {
                                    ChangeMapService.gI().goToKGHD(player);
                                } else {
                                    this.npcChat(player, "Sức mạnh của con phải ít nhất phải đạt "
                                            + Util.numberToMoney(KhiGasHuyDiet.POWER_CAN_GO_TO_KGHD));
                                }
                                break;

                        }
                    } else if (player.iDMark.getIndexMenu() == ConstNpc.MENU_OPEN_KGHD) {
                        switch (select) {
                            case 0:
                                if (player.isAdmin() || player.nPoint.power >= KhiGasHuyDiet.POWER_CAN_GO_TO_KGHD) {
                                    Input.gI().createFormChooseLevelKGHD(player);
                                } else {
                                    this.npcChat(player, "Sức mạnh của con phải ít nhất phải đạt "
                                            + Util.numberToMoney(KhiGasHuyDiet.POWER_CAN_GO_TO_KGHD));
                                }
                                break;
                        }

                    } else if (player.iDMark.getIndexMenu() == ConstNpc.MENU_ACCEPT_GO_TO_KGHD) {
                        switch (select) {
                            case 0:
                                KhiGasHuyDietService.gI().openKhiGaHuyDiet(player, Byte.parseByte(String.valueOf(PLAYERID_OBJECT.get(player.id))));
                                break;
                        }
                }
            }
        };
      };
    }
    public static Npc quaTrung(int mapId, int status, int cx, int cy, int tempId, int avartar) {
        return new Npc(mapId, status, cx, cy, tempId, avartar) {

            private final int COST_AP_TRUNG_NHANH = 1000000000;

            @Override
            public void openBaseMenu(Player player) {
                if (canOpenNpc(player)) {
                    player.mabuEgg.sendMabuEgg();
                    if (player.mabuEgg.getSecondDone() != 0) {
                        this.createOtherMenu(player, ConstNpc.CAN_NOT_OPEN_EGG, "Bư bư bư...",
                                "Hủy bỏ\ntrứng", "Ấp nhanh\n" + Util.numberToMoney(COST_AP_TRUNG_NHANH) + " vàng", "Đóng");
                    } else {
                        this.createOtherMenu(player, ConstNpc.CAN_OPEN_EGG, "Bư bư bư...", "Nở", "Hủy bỏ\ntrứng", "Đóng");
                    }
                }
            }

            @Override
            public void confirmMenu(Player player, int select) {
                if (canOpenNpc(player)) {
                    switch (player.iDMark.getIndexMenu()) {
                        case ConstNpc.CAN_NOT_OPEN_EGG:
                            if (select == 0) {
                                this.createOtherMenu(player, ConstNpc.CONFIRM_DESTROY_EGG,
                                        "Bạn có chắc chắn muốn hủy bỏ trứng Mabư?", "Đồng ý", "Từ chối");
                            } else if (select == 1) {
                                if (player.inventory.gold >= COST_AP_TRUNG_NHANH) {
                                    player.inventory.gold -= COST_AP_TRUNG_NHANH;
                                    player.mabuEgg.timeDone = 0;
                                    Service.getInstance().sendMoney(player);
                                    player.mabuEgg.sendMabuEgg();
                                } else {
                                    Service.getInstance().sendThongBao(player,
                                            "Bạn không đủ vàng để thực hiện, còn thiếu "
                                            + Util.numberToMoney((COST_AP_TRUNG_NHANH - player.inventory.gold)) + " vàng");
                                }
                            }
                            break;
                        case ConstNpc.CAN_OPEN_EGG:
                            switch (select) {
                                case 0:
                                    this.createOtherMenu(player, ConstNpc.CONFIRM_OPEN_EGG,
                                            "Bạn có chắc chắn cho trứng nở?\n"
                                            + "Đệ tử của bạn sẽ được thay thế bằng đệ Mabư",
                                            "Đệ mabư\nTrái Đất", "Đệ mabư\nNamếc", "Đệ mabư\nXayda", "Từ chối");
                                    break;
                                case 1:
                                    this.createOtherMenu(player, ConstNpc.CONFIRM_DESTROY_EGG,
                                            "Bạn có chắc chắn muốn hủy bỏ trứng Mabư?", "Đồng ý", "Từ chối");
                                    break;
                            }
                            break;
                        case ConstNpc.CONFIRM_OPEN_EGG:
                            switch (select) {
                                case 0:
                                    player.mabuEgg.openEgg(ConstPlayer.TRAI_DAT);
                                    break;
                                case 1:
                                    player.mabuEgg.openEgg(ConstPlayer.NAMEC);
                                    break;
                                case 2:
                                    player.mabuEgg.openEgg(ConstPlayer.XAYDA);
                                    break;
                                default:
                                    break;
                            }
                            break;
                        case ConstNpc.CONFIRM_DESTROY_EGG:
                            if (select == 0) {
                                player.mabuEgg.destroyEgg();
                            }
                            break;
                    }
                }
            }
        };
    }

    public static Npc quocVuong(int mapId, int status, int cx, int cy, int tempId, int avartar) {
        return new Npc(mapId, status, cx, cy, tempId, avartar) {

            @Override
            public void openBaseMenu(Player player) {
                this.createOtherMenu(player, ConstNpc.BASE_MENU,
                        "Con muốn nâng giới hạn sức mạnh cho bản thân hay đệ tử?",
                        "Bản thân", "Đệ tử", "Từ chối");
            }

            @Override
            public void confirmMenu(Player player, int select) {
                if (canOpenNpc(player)) {
                    if (player.iDMark.isBaseMenu()) {
                        switch (select) {
                            case 0:
                                if (player.nPoint.limitPower < NPoint.MAX_LIMIT) {
                                    this.createOtherMenu(player, ConstNpc.OPEN_POWER_MYSEFT,
                                            "Ta sẽ truền năng lượng giúp con mở giới hạn sức mạnh của bản thân lên "
                                            + Util.numberToMoney(player.nPoint.getPowerNextLimit()),
                                            "Nâng\ngiới hạn\nsức mạnh",
                                            "Nâng ngay\n" + Util.numberToMoney(OpenPowerService.COST_SPEED_OPEN_LIMIT_POWER) + " vàng", "Đóng");
                                } else {
                                    this.createOtherMenu(player, ConstNpc.IGNORE_MENU,
                                            "Sức mạnh của con đã đạt tới giới hạn",
                                            "Đóng");
                                }
                                break;
                            case 1:
                                if (player.pet != null) {
                                    if (player.pet.nPoint.limitPower < NPoint.MAX_LIMIT) {
                                        this.createOtherMenu(player, ConstNpc.OPEN_POWER_PET,
                                                "Ta sẽ truền năng lượng giúp con mở giới hạn sức mạnh của đệ tử lên "
                                                + Util.numberToMoney(player.pet.nPoint.getPowerNextLimit()),
                                                "Nâng ngay\n" + Util.numberToMoney(OpenPowerService.COST_SPEED_OPEN_LIMIT_POWER) + " vàng", "Đóng");
                                    } else {
                                        this.createOtherMenu(player, ConstNpc.IGNORE_MENU,
                                                "Sức mạnh của đệ con đã đạt tới giới hạn",
                                                "Đóng");
                                    }
                                } else {
                                    Service.gI().sendThongBao(player, "Không thể thực hiện");
                                }
                                //giới hạn đệ tử
                                break;
                        }
                    } else if (player.iDMark.getIndexMenu() == ConstNpc.OPEN_POWER_MYSEFT) {
                        switch (select) {
                            case 0:
                                OpenPowerService.gI().openPowerBasic(player);
                                break;
                            case 1:
                                if (player.inventory.gold >= OpenPowerService.COST_SPEED_OPEN_LIMIT_POWER) {
                                    if (OpenPowerService.gI().openPowerSpeed(player)) {
                                        player.inventory.gold -= OpenPowerService.COST_SPEED_OPEN_LIMIT_POWER;
                                        Service.gI().sendMoney(player);
                                    }
                                } else {
                                    Service.gI().sendThongBao(player,
                                            "Bạn không đủ vàng để mở, còn thiếu "
                                            + Util.numberToMoney((OpenPowerService.COST_SPEED_OPEN_LIMIT_POWER - player.inventory.gold)) + " vàng");
                                }
                                break;
                        }
                    } else if (player.iDMark.getIndexMenu() == ConstNpc.OPEN_POWER_PET) {
                        if (select == 0) {
                            if (player.inventory.gold >= OpenPowerService.COST_SPEED_OPEN_LIMIT_POWER) {
                                if (OpenPowerService.gI().openPowerSpeed(player.pet)) {
                                    player.inventory.gold -= OpenPowerService.COST_SPEED_OPEN_LIMIT_POWER;
                                    Service.gI().sendMoney(player);
                                }
                            } else {
                                Service.gI().sendThongBao(player,
                                        "Bạn không đủ vàng để mở, còn thiếu "
                                        + Util.numberToMoney((OpenPowerService.COST_SPEED_OPEN_LIMIT_POWER - player.inventory.gold)) + " vàng");
                            }
                        }
                    }
                }
            }
        };
    }
     public static Npc blackrose(int mapId, int status, int cx, int cy, int tempId, int avartar) {
        return new Npc(mapId, status, cx, cy, tempId, avartar) {

            @Override
            public void openBaseMenu(Player player) {
                this.createOtherMenu(player, ConstNpc.BASE_MENU,
                        "Con muốn nâng giới hạn sức mạnh cho bản thân hay đệ tử?",
                        "Bản thân", "Đệ tử", "Từ chối");
            }

            @Override
            public void confirmMenu(Player player, int select) {
                if (canOpenNpc(player)) {
                    if (player.iDMark.isBaseMenu()) {
                        switch (select) {
                            case 0:
                                if (player.nPoint.limitPower < NPoint.MAX_LIMIT) {
                                    this.createOtherMenu(player, ConstNpc.OPEN_POWER_MYSEFT,
                                            "Ta sẽ truền năng lượng giúp con mở giới hạn sức mạnh của bản thân lên "
                                            + Util.numberToMoney(player.nPoint.getPowerNextLimit()),
                                            "Nâng\ngiới hạn\nsức mạnh",
                                            "Nâng ngay\n" + Util.numberToMoney(OpenPowerService.COST_SPEED_OPEN_LIMIT_POWER) + " vàng", "Đóng");
                                } else {
                                    this.createOtherMenu(player, ConstNpc.IGNORE_MENU,
                                            "Sức mạnh của con đã đạt tới giới hạn",
                                            "Đóng");
                                }
                                break;
                            case 1:
                                if (player.pet != null) {
                                    if (player.pet.nPoint.limitPower < NPoint.MAX_LIMIT) {
                                        this.createOtherMenu(player, ConstNpc.OPEN_POWER_PET,
                                                "Ta sẽ truền năng lượng giúp con mở giới hạn sức mạnh của đệ tử lên "
                                                + Util.numberToMoney(player.pet.nPoint.getPowerNextLimit()),
                                                "Nâng ngay\n" + Util.numberToMoney(OpenPowerService.COST_SPEED_OPEN_LIMIT_POWER) + " vàng", "Đóng");
                                    } else {
                                        this.createOtherMenu(player, ConstNpc.IGNORE_MENU,
                                                "Sức mạnh của đệ con đã đạt tới giới hạn",
                                                "Đóng");
                                    }
                                } else {
                                    Service.getInstance().sendThongBao(player, "Không thể thực hiện");
                                }
                                //giới hạn đệ tử
                                break;
                        }
                    } else if (player.iDMark.getIndexMenu() == ConstNpc.OPEN_POWER_MYSEFT) {
                        switch (select) {
                            case 0:
                                OpenPowerService.gI().openPowerBasic(player);
                                break;
                            case 1:
                                if (player.inventory.gold >= OpenPowerService.COST_SPEED_OPEN_LIMIT_POWER) {
                                    if (OpenPowerService.gI().openPowerSpeed(player)) {
                                        player.inventory.gold -= OpenPowerService.COST_SPEED_OPEN_LIMIT_POWER;
                                        Service.getInstance().sendMoney(player);
                                    }
                                } else {
                                    Service.getInstance().sendThongBao(player,
                                            "Bạn không đủ vàng để mở, còn thiếu "
                                            + Util.numberToMoney((OpenPowerService.COST_SPEED_OPEN_LIMIT_POWER - player.inventory.gold)) + " vàng");
                                }
                                break;
                        }
                    } else if (player.iDMark.getIndexMenu() == ConstNpc.OPEN_POWER_PET) {
                        if (select == 0) {
                            if (player.inventory.gold >= OpenPowerService.COST_SPEED_OPEN_LIMIT_POWER) {
                                if (OpenPowerService.gI().openPowerSpeed(player.pet)) {
                                    player.inventory.gold -= OpenPowerService.COST_SPEED_OPEN_LIMIT_POWER;
                                    Service.getInstance().sendMoney(player);
                                }
                            } else {
                                Service.getInstance().sendThongBao(player,
                                        "Bạn không đủ vàng để mở, còn thiếu "
                                        + Util.numberToMoney((OpenPowerService.COST_SPEED_OPEN_LIMIT_POWER - player.inventory.gold)) + " vàng");
                            }
                        }
                    }
                }
            }
        };
    }

    public static Npc bulmaTL(int mapId, int status, int cx, int cy, int tempId, int avartar) {
        return new Npc(mapId, status, cx, cy, tempId, avartar) {
            @Override
            public void openBaseMenu(Player player) {
                if (!TaskService.gI().checkDoneTaskTalkNpc(player, this)) {
                    if (canOpenNpc(player)) {

                        if (this.mapId == 102) {
                            this.createOtherMenu(player, ConstNpc.BASE_MENU, "Cậu bé muốn mua gì nào?", "Cửa hàng","Quà Boss", "Đóng");
                        }
                    }
                }
            }

            @Override
            public void confirmMenu(Player player, int select) {
                if (canOpenNpc(player)) {
                    if (this.mapId == 102) {
                        if (player.iDMark.isBaseMenu()) {
                            if (select == 0) {
                                ShopServiceNew.gI().opendShop(player, "BUNMA_FUTURE", true);
                                }
                                if (select == 1) {
                                ShopServiceNew.gI().opendShop(player, "QUA_BOSS", true);
                            }
                        }
                    }
                }
            }
        };
    }
     public static Npc caythong(int mapId, int status, int cx, int cy, int tempId, int avartar) {
        return new Npc(mapId, status, cx, cy, tempId, avartar) {
            @Override
            public void openBaseMenu(Player player) {
                if (canOpenNpc(player)) {
                    if (this.mapId == 104 || this.mapId == 5) {
                        if (!TaskService.gI().checkDoneTaskTalkNpc(player, this)) {
                            this.createOtherMenu(player, ConstNpc.BASE_MENU, "tao la?", "Cửa hàng", "Đóng");
                        }
                    } else if (this.mapId == 104) {
                        this.createOtherMenu(player, ConstNpc.BASE_MENU, "Kính chào Ngài Linh thú sư!", "Cửa hàng", "Đóng");
                    }
                }
            }

            @Override
            public void confirmMenu(Player player, int select) {
                if (canOpenNpc(player)) {
                    if (this.mapId == 104 || this.mapId == 5) {
                        if (player.iDMark.isBaseMenu()) {
                            if (select == 0) {
                                ShopServiceNew.gI().opendShop(player, "binn", true);
                            }
                        }
                    } else if (this.mapId == 104 || this.mapId == 5) {
                        if (player.iDMark.isBaseMenu()) {
                            if (select == 0) {
//                                ShopServiceNew.gI().opendShop(player, "BUNMA_LINHTHU", true);
                            }
                        }
                    }
                }
            }
        };
    }

     public static Npc Brook(int mapId, int status, int cx, int cy, int tempId, int avartar) {
        return new Npc(mapId, status, cx, cy, tempId, avartar) {
            @Override
            public void openBaseMenu(Player player) {
                if (canOpenNpc(player)) {                   
                  if (this.mapId == 5) {
                        createOtherMenu(player, ConstNpc.BASE_MENU,
                      "Xin chào, ta lưu trữ tiền tiết kiệm của cậu !!!",
                            "Rút Coin", "Đóng");
                    }
                }
            }

             @Override
            public void confirmMenu(Player player, int select) {
                if (canOpenNpc(player)) {
                    if (this.mapId == 5) {
                        if (player.iDMark.isBaseMenu()) {
                            switch (select) {
                            case 0:
                                    this.createOtherMenu(player, 1, "|7|Số tiền của bạn còn : " + player.getSession().vnd + " VND"
                                            + "\b|1|Tỉ lệ quy đổi là 1000VND = 4 thỏi vàng\n" + "1000VND = 2000 hồng ngọc\n Ví Dụ Có 10.000VND Thì Nhập Vào Là 10\nCứ Quy Đổi Kể Cả 1 Thỏi Vàng Là Được Kích Hoạt Tài Khoản\nQuy Đổi Lỗi Thì Quy Đổi Lại Lần 2", "Quy đổi\n Thỏi vàng", "Quy Đổi\nHồng Ngọc", "Mở thành viên");
                                    break;
                            }
                        }
                      else if (player.iDMark.getIndexMenu() == 1){
                            switch (select) {
                                case 0:
                                    Input.gI().createFormQDTV(player);
                                    break;
                                case 1:
                                    Input.gI().createFormQDHN(player);
                                    break;
                                case 2:
                                    if (player.getSession().vnd >= 10000){
                                        if (player.kichhoat == 0){
                                            player.kichhoat = 1;
                                            PlayerDAO.subvnd(player, 10000);
                                            player.getSession().vnd -= 10000;
                                            Service.gI().sendThongBao(player, "Kích hoạt thành công");
                                        } else {
                                            Service.getInstance().sendThongBao(player, "Đã kích hoạt thành viên rồi");
                                        }
                                    } else {
                                        Service.getInstance().sendThongBao(player, "Bạn không đủ 10k");
                                    }
                                    break;
                            }
                        }
                    }
                }
            }
        };
    }
    public static Npc rongOmega(int mapId, int status, int cx, int cy, int tempId, int avartar) {
        return new Npc(mapId, status, cx, cy, tempId, avartar) {
            @Override
            public void openBaseMenu(Player player) {
                if (canOpenNpc(player)) {
                    BlackBallWar.gI().setTime();
                    if (this.mapId == 24 || this.mapId == 25 || this.mapId == 26) {
                        try {
                            long now = System.currentTimeMillis();
                            if (now > BlackBallWar.TIME_OPEN && now < BlackBallWar.TIME_CLOSE) {
                                this.createOtherMenu(player, ConstNpc.MENU_OPEN_BDW, "Đường đến với ngọc rồng sao đen đã mở, "
                                        + "ngươi có muốn tham gia không?",
                                        "Hướng dẫn\nthêm", "Tham gia", "Từ chối");
                            } else {
                                String[] optionRewards = new String[7];
                                int index = 0;
                                for (int i = 0; i < 7; i++) {
                                    if (player.rewardBlackBall.timeOutOfDateReward[i] > System.currentTimeMillis()) {
                                        String quantily = player.rewardBlackBall.quantilyBlackBall[i] > 1 ? "x" + player.rewardBlackBall.quantilyBlackBall[i] + " " : "";
                                        optionRewards[index] = quantily + (i + 1) + " sao";
                                        index++;
                                    }
                                }
                                if (index != 0) {
                                    String[] options = new String[index + 1];
                                    for (int i = 0; i < index; i++) {
                                        options[i] = optionRewards[i];
                                    }
                                    options[options.length - 1] = "Từ chối";
                                    this.createOtherMenu(player, ConstNpc.MENU_REWARD_BDW, "Ngươi có một vài phần thưởng ngọc "
                                            + "rồng sao đen đây!",
                                            options);
                                } else {
                                    this.createOtherMenu(player, ConstNpc.MENU_NOT_OPEN_BDW,
                                            "Ta có thể giúp gì cho ngươi?", "Hướng dẫn", "Từ chối");
                                }
                            }
                        } catch (Exception ex) {
                            Logger.error("Lỗi mở menu rồng Omega");
                        }
                    }
                }
            }

            @Override
            public void confirmMenu(Player player, int select) {
                if (canOpenNpc(player)) {
                    switch (player.iDMark.getIndexMenu()) {
                        case ConstNpc.MENU_REWARD_BDW:
                            player.rewardBlackBall.getRewardSelect((byte) select);
                            break;
                        case ConstNpc.MENU_OPEN_BDW:
                            if (select == 0) {
                                NpcService.gI().createTutorial(player, this.avartar, ConstNpc.HUONG_DAN_BLACK_BALL_WAR);
                            } else if (select == 1) {
                                player.iDMark.setTypeChangeMap(ConstMap.CHANGE_BLACK_BALL);
                                ChangeMapService.gI().openChangeMapTab(player);
                            }
                            break;
                        case ConstNpc.MENU_NOT_OPEN_BDW:
                            if (select == 0) {
                                NpcService.gI().createTutorial(player, this.avartar, ConstNpc.HUONG_DAN_BLACK_BALL_WAR);
                            }
                            break;
                    }
                }
            }

        };
    }

    public static Npc rong1_to_7s(int mapId, int status, int cx, int cy, int tempId, int avartar) {
        return new Npc(mapId, status, cx, cy, tempId, avartar) {
            @Override
            public void openBaseMenu(Player player) {
                if (canOpenNpc(player)) {
                    if (player.iDMark.isHoldBlackBall()) {
                        this.createOtherMenu(player, ConstNpc.MENU_PHU_HP, "Ta có thể giúp gì cho ngươi?", "Phù hộ", "Từ chối");
                    } else {
                        if (BossManager.gI().existBossOnPlayer(player)
                                || player.zone.items.stream().anyMatch(itemMap -> ItemMapService.gI().isBlackBall(itemMap.itemTemplate.id))
                                || player.zone.getPlayers().stream().anyMatch(p -> p.iDMark.isHoldBlackBall())) {
                            this.createOtherMenu(player, ConstNpc.MENU_OPTION_GO_HOME, "Ta có thể giúp gì cho ngươi?", "Về nhà", "Từ chối");
                        } else {
                            this.createOtherMenu(player, ConstNpc.MENU_OPTION_GO_HOME, "Ta có thể giúp gì cho ngươi?", "Về nhà", "Từ chối", "Gọi BOSS");
                        }
                    }
                }
            }

            @Override
            public void confirmMenu(Player player, int select) {
                if (canOpenNpc(player)) {
                    if (player.iDMark.getIndexMenu() == ConstNpc.MENU_PHU_HP) {
                        if (select == 0) {
                            this.createOtherMenu(player, ConstNpc.MENU_OPTION_PHU_HP,
                                    "Ta sẽ giúp ngươi tăng HP lên mức kinh hoàng, ngươi chọn đi",
                                    "x3 HP\n" + Util.numberToMoney(BlackBallWar.COST_X3) + " vàng",
                                    "x5 HP\n" + Util.numberToMoney(BlackBallWar.COST_X5) + " vàng",
                                    "x9000 HP\n" + Util.numberToMoney(BlackBallWar.COST_X7) + " vàng",
                                    "Từ chối"
                            );
                        }
                    } else if (player.iDMark.getIndexMenu() == ConstNpc.MENU_OPTION_GO_HOME) {
                        if (select == 0) {
                            ChangeMapService.gI().changeMapBySpaceShip(player, player.gender + 21, -1, 250);
                        } else if (select == 2) {
                            BossManager.gI().callBoss(player, mapId);
                        } else if (select == 1) {
                            this.npcChat(player, "Để ta xem ngươi trụ được bao lâu");
                        }
                    } else if (player.iDMark.getIndexMenu() == ConstNpc.MENU_OPTION_PHU_HP) {
                        if (player.effectSkin.xHPKI > 1) {
                            Service.getInstance().sendThongBao(player, "Bạn đã được phù hộ rồi!");
                            return;
                        }
                        switch (select) {
                            case 0:
                                BlackBallWar.gI().xHPKI(player, BlackBallWar.X3);
                                break;
                            case 1:
                                BlackBallWar.gI().xHPKI(player, BlackBallWar.X5);
                                break;
                            case 2:
                                BlackBallWar.gI().xHPKI(player, BlackBallWar.X7);
                                break;
                            case 3:
                                this.npcChat(player, "Để ta xem ngươi trụ được bao lâu");
                                break;
                        }
                    }
                }
            }
        };
    }

    public static Npc bill(int mapId, int status, int cx, int cy, int tempId, int avartar) {
        return new Npc(mapId, status, cx, cy, tempId, avartar) {
            @Override
            public void openBaseMenu(Player player) {
                if (canOpenNpc(player)) {
                    if (!player.setClothes.godClothes) {
                        this.createOtherMenu(player, ConstNpc.IGNORE_MENU,
                                "Ngươi Hãy Mặc đủ 5 món thần linh mới mở được SHOP Hủy Diệt, Hãy Đem Thức ăn đến đây",
                                "Đóng");
                    } else {
                        createOtherMenu(player, ConstNpc.BASE_MENU,
                                "Ngươi muốn gì nào?",
                                "Mua đồ hủy diệt", "Đóng");
                    }

                }
            }

            @Override
            public void confirmMenu(Player player, int select) {
                if (canOpenNpc(player)) {
                    switch (this.mapId) {
                        case 48:
                            switch (player.iDMark.getIndexMenu()) {
                                case ConstNpc.BASE_MENU:
                                    if (select == 0) {
                                        ShopServiceNew.gI().opendShop(player, "HUY_DIET", true);
                                        break;
                                    }
                                    break;
                            }
                            break;
                    }
                }
            }
        };
    }

    public static Npc boMong(int mapId, int status, int cx, int cy, int tempId, int avartar) {
        return new Npc(mapId, status, cx, cy, tempId, avartar) {
            @Override
            public void openBaseMenu(Player player) {
                if (canOpenNpc(player)) {
                    if (this.mapId == 47 || this.mapId == 84) {
                        this.createOtherMenu(player, ConstNpc.BASE_MENU,
                                "Chào bạn \btôi có thể giúp bạn làm nhiệm vụ", "Nhiệm vụ\nhàng ngày", "Nhận ngọc\nmiễn phí", "Từ chối");
                    }
                }
            }

            @Override
            public void confirmMenu(Player player, int select) {
                if (canOpenNpc(player)) {
                    if (this.mapId == 47 || this.mapId == 84) {
                        if (player.iDMark.isBaseMenu()) {
                            switch (select) {
                                case 0:
                                    if (player.playerTask.sideTask.template != null) {
                                        String npcSay = "Nhiệm vụ hiện tại: " + player.playerTask.sideTask.getName() + " ("
                                                + player.playerTask.sideTask.getLevel() + ")"
                                                + "\nHiện tại đã hoàn thành: " + player.playerTask.sideTask.count + "/"
                                                + player.playerTask.sideTask.maxCount + " ("
                                                + player.playerTask.sideTask.getPercentProcess() + "%)\nSố nhiệm vụ còn lại trong ngày: "
                                                + player.playerTask.sideTask.leftTask + "/" + ConstTask.MAX_SIDE_TASK;
                                        this.createOtherMenu(player, ConstNpc.MENU_OPTION_PAY_SIDE_TASK,
                                                npcSay, "Trả nhiệm\nvụ", "Hủy nhiệm\nvụ");
                                    } else {
                                        this.createOtherMenu(player, ConstNpc.MENU_OPTION_LEVEL_SIDE_TASK,
                                                "Tôi có vài nhiệm vụ theo cấp bậc, "
                                                + "sức cậu có thể làm được cái nào?",
                                                "Dễ", "Bình thường", "Khó", "Siêu khó", "Địa ngục", "Từ chối");
                                    }
                                    break;
                                    case 1:
                                        player.achievement.Show();
                                    break;
                            }
                        } else if (player.iDMark.getIndexMenu() == ConstNpc.MENU_OPTION_LEVEL_SIDE_TASK) {
                            switch (select) {
                                case 0:
                                case 1:
                                case 2:
                                case 3:
                                case 4:
                                    TaskService.gI().changeSideTask(player, (byte) select);
                                    break;
                            }
                        } else if (player.iDMark.getIndexMenu() == ConstNpc.MENU_OPTION_PAY_SIDE_TASK) {
                            switch (select) {
                                case 0:
                                    TaskService.gI().paySideTask(player);
                                    break;
                                case 1:
                                    TaskService.gI().removeSideTask(player);
                                    break;
                            }
                            
                        }
                    }
                }
            }
        };
    }

    public static Npc karin(int mapId, int status, int cx, int cy, int tempId, int avartar) {
        return new Npc(mapId, status, cx, cy, tempId, avartar) {
            @Override
            public void openBaseMenu(Player player) {
                if (canOpenNpc(player)) {
                    if (this.mapId == 46) {
                        if (!TaskService.gI().checkDoneTaskTalkNpc(player, this)) {
                            this.createOtherMenu(player, ConstNpc.BASE_MENU,"|7|Chào ngài, Ngài Cần gì ở tôi\b|1|Tôi có thể giúp ngài chuyển sinh với điều khiện\nCần 500 tỷ sức mạnh\nX99 mảnh linh hồn\nX99 Phù hiệu không gian, X99 TV\n|1|Hãy Qua Ngục từ các thủ lĩnh boss đang nắm giữ những vật phẩm ấy!!",
                                    "Chuyển sinh", "Đóng");
                         }
                      } 
                    }
                }
            @Override
            public void confirmMenu(Player player, int select) {
                if (canOpenNpc(player)) {
                    if (this.mapId == 46) {
                        if (player.iDMark.isBaseMenu()) {
                            if (select == 0) {
                                
                                OpenPowerService.gI().chuyenSinh(player);
                            }
                            
                       }
                      }
                   }
               }                         
        };
    }
    public static Npc Mai(int mapId, int status, int cx, int cy, int tempId, int avartar) {
        return new Npc(mapId, status, cx, cy, tempId, avartar) {
            @Override
            public void openBaseMenu(Player player) {
                if (canOpenNpc(player)) {                   
                  if (this.mapId == 5) {
                        createOtherMenu(player, ConstNpc.BASE_MENU,
                      "|7|Xin chào\b|3|ta đang giữ giúp ngươi quỹ đen !!!",
                            "Rút Coin","Nhận Thưởng\nMốc nạp","Chi tiết\nMốc Nhận", "Đóng");
                    }
                }
            }

             @Override
            public void confirmMenu(Player player, int select) {
                if (canOpenNpc(player)) {
                    if (this.mapId == 5) {
                        if (player.iDMark.isBaseMenu()) {
                            switch (select) {
                            case 0:
                                    this.createOtherMenu(player, 1, "|7|Số tiền của bạn còn : " + player.getSession().vnd + " VND"
                                            + "\b|1|Tỉ lệ quy đổi là 1000VND = 4 thỏi vàng\n" + "1000VND = 2000 hồng ngọc\n Ví Dụ Có 10.000VND Thì Nhập Vào Là 10\nCứ Quy Đổi Kể Cả 1 Thỏi Vàng Là Được Kích Hoạt Tài Khoản\nQuy Đổi Lỗi Thì Quy Đổi Lại Lần 2", "Quy đổi\n Thỏi vàng", "Quy Đổi\nHồng Ngọc", "Mở thành viên");
                                    break;
                                    case 1:
                                this.createOtherMenu(player, 2,
                                        "\b|1|Ngươi muốn đổi Quà Nạp à?\n|5|Khi quy đổi một mốc, số zeni trong hành trang ngươi sẽ bị trừ đi\n|5|Hãy lưu ý đọc kỹ quà nhận trước khi đổi\n|9|Cảm Ơn Bạn Đã Ủng Hộ!!!Mãi Keo:vv"
                                        + "\b|7|Bạn đang có :" + player.getSession().coinBar + " Zeni",
                                        "Nhận Mốc 10k", "Nhận Mốc 20k", "Nhận Mốc 50k", "Nhận Mốc 100k","Nhận Mốc 200k","Nhận Mốc 500k","Nhận Mốc 1000k");
                                break;
                                    case 2:
                                        NpcService.gI().createTutorial(player, this.avartar, ConstNpc.HUONG_DAN_MOC_NAP);
                                        break;
                            }
                        }
                      else if (player.iDMark.getIndexMenu() == 1){
                            switch (select) {
                                case 0:
                                    Input.gI().createFormQDTV(player);
                                    break;
                                case 1:
                                    Input.gI().createFormQDHN(player);
                                    break;
                                case 2:
                                    if (player.getSession().vnd >= 10000){
                                        if (player.kichhoat == 0){
                                            player.kichhoat = 1;
                                            PlayerDAO.subvnd(player, 10000);
                                            player.getSession().vnd -= 10000;
                                            Service.gI().sendThongBao(player, "Kích hoạt thành công");
                                        } else {
                                            Service.getInstance().sendThongBao(player, "Đã kích hoạt thành viên rồi");
                                        }
                                    } else {
                                        Service.getInstance().sendThongBao(player, "Bạn không đủ 10k");
                                    }
                                    break;
                            }
                            } else if (player.iDMark.getIndexMenu() == 2) {
                        switch (select) {
                            case 0: //barcosll
                                if (player.getSession().coinBar < 10000) {
                                    Service.gI().sendThongBao(player, "Bạn không tích đủ mốc 10k zeni");
                                    return;}
                                if (PlayerDAO.subcoin(player, 10000)) {
                                    player.getSession().coinBar -= 10000;
                                    Item i = ItemService.gI().createNewItem((short) 16, 10);
                                    InventoryServiceNew.gI().addItemBag(player, i);
                                    InventoryServiceNew.gI().sendItemBags(player);
                                    Service.gI().sendThongBao(player, "Bạn đã nhận quà mốc 10k, Xin chúc mừng");
                               //    Service.getInstance().sendThongBao(player, "Bạn phải có ít nhất 1 ô trống trong hành trang.");
                                }   break;
                            case 1:
                                if (player.getSession().coinBar < 20000) {
                                    Service.gI().sendThongBao(player, "Bạn không tích đủ mốc 20k zeni");
                                    return;}
                                if (PlayerDAO.subcoin(player, 20000)) {
                                    player.getSession().coinBar -= 20000;
                                    Item i = ItemService.gI().createNewItem((short) 16, 20);
                                    Item i1 = ItemService.gI().createNewItem((short) 20, 20);
                                    InventoryServiceNew.gI().addItemBag(player, i);
                                    InventoryServiceNew.gI().addItemBag(player, i1);
                                    InventoryServiceNew.gI().sendItemBags(player);
                                    Service.gI().sendThongBao(player, "Bạn đã nhận quà mốc 20k, Xin chúc mừng");
                               //    Service.getInstance().sendThongBao(player, "Bạn phải có ít nhất 1 ô trống trong hành trang.");
                                }   break;
                            case 2:
                                if (player.getSession().coinBar < 50000) {
                                    Service.gI().sendThongBao(player, "Bạn không tích đủ mốc 50k zeni");
                                    return;}
                                if (PlayerDAO.subcoin(player, 50000)) {
                                    player.getSession().coinBar -= 50000;
                                    Item i1 = ItemService.gI().createNewItem((short) 719, 1);
                                    Item i2 = ItemService.gI().createNewItem((short) 1234, 1);
                                    Item i3 = ItemService.gI().createNewItem((short) 1341, 1);
                                    i1.itemOptions.add(new Item.ItemOption(50, 30));
                                    i1.itemOptions.add(new Item.ItemOption(77, 30));
                                    i1.itemOptions.add(new Item.ItemOption(103, 15));
                                    i2.itemOptions.add(new Item.ItemOption(50, 15));
                                    i2.itemOptions.add(new Item.ItemOption(77, 15));
                                    i2.itemOptions.add(new Item.ItemOption(103, 30));
                                    i3.itemOptions.add(new Item.ItemOption(50, 15));
                                    i3.itemOptions.add(new Item.ItemOption(95, 15));
                                    i3.itemOptions.add(new Item.ItemOption(96, 25));
                                    InventoryServiceNew.gI().addItemBag(player, i1);
                                    InventoryServiceNew.gI().addItemBag(player, i2);
                                    InventoryServiceNew.gI().addItemBag(player, i3);
                                    InventoryServiceNew.gI().sendItemBags(player);
                                    Service.gI().sendThongBao(player, "Bạn đã nhận quà mốc 50k, Xin chúc mừng");
                                //    Service.getInstance().sendThongBao(player, "Bạn phải có ít nhất 1 ô trống trong hành trang.");
                                }   break;
                            case 3:
                                if (player.getSession().coinBar < 100000) {
                                    Service.gI().sendThongBao(player, "Bạn không tích đủ mốc 100k zeni");
                                    return;}
                                if (PlayerDAO.subcoin(player, 100000)) {
                                    player.getSession().coinBar -= 100000;
                                    Item i0 = ItemService.gI().createNewItem((short) 2001, 50);
                                    Item i1 = ItemService.gI().createNewItem((short) 2002, 50);
                                    Item i2 = ItemService.gI().createNewItem((short) 2003, 50);
                                    Item i3 = ItemService.gI().createNewItem((short) 14, 3);
                                    Item i4 = ItemService.gI().createNewItem((short) 15, 3);
                                    Item i5 = ItemService.gI().createNewItem((short) 16, 3);
                                    Item i6 = ItemService.gI().createNewItem((short) 17, 3);
                                    Item i7 = ItemService.gI().createNewItem((short) 18, 3);
                                    Item i8 = ItemService.gI().createNewItem((short) 20, 3);
                                    Item i9 = ItemService.gI().createNewItem((short) 19, 3);
                                    InventoryServiceNew.gI().addItemBag(player, i0);
                                    InventoryServiceNew.gI().addItemBag(player, i1);
                                    InventoryServiceNew.gI().addItemBag(player, i2);
                                    InventoryServiceNew.gI().addItemBag(player, i4);
                                    InventoryServiceNew.gI().addItemBag(player, i3);
                                    InventoryServiceNew.gI().addItemBag(player, i5);
                                    InventoryServiceNew.gI().addItemBag(player, i6);
                                    InventoryServiceNew.gI().addItemBag(player, i7);
                                    InventoryServiceNew.gI().addItemBag(player, i8);
                                    InventoryServiceNew.gI().addItemBag(player, i9);
                                    InventoryServiceNew.gI().sendItemBags(player);
                                    Service.gI().sendThongBao(player, "Bạn đã nhận quà mốc 100k, Xin chúc mừng");
                               // Service.getInstance().sendThongBao(player, "Bạn phải có ít nhất 10 ô trống trong hành trang.");
                                }   break;
                             case 4:
                                if (player.getSession().coinBar < 200000) {
                                    Service.gI().sendThongBao(player, "Bạn không tích đủ mốc 200k zeni");
                                    return;}
                                if (PlayerDAO.subcoin(player, 200000)) {
                                    player.getSession().coinBar -= 200000;
                                    Item i0 = ItemService.gI().createNewItem((short) 2091, 10);
                                    Item i1 = ItemService.gI().createNewItem((short) 2092, 10);
                                    Item i2 = ItemService.gI().createNewItem((short) 2093, 10);
                                    Item i00 = ItemService.gI().createNewItem((short) 381, 99);
                                    Item i10 = ItemService.gI().createNewItem((short) 382, 99);
                                    Item i20 = ItemService.gI().createNewItem((short) 383, 99);
                                    Item i200= ItemService.gI().createNewItem((short) 384, 99);
                                    Item i3 = ItemService.gI().createNewItem((short) 14, 10);
                                    Item i4 = ItemService.gI().createNewItem((short) 15, 10);
                                    Item i5 = ItemService.gI().createNewItem((short) 16, 10);
                                    Item i6 = ItemService.gI().createNewItem((short) 17, 10);
                                    Item i7 = ItemService.gI().createNewItem((short) 18, 10);
                                    Item i9 = ItemService.gI().createNewItem((short) 19, 10);
                                    Item i8 = ItemService.gI().createNewItem((short) 20, 10);
                                    InventoryServiceNew.gI().addItemBag(player, i0);
                                    InventoryServiceNew.gI().addItemBag(player, i1);
                                    InventoryServiceNew.gI().addItemBag(player, i2);
                                    InventoryServiceNew.gI().addItemBag(player, i00);
                                    InventoryServiceNew.gI().addItemBag(player, i10);
                                    InventoryServiceNew.gI().addItemBag(player, i20);
                                    InventoryServiceNew.gI().addItemBag(player, i200);
                                    InventoryServiceNew.gI().addItemBag(player, i4);
                                    InventoryServiceNew.gI().addItemBag(player, i3);
                                    InventoryServiceNew.gI().addItemBag(player, i5);
                                    InventoryServiceNew.gI().addItemBag(player, i6);
                                    InventoryServiceNew.gI().addItemBag(player, i7);
                                    InventoryServiceNew.gI().addItemBag(player, i8);
                                    InventoryServiceNew.gI().addItemBag(player, i9);
                                    InventoryServiceNew.gI().sendItemBags(player);
                                    Service.gI().sendThongBao(player, "Bạn đã nhận quà mốc 200k, Xin chúc mừng");
                            //    Service.getInstance().sendThongBao(player, "Bạn phải có ít nhất 10 ô trống trong hành trang.");
                                }   break; 
                            case 5:
                                if (player.getSession().coinBar < 500000) {
                                    Service.gI().sendThongBao(player, "Bạn không tích đủ mốc 500k zeni");
                                    return;}
                                if (PlayerDAO.subcoin(player, 500000)) {
                                    player.getSession().coinBar -= 500000;
                                    Item i0 = ItemService.gI().createNewItem((short) 1249, 1);
                                    Item i1 = ItemService.gI().createNewItem((short) 1234, 1);
                                    Item i4 = ItemService.gI().createNewItem((short) 1322, 1);
                                    Item i5 = ItemService.gI().createNewItem((short) 220, 2000);
                                    Item i6 = ItemService.gI().createNewItem((short) 221, 200);
                                    Item i7 = ItemService.gI().createNewItem((short) 222, 2000);
                                    Item i8 = ItemService.gI().createNewItem((short) 223, 2000);
                                    i4.itemOptions.add(new Item.ItemOption(50, 30));
                                    i4.itemOptions.add(new Item.ItemOption(77, 30));
                                    i4.itemOptions.add(new Item.ItemOption(103, 30));
                                    i0.itemOptions.add(new Item.ItemOption(0, 1200));
                                    i0.itemOptions.add(new Item.ItemOption(95, 15));
                                    i0.itemOptions.add(new Item.ItemOption(96, 15));
                                    i1.itemOptions.add(new Item.ItemOption(6, 5700));
                                    i1.itemOptions.add(new Item.ItemOption(7, 5700));
                                    i1.itemOptions.add(new Item.ItemOption(95, 15));
                                    i1.itemOptions.add(new Item.ItemOption(96, 15));
                                    InventoryServiceNew.gI().addItemBag(player, i0);
                                    InventoryServiceNew.gI().addItemBag(player, i1);
                                    InventoryServiceNew.gI().addItemBag(player, i4);
                                    InventoryServiceNew.gI().addItemBag(player, i5);
                                    InventoryServiceNew.gI().addItemBag(player, i6);
                                    InventoryServiceNew.gI().addItemBag(player, i7);
                                    InventoryServiceNew.gI().addItemBag(player, i8);
                                    InventoryServiceNew.gI().sendItemBags(player);
                                    Service.gI().sendThongBao(player, "Bạn đã nhận quà mốc 500k, Xin chúc mừng");
                             //   Service.getInstance().sendThongBao(player, "Bạn phải có ít nhất 10 ô trống trong hành trang.");
                                }   break; 
                                case 6:
                                if (player.getSession().coinBar < 1000000) {
                                    Service.gI().sendThongBao(player, "Bạn không tích đủ mốc 1000k zeni");
                                    return;}
                                if (PlayerDAO.subcoin(player, 1000000)) {
                                    player.getSession().coinBar -= 1000000;
                                    Item i0 = ItemService.gI().createNewItem((short) 1249, 1);
                                    Item i1 = ItemService.gI().createNewItem((short) 1234, 1);
                                    Item i3 = ItemService.gI().createNewItem((short) 1321, 1);
                                    Item i5 = ItemService.gI().createNewItem((short) 16, 99);
                                    Item i6 = ItemService.gI().createNewItem((short) 1345, 3);
                                    Item i9 = ItemService.gI().createNewItem((short) 1223, 1);
                                    i3.itemOptions.add(new Item.ItemOption(50, 45));
                                    i3.itemOptions.add(new Item.ItemOption(77, 45));
                                    i3.itemOptions.add(new Item.ItemOption(103, 45));
                                    i0.itemOptions.add(new Item.ItemOption(0, 1200));
                                    i0.itemOptions.add(new Item.ItemOption(95, 15));
                                    i0.itemOptions.add(new Item.ItemOption(96, 15));
                                    i1.itemOptions.add(new Item.ItemOption(6, 6700));
                                    i1.itemOptions.add(new Item.ItemOption(7, 6700));
                                    i1.itemOptions.add(new Item.ItemOption(95, 20));
                                    i1.itemOptions.add(new Item.ItemOption(96, 20));
                                    i6.itemOptions.add(new Item.ItemOption(5, 125));
                                    i6.itemOptions.add(new Item.ItemOption(36, 0));
                                    i6.itemOptions.add(new Item.ItemOption(49, 42));
                                    i6.itemOptions.add(new Item.ItemOption(30, 0));
                                    i9.itemOptions.add(new Item.ItemOption(2, 12080));
                                    i9.itemOptions.add(new Item.ItemOption(15, 15));
                                    i9.itemOptions.add(new Item.ItemOption(94, 15));
                                    InventoryServiceNew.gI().addItemBag(player, i0);
                                    InventoryServiceNew.gI().addItemBag(player, i1);
                                    InventoryServiceNew.gI().addItemBag(player, i3);
                                    InventoryServiceNew.gI().addItemBag(player, i5);
                                    InventoryServiceNew.gI().addItemBag(player, i6);
                                    InventoryServiceNew.gI().sendItemBags(player);
                                    Service.gI().sendThongBao(player, "Bạn đã nhận quà mốc 1000k, Xin chúc mừng");
                            //    Service.getInstance().sendThongBao(player, "Bạn phải có ít nhất 10 ô trống trong hành trang.");
                                   break;  
                                     
                            
                                }
                        }
                        }
                    }
                }
            }
        };
    }
    public static Npc quytoc(int mapId, int status, int cx, int cy, int tempId, int avartar) {
        return new Npc(mapId, status, cx, cy, tempId, avartar) {
            @Override
            public void openBaseMenu(Player player) {
                if (canOpenNpc(player)) {
                    if (this.mapId == 102) {
                        if (!TaskService.gI().checkDoneTaskTalkNpc(player, this)) {
                            this.createOtherMenu(player, ConstNpc.BASE_MENU, "Cậu bé muốn mua gì nào?", "Cửa hàng", "Đóng");
                        }
                    } else if (this.mapId == 46 || this.mapId == 5) {
                        this.createOtherMenu(player, ConstNpc.BASE_MENU, "Kính chào Ngài Linh thú sư!", "Cửa hàng", "Đóng");
                    }
                }
            }

            @Override
            public void confirmMenu(Player player, int select) {
                if (canOpenNpc(player)) {
                    if (this.mapId == 102) {
                        if (player.iDMark.isBaseMenu()) {
                            if (select == 0) {
                                ShopServiceNew.gI().opendShop(player, "BUNMA_FUTURE", true);
                            }
                        }
                    } else if (this.mapId == 46 || this.mapId == 5) {
                        if (player.iDMark.isBaseMenu()) {
                            if (select == 0) {
                                ShopServiceNew.gI().opendShop(player, "quytoc", true);
                            }
                        }
                    }
                }
            }
        };
    }
public static Npc vados(int mapId, int status, int cx, int cy, int tempId, int avartar) {
        return new Npc(mapId, status, cx, cy, tempId, avartar) {
            public void chatWithNpc(Player player) {
                String[] chat = {
                    "TOP Máy Chủ",
                };
                Timer timer = new Timer();
                timer.scheduleAtFixedRate(new TimerTask() {
                    int index = 0;
                    @Override
                    public void run() {
                        npcChat(player, chat[index]);
                        index = (index + 1) % chat.length;
                    }
                }, 1000, 1000);
            }
            @Override
            public void openBaseMenu(Player player) {
                 chatWithNpc(player);
                if (canOpenNpc(player)) {
                    createOtherMenu(player, ConstNpc.BASE_MENU,
                            "|2|Ta Vừa Hắc Mắp Xêm Được T0p Của Toàn Server\b|7|Mi Muống Xem Tóp Gì?",
                            "Tóp Sức Mạnh", "Top Nhiệm Vụ", "Top Sức Đánh", "Top Hồng Ngọc","Top Nạp","Đóng");
                }
            }
            @Override
            public void confirmMenu(Player player, int select) {
                if (canOpenNpc(player)) {
                    switch (this.mapId) {
                        case 5:
                            switch (player.iDMark.getIndexMenu()) {
                                case ConstNpc.BASE_MENU:
                                    if (select == 0) {
                                        Service.gI().showListTop(player, Manager.topSM);
                                        break;
                                    }// này à yes
                                    if (select == 1) {
                                        Service.gI().showListTop(player, Manager.topNV);
                                        break;
                                    }
                                    if (select == 2) {
                                        Service.gI().showListTop(player, Manager.topSD);
                                        break;
                                    }
                                    if (select == 3) {
                                        Service.gI().showListTop(player, Manager.tophongngoc);
                                        break;
                                    }    
                                    if (select == 4) {
                                        Service.gI().showListTop(player, Manager.topNAP);
                                        break;                                 
                                    }
                                  //  if (select == 5) {
                               //         Service.gI().showListTop(player, Manager.topCS);
                              //      }
                             //       break;
                            }
                            break;
                    }
                }
            }
        };
    }

   public static Npc gokuSSJ_1(int mapId, int status, int cx, int cy, int tempId, int avartar) {
        return new Npc(mapId, status, cx, cy, tempId, avartar) {
            @Override
            public void openBaseMenu(Player player) {
                if (canOpenNpc(player)) {
                    if (this.mapId == 80) {
                        this.createOtherMenu(player, ConstNpc.BASE_MENU, "Xin chào, tôi có thể giúp gì cho cậu?", "Tới hành tinh\nYardart", "Từ chối");
                    } else if (this.mapId == 131) {
                        this.createOtherMenu(player, ConstNpc.BASE_MENU, "Xin chào, tôi có thể giúp gì cho cậu?", "Quay về", "Từ chối");
                    } else {
                        super.openBaseMenu(player);
                    }
                }
            }
            @Override
            public void confirmMenu(Player player, int select) {
                if (canOpenNpc(player)) {
                    switch (player.iDMark.getIndexMenu()) {
                        case ConstNpc.BASE_MENU:
                            if (this.mapId == 131) {
                                if (select == 0) {
                                    ChangeMapService.gI().changeMapBySpaceShip(player, 80, -1, 870);
                                break;
                            }
                        }
                    }
                    if (this.mapId == 80) {
                        if (player.iDMark.isBaseMenu()) {
                            switch (select) {
                                case 0:
                                    if (player.getSession().player.nPoint.power >= 18000000000L) {
                                    ChangeMapService.gI().changeMapBySpaceShip(player, 131, -1, 312);
                                } else {
                                    this.npcChat(player, "Bạn chưa đủ 18 tỷ sức mạnh để vào");
                                }
                                break;   
                        }
                    }
                }
            }
        }
    };
  }
    public static Npc gokuSSJ_2(int mapId, int status, int cx, int cy, int tempId, int avartar) {
        return new Npc(mapId, status, cx, cy, tempId, avartar) {
            @Override
            public void openBaseMenu(Player player) {
                new Thread(() -> {
                try {
        while (true) {
            Thread.sleep(5000);
        new Thread(() -> {
                try {
                    Thread.sleep(1000);
                    this.npcChat(player, "Up Yardart Đi Em");
                } catch (Exception e) {
                }
            }).start();}
        } catch (Exception e) {
                }
            }).start();
                if (canOpenNpc(player)) {{
                    this.createOtherMenu(player, ConstNpc.BASE_MENU, "Bạn đang có 0 bí kiếp.\n"
                                    + "Hãy kiếm đủ 10000 bí kiếp tôi sẽ dạy bạn cách dịch chuyển tức thời của người Yardart", "Học dịch\nchuyển", "Đóng");
                        }
                    try {
                        Item biKiep = InventoryServiceNew.gI().findItem(player.inventory.itemsBag, 590);
                        if (biKiep != null) {
                            this.createOtherMenu(player, ConstNpc.BASE_MENU, "Bạn đang có " + biKiep.quantity + " bí kiếp.\n"
                                    + "Hãy kiếm đủ 10000 bí kiếp tôi sẽ dạy bạn cách dịch chuyển tức thời của người Yardart", "Học dịch\nchuyển", "Đóng");
                        }

                    } catch (Exception ex) {
                        ex.printStackTrace();

                    }
                }
            }
            @Override
            public void confirmMenu(Player player, int select) {
                if (canOpenNpc(player)) {
                    try {
                        Item biKiep = InventoryServiceNew.gI().findItem(player.inventory.itemsBag, 590);
                        if (biKiep != null) {
                            if (biKiep.quantity >= 10000 && InventoryServiceNew.gI().getCountEmptyBag(player) > 0) {
                                Item yardart = ItemService.gI().createNewItem((short) (player.gender + 592));
                                yardart.itemOptions.add(new Item.ItemOption(80, 15));
                                yardart.itemOptions.add(new Item.ItemOption(81, 15));
                                yardart.itemOptions.add(new Item.ItemOption(33, 1));                             
                                InventoryServiceNew.gI().addItemBag(player, yardart);
                                InventoryServiceNew.gI().subQuantityItemsBag(player, biKiep, 10000);
                                InventoryServiceNew.gI().sendItemBags(player);
                                Service.gI().sendThongBao(player, "Bạn vừa nhận được trang phục tộc Yardart");
                            }
                            Service.gI().sendThongBao(player, "Bạn không đủ bí kíp");
                        }
                    } catch (Exception ex) {

                    }
                }
            }
        };
    }
    public static Npc Nit(int mapId, int status, int cx, int cy, int tempId, int avartar) {
        return new Npc(mapId, status, cx, cy, tempId, avartar) {
            @Override
            public void openBaseMenu(Player player) {
                if (canOpenNpc(player)) {
                    createOtherMenu(player, ConstNpc.BASE_MENU,
                            "|7|Xin chào\n|1|ta có một số vật phẩm đặt biệt cậu có muốn xem không\n|5|đây là cửa hàng hỗ trợ giá siêu rẻ\n|1|Cảm Ơn Quý Khách\n|3|I Need You",
                            "Cửa Hàng\nHỗ Trợ","Đóng");//"Cải trang","Khiêu chiến","Map\nUp Mảnh Vỡ");//,"Phụ kiện", "Vật phẩm");
                }
            }

            @Override
            public void confirmMenu(Player player, int select) {
                if (canOpenNpc(player)) {
                    if (this.mapId == 21 || this.mapId == 22 || this.mapId == 23) {
                        if (player.iDMark.isBaseMenu()) {
                            switch (select) {
                            //    case 0: //shop
                            //        ShopServiceNew.gI().opendShop(player, "SANTA", false);
                            //        break;
                                case 0:
                                    ShopServiceNew.gI().opendShop(player, "CAI_TRANG", false);                                                                      
                                    break;
                             //   case 2:
                              //      ShopServiceNew.gI().opendShop(player, "PHU_KIEN", false);                                                                      
                              //      break;
                        //            case 2:
                        //        if(player.getSession().player.nPoint.power < 10000000000L){
                        //        Service.gI().sendThongBao(player, "Cần Có Sức Mạnh Là 10 Tỉ");}
                        //        else if(player.getSession().player.inventory.gold < 300000000){
                        //        Service.gI().sendThongBao(player, "Cần 300tr Vàng");
                        //    }   else {player.nPoint.power -=10000000000L ;
                        //    player.getSession().player.inventory.gold -= 300000000;
                       ///     player.nPoint.teleport=true;
                        //    player.idAura=95;
                         //   player.name="[ Hủy Diệt]\n"+player.name;
                        //    Service.gI().player(player);
                        //    Service.gI().Send_Caitrang(player);
                        //    Service.gI().sendFlagBag(player);
                        //    Zone zone = player.zone;
                        //    ChangeMapService.gI().changeMap(player, zone, player.location.x, player.location.y);
//                            ChangeMapService.gI().changeMapBySpaceShip(player, 5, -1, 495);
                        //                Service.gI().changeFlag(player, 8);
                        //    PlayerService.gI().changeAndSendTypePK(player, ConstPlayer.PK_ALL);
                        //    new Thread(() -> {
                         //   try {
                         //   Thread.sleep(240000);
                         //       } catch (Exception e) {
                        //        }
                        //    Client.gI().kickSession (player.getSession());
                        //    }).start();
                        //    
                        //    Service.gI().sendThongBaoAllPlayer("Kẻ "+player.name+" Đang Ở "+ player.zone.map.mapName + " Khu " + player.zone.zoneId);
                        //            break;
                       //         }
                        //     case 3:
                        //         if (player.getSession().player.nPoint.power >= 80000000000L && player.inventory.gold > COST_HD) {
                        //        player.inventory.gold -= COST_HD;
                        //        Service.gI().sendMoney(player);
                         //           ChangeMapService.gI().changeMapBySpaceShip(player, 156, -1, 90);
                         //           } else {
                         //       this.npcChat(player, "Bạn chưa đủ 80 tỷ sức mạnh, 50 triệu vàng");
                         //           break;   
                         //        }
                            
                            //    case 3:
                            //        ShopServiceNew.gI().opendShop(player, "VAT_PHAM", false);                                                                      
                            //        break;
                            //    case 9:
                            //        ShopServiceNew.gI().opendShop(player, "SANTA_EVENT", false);                                                                      
                            //        break;
                            //    case 4:
                            //        this.createOtherMenu(player, 1, "|7|Số tiền của bạn còn : " + player.getSession().vnd + " VND\n"
                            //                + "Tỉ lệ quy đổi là 1000VND = 4 thỏi vàng\n" + "1000VND = 2000 hồng ngọc\n Ví Dụ Có 10.000VND Thì Nhập Vào Là 10\nCứ Quy Đổi Kể Cả 1 Thỏi Vàng Là Được Kích Hoạt Tài Khoản\nQuy Đổi Lỗi Thì Quy Đổi Lại Lần 2", "Quy đổi\n Thỏi vàng", "Quy Đổi\nHồng Ngọc", "Mở thành viên");
                            //        break;
                            }
                        }
                        else if (player.iDMark.getIndexMenu() == 1){
                            switch (select) {
                                case 0:
                                    Input.gI().createFormQDTV(player);
                                    break;
                                case 1:
                                    Input.gI().createFormQDHN(player);
                                    break;
                                case 2:
                                    if (player.getSession().vnd >= 20000){
                                        if (player.kichhoat == 0){
                                            player.kichhoat = 1;
                                            PlayerDAO.subvnd(player, 20000);
                                            player.getSession().vnd -= 20000;
                                            Service.gI().sendThongBao(player, "Kích hoạt thành công");
                                        } else {
                                            Service.getInstance().sendThongBao(player, "Đã kích hoạt thành viên rồi");
                                        }
                                    } else {
                                        Service.getInstance().sendThongBao(player, "Bạn không đủ 20k");
                                    }
                                    break;
                            }
                        }
                    }
                }
            }
        };
    }

    ///////////////////////////////////////////NPC Ký Gửi///////////////////////////////////////////
    private static Npc kyGui(int mapId, int status, int cx, int cy, int tempId, int avartar) {
        return new Npc(mapId, status, cx, cy, tempId, avartar) {

            @Override
            public void openBaseMenu(Player player) {
                if (canOpenNpc(player)) {
                    createOtherMenu(player, 0, "Cửa hàng chúng tôi chuyên mua bán hàng hiệu, hàng độc, cảm ơn bạn đã ghé thăm.", "Hướng\ndẫn\nthêm", "Mua bán\nKý gửi", "Từ chối");
                }
            }

            @Override
            public void confirmMenu(Player pl, int select) {
                if (canOpenNpc(pl)) {
                    switch (select) {
                        case 0:
                            Service.getInstance().sendPopUpMultiLine(pl, tempId, avartar, "Cửa hàng chuyên nhận ký gửi mua bán vật phẩm\bChỉ với 5 hồng ngọc\bGiá trị ký gửi 10k-200Tr vàng hoặc 2-2k ngọc\bMột người bán, vạn người mua, mại dô, mại dô");
                            break;
                        case 1:
                            ShopKyGuiService.gI().openShopKyGui(pl);
                            break;
                    }
                }
            }
        };
    }
        public static Npc Pic2Mai(int mapId, int status, int cx, int cy, int tempId, int avartar) {
        return new Npc(mapId, status, cx, cy, tempId, avartar) {
            @Override
            public void openBaseMenu(Player player) {
                if (this.mapId == 219 ) {
                    this.createOtherMenu(player, ConstNpc.BASE_MENU,
                            "|7|Hiện Tại Bạn Đang Đang Có Số Điểm Sự Kiện Là " + player.NguHanhSonPoint + "\nĐể Đổi Hộp Qùa Sự Kiện Hè Bạn Cần 99 San Hô + 99 Bình Nước + 99 Khúc Gỗ + 1 Que Diêm + 5 Thỏi Vàng. Bạn Sẽ Nhận Được Hộp Qùa VIP Và Điểm Sự Kiện. Ngoài Ra Bạn Có Thể Sử Dụng X99 Nguyên Liệu Mỗi Loại Kết Hợp Với 1 Que Diêm Để Nhận Được Hộp Qùa Thường Và Điểm Sự Kiện.", "Đổi \n Hộp Qùa VIP", "Đổi \n Hộp Qùa VIP");

                }
            }
            @Override
            public void confirmMenu(Player player, int select) {
                if (canOpenNpc(player)) {
                    if (this.mapId == 219 ) {
                        if (player.iDMark.isBaseMenu()) {
                            switch (select) {
                                case 0: {
                                    Item sanho = null;
                                    Item binhnuoc = null;
                                    Item khucgo = null;
                                    Item quediem = null;
                                    Item thoivang = null;
                                    

                                    try {
                                        sanho = InventoryServiceNew.gI().findItemBag(player, 1251);
                                        binhnuoc = InventoryServiceNew.gI().findItemBag(player, 1252);
                                        khucgo = InventoryServiceNew.gI().findItemBag(player, 1253);
                                        quediem = InventoryServiceNew.gI().findItemBag(player, 1254);
                                        thoivang = InventoryServiceNew.gI().findItemBag(player, 457);
                                        
                                    } catch (Exception e) {
//                                        throw new RuntimeException(e);
                                    }
                                    if (sanho == null || sanho.quantity < 99 || binhnuoc == null || binhnuoc.quantity < 99 || khucgo == null || khucgo.quantity < 99 || quediem == null || quediem.quantity < 1) {
                                        this.npcChat(player, "Bạn không đủ nguyên liệu để nấu bánh");
                                    } else if (thoivang == null || thoivang.quantity < 5) {
                                        this.npcChat(player, "Bạn không đủ thỏi vàng");
                                    } else if (InventoryServiceNew.gI().getCountEmptyBag(player) == 0) {
                                        this.npcChat(player, "Hành trang của bạn không đủ chỗ trống");
                                    } else {
                                        InventoryServiceNew.gI().subQuantityItemsBag(player, sanho, 99);
                                        InventoryServiceNew.gI().subQuantityItemsBag(player, binhnuoc, 99);
                                        InventoryServiceNew.gI().subQuantityItemsBag(player, khucgo, 99);
                                        InventoryServiceNew.gI().subQuantityItemsBag(player, quediem, 99);
                                        InventoryServiceNew.gI().subQuantityItemsBag(player, thoivang, 5);
                                        
                                        Service.getInstance().sendMoney(player);
                                        Item trungLinhThu = ItemService.gI().createNewItem((short) 1255);
                                        player.NguHanhSonPoint += 1;
                                        InventoryServiceNew.gI().addItemBag(player, trungLinhThu);
                                        InventoryServiceNew.gI().sendItemBags(player);
                                        this.npcChat(player, "Bạn nhận được 1 Hộp Qùa VIP");
                                    }
                                    break;
                                }

                                case 1: {
                                    Item sanho = null;
                                    Item binhnuoc = null;
                                    Item khucgo = null;
                                    Item quediem = null;
                                    

                                    try {
                                        sanho = InventoryServiceNew.gI().findItemBag(player, 1251);
                                        binhnuoc = InventoryServiceNew.gI().findItemBag(player, 1252);
                                        khucgo = InventoryServiceNew.gI().findItemBag(player, 1253);
                                        quediem = InventoryServiceNew.gI().findItemBag(player, 1254);
                                        
                                        
                                    } catch (Exception e) {
//                                        throw new RuntimeException(e);
                                    }
                                    if (sanho == null || sanho.quantity < 99 || binhnuoc == null || binhnuoc.quantity < 99 || khucgo == null || khucgo.quantity < 99 || quediem == null || quediem.quantity < 1) {
                                        this.npcChat(player, "Bạn không đủ nguyên liệu để nấu bánh");
                                    } else if (InventoryServiceNew.gI().getCountEmptyBag(player) == 0) {
                                        this.npcChat(player, "Hành trang của bạn không đủ chỗ trống");
                                    } else {
                                        InventoryServiceNew.gI().subQuantityItemsBag(player, sanho, 99);
                                        InventoryServiceNew.gI().subQuantityItemsBag(player, khucgo, 99);
                                        InventoryServiceNew.gI().subQuantityItemsBag(player, quediem, 1);
                                        Service.getInstance().sendMoney(player);
                                        Item trungLinhThu = ItemService.gI().createNewItem((short) 1255);
                                        player.NguHanhSonPoint += 1;
                                        InventoryServiceNew.gI().addItemBag(player, trungLinhThu);
                                        InventoryServiceNew.gI().sendItemBags(player);
                                        InventoryServiceNew.gI().subQuantityItemsBag(player, binhnuoc, 99);
                                        InventoryServiceNew.gI().subQuantityItemsBag(player, khucgo, 99);
                                        InventoryServiceNew.gI().subQuantityItemsBag(player, quediem, 1);
                                        Service.getInstance().sendMoney(player);
                                        InventoryServiceNew.gI().addItemBag(player, trungLinhThu);
                                        InventoryServiceNew.gI().sendItemBags(player);
                                        this.npcChat(player, "Bạn nhận được 1 Hộp Qùa Thường");
                                    }
                                    break;
                                }
                            }
                        }
                    }
                }
            }
        };
    }

    public static Npc createNPC(int mapId, int status, int cx, int cy, int tempId) {
        int avatar = Manager.NPC_TEMPLATES.get(tempId).avatar;
        try {
            switch (tempId) {
                case ConstNpc.TRUNG_LINH_THU:
                    return trungLinhThu(mapId, status, cx, cy, tempId, avatar);
                case ConstNpc.POTAGE:
                    return poTaGe(mapId, status, cx, cy, tempId, avatar);
                case ConstNpc.QUY_LAO_KAME:
                    return quyLaoKame(mapId, status, cx, cy, tempId, avatar);
                 case ConstNpc.CUA_HANG_KY_GUI:
                    return kyGui(mapId, status, cx, cy, tempId, avatar);
                case ConstNpc.TRUONG_LAO_GURU:
                    return truongLaoGuru(mapId, status, cx, cy, tempId, avatar);
                case ConstNpc.VUA_VEGETA:
                    return vuaVegeta(mapId, status, cx, cy, tempId, avatar);
                    case ConstNpc.THO_DAI_CA:
                    return thodaika(mapId, status, cx, cy, tempId, avatar);
                case ConstNpc.ONG_GOHAN:
                case ConstNpc.ONG_MOORI:
                case ConstNpc.ONG_PARAGUS:
                    return ongGohan_ongMoori_ongParagus(mapId, status, cx, cy, tempId, avatar);
                case ConstNpc.BUNMA:
                    return bulmaQK(mapId, status, cx, cy, tempId, avatar);
                    case ConstNpc.nit:
                    return Nit(mapId, status, cx, cy, tempId, avatar);
                case ConstNpc.DENDE:
                    return dende(mapId, status, cx, cy, tempId, avatar);
                case ConstNpc.APPULE:
                    return appule(mapId, status, cx, cy, tempId, avatar);
                case ConstNpc.DR_DRIEF:
                    return drDrief(mapId, status, cx, cy, tempId, avatar);
                case ConstNpc.CARGO:
                    return cargo(mapId, status, cx, cy, tempId, avatar);
                 case ConstNpc.kichi:
                    return Kichi(mapId, status, cx, cy, tempId, avatar);   
                case ConstNpc.CUI:
                    return cui(mapId, status, cx, cy, tempId, avatar);
                case ConstNpc.SANTA:
                    return santa(mapId, status, cx, cy, tempId, avatar);
                case ConstNpc.URON:
                    return uron(mapId, status, cx, cy, tempId, avatar);
                case ConstNpc.BA_HAT_MIT:
                    return baHatMit(mapId, status, cx, cy, tempId, avatar);
                case ConstNpc.RUONG_DO:
                    return ruongDo(mapId, status, cx, cy, tempId, avatar);
                case ConstNpc.DAU_THAN:
                    return dauThan(mapId, status, cx, cy, tempId, avatar);
                case ConstNpc.CALICK:
                    return calick(mapId, status, cx, cy, tempId, avatar);
                case ConstNpc.JACO:
                    return jaco(mapId, status, cx, cy, tempId, avatar);
                case ConstNpc.THUONG_DE:
                    return thuongDe(mapId, status, cx, cy, tempId, avatar);
                case ConstNpc.VADOS:
                    return vados(mapId, status, cx, cy, tempId, avatar);
                case ConstNpc.THAN_VU_TRU:
                    return thanVuTru(mapId, status, cx, cy, tempId, avatar);
                case ConstNpc.KIBIT:
                    return kibit(mapId, status, cx, cy, tempId, avatar);
                case ConstNpc.OSIN:
                    return osin(mapId, status, cx, cy, tempId, avatar);
                case ConstNpc.LY_TIEU_NUONG:
                    return npclytieunuong54(mapId, status, cx, cy, tempId, avatar);
                case ConstNpc.LINH_CANH:
                    return linhCanh(mapId, status, cx, cy, tempId, avatar);
                case ConstNpc.GIUMA_DAU_BO:
                    return giuma(mapId, status, cx, cy, tempId, avatar);
                case ConstNpc.QUA_TRUNG:
                    return quaTrung(mapId, status, cx, cy, tempId, avatar);
                case ConstNpc.QUOC_VUONG:
                    return quocVuong(mapId, status, cx, cy, tempId, avatar);
                case ConstNpc.BUNMA_TL:
                    return bulmaTL(mapId, status, cx, cy, tempId, avatar);
                case ConstNpc.GHI_DANH:
                    return GhiDanh(mapId, status, cx, cy, tempId, avatar);
                case ConstNpc.Monaito:
                    return monaito(mapId, status, cx, cy, tempId, avatar);
                case ConstNpc.RONG_OMEGA:
                    return rongOmega(mapId, status, cx, cy, tempId, avatar);
                case ConstNpc.RONG_1S:
                case ConstNpc.RONG_2S:
                case ConstNpc.RONG_3S:
                case ConstNpc.RONG_4S:
                case ConstNpc.RONG_5S:
                case ConstNpc.RONG_6S:
                case ConstNpc.RONG_7S:
                    return rong1_to_7s(mapId, status, cx, cy, tempId, avatar);
                case ConstNpc.TO_SU_KAIO:
                    return tosukaio(mapId, status, cx, cy, tempId, avatar);
                case ConstNpc.BILL:
                    return bill(mapId, status, cx, cy, tempId, avatar);
                case ConstNpc.NPC_64:
                    return npcThienSu64(mapId, status, cx, cy, tempId, avatar); 
                case ConstNpc.BO_MONG:
                    return boMong(mapId, status, cx, cy, tempId, avatar);
                case ConstNpc.THAN_MEO_KARIN:
                    return karin(mapId, status, cx, cy, tempId, avatar);
                    case ConstNpc.BROOK:
                    return Brook(mapId, status, cx, cy, tempId, avatar);
                case ConstNpc.GOKU_SSJ:
                    return gokuSSJ_1(mapId, status, cx, cy, tempId, avatar);
                case ConstNpc.CHOPPER:
                    return chopper(mapId, status, cx, cy, tempId, avatar);
                case ConstNpc.MR_POPO:
                    return mrpopo(mapId, status, cx, cy, tempId, avatar);
                case ConstNpc.GOKU_SSJ_:
                    return gokuSSJ_2(mapId, status, cx, cy, tempId, avatar);
                    case ConstNpc.mai:
                    return Mai(mapId, status, cx, cy, tempId, avatar);  
                case ConstNpc.DUONG_TANG:
                    return duongtank(mapId, status, cx, cy, tempId, avatar);
                case ConstNpc.WHIS:
                    return whis(mapId, status, cx, cy, tempId, avatar);
                case ConstNpc.TAPION:
                    return Tapion(mapId, status, cx, cy, tempId, avatar); 
                case ConstNpc.npc92:
                    return npc92(mapId, status, cx, cy, tempId, avatar);
                case ConstNpc.UNKOWN:
                    return unkonw(mapId, status, cx, cy, tempId, avatar);
                    case ConstNpc.anime:
                    return Anime(mapId, status, cx, cy, tempId, avatar);
                case ConstNpc.Pic:
                    return Pic2Mai(mapId, status, cx, cy, tempId, avatar);
                default:
                    return new Npc(mapId, status, cx, cy, tempId, avatar) {
                        @Override
                        public void openBaseMenu(Player player) {
                            if (canOpenNpc(player)) {
                                super.openBaseMenu(player);
                            }
                        }

                        @Override
                        public void confirmMenu(Player player, int select) {
                            if (canOpenNpc(player)) {
//                                ShopService.gI().openShopNormal(player, this, ConstNpc.SHOP_BUNMA_TL_0, 0, player.gender);
                            }
                        }
                    };
            }
        } catch (Exception e) {
            Logger.logException(NpcFactory.class, e, "Lỗi load npc");
            return null;
        }
    }

    //girlbeo-mark
    public static void createNpcRongThieng() {
        Npc npc = new Npc(-1, -1, -1, -1, ConstNpc.RONG_THIENG, -1) {
            @Override
            public void confirmMenu(Player player, int select) {
                switch (player.iDMark.getIndexMenu()) {
                    case ConstNpc.IGNORE_MENU:

                        break;
                    case ConstNpc.SHENRON_CONFIRM:
                        if (select == 0) {
                            SummonDragon.gI().confirmWish();
                        } else if (select == 1) {
                            SummonDragon.gI().reOpenShenronWishes(player);
                        }
                        break;
                    case ConstNpc.SHENRON_1_1:
                        if (player.iDMark.getIndexMenu() == ConstNpc.SHENRON_1_1 && select == SHENRON_1_STAR_WISHES_1.length - 1) {
                            NpcService.gI().createMenuRongThieng(player, ConstNpc.SHENRON_1_2, SHENRON_SAY, SHENRON_1_STAR_WISHES_2);
                            break;
                        }
                    case ConstNpc.SHENRON_1_2:
                        if (player.iDMark.getIndexMenu() == ConstNpc.SHENRON_1_2 && select == SHENRON_1_STAR_WISHES_2.length - 1) {
                            NpcService.gI().createMenuRongThieng(player, ConstNpc.SHENRON_1_1, SHENRON_SAY, SHENRON_1_STAR_WISHES_1);
                            break;
                        }
                    default:
                        SummonDragon.gI().showConfirmShenron(player, player.iDMark.getIndexMenu(), (byte) select);
                        break;
                }
            }
        };
    }
    

    public static void createNpcConMeo() {
        Npc npc;
        npc = new Npc(-1, -1, -1, -1, ConstNpc.CON_MEO, 351) {
            @Override
            public void confirmMenu(Player player, int select) {
                switch (player.iDMark.getIndexMenu()) {
                    case ConstNpc.IGNORE_MENU:

                        break;
                    case ConstNpc.MAKE_MATCH_PVP: //                        if (player.kichhoat == 1) 
                    {
                        if (Maintenance.isRuning) {
                            break;
                        }
                        PVPService.gI().sendInvitePVP(player, (byte) select);
                        break;
                    }
//                        else {
//                            Service.getInstance().sendThongBao(player, "|5|VUI LÒNG KÍCH HOẠT TÀI KHOẢN TẠI\n|7|NROGOD.COM\n|5|ĐỂ MỞ KHÓA TÍNH NĂNG");
//                            break;
//                        }
                    case ConstNpc.MAKE_FRIEND:
                        if (select == 0) {
                            Object playerId = PLAYERID_OBJECT.get(player.id);
                            if (playerId != null) {
                                FriendAndEnemyService.gI().acceptMakeFriend(player,
                                        Integer.parseInt(String.valueOf(playerId)));
                            }
                        }
                        break;
                        case ConstNpc.RUONG_GO:
                        int size = player.textRuongGo.size();
                        if (size > 0) {
                            String menuselect = "OK [" + (size - 1) + "]";
                            if (size == 1) {
                                menuselect = "OK";
                            }
                            NpcService.gI().createMenuConMeo(player, ConstNpc.RUONG_GO, -1, player.textRuongGo.get(size - 1), menuselect);
                            player.textRuongGo.remove(size - 1);
                        }
                        break;
                    case ConstNpc.REVENGE:
                        if (select == 0) {
                            PVPService.gI().acceptRevenge(player);
                        }
                        break;
                        
                     
                    case ConstNpc.TUTORIAL_SUMMON_DRAGON:
                        if (select == 0) {
                            NpcService.gI().createTutorial(player, -1, SummonDragon.SUMMON_SHENRON_TUTORIAL);
                        }
                        break;
                    case ConstNpc.SUMMON_SHENRON:
                        if (select == 0) {
                            NpcService.gI().createTutorial(player, -1, SummonDragon.SUMMON_SHENRON_TUTORIAL);
                        } else if (select == 1) {
                            SummonDragon.gI().summonShenron(player);
                        }
                        break;
                       
                    case ConstNpc.MENU_OPTION_USE_ITEM1105:
                        if (select == 0) {
                            IntrinsicService.gI().sattd(player);
                        } else if (select == 1) {
                            IntrinsicService.gI().satnm(player);
                        } else if (select == 2) {
                            IntrinsicService.gI().setxd(player);
                        }
                        break;
                    case ConstNpc.MENU_OPTION_USE_ITEM2000:
                    case ConstNpc.MENU_OPTION_USE_ITEM2001:
                    case ConstNpc.MENU_OPTION_USE_ITEM2002:
                        try {
                            ItemService.gI().OpenSKH(player, player.iDMark.getIndexMenu(), select);
                        } catch (Exception e) {
                            Logger.error("Lỗi mở hộp quà");
                        }
                         
                
                        break;
                    case ConstNpc.MENU_OPTION_USE_ITEM2003:
                    case ConstNpc.MENU_OPTION_USE_ITEM2004:
                   case ConstNpc.MENU_OPTION_USE_ITEM2005:
                        try {
                            ItemService.gI().OpenDHD(player, player.iDMark.getIndexMenu(), select);
                        } catch (Exception e) {
                            Logger.error("Lỗi mở hộp quà");
                        }
                        break;
                    case ConstNpc.MENU_OPTION_USE_ITEM736:
                        try {
                            ItemService.gI().OpenDHD(player, player.iDMark.getIndexMenu(), select);
                        } catch (Exception e) {
                            Logger.error("Lỗi mở hộp quà");
                        }
                        break;
                    case ConstNpc.INTRINSIC:
                        if (select == 0) {
                            IntrinsicService.gI().showAllIntrinsic(player);
                        } else if (select == 1) {
                            IntrinsicService.gI().showConfirmOpen(player);
                        } else if (select == 2) {
                            IntrinsicService.gI().showConfirmOpenVip(player);
                        }
                        break;
                    case ConstNpc.CONFIRM_OPEN_INTRINSIC:
                        if (select == 0) {
                            IntrinsicService.gI().open(player);
                        }
                        break;
                    case ConstNpc.CONFIRM_OPEN_INTRINSIC_VIP:
                        if (select == 0) {
                            IntrinsicService.gI().openVip(player);
                        }
                        break;
                    case ConstNpc.CONFIRM_LEAVE_CLAN:
                        if (select == 0) {
                            ClanService.gI().leaveClan(player);
                        }
                        break;
                    case ConstNpc.CONFIRM_NHUONG_PC:
                        if (select == 0) {
                            ClanService.gI().phongPc(player, (int) PLAYERID_OBJECT.get(player.id));
                        }
                        break;
                    case ConstNpc.BAN_PLAYER:
                        if (select == 0) {
                            PlayerService.gI().banPlayer((Player) PLAYERID_OBJECT.get(player.id));
                            Service.getInstance().sendThongBao(player, "Ban người chơi " + ((Player) PLAYERID_OBJECT.get(player.id)).name + " thành công");
                        }
                        break;

                    case ConstNpc.BUFF_PET:
                        if (select == 0) {
                            Player pl = (Player) PLAYERID_OBJECT.get(player.id);
                            if (pl.pet == null) {
                                PetService.gI().createNormalPet(pl);
                                Service.getInstance().sendThongBao(player, "Phát đệ tử cho " + ((Player) PLAYERID_OBJECT.get(player.id)).name + " thành công");
                            }
                        }
                        break;
                    case ConstNpc.MENU_ADMIN:
                        switch (select) {
                            case 0:
                                for (int i = 14; i <= 20; i++) {
                                    Item item = ItemService.gI().createNewItem((short) i);
                                    InventoryServiceNew.gI().addItemBag(player, item);
                                }
                                InventoryServiceNew.gI().sendItemBags(player);
                                break;
                            case 1:
                                if (player.pet == null) {
                                    PetService.gI().createNormalPet(player);
                                } else {
                                    if (player.pet.typePet == 1) {
                                        PetService.gI().changePicPet(player);
                                    } else if (player.pet.typePet == 2) {
                                        PetService.gI().changeMabuPet(player);
                                    }
                                    PetService.gI().changeBerusPet(player);
                                }
                                break;
                            case 2:
                                if (player.isAdmin()) {
                                    System.out.println(player.name);
//                                PlayerService.gI().baoTri();
                             Maintenance.gI().start(15);
                             System.out.println(player.name);
                                }
                                break;
                            case 3:
                                Input.gI().createFormFindPlayer(player);
                                break;
                            case 4:
                                BossManager.gI().showListBoss(player);
                                break;
                            case 5:
                                MaQuaTangManager.gI().checkInfomationGiftCode(player);
                                break;
                            case 6:
                                Input.gI().createFormChangeTNSMServer(player);
                                break;
                            case 7:
                                Input.gI().createFormGiveItem(player);
                                break;
                            case 8:
                                Input.gI().createFormSendItem(player);
                                break;
                            case 9:
                                this.createOtherMenu(player, ConstNpc.CALL_BOSS,
                                        "Chọn Boss?", "Full Cụm\nANDROID", "BLACK", "BROLY", "Cụm\nCell",
                                        "Cụm\nDoanh trại", "DOREMON", "FIDE", "FIDE\nBlack", "Cụm\nGINYU", "Cụm\nNAPPA", "NGỤC\nTÙ");
                                break;
                        }
                        break;
                    case ConstNpc.CALL_BOSS:
                        switch (select) {
                            case 0:
                                BossManager.gI().createBoss(BossID.ANDROID_13);
                                BossManager.gI().createBoss(BossID.ANDROID_14);
                                BossManager.gI().createBoss(BossID.ANDROID_15);
                                BossManager.gI().createBoss(BossID.ANDROID_19);
                                BossManager.gI().createBoss(BossID.DR_KORE);
                                BossManager.gI().createBoss(BossID.KING_KONG);
                                BossManager.gI().createBoss(BossID.PIC);
                                BossManager.gI().createBoss(BossID.POC);
                                break;
                            case 1:
                                BossManager.gI().createBoss(BossID.BLACK);
                                break;
                            case 2:
                                BossManager.gI().createBoss(BossID.BROLY);
                                break;
                            case 3:
                                BossManager.gI().createBoss(BossID.SIEU_BO_HUNG);
                                BossManager.gI().createBoss(BossID.XEN_BO_HUNG);
                                break;
                            case 4:
                                Service.getInstance().sendThongBao(player, "Không có boss");
                                break;
                            case 5:
                                BossManager.gI().createBoss(BossID.CHAIEN);
                                BossManager.gI().createBoss(BossID.XEKO);
                                BossManager.gI().createBoss(BossID.XUKA);
                                BossManager.gI().createBoss(BossID.NOBITA);
                                BossManager.gI().createBoss(BossID.DORAEMON);
                                break;
                            case 6:
                                BossManager.gI().createBoss(BossID.FIDE);
                            case 7:
                                BossManager.gI().createBoss(BossID.FIDE_ROBOT);
                                BossManager.gI().createBoss(BossID.VUA_COLD);
                                break;
                            case 8:
                                BossManager.gI().createBoss(BossID.SO_1);
                                BossManager.gI().createBoss(BossID.SO_2);
                                BossManager.gI().createBoss(BossID.SO_3);
                                BossManager.gI().createBoss(BossID.SO_4);
                                BossManager.gI().createBoss(BossID.TIEU_DOI_TRUONG);
                                break;
                            case 9:
                                BossManager.gI().createBoss(BossID.KUKU);
                                BossManager.gI().createBoss(BossID.MAP_DAU_DINH);
                                BossManager.gI().createBoss(BossID.RAMBO);
                                break;
                            case 10:
                                BossManager.gI().createBoss(BossID.COOLER_GOLD);
                                BossManager.gI().createBoss(BossID.CUMBER);
                                BossManager.gI().createBoss(BossID.SONGOKU_TA_AC);
                                break;
                            case 11:
                                BossManager.gI().createBoss(BossID.BLACK);
                                BossManager.gI().createBoss(BossID.BLACK3);
                                BossManager.gI().createBoss(BossID.BLACK1);
                                BossManager.gI().createBoss(BossID.ZAMASZIN);
                                BossManager.gI().createBoss(BossID.ZAMASMAX);
                                break;
                            case 12:
                                BossManager.gI().createBoss(BossID.TAU_PAY_PAY_M);
                        }
                        break;
                    case ConstNpc.menutd:
                        switch (select) {
                            case 0:
                                try {
                                    ItemService.gI().settaiyoken(player);
                                } catch (Exception e) {
                                }
                                break;
                            case 1:
                                try {
                                    ItemService.gI().setgenki(player);
                                } catch (Exception e) {
                                }
                                break;
                            case 2:
                                try {
                                    ItemService.gI().setkamejoko(player);
                                } catch (Exception e) {
                                }
                                break;
                        }
                        break;

                    case ConstNpc.menunm:
                        switch (select) {
                            case 0:
                                try {
                                    ItemService.gI().setgodki(player);
                                } catch (Exception e) {
                                }
                                break;
                            case 1:
                                try {
                                    ItemService.gI().setgoddam(player);
                                } catch (Exception e) {
                                }
                                break;
                            case 2:
                                try {
                                    ItemService.gI().setsummon(player);
                                } catch (Exception e) {
                                }
                                break;
                        }
                        break;

                    case ConstNpc.menuxd:
                        switch (select) {
                            case 0:
                                try {
                                    ItemService.gI().setgodgalick(player);
                                } catch (Exception e) {
                                }
                                break;
                            case 1:
                                try {
                                    ItemService.gI().setmonkey(player);
                                } catch (Exception e) {
                                }
                                break;
                            case 2:
                                try {
                                    ItemService.gI().setgodhp(player);
                                } catch (Exception e) {
                                }
                                break;
                        }
                        break;

                    case ConstNpc.CONFIRM_DISSOLUTION_CLAN:
                        switch (select) {
                            case 0:
                                Clan clan = player.clan;
                                clan.deleteDB(clan.id);
                                Manager.CLANS.remove(clan);
                                player.clan = null;
                                player.clanMember = null;
                                ClanService.gI().sendMyClan(player);
                                ClanService.gI().sendClanId(player);
                                Service.getInstance().sendThongBao(player, "Đã giải tán bang hội.");
                                break;
                        }
                        break;
                    case ConstNpc.CONFIRM_ACTIVE:
                        switch (select) {
                            case 0:
                                if (player.getSession().goldBar >= 20) {
                                    player.kichhoat = 1;
                                    
                                        Service.getInstance().sendThongBao(player, "Đã mở thành viên thành công!");
                                        break;
                                    
                                }
//                                Service.getInstance().sendThongBao(player, "Bạn không có vàng\n Vui lòng NROGOD.COM để nạp thỏi vàng");
                                break;
                        }
                        break;
                    case ConstNpc.CONFIRM_REMOVE_ALL_ITEM_LUCKY_ROUND:
                        if (select == 0) {
                            for (int i = 0; i < player.inventory.itemsBoxCrackBall.size(); i++) {
                                player.inventory.itemsBoxCrackBall.set(i, ItemService.gI().createItemNull());
                            }
                            player.inventory.itemsBoxCrackBall.clear();
                            Service.getInstance().sendThongBao(player, "Đã xóa hết vật phẩm trong rương");
                        }
                        break;
                    case ConstNpc.MENU_FIND_PLAYER:
                        Player p = (Player) PLAYERID_OBJECT.get(player.id);
                        if (p != null) {
                            switch (select) {
                                case 0:
                                    if (p.zone != null) {
                                        ChangeMapService.gI().changeMapYardrat(player, p.zone, p.location.x, p.location.y);
                                    }
                                    break;
                                case 1:
                                    if (p.zone != null) {
                                        ChangeMapService.gI().changeMap(p, player.zone, player.location.x, player.location.y);
                                    }
                                    break;
                                case 2:
                                    Input.gI().createFormChangeName(player, p);
                                    break;
                                case 3:
                                    String[] selects = new String[]{"Đồng ý", "Hủy"};
                                    NpcService.gI().createMenuConMeo(player, ConstNpc.BAN_PLAYER, -1,
                                            "Bạn có chắc chắn muốn ban " + p.name, selects, p);
                                    break;
                                case 4:
                                    Service.getInstance().sendThongBao(player, "Kik người chơi " + p.name + " thành công");
                                    Client.gI().getPlayers().remove(p);
                                    Client.gI().kickSession(p.getSession());
                                    break;
                            }
                        }
                        break;
                    case ConstNpc.DOI_TIEN:  // 
                        switch (select) {
                            case 0:
                                NpcService.gI().createMenuConMeo(player, ConstNpc.MENU_DOITIEN1, 564,
                                        "Con Đang Có " + player.getSession().vnd + "vnd", "20.000vnđ", "50.000vnđ", "100.000vnđ", "200.000vnđ");
                                                break;
                                                
                            case 1:  //

                                NpcService.gI().createMenuConMeo(player, ConstNpc.MENU_DOITIEN2, 564,
                                        "Con Đang Có" + player.getSession().vnd + "vnd", "20.000vnđ", "50.000vnđ", "100.000vnđ", "200.000vnđ");
                                break;
                                
                            case 2:  //

                                break;

                        }
                        break;
                    case ConstNpc.DOI_DTVIP:  // 
                        switch (select) {
                            case 0:
                                if (player.getSession().vnd >= 30000) {
                                    if (player.pet == null) {
                                        Service.getInstance().sendThongBao(player, "Bạn không đủ tiền");
                                    } else {
                                   try {
                                        
                                        
                                         PetService.gI().changeBillPet(player, player.gender);
                                        GirlkunDB.executeUpdate("update player set vnd = (vnd - 300000) where id = " + player.id);
                                        Service.getInstance().sendThongBao(player, "doi thanh cong");
                                    } catch (Exception ex) {
                                        java.util.logging.Logger.getLogger(NpcFactory.class.getName()).log(Level.SEVERE, null, ex);
                                    }                                    
                                }
                                } else {
                                    Service.getInstance().sendThongBao(player, "Bạn không đủ tiền");
                                }
                                break;          
                            case 1:  //

                                if (player.getSession().vnd >= 50000) {
                                    if (player.pet == null) {
                                        Service.getInstance().sendThongBao(player, "Bạn không đủ tiền");
                                    } else {
                                  try {
                                        
                                       
                                         PetService.gI().changeWhisPet(player, player.gender);
                                        GirlkunDB.executeUpdate("update player set vnd = (vnd - 500000) where id = " + player.id);
                                        Service.getInstance().sendThongBao(player, "doi thanh cong");
                                    } catch (Exception ex) {
                                        java.util.logging.Logger.getLogger(NpcFactory.class.getName()).log(Level.SEVERE, null, ex);
                                    }                                    
                                }
                                } else {
                                    Service.getInstance().sendThongBao(player, "Bạn không đủ tiền");
                                }
                                break;
                                
                            case 2:  //

                               if (player.getSession().vnd >= 100000) {
                                    if (player.pet == null) {
                                        Service.getInstance().sendThongBao(player, "Bạn không đủ tiền");
                                    } else {
                                   try {
                                        
                                       
                                         PetService.gI().changeGokuPet(player, player.gender);
                                        GirlkunDB.executeUpdate("update player set vnd = (vnd - 100000) where id = " + player.id);
                                        Service.getInstance().sendThongBao(player, "doi thanh cong");
                                    } catch (Exception ex) {
                                        java.util.logging.Logger.getLogger(NpcFactory.class.getName()).log(Level.SEVERE, null, ex);
                                    }                                    
                                }
                                } else {
                                    Service.getInstance().sendThongBao(player, "Bạn không đủ tiền");
                                }
                                break;
                            
                             case 3:  //

                               if (player.getSession().vnd >= 150000) {
                                    if (player.pet == null) {
                                        Service.getInstance().sendThongBao(player, "Bạn không đủ tiền");
                                    } else {
                                  try {
                                        
                                        
                                         PetService.gI().changeCumberPet(player, player.gender);
                                        GirlkunDB.executeUpdate("update player set vnd = (vnd - 150000) where id = " + player.id);
                                        Service.getInstance().sendThongBao(player, "doi thanh cong");
                                    } catch (Exception ex) {
                                        java.util.logging.Logger.getLogger(NpcFactory.class.getName()).log(Level.SEVERE, null, ex);
                                    }                                    
                                }
                                } else {
                                    Service.getInstance().sendThongBao(player, "Bạn không đủ tiền");
                                }
                                break;

                        }
                        break;
                    case ConstNpc.MENU_DOITIEN1:
                        switch (select) {
                            case 0:
                                if (player.getSession().vnd >= 20000) {
                                    try {
                                        Item thoivang = ItemService.gI().createNewItem((short) 457, 40);
                                        InventoryServiceNew.gI().addItemBag(player, thoivang);
                                        InventoryServiceNew.gI().sendItemBags(player);
                                        PlayerDAO.subvnd(player, 20000);
                                        
                                        Service.getInstance().sendThongBao(player, "doi thanh cong");
                                    } catch (Exception ex) {
                                        java.util.logging.Logger.getLogger(NpcFactory.class.getName()).log(Level.SEVERE, null, ex);
                                    }
                                } else {
                                    Service.getInstance().sendThongBao(player, "Bạn không đủ tiền");

                                }
                                break;

                            case 1:
                                if (player.getSession().vnd >= 50000) {
                                    try {
                                        Item thoivang = ItemService.gI().createNewItem((short) 457, 100);
                                        InventoryServiceNew.gI().addItemBag(player, thoivang);
                                        InventoryServiceNew.gI().sendItemBags(player);
                                        PlayerDAO.subvnd(player, 50000);
                                       
                                        Service.getInstance().sendThongBao(player, "doi thanh cong");
                                    } catch (Exception ex) {
                                        java.util.logging.Logger.getLogger(NpcFactory.class.getName()).log(Level.SEVERE, null, ex);
                                    }
                                } else {
                                    Service.getInstance().sendThongBao(player, "Bạn không đủ tiền");

                                }
                                break;
                            case 2:  // 
                                if (player.getSession().vnd >= 100000) {
                                    try {
                                        Item thoivang = ItemService.gI().createNewItem((short) 457, 200);
                                        InventoryServiceNew.gI().addItemBag(player, thoivang);
                                        InventoryServiceNew.gI().sendItemBags(player);
                                        PlayerDAO.subvnd(player, 100000);
                                        
                                        Service.getInstance().sendThongBao(player, "doi thanh cong");
                                    } catch (Exception ex) {
                                        java.util.logging.Logger.getLogger(NpcFactory.class.getName()).log(Level.SEVERE, null, ex);
                                    }
                                } else {
                                    Service.getInstance().sendThongBao(player, "Bạn không đủ tiền");

                                }
                                break;
                            case 3:  // 
                                if (player.getSession().vnd >= 200000) {
                                    try {
                                        Item thoivang = ItemService.gI().createNewItem((short) 457, 400);
                                        InventoryServiceNew.gI().addItemBag(player, thoivang);
                                        InventoryServiceNew.gI().sendItemBags(player);
                                        PlayerDAO.subvnd(player, 200000);
                                        
                                        Service.getInstance().sendThongBao(player, "doi thanh cong");
                                    } catch (Exception ex) {
                                        java.util.logging.Logger.getLogger(NpcFactory.class.getName()).log(Level.SEVERE, null, ex);
                                    }
                                } else {
                                    Service.getInstance().sendThongBao(player, "Bạn không đủ tiền");

                                }
                                break;
                        }
                        break;
                    case ConstNpc.MENU_DOITIEN2:
                        switch (select) {
                            case 0:
                                if (player.getSession().vnd >= 20000) {
                                    try {
                                        player.inventory.ruby += 20000;
                                        Service.getInstance().sendMoney(player);
                                        InventoryServiceNew.gI().sendItemBags(player);
                                        PlayerDAO.subvnd(player, 20000);
                                        
                                        Service.getInstance().sendThongBao(player, "doi thanh cong");
                                    } catch (Exception ex) {
                                        java.util.logging.Logger.getLogger(NpcFactory.class.getName()).log(Level.SEVERE, null, ex);
                                    }
                                } else {
                                    Service.getInstance().sendThongBao(player, "Bạn không đủ tiền");

                                }
                                break;

                            case 1:
                                if (player.getSession().vnd >= 50000) {

                                    try {
                                        player.inventory.gem += 50000;
                                        Service.getInstance().sendMoney(player);
                                        InventoryServiceNew.gI().sendItemBags(player);
                                        PlayerDAO.subvnd(player, 50000);
                                        
                                        Service.getInstance().sendThongBao(player, "doi thanh cong");
                                    } catch (Exception ex) {
                                        java.util.logging.Logger.getLogger(NpcFactory.class.getName()).log(Level.SEVERE, null, ex);
                                    }
                                } else {
                                    Service.getInstance().sendThongBao(player, "Bạn không đủ tiền");

                                }
                                break;
                            case 2:  // 
                                if (player.getSession().vnd >= 100000) {

                                    try {
                                        player.inventory.gem += 100000;
                                        Service.getInstance().sendMoney(player);
                                        InventoryServiceNew.gI().sendItemBags(player);
                                        PlayerDAO.subvnd(player, 100000);
                                        
                                        Service.getInstance().sendThongBao(player, "doi thanh cong");
                                    } catch (Exception ex) {
                                        java.util.logging.Logger.getLogger(NpcFactory.class.getName()).log(Level.SEVERE, null, ex);
                                    }
                                } else {
                                    Service.getInstance().sendThongBao(player, "Bạn không đủ tiền");

                                }
                                break;
                            case 3:  // 
                                if (player.getSession().vnd >= 200000) {
                                    try {
                                        player.inventory.gem += 200000;
                                        Service.getInstance().sendMoney(player);
                                        InventoryServiceNew.gI().sendItemBags(player);
                                        
                                        GirlkunDB.executeUpdate("update player set vnd = (vnd + 200000) where id = " + player.id);

                                        Service.getInstance().sendThongBao(player, "doi thanh cong");
                                    } catch (Exception ex) {
                                        java.util.logging.Logger.getLogger(NpcFactory.class.getName()).log(Level.SEVERE, null, ex);
                                    }
                                } else {
                                    Service.getInstance().sendThongBao(player, "Bạn không đủ tiền");

                                }
                                break;

                        }
                        break;
                    case ConstNpc.MENU_EVENT:
                        switch (select) {
                            case 0:
                                Service.getInstance().sendThongBaoOK(player, "Điểm sự kiện: " + player.inventory.event + " ngon ngon...");
                                break;
                            case 1:
                                Util.showListTop(player, (byte) 2);
                                break;
                            case 2:
                                Service.getInstance().sendThongBao(player, "Sự kiện đã kết thúc...");
//                                NpcService.gI().createMenuConMeo(player, ConstNpc.MENU_GIAO_BONG, -1, "Người muốn giao bao nhiêu bông...",
//                                        "100 bông", "1000 bông", "10000 bông");
                                break;
                            case 3:
                                Service.getInstance().sendThongBao(player, "Sự kiện đã kết thúc...");
//                                NpcService.gI().createMenuConMeo(player, ConstNpc.CONFIRM_DOI_THUONG_SU_KIEN, -1, "Con có thực sự muốn đổi thưởng?\nPhải giao cho ta 3000 điểm sự kiện đấy... ",
//                                        "Đồng ý", "Từ chối");
                                break;

                        }
                        break;
                    case ConstNpc.MENU_GIAO_BONG:
                        ItemService.gI().giaobong(player, (int) Util.tinhLuyThua(10, select + 2));
                        break;
                    case ConstNpc.CONFIRM_DOI_THUONG_SU_KIEN:
                        if (select == 0) {
                            ItemService.gI().openBoxVip(player);
                        }
                        break;
                    case ConstNpc.CONFIRM_TELE_NAMEC:
                        if (select == 0) {
                            NgocRongNamecService.gI().teleportToNrNamec(player);
                            player.inventory.subGemAndRuby(50);
                            Service.getInstance().sendMoney(player);
                        }
                        break;
                }
            }
        };
    }

}
