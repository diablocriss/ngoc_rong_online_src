package com.girlkun.models.player;

import BoMong.BoMong;
import com.girlkun.card.Card;
import com.girlkun.models.map.MapMaBu.MapMaBu;
import com.girlkun.models.skill.PlayerSkill;

import java.util.List;

import com.girlkun.models.clan.Clan;
import com.girlkun.models.intrinsic.IntrinsicPlayer;
import com.girlkun.models.item.Item;
import com.girlkun.models.item.ItemTime;
import com.girlkun.models.npc.specialnpc.MagicTree;
import com.girlkun.consts.ConstPlayer;
import com.girlkun.consts.ConstTask;
import com.girlkun.models.npc.specialnpc.MabuEgg;
import com.girlkun.models.mob.MobMe;
import com.girlkun.data.DataGame;
import com.girlkun.models.clan.ClanMember;
import com.girlkun.models.map.BanDoKhoBau.BanDoKhoBauService;
import com.girlkun.models.map.Mapchichi.Map22h;
import com.girlkun.models.map.TrapMap;
import com.girlkun.models.map.Zone;
import com.girlkun.models.map.blackball.BlackBallWar;
import com.girlkun.models.matches.IPVP;
import com.girlkun.models.matches.TYPE_LOSE_PVP;
import com.girlkun.models.skill.Skill;
import com.girlkun.server.Manager;
import com.girlkun.services.Service;
import com.girlkun.server.io.MySession;
import com.girlkun.models.task.TaskPlayer;
import com.girlkun.network.io.Message;
import com.girlkun.server.Client;
import com.girlkun.services.EffectSkillService;
import com.girlkun.services.FriendAndEnemyService;
import com.girlkun.services.PetService;
import com.girlkun.services.TaskService;
import com.girlkun.services.func.ChangeMapService;
import com.girlkun.services.func.ChonAiDay;
import com.girlkun.services.func.CombineNew;
import com.girlkun.services.func.TopService;
import com.girlkun.utils.Logger;
import com.girlkun.utils.Util;

import java.util.ArrayList;

public class Player {

    public MySession session;

    public boolean beforeDispose;

    public boolean isPet;
    public boolean isNewPet;
    public boolean isNewPet1;
    public boolean isBoss;
    public int NguHanhSonPoint = 0;
    public IPVP pvp;
    public int pointPvp;
    public int chuyenSinh;
    public byte maxTime = 30;
    public byte type = 0;
    public int kichhoat;
    public short idAura = -1;

    public int mapIdBeforeLogout;
    public List<Zone> mapBlackBall;
    public List<Zone> mapMaBu;

    public Zone zone;
    public Zone mapBeforeCapsule;
    public List<Zone> mapCapsule;
    public Pet pet;
    public NewPet newpet;
    //  public NewPet newpet1;
    public MobMe mobMe;
    public Location location;
    public SetClothes setClothes;
    public EffectSkill effectSkill;
    public MabuEgg mabuEgg;
    public TaskPlayer playerTask;
    public ItemTime itemTime;
    public Fusion fusion;
    public MagicTree magicTree;
    public IntrinsicPlayer playerIntrinsic;
    public Inventory inventory;
    public PlayerSkill playerSkill;
    public CombineNew combineNew;
    public IDMark iDMark;
    public Charms charms;
    public EffectSkin effectSkin;
    public Gift gift;
    public NPoint nPoint;
    public RewardBlackBall rewardBlackBall;
    public EffectFlagBag effectFlagBag;
    public FightMabu fightMabu;

    public Clan clan;
    public ClanMember clanMember;
    public SkillSpecial skillSpecial;

    public List<Friend> friends;
    public List<Enemy> enemies;

    public long id;
    public String name;
    public byte gender;
    public boolean isNewMember;
    public short head;

    public byte typePk;

    public byte cFlag;

    public boolean haveTennisSpaceShip;

    public boolean justRevived;
    public long lastTimeRevived;

    public int violate;
    public byte totalPlayerViolate;
    public long timeChangeZone;
    public long lastTimeUseOption;

