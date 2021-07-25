package reboot.spring.main;

import java.util.Map;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import reboot.spring.bean.profile.IProfileService;
import reboot.spring.config.DevProfileConfig;
import reboot.spring.config.RealProfileConfig;
import reboot.spring.config.StageProfileConfig;

public class ProfileMain {

    public static void main(String[] args) {
        checkProfileByCtxApi();
        checkProfileByCtxSystemProperty();
//        checkProfileByCmdOption();
    }

    private static void checkProfileByCtxApi() {
        AnnotationConfigApplicationContext ctx = new AnnotationConfigApplicationContext();
        ctx.getEnvironment().setActiveProfiles("dev");
        ctx.register(RealProfileConfig.class, DevProfileConfig.class);
        ctx.refresh();
        IProfileService profileService = ctx.getBean(IProfileService.class);
        System.out.println("profileService ctx : " + profileService.getProfileName());
        ctx.close();
    }

    private static void checkProfileByCtxSystemProperty() {
        System.setProperty("spring.profiles.active", "real");
        AnnotationConfigApplicationContext ctx =
            new AnnotationConfigApplicationContext(RealProfileConfig.class, DevProfileConfig.class);
        IProfileService profileService = ctx.getBean(IProfileService.class);
        System.out.println("profileService system : " + profileService.getProfileName());
        ctx.close();
    }

    private static void checkProfileByCmdOption() {
        AnnotationConfigApplicationContext ctx =
            new AnnotationConfigApplicationContext(RealProfileConfig.class, DevProfileConfig.class, StageProfileConfig.class);
        IProfileService profileService = ctx.getBean(IProfileService.class);
        System.out.println("profileService cmd option : " + profileService.getProfileName());
        Map<String, String> stringMap = ctx.getBean("stringMap", Map.class);
        System.out.println("stringMap : " + stringMap);
        ctx.close();
    }

}
