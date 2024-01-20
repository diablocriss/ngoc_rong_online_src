/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.girlkun.models.boss.list_boss.NgucTu;

import com.girlkun.models.boss.Boss;
import com.girlkun.models.boss.BossID;
import com.girlkun.models.boss.BossesData;
import com.girlkun.models.map.ItemMap;
import com.girlkun.models.player.Player;
import com.girlkun.models.skill.Skill;
import com.girlkun.services.EffectSkillService;
import com.girlkun.services.PetService;
import com.girlkun.services.Service;
import com.girlkun.utils.Util;
import java.util.Random;

/**
 *
 * @Stole By barcoll
 */
    public class Cumber extends Boss {

    public Cumber() throws Exception {
       super(BossID.CUMBER, BossesData.CUMBER);
   }

    @Override
    public void reward(Player plKill) {
        if (Util.isTrue(100,100)) {
            if (Util.isTrue(100, 100)) {
                if (Util.isTrue(100, 100)) {
                    if (Util.isTrue(100, 100)) {
                        if (Util.isTrue(100, 100)) {
                }
            }
            ItemMap it = new ItemMap(this.zone, 2153, 3, this.location.x, this.zone.map.yPhysicInTop(this.location.x,
                    this.location.y - 24), plKill.id);
            ItemMap it1 = new ItemMap(this.zone, 2154, 3, this.location.x, this.zone.map.yPhysicInTop(this.location.x,
                    this.location.y - 24), plKill.id);
            ItemMap it2 = new ItemMap(this.zone, 2155, 3, this.location.x, this.zone.map.yPhysicInTop(this.location.x,
                    this.location.y - 24), plKill.id);
            ItemMap it3 = new ItemMap(this.zone, 2156, 3, this.location.x, this.zone.map.yPhysicInTop(this.location.x,
                    this.location.y - 24), plKill.id);
            ItemMap it4 = new ItemMap(this.zone, 2157, 3, this.location.x, this.zone.map.yPhysicInTop(this.location.x,
                    this.location.y - 24), plKill.id);
            Service.getInstance().dropItemMap(this.zone, it);
            Service.getInstance().dropItemMap(this.zone, it1);
            Service.getInstance().dropItemMap(this.zone, it2);
            Service.getInstance().dropItemMap(this.zone, it3);
            Service.getInstance().dropItemMap(this.zone, it4);
        
}
    }
}
}
}
