package reboot.spring.boot.platform.license;

public interface LicenseCheck {

    public String currentVersion();

    public void init();

    public void destroy();

}
