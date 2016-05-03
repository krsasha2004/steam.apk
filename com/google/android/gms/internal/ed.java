package com.google.android.gms.internal;

import android.os.Parcel;
import com.google.android.gms.internal.an.C0112a;
import com.google.android.gms.plus.model.moments.ItemScope;
import com.valvesoftware.android.steam.community.C0151R;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public final class ed extends an implements ae, ItemScope {
    public static final ee CREATOR;
    private static final HashMap<String, C0112a<?, ?>> hY;
    private final int f25T;
    private String ck;
    private double eA;
    private double eB;
    private String hL;
    private final Set<Integer> hZ;
    private ed iA;
    private String iB;
    private String iC;
    private String iD;
    private String iE;
    private ed iF;
    private ed iG;
    private ed iH;
    private List<ed> iI;
    private String iJ;
    private String iK;
    private String iL;
    private String iM;
    private ed iN;
    private String iO;
    private String iP;
    private String iQ;
    private ed iR;
    private String iS;
    private String iT;
    private String iU;
    private String iV;
    private String iW;
    private ed ia;
    private List<String> ib;
    private ed ic;
    private String id;
    private String ie;
    private String f26if;
    private List<ed> ig;
    private int ih;
    private List<ed> ii;
    private ed ij;
    private List<ed> ik;
    private String il;
    private String im;
    private ed in;
    private String io;
    private String ip;
    private String iq;
    private List<ed> ir;
    private String is;
    private String it;
    private String iu;
    private String iv;
    private String iw;
    private String ix;
    private String iy;
    private String iz;
    private String mName;

    static {
        CREATOR = new ee();
        hY = new HashMap();
        hY.put("about", C0112a.m133a("about", 2, ed.class));
        hY.put("additionalName", C0112a.m140g("additionalName", 3));
        hY.put("address", C0112a.m133a("address", 4, ed.class));
        hY.put("addressCountry", C0112a.m139f("addressCountry", 5));
        hY.put("addressLocality", C0112a.m139f("addressLocality", 6));
        hY.put("addressRegion", C0112a.m139f("addressRegion", 7));
        hY.put("associated_media", C0112a.m134b("associated_media", 8, ed.class));
        hY.put("attendeeCount", C0112a.m135c("attendeeCount", 9));
        hY.put("attendees", C0112a.m134b("attendees", 10, ed.class));
        hY.put("audio", C0112a.m133a("audio", 11, ed.class));
        hY.put("author", C0112a.m134b("author", 12, ed.class));
        hY.put("bestRating", C0112a.m139f("bestRating", 13));
        hY.put("birthDate", C0112a.m139f("birthDate", 14));
        hY.put("byArtist", C0112a.m133a("byArtist", 15, ed.class));
        hY.put("caption", C0112a.m139f("caption", 16));
        hY.put("contentSize", C0112a.m139f("contentSize", 17));
        hY.put("contentUrl", C0112a.m139f("contentUrl", 18));
        hY.put("contributor", C0112a.m134b("contributor", 19, ed.class));
        hY.put("dateCreated", C0112a.m139f("dateCreated", 20));
        hY.put("dateModified", C0112a.m139f("dateModified", 21));
        hY.put("datePublished", C0112a.m139f("datePublished", 22));
        hY.put("description", C0112a.m139f("description", 23));
        hY.put("duration", C0112a.m139f("duration", 24));
        hY.put("embedUrl", C0112a.m139f("embedUrl", 25));
        hY.put("endDate", C0112a.m139f("endDate", 26));
        hY.put("familyName", C0112a.m139f("familyName", 27));
        hY.put("gender", C0112a.m139f("gender", 28));
        hY.put("geo", C0112a.m133a("geo", 29, ed.class));
        hY.put("givenName", C0112a.m139f("givenName", 30));
        hY.put("height", C0112a.m139f("height", 31));
        hY.put("id", C0112a.m139f("id", 32));
        hY.put("image", C0112a.m139f("image", 33));
        hY.put("inAlbum", C0112a.m133a("inAlbum", 34, ed.class));
        hY.put("latitude", C0112a.m137d("latitude", 36));
        hY.put("location", C0112a.m133a("location", 37, ed.class));
        hY.put("longitude", C0112a.m137d("longitude", 38));
        hY.put("name", C0112a.m139f("name", 39));
        hY.put("partOfTVSeries", C0112a.m133a("partOfTVSeries", 40, ed.class));
        hY.put("performers", C0112a.m134b("performers", 41, ed.class));
        hY.put("playerType", C0112a.m139f("playerType", 42));
        hY.put("postOfficeBoxNumber", C0112a.m139f("postOfficeBoxNumber", 43));
        hY.put("postalCode", C0112a.m139f("postalCode", 44));
        hY.put("ratingValue", C0112a.m139f("ratingValue", 45));
        hY.put("reviewRating", C0112a.m133a("reviewRating", 46, ed.class));
        hY.put("startDate", C0112a.m139f("startDate", 47));
        hY.put("streetAddress", C0112a.m139f("streetAddress", 48));
        hY.put("text", C0112a.m139f("text", 49));
        hY.put("thumbnail", C0112a.m133a("thumbnail", 50, ed.class));
        hY.put("thumbnailUrl", C0112a.m139f("thumbnailUrl", 51));
        hY.put("tickerSymbol", C0112a.m139f("tickerSymbol", 52));
        hY.put("type", C0112a.m139f("type", 53));
        hY.put("url", C0112a.m139f("url", 54));
        hY.put("width", C0112a.m139f("width", 55));
        hY.put("worstRating", C0112a.m139f("worstRating", 56));
    }

    public ed() {
        this.f25T = 1;
        this.hZ = new HashSet();
    }

    ed(Set<Integer> set, int i, ed edVar, List<String> list, ed edVar2, String str, String str2, String str3, List<ed> list2, int i2, List<ed> list3, ed edVar3, List<ed> list4, String str4, String str5, ed edVar4, String str6, String str7, String str8, List<ed> list5, String str9, String str10, String str11, String str12, String str13, String str14, String str15, String str16, String str17, ed edVar5, String str18, String str19, String str20, String str21, ed edVar6, double d, ed edVar7, double d2, String str22, ed edVar8, List<ed> list6, String str23, String str24, String str25, String str26, ed edVar9, String str27, String str28, String str29, ed edVar10, String str30, String str31, String str32, String str33, String str34, String str35) {
        this.hZ = set;
        this.f25T = i;
        this.ia = edVar;
        this.ib = list;
        this.ic = edVar2;
        this.id = str;
        this.ie = str2;
        this.f26if = str3;
        this.ig = list2;
        this.ih = i2;
        this.ii = list3;
        this.ij = edVar3;
        this.ik = list4;
        this.il = str4;
        this.im = str5;
        this.in = edVar4;
        this.io = str6;
        this.ip = str7;
        this.iq = str8;
        this.ir = list5;
        this.is = str9;
        this.it = str10;
        this.iu = str11;
        this.ck = str12;
        this.iv = str13;
        this.iw = str14;
        this.ix = str15;
        this.iy = str16;
        this.iz = str17;
        this.iA = edVar5;
        this.iB = str18;
        this.iC = str19;
        this.iD = str20;
        this.iE = str21;
        this.iF = edVar6;
        this.eA = d;
        this.iG = edVar7;
        this.eB = d2;
        this.mName = str22;
        this.iH = edVar8;
        this.iI = list6;
        this.iJ = str23;
        this.iK = str24;
        this.iL = str25;
        this.iM = str26;
        this.iN = edVar9;
        this.iO = str27;
        this.iP = str28;
        this.iQ = str29;
        this.iR = edVar10;
        this.iS = str30;
        this.iT = str31;
        this.iU = str32;
        this.hL = str33;
        this.iV = str34;
        this.iW = str35;
    }

    public HashMap<String, C0112a<?, ?>> m247G() {
        return hY;
    }

    protected boolean m248a(C0112a c0112a) {
        return this.hZ.contains(Integer.valueOf(c0112a.m146N()));
    }

    protected Object m249b(C0112a c0112a) {
        switch (c0112a.m146N()) {
            case C0151R.styleable.View_paddingEnd /*2*/:
                return this.ia;
            case C0151R.styleable.Toolbar_subtitle /*3*/:
                return this.ib;
            case C0151R.styleable.Toolbar_contentInsetStart /*4*/:
                return this.ic;
            case C0151R.styleable.Toolbar_contentInsetEnd /*5*/:
                return this.id;
            case C0151R.styleable.Toolbar_contentInsetLeft /*6*/:
                return this.ie;
            case C0151R.styleable.Toolbar_contentInsetRight /*7*/:
                return this.f26if;
            case C0151R.styleable.Toolbar_popupTheme /*8*/:
                return this.ig;
            case C0151R.styleable.Toolbar_titleTextAppearance /*9*/:
                return Integer.valueOf(this.ih);
            case C0151R.styleable.Toolbar_subtitleTextAppearance /*10*/:
                return this.ii;
            case C0151R.styleable.Toolbar_titleMargins /*11*/:
                return this.ij;
            case C0151R.styleable.Toolbar_titleMarginStart /*12*/:
                return this.ik;
            case C0151R.styleable.Toolbar_titleMarginEnd /*13*/:
                return this.il;
            case C0151R.styleable.Toolbar_titleMarginTop /*14*/:
                return this.im;
            case C0151R.styleable.Toolbar_titleMarginBottom /*15*/:
                return this.in;
            case C0151R.styleable.Toolbar_maxButtonHeight /*16*/:
                return this.io;
            case C0151R.styleable.Toolbar_theme /*17*/:
                return this.ip;
            case C0151R.styleable.Toolbar_collapseIcon /*18*/:
                return this.iq;
            case C0151R.styleable.Toolbar_collapseContentDescription /*19*/:
                return this.ir;
            case C0151R.styleable.Toolbar_navigationIcon /*20*/:
                return this.is;
            case C0151R.styleable.Toolbar_navigationContentDescription /*21*/:
                return this.it;
            case C0151R.styleable.Theme_actionMenuTextColor /*22*/:
                return this.iu;
            case C0151R.styleable.Theme_actionModeStyle /*23*/:
                return this.ck;
            case C0151R.styleable.Theme_actionModeCloseButtonStyle /*24*/:
                return this.iv;
            case C0151R.styleable.Theme_actionModeBackground /*25*/:
                return this.iw;
            case C0151R.styleable.Theme_actionModeSplitBackground /*26*/:
                return this.ix;
            case C0151R.styleable.Theme_actionModeCloseDrawable /*27*/:
                return this.iy;
            case C0151R.styleable.Theme_actionModeCutDrawable /*28*/:
                return this.iz;
            case C0151R.styleable.Theme_actionModeCopyDrawable /*29*/:
                return this.iA;
            case C0151R.styleable.Theme_actionModePasteDrawable /*30*/:
                return this.iB;
            case C0151R.styleable.Theme_actionModeSelectAllDrawable /*31*/:
                return this.iC;
            case C0151R.styleable.Theme_actionModeShareDrawable /*32*/:
                return this.iD;
            case C0151R.styleable.Theme_actionModeFindDrawable /*33*/:
                return this.iE;
            case C0151R.styleable.Theme_actionModeWebSearchDrawable /*34*/:
                return this.iF;
            case C0151R.styleable.Theme_textAppearanceLargePopupMenu /*36*/:
                return Double.valueOf(this.eA);
            case C0151R.styleable.Theme_textAppearanceSmallPopupMenu /*37*/:
                return this.iG;
            case C0151R.styleable.Theme_actionDropDownStyle /*38*/:
                return Double.valueOf(this.eB);
            case C0151R.styleable.Theme_dropdownListPreferredItemHeight /*39*/:
                return this.mName;
            case C0151R.styleable.Theme_spinnerStyle /*40*/:
                return this.iH;
            case C0151R.styleable.Theme_spinnerDropDownItemStyle /*41*/:
                return this.iI;
            case C0151R.styleable.Theme_homeAsUpIndicator /*42*/:
                return this.iJ;
            case C0151R.styleable.Theme_actionButtonStyle /*43*/:
                return this.iK;
            case C0151R.styleable.Theme_buttonBarStyle /*44*/:
                return this.iL;
            case C0151R.styleable.Theme_buttonBarButtonStyle /*45*/:
                return this.iM;
            case C0151R.styleable.Theme_selectableItemBackground /*46*/:
                return this.iN;
            case C0151R.styleable.Theme_selectableItemBackgroundBorderless /*47*/:
                return this.iO;
            case C0151R.styleable.Theme_dividerVertical /*48*/:
                return this.iP;
            case C0151R.styleable.Theme_dividerHorizontal /*49*/:
                return this.iQ;
            case C0151R.styleable.Theme_activityChooserViewStyle /*50*/:
                return this.iR;
            case C0151R.styleable.Theme_toolbarStyle /*51*/:
                return this.iS;
            case C0151R.styleable.Theme_toolbarNavigationButtonStyle /*52*/:
                return this.iT;
            case C0151R.styleable.Theme_popupMenuStyle /*53*/:
                return this.iU;
            case C0151R.styleable.Theme_popupWindowStyle /*54*/:
                return this.hL;
            case C0151R.styleable.Theme_editTextColor /*55*/:
                return this.iV;
            case C0151R.styleable.Theme_editTextBackground /*56*/:
                return this.iW;
            default:
                throw new IllegalStateException("Unknown safe parcelable id=" + c0112a.m146N());
        }
    }

    ed bA() {
        return this.ia;
    }

    ed bB() {
        return this.ic;
    }

    List<ed> bC() {
        return this.ig;
    }

    List<ed> bD() {
        return this.ii;
    }

    ed bE() {
        return this.ij;
    }

    List<ed> bF() {
        return this.ik;
    }

    ed bG() {
        return this.in;
    }

    List<ed> bH() {
        return this.ir;
    }

    ed bI() {
        return this.iA;
    }

    ed bJ() {
        return this.iF;
    }

    ed bK() {
        return this.iG;
    }

    ed bL() {
        return this.iH;
    }

    List<ed> bM() {
        return this.iI;
    }

    ed bN() {
        return this.iN;
    }

    ed bO() {
        return this.iR;
    }

    public ed bP() {
        return this;
    }

    Set<Integer> bz() {
        return this.hZ;
    }

    public int describeContents() {
        return 0;
    }

    public boolean equals(Object obj) {
        if (!(obj instanceof ed)) {
            return false;
        }
        if (this == obj) {
            return true;
        }
        ed edVar = (ed) obj;
        for (C0112a c0112a : hY.values()) {
            if (m248a(c0112a)) {
                if (!edVar.m248a(c0112a)) {
                    return false;
                }
                if (!m249b(c0112a).equals(edVar.m249b(c0112a))) {
                    return false;
                }
            } else if (edVar.m248a(c0112a)) {
                return false;
            }
        }
        return true;
    }

    public /* synthetic */ Object freeze() {
        return bP();
    }

    public List<String> getAdditionalName() {
        return this.ib;
    }

    public String getAddressCountry() {
        return this.id;
    }

    public String getAddressLocality() {
        return this.ie;
    }

    public String getAddressRegion() {
        return this.f26if;
    }

    public int getAttendeeCount() {
        return this.ih;
    }

    public String getBestRating() {
        return this.il;
    }

    public String getBirthDate() {
        return this.im;
    }

    public String getCaption() {
        return this.io;
    }

    public String getContentSize() {
        return this.ip;
    }

    public String getContentUrl() {
        return this.iq;
    }

    public String getDateCreated() {
        return this.is;
    }

    public String getDateModified() {
        return this.it;
    }

    public String getDatePublished() {
        return this.iu;
    }

    public String getDescription() {
        return this.ck;
    }

    public String getDuration() {
        return this.iv;
    }

    public String getEmbedUrl() {
        return this.iw;
    }

    public String getEndDate() {
        return this.ix;
    }

    public String getFamilyName() {
        return this.iy;
    }

    public String getGender() {
        return this.iz;
    }

    public String getGivenName() {
        return this.iB;
    }

    public String getHeight() {
        return this.iC;
    }

    public String getId() {
        return this.iD;
    }

    public String getImage() {
        return this.iE;
    }

    public double getLatitude() {
        return this.eA;
    }

    public double getLongitude() {
        return this.eB;
    }

    public String getName() {
        return this.mName;
    }

    public String getPlayerType() {
        return this.iJ;
    }

    public String getPostOfficeBoxNumber() {
        return this.iK;
    }

    public String getPostalCode() {
        return this.iL;
    }

    public String getRatingValue() {
        return this.iM;
    }

    public String getStartDate() {
        return this.iO;
    }

    public String getStreetAddress() {
        return this.iP;
    }

    public String getText() {
        return this.iQ;
    }

    public String getThumbnailUrl() {
        return this.iS;
    }

    public String getTickerSymbol() {
        return this.iT;
    }

    public String getType() {
        return this.iU;
    }

    public String getUrl() {
        return this.hL;
    }

    public String getWidth() {
        return this.iV;
    }

    public String getWorstRating() {
        return this.iW;
    }

    public int hashCode() {
        int i = 0;
        for (C0112a c0112a : hY.values()) {
            int hashCode;
            if (m248a(c0112a)) {
                hashCode = m249b(c0112a).hashCode() + (i + c0112a.m146N());
            } else {
                hashCode = i;
            }
            i = hashCode;
        }
        return i;
    }

    protected Object m250j(String str) {
        return null;
    }

    protected boolean m251k(String str) {
        return false;
    }

    int m252u() {
        return this.f25T;
    }

    public void writeToParcel(Parcel out, int flags) {
        ee.m253a(this, out, flags);
    }
}