    public short idNRNM = -1;
    public short idGo = -1;
    public long lastTimePickNRNM;
    public int goldNormar;
    public int goldVIP;
    public long lastTimeWin;
    public boolean isWin;
    public long limitgold;
    public int levelWoodChest;
    public boolean receivedWoodChest;
    public int goldChallenge;
    public int gemChallenge;
    public List<Card> Cards = new ArrayList<>();
    public long lasttimeuseoption;
    public BoMong achievement;

    public Player() {
        lastTimeUseOption = System.currentTimeMillis();
        location = new Location();
        nPoint = new NPoint(this);
        inventory = new Inventory();
        playerSkill = new PlayerSkill(this);
        setClothes = new SetClothes(this);
        effectSkill = new EffectSkill(this);
        fusion = new Fusion(this);
        playerIntrinsic = new IntrinsicPlayer();
        rewardBlackBall = new RewardBlackBall(this);
        effectFlagBag = new EffectFlagBag();
        fightMabu = new FightMabu(this);
        //----------------------------------------------------------------------
        iDMark = new IDMark();
        combineNew = new CombineNew();
        playerTask = new TaskPlayer();
        friends = new ArrayList<>();
        enemies = new ArrayList<>();
        itemTime = new ItemTime(this);
        charms = new Charms();
        gift = new Gift(this);
        effectSkin = new EffectSkin(this);
        skillSpecial = new SkillSpecial(this);
        achievement = new BoMong(this);
    }

    //--------------------------------------------------------------------------
    public boolean isDie() {
        if (this.nPoint != null) {
            return this.nPoint.hp <= 0;
        }
        return true;
    }

    //--------------------------------------------------------------------------
    public void setSession(MySession session) {
        this.session = session;
    }

    public void sendMessage(Message msg) {
        if (this.session != null) {
            session.sendMessage(msg);
        }
    }

    public MySession getSession() {
        return this.session;
    }

    public boolean isPl() {
        return !isPet && !isBoss && !isNewPet;
    }

