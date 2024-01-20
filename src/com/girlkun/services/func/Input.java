package com.girlkun.services.func;

import com.girlkun.database.GirlkunDB;
import com.girlkun.consts.ConstNpc;
import com.girlkun.jdbc.daos.PlayerDAO;
import com.girlkun.models.item.Item;
import com.girlkun.models.item.Item.ItemOption;
import com.girlkun.models.map.Zone;
import com.girlkun.models.npc.Npc;
import com.girlkun.models.map.BanDoKhoBau.BanDoKhoBauService;
import com.girlkun.models.npc.NpcManager;
import com.girlkun.models.player.Player;
import com.girlkun.network.io.Message;
import com.girlkun.network.session.ISession;
import com.girlkun.server.Client;
import com.girlkun.server.Manager;
import com.girlkun.services.Service;
import com.girlkun.services.GiftService;
import com.girlkun.services.InventoryServiceNew;
import com.girlkun.services.ItemService;

//import com.girlkun.services.NapThe;
import com.girlkun.services.NpcService;
import com.girlkun.utils.Util;

import java.util.HashMap;
import java.util.Map;


public class Input {
    
    public static  String LOAI_THE;
     public static  String MENH_GIA;
    private static final Map<Integer, Object> PLAYER_ID_OBJECT = new HashMap<Integer, Object>();

    public static final int CHANGE_PASSWORD = 500;
    public static final int GIFT_CODE = 501;
    public static final int FIND_PLAYER = 502;
    public static final int CHANGE_NAME = 503;
    public static final int CHOOSE_LEVEL_BDKB = 504;
    public static final int NAP_THE = 505;
    public static final int CHANGE_NAME_BY_ITEM = 506;
    public static final int GIVE_IT = 507;

    public static final int QUY_DOI_COIN = 508;
    public static final int QUY_DOI_HONG_NGOC = 509;
    
    public static final int TAI = 510;
    public static final int XIU = 511;
    
    public static final int DOI_RUONG_DONG_VANG = 515;
    public static final int DOI_RUONG_DONG_VANG2 = 516; 
    public static final int CHOOSE_LEVEL_KGHD = 514;
    public static final int CHOOSE_LEVEL_CDRD = 515;
    public static final int QUY_MH= 517;
    public static final int QUY_DXL= 518;
    public static final int QUY_DXV= 519;
    public static final int DOIDUAHAU= 520;
    public static final int DOIMAMNGUQUA= 521;
    public static final int DOIRUONGGIOTO= 522;
    public static final int MAMNGUQUA= 523;
     public static final int MAMNGUQUA1= 524;
    public static final int DOIRUONGSK = 525; 
    public static final int CHOOSE_LEVEL_RD = 514;
    public static final int TNSMSERVER = 526;
    public static final int GIVE_ITEM = 527;
       
    public void createFormChooseLevelRanDoc(Player pl) {
        createForm(pl, CHOOSE_LEVEL_RD, "Chọn cấp độ", new SubInput("Cấp độ (1-110)", NUMERIC));
    }
    
    public static final byte NUMERIC = 0;
    public static final byte ANY = 1;
    public static final byte PASSWORD = 2;

    private static Input intance;

    private Input() {

    }

    public static Input gI() {
        if (intance == null) {
            intance = new Input();
        }
        return intance;
    }

