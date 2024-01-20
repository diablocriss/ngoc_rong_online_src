package com.girlkun.models.boss.list_boss.Caitranganime;

import com.girlkun.models.boss.Boss;
import com.girlkun.models.boss.BossID;
import com.girlkun.models.boss.BossStatus;
import com.girlkun.models.boss.BossesData;
import com.girlkun.models.map.ItemMap;
import com.girlkun.models.player.Player;
import com.girlkun.services.EffectSkillService;
import com.girlkun.services.Service;
import com.girlkun.services.TaskService;
import com.girlkun.utils.Util;
import java.util.Random;


public class Anime3 extends Boss {

    public Anime3() throws Exception {
        super(BossID.Anime_33, BossesData.Anime_3);
}
     @Override
    public void reward(Player plKill) {
        if (Util.isTrue(100,100)) {
            if (Util.isTrue(100, 100)) {
                if (Util.isTrue(100, 1)) {
                    if (Util.isTrue(100, 100)) {
                }
            }
            ItemMap it = new ItemMap(this.zone, 2148, 1, this.location.x, this.zone.map.yPhysicInTop(this.location.x,
                    this.location.y - 24), plKill.id);
            ItemMap it1 = new ItemMap(this.zone, 2149, 3, this.location.x, this.zone.map.yPhysicInTop(this.location.x,
                    this.location.y - 24), plKill.id);
            ItemMap it2 = new ItemMap(this.zone, 2150, 3, this.location.x, this.zone.map.yPhysicInTop(this.location.x,
                    this.location.y - 24), plKill.id);
            ItemMap it3 = new ItemMap(this.zone, 2151, 3, this.location.x, this.zone.map.yPhysicInTop(this.location.x,
                    this.location.y - 24), plKill.id);
            Service.getInstance().dropItemMap(this.zone, it);
            Service.getInstance().dropItemMap(this.zone, it1);
            Service.getInstance().dropItemMap(this.zone, it2);
            Service.getInstance().dropItemMap(this.zone, it3);
        
}
    }
}
}
  
   
