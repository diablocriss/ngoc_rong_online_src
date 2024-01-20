package com.girlkun.utils;

import com.girlkun.jdbc.daos.GodGK;
import com.girlkun.models.boss.BossManager;
import com.girlkun.models.item.Item;
import com.girlkun.models.map.ItemMap;
import com.girlkun.models.map.Zone;
import java.text.NumberFormat;
import java.util.*;

import com.girlkun.models.matches.TOP;
import com.girlkun.models.mob.Mob;
import com.girlkun.models.npc.Npc;
import com.girlkun.models.player.Player;
import com.girlkun.network.io.Message;
import com.girlkun.server.Client;
import com.girlkun.server.Manager;
import com.girlkun.services.ItemService;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;
import org.apache.commons.lang.ArrayUtils;
import java.security.MessageDigest;
import java.math.BigInteger;
import java.time.LocalTime;

public class Util {

    private static final Random rand;
    private static final Locale locale = new Locale("vi", "VN");
    private static final NumberFormat num = NumberFormat.getInstance(locale);

    static {
        rand = new Random();
    }

    public static String md5(String input) {
        try {
            MessageDigest md = MessageDigest.getInstance("MD5");
            byte[] messageDigest = md.digest(input.getBytes());
            BigInteger no = new BigInteger(1, messageDigest);
            String hashtext = no.toString(16);
            while (hashtext.length() < 32) {
                hashtext = "0" + hashtext;
            }
            return hashtext;
        } catch (Exception e) {
        }
        return "";
    }

    public static String getFormatNumber(float hp) {
        // Telegram: @Tamkjll
        return Util.num.format(hp);
    }

    public static int createIdBossClone(int idPlayer) {
        return -idPlayer - 100_000_000;
    }

    public static boolean contains(String[] arr, String key) {
        return Arrays.toString(arr).contains(key);
    }

    public static long Tamkjllnext(double from, double to) {
        // Telegram: @Tamkjll
        return (long) (from + rand.nextInt((int) (to - from + 1)));
    }

    public static int TamkjllGH(double a) {
        // Telegram: @Tamkjll
        if (a > Integer.MAX_VALUE) {
            a = Integer.MAX_VALUE;
        }
        return (int) a;
    }

    public static String numberToMoney(float power) {
        Locale locale = new Locale("vi", "VN");
        NumberFormat num = NumberFormat.getInstance(locale);
        num.setMaximumFractionDigits(1);
        if (power >= 1000000000) {
            return num.format((double) power / 1000000000) + " Tỷ";
        } else if (power >= 1000000) {
            return num.format((double) power / 1000000) + " Tr";
        } else if (power >= 1000) {
            return num.format((double) power / 1000) + " k";
        } else {
            return num.format(power);
        }
    }

    public static String powerToString(long power) {
        Locale locale = new Locale("vi", "VN");
        NumberFormat num = NumberFormat.getInstance(locale);
        num.setMaximumFractionDigits(1);
        if (power >= 1000000000) {
            return num.format((double) power / 1000000000) + " Tỷ";
        } else if (power >= 1000000) {
            return num.format((double) power / 1000000) + " Tr";
        } else if (power >= 1000) {
            return num.format((double) power / 1000) + " k";
        } else {
            return num.format(power);
        }
    }

    public static int getDistance(int x1, int y1, int x2, int y2) {
        return (int) Math.sqrt(Math.pow(x1 - x2, 2) + Math.pow(y1 - y2, 2));
    }

    public static int getDistance(Player pl1, Player pl2) {
        return getDistance(pl1.location.x, pl1.location.y, pl2.location.x, pl2.location.y);
    }

    public static int getDistance(Player pl, Npc npc) {
        return getDistance(pl.location.x, pl.location.y, npc.cx, npc.cy);
    }

    public static int getDistance(Player pl, Mob mob) {
        return getDistance(pl.location.x, pl.location.y, mob.location.x, mob.location.y);
    }

    public static int getDistance(Mob mob1, Mob mob2) {
        return getDistance(mob1.location.x, mob1.location.y, mob2.location.x, mob2.location.y);
    }

    public static int nextInt(int from, int to) {
        return from + rand.nextInt(to - from + 1);
    }

    public static int nextInt(int max) {
        return rand.nextInt(max);
    }

    public static int nextInt(int[] percen) {
        int next = nextInt(1000), i;
        for (i = 0; i < percen.length; i++) {
            if (next < percen[i]) {
                return i;
            }
            next -= percen[i];
        }
        return i;
    }

    public static int getOne(int n1, int n2) {
        return rand.nextInt() % 2 == 0 ? n1 : n2;
    }

    public static int currentTimeSec() {
        return (int) System.currentTimeMillis() / 1000;
    }

    public static String replace(String text, String regex, String replacement) {
        return text.replace(regex, replacement);
    }

    public static boolean isTrue(int ratio, int typeRatio) {
        int num = Util.nextInt(typeRatio);
        if (num < ratio) {
            return true;
        }
        return false;
    }

    public static boolean isTrue(float ratio, int typeRatio) {
        if (ratio < 1) {
            ratio *= 10;
            typeRatio *= 10;
        }
        int num = Util.nextInt(typeRatio);
        if (num < ratio) {
            return true;
        }
        return false;
    }

    public static boolean haveSpecialCharacter(String text) {
        Pattern p = Pattern.compile("[^a-z0-9 ]", Pattern.CASE_INSENSITIVE);
        Matcher m = p.matcher(text);
        boolean b = m.find();
        return b || text.contains(" ");
    }

    public static boolean canDoWithTime(long lastTime, long miniTimeTarget) {
        return System.currentTimeMillis() - lastTime > miniTimeTarget;
    }

    private static final char[] SOURCE_CHARACTERS = {'À', 'Á', 'Â', 'Ã', 'È', 'É',
        'Ê', 'Ì', 'Í', 'Ò', 'Ó', 'Ô', 'Õ', 'Ù', 'Ú', 'Ý', 'à', 'á', 'â',
        'ã', 'è', 'é', 'ê', 'ì', 'í', 'ò', 'ó', 'ô', 'õ', 'ù', 'ú', 'ý',
        'Ă', 'ă', 'Đ', 'đ', 'Ĩ', 'ĩ', 'Ũ', 'ũ', 'Ơ', 'ơ', 'Ư', 'ư', 'Ạ',
        'ạ', 'Ả', 'ả', 'Ấ', 'ấ', 'Ầ', 'ầ', 'Ẩ', 'ẩ', 'Ẫ', 'ẫ', 'Ậ', 'ậ',
        'Ắ', 'ắ', 'Ằ', 'ằ', 'Ẳ', 'ẳ', 'Ẵ', 'ẵ', 'Ặ', 'ặ', 'Ẹ', 'ẹ', 'Ẻ',
        'ẻ', 'Ẽ', 'ẽ', 'Ế', 'ế', 'Ề', 'ề', 'Ể', 'ể', 'Ễ', 'ễ', 'Ệ', 'ệ',
        'Ỉ', 'ỉ', 'Ị', 'ị', 'Ọ', 'ọ', 'Ỏ', 'ỏ', 'Ố', 'ố', 'Ồ', 'ồ', 'Ổ',
        'ổ', 'Ỗ', 'ỗ', 'Ộ', 'ộ', 'Ớ', 'ớ', 'Ờ', 'ờ', 'Ở', 'ở', 'Ỡ', 'ỡ',
        'Ợ', 'ợ', 'Ụ', 'ụ', 'Ủ', 'ủ', 'Ứ', 'ứ', 'Ừ', 'ừ', 'Ử', 'ử', 'Ữ',
        'ữ', 'Ự', 'ự',};

    private static final char[] DESTINATION_CHARACTERS = {'A', 'A', 'A', 'A', 'E',
        'E', 'E', 'I', 'I', 'O', 'O', 'O', 'O', 'U', 'U', 'Y', 'a', 'a',
        'a', 'a', 'e', 'e', 'e', 'i', 'i', 'o', 'o', 'o', 'o', 'u', 'u',
        'y', 'A', 'a', 'D', 'd', 'I', 'i', 'U', 'u', 'O', 'o', 'U', 'u',
        'A', 'a', 'A', 'a', 'A', 'a', 'A', 'a', 'A', 'a', 'A', 'a', 'A',
        'a', 'A', 'a', 'A', 'a', 'A', 'a', 'A', 'a', 'A', 'a', 'E', 'e',
        'E', 'e', 'E', 'e', 'E', 'e', 'E', 'e', 'E', 'e', 'E', 'e', 'E',
        'e', 'I', 'i', 'I', 'i', 'O', 'o', 'O', 'o', 'O', 'o', 'O', 'o',
        'O', 'o', 'O', 'o', 'O', 'o', 'O', 'o', 'O', 'o', 'O', 'o', 'O',
        'o', 'O', 'o', 'U', 'u', 'U', 'u', 'U', 'u', 'U', 'u', 'U', 'u',
        'U', 'u', 'U', 'u',};