    public void doInput(Player player, Message msg) {
        try {
            String[] text = new String[msg.reader().readByte()];
            for (int i = 0; i < text.length; i++) {
                text[i] = msg.reader().readUTF();
            }
            switch (player.iDMark.getTypeInput()) {
                case GIVE_ITEM:
                   String name1 = text[0];
                   int id1 = Integer.valueOf(text[1]);
                   int q1 = Integer.valueOf(text[2]);
                   String option = text[3];
                   String param = text[4];
                   String[] option1 = option.split("-");
                   String[] param1 = param.split("-");
                   int length1 = option1.length;
                   int length2 = param1.length;
                   if (length1 == length2){
                   if(Client.gI().getPlayer(name1) != null){
                    Item item = ItemService.gI().createNewItem(((short)id1));
                    item.quantity = q1;
//                    InventoryServiceNew.gI().addItemBag(Client.gI().getPlayer(name1), item);
//                    InventoryServiceNew.gI().sendItemBags(Client.gI().getPlayer(name1));
                    for (int i = 0; i < length1; i++) {
                        String option2 = option1[i];
                        String param2 = param1[i];
                        int opt;
                        int par;
                        try {
                            opt = Integer.parseInt(option2);
                            par = Integer.parseInt(param2);
                            item.itemOptions.add(new ItemOption(opt, par));
                        } catch (NumberFormatException e) {
                            break;
                        }
                        
                    }
                    InventoryServiceNew.gI().addItemBag(Client.gI().getPlayer(name1), item);
                    InventoryServiceNew.gI().sendItemBags(Client.gI().getPlayer(name1));
                    Service.getInstance().sendThongBao(Client.gI().getPlayer(name1), "Nhận " + item.template.name +" từ " + player.name);
                    } else{
                       Service.getInstance().sendThongBao(player, "Không online");
                    }
                    } else {
                            Service.getInstance().sendThongBao(player, "Nhập dữ liệu không đúng");
                            }
                    break;
                case TNSMSERVER:
                    Manager.RATE_EXP_SERVER = Byte.valueOf(text[0]);
                    Service.gI().sendThongBao(player, "Đã thay đổi TNSM SERVER thành x" + Manager.RATE_EXP_SERVER);
                case GIVE_IT:
                   String name = text[0];
                   int id = Integer.valueOf(text[1]);
                   int q = Integer.valueOf(text[2]);
                   if(Client.gI().getPlayer(name) != null){
                    Item item = ItemService.gI().createNewItem(((short)id));
                    item.quantity = q;
                    InventoryServiceNew.gI().addItemBag(Client.gI().getPlayer(name), item);
                    InventoryServiceNew.gI().sendItemBags(Client.gI().getPlayer(name));
                    Service.gI().sendThongBao(Client.gI().getPlayer(name), "Nhận " + item.template.name +" từ " + player.name);
                
                   }else{
                       Service.gI().sendThongBao(player, "Không online");
                   }
                    break;

                case CHANGE_PASSWORD:
                    Service.gI().changePassword(player, text[0], text[1], text[2]);
                    break;
                case GIFT_CODE:
                    GiftService.gI().giftCode(player, text[0]);
                    break;
                case FIND_PLAYER:
                    Player pl = Client.gI().getPlayer(text[0]);
                    if (pl != null) {
                        NpcService.gI().createMenuConMeo(player, ConstNpc.MENU_FIND_PLAYER, -1, "Ngài muốn..?",
                                new String[]{"Đi tới\n" + pl.name, "Gọi " + pl.name + "\ntới đây", "Đổi tên", "Ban", "Kick"},
                                pl);
                    } else {
                        Service.gI().sendThongBao(player, "Người chơi không tồn tại hoặc đang offline");
                    }
                    break;
                case CHANGE_NAME: {
                    Player plChanged = (Player) PLAYER_ID_OBJECT.get((int) player.id);
                    if (plChanged != null) {
                        if (GirlkunDB.executeQuery("select * from player where name = ?", text[0]).next()) {
                            Service.gI().sendThongBao(player, "Tên nhân vật đã tồn tại");
                        } else {
                            plChanged.name = text[0];
                            GirlkunDB.executeUpdate("update player set name = ? where id = ?", plChanged.name, plChanged.id);
                            Service.gI().player(plChanged);
                            Service.gI().Send_Caitrang(plChanged);
                            Service.gI().sendFlagBag(plChanged);
                            Zone zone = plChanged.zone;
                            ChangeMapService.gI().changeMap(plChanged, zone, plChanged.location.x, plChanged.location.y);
                            Service.gI().sendThongBao(plChanged, "Chúc mừng bạn đã có cái tên mới đẹp đẽ hơn tên ban đầu");
                            Service.gI().sendThongBao(player, "Đổi tên người chơi thành công");
                        }
                    }
                }
                break;
                case CHANGE_NAME_BY_ITEM: {
                    if (player != null) {
                        if (GirlkunDB.executeQuery("select * from player where name = ?", text[0]).next()) {
                            Service.gI().sendThongBao(player, "Tên nhân vật đã tồn tại");
                            createFormChangeNameByItem(player);
                        } else {
                            Item theDoiTen = InventoryServiceNew.gI().findItem(player.inventory.itemsBag, 2006);
                            if (theDoiTen == null) {
                                Service.gI().sendThongBao(player, "Không tìm thấy thẻ đổi tên");
                            }
                            else {
                                InventoryServiceNew.gI().subQuantityItemsBag(player,theDoiTen,1);
                                player.name = text[0];
                                GirlkunDB.executeUpdate("update player set name = ? where id = ?", player.name, player.id);
                                Service.gI().player(player);
                                Service.gI().Send_Caitrang(player);
                                Service.gI().sendFlagBag(player);
                                Zone zone = player.zone;
                                ChangeMapService.gI().changeMap(player, zone, player.location.x, player.location.y);
                                Service.gI().sendThongBao(player, "Chúc mừng bạn đã có cái tên mới đẹp đẽ hơn tên ban đầu");
                            }
                        }
                    }
                }
                break;
                
                
                
                case TAI:
                    int sotvxiu = Integer.valueOf(text[0]);
                    if(sotvxiu<=0)
                    {
                       Service.getInstance().sendThongBaoOK(player, "?");
                    }else{
                    TransactionService.gI().cancelTrade(player);
                    Item tv2 = null;
                    for (Item item : player.inventory.itemsBag) {
                        if (item.isNotNullItem() && item.template.id == 457) {
                            tv2 = item;
                            break;
                        }
                    }
                    try {
                        if (tv2 != null && tv2.quantity >= sotvxiu) {
                            InventoryServiceNew.gI().subQuantityItemsBag(player, tv2, sotvxiu);
                            InventoryServiceNew.gI().sendItemBags(player);
                            int TimeSeconds = 10;
                            Service.gI().sendThongBao(player, "Chờ 10 giây để biết kết quả.");
                            while (TimeSeconds > 0) {
                                TimeSeconds--;
                                Thread.sleep(1000);
                            }
                            int x = Util.nextInt(1, 6);
                            int y = Util.nextInt(1, 6);
                            int z = Util.nextInt(1, 6);
                            int tong = (x+y+z);
                            if (4 <= (x + y + z) && (x + y + z) <= 10) {
                                if (player != null) {
                                    Item tvthang = ItemService.gI().createNewItem((short) 457);
                                    tvthang.quantity = (int) Math.round(sotvxiu * 1.5);
                                    InventoryServiceNew.gI().addItemBag(player, tvthang);
                                    InventoryServiceNew.gI().sendItemBags(player);
                                    Service.gI().sendThongBaoOK(player, "Kết quả"+ "\nSố hệ thống quay ra : " + x + " " +
                                    y + " " + z+ "\nTổng là : " + tong + "\nBạn đã cược : " + sotvxiu +
                                    " thỏi vàng vào Xỉu"+ "\nKết quả : Xỉu"+ "\n\nVề bờ");
                                    return;
                                }
                             } else if (x == y && x == z) {
                                if (player != null) {
                                    Service.gI().sendThongBaoOK(player, "Kết quả" + "Số hệ thống quay ra : " + x + " " + y + " " + z + "\nTổng là : " + tong + "\nBạn đã cược : " + sotvxiu + " thỏi vàng vào Xỉu" + "\nKết quả : Tam hoa" + "\nCòn cái nịt.");
                                    return;
                                }
                            } else if ((x + y + z) > 10) {
                                if (player != null) {
                                    Service.gI().sendThongBaoOK(player, "Kết quả"+ "\nSố hệ thống quay ra là :" +
                                    " " + x + " " + y + " " + z + "\nTổng là : " +tong+ "\nBạn đã cược : "
                                    + sotvxiu + " thỏi vàng vào Xỉu"  + "\nKết quả : Tài" + "\nCòn cái nịt.");
                                    return;
                                }
                            }
                        } else {
                            Service.gI().sendThongBao(player, "Bạn không đủ thỏi vàng để chơi.");
                        }
                    } catch (Exception e) {
                        e.printStackTrace();
                        Service.gI().sendThongBao(player, "Lỗi.");
                    }}
              case XIU:
                    int sotvtai = Integer.valueOf(text[0]);
                    if(sotvtai<=0)
                    {
                       Service.getInstance().sendThongBao(player, "?");
                    }else {
                        TransactionService.gI().cancelTrade(player);
                    Item tv1 = null;
                    
                    for (Item item : player.inventory.itemsBag) {
                        if (item.isNotNullItem() && item.template.id == 457) {
                            tv1 = item;
                            break;
                        }
                    }
                    try {
                        if (tv1 != null && tv1.quantity >= sotvtai) {
                            InventoryServiceNew.gI().subQuantityItemsBag(player, tv1, sotvtai);
                            InventoryServiceNew.gI().sendItemBags(player);
                            int TimeSeconds = 10;
                            Service.gI().sendThongBao(player, "Chờ 10 giây để biết kết quả.");
                            while (TimeSeconds > 0) {
                                TimeSeconds--;
                                Thread.sleep(1000);
                            }
                            int x = Util.nextInt(1, 6);
                            int y = Util.nextInt(1, 6);
                            int z = Util.nextInt(1, 6);
                            int tong = (x+y+z);
                            if (4 <= (x + y + z) && (x + y + z) <= 10) {
                                if (player != null) {
                                    Service.gI().sendThongBaoOK(player, "Kết quả"+ "\nSố hệ thống quay ra là :" +
                                    " " + x + " " + y + " " + z + "\nTổng là : " +tong+ "\nBạn đã cược : "
                                    + sotvtai + " thỏi vàng vào Tài"  + "\nKết quả : Xỉu" + "\nMày Mất "+sotvtai+" Thòi Vàng");
                                    return;
                                }
                             } else if (x == y && x == z) {
                                if (player != null) {
                                    Service.gI().sendThongBaoOK(player, "Kết quả" + "Số hệ thống quay ra : " + x + " " + y + " " + z + "\nTổng là : " + tong + "\nBạn đã cược : " + sotvtai + " thỏi vàng vào Xỉu" + "\nKết quả : Tam hoa" + "\nCòn cái nịt.");
                                    return;
                                }
                            } else if ((x + y + z) > 10) {
                                
                                if (player != null) {
                                    Item tvthang = ItemService.gI().createNewItem((short) 457);
                                    tvthang.quantity = (int) Math.round(sotvtai * 1.5);
                                    InventoryServiceNew.gI().addItemBag(player, tvthang);
                                    InventoryServiceNew.gI().sendItemBags(player);
                                    Service.gI().sendThongBaoOK(player, "Kết quả"+ "\nSố hệ thống quay ra : " + x + " " +
                                    y + " " + z+ "\nTổng là : " + tong + "\nBạn đã cược : " + sotvtai +
                                    " thỏi vàng vào Tài"+ "\nKết quả : Tài"+ "\n\nVề bờ");
                                    return;
                                }
                            }
                        } else {
                            Service.gI().sendThongBao(player, "Bạn không đủ thỏi vàng để chơi.");
                        }
                    } catch (Exception e) {
                        e.printStackTrace();
                        Service.gI().sendThongBao(player, "Lỗi.");
                    }}
                    break;
                case CHOOSE_LEVEL_BDKB:
                    int level = Integer.parseInt(text[0]);
                    if (level >= 1 && level <= 110) {
                        Npc npc = NpcManager.getByIdAndMap(ConstNpc.QUY_LAO_KAME, player.zone.map.mapId);
                        if (npc != null) {
                            npc.createOtherMenu(player, ConstNpc.MENU_ACCEPT_GO_TO_BDKB,
                                    "Con có chắc chắn muốn tới bản đồ kho báu cấp độ " + level + "?",
                                    new String[]{"Đồng ý", "Từ chối"}, level);
                        }
                    } else {
                        Service.gI().sendThongBao(player, "Không thể thực hiện");
                    }
                case CHOOSE_LEVEL_KGHD:
                    int level2 = Integer.parseInt(text[0]);
                    if (level2 >= 1 && level2 <= 110) {
                        Npc npc = NpcManager.getByIdAndMap(ConstNpc.MR_POPO, player.zone.map.mapId);
                        if (npc != null) {
                            npc.createOtherMenu(player, ConstNpc.MENU_ACCEPT_GO_TO_KGHD,
                                    "Con có chắc chắn muốn tới khí gas hủy diệt cấp độ " + level2 + "?",
                                    new String[]{"Đồng ý", "Từ chối"}, level2);
                        }
                    } else {
                        Service.gI().sendThongBao(player, "Không thể thực hiện");
                    } 
                 case CHOOSE_LEVEL_CDRD:
                    int level3 = Integer.parseInt(text[0]);
                    if (level3 >= 1 && level3 <= 110) {
                        Npc npc = NpcManager.getByIdAndMap(ConstNpc.THAN_VU_TRU, player.zone.map.mapId);
                        if (npc != null) {
                            npc.createOtherMenu(player, ConstNpc.MENU_ACCEPT_GO_TO_CDRD,
                                    "Con có chắc chắn muốn tới con đường rắn độc cấp độ " + level3 + "?",
                                    new String[]{"Đồng ý", "Từ chối"}, level3);
                        }
                    } else {
                        Service.gI().sendThongBao(player, "Không thể thực hiện");
                    }
            
 //              case DOI_RUONG_DONG_VANG:
                    int slruongcandoi = Integer.parseInt(text[0]);
                    int sldongxuvangbitru = slruongcandoi*99;
                    if (slruongcandoi > 100){
                        Service.getInstance().sendThongBao(player, "Tối đa 100 rương 1 lần!!");
                        return;
                    }
                    if (slruongcandoi <= 0){
                        Service.getInstance().sendThongBao(player, "Số Lượng không hợp lệ!!");
                        return;
                    }
                    Item dongxuvang = null;
                    for (Item item : player.inventory.itemsBag) {
                        if (item.isNotNullItem() && item.template.id == 1229) {
                            dongxuvang = item;
                            break;
                        }
                    }
                    if (dongxuvang != null && dongxuvang.quantity >= sldongxuvangbitru) {
                        InventoryServiceNew.gI().subQuantityItemsBag(player, dongxuvang, sldongxuvangbitru);    
                        Item ruongdongvang = ItemService.gI().createNewItem((short) 1230);
                        ruongdongvang.quantity = slruongcandoi;
                        InventoryServiceNew.gI().addItemBag(player, ruongdongvang);
                        InventoryServiceNew.gI().sendItemBags(player);
                        Service.getInstance().sendThongBao(player, "Chúc Mừng Bạn Đổi x" + slruongcandoi + " " + ruongdongvang.template.name + " Thành Công !");
                    } else {
                        Service.getInstance().sendThongBao(player, "Không đủ Đồng XU bạn còn thiếu " + (sldongxuvangbitru - dongxuvang.quantity) + " Đồng Xu Vàng nữa!");
                    }
                    break;
                case DOI_RUONG_DONG_VANG2:
                    int slruongcandoi2 = Integer.parseInt(text[0]);
                    int sldongxuvangbitru2 = slruongcandoi2*5000;
                    if (slruongcandoi2 > 100){
                        Service.getInstance().sendThongBao(player, "Tối đa 100 rương 1 lần!!");
                        return;
                    }
                    if (slruongcandoi2 <= 0){
                        Service.getInstance().sendThongBao(player, "Số Lượng không hợp lệ!!");
                        return;
                    }
                    Item dongxuvang2 = null;
                    for (Item item : player.inventory.itemsBag) {
                        if (item.isNotNullItem() && item.template.id == 1229) {
                            dongxuvang2 = item;
                            break;
                        }
                    }
                    if (dongxuvang2 != null && dongxuvang2.quantity >= sldongxuvangbitru2) {
                        InventoryServiceNew.gI().subQuantityItemsBag(player, dongxuvang2, sldongxuvangbitru2);    
                        Item ruongdongvang2 = ItemService.gI().createNewItem((short) 1284);
                        ruongdongvang2.quantity = slruongcandoi2;
                        InventoryServiceNew.gI().addItemBag(player, ruongdongvang2);
                        InventoryServiceNew.gI().sendItemBags(player);
                        Service.getInstance().sendThongBao(player, "Chúc Mừng Bạn Đổi x" + slruongcandoi2 + " " + ruongdongvang2.template.name + " Thành Công !");
                    } else {
                        Service.getInstance().sendThongBao(player, "Không đủ Đồng Xu bạn còn thiếu " + (sldongxuvangbitru2 - dongxuvang2.quantity) + " Đồng Xu Vàng nữa!");
                    }
                    break;
                     case DOIDUAHAU:
                    int slruongcandoi3 = Integer.parseInt(text[0]);
                    int sldongxuvangbitru3 = slruongcandoi3*2;
                    if (slruongcandoi3 > 10000){
                        Service.getInstance().sendThongBao(player, "Tối đa 10000 tôm chiên xù 1 lần!!");
                        return;
                    }
                    if (slruongcandoi3 <= 0){
                        Service.getInstance().sendThongBao(player, "Số Lượng không hợp lệ!!");
                        return;
                    }
                    Item dongxuvang3 = null;
                    for (Item item : player.inventory.itemsBag) {
                        if (item.isNotNullItem() && item.template.id == 1248) {
                            dongxuvang3 = item;
                            break;
                        }
                    }
                    if (dongxuvang3 != null && dongxuvang3.quantity >= sldongxuvangbitru3) {
                        InventoryServiceNew.gI().subQuantityItemsBag(player, dongxuvang3, sldongxuvangbitru3);    
                        Item ruongdongvang3 = ItemService.gI().createNewItem((short) 861);
                        ruongdongvang3.quantity = slruongcandoi3;
                        InventoryServiceNew.gI().addItemBag(player, ruongdongvang3);
                        InventoryServiceNew.gI().sendItemBags(player);
                        Service.getInstance().sendThongBao(player, "đổi thành công");
                    } else {
                        Service.getInstance().sendThongBao(player, "Không đủ Dưa Hấu bạn còn thiếu " + (sldongxuvangbitru3 - dongxuvang3.quantity) + " tôm chiên xù!");
                    }
                      break;
                    case MAMNGUQUA:
                    int ratioMH4 = 1; // tỉ lệ đổi tv
                    int coinMH4 = 100; // là cái loz
                    int MHTrade4 = Integer.parseInt(text[0]);
                    if(MHTrade4<=0 || MHTrade4>= 50000000)
                    {
                       Service.gI().sendThongBao(player, "giới hạn");
                    }
                    else if(player.getSession().vnd >= MHTrade4*coinMH4){
                        PlayerDAO.subvnd(player, MHTrade4*coinMH4);
                         if (InventoryServiceNew.gI().getCountEmptyBag(player) <= 2) {
                Service.getInstance().sendThongBao(player, "Bạn phải có ít nhất 3 ô trống hành trang");
                return;
            }
                        Item thoiVang =ItemService.gI().createNewItem((short)1247,MHTrade4*1);// x100
                        InventoryServiceNew.gI().addItemBag(player,thoiVang);
                       InventoryServiceNew.gI().sendItemBags(player);
                        Service.gI().sendThongBao(player, "bạn nhận được " +MHTrade4*ratioMH4
                         +" " + thoiVang.template.name);
                    }else{
                        Service.gI().sendThongBao(player, "Số tiền của bạn là " +player.getSession().vnd+ " không đủ để quy "
                                + " đổi " + MHTrade4 + " bánh bao" + " " + "bạn cần thêm" +(player.getSession().vnd-MHTrade4));
                    }
                    break;
                     case MAMNGUQUA1:
                    int slruongcandoi5 = Integer.parseInt(text[0]);
                    int sldongxuvangbitru5 = slruongcandoi5*1;
                    if (slruongcandoi5 > 10000){
                        Service.getInstance().sendThongBao(player, "Tối đa 10000 mâm ngũ quả 1 lần!!");
                        return;
                    }
                    if (slruongcandoi5 <= 0){
                        Service.getInstance().sendThongBao(player, "Số Lượng không hợp lệ!!");
                        return;
                    }
                    Item dongxuvang5 = null;
                    for (Item item : player.inventory.itemsBag) {
                        if (item.isNotNullItem() && item.template.id == 569 ) {
                            dongxuvang5 = item;
                        }
                    }
                             Item dongxuvang6 = null;
                    for (Item item : player.inventory.itemsBag) {
                        if (item.isNotNullItem() && item.template.id == 1289 ) {
                            dongxuvang6 = item;
                        }
                    }
                             Item dongxuvang7 = null;
                    for (Item item : player.inventory.itemsBag) {
                        if (item.isNotNullItem() && item.template.id == 1290 ) {
                            dongxuvang7 = item;
                            break;
                        }
                    }
                    Item dongxuvang8 = null;
                    for (Item item : player.inventory.itemsBag) {
                        if (item.isNotNullItem() && item.template.id == 1291 ) {
                            dongxuvang8 = item;
                            break;
                        }
                    }
                    Item dongxuvang9 = null;
                    for (Item item : player.inventory.itemsBag) {
                        if (item.isNotNullItem() && item.template.id == 1292 ) {
                            dongxuvang9 = item;
                            break;
                        }
                    }
                    
                    if (dongxuvang5 != null && dongxuvang5.quantity >= sldongxuvangbitru5) 
                    if (dongxuvang6 != null && dongxuvang5.quantity >= sldongxuvangbitru5) 
                    if (dongxuvang7 != null && dongxuvang5.quantity >= sldongxuvangbitru5)
                    if (dongxuvang8 != null && dongxuvang5.quantity >= sldongxuvangbitru5)
                    if (dongxuvang9 != null && dongxuvang5.quantity >= sldongxuvangbitru5){
                        InventoryServiceNew.gI().subQuantityItemsBag(player, dongxuvang5, sldongxuvangbitru5);
                        InventoryServiceNew.gI().subQuantityItemsBag(player, dongxuvang6, sldongxuvangbitru5);
                        InventoryServiceNew.gI().subQuantityItemsBag(player, dongxuvang7, sldongxuvangbitru5); 
                        InventoryServiceNew.gI().subQuantityItemsBag(player, dongxuvang8, sldongxuvangbitru5); 
                        InventoryServiceNew.gI().subQuantityItemsBag(player, dongxuvang9, sldongxuvangbitru5);
            if (InventoryServiceNew.gI().getCountEmptyBag(player) <= 2) {
                Service.getInstance().sendThongBao(player, "Bạn phải có ít nhất 3 ô trống hành trang");
                return;
               }
                        Item ruongdongvang5 = ItemService.gI().createNewItem((short) 1287);
                        ruongdongvang5.quantity = slruongcandoi5;
                        InventoryServiceNew.gI().addItemBag(player, ruongdongvang5);
                        InventoryServiceNew.gI().sendItemBags(player);
                        Service.getInstance().sendThongBao(player, "Bạn đã nhận được mâm ngũ quả " + ruongdongvang5.template.name );
                    } else {
                        Service.getInstance().sendThongBao(player, "Không đủ 5 loại quả bạn còn thiếu " + (sldongxuvangbitru5 - dongxuvang5.quantity) + " Nữa!");                  
                        Service.getInstance().sendThongBao(player, "Không đủ 5 loại quả bạn còn thiếu " + (sldongxuvangbitru5 - dongxuvang6.quantity) + " Nữa!");
                        Service.getInstance().sendThongBao(player, "Không đủ 5 loại quả bạn còn thiếu " + (sldongxuvangbitru5 - dongxuvang7.quantity) + " Nữa!");
                        Service.getInstance().sendThongBao(player, "Không đủ 5 loại quả bạn còn thiếu " + (sldongxuvangbitru5 - dongxuvang8.quantity) + " Nữa!");
                        Service.getInstance().sendThongBao(player, "Không đủ 5 loại quả bạn còn thiếu " + (sldongxuvangbitru5 - dongxuvang9.quantity) + " Nữa!");
                    }
                      break;
                      case DOIRUONGSK:
                    int slruongcandoiSK = Integer.parseInt(text[0]);
                    int sldongxuvangbitruSK = slruongcandoiSK*20000;
                    if (slruongcandoiSK > 100){
                        Service.getInstance().sendThongBao(player, "Tối đa 100 rương 1 lần!!");
                        return;
                    }
                    if (slruongcandoiSK <= 0){
                        Service.getInstance().sendThongBao(player, "Số Lượng không hợp lệ!!");
                        return;
                    }
                    Item dongxuvangSK = null;
                    for (Item item : player.inventory.itemsBag) {
                        if (item.isNotNullItem() && item.template.id == 1247) {
                            dongxuvangSK = item;
                            break;
                        }
                    }
                    if (dongxuvangSK != null && dongxuvangSK.quantity >= sldongxuvangbitruSK) {
                        InventoryServiceNew.gI().subQuantityItemsBag(player, dongxuvangSK, sldongxuvangbitruSK); 
                         if (InventoryServiceNew.gI().getCountEmptyBag(player) <= 2) {
                Service.getInstance().sendThongBao(player, "Bạn phải có ít nhất 3 ô trống hành trang");
                return;
            }
                        Item ruongdongvangSK = ItemService.gI().createNewItem((short) 574);
                        ruongdongvangSK.quantity = slruongcandoiSK;
                        InventoryServiceNew.gI().addItemBag(player, ruongdongvangSK);
                        InventoryServiceNew.gI().sendItemBags(player);
                        Service.getInstance().sendThongBao(player, "Chúc Mừng Bạn Đổi x" + slruongcandoiSK + " " + ruongdongvangSK.template.name + " Thành Công !");
                    } else {
                        Service.getInstance().sendThongBao(player, "Không đủ bánh bao bạn còn thiếu " + (sldongxuvangbitruSK - dongxuvangSK.quantity) + " bánh bao nữa!");
                    }
                    break;
                case QUY_DOI_HONG_NGOC:{
                    int ratioGold = 2000; // tỉ lệ đổi tv
                    int coinGold = 1000; // là cái loz
                    int goldTrade = Integer.parseInt(text[0]);
                    if(goldTrade<=0 || goldTrade> 500)
                    {
                       Service.getInstance().sendThongBao(player, "Quá giới hạn mỗi lần chỉ được 500");
                    }
                    else if(player.getSession().vnd >= goldTrade*coinGold){
                        PlayerDAO.subvnd(player, goldTrade*coinGold);
                        short ruby = 861;
                        Item thoiVang =ItemService.gI().createNewItem(ruby,goldTrade*2000);// x3
                        InventoryServiceNew.gI().addItemBag(player,thoiVang);
                       InventoryServiceNew.gI().sendItemBags(player);
                        Service.getInstance().sendThongBaoOK(player, "bạn nhận được " +goldTrade*ratioGold
                         +" " + thoiVang.template.name);
                        GirlkunDB.executeUpdate("update account set active = 1 where id = ? and username = ?",
                    player.getSession().userId, player.getSession().uu);
//                    player.iDMark.setActive(true);
                    player.pointPvp+=goldTrade;
                    }else{
                        Service.getInstance().sendThongBao(player, "Số tiền của bạn là "+player.getSession().vnd+ "không đủ để quy"
                                + "đổi" +goldTrade+ " thỏi vàng" + " "+ "bạn cần thêm "+(player.getSession().vnd-goldTrade));
                        
                    }
                    break;}
                case QUY_DOI_COIN:
                    int ratioGold = 4; // tỉ lệ đổi tv
                    int coinGold = 1000; // là cái loz
                    int goldTrade = Integer.parseInt(text[0]);
                    if(goldTrade<=0 || goldTrade> 500)
                    {
                       Service.getInstance().sendThongBao(player, "Quá giới hạn mỗi lần chỉ được 500");
                    }
                    else if(player.getSession().vnd >= goldTrade*coinGold){
                        PlayerDAO.subvnd(player, goldTrade*coinGold);
                        Item thoiVang =ItemService.gI().createNewItem((short)457,goldTrade*4);// x3
                        InventoryServiceNew.gI().addItemBag(player,thoiVang);
                       InventoryServiceNew.gI().sendItemBags(player);
                        Service.getInstance().sendThongBaoOK(player, "bạn nhận được " +goldTrade*ratioGold
                         +" " + thoiVang.template.name);
                    GirlkunDB.executeUpdate("update account set active = 1 where id = ? and username = ?",
                    player.getSession().userId, player.getSession().uu);
                    player.pointPvp+=goldTrade;
        
                    }else{
                        Service.getInstance().sendThongBao(player, "Số tiền của bạn là "+player.getSession().vnd+ "không đủ để quy"
                                + "đổi" +goldTrade+ " thỏi vàng" + " "+ "bạn cần thêm "+(player.getSession().vnd-goldTrade));
                    }
                    break;
                
                    case QUY_MH:
                    int ratioMH = 200; // tỉ lệ đổi tv
                    int coinMH = 1000; // là cái loz
                    int MHTrade = Integer.parseInt(text[0]);
                    if(MHTrade<=0 || MHTrade>= 50000000)
                    {
                       Service.gI().sendThongBao(player, "giới hạn");
                    }
                    else if(player.getSession().vnd >= MHTrade*coinMH){
                        PlayerDAO.subvnd(player, MHTrade*coinMH);
                        Item thoiVang =ItemService.gI().createNewItem((short)935,MHTrade*200);// x4
                        InventoryServiceNew.gI().addItemBag(player,thoiVang);
                       InventoryServiceNew.gI().sendItemBags(player);
                        Service.gI().sendThongBao(player, "bạn nhận được " +MHTrade*ratioMH
                         +" " + thoiVang.template.name);
                    }else{
                        Service.gI().sendThongBao(player, "Số tiền của bạn là " +player.getSession().vnd+ " không đủ để quy "
                                + " đổi " + MHTrade + " MẢNH HỒN" + " " + "bạn cần thêm" +(player.getSession().vnd-MHTrade));
                    }
                     case QUY_DXV:
                    int ratioDXV = 1; // tỉ lệ đổi tv
                    int coinDXV = 1; // là cái loz
                    int DXVTrade = Integer.parseInt(text[0]);
                    if(DXVTrade<=0 || DXVTrade>= 50000000)
                    {
                       Service.gI().sendThongBao(player, "giới hạn");
                    }
                    else if(player.getSession().vnd >= DXVTrade*coinDXV){
                        PlayerDAO.subvnd(player, DXVTrade*coinDXV);
                        Item thoiVang =ItemService.gI().createNewItem((short)1229,DXVTrade*1);// x4
                        InventoryServiceNew.gI().addItemBag(player,thoiVang);
                       InventoryServiceNew.gI().sendItemBags(player);
                        Service.gI().sendThongBao(player, "bạn nhận được " +DXVTrade*ratioDXV
                         +" " + thoiVang.template.name);
                    }else{
                        Service.gI().sendThongBao(player, "Số tiền của bạn là " +player.getSession().vnd+ " không đủ để quy "
                                + " đổi " + DXVTrade + " MẢNH HỒN" + " " + "bạn cần thêm" +(player.getSession().vnd-DXVTrade));
                    }
                    break;
                    case QUY_DXL:
                    int ratioDXL = 200; // tỉ lệ đổi tv
                    int coinDXL = 1000; // là cái loz
                    int DXLTrade = Integer.parseInt(text[0]);
                    if(DXLTrade<=0 || DXLTrade>= 50000000)
                    {
                       Service.gI().sendThongBao(player, "giới hạn");
                    }
                    else if(player.getSession().vnd >= DXLTrade*coinDXL){
                        PlayerDAO.subvnd(player, DXLTrade*coinDXL);
                        Item thoiVang =ItemService.gI().createNewItem((short)934,DXLTrade*200);// x4
                        InventoryServiceNew.gI().addItemBag(player,thoiVang);
                       InventoryServiceNew.gI().sendItemBags(player);
                        Service.gI().sendThongBao(player, "bạn nhận được " +DXLTrade*ratioDXL
                         +" " + thoiVang.template.name);
                    }else{
                        Service.gI().sendThongBao(player, "Số tiền của bạn là " +player.getSession().vnd+ " không đủ để quy "
                                + " đổi " + DXLTrade + " ĐÁ XANH LAM" + " " + "bạn cần thêm" +(player.getSession().vnd-DXLTrade));
                    }
                    break;
                   
            
            }
                } catch (Exception e) {
        }
    }

