package support.core;

import java.util.HashMap;

public class AppSystemInfo {

    public HashMap<String,String> getAppSystemInfo(){

        HashMap<String,String> appSystemInfo = new HashMap<>();
        appSystemInfo.put("app name", "CieloApp");
        appSystemInfo.put("app version", "5.2.1");
        return appSystemInfo;
    }

}