    public static char removeAccent(char ch) {
        int index = Arrays.binarySearch(SOURCE_CHARACTERS, ch);
        if (index >= 0) {
            ch = DESTINATION_CHARACTERS[index];
        }
        return ch;
    }

    public static String removeAccent(String str) {
        StringBuilder sb = new StringBuilder(str);
        for (int i = 0; i < sb.length(); i++) {
            sb.setCharAt(i, removeAccent(sb.charAt(i)));
        }
        return sb.toString();
    }

    public static String generateRandomText(int len) {
        String chars = "0123456789ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijk"
                + "lmnopqrstuvwxyz!@#$%&";
        Random rnd = new Random();
        StringBuilder sb = new StringBuilder(len);
        for (int i = 0; i < len; i++) {
            sb.append(chars.charAt(rnd.nextInt(chars.length())));
        }
        return sb.toString();
    }

    public static Object[] addArray(Object[]... arrays) {
        if (arrays == null || arrays.length == 0) {
            return null;
        }
        if (arrays.length == 1) {
            return arrays[0];
        }
        Object[] arr0 = arrays[0];
        for (int i = 1; i < arrays.length; i++) {
            arr0 = ArrayUtils.addAll(arr0, arrays[i]);
        }
        return arr0;
    }

    public static ItemMap manhTS(Zone zone, int tempId, int quantity, int x, int y, long playerId) {
        return new ItemMap(zone, tempId, quantity, x, y, playerId);
    }
    public static ItemMap ratiDTL(Zone zone, int tempId, int quantity, int x, int y, long playerId) {
        ItemMap it = new ItemMap(zone, tempId, quantity, x, zone.map.yPhysicInTop(x, y - 24), playerId);
        List<Integer> ao = Arrays.asList(555, 557, 559);
        List<Integer> quan = Arrays.asList(556, 558, 560);
        List<Integer> gang = Arrays.asList(562, 564, 566);
        List<Integer> giay = Arrays.asList(563, 565, 567);
        int ntl = 561;
        if (ao.contains(tempId)) {
            it.options.add(new Item.ItemOption(47, highlightsItem(it.itemTemplate.gender == 2, new Random().nextInt(501) + 1300)));
        }
        if (quan.contains(tempId)) {
            it.options.add(new Item.ItemOption(22, highlightsItem(it.itemTemplate.gender == 0, new Random().nextInt(11) + 45)));
        }
        if (gang.contains(tempId)) {
            it.options.add(new Item.ItemOption(0, highlightsItem(it.itemTemplate.gender == 2, new Random().nextInt(1001) + 3500)));
        }
        if (giay.contains(tempId)) {
            it.options.add(new Item.ItemOption(23, highlightsItem(it.itemTemplate.gender == 1, new Random().nextInt(11) + 35)));
        }
        if (ntl == tempId) {
            it.options.add(new Item.ItemOption(14, new Random().nextInt(2) + 15));
        }
        it.options.add(new Item.ItemOption(209, 1)); // đồ rơi từ boss
        it.options.add(new Item.ItemOption(21, 18)); // ycsm 18 tỉ
        it.options.add(new Item.ItemOption(30, 1)); // ko thể gd
        if (Util.isTrue(20, 100)) {// tỉ lệ ra spl
            it.options.add(new Item.ItemOption(107, new Random().nextInt(3) + 1));
        } else if (Util.isTrue(4, 100)) {
            it.options.add(new Item.ItemOption(107, new Random().nextInt(3) + 5));
        } else {
            it.options.add(new Item.ItemOption(107, new Random().nextInt(5) + 1));
        }
        return it;
    }
    
    
    public static ItemMap ratiDTL1(Zone zone, int tempId, int quantity, int x, int y, long playerId) {
        ItemMap it = new ItemMap(zone, tempId, quantity, x, zone.map.yPhysicInTop(x, y - 24), playerId);
        List<Integer> ao = Arrays.asList(555, 557, 559);
        List<Integer> quan = Arrays.asList(556, 558, 560);
        List<Integer> gang = Arrays.asList(562, 564, 566);
        List<Integer> giay = Arrays.asList(563, 565, 567);
        int ntl = 561;
        if (ao.contains(tempId)) {
            it.options.add(new Item.ItemOption(47, highlightsItem(it.itemTemplate.gender == 2, new Random().nextInt(501) + 1300)));
        }
        if (quan.contains(tempId)) {
            it.options.add(new Item.ItemOption(22, highlightsItem(it.itemTemplate.gender == 0, new Random().nextInt(11) + 45)));
        }
        if (gang.contains(tempId)) {
            it.options.add(new Item.ItemOption(0, highlightsItem(it.itemTemplate.gender == 2, new Random().nextInt(1001) + 3500)));
        }
        if (giay.contains(tempId)) {
            it.options.add(new Item.ItemOption(23, highlightsItem(it.itemTemplate.gender == 1, new Random().nextInt(11) + 35)));
        }
        if (ntl == tempId) {
            it.options.add(new Item.ItemOption(14, new Random().nextInt(2) + 15));
        }
//        it.options.add(new Item.ItemOption(209, 1)); // đồ rơi từ boss
        it.options.add(new Item.ItemOption(21, 18)); // ycsm 18 tỉ
//        it.options.add(new Item.ItemOption(30, 1)); // ko thể gd
        if (Util.isTrue(1, 5)) {// tỉ lệ ra spl
            it.options.add(new Item.ItemOption(107, new Random().nextInt(3) + 1));
        } else if (Util.isTrue(1, 5)) {
            it.options.add(new Item.ItemOption(107, new Random().nextInt(3) + 5));
        } else {
            it.options.add(new Item.ItemOption(107, new Random().nextInt(5) + 1));
        }
        return it;
    }
        public static ItemMap ratiDTL2(Zone zone, int tempId, int quantity, int x, int y, long playerId) {
        ItemMap it = new ItemMap(zone, tempId, quantity, x, zone.map.yPhysicInTop(x, y - 24), playerId);
        List<Integer> spl = Arrays.asList(441);
        List<Integer> spl1 = Arrays.asList(442);
        List<Integer> spl2 = Arrays.asList(443);
        List<Integer> spl3 = Arrays.asList(444);
        List<Integer> spl4 = Arrays.asList(445);
        List<Integer> spl5 = Arrays.asList(446);
        List<Integer> spl6 = Arrays.asList(447);
        if (spl.contains(tempId)) {
          it.options.add(new Item.ItemOption(95, 5));
        }
        if (spl1.contains(tempId)) {
          it.options.add(new Item.ItemOption(96, 5));
        }
        if (spl2.contains(tempId)) {
           it.options.add(new Item.ItemOption(97, 5));
        }
        if (spl3.contains(tempId)) {
          it.options.add(new Item.ItemOption(98, 5));
        }
        if (spl4.contains(tempId)) {
           it.options.add(new Item.ItemOption(99, 5));
        }
        if (spl5.contains(tempId)) {
           it.options.add(new Item.ItemOption(100, 5));
        }
        if (spl6.contains(tempId)) {
           it.options.add(new Item.ItemOption(101, 5));
        }
        return it;
        }
     public static ItemMap ratiDTL3(Zone zone, int tempId, int quantity, int x, int y, long playerId) {
        ItemMap it = new ItemMap(zone, tempId, quantity, x, zone.map.yPhysicInTop(x, y - 24), playerId);
        List<Integer> spl = Arrays.asList(1992);
        if (spl.contains(tempId)) {
          it.options.add(new Item.ItemOption(47, 5));
        }
        if (spl.contains(tempId)) {
          it.options.add(new Item.ItemOption(135, 0));
        }
        if (spl.contains(tempId)) {
          it.options.add(new Item.ItemOption(138, 0));
        }
        if (spl.contains(tempId)) {
          it.options.add(new Item.ItemOption(30, 0));
        }
        return it;
    }
       public static ItemMap ratiDTL4(Zone zone, int tempId, int quantity, int x, int y, long playerId) {
        ItemMap it = new ItemMap(zone, tempId, quantity, x, zone.map.yPhysicInTop(x, y - 24), playerId);
        List<Integer> spl = Arrays.asList(1992);
        if (spl.contains(tempId)) {
          it.options.add(new Item.ItemOption(47, 5));
        }
        if (spl.contains(tempId)) {
          it.options.add(new Item.ItemOption(133, 0));
        }
        if (spl.contains(tempId)) {
          it.options.add(new Item.ItemOption(136, 0));
        }
        if (spl.contains(tempId)) {
          it.options.add(new Item.ItemOption(30, 0));
        }
        return it;
    }
       public static ItemMap ratiDTL5(Zone zone, int tempId, int quantity, int x, int y, long playerId) {
        ItemMap it = new ItemMap(zone, tempId, quantity, x, zone.map.yPhysicInTop(x, y - 24), playerId);
        List<Integer> spl = Arrays.asList(1992);
        if (spl.contains(tempId)) {
          it.options.add(new Item.ItemOption(47, 5));
        }
        if (spl.contains(tempId)) {
          it.options.add(new Item.ItemOption(134, 0));
        }
        if (spl.contains(tempId)) {
          it.options.add(new Item.ItemOption(137, 0));
        }
        if (spl.contains(tempId)) {
          it.options.add(new Item.ItemOption(30, 0));
        }
        return it;
    }
       public static ItemMap ratiDTL6(Zone zone, int tempId, int quantity, int x, int y, long playerId) {
        ItemMap it = new ItemMap(zone, tempId, quantity, x, zone.map.yPhysicInTop(x, y - 24), playerId);
        List<Integer> spl = Arrays.asList(1993);
        if (spl.contains(tempId)) {
          it.options.add(new Item.ItemOption(6, 20));
        }
        if (spl.contains(tempId)) {
          it.options.add(new Item.ItemOption(135, 0));
        }
        if (spl.contains(tempId)) {
          it.options.add(new Item.ItemOption(138, 0));
        }
        if (spl.contains(tempId)) {
          it.options.add(new Item.ItemOption(30, 0));
        }
        return it;
    }
       public static ItemMap ratiDTL7(Zone zone, int tempId, int quantity, int x, int y, long playerId) {
        ItemMap it = new ItemMap(zone, tempId, quantity, x, zone.map.yPhysicInTop(x, y - 24), playerId);
        List<Integer> spl = Arrays.asList(1993);
        if (spl.contains(tempId)) {
          it.options.add(new Item.ItemOption(6, 20));
        }
        if (spl.contains(tempId)) {
          it.options.add(new Item.ItemOption(133, 0));
        }
        if (spl.contains(tempId)) {
          it.options.add(new Item.ItemOption(136, 0));
        }
        if (spl.contains(tempId)) {
          it.options.add(new Item.ItemOption(30, 0));
        }
        return it;
    }
       public static ItemMap ratiDTL8(Zone zone, int tempId, int quantity, int x, int y, long playerId) {
        ItemMap it = new ItemMap(zone, tempId, quantity, x, zone.map.yPhysicInTop(x, y - 24), playerId);
        List<Integer> spl = Arrays.asList(1993);
        if (spl.contains(tempId)) {
          it.options.add(new Item.ItemOption(6, 20));
        }
        if (spl.contains(tempId)) {
          it.options.add(new Item.ItemOption(134, 0));
        }
        if (spl.contains(tempId)) {
          it.options.add(new Item.ItemOption(137, 0));
        }
        if (spl.contains(tempId)) {
          it.options.add(new Item.ItemOption(30, 0));
        }
        return it;
    }
       public static ItemMap ratiDTL9(Zone zone, int tempId, int quantity, int x, int y, long playerId) {
        ItemMap it = new ItemMap(zone, tempId, quantity, x, zone.map.yPhysicInTop(x, y - 24), playerId);
        List<Integer> spl = Arrays.asList(1994);
        if (spl.contains(tempId)) {
          it.options.add(new Item.ItemOption(0, 20));
        }
        if (spl.contains(tempId)) {
          it.options.add(new Item.ItemOption(135, 0));
        }
        if (spl.contains(tempId)) {
          it.options.add(new Item.ItemOption(138, 0));
        }
        if (spl.contains(tempId)) {
          it.options.add(new Item.ItemOption(30, 0));
        }
        return it;
    }
       public static ItemMap ratiDTL10(Zone zone, int tempId, int quantity, int x, int y, long playerId) {
        ItemMap it = new ItemMap(zone, tempId, quantity, x, zone.map.yPhysicInTop(x, y - 24), playerId);
        List<Integer> spl = Arrays.asList(1994);
        if (spl.contains(tempId)) {
          it.options.add(new Item.ItemOption(0, 20));
        }
        if (spl.contains(tempId)) {
          it.options.add(new Item.ItemOption(133, 0));
        }
        if (spl.contains(tempId)) {
          it.options.add(new Item.ItemOption(136, 0));
        }
        if (spl.contains(tempId)) {
          it.options.add(new Item.ItemOption(30, 0));
        }
        return it;
    }
       public static ItemMap ratiDTL11(Zone zone, int tempId, int quantity, int x, int y, long playerId) {
        ItemMap it = new ItemMap(zone, tempId, quantity, x, zone.map.yPhysicInTop(x, y - 24), playerId);
        List<Integer> spl = Arrays.asList(1994);
        if (spl.contains(tempId)) {
          it.options.add(new Item.ItemOption(0, 20));
        }
        if (spl.contains(tempId)) {
          it.options.add(new Item.ItemOption(134, 0));
        }
        if (spl.contains(tempId)) {
          it.options.add(new Item.ItemOption(137, 0));
        }
        if (spl.contains(tempId)) {
          it.options.add(new Item.ItemOption(30, 0));
        }
        return it;
    }
       public static ItemMap ratiDTL12(Zone zone, int tempId, int quantity, int x, int y, long playerId) {
        ItemMap it = new ItemMap(zone, tempId, quantity, x, zone.map.yPhysicInTop(x, y - 24), playerId);
        List<Integer> spl = Arrays.asList(1995);
        if (spl.contains(tempId)) {
          it.options.add(new Item.ItemOption(7, 20));
        }
        if (spl.contains(tempId)) {
          it.options.add(new Item.ItemOption(135, 0));
        }
        if (spl.contains(tempId)) {
          it.options.add(new Item.ItemOption(138, 0));
        }
        if (spl.contains(tempId)) {
          it.options.add(new Item.ItemOption(30, 0));
        }
        return it;
    }
      public static ItemMap ratiDTL13(Zone zone, int tempId, int quantity, int x, int y, long playerId) {
        ItemMap it = new ItemMap(zone, tempId, quantity, x, zone.map.yPhysicInTop(x, y - 24), playerId);
        List<Integer> spl = Arrays.asList(1995);
        if (spl.contains(tempId)) {
          it.options.add(new Item.ItemOption(7, 20));
        }
        if (spl.contains(tempId)) {
          it.options.add(new Item.ItemOption(133, 0));
        }
        if (spl.contains(tempId)) {
          it.options.add(new Item.ItemOption(136, 0));
        }
        if (spl.contains(tempId)) {
          it.options.add(new Item.ItemOption(30, 0));
        }
        return it;
    }
    public static ItemMap ratiDTL14(Zone zone, int tempId, int quantity, int x, int y, long playerId) {
        ItemMap it = new ItemMap(zone, tempId, quantity, x, zone.map.yPhysicInTop(x, y - 24), playerId);
        List<Integer> spl = Arrays.asList(1995);
        if (spl.contains(tempId)) {
          it.options.add(new Item.ItemOption(7, 20));
        }
        if (spl.contains(tempId)) {
          it.options.add(new Item.ItemOption(134, 0));
        }
        if (spl.contains(tempId)) {
          it.options.add(new Item.ItemOption(137, 0));
        }
        if (spl.contains(tempId)) {
          it.options.add(new Item.ItemOption(30, 0));
        }
        return it;
    }
    public static ItemMap ratiDTL15(Zone zone, int tempId, int quantity, int x, int y, long playerId) {
        ItemMap it = new ItemMap(zone, tempId, quantity, x, zone.map.yPhysicInTop(x, y - 24), playerId);
        List<Integer> spl = Arrays.asList(1996);
        if (spl.contains(tempId)) {
          it.options.add(new Item.ItemOption(14, 2));
        }
        if (spl.contains(tempId)) {
          it.options.add(new Item.ItemOption(135, 0));
        }
        if (spl.contains(tempId)) {
          it.options.add(new Item.ItemOption(138, 0));
        }
        if (spl.contains(tempId)) {
          it.options.add(new Item.ItemOption(30, 0));
        }
        return it;
    }
    public static ItemMap ratiDTL16(Zone zone, int tempId, int quantity, int x, int y, long playerId) {
        ItemMap it = new ItemMap(zone, tempId, quantity, x, zone.map.yPhysicInTop(x, y - 24), playerId);
        List<Integer> spl = Arrays.asList(1996);
        if (spl.contains(tempId)) {
          it.options.add(new Item.ItemOption(14, 2));
        }
        if (spl.contains(tempId)) {
          it.options.add(new Item.ItemOption(133, 0));
        }
        if (spl.contains(tempId)) {
          it.options.add(new Item.ItemOption(136, 0));
        }
        if (spl.contains(tempId)) {
          it.options.add(new Item.ItemOption(30, 0));
        }
        return it;
    }
    public static ItemMap ratiDTL17(Zone zone, int tempId, int quantity, int x, int y, long playerId) {
        ItemMap it = new ItemMap(zone, tempId, quantity, x, zone.map.yPhysicInTop(x, y - 24), playerId);
        List<Integer> spl = Arrays.asList(1996);
        if (spl.contains(tempId)) {
          it.options.add(new Item.ItemOption(14, 2));
        }
        if (spl.contains(tempId)) {
          it.options.add(new Item.ItemOption(134, 0));
        }
        if (spl.contains(tempId)) {
          it.options.add(new Item.ItemOption(137, 0));
        }
        if (spl.contains(tempId)) {
          it.options.add(new Item.ItemOption(30, 0));
        }
        return it;
    }
    public static ItemMap ratiDTL18(Zone zone, int tempId, int quantity, int x, int y, long playerId) {
        ItemMap it = new ItemMap(zone, tempId, quantity, x, zone.map.yPhysicInTop(x, y - 24), playerId);
        List<Integer> spl = Arrays.asList(1987);
        if (spl.contains(tempId)) {
          it.options.add(new Item.ItemOption(47, 5));
        }
        if (spl.contains(tempId)) {
          it.options.add(new Item.ItemOption(129, 0));
        }
        if (spl.contains(tempId)) {
          it.options.add(new Item.ItemOption(141, 0));
        }
        if (spl.contains(tempId)) {
          it.options.add(new Item.ItemOption(30, 0));
        }
        return it;
    }
    public static ItemMap ratiDTL19(Zone zone, int tempId, int quantity, int x, int y, long playerId) {
        ItemMap it = new ItemMap(zone, tempId, quantity, x, zone.map.yPhysicInTop(x, y - 24), playerId);
        List<Integer> spl = Arrays.asList(1987);
        if (spl.contains(tempId)) {
          it.options.add(new Item.ItemOption(47, 5));
        }
        if (spl.contains(tempId)) {
          it.options.add(new Item.ItemOption(127, 0));
        }
        if (spl.contains(tempId)) {
          it.options.add(new Item.ItemOption(139, 0));
        }
        if (spl.contains(tempId)) {
          it.options.add(new Item.ItemOption(30, 0));
        }
        return it;
    }
    public static ItemMap ratiDTL20(Zone zone, int tempId, int quantity, int x, int y, long playerId) {
        ItemMap it = new ItemMap(zone, tempId, quantity, x, zone.map.yPhysicInTop(x, y - 24), playerId);
        List<Integer> spl = Arrays.asList(1987);
        if (spl.contains(tempId)) {
          it.options.add(new Item.ItemOption(47, 5));
        }
        if (spl.contains(tempId)) {
          it.options.add(new Item.ItemOption(128, 0));
        }
        if (spl.contains(tempId)) {
          it.options.add(new Item.ItemOption(140, 0));
        }
        if (spl.contains(tempId)) {
          it.options.add(new Item.ItemOption(30, 0));
        }
        return it;
    }
    public static ItemMap ratiDTL21(Zone zone, int tempId, int quantity, int x, int y, long playerId) {
        ItemMap it = new ItemMap(zone, tempId, quantity, x, zone.map.yPhysicInTop(x, y - 24), playerId);
        List<Integer> spl = Arrays.asList(1988);
        if (spl.contains(tempId)) {
          it.options.add(new Item.ItemOption(6, 20));
        }
        if (spl.contains(tempId)) {
          it.options.add(new Item.ItemOption(129, 0));
        }
        if (spl.contains(tempId)) {
          it.options.add(new Item.ItemOption(141, 0));
        }
        if (spl.contains(tempId)) {
          it.options.add(new Item.ItemOption(30, 0));
        }
        return it;
    }
    public static ItemMap ratiDTL22(Zone zone, int tempId, int quantity, int x, int y, long playerId) {
        ItemMap it = new ItemMap(zone, tempId, quantity, x, zone.map.yPhysicInTop(x, y - 24), playerId);
        List<Integer> spl = Arrays.asList(1988);
        if (spl.contains(tempId)) {
          it.options.add(new Item.ItemOption(6, 20));
        }
        if (spl.contains(tempId)) {
          it.options.add(new Item.ItemOption(127, 0));
        }
        if (spl.contains(tempId)) {
          it.options.add(new Item.ItemOption(139, 0));
        }
        if (spl.contains(tempId)) {
          it.options.add(new Item.ItemOption(30, 0));
        }
        return it;
    }
    public static ItemMap ratiDTL23(Zone zone, int tempId, int quantity, int x, int y, long playerId) {
        ItemMap it = new ItemMap(zone, tempId, quantity, x, zone.map.yPhysicInTop(x, y - 24), playerId);
        List<Integer> spl = Arrays.asList(1988);
        if (spl.contains(tempId)) {
          it.options.add(new Item.ItemOption(6, 20));
        }
        if (spl.contains(tempId)) {
          it.options.add(new Item.ItemOption(128, 0));
        }
        if (spl.contains(tempId)) {
          it.options.add(new Item.ItemOption(140, 0));
        }
        if (spl.contains(tempId)) {
          it.options.add(new Item.ItemOption(30, 0));
        }
        return it;
    }
    public static ItemMap ratiDTL24(Zone zone, int tempId, int quantity, int x, int y, long playerId) {
        ItemMap it = new ItemMap(zone, tempId, quantity, x, zone.map.yPhysicInTop(x, y - 24), playerId);
        List<Integer> spl = Arrays.asList(1989);
        if (spl.contains(tempId)) {
          it.options.add(new Item.ItemOption(0, 20));
        }
        if (spl.contains(tempId)) {
          it.options.add(new Item.ItemOption(129, 0));
        }
        if (spl.contains(tempId)) {
          it.options.add(new Item.ItemOption(141, 0));
        }
        if (spl.contains(tempId)) {
          it.options.add(new Item.ItemOption(30, 0));
        }
        return it;
    }
    public static ItemMap ratiDTL25(Zone zone, int tempId, int quantity, int x, int y, long playerId) {
        ItemMap it = new ItemMap(zone, tempId, quantity, x, zone.map.yPhysicInTop(x, y - 24), playerId);
        List<Integer> spl = Arrays.asList(1989);
        if (spl.contains(tempId)) {
          it.options.add(new Item.ItemOption(0, 20));
        }
        if (spl.contains(tempId)) {
          it.options.add(new Item.ItemOption(127, 0));
        }
        if (spl.contains(tempId)) {
          it.options.add(new Item.ItemOption(139, 0));
        }
        if (spl.contains(tempId)) {
          it.options.add(new Item.ItemOption(30, 0));
        }
        return it;
    }
    public static ItemMap ratiDTL26(Zone zone, int tempId, int quantity, int x, int y, long playerId) {
        ItemMap it = new ItemMap(zone, tempId, quantity, x, zone.map.yPhysicInTop(x, y - 24), playerId);
        List<Integer> spl = Arrays.asList(1989);
        if (spl.contains(tempId)) {
          it.options.add(new Item.ItemOption(0, 20));
        }
        if (spl.contains(tempId)) {
          it.options.add(new Item.ItemOption(128, 0));
        }
        if (spl.contains(tempId)) {
          it.options.add(new Item.ItemOption(140, 0));
        }
        if (spl.contains(tempId)) {
          it.options.add(new Item.ItemOption(30, 0));
        }
        return it;
    }
    public static ItemMap ratiDTL27(Zone zone, int tempId, int quantity, int x, int y, long playerId) {
        ItemMap it = new ItemMap(zone, tempId, quantity, x, zone.map.yPhysicInTop(x, y - 24), playerId);
        List<Integer> spl = Arrays.asList(1990);
        if (spl.contains(tempId)) {
          it.options.add(new Item.ItemOption(7, 20));
        }
        if (spl.contains(tempId)) {
          it.options.add(new Item.ItemOption(129, 0));
        }
        if (spl.contains(tempId)) {
          it.options.add(new Item.ItemOption(141, 0));
        }
        if (spl.contains(tempId)) {
          it.options.add(new Item.ItemOption(30, 0));
        }
        return it;
    }
    public static ItemMap ratiDTL28(Zone zone, int tempId, int quantity, int x, int y, long playerId) {
        ItemMap it = new ItemMap(zone, tempId, quantity, x, zone.map.yPhysicInTop(x, y - 24), playerId);
        List<Integer> spl = Arrays.asList(1990);
        if (spl.contains(tempId)) {
          it.options.add(new Item.ItemOption(7, 20));
        }
        if (spl.contains(tempId)) {
          it.options.add(new Item.ItemOption(127, 0));
        }
        if (spl.contains(tempId)) {
          it.options.add(new Item.ItemOption(139, 0));
        }
        if (spl.contains(tempId)) {
          it.options.add(new Item.ItemOption(30, 0));
        }
        return it;
    }
    public static ItemMap ratiDTL29(Zone zone, int tempId, int quantity, int x, int y, long playerId) {
        ItemMap it = new ItemMap(zone, tempId, quantity, x, zone.map.yPhysicInTop(x, y - 24), playerId);
        List<Integer> spl = Arrays.asList(1990);
        if (spl.contains(tempId)) {
          it.options.add(new Item.ItemOption(7, 20));
        }
        if (spl.contains(tempId)) {
          it.options.add(new Item.ItemOption(128, 0));
        }
        if (spl.contains(tempId)) {
          it.options.add(new Item.ItemOption(140, 0));
        }
        if (spl.contains(tempId)) {
          it.options.add(new Item.ItemOption(30, 0));
        }
        return it;
    }
    public static ItemMap ratiDTL30(Zone zone, int tempId, int quantity, int x, int y, long playerId) {
        ItemMap it = new ItemMap(zone, tempId, quantity, x, zone.map.yPhysicInTop(x, y - 24), playerId);
        List<Integer> spl = Arrays.asList(1991);
        if (spl.contains(tempId)) {
          it.options.add(new Item.ItemOption(14, 2));
        }
        if (spl.contains(tempId)) {
          it.options.add(new Item.ItemOption(129, 0));
        }
        if (spl.contains(tempId)) {
          it.options.add(new Item.ItemOption(141, 0));
        }
        if (spl.contains(tempId)) {
          it.options.add(new Item.ItemOption(30, 0));
        }
        return it;
    }
    public static ItemMap ratiDTL322(Zone zone, int tempId, int quantity, int x, int y, long playerId) {
        ItemMap it = new ItemMap(zone, tempId, quantity, x, zone.map.yPhysicInTop(x, y - 24), playerId);
        List<Integer> spl = Arrays.asList(1991);
        if (spl.contains(tempId)) {
          it.options.add(new Item.ItemOption(14, 2));
        }
        if (spl.contains(tempId)) {
          it.options.add(new Item.ItemOption(128, 0));
        }
        if (spl.contains(tempId)) {
          it.options.add(new Item.ItemOption(140, 0));
        }
        if (spl.contains(tempId)) {
          it.options.add(new Item.ItemOption(30, 0));
        }
        return it;
    }
    public static ItemMap ratiDTL31(Zone zone, int tempId, int quantity, int x, int y, long playerId) {
        ItemMap it = new ItemMap(zone, tempId, quantity, x, zone.map.yPhysicInTop(x, y - 24), playerId);
        List<Integer> spl = Arrays.asList(1991);
        if (spl.contains(tempId)) {
          it.options.add(new Item.ItemOption(14, 2));
        }
        if (spl.contains(tempId)) {
          it.options.add(new Item.ItemOption(127, 0));
        }
        if (spl.contains(tempId)) {
          it.options.add(new Item.ItemOption(139, 0));
        }
        if (spl.contains(tempId)) {
          it.options.add(new Item.ItemOption(30, 0));
        }
        return it;
    }
    public static ItemMap ratiDTL32(Zone zone, int tempId, int quantity, int x, int y, long playerId) {
        ItemMap it = new ItemMap(zone, tempId, quantity, x, zone.map.yPhysicInTop(x, y - 24), playerId);
        List<Integer> spl = Arrays.asList(1991);
        if (spl.contains(tempId)) {
          it.options.add(new Item.ItemOption(14, 2));
        }
        if (spl.contains(tempId)) {
          it.options.add(new Item.ItemOption(128, 0));
        }
        if (spl.contains(tempId)) {
          it.options.add(new Item.ItemOption(140, 0));
        }
        if (spl.contains(tempId)) {
          it.options.add(new Item.ItemOption(30, 0));
        }
        return it;
    }
    public static ItemMap ratiDTL33(Zone zone, int tempId, int quantity, int x, int y, long playerId) {
        ItemMap it = new ItemMap(zone, tempId, quantity, x, zone.map.yPhysicInTop(x, y - 24), playerId);
        List<Integer> spl = Arrays.asList(1982);
        if (spl.contains(tempId)) {
          it.options.add(new Item.ItemOption(47, 20));
        }
        if (spl.contains(tempId)) {
          it.options.add(new Item.ItemOption(131, 0));
        }
        if (spl.contains(tempId)) {
          it.options.add(new Item.ItemOption(143, 0));
        }
        if (spl.contains(tempId)) {
          it.options.add(new Item.ItemOption(30, 0));
        }
        return it;
    }
    public static ItemMap ratiDTL34(Zone zone, int tempId, int quantity, int x, int y, long playerId) {
        ItemMap it = new ItemMap(zone, tempId, quantity, x, zone.map.yPhysicInTop(x, y - 24), playerId);
        List<Integer> spl = Arrays.asList(1982);
        if (spl.contains(tempId)) {
          it.options.add(new Item.ItemOption(47, 20));
        }
        if (spl.contains(tempId)) {
          it.options.add(new Item.ItemOption(132, 0));
        }
        if (spl.contains(tempId)) {
          it.options.add(new Item.ItemOption(144, 0));
        }
        if (spl.contains(tempId)) {
          it.options.add(new Item.ItemOption(30, 0));
        }
        return it;
    }
    public static ItemMap ratiDTL35(Zone zone, int tempId, int quantity, int x, int y, long playerId) {
        ItemMap it = new ItemMap(zone, tempId, quantity, x, zone.map.yPhysicInTop(x, y - 24), playerId);
        List<Integer> spl = Arrays.asList(1982);
        if (spl.contains(tempId)) {
          it.options.add(new Item.ItemOption(47, 20));
        }
        if (spl.contains(tempId)) {
          it.options.add(new Item.ItemOption(130, 0));
        }
        if (spl.contains(tempId)) {
          it.options.add(new Item.ItemOption(142, 0));
        }
        if (spl.contains(tempId)) {
          it.options.add(new Item.ItemOption(30, 0));
        }
        return it;
    }
    public static ItemMap ratiDTL36(Zone zone, int tempId, int quantity, int x, int y, long playerId) {
        ItemMap it = new ItemMap(zone, tempId, quantity, x, zone.map.yPhysicInTop(x, y - 24), playerId);
        List<Integer> spl = Arrays.asList(1983);
        if (spl.contains(tempId)) {
          it.options.add(new Item.ItemOption(6, 20));
        }
        if (spl.contains(tempId)) {
          it.options.add(new Item.ItemOption(131, 0));
        }
        if (spl.contains(tempId)) {
          it.options.add(new Item.ItemOption(143, 0));
        }
        if (spl.contains(tempId)) {
          it.options.add(new Item.ItemOption(30, 0));
        }
        return it;
    }
    public static ItemMap ratiDTL37(Zone zone, int tempId, int quantity, int x, int y, long playerId) {
        ItemMap it = new ItemMap(zone, tempId, quantity, x, zone.map.yPhysicInTop(x, y - 24), playerId);
        List<Integer> spl = Arrays.asList(1983);
        if (spl.contains(tempId)) {
          it.options.add(new Item.ItemOption(6, 20));
        }
        if (spl.contains(tempId)) {
          it.options.add(new Item.ItemOption(132, 0));
        }
        if (spl.contains(tempId)) {
          it.options.add(new Item.ItemOption(144, 0));
        }
        if (spl.contains(tempId)) {
          it.options.add(new Item.ItemOption(30, 0));
        }
        return it;
    }
    public static ItemMap ratiDTL38(Zone zone, int tempId, int quantity, int x, int y, long playerId) {
        ItemMap it = new ItemMap(zone, tempId, quantity, x, zone.map.yPhysicInTop(x, y - 24), playerId);
        List<Integer> spl = Arrays.asList(1983);
        if (spl.contains(tempId)) {
          it.options.add(new Item.ItemOption(6, 20));
        }
        if (spl.contains(tempId)) {
          it.options.add(new Item.ItemOption(130, 0));
        }
        if (spl.contains(tempId)) {
          it.options.add(new Item.ItemOption(142, 0));
        }
        if (spl.contains(tempId)) {
          it.options.add(new Item.ItemOption(30, 0));
        }
        return it;
    }
    public static ItemMap ratiDTL39(Zone zone, int tempId, int quantity, int x, int y, long playerId) {
        ItemMap it = new ItemMap(zone, tempId, quantity, x, zone.map.yPhysicInTop(x, y - 24), playerId);
        List<Integer> spl = Arrays.asList(1984);
        if (spl.contains(tempId)) {
          it.options.add(new Item.ItemOption(0, 20));
        }
        if (spl.contains(tempId)) {
          it.options.add(new Item.ItemOption(131, 0));
        }
        if (spl.contains(tempId)) {
          it.options.add(new Item.ItemOption(143, 0));
        }
        if (spl.contains(tempId)) {
          it.options.add(new Item.ItemOption(30, 0));
        }
        return it;
    }
    public static ItemMap ratiDTL40(Zone zone, int tempId, int quantity, int x, int y, long playerId) {
        ItemMap it = new ItemMap(zone, tempId, quantity, x, zone.map.yPhysicInTop(x, y - 24), playerId);
        List<Integer> spl = Arrays.asList(1984);
        if (spl.contains(tempId)) {
          it.options.add(new Item.ItemOption(0, 20));
        }
        if (spl.contains(tempId)) {
          it.options.add(new Item.ItemOption(132, 0));
        }
        if (spl.contains(tempId)) {
          it.options.add(new Item.ItemOption(144, 0));
        }
        if (spl.contains(tempId)) {
          it.options.add(new Item.ItemOption(30, 0));
        }
        return it;
    }
     public static ItemMap ratiDTL41(Zone zone, int tempId, int quantity, int x, int y, long playerId) {
        ItemMap it = new ItemMap(zone, tempId, quantity, x, zone.map.yPhysicInTop(x, y - 24), playerId);
        List<Integer> spl = Arrays.asList(1984);
        if (spl.contains(tempId)) {
          it.options.add(new Item.ItemOption(0, 20));
        }
        if (spl.contains(tempId)) {
          it.options.add(new Item.ItemOption(130, 0));
        }
        if (spl.contains(tempId)) {
          it.options.add(new Item.ItemOption(142, 0));
        }
        if (spl.contains(tempId)) {
          it.options.add(new Item.ItemOption(30, 0));
        }
        return it;
    }
    public static ItemMap ratiDTL42(Zone zone, int tempId, int quantity, int x, int y, long playerId) {
        ItemMap it = new ItemMap(zone, tempId, quantity, x, zone.map.yPhysicInTop(x, y - 24), playerId);
        List<Integer> spl = Arrays.asList(1985);
        if (spl.contains(tempId)) {
          it.options.add(new Item.ItemOption(7, 20));
        }
        if (spl.contains(tempId)) {
          it.options.add(new Item.ItemOption(131, 0));
        }
        if (spl.contains(tempId)) {
          it.options.add(new Item.ItemOption(143, 0));
        }
        if (spl.contains(tempId)) {
          it.options.add(new Item.ItemOption(30, 0));
        }
        return it;
    }
    public static ItemMap ratiDTL43(Zone zone, int tempId, int quantity, int x, int y, long playerId) {
        ItemMap it = new ItemMap(zone, tempId, quantity, x, zone.map.yPhysicInTop(x, y - 24), playerId);
        List<Integer> spl = Arrays.asList(1985);
        if (spl.contains(tempId)) {
          it.options.add(new Item.ItemOption(7, 20));
        }
        if (spl.contains(tempId)) {
          it.options.add(new Item.ItemOption(132, 0));
        }
        if (spl.contains(tempId)) {
          it.options.add(new Item.ItemOption(144, 0));
        }
        if (spl.contains(tempId)) {
          it.options.add(new Item.ItemOption(30, 0));
        }
        return it;
    }
    public static ItemMap ratiDTL44(Zone zone, int tempId, int quantity, int x, int y, long playerId) {
        ItemMap it = new ItemMap(zone, tempId, quantity, x, zone.map.yPhysicInTop(x, y - 24), playerId);
        List<Integer> spl = Arrays.asList(1985);
        if (spl.contains(tempId)) {
          it.options.add(new Item.ItemOption(7, 20));
        }
        if (spl.contains(tempId)) {
          it.options.add(new Item.ItemOption(130, 0));
        }
        if (spl.contains(tempId)) {
          it.options.add(new Item.ItemOption(142, 0));
        }
        if (spl.contains(tempId)) {
          it.options.add(new Item.ItemOption(30, 0));
        }
        return it;
    }
   public static ItemMap ratiDTL45(Zone zone, int tempId, int quantity, int x, int y, long playerId) {
        ItemMap it = new ItemMap(zone, tempId, quantity, x, zone.map.yPhysicInTop(x, y - 24), playerId);
        List<Integer> spl = Arrays.asList(1986);
        if (spl.contains(tempId)) {
          it.options.add(new Item.ItemOption(14, 2));
        }
        if (spl.contains(tempId)) {
          it.options.add(new Item.ItemOption(131, 0));
        }
        if (spl.contains(tempId)) {
          it.options.add(new Item.ItemOption(143, 0));
        }
        if (spl.contains(tempId)) {
          it.options.add(new Item.ItemOption(30, 0));
        }
        return it;
    }
    public static ItemMap ratiDTL46(Zone zone, int tempId, int quantity, int x, int y, long playerId) {
        ItemMap it = new ItemMap(zone, tempId, quantity, x, zone.map.yPhysicInTop(x, y - 24), playerId);
        List<Integer> spl = Arrays.asList(1986);
        if (spl.contains(tempId)) {
          it.options.add(new Item.ItemOption(14, 2));
        }
        if (spl.contains(tempId)) {
          it.options.add(new Item.ItemOption(132, 0));
        }
        if (spl.contains(tempId)) {
          it.options.add(new Item.ItemOption(144, 0));
        }
        if (spl.contains(tempId)) {
          it.options.add(new Item.ItemOption(30, 0));
        }
        return it;
    }
    public static ItemMap ratiDTL47(Zone zone, int tempId, int quantity, int x, int y, long playerId) {
        ItemMap it = new ItemMap(zone, tempId, quantity, x, zone.map.yPhysicInTop(x, y - 24), playerId);
        List<Integer> spl = Arrays.asList(1986);
        if (spl.contains(tempId)) {
          it.options.add(new Item.ItemOption(14, 2));
        }
        if (spl.contains(tempId)) {
          it.options.add(new Item.ItemOption(130, 0));
        }
        if (spl.contains(tempId)) {
          it.options.add(new Item.ItemOption(142, 0));
        }
        if (spl.contains(tempId)) {
          it.options.add(new Item.ItemOption(30, 0));
        }
        return it;
    }
    public static ItemMap ratiDTL48(Zone zone, int tempId, int quantity, int x, int y, long playerId) {
        ItemMap it = new ItemMap(zone, tempId, quantity, x, zone.map.yPhysicInTop(x, y - 24), playerId);
        List<Integer> spl = Arrays.asList(16,17,18,19,20);
        if (spl.contains(tempId)) {
        }
        return it;
    }
    public static ItemMap ratiDTL49(Zone zone, int tempId, int quantity, int x, int y, long playerId) {
        ItemMap it = new ItemMap(zone, tempId, quantity, x, zone.map.yPhysicInTop(x, y - 24), playerId);
        List<Integer> spl = Arrays.asList(220);
        if (spl.contains(tempId)) {
          it.options.add(new Item.ItemOption(215, 0));
        }
        return it;
    }
    public static ItemMap ratiDTL50(Zone zone, int tempId, int quantity, int x, int y, long playerId) {
        ItemMap it = new ItemMap(zone, tempId, quantity, x, zone.map.yPhysicInTop(x, y - 24), playerId);
        List<Integer> spl = Arrays.asList(221);
        if (spl.contains(tempId)) {
          it.options.add(new Item.ItemOption(218, 0));
        }
        return it;
    }
    public static ItemMap ratiDTL51(Zone zone, int tempId, int quantity, int x, int y, long playerId) {
        ItemMap it = new ItemMap(zone, tempId, quantity, x, zone.map.yPhysicInTop(x, y - 24), playerId);
        List<Integer> spl = Arrays.asList(222);
        if (spl.contains(tempId)) {
          it.options.add(new Item.ItemOption(216, 0));
        }
        return it;
    }
    public static ItemMap ratiDTL52(Zone zone, int tempId, int quantity, int x, int y, long playerId) {
        ItemMap it = new ItemMap(zone, tempId, quantity, x, zone.map.yPhysicInTop(x, y - 24), playerId);
        List<Integer> spl = Arrays.asList(223);
        if (spl.contains(tempId)) {
          it.options.add(new Item.ItemOption(217, 0));
        }
        return it;
    }
    public static ItemMap ratiDTL53(Zone zone, int tempId, int quantity, int x, int y, long playerId) {
        ItemMap it = new ItemMap(zone, tempId, quantity, x, zone.map.yPhysicInTop(x, y - 24), playerId);
        List<Integer> spl = Arrays.asList(224);
        if (spl.contains(tempId)) {
          it.options.add(new Item.ItemOption(219, 0));
        }
        return it;
    }
    public static ItemMap ratiDTL54(Zone zone, int tempId, int quantity, int x, int y, long playerId) {
        ItemMap it = new ItemMap(zone, tempId, quantity, x, zone.map.yPhysicInTop(x, y - 24), playerId);
        List<Integer> spl = Arrays.asList(590);
        if (spl.contains(tempId)) {
        }
        return it;
    }
   