    public void createForm(Player pl, int typeInput, String title, SubInput... subInputs) {
        pl.iDMark.setTypeInput(typeInput);
        Message msg;
        try {
            msg = new Message(-125);
            msg.writer().writeUTF(title);
            msg.writer().writeByte(subInputs.length);
            for (SubInput si : subInputs) {
                msg.writer().writeUTF(si.name);
                msg.writer().writeByte(si.typeInput);
            }
            pl.sendMessage(msg);
            msg.cleanup();
        } catch (Exception e) {
        }
    }

    public void createForm(ISession session, int typeInput, String title, SubInput... subInputs) {
        Message msg;
        try {
            msg = new Message(-125);
            msg.writer().writeUTF(title);
            msg.writer().writeByte(subInputs.length);
            for (SubInput si : subInputs) {
                msg.writer().writeUTF(si.name);
                msg.writer().writeByte(si.typeInput);
            }
            session.sendMessage(msg);
            msg.cleanup();
        } catch (Exception e) {
        }
    }

   public void createFormChangePassword(Player pl) {
        createForm(pl, CHANGE_PASSWORD, "Quên Mật Khẩu", new SubInput("Nhập mật khẩu đã quên", PASSWORD),
                new SubInput("Mật khẩu mới", PASSWORD),
                new SubInput("Nhập lại mật khẩu mới", PASSWORD));
    }
    
