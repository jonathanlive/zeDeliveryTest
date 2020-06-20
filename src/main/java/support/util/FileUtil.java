package support.util;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Random;

public class FileUtil {

    public static String createRandomFileName(String prefixName,String extension) {
        String date = new SimpleDateFormat("ddMMyyy_HHmm").format(new Date());
        int randNum = new Random().nextInt(999999);
        return prefixName + "_" + date + "_" + randNum + extension;
    }
}