    public void update() {
        if (!this.beforeDispose) {
            try {
                if (!iDMark.isBan()) {

                    if (nPoint != null) {
                        nPoint.update();
                    }
                    if (fusion != null) {
                        fusion.update();
                    }
                    if (effectSkin != null) {
                        effectSkill.update();
                    }
                    if (mobMe != null) {
                        mobMe.update();
                    }
                    if (effectSkin != null) {
                        effectSkin.update();
                    }
                    if (pet != null) {
                        pet.update();
                    }
                    if (newpet != null) {
                        newpet.update();
                    }
                    if (magicTree != null) {
                        magicTree.update();
                    }
                    if (itemTime != null) {
                        itemTime.update();
                    }
                    BlackBallWar.gI().update(this);
                    MapMaBu.gI().update(this);
                    Map22h.gI().update(this);
                    if (!isBoss && this.iDMark.isGotoFuture() && Util.canDoWithTime(this.iDMark.getLastTimeGoToFuture(), 6000)) {
                        ChangeMapService.gI().changeMapBySpaceShip(this, 102, -1, 200);
                        this.iDMark.setGotoFuture(false);
                    }
                    if (!isBoss && this.iDMark.isGoToBDKB() && Util.canDoWithTime(this.iDMark.getLastTimeGoToBDKB(), 6000)) {
                        ChangeMapService.gI().changeMapInYard(this, 135, -1, 100);
                        this.iDMark.setGoToBDKB(false);
                    }
                    if (!isBoss && this.iDMark.isGoToKGHD() && Util.canDoWithTime(this.iDMark.getLastTimeGoToKGHD(), 6000)) {
                        ChangeMapService.gI().changeMapInYard(this, 149, -1, 100);
                        this.iDMark.setGoToKGHD(false);
                    }
                    if (!isBoss && this.iDMark.isGoToCDRD() && Util.canDoWithTime(this.iDMark.getLastTimeGoToKGHD(), 10000)) {
                        ChangeMapService.gI().changeMapInYard(this, 143, -1, 1108);
                        this.iDMark.setGoToCDRD(false);
                    }
                    if (this.zone != null) {
                        TrapMap trap = this.zone.isInTrap(this);
                        if (trap != null) {
                            trap.doPlayer(this);
                        }
                    }
                    if (this.isPl() && this.inventory.itemsBody.get(7) != null) {
                        Item it = this.inventory.itemsBody.get(7);
                        if (it != null && it.isNotNullItem() && this.newpet == null) {
                            switch (it.template.id) {
                                case 1407: // Con cún vàng
                                    PetService.Pet2(this, 663, 664, 665);
                                    Service.gI().point(this);
                                    break;
                                case 1408: // Cua đỏ
                                    PetService.Pet2(this, 1074, 1075, 1076);
                                    Service.gI().point(this);
                                    break;
                                case 1409: // Bí ma
                                    PetService.Pet2(this, 1158, 1159, 1160);
                                    Service.gI().point(this);
                                    break;
                                case 1410: // Bí ma vương
                                    PetService.Pet2(this, 1155, 1156, 1157);
                                    Service.gI().point(this);
                                    break;
                                case 1411: //Mèo đuôi vàng đen
                                    PetService.Pet2(this, 1183, 1184, 1185);
                                    Service.gI().point(this);
                                    break;
                                case 1412: //Mèo đuôi vàng trắng
                                    PetService.Pet2(this, 1201, 1202, 1203);
                                    Service.gI().point(this);
                                    break;
                                case 1311: //Gà 9 cự
                                    PetService.Pet2(this,  1419,1420,1421);
                                    Service.gI().point(this);
                                    break;
                                case 1312: //Ngựa 9 hồng mao
                                    PetService.Pet2(this,  1422,1423,1424);
                                    Service.gI().point(this);
                                    break;
                                case 1313: //Voi 9 ngà
                                    PetService.Pet2(this, 1425,1426,1427);
                                    Service.gI().point(this);
                                    break;
                                case 1416: //Minions
                                    PetService.Pet2(this, 1254, 1255, 1256);
                                    Service.gI().point(this);
                                    break;
                                case 1179: //Minions
                                    PetService.Pet2(this, 1347, 1348, 1349);
                                    Service.gI().point(this);
                                    break;

                            }
                        }
                    } else if (this.isPl() && newpet != null && !this.inventory.itemsBody.get(7).isNotNullItem()) {
                        newpet.dispose();
                        newpet = null;
                        // newpet1.dispose();
                        //  newpet1 = null;
                    }
                    if (location.lastTimeplayerMove < System.currentTimeMillis() - 30 * 60 * 1000) {
                        Client.gI().kickSession(getSession());
                    }
                } else {
                    if (Util.canDoWithTime(iDMark.getLastTimeBan(), 5000)) {
                        Client.gI().kickSession(session);
                    }
                }
            } catch (Exception e) {
                e.getStackTrace();
                Logger.logException(Player.class, e, "Lỗi tại player: " + this.name);
            }
        }
    }

    //--------------------------------------------------------------------------
    /*
     * {380, 381, 382}: ht lưỡng long nhất thể xayda trái đất
     * {383, 384, 385}: ht porata xayda trái đất
     * {391, 392, 393}: ht namếc
     * {870, 871, 872}: ht c2 trái đất
     * {873, 874, 875}: ht c2 namếc
     * {867, 878, 869}: ht c2 xayda
     * {2033,2034,2035}: ht c3 td
     * {2030,2031,2032}: ht c3 nm
     * {2027,2028,2029}: ht c3 xd*/
    private static final short[][] idOutfitFusion = {
        {380, 381, 382}, {383, 384, 385}, {391, 392, 393},//luong long nhat the, va cap 1
        {870, 871, 872}, {873, 874, 875}, {867, 868, 869},//bt cap 2
        {630, 631, 632}, {633, 634, 635}, {627, 628, 629}, //bt cap 3
        {2059, 2060, 2061}, {2094, 2095, 2096}, {2053, 2054, 2055},//bt cap 4
        {1271, 1272, 1273}, {1277, 1278, 1279}, {1274, 1275, 1276},//bt cap 5
    };