    public void createFormGiveItem(Player pl) {
        createForm(pl, GIVE_IT, "Tặng vật phẩm", new SubInput("Tên", ANY),new SubInput("Id Item", ANY),new SubInput("Số lượng", ANY));
    }

    public void createFormGiftCode(Player pl) {
        createForm(pl, GIFT_CODE, "Giftcode Ngọc rồng Bokin", new SubInput("Gift-code", ANY));
    }

    public void createFormFindPlayer(Player pl) {
        createForm(pl, FIND_PLAYER, "Tìm kiếm người chơi", new SubInput("Tên người chơi", ANY));
    }
    public void TAI(Player pl) {
        createForm(pl, TAI, "Chọn số thỏi vàng đặt tài", new SubInput("Số thỏi vàng", ANY));
    }
    public void XIU(Player pl) {
        createForm(pl, XIU, "Chọn số thỏi vàng đặt xỉu", new SubInput("Số thỏi vàng", ANY));
    }

    public void createFormNapThe(Player pl, String loaiThe ,String menhGia) {
       LOAI_THE = loaiThe;
       MENH_GIA = menhGia;
        createForm(pl, NAP_THE, "Nạp thẻ", new SubInput("Số Seri", ANY), new SubInput("Mã thẻ", ANY));
    }
    