    public static ItemMap RaitiDoc12(Zone zone, int tempId, int quantity, int x, int y, long playerId) {
        ItemMap it = new ItemMap(zone, tempId, quantity, x, y, playerId);
        List<Integer> ao = Arrays.asList(233, 237, 241);
        List<Integer> quan = Arrays.asList(245, 249, 253);
        List<Integer> gang = Arrays.asList(257, 261, 265);
        List<Integer> giay = Arrays.asList(269, 273, 277);
        int rd12 = 281;
        if (ao.contains(tempId)) {
            it.options.add(new Item.ItemOption(47, highlightsItem(it.itemTemplate.gender == 2, new Random().nextInt(121) + 350)));//giáp 350-470
        }
        if (quan.contains(tempId)) {
            it.options.add(new Item.ItemOption(22, highlightsItem(it.itemTemplate.gender == 0, new Random().nextInt(5) + 20)));//hp 20-24k
        }
        if (gang.contains(tempId)) {
            it.options.add(new Item.ItemOption(0, highlightsItem(it.itemTemplate.gender == 2, new Random().nextInt(51) + 2200)));//2200-2250
        }
        if (giay.contains(tempId)) {
            it.options.add(new Item.ItemOption(23, highlightsItem(it.itemTemplate.gender == 1, new Random().nextInt(4) + 20)));//20-23k ki
        }
        if (rd12 == tempId) {
            it.options.add(new Item.ItemOption(14, new Random().nextInt(3) + 10));//10-12cm
        }
        it.options.add(new Item.ItemOption(209, 1));//đồ rơi từ boss
        if (Util.isTrue(40, 100)) {// tỉ lệ ra spl 1-3 sao 70%
            it.options.add(new Item.ItemOption(107, new Random().nextInt(1) + 3));
        } else if (Util.isTrue(4, 100)) {// tỉ lệ ra spl 5-7 sao 4%
            it.options.add(new Item.ItemOption(107, new Random().nextInt(3) + 5));
        } else {// tỉ lệ ra spl 1-5 sao 6%
            it.options.add(new Item.ItemOption(107, new Random().nextInt(2) + 3));
        }
        return it;
    }
        public static ItemMap RaitiDoc1(Zone zone, int tempId, int quantity, int x, int y, long playerId) {
        ItemMap it = new ItemMap(zone, tempId, quantity, x, y, playerId);
        List<Integer> ao = Arrays.asList(233, 237, 241);
        List<Integer> quan = Arrays.asList(245, 249, 253);
        List<Integer> gang = Arrays.asList(257, 261, 265);
        List<Integer> giay = Arrays.asList(269, 273, 277);
        int rd12 = 281;
        if (ao.contains(tempId)) {
            it.options.add(new Item.ItemOption(47, highlightsItem(it.itemTemplate.gender == 2, new Random().nextInt(121) + 350)));//giáp 350-470
        }
        if (quan.contains(tempId)) {
            it.options.add(new Item.ItemOption(22, highlightsItem(it.itemTemplate.gender == 0, new Random().nextInt(5) + 20)));//hp 20-24k
        }
        if (gang.contains(tempId)) {
            it.options.add(new Item.ItemOption(0, highlightsItem(it.itemTemplate.gender == 2, new Random().nextInt(51) + 2200)));//2200-2250
        }
        if (giay.contains(tempId)) {
            it.options.add(new Item.ItemOption(23, highlightsItem(it.itemTemplate.gender == 1, new Random().nextInt(4) + 20)));//20-23k ki
        }
        if (rd12 == tempId) {
            it.options.add(new Item.ItemOption(14, new Random().nextInt(3) + 10));//10-12cm
        }
        it.options.add(new Item.ItemOption(209, 1));//đồ rơi từ boss
        if (Util.isTrue(40, 100)) {// tỉ lệ ra spl 1-3 sao 70%
            it.options.add(new Item.ItemOption(107, new Random().nextInt(1) + 3));
        } else if (Util.isTrue(4, 100)) {// tỉ lệ ra spl 5-7 sao 4%
            it.options.add(new Item.ItemOption(107, new Random().nextInt(3) + 5));
        } else {// tỉ lệ ra spl 1-5 sao 6%
            it.options.add(new Item.ItemOption(107, new Random().nextInt(2) + 3));
        }
        return it;
    }