    public byte getAura() {
        Card card = this.Cards.stream().filter(r -> r != null && r.Used == 1).findFirst().orElse(null);
        if (card != null) {
            if (card.Id == 1142 && card.Used == 1 && card.Level >= 1) {
                return 1;
            }
            if (card.Id == 956 && card.Used == 1 && card.Level >= 1) {
                return 0;
            }

        }
        if (this.inventory.itemsBody.isEmpty() || this.inventory.itemsBody.size() < 10) {
            return -1;
        }
        Item item = this.inventory.itemsBody.get(5);
        if (!item.isNotNullItem()) {
            return -1;
        }
        if (item.template.id == 1121) {
            return 10;//
        } else if (item.template.id == 282) {
            return 1;
        } else if (item.template.id == 283) {
            return 2;
        } else if (item.template.id == 284) {
            return 3;
        } else if (item.template.id == 285) {
            return 4;
        } else if (item.template.id == 286) {
            return 5;
        } else if (item.template.id == 287) {
            return 6;
        } else if (item.template.id == 288) {
            return 7;
        } else if (item.template.id == 289) {
            return 8;
        } else if (item.template.id == 290) {
            return 9;
        } else if (item.template.id == 291) {
            return 10;
        } else if (item.template.id == 292) {
            return 11;
        } else if (item.template.id == 386) {
            return 12;
        } else if (item.template.id == 387) {
            return 6;
        } else if (item.template.id == 388) {
            return 7;
        } else if (item.template.id == 389) {
            return 8;
        } else if (item.template.id == 390) {
            return 9;
        } else if (item.template.id == 391) {
            return 10;
        } else if (item.template.id == 392) {
            return 11;
        } else if (item.template.id == 393) {
            return 12;
        } else if (item.template.id == 968) {
            return 20;
        } else if (item.template.id == 969) {
            return 22;
        } else if (item.template.id == 970) {
            return 23;
        } else if (item.template.id == 971) {
            return 24;
        } else if (item.template.id == 972) {
            return 25;
            } else if (item.template.id == 1284) {
            return 24;
            } else if (item.template.id == 1322) {
            return 17;
        } else if (item.template.id == 1321) {
            return 20;
            } else if (item.template.id == 1320) {
            return 23;
        } else if (item.template.id == 1285) {
            return 2;
        } else if (item.template.id == 1286) {
            return 3;
        } else if (item.template.id == 1287) {
            return 4;
        } else if (item.template.id == 1288) {
            return 5;
        } else if (item.template.id == 1289) {
            return 6;
        } else if (item.template.id == 1290) {
            return 7;
        } else if (item.template.id == 1291) {
            return 8;
        } else if (item.template.id == 1292) {
            return 9;
        } else if (item.template.id == 1293) {
            return 10;
        } else if (item.template.id == 1294) {
            return 11;
        } else if (item.template.id == 1295) {
            return 12;
        } else if (item.template.id == 1296) {
            return 6;
        } else if (item.template.id == 1297) {
            return 7;
        } else if (item.template.id == 1298) {
            return 8;
        } else if (item.template.id == 1299) {
            return 9;
        } else if (item.template.id == 1300) {
            return 10;
        } else if (item.template.id == 1301) {
            return 11;
        } else if (item.template.id == 1302) {
            return 12;
        } else if (item.template.id == 1303) {
            return 20;
        } else if (item.template.id == 1304) {
            return 22;
        } else if (item.template.id == 1305) {
            return 23;
        } else if (item.template.id == 1306) {
            return 24;
        } else if (item.template.id == 1307) {
            return 25;
            } else if (item.template.id == 1308) {
            return 24;
            } else if (item.template.id == 1309) {
            return 17;
        } else if (item.template.id == 1310) {
            return 20;
            } else if (item.template.id == 1311) {
            return 23;
            } else if (item.template.id == 1312) {
            return 23;
            } else if (item.template.id == 1313) {
            return 23;
            } else if (item.template.id == 1314) {
            return 23;
            } else if (item.template.id == 1315) {
            return 23;
            } else if (item.template.id == 1316) {
            return 23;
            } else if (item.template.id == 1317) {
            return 23;
            } else if (item.template.id == 1318) {
            return 23;
            } else if (item.template.id == 1319) {
            return 23;
            } else if (item.template.id == 719) {
            return 22;
        } else {
            return -1;
        }
    }

