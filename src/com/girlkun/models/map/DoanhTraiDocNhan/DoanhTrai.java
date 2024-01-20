package com.girlkun.models.map.DoanhTraiDocNhan;

import com.girlkun.models.clan.Clan;
import com.girlkun.models.map.TrapMap;
import com.girlkun.models.map.Zone;
import com.girlkun.models.mob.Mob;
import com.girlkun.models.player.Player;
import com.girlkun.services.ItemTimeService;
import com.girlkun.services.MapService;
import com.girlkun.services.Service;
import com.girlkun.services.func.ChangeMapService;
import com.girlkun.utils.Logger;
import com.girlkun.utils.Util;

import java.util.ArrayList;
import java.util.List;
//import static jdk.jfr.internal.consumer.EventLog.update;

/**
 *
  @author BTH

 *
 */
public class DoanhTrai { 

   
    public static final List<DoanhTrai> DOANH_TRAI;
    public static final int MAX_AVAILABLE = 500;
    public static final int TIME_DOANH_TRAI = 1800000;
    public static int N_PLAYER_MAP = 0;
    public static final int N_PLAYER_CLAN = 0;
    private Player player;
    
    
 
    static {
        DOANH_TRAI = new ArrayList<>();
        for (int i = 0; i < MAX_AVAILABLE; i++) {
            DOANH_TRAI.add(new DoanhTrai(i));
        }
    }

    public int id;
    public byte level;
    public final List<Zone> zones;

    public Clan clan;
    public boolean isOpened;
    private long lastTimeOpen;
    private boolean running;
    private long lastTimeUpdate;

    public DoanhTrai(int id) {
        this.id = id;
        this.zones = new ArrayList<>();
        running = true;
        //new Thread(this).start();
    }
    
    public void run() {
        while (running) {
            try {
                Thread.sleep(10000);
                if (Util.canDoWithTime(lastTimeUpdate, 10000)) {
//                    update();
                    lastTimeUpdate = System.currentTimeMillis();
                }
            } catch (Exception ignored) {
            }

        }
    }

    public void opendoanhtrai(Player plOpen, Clan clan) {
        this.lastTimeOpen = System.currentTimeMillis();
        this.isOpened = true;
        this.clan = clan;
        this.clan.timeOpenDoanhTrai = this.lastTimeOpen;
        this.clan.playerOpenDoanhTrai = plOpen;
        this.clan.doanhTrai = this;
        ChangeMapService.gI().changeMapInYard(plOpen, 53, -1, 60);
        resetDT();
        sendTextDoanhTrai();
    }
    
    private void kickOutOfBDKB(Player player) {
        if (MapService.gI().isMapDoanhTrai(player.zone.map.mapId)) {
            Service.getInstance().sendThongBao(player, "Doanh Trại Đã Kết Thúc Bạn Đang Được Đưa Ra Ngoài");
            ChangeMapService.gI().changeMapBySpaceShip(player, 27, -1, 1038);
            running = false;
            this.clan.BanDoKhoBau = null;
        }
    }

    private void resetDT() {
        long totalDame = 0;
        long totalHp = 0;
        for(Player pl : this.clan.membersInGame){
            totalDame += pl.nPoint.dame;
            totalHp += pl.nPoint.hpMax;
        }
        if (totalDame * 20 >= Integer.MAX_VALUE){
            totalDame = Integer.MAX_VALUE/20;
        }
        if (totalHp >= Integer.MAX_VALUE){
            totalHp = Integer.MAX_VALUE;
        }
        for(Zone zone : this.zones){
            for(Mob mob : zone.mobs){
                mob.point.dame = (int) (totalHp / 20);
                mob.point.maxHp = (int) (totalDame * 20);
                mob.hoiSinh();
            }
        }
    }
    public Zone getMapById(int mapId) {
        for (Zone zone : zones) {
            if (zone.map.mapId == mapId) {
                return zone;
            }
        }
        return null;
    }

    public static void addZone(int idBanDo, Zone zone) {
        DOANH_TRAI.get(idBanDo).zones.add(zone);
    }

    private void sendTextDoanhTrai() {
        for (Player pl : this.clan.membersInGame) {
            ItemTimeService.gI().sendTextDoanhTrai(pl);
        }
    }
}