    public static Item ratiItemTL(int tempId) {
        Item it = ItemService.gI().createItemSetKichHoat(tempId, 1);
        List<Integer> ao = Arrays.asList(555, 557, 559);
        List<Integer> quan = Arrays.asList(556, 558, 560);
        List<Integer> gang = Arrays.asList(562, 564, 566);
        List<Integer> giay = Arrays.asList(563, 565, 567);
        int ntl = 561;
        if (ao.contains(tempId)) {
            it.itemOptions.add(new Item.ItemOption(47, highlightsItem(it.template.gender == 2, new Random().nextInt(201) + 400)));
        }
        if (quan.contains(tempId)) {
            it.itemOptions.add(new Item.ItemOption(22, highlightsItem(it.template.gender == 0, new Random().nextInt(11) + 45)));
        }
        if (gang.contains(tempId)) {
            it.itemOptions.add(new Item.ItemOption(0, highlightsItem(it.template.gender == 2, new Random().nextInt(1001) + 3500)));
        }
        if (giay.contains(tempId)) {
            it.itemOptions.add(new Item.ItemOption(23, highlightsItem(it.template.gender == 1, new Random().nextInt(11) + 35)));
        }
        if (ntl == tempId) {
            it.itemOptions.add(new Item.ItemOption(14, new Random().nextInt(3) + 15));
        }
        it.itemOptions.add(new Item.ItemOption(21, 15));
        return it;
    }
    public static Item ratiItemHD(int tempId) {
        Item it = ItemService.gI().createItemSetKichHoat(tempId, 1);
        List<Integer> ao = Arrays.asList(650, 652, 654);
        List<Integer> quan = Arrays.asList(651, 653, 655);
        List<Integer> gang = Arrays.asList(657, 659, 661);
        List<Integer> giay = Arrays.asList(658, 660, 662);
        int ntl = 561;
        if (ao.contains(tempId)) {
            it.itemOptions.add(new Item.ItemOption(47, highlightsItem(it.template.gender == 2, new Random().nextInt(301) + 800)));
        }
        if (quan.contains(tempId)) {
            it.itemOptions.add(new Item.ItemOption(22, highlightsItem(it.template.gender == 0, new Random().nextInt(11) + 80)));
        }
        if (gang.contains(tempId)) {
            it.itemOptions.add(new Item.ItemOption(0, highlightsItem(it.template.gender == 2, new Random().nextInt(1001) + 7500)));
        }
        if (giay.contains(tempId)) {
            it.itemOptions.add(new Item.ItemOption(23, highlightsItem(it.template.gender == 1, new Random().nextInt(11) + 85)));
        }
        if (ntl == tempId) {
            it.itemOptions.add(new Item.ItemOption(14, new Random().nextInt(3) + 17));
        }
        it.itemOptions.add(new Item.ItemOption(21, 15));
        return it;
    }
    public static Item ratiItemTS(int tempId) {
        Item it = ItemService.gI().createItemSetKichHoat(tempId, 1);
        List<Integer> ao = Arrays.asList(1048, 1049, 1050);
        List<Integer> quan = Arrays.asList(1051, 1052, 1053);
        List<Integer> gang = Arrays.asList(1054, 1055, 1056);
        List<Integer> giay = Arrays.asList(1057, 1058, 1059);
        int ntl = 561;
        if (ao.contains(tempId)) {
            it.itemOptions.add(new Item.ItemOption(47, highlightsItem(it.template.gender == 2, new Random().nextInt(401) + 1200)));
        }
        if (quan.contains(tempId)) {
            it.itemOptions.add(new Item.ItemOption(22, highlightsItem(it.template.gender == 0, new Random().nextInt(11) + 150)));
        }
        if (gang.contains(tempId)) {
            it.itemOptions.add(new Item.ItemOption(0, highlightsItem(it.template.gender == 2, new Random().nextInt(1001) + 15500)));
        }
        if (giay.contains(tempId)) {
            it.itemOptions.add(new Item.ItemOption(23, highlightsItem(it.template.gender == 1, new Random().nextInt(11) + 150)));
        }
        if (ntl == tempId) {
            it.itemOptions.add(new Item.ItemOption(14, new Random().nextInt(3) + 20));
        }
        it.itemOptions.add(new Item.ItemOption(21, 15));
        return it;
    }

