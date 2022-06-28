public class WifiDevice {
    private String address;
    private byte[] channel;
    private byte[] signal;
    private String essid;
    private String mode;
    private String quality;
    private String encryption;

    public WifiDevice() {
    }

    public String getAddress() {
        return this.address;
    }

    public void setAddress(String Address) {
        this.address = Address;
    }

    public String getESSID() {
        return this.essid;
    }

    public void setESSID(String Essid) {
        this.essid = Essid;
    }

    public String getMode() {
        return this.mode;
    }

    public void setMode(String Mode) {
        this.mode = Mode;
    }

    public byte[] getChannel() {
        return this.channel;
    }

    public void setChannel(byte[] Channel) {
        this.channel = Channel;
    }

    public byte[] getSignal() {
        return this.signal;
    }

    public void setSignal(byte[] Signal) {
        this.signal = Signal;
    }

    public String getQuality() {
        return this.quality;
    }

    public void setQuality(String Quality) {
        this.quality = Quality;
    }

    public String getEncryption() {
        return this.encryption;
    }

    public void setEncryption(String Encryption) {
        this.encryption = Encryption;
    }

    public String toString() {
        String actual0 = this.channel.toString();
        String actual1 = new String(this.channel);
        String actual3 = this.signal.toString();
        String actual4 = new String(this.signal);
        return "Address:" + this.address + " Essid:" + this.essid + " Mode:" + this.mode + " Channel:" + actual1 + " Signal:" + actual4 + " Quality:" + this.quality + " Encryption:" + this.encryption;
    }
}