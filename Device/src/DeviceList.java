import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.Objects;

public class DeviceList {
    public DeviceList() {
    }

    public static void readTxtFile(String filePath, WifiDevice[] wifiArray) {
        int temp = 0;
        int index = 0;

        try {
            String encoding = "GBK";
            File file = new File(filePath);
            if (file.isFile() && file.exists()) {
                InputStreamReader read = new InputStreamReader(new FileInputStream(file), encoding);
                BufferedReader bufferedReader = new BufferedReader(read);
                String lineTxt = null;
                WifiDevice a0 = new WifiDevice();
                WifiDevice a1 = new WifiDevice();
                WifiDevice a2 = new WifiDevice();
                WifiDevice a3 = new WifiDevice();
                WifiDevice a4 = new WifiDevice();
                WifiDevice a5 = new WifiDevice();
                WifiDevice a6 = new WifiDevice();
                WifiDevice a7 = new WifiDevice();
                WifiDevice a8 = new WifiDevice();
                WifiDevice a9 = new WifiDevice();

                while((lineTxt = bufferedReader.readLine()) != null) {
                    if (index == 0) a0 = a1;
                    if (index == 1) a0 = a2;
                    if (index == 2) a0 = a3;
                    if (index == 3) a0 = a4;
                    if (index == 4) a0 = a5;
                    if (index == 5) a0 = a6;
                    if (index == 6) a0 = a7;
                    if (index == 7) a0 = a8;
                    if (index == 8) a0 = a9;

                    if (temp == 0) {
                        a0.setAddress(lineTxt.substring(19));
                        wifiArray[index] = a0;
                    }

                    if (temp == 1) {
                        a0.setESSID(lineTxt.substring(17));
                        wifiArray[index] = a0;
                    }

                    String actual;
                    if (temp == 2) {
                        a0.setMode(lineTxt.substring(16, 22));
                        wifiArray[index] = a0;
                        a0.setChannel(lineTxt.substring(33).getBytes());
                        wifiArray[index] = a0;
                        actual = a0.getChannel().toString();
                        new String(wifiArray[index].getChannel());
                    }

                    if (temp == 3) {
                        a0.setSignal(lineTxt.substring(18, 21).getBytes());
                        wifiArray[index] = a0;
                        actual = a0.getSignal().toString();
                        new String(wifiArray[index].getSignal());
                        a0.setQuality(lineTxt.substring(36));
                        wifiArray[index] = a0;
                    }

                    if (temp == 4) {
                        a0.setEncryption(lineTxt.substring(22));
                        wifiArray[index] = a0;
                    }

                    ++temp;
                    if (temp == 5) {
                    	temp = -1;
                        ++index;
                    }
                }
                read.close();
            } else {
                System.out.println("Can't find file!");
            }
        } catch (Exception var21) {
            System.out.println("Wrong!");
            var21.printStackTrace();
        }
    }

    public void searchAddress(String Address, WifiDevice[] wifiArray) {
        int count = 0;

        for(int i = 0; i < 9; ++i) {
            if (Objects.equals(wifiArray[i].getAddress(), Address)) {
                System.out.println(wifiArray[i].toString());
                break;
            }
            ++count;
            if (count == 9) {
                System.out.println("device not exist");
            }
        }

    }

  public void searchEssid(String Essid, WifiDevice[] wifiArray) {
        int count = 0;

        for(int i = 0; i < 9; ++i) {
            if (Objects.equals(wifiArray[i].getESSID(), Essid)) {
                System.out.println(wifiArray[i].toString());
                break;
            }

            ++count;
            if (count == 9) {
                System.out.println("device not exist");
            }
        }

    }

    public void searchSignal(String Signal, WifiDevice[] wifiArray) {
        int count = 0;

        for(int i = 0; i < 9; ++i) {
            String value = new String(wifiArray[i].getSignal());
            if (Objects.equals(value, Signal)) {
                System.out.println(wifiArray[i].toString());
                break;
            }

            ++count;
            if (count == 9) {
                System.out.println("device not exist");
            }
        }

    }

    public void searchChannel(String Channel, WifiDevice[] wifiArray) {
        int count = 0;

        for(int i = 0; i < 9; ++i) {
            String value = new String(wifiArray[i].getChannel());
            if (Objects.equals(value, Channel)) {
                System.out.println(wifiArray[i].toString());
                break;
            }

            ++count;
            if (count == 9) {
                System.out.println("device not exist");
            }
        }

    }

    public void addresses(WifiDevice[] wifiArray) {
        for(int i = 0; i < 9; ++i) {
            System.out.println(wifiArray[i].getAddress());
        }

    }

    public void essids(WifiDevice[] wifiArray) {
        for(int i = 0; i < 9; ++i) {
            System.out.println(wifiArray[i].getESSID());
        }

    }

    public void channels(WifiDevice[] wifiArray) {
        for(int i = 0; i < 9; ++i) {
            String actual3 = wifiArray[i].getChannel().toString();
            String actual4 = new String(wifiArray[i].getChannel());
            System.out.println(actual4);
        }

    }

    public static void main(String[] args) {
        String filePath = "src/scanlist.txt";
        WifiDevice[] wifiArray = new WifiDevice[10];
        DeviceList test = new DeviceList();
        readTxtFile(filePath, wifiArray);
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