    public byte getEffFront() {
        return -1;
    }

    public short getHead() {
        if (effectSkill != null && effectSkill.isMonkey) {
            return (short) ConstPlayer.HEADMONKEY[effectSkill.levelMonkey - 1];
        } else if (effectSkill != null && effectSkill.isSocola) {
            return 412;
        } else if (fusion != null && fusion.typeFusion != ConstPlayer.NON_FUSION) {
            if (fusion.typeFusion == ConstPlayer.LUONG_LONG_NHAT_THE) {
                return idOutfitFusion[this.gender == ConstPlayer.NAMEC ? 2 : 0][0];
            } else if (fusion.typeFusion == ConstPlayer.HOP_THE_PORATA) {
//                if (this.pet.typePet == 1) {
//                    return idOutfitFusion[3 + this.gender][0];
//                }
                return idOutfitFusion[this.gender == ConstPlayer.NAMEC ? 2 : 1][0];
            } else if (fusion.typeFusion == ConstPlayer.HOP_THE_PORATA2) {
//                if (this.pet.typePet == 1) {
//                    return idOutfitFusion[3 + this.gender][0];
//                }
                return idOutfitFusion[3 + this.gender][0];
            } else if (fusion.typeFusion == ConstPlayer.HOP_THE_PORATA3) {
//                if (this.pet.typePet == 1) {
//                    return idOutfitFusion[3 + this.gender][0];
//                }

                return idOutfitFusion[6 + this.gender][0];

            } else if (fusion.typeFusion == ConstPlayer.HOP_THE_PORATA4) {
//                if (this.pet.typePet == 1) {
//                    return idOutfitFusion[3 + this.gender][0];
//                }

                return idOutfitFusion[9 + this.gender][0];
            } else if (fusion.typeFusion == ConstPlayer.HOP_THE_PORATA5) {
//                if (this.pet.typePet == 1) {
//                    return idOutfitFusion[3 + this.gender][0];
//                }

                return idOutfitFusion[12 + this.gender][0];
            }
        } else if (inventory != null && inventory.itemsBody.get(5).isNotNullItem()) {
            int head = inventory.itemsBody.get(5).template.head;
            if (head != -1) {
                return (short) head;
            }
        }
        return this.head;
    }

    public short getBody() {
        if (effectSkill != null && effectSkill.isMonkey) {
            return 193;
        } else if (effectSkill != null && effectSkill.isSocola) {
            return 413;
        } else if (fusion != null && fusion.typeFusion != ConstPlayer.NON_FUSION) {
            if (fusion.typeFusion == ConstPlayer.LUONG_LONG_NHAT_THE) {
                return idOutfitFusion[this.gender == ConstPlayer.NAMEC ? 2 : 0][1];
            } else if (fusion.typeFusion == ConstPlayer.HOP_THE_PORATA) {
//                if (this.pet.typePet == 1) {
//                    return idOutfitFusion[3 + this.gender][1];
//                }
                return idOutfitFusion[this.gender == ConstPlayer.NAMEC ? 2 : 1][1];
            } else if (fusion.typeFusion == ConstPlayer.HOP_THE_PORATA2) {
//                if (this.pet.typePet == 1) {
//                    return idOutfitFusion[3 + this.gender][1];
                return idOutfitFusion[3 + this.gender][1];
            
         } else if (fusion.typeFusion == ConstPlayer.HOP_THE_PORATA3) {
//                if (this.pet.typePet == 1) {
//                    return idOutfitFusion[3 + this.gender][1];
//                }
                return idOutfitFusion[6 + this.gender][1];
            } else if (fusion.typeFusion == ConstPlayer.HOP_THE_PORATA4) {
//                if (this.pet.typePet == 1) {
//                    return idOutfitFusion[3 + this.gender][1];

                return idOutfitFusion[9 + this.gender][1];
            } else if (fusion.typeFusion == ConstPlayer.HOP_THE_PORATA5) {
//                if (this.pet.typePet == 1) {
//                    return idOutfitFusion[3 + this.gender][1];

                return idOutfitFusion[12 + this.gender][1];
            }
        } else if (inventory != null && inventory.itemsBody.get(5).isNotNullItem()) {
            int body = inventory.itemsBody.get(5).template.body;
            if (body != -1) {
                return (short) body;
            }
        }
        if (inventory != null && inventory.itemsBody.get(0).isNotNullItem()) {
            return inventory.itemsBody.get(0).template.part;
        }
        return (short) (gender == ConstPlayer.NAMEC ? 59 : 57);
    }

