package reboot.spring.bean.profile;

public class DevProfileService implements IProfileService {

    @Override
    public String getProfileName() {
        return "dev profile";
    }
}
