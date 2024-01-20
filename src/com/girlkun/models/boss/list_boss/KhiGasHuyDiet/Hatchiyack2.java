package com.girlkun.models.boss.list_boss.KhiGasHuyDiet;

import com.girlkun.models.boss.BossData;
import com.girlkun.models.boss.BossManager;
import com.girlkun.models.boss.BossID;
import com.girlkun.models.boss.Boss;
import com.girlkun.consts.ConstPlayer;
import com.girlkun.models.item.Item;
import com.girlkun.models.map.BanDoKhoBau.BanDoKhoBau;
import com.girlkun.models.map.ItemMap;
import com.girlkun.models.map.Zone;
import com.girlkun.models.player.Player;
import com.girlkun.models.skill.Skill;
import com.girlkun.services.Service;
import com.girlkun.utils.Util;


public class Hatchiyack2 extends Boss {
    private static final int[][] FULL_DEMON = new int[][]{{Skill.DEMON, 1}, {Skill.DEMON, 2}, {Skill.DEMON, 3}, {Skill.DEMON, 4}, {Skill.DEMON, 5}, {Skill.DEMON, 6}, {Skill.DEMON, 7}};

    public Hatchiyack2(Zone zone , int level, int dame, int hp) throws Exception {
        super(BossID.TRUNG_UY_XANH_LO, new BossData(
                "Hatchiyack",
                ConstPlayer.TRAI_DAT,
                new short[]{639, 640, 641, -1, -1, -1},
                ((10000 + dame) * level),
                new int[]{((500000 + hp) * level)},
                new int[]{103},
                (int[][]) Util.addArray(FULL_DEMON),
                new String[]{},
                new String[]{"|-1|Nhóc con"},
                new String[]{},
                60
        ));
        this.zone = zone;
    }

    public void reward(Player plKill) {
        if (Util.isTrue(100,100)) {
            ItemMap it = new ItemMap(this.zone, 729, 1, this.location.x, this.zone.map.yPhysicInTop(this.location.x,
                    this.location.y - 729), plKill.id);
            it.options.add(new Item.ItemOption(50, Util.nextInt(1, 45)));
            it.options.add(new Item.ItemOption(77, Util.nextInt(1, 45)));
            it.options.add(new Item.ItemOption(103, Util.nextInt(1, 45)));
            it.options.add(new Item.ItemOption(5, Util.nextInt(1, 20)));
            it.options.add(new Item.ItemOption(93, Util.nextInt(1, 3)));
            Service.getInstance().dropItemMap(this.zone, it);
        }
    }
    @Override
    public void active() {
        super.active();
    }

    @Override
    public void joinMap() {
        super.joinMap();
    }

    @Override
    public void leaveMap() {
        super.leaveMap();
        BossManager.gI().removeBoss(this);
        this.dispose();
    }
}