package other;

import java.text.SimpleDateFormat;
import java.util.Date;
/**
 * @author xie
 * @create 2022-05-20-19:03
 */
public class Methods {

    public static String getCurrentTime(){
        java.util.Date day=new Date();
        SimpleDateFormat sdf= new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        return sdf.format(day);
    }
}
