package reboot.spring.main;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.core.env.Environment;
import reboot.spring.bean.profile.IProfileService;
import reboot.spring.config.DevProfileConfig;
import reboot.spring.config.RealProfileConfig;
import reboot.spring.config.StageProfileConfig;

import java.util.Arrays;
import java.util.Map;

public class ProfileMain {

    public static void main(String[] args) {
//        checkProfileByCtxApi();
//        checkProfileByCtxSystemProperty();
        checkProfileByCmdOption();
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
//        System.setProperty("spring.profiles.active", "stage,dev");
        AnnotationConfigApplicationContext ctx =
                new AnnotationConfigApplicationContext(RealProfileConfig.class, DevProfileConfig.class, StageProfileConfig.class);
        Environment environment = ctx.getBean(Environment.class);
        System.out.println("spring.profiles.active : " + Arrays.toString(environment.getActiveProfiles()));
        IProfileService profileService = ctx.getBean(IProfileService.class);
        System.out.println("profileService cmd option : " + profileService.getProfileName());
        Map<String, String> stringMap = ctx.getBean("stringMap", Map.class);
        System.out.println("stringMap : " + stringMap);
        ctx.close();
    }

}