    public short getLeg() {
        if (effectSkill != null && effectSkill.isMonkey) {
            return 194;
        } else if (effectSkill != null && effectSkill.isSocola) {
            return 414;
        } else if (fusion != null && fusion.typeFusion != ConstPlayer.NON_FUSION) {
            if (fusion.typeFusion == ConstPlayer.LUONG_LONG_NHAT_THE) {
                return idOutfitFusion[this.gender == ConstPlayer.NAMEC ? 2 : 0][2];
            } else if (fusion.typeFusion == ConstPlayer.HOP_THE_PORATA) {
//                if (this.pet.typePet == 1) {
//                    return idOutfitFusion[3 + this.gender][2];
//                }
                return idOutfitFusion[this.gender == ConstPlayer.NAMEC ? 2 : 1][2];
            } else if (fusion.typeFusion == ConstPlayer.HOP_THE_PORATA2) {
//                if (this.pet.typePet == 1) {
//                    return idOutfitFusion[3 + this.gender][2];
//                }
                return idOutfitFusion[3 + this.gender][2];
           } else if (fusion.typeFusion == ConstPlayer.HOP_THE_PORATA3) {
//                if (this.pet.typePet == 1) {
//                    return idOutfitFusion[3 + this.gender][2];
//                }
                return idOutfitFusion[6 + this.gender][2];
            } else if (fusion.typeFusion == ConstPlayer.HOP_THE_PORATA4) {
//                if (this.pet.typePet == 1) {
//                    return idOutfitFusion[3 + this.gender][2];
//                }
                return idOutfitFusion[9 + this.gender][2];
            } else if (fusion.typeFusion == ConstPlayer.HOP_THE_PORATA5) {
//                if (this.pet.typePet == 1) {
//                    return idOutfitFusion[3 + this.gender][2];
//                }
                return idOutfitFusion[12 + this.gender][2];
            }
        } else if (inventory != null && inventory.itemsBody.get(5).isNotNullItem()) {
            int leg = inventory.itemsBody.get(5).template.leg;
            if (leg != -1) {
                return (short) leg;
            }
        }
        if (inventory != null && inventory.itemsBody.get(1).isNotNullItem()) {
            return inventory.itemsBody.get(1).template.part;
        }
        return (short) (gender == 1 ? 60 : 58);
    }

    public short getFlagBag() {
        if (this.iDMark.isHoldBlackBall()) {
            return 31;
        } else if (this.idNRNM >= 353 && this.idNRNM <= 359) {
            return 30;
        }
        if (this.inventory.itemsBody.size() == 11) {
            if (this.inventory.itemsBody.get(8).isNotNullItem()) {
                return this.inventory.itemsBody.get(8).template.part;
            }
        }
        if (TaskService.gI().getIdTask(this) == ConstTask.TASK_3_2) {
            return 28;
        }
        if (this.clan != null) {
            return (short) this.clan.imgId;
        }
        return -1;
    }

    public short getMount() {
        if (this.inventory.itemsBody.isEmpty() || this.inventory.itemsBody.size() < 10) {
            return -1;
        }
        Item item = this.inventory.itemsBody.get(9);
        if (!item.isNotNullItem()) {
            return -1;
        }
        if (item.template.type == 24) {
            if (item.template.gender == 3 || item.template.gender == this.gender) {
                return item.template.id;
            } else {
                return -1;
            }
        } else {
            if (item.template.id < 500) {
                return item.template.id;
            } else {
                return (short) DataGame.MAP_MOUNT_NUM.get(String.valueOf(item.template.id));
            }
        }
    }

