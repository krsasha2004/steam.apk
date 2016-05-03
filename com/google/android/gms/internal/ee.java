package com.google.android.gms.internal;

import android.os.Parcel;
import android.os.Parcelable.Creator;
import com.google.android.gms.internal.ac.C0109a;
import com.valvesoftware.android.steam.community.C0151R;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class ee implements Creator<ed> {
    static void m253a(ed edVar, Parcel parcel, int i) {
        int d = ad.m96d(parcel);
        Set bz = edVar.bz();
        if (bz.contains(Integer.valueOf(1))) {
            ad.m94c(parcel, 1, edVar.m252u());
        }
        if (bz.contains(Integer.valueOf(2))) {
            ad.m83a(parcel, 2, edVar.bA(), i, true);
        }
        if (bz.contains(Integer.valueOf(3))) {
            ad.m85a(parcel, 3, edVar.getAdditionalName(), true);
        }
        if (bz.contains(Integer.valueOf(4))) {
            ad.m83a(parcel, 4, edVar.bB(), i, true);
        }
        if (bz.contains(Integer.valueOf(5))) {
            ad.m84a(parcel, 5, edVar.getAddressCountry(), true);
        }
        if (bz.contains(Integer.valueOf(6))) {
            ad.m84a(parcel, 6, edVar.getAddressLocality(), true);
        }
        if (bz.contains(Integer.valueOf(7))) {
            ad.m84a(parcel, 7, edVar.getAddressRegion(), true);
        }
        if (bz.contains(Integer.valueOf(8))) {
            ad.m93b(parcel, 8, edVar.bC(), true);
        }
        if (bz.contains(Integer.valueOf(9))) {
            ad.m94c(parcel, 9, edVar.getAttendeeCount());
        }
        if (bz.contains(Integer.valueOf(10))) {
            ad.m93b(parcel, 10, edVar.bD(), true);
        }
        if (bz.contains(Integer.valueOf(11))) {
            ad.m83a(parcel, 11, edVar.bE(), i, true);
        }
        if (bz.contains(Integer.valueOf(12))) {
            ad.m93b(parcel, 12, edVar.bF(), true);
        }
        if (bz.contains(Integer.valueOf(13))) {
            ad.m84a(parcel, 13, edVar.getBestRating(), true);
        }
        if (bz.contains(Integer.valueOf(14))) {
            ad.m84a(parcel, 14, edVar.getBirthDate(), true);
        }
        if (bz.contains(Integer.valueOf(15))) {
            ad.m83a(parcel, 15, edVar.bG(), i, true);
        }
        if (bz.contains(Integer.valueOf(17))) {
            ad.m84a(parcel, 17, edVar.getContentSize(), true);
        }
        if (bz.contains(Integer.valueOf(16))) {
            ad.m84a(parcel, 16, edVar.getCaption(), true);
        }
        if (bz.contains(Integer.valueOf(19))) {
            ad.m93b(parcel, 19, edVar.bH(), true);
        }
        if (bz.contains(Integer.valueOf(18))) {
            ad.m84a(parcel, 18, edVar.getContentUrl(), true);
        }
        if (bz.contains(Integer.valueOf(21))) {
            ad.m84a(parcel, 21, edVar.getDateModified(), true);
        }
        if (bz.contains(Integer.valueOf(20))) {
            ad.m84a(parcel, 20, edVar.getDateCreated(), true);
        }
        if (bz.contains(Integer.valueOf(23))) {
            ad.m84a(parcel, 23, edVar.getDescription(), true);
        }
        if (bz.contains(Integer.valueOf(22))) {
            ad.m84a(parcel, 22, edVar.getDatePublished(), true);
        }
        if (bz.contains(Integer.valueOf(25))) {
            ad.m84a(parcel, 25, edVar.getEmbedUrl(), true);
        }
        if (bz.contains(Integer.valueOf(24))) {
            ad.m84a(parcel, 24, edVar.getDuration(), true);
        }
        if (bz.contains(Integer.valueOf(27))) {
            ad.m84a(parcel, 27, edVar.getFamilyName(), true);
        }
        if (bz.contains(Integer.valueOf(26))) {
            ad.m84a(parcel, 26, edVar.getEndDate(), true);
        }
        if (bz.contains(Integer.valueOf(29))) {
            ad.m83a(parcel, 29, edVar.bI(), i, true);
        }
        if (bz.contains(Integer.valueOf(28))) {
            ad.m84a(parcel, 28, edVar.getGender(), true);
        }
        if (bz.contains(Integer.valueOf(31))) {
            ad.m84a(parcel, 31, edVar.getHeight(), true);
        }
        if (bz.contains(Integer.valueOf(30))) {
            ad.m84a(parcel, 30, edVar.getGivenName(), true);
        }
        if (bz.contains(Integer.valueOf(34))) {
            ad.m83a(parcel, 34, edVar.bJ(), i, true);
        }
        if (bz.contains(Integer.valueOf(32))) {
            ad.m84a(parcel, 32, edVar.getId(), true);
        }
        if (bz.contains(Integer.valueOf(33))) {
            ad.m84a(parcel, 33, edVar.getImage(), true);
        }
        if (bz.contains(Integer.valueOf(38))) {
            ad.m77a(parcel, 38, edVar.getLongitude());
        }
        if (bz.contains(Integer.valueOf(39))) {
            ad.m84a(parcel, 39, edVar.getName(), true);
        }
        if (bz.contains(Integer.valueOf(36))) {
            ad.m77a(parcel, 36, edVar.getLatitude());
        }
        if (bz.contains(Integer.valueOf(37))) {
            ad.m83a(parcel, 37, edVar.bK(), i, true);
        }
        if (bz.contains(Integer.valueOf(42))) {
            ad.m84a(parcel, 42, edVar.getPlayerType(), true);
        }
        if (bz.contains(Integer.valueOf(43))) {
            ad.m84a(parcel, 43, edVar.getPostOfficeBoxNumber(), true);
        }
        if (bz.contains(Integer.valueOf(40))) {
            ad.m83a(parcel, 40, edVar.bL(), i, true);
        }
        if (bz.contains(Integer.valueOf(41))) {
            ad.m93b(parcel, 41, edVar.bM(), true);
        }
        if (bz.contains(Integer.valueOf(46))) {
            ad.m83a(parcel, 46, edVar.bN(), i, true);
        }
        if (bz.contains(Integer.valueOf(47))) {
            ad.m84a(parcel, 47, edVar.getStartDate(), true);
        }
        if (bz.contains(Integer.valueOf(44))) {
            ad.m84a(parcel, 44, edVar.getPostalCode(), true);
        }
        if (bz.contains(Integer.valueOf(45))) {
            ad.m84a(parcel, 45, edVar.getRatingValue(), true);
        }
        if (bz.contains(Integer.valueOf(51))) {
            ad.m84a(parcel, 51, edVar.getThumbnailUrl(), true);
        }
        if (bz.contains(Integer.valueOf(50))) {
            ad.m83a(parcel, 50, edVar.bO(), i, true);
        }
        if (bz.contains(Integer.valueOf(49))) {
            ad.m84a(parcel, 49, edVar.getText(), true);
        }
        if (bz.contains(Integer.valueOf(48))) {
            ad.m84a(parcel, 48, edVar.getStreetAddress(), true);
        }
        if (bz.contains(Integer.valueOf(55))) {
            ad.m84a(parcel, 55, edVar.getWidth(), true);
        }
        if (bz.contains(Integer.valueOf(54))) {
            ad.m84a(parcel, 54, edVar.getUrl(), true);
        }
        if (bz.contains(Integer.valueOf(53))) {
            ad.m84a(parcel, 53, edVar.getType(), true);
        }
        if (bz.contains(Integer.valueOf(52))) {
            ad.m84a(parcel, 52, edVar.getTickerSymbol(), true);
        }
        if (bz.contains(Integer.valueOf(56))) {
            ad.m84a(parcel, 56, edVar.getWorstRating(), true);
        }
        ad.m75C(parcel, d);
    }

    public ed[] m254Q(int i) {
        return new ed[i];
    }

    public /* synthetic */ Object createFromParcel(Parcel x0) {
        return m255v(x0);
    }

    public /* synthetic */ Object[] newArray(int x0) {
        return m254Q(x0);
    }

    public ed m255v(Parcel parcel) {
        int c = ac.m46c(parcel);
        Set hashSet = new HashSet();
        int i = 0;
        ed edVar = null;
        List list = null;
        ed edVar2 = null;
        String str = null;
        String str2 = null;
        String str3 = null;
        List list2 = null;
        int i2 = 0;
        List list3 = null;
        ed edVar3 = null;
        List list4 = null;
        String str4 = null;
        String str5 = null;
        ed edVar4 = null;
        String str6 = null;
        String str7 = null;
        String str8 = null;
        List list5 = null;
        String str9 = null;
        String str10 = null;
        String str11 = null;
        String str12 = null;
        String str13 = null;
        String str14 = null;
        String str15 = null;
        String str16 = null;
        String str17 = null;
        ed edVar5 = null;
        String str18 = null;
        String str19 = null;
        String str20 = null;
        String str21 = null;
        ed edVar6 = null;
        double d = 0.0d;
        ed edVar7 = null;
        double d2 = 0.0d;
        String str22 = null;
        ed edVar8 = null;
        List list6 = null;
        String str23 = null;
        String str24 = null;
        String str25 = null;
        String str26 = null;
        ed edVar9 = null;
        String str27 = null;
        String str28 = null;
        String str29 = null;
        ed edVar10 = null;
        String str30 = null;
        String str31 = null;
        String str32 = null;
        String str33 = null;
        String str34 = null;
        String str35 = null;
        while (parcel.dataPosition() < c) {
            int b = ac.m43b(parcel);
            ed edVar11;
            switch (ac.m56j(b)) {
                case C0151R.styleable.View_paddingStart /*1*/:
                    i = ac.m51f(parcel, b);
                    hashSet.add(Integer.valueOf(1));
                    break;
                case C0151R.styleable.View_paddingEnd /*2*/:
                    edVar11 = (ed) ac.m40a(parcel, b, ed.CREATOR);
                    hashSet.add(Integer.valueOf(2));
                    edVar = edVar11;
                    break;
                case C0151R.styleable.Toolbar_subtitle /*3*/:
                    list = ac.m70x(parcel, b);
                    hashSet.add(Integer.valueOf(3));
                    break;
                case C0151R.styleable.Toolbar_contentInsetStart /*4*/:
                    edVar11 = (ed) ac.m40a(parcel, b, ed.CREATOR);
                    hashSet.add(Integer.valueOf(4));
                    edVar2 = edVar11;
                    break;
                case C0151R.styleable.Toolbar_contentInsetEnd /*5*/:
                    str = ac.m58l(parcel, b);
                    hashSet.add(Integer.valueOf(5));
                    break;
                case C0151R.styleable.Toolbar_contentInsetLeft /*6*/:
                    str2 = ac.m58l(parcel, b);
                    hashSet.add(Integer.valueOf(6));
                    break;
                case C0151R.styleable.Toolbar_contentInsetRight /*7*/:
                    str3 = ac.m58l(parcel, b);
                    hashSet.add(Integer.valueOf(7));
                    break;
                case C0151R.styleable.Toolbar_popupTheme /*8*/:
                    list2 = ac.m47c(parcel, b, ed.CREATOR);
                    hashSet.add(Integer.valueOf(8));
                    break;
                case C0151R.styleable.Toolbar_titleTextAppearance /*9*/:
                    i2 = ac.m51f(parcel, b);
                    hashSet.add(Integer.valueOf(9));
                    break;
                case C0151R.styleable.Toolbar_subtitleTextAppearance /*10*/:
                    list3 = ac.m47c(parcel, b, ed.CREATOR);
                    hashSet.add(Integer.valueOf(10));
                    break;
                case C0151R.styleable.Toolbar_titleMargins /*11*/:
                    edVar11 = (ed) ac.m40a(parcel, b, ed.CREATOR);
                    hashSet.add(Integer.valueOf(11));
                    edVar3 = edVar11;
                    break;
                case C0151R.styleable.Toolbar_titleMarginStart /*12*/:
                    list4 = ac.m47c(parcel, b, ed.CREATOR);
                    hashSet.add(Integer.valueOf(12));
                    break;
                case C0151R.styleable.Toolbar_titleMarginEnd /*13*/:
                    str4 = ac.m58l(parcel, b);
                    hashSet.add(Integer.valueOf(13));
                    break;
                case C0151R.styleable.Toolbar_titleMarginTop /*14*/:
                    str5 = ac.m58l(parcel, b);
                    hashSet.add(Integer.valueOf(14));
                    break;
                case C0151R.styleable.Toolbar_titleMarginBottom /*15*/:
                    edVar11 = (ed) ac.m40a(parcel, b, ed.CREATOR);
                    hashSet.add(Integer.valueOf(15));
                    edVar4 = edVar11;
                    break;
                case C0151R.styleable.Toolbar_maxButtonHeight /*16*/:
                    str6 = ac.m58l(parcel, b);
                    hashSet.add(Integer.valueOf(16));
                    break;
                case C0151R.styleable.Toolbar_theme /*17*/:
                    str7 = ac.m58l(parcel, b);
                    hashSet.add(Integer.valueOf(17));
                    break;
                case C0151R.styleable.Toolbar_collapseIcon /*18*/:
                    str8 = ac.m58l(parcel, b);
                    hashSet.add(Integer.valueOf(18));
                    break;
                case C0151R.styleable.Toolbar_collapseContentDescription /*19*/:
                    list5 = ac.m47c(parcel, b, ed.CREATOR);
                    hashSet.add(Integer.valueOf(19));
                    break;
                case C0151R.styleable.Toolbar_navigationIcon /*20*/:
                    str9 = ac.m58l(parcel, b);
                    hashSet.add(Integer.valueOf(20));
                    break;
                case C0151R.styleable.Toolbar_navigationContentDescription /*21*/:
                    str10 = ac.m58l(parcel, b);
                    hashSet.add(Integer.valueOf(21));
                    break;
                case C0151R.styleable.Theme_actionMenuTextColor /*22*/:
                    str11 = ac.m58l(parcel, b);
                    hashSet.add(Integer.valueOf(22));
                    break;
                case C0151R.styleable.Theme_actionModeStyle /*23*/:
                    str12 = ac.m58l(parcel, b);
                    hashSet.add(Integer.valueOf(23));
                    break;
                case C0151R.styleable.Theme_actionModeCloseButtonStyle /*24*/:
                    str13 = ac.m58l(parcel, b);
                    hashSet.add(Integer.valueOf(24));
                    break;
                case C0151R.styleable.Theme_actionModeBackground /*25*/:
                    str14 = ac.m58l(parcel, b);
                    hashSet.add(Integer.valueOf(25));
                    break;
                case C0151R.styleable.Theme_actionModeSplitBackground /*26*/:
                    str15 = ac.m58l(parcel, b);
                    hashSet.add(Integer.valueOf(26));
                    break;
                case C0151R.styleable.Theme_actionModeCloseDrawable /*27*/:
                    str16 = ac.m58l(parcel, b);
                    hashSet.add(Integer.valueOf(27));
                    break;
                case C0151R.styleable.Theme_actionModeCutDrawable /*28*/:
                    str17 = ac.m58l(parcel, b);
                    hashSet.add(Integer.valueOf(28));
                    break;
                case C0151R.styleable.Theme_actionModeCopyDrawable /*29*/:
                    edVar11 = (ed) ac.m40a(parcel, b, ed.CREATOR);
                    hashSet.add(Integer.valueOf(29));
                    edVar5 = edVar11;
                    break;
                case C0151R.styleable.Theme_actionModePasteDrawable /*30*/:
                    str18 = ac.m58l(parcel, b);
                    hashSet.add(Integer.valueOf(30));
                    break;
                case C0151R.styleable.Theme_actionModeSelectAllDrawable /*31*/:
                    str19 = ac.m58l(parcel, b);
                    hashSet.add(Integer.valueOf(31));
                    break;
                case C0151R.styleable.Theme_actionModeShareDrawable /*32*/:
                    str20 = ac.m58l(parcel, b);
                    hashSet.add(Integer.valueOf(32));
                    break;
                case C0151R.styleable.Theme_actionModeFindDrawable /*33*/:
                    str21 = ac.m58l(parcel, b);
                    hashSet.add(Integer.valueOf(33));
                    break;
                case C0151R.styleable.Theme_actionModeWebSearchDrawable /*34*/:
                    edVar11 = (ed) ac.m40a(parcel, b, ed.CREATOR);
                    hashSet.add(Integer.valueOf(34));
                    edVar6 = edVar11;
                    break;
                case C0151R.styleable.Theme_textAppearanceLargePopupMenu /*36*/:
                    d = ac.m55j(parcel, b);
                    hashSet.add(Integer.valueOf(36));
                    break;
                case C0151R.styleable.Theme_textAppearanceSmallPopupMenu /*37*/:
                    edVar11 = (ed) ac.m40a(parcel, b, ed.CREATOR);
                    hashSet.add(Integer.valueOf(37));
                    edVar7 = edVar11;
                    break;
                case C0151R.styleable.Theme_actionDropDownStyle /*38*/:
                    d2 = ac.m55j(parcel, b);
                    hashSet.add(Integer.valueOf(38));
                    break;
                case C0151R.styleable.Theme_dropdownListPreferredItemHeight /*39*/:
                    str22 = ac.m58l(parcel, b);
                    hashSet.add(Integer.valueOf(39));
                    break;
                case C0151R.styleable.Theme_spinnerStyle /*40*/:
                    edVar11 = (ed) ac.m40a(parcel, b, ed.CREATOR);
                    hashSet.add(Integer.valueOf(40));
                    edVar8 = edVar11;
                    break;
                case C0151R.styleable.Theme_spinnerDropDownItemStyle /*41*/:
                    list6 = ac.m47c(parcel, b, ed.CREATOR);
                    hashSet.add(Integer.valueOf(41));
                    break;
                case C0151R.styleable.Theme_homeAsUpIndicator /*42*/:
                    str23 = ac.m58l(parcel, b);
                    hashSet.add(Integer.valueOf(42));
                    break;
                case C0151R.styleable.Theme_actionButtonStyle /*43*/:
                    str24 = ac.m58l(parcel, b);
                    hashSet.add(Integer.valueOf(43));
                    break;
                case C0151R.styleable.Theme_buttonBarStyle /*44*/:
                    str25 = ac.m58l(parcel, b);
                    hashSet.add(Integer.valueOf(44));
                    break;
                case C0151R.styleable.Theme_buttonBarButtonStyle /*45*/:
                    str26 = ac.m58l(parcel, b);
                    hashSet.add(Integer.valueOf(45));
                    break;
                case C0151R.styleable.Theme_selectableItemBackground /*46*/:
                    edVar11 = (ed) ac.m40a(parcel, b, ed.CREATOR);
                    hashSet.add(Integer.valueOf(46));
                    edVar9 = edVar11;
                    break;
                case C0151R.styleable.Theme_selectableItemBackgroundBorderless /*47*/:
                    str27 = ac.m58l(parcel, b);
                    hashSet.add(Integer.valueOf(47));
                    break;
                case C0151R.styleable.Theme_dividerVertical /*48*/:
                    str28 = ac.m58l(parcel, b);
                    hashSet.add(Integer.valueOf(48));
                    break;
                case C0151R.styleable.Theme_dividerHorizontal /*49*/:
                    str29 = ac.m58l(parcel, b);
                    hashSet.add(Integer.valueOf(49));
                    break;
                case C0151R.styleable.Theme_activityChooserViewStyle /*50*/:
                    edVar11 = (ed) ac.m40a(parcel, b, ed.CREATOR);
                    hashSet.add(Integer.valueOf(50));
                    edVar10 = edVar11;
                    break;
                case C0151R.styleable.Theme_toolbarStyle /*51*/:
                    str30 = ac.m58l(parcel, b);
                    hashSet.add(Integer.valueOf(51));
                    break;
                case C0151R.styleable.Theme_toolbarNavigationButtonStyle /*52*/:
                    str31 = ac.m58l(parcel, b);
                    hashSet.add(Integer.valueOf(52));
                    break;
                case C0151R.styleable.Theme_popupMenuStyle /*53*/:
                    str32 = ac.m58l(parcel, b);
                    hashSet.add(Integer.valueOf(53));
                    break;
                case C0151R.styleable.Theme_popupWindowStyle /*54*/:
                    str33 = ac.m58l(parcel, b);
                    hashSet.add(Integer.valueOf(54));
                    break;
                case C0151R.styleable.Theme_editTextColor /*55*/:
                    str34 = ac.m58l(parcel, b);
                    hashSet.add(Integer.valueOf(55));
                    break;
                case C0151R.styleable.Theme_editTextBackground /*56*/:
                    str35 = ac.m58l(parcel, b);
                    hashSet.add(Integer.valueOf(56));
                    break;
                default:
                    ac.m44b(parcel, b);
                    break;
            }
        }
        if (parcel.dataPosition() == c) {
            return new ed(hashSet, i, edVar, list, edVar2, str, str2, str3, list2, i2, list3, edVar3, list4, str4, str5, edVar4, str6, str7, str8, list5, str9, str10, str11, str12, str13, str14, str15, str16, str17, edVar5, str18, str19, str20, str21, edVar6, d, edVar7, d2, str22, edVar8, list6, str23, str24, str25, str26, edVar9, str27, str28, str29, edVar10, str30, str31, str32, str33, str34, str35);
        }
        throw new C0109a("Overread allowed size end=" + c, parcel);
    }
}