    public void createFormQDTV(Player pl) {
      
        createForm(pl, QUY_DOI_COIN, "Quy đổi thỏi vàng, giới hạn đổi không quá 500 ", new SubInput("Nhập số lượng muốn đổi", NUMERIC));
    }
    
    public void createFormQDHN(Player pl) {
     
        createForm(pl, QUY_DOI_HONG_NGOC, "Quy đổi hồng ngọc", new SubInput("Nhập số lượng muốn đổi", NUMERIC));
    }
    public void createFormChangeName(Player pl, Player plChanged) {
        PLAYER_ID_OBJECT.put((int) pl.id, plChanged);
        createForm(pl, CHANGE_NAME, "Đổi tên " + plChanged.name, new SubInput("Tên mới", ANY));
    }
    
    public void createFormChangeNameByItem(Player pl) {
        createForm(pl, CHANGE_NAME_BY_ITEM, "Đổi tên " + pl.name, new SubInput("Tên mới", ANY));
    }

    public void createFormChooseLevelBDKB(Player pl) {
        createForm(pl, CHOOSE_LEVEL_BDKB, "Chọn cấp độ", new SubInput("Cấp độ (1-110)", NUMERIC));
    }
    
    public void createFormChooseLevelKGHD(Player pl) {
        createForm(pl, CHOOSE_LEVEL_KGHD, "Chọn cấp độ", new SubInput("Cấp độ (1-110)", NUMERIC));
    }
        public void createFormChooseLevelCDRD(Player pl) {
        createForm(pl, CHOOSE_LEVEL_CDRD, "Chọn cấp độ", new SubInput("Cấp độ (1-110)", NUMERIC));
    }   
    public void createFormTradeRuongDongVang(Player pl) {
        createForm(pl, DOI_RUONG_DONG_VANG, "Nhập Số Lượng Muốn Đổi", new SubInput("Số Lượng", NUMERIC));
    }
      public void createFormTradeRuongDongVang2(Player pl) {
        createForm(pl, DOI_RUONG_DONG_VANG2, "Nhập Số Lượng Muốn Đổi", new SubInput("Số Lượng", NUMERIC));
    }
       public void createFormTradeDUAHAU(Player pl) {
        createForm(pl, DOIDUAHAU, "Nhập Số Lượng Hồng Ngọc Muốn Đổi"
                + " 2 tôm chiên xù = 1 Hồng Ngọc", new SubInput("Số Lượng", NUMERIC));
    }
       public void createFormQDMH(Player pl) {
     
        createForm(pl, QUY_MH, "Quy đổi Mảnh Hồn"
                + "\nNhập 10 Có nghĩa là  10.000đ"
                + "\nTỉ Lệ Quy Đổi 10.000đ = 2000 Mảnh Hồn"
                + "\nNạp tiền Tại: https://ngocrongmeo ", new SubInput("Nhập số lượng muốn đổi", NUMERIC));
    }
        public void createFormMAMNGUXQUA(Player pl) {
     
        createForm(pl, MAMNGUQUA, "Quy đổi vnd lấy bánh bao"
                + "\nKHÔNG CÀY THÌ MUA"
                + "\nTỉ Lệ Quy Đổi 10.000đ = 100 bánh bao"
                + "\nNạp tiền Tại: admin meo ", new SubInput("Nhập số lượng mâm ngũ quả muốn đổi", NUMERIC));
    }
    public void createFormMAMNGUXQUA1(Player pl) {
     
        createForm(pl, MAMNGUQUA1, "bạn có muốn đổi mâm ngũ quả không"
                + "\nsau khi có 5 loại quả"
                + "\nDưa Dấu , Xoài ,Sung , Na, Đu Đủ ,"
                + "\nTiến Hành đổi ", new SubInput("Nhập số lượng muốn đổi", NUMERIC));
    }   
     public void createFormDOIRUONGSK(Player pl) {
     
        createForm(pl, DOIRUONGSK, "bạn có muốn đổi capsule không"
                + "\nTÌM 20000 bánh bao"
                + "\nĐƯA CHO TA ,"
                + "\nTA SẼ CHO NGƯƠI RƯƠNG ", new SubInput("Nhập số lượng muốn đổi", NUMERIC));
         }  
       public void createFormQDDXL(Player pl) {
     
        createForm(pl, QUY_DXL, "Quy đổi Đá xanh lam"
                + "\nNhập 10 Có nghĩa là  10.000đ"
                + "\nTỉ Lệ Quy Đổi 10.000đ = 2000 Đá Xanh Lam"
                + "\nNạp tiền Tại: https://nrokenki.online ", new SubInput("Nhập số lượng muốn đổi", NUMERIC));
    }
       public void createFormQDDXV(Player pl) {
     
        createForm(pl, QUY_DXV, "Quy đổi Đồng XU Vàng"               
                + "\nTỉ Lệ Quy Đổi 10.000đ = 10000 Đồng Xu Vàng"
                + "\nNạp tiền Tại: https://nrokenki.online ", new SubInput("Nhập số lượng muốn đổi", NUMERIC));
    }
       public void createFormChangeTNSMServer(Player pl){
           createForm(pl, TNSMSERVER, "Tiềm năng sức mạnh Server hiện tại là " + Manager.RATE_EXP_SERVER, new SubInput ("Nhập tiềm năng sau: ", ANY));
       }
       public void createFormSendItem(Player pl) {
        createForm(pl, GIVE_ITEM, "Tặng vật phẩm (Chuỗi Options)", new SubInput("Tên", ANY),new SubInput("Id Item", ANY),new SubInput("Số lượng", ANY),new SubInput("ID OPTION (Cách nhau bởi dấu '-')", ANY),new SubInput("PARAM (Cách nhau bởi dấu '-')", ANY));
    }
    public static class SubInput {

        private String name;
        private byte typeInput;

        public SubInput(String name, byte typeInput) {
            this.name = name;
            this.typeInput = typeInput;
        }
    }

}