    //--------------------------------------------------------------------------
    public int injured(Player plAtt, int damage, boolean piercing, boolean isMobAttack) {
        if (!this.isDie()) {
            if (this.pet != null && this.pet.status == Pet.PROTECT) {
                this.pet.playerAttack = plAtt;
            }
            boolean isSkillChuong = false;
            if (plAtt != null) {
                switch (plAtt.playerSkill.skillSelect.template.id) {
                    case Skill.KAMEJOKO:
                    case Skill.MASENKO:
                    case Skill.ANTOMIC:
                        if (this.nPoint.voHieuChuong > 0) {
                            com.girlkun.services.PlayerService.gI().hoiPhuc(this, 0, damage * this.nPoint.voHieuChuong / 100);
                            return 0;
                        }
                        isSkillChuong = true;
                }
            }
            if (!piercing && Util.isTrue(this.nPoint.tlNeDon, 100)) {
                return 0;
            }
            damage = (int) this.nPoint.subDameInjureWithDeff(damage);
            if (!piercing && effectSkill.isShielding) {
                if (damage > nPoint.hpMax) {
                    EffectSkillService.gI().breakShield(this);
                }
                damage = 1;
            }
            if (isMobAttack && this.charms.tdBatTu > System.currentTimeMillis() && damage >= this.nPoint.hp) {
                damage = (int) (this.nPoint.hp - 1);
            }
            if (plAtt != null) {
                if (isSkillChuong && plAtt.nPoint != null && plAtt.nPoint.multicationChuong > 0 && Util.canDoWithTime(plAtt.nPoint.lastTimeMultiChuong, PlayerSkill.TIME_MUTIL_CHUONG)) {
                    damage *= plAtt.nPoint.multicationChuong;
                    plAtt.nPoint.lastTimeMultiChuong = System.currentTimeMillis();
                }
            }

            this.nPoint.subHP(damage);
            if (isDie()) {
                if (this.zone.map.mapId == 129 && plAtt != null) {
                    plAtt.pointPvp++;
                }

                if (plAtt != null && plAtt.isPl() && this.isPl()) {
                    TaskService.gI().checkDoneTaskPvP(plAtt);
                }

                setDie(plAtt);
            }

            return damage;
        } else {
            return 0;
        }
    }

    protected void setDie(Player plAtt) {
        // Xóa phù
        if (this.effectSkin != null && this.effectSkin.xHPKI > 1) {
            this.effectSkin.xHPKI = 1;
            Service.getInstance().point(this);
        }

        // Xóa tụ skill đặc biệt
        this.playerSkill.prepareQCKK = false;
        this.playerSkill.prepareLaze = false;
        this.playerSkill.prepareTuSat = false;

        // Xóa hiệu ứng skill
        if (this.effectSkill != null) {
            this.effectSkill.removeSkillEffectWhenDie();
        }

        // Xóa HP và MP
        this.nPoint.setHp(0);
        this.nPoint.setMp(0);

        // Xóa trứng
        if (this.mobMe != null) {
            this.mobMe.mobMeDie();
        }

        // Gọi Service xử lý khi nhân vật chết
        Service.getInstance().charDie(this);

        // Thêm kẻ thù
        if (!this.isPet && !this.isNewPet && !this.isNewPet1 && !this.isBoss && plAtt != null && !plAtt.isPet && !plAtt.isNewPet && !plAtt.isNewPet1 && !plAtt.isBoss) {
            if (!plAtt.itemTime.isUseAnDanh) {
                FriendAndEnemyService.gI().addEnemy(this, plAtt);
            }

            // Xử lý nếu cả hai đều là người chơi
            if (this.isPl() && plAtt.isPl()) {
                plAtt.achievement.plusCount(3);
            }
        }

        // Kết thúc PK
        if (this.pvp != null) {
            this.pvp.lose(this, TYPE_LOSE_PVP.DEAD);
        }

        // Drop Black Ball
        BlackBallWar.gI().dropBlackBall(this);
    }