    public static ItemMap useItem(Zone zone, int tempId, int quantity, int x, int y, long playerId) {
        ItemMap it = new ItemMap(zone, tempId, quantity, x, zone.map.yPhysicInTop(x, y - 24), playerId);
        List<Integer> tanjiro = Arrays.asList(1087, 1088, 1091, 1090);
        if (tanjiro.contains(tempId)) {
            it.options.add(new Item.ItemOption(77, highlightsItem(it.itemTemplate.gender == 3, new Random().nextInt(30) + 1)));
            it.options.add(new Item.ItemOption(103, highlightsItem(it.itemTemplate.gender == 3, new Random().nextInt(30) + 1)));
            it.options.add(new Item.ItemOption(50, highlightsItem(it.itemTemplate.gender == 3, new Random().nextInt(30) + 1)));
        }
        it.options.add(new Item.ItemOption(209, 1)); // đồ rơi từ boss
        it.options.add(new Item.ItemOption(30, 1)); // ko thể gd

        return it;
    }

    public static ItemMap ratiItem(Zone zone, int tempId, int quantity, int x, int y, long playerId) {
        ItemMap it = new ItemMap(zone, tempId, quantity, x, y, playerId);
        List<Integer> ao = Arrays.asList(555, 557, 559);
        List<Integer> quan = Arrays.asList(556, 558, 560);
        List<Integer> gang = Arrays.asList(562, 564, 566);
        List<Integer> giay = Arrays.asList(563, 565, 567);
        int ntl = 561;
        if (ao.contains(tempId)) {
            it.options.add(new Item.ItemOption(47, highlightsItem(it.itemTemplate.gender == 2, new Random().nextInt(501) + 1000)));
        }
        if (quan.contains(tempId)) {
            it.options.add(new Item.ItemOption(22, highlightsItem(it.itemTemplate.gender == 0, new Random().nextInt(11) + 45)));
        }
        if (gang.contains(tempId)) {
            it.options.add(new Item.ItemOption(0, highlightsItem(it.itemTemplate.gender == 2, new Random().nextInt(1001) + 3500)));
        }
        if (giay.contains(tempId)) {
            it.options.add(new Item.ItemOption(23, highlightsItem(it.itemTemplate.gender == 1, new Random().nextInt(11) + 35)));
        }
        if (ntl == tempId) {
            it.options.add(new Item.ItemOption(14, new Random().nextInt(3) + 15));
        }
        it.options.add(new Item.ItemOption(209, 1));
        it.options.add(new Item.ItemOption(21, 15));
        return it;
    }
public static ItemMap ratiItem1(Zone zone, int tempId, int quantity, int x, int y, long playerId) {
        ItemMap it = new ItemMap(zone, tempId, quantity, x, y, playerId);
        List<Integer> ngocthach = Arrays.asList(2148);
        if (ngocthach.contains(tempId)) {
            it.options.add(new Item.ItemOption(30, 0));
        }
        it.options.add(new Item.ItemOption(30, 0));
       
        return it;
    }
    public static int highlightsItem(boolean highlights, int value) {
        double highlightsNumber = 1.1;
        return highlights ? (int) (value * highlightsNumber) : value;
    }

