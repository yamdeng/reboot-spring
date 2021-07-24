package reboot.spring.web.validator;

import java.util.regex.Matcher;
import java.util.regex.Pattern;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;
import reboot.spring.bean.vo.FirstVo;
import reboot.spring.bean.vo.Member;

public class MemberValidator implements Validator {

    private static final String emailRegExp =
        "^[_A-Za-z0-9-\\+]+(\\.[_A-Za-z0-9-]+)*@" +
            "[A-Za-z0-9-]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$";

    private static final Pattern pattern = Pattern.compile(emailRegExp);

    public MemberValidator() {
    }

    @Override
    public boolean supports(Class<?> clazz) {
        return Member.class.isAssignableFrom(clazz);
    }

    @Override
    public void validate(Object target, Errors errors) {
        Member member = (Member) target;
        ValidationUtils.rejectIfEmpty(errors,"name", "name-empty");
        ValidationUtils.rejectIfEmptyOrWhitespace(errors,"name", "name-whitespace");
        ValidationUtils.rejectIfEmptyOrWhitespace(errors,"email", "email-whitespace");
        if(member.getPassword() == null || member.getPassword().trim().isEmpty()) {
            errors.rejectValue("password", "password-required");
        }
        if(member.getEmail() != null) {
            Matcher matcher = pattern.matcher(member.getEmail());
            if (!matcher.matches()) {
                errors.rejectValue("email", "email-not-pattern");
            }
        }
    }

}
