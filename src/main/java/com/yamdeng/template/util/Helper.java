package com.yamdeng.template.util;

public class Helper {
    
    // 첫 글자를 소문자로
    private static String changeStringToFirstLower(String str) {
        return Character.toLowerCase(str.charAt(0)) + str.substring(1);
    }

    // 복수형으로 변경
    private static String changeStringToPlural(String str) {
        if ((str.endsWith("s")) || (str.endsWith("z")) || (str.endsWith("x")) || (str.endsWith("sh"))
                || (str.endsWith("ch"))) {
            str = str + "es";
        } else if (str.endsWith("y")) {
            if ((str.endsWith("ay")) || (str.endsWith("ey")) || (str.endsWith("iy")) || (str.endsWith("oy"))
                    || (str.endsWith("uy"))) {
                str = str + "s";
            } else {
                str = str.substring(0, str.length() - 1) + "ies";
            }
        } else if (str.endsWith("o")) {
            if ((str.endsWith("ao")) || (str.endsWith("eo")) || (str.endsWith("io")) || (str.endsWith("oo"))
                    || (str.endsWith("uo"))) {
                str = str + "s";
            } else {
                str = str + "es";
            }
        } else if ((str.endsWith("f")) && (str.length() >= 1)) {
            str = str.substring(0, str.length() - 1) + "ves";
        } else if ((str.endsWith("fe")) && (str.length() >= 2)) {
            str = str.substring(0, str.length() - 2) + "ves";
        } else if (!str.endsWith("\"")) {
            str = str + "s";
        }
        return str;
    }

    public static String getRestUriName(String entityName) {
        // Member ---> members
        String firstLowerConvertString = changeStringToFirstLower(entityName);
        return changeStringToPlural(firstLowerConvertString);
    }

    public static String getRestServiceName(String entityName) {
        // Member ---> memberService
        String firstLowerConvertString = changeStringToFirstLower(entityName);
        return firstLowerConvertString + "Service";
    }

    public static String getRestDtoName(String entityName) {
        // Member ---> MemberDto
        String firstLowerConvertString = changeStringToFirstLower(entityName);
        return firstLowerConvertString + "Dto";
    }
    
}
