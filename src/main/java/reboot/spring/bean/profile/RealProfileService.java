package reboot.spring.bean.profile;

public class RealProfileService implements IProfileService {

    @Override
    public String getProfileName() {
        return "real profile";
    }
}