    public static Item sendDo(int itemId, int sql, List<Item.ItemOption> ios) {
//        InventoryServiceNew.gI().addItemBag(player, ItemService.gI().createItemFromItemShop(is));
//        InventoryServiceNew.gI().sendItemBags(player);
        Item item = ItemService.gI().createNewItem((short) itemId);
        item.itemOptions.addAll(ios);
        item.itemOptions.add(new Item.ItemOption(107, sql));
        return item;
    }

    public static boolean checkDo(Item.ItemOption itemOption) {
        switch (itemOption.optionTemplate.id) {
            case 0:// tấn công
                if (itemOption.param > 12000) {
                    return false;
                }
                break;
            case 14:// chí mạng
                if (itemOption.param > 30) {
                    return false;
                }
                break;
            case 107:// spl
            case 102:// spl
                if (itemOption.param > 8) {
                    return false;
                }
                break;
            case 77:
            case 103:
            case 95:
            case 96:
                if (itemOption.param > 41) {
                    return false;
                }
                break;
            case 50:// sd 3%
                if (itemOption.param > 24) {
                    return false;
                }
                break;
            case 6:// hp
            case 7:// ki
                if (itemOption.param > 120000) {
                    return false;
                }
                break;
            case 47:// giáp
                if (itemOption.param > 3500) {
                    return false;
                }
                break;
        }
        return true;
    }

