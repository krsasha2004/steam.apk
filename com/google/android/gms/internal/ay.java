package com.google.android.gms.internal;

import android.text.TextUtils;
import com.valvesoftware.android.steam.community.C0151R;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public final class ay {
    private static final Pattern bR;
    private static final Pattern bS;

    static {
        bR = Pattern.compile("\\\\.");
        bS = Pattern.compile("[\\\\\"/\b\f\n\r\t]");
    }

    public static String m214o(String str) {
        if (TextUtils.isEmpty(str)) {
            return str;
        }
        Matcher matcher = bS.matcher(str);
        StringBuffer stringBuffer = null;
        while (matcher.find()) {
            if (stringBuffer == null) {
                stringBuffer = new StringBuffer();
            }
            switch (matcher.group().charAt(0)) {
                case C0151R.styleable.Toolbar_popupTheme /*8*/:
                    matcher.appendReplacement(stringBuffer, "\\\\b");
                    break;
                case C0151R.styleable.Toolbar_titleTextAppearance /*9*/:
                    matcher.appendReplacement(stringBuffer, "\\\\t");
                    break;
                case C0151R.styleable.Toolbar_subtitleTextAppearance /*10*/:
                    matcher.appendReplacement(stringBuffer, "\\\\n");
                    break;
                case C0151R.styleable.Toolbar_titleMarginStart /*12*/:
                    matcher.appendReplacement(stringBuffer, "\\\\f");
                    break;
                case C0151R.styleable.Toolbar_titleMarginEnd /*13*/:
                    matcher.appendReplacement(stringBuffer, "\\\\r");
                    break;
                case C0151R.styleable.Theme_actionModeWebSearchDrawable /*34*/:
                    matcher.appendReplacement(stringBuffer, "\\\\\\\"");
                    break;
                case C0151R.styleable.Theme_selectableItemBackgroundBorderless /*47*/:
                    matcher.appendReplacement(stringBuffer, "\\\\/");
                    break;
                case '\\':
                    matcher.appendReplacement(stringBuffer, "\\\\\\\\");
                    break;
                default:
                    break;
            }
        }
        if (stringBuffer == null) {
            return str;
        }
        matcher.appendTail(stringBuffer);
        return stringBuffer.toString();
    }
}