    //--------------------------------------------------------------------------
    public void setClanMember() {
        if (this.clanMember != null) {
            this.clanMember.powerPoint = this.nPoint.power;
            this.clanMember.head = this.getHead();
            this.clanMember.body = this.getBody();
            this.clanMember.leg = this.getLeg();
        }
    }

    public boolean isAdmin() {
        return this.session.isAdmin;
    }

    public void setJustRevivaled() {
        this.justRevived = true;
        this.lastTimeRevived = System.currentTimeMillis();
    }

    public void preparedToDispose() {

    }

    public void dispose() {
        if (pet != null) {
            pet.dispose();
            pet = null;
        }
        if (newpet != null) {
            newpet.dispose();
            newpet = null;
        }
        //  if (newpet1 != null) {
        //    newpet1.dispose();
        //     newpet1 = null;
        //}
        if (mapBlackBall != null) {
            mapBlackBall.clear();
            mapBlackBall = null;
        }
        zone = null;
        mapBeforeCapsule = null;
        if (mapMaBu != null) {
            mapMaBu.clear();
            mapMaBu = null;
        }
        zone = null;
        mapBeforeCapsule = null;
        if (mapCapsule != null) {
            mapCapsule.clear();
            mapCapsule = null;
        }
        if (mobMe != null) {
            mobMe.dispose();
            mobMe = null;
        }
        location = null;
        if (setClothes != null) {
            setClothes.dispose();
            setClothes = null;
        }
        if (effectSkill != null) {
            effectSkill.dispose();
            effectSkill = null;
        }
        if (mabuEgg != null) {
            mabuEgg.dispose();
            mabuEgg = null;
        }
        if (skillSpecial != null) {
            skillSpecial.dispose();
            skillSpecial = null;
        }
        if (playerTask != null) {
            playerTask.dispose();
            playerTask = null;
        }
        if (itemTime != null) {
            itemTime.dispose();
            itemTime = null;
        }
        if (fusion != null) {
            fusion.dispose();
            fusion = null;
        }
        if (magicTree != null) {
            magicTree.dispose();
            magicTree = null;
        }
        if (playerIntrinsic != null) {
            playerIntrinsic.dispose();
            playerIntrinsic = null;
        }
        if (inventory != null) {
            inventory.dispose();
            inventory = null;
        }
        if (playerSkill != null) {
            playerSkill.dispose();
            playerSkill = null;
        }
        if (combineNew != null) {
            combineNew.dispose();
            combineNew = null;
        }
        if (iDMark != null) {
            iDMark.dispose();
            iDMark = null;
        }
        if (charms != null) {
            charms.dispose();
            charms = null;
        }
        if (effectSkin != null) {
            effectSkin.dispose();
            effectSkin = null;
        }
        if (gift != null) {
            gift.dispose();
            gift = null;
        }
        if (nPoint != null) {
            nPoint.dispose();
            nPoint = null;
        }
        if (rewardBlackBall != null) {
            rewardBlackBall.dispose();
            rewardBlackBall = null;
        }
        if (effectFlagBag != null) {
            effectFlagBag.dispose();
            effectFlagBag = null;
        }
        if (pvp != null) {
            pvp.dispose();
            pvp = null;
        }
        effectFlagBag = null;
        clan = null;
        clanMember = null;
        friends = null;
        enemies = null;
        session = null;
        name = null;
    }

    public String percentGold(int type) {
        try {
            if (type == 0) {
                double percent = ((double) this.goldNormar / ChonAiDay.gI().goldNormar) * 100;
                return String.valueOf(Math.ceil(percent));
            } else if (type == 1) {
                double percent = ((double) this.goldVIP / ChonAiDay.gI().goldVip) * 100;
                return String.valueOf(Math.ceil(percent));
            }
        } catch (ArithmeticException e) {
            return "0";
        }
        return "0";
    }

    public List<String> textRuongGo = new ArrayList<>();
}
//nplayer
