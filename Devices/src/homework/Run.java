package homework;

import homework.DeviceList;
import homework.WifiDevice;
public class Run {
    public static void main(String[] args) {
        String filePath = "D:\\\\Desktop\\\\xuexi\\\\Java\\\\scanlist.txt";
        WifiDevice[] wifiArray = new WifiDevice[10];
        DeviceList test = new DeviceList();
        test.readTxtFile(filePath, wifiArray);
        test.searchAddress("18:D9:8F:BE:DB:7D", wifiArray);
        System.out.println();
        test.searchEssid("\"www.iaxel.cn_2.4G\"", wifiArray);
        System.out.println();
        String str = "-96";
        test.searchSignal(str, wifiArray);
        System.out.println();
        str = "15";
        test.searchChannel(str, wifiArray);
        System.out.println();
        test.addresses(wifiArray);
        System.out.println();
        test.channels(wifiArray);
    }
}