    public static void useCheckDo(Player player, Item item, String position) {
        try {
            if (item.template != null) {
                if (item.template.id >= 381 && item.template.id <= 385) {
                    return;
                }
                if (item.template.id >= 66 && item.template.id <= 135) {
                    return;
                }
                if (item.template.id >= 474 && item.template.id <= 515) {
                    return;
                }
                item.itemOptions.forEach(itemOption -> {
                    if (!Util.checkDo(itemOption)) {
                        Logger.error(player.name + "-" + item.template.name + "-" + position + "\n");
                    }
                });
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static String phanthuong(int i) {
        switch (i) {
            case 1:
                return "5tr";
            case 2:
                return "3tr";
            case 3:
                return "1tr";
            default:
                return "100k";
        }
    }

    public static int randomBossId() {
        int bossId = Util.nextInt(10000);
        while (BossManager.gI().getBossById(bossId) != null) {
            bossId = Util.nextInt(10000);
        }
        return bossId;
    }

    public static long tinhLuyThua(int coSo, int soMu) {
        long ketQua = 1;

        for (int i = 0; i < soMu; i++) {
            ketQua *= coSo;
        }
        return ketQua;
    }

    public static void checkPlayer(Player player) {
        new Thread(() -> {
            List<Player> list = Client.gI().getPlayers().stream().filter(p -> !p.isPet && !p.isNewPet && p.getSession().userId == player.getSession().userId).collect(Collectors.toList());
            if (list.size() > 1) {
                list.forEach(pp -> Client.gI().kickSession(pp.getSession()));
                list.clear();
            }
        }).start();
    }

    public static void showListTop(Player player, byte b) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    public static int[] pickNRandInArr(int[] listItem, int i) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    public static boolean isTrue(int i, int i0, int i1, int i2, int i3) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    public static boolean isTimeHiru(){
        LocalTime currentTime = LocalTime.now();
        int hour = currentTime.getHour();
        return (hour == Mob.TIME_START_HIRU);
    }

    public static ItemMap HangBang(Zone zone, int itemDo, int i, int x, int y, long id) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }
}
