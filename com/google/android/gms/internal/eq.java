package com.google.android.gms.internal;

import android.os.Parcel;
import com.google.android.gms.internal.an.C0112a;
import com.google.android.gms.plus.model.people.Person;
import com.google.android.gms.plus.model.people.Person.AgeRange;
import com.google.android.gms.plus.model.people.Person.Cover;
import com.google.android.gms.plus.model.people.Person.Cover.CoverInfo;
import com.google.android.gms.plus.model.people.Person.Cover.CoverPhoto;
import com.google.android.gms.plus.model.people.Person.Emails;
import com.google.android.gms.plus.model.people.Person.Image;
import com.google.android.gms.plus.model.people.Person.Name;
import com.google.android.gms.plus.model.people.Person.Organizations;
import com.google.android.gms.plus.model.people.Person.PlacesLived;
import com.google.android.gms.plus.model.people.Person.Urls;
import com.valvesoftware.android.steam.community.C0151R;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public final class eq extends an implements ae, Person {
    public static final er CREATOR;
    private static final HashMap<String, C0112a<?, ?>> hY;
    private final int f38T;
    private String bp;
    private String hL;
    private final Set<Integer> hZ;
    private String iD;
    private String ja;
    private C0120a jb;
    private String jc;
    private String jd;
    private int je;
    private C0123b jf;
    private String jg;
    private List<C0124c> jh;
    private String ji;
    private int jj;
    private boolean jk;
    private C0125d jl;
    private boolean jm;
    private String jn;
    private C0126e jo;
    private String jp;
    private int jq;
    private List<C0127g> jr;
    private List<C0128h> js;
    private int jt;
    private int ju;
    private String jv;
    private List<C0129i> jw;
    private boolean jx;

    /* renamed from: com.google.android.gms.internal.eq.a */
    public static final class C0120a extends an implements ae, AgeRange {
        public static final ei CREATOR;
        private static final HashMap<String, C0112a<?, ?>> hY;
        private final int f28T;
        private final Set<Integer> hZ;
        private int jy;
        private int jz;

        static {
            CREATOR = new ei();
            hY = new HashMap();
            hY.put("max", C0112a.m135c("max", 2));
            hY.put("min", C0112a.m135c("min", 3));
        }

        public C0120a() {
            this.f28T = 1;
            this.hZ = new HashSet();
        }

        C0120a(Set<Integer> set, int i, int i2, int i3) {
            this.hZ = set;
            this.f28T = i;
            this.jy = i2;
            this.jz = i3;
        }

        public HashMap<String, C0112a<?, ?>> m289G() {
            return hY;
        }

        protected boolean m290a(C0112a c0112a) {
            return this.hZ.contains(Integer.valueOf(c0112a.m146N()));
        }

        protected Object m291b(C0112a c0112a) {
            switch (c0112a.m146N()) {
                case C0151R.styleable.View_paddingEnd /*2*/:
                    return Integer.valueOf(this.jy);
                case C0151R.styleable.Toolbar_subtitle /*3*/:
                    return Integer.valueOf(this.jz);
                default:
                    throw new IllegalStateException("Unknown safe parcelable id=" + c0112a.m146N());
            }
        }

        Set<Integer> bz() {
            return this.hZ;
        }

        public C0120a ce() {
            return this;
        }

        public int describeContents() {
            return 0;
        }

        public /* synthetic */ Object freeze() {
            return ce();
        }

        public int getMax() {
            return this.jy;
        }

        public int getMin() {
            return this.jz;
        }

        protected Object m292j(String str) {
            return null;
        }

        protected boolean m293k(String str) {
            return false;
        }

        int m294u() {
            return this.f28T;
        }

        public void writeToParcel(Parcel out, int flags) {
            ei.m265a(this, out, flags);
        }
    }

    /* renamed from: com.google.android.gms.internal.eq.b */
    public static final class C0123b extends an implements ae, Cover {
        public static final ej CREATOR;
        private static final HashMap<String, C0112a<?, ?>> hY;
        private final int f31T;
        private final Set<Integer> hZ;
        private C0121a jA;
        private C0122b jB;
        private int jC;

        /* renamed from: com.google.android.gms.internal.eq.b.a */
        public static final class C0121a extends an implements ae, CoverInfo {
            public static final ek CREATOR;
            private static final HashMap<String, C0112a<?, ?>> hY;
            private final int f29T;
            private final Set<Integer> hZ;
            private int jD;
            private int jE;

            static {
                CREATOR = new ek();
                hY = new HashMap();
                hY.put("leftImageOffset", C0112a.m135c("leftImageOffset", 2));
                hY.put("topImageOffset", C0112a.m135c("topImageOffset", 3));
            }

            public C0121a() {
                this.f29T = 1;
                this.hZ = new HashSet();
            }

            C0121a(Set<Integer> set, int i, int i2, int i3) {
                this.hZ = set;
                this.f29T = i;
                this.jD = i2;
                this.jE = i3;
            }

            public HashMap<String, C0112a<?, ?>> m295G() {
                return hY;
            }

            protected boolean m296a(C0112a c0112a) {
                return this.hZ.contains(Integer.valueOf(c0112a.m146N()));
            }

            protected Object m297b(C0112a c0112a) {
                switch (c0112a.m146N()) {
                    case C0151R.styleable.View_paddingEnd /*2*/:
                        return Integer.valueOf(this.jD);
                    case C0151R.styleable.Toolbar_subtitle /*3*/:
                        return Integer.valueOf(this.jE);
                    default:
                        throw new IllegalStateException("Unknown safe parcelable id=" + c0112a.m146N());
                }
            }

            Set<Integer> bz() {
                return this.hZ;
            }

            public C0121a ci() {
                return this;
            }

            public int describeContents() {
                return 0;
            }

            public boolean equals(Object obj) {
                if (!(obj instanceof C0121a)) {
                    return false;
                }
                if (this == obj) {
                    return true;
                }
                C0121a c0121a = (C0121a) obj;
                for (C0112a c0112a : hY.values()) {
                    if (m296a(c0112a)) {
                        if (!c0121a.m296a(c0112a)) {
                            return false;
                        }
                        if (!m297b(c0112a).equals(c0121a.m297b(c0112a))) {
                            return false;
                        }
                    } else if (c0121a.m296a(c0112a)) {
                        return false;
                    }
                }
                return true;
            }

            public /* synthetic */ Object freeze() {
                return ci();
            }

            public int getLeftImageOffset() {
                return this.jD;
            }

            public int getTopImageOffset() {
                return this.jE;
            }

            public int hashCode() {
                int i = 0;
                for (C0112a c0112a : hY.values()) {
                    int hashCode;
                    if (m296a(c0112a)) {
                        hashCode = m297b(c0112a).hashCode() + (i + c0112a.m146N());
                    } else {
                        hashCode = i;
                    }
                    i = hashCode;
                }
                return i;
            }

            protected Object m298j(String str) {
                return null;
            }

            protected boolean m299k(String str) {
                return false;
            }

            int m300u() {
                return this.f29T;
            }

            public void writeToParcel(Parcel out, int flags) {
                ek.m271a(this, out, flags);
            }
        }

        /* renamed from: com.google.android.gms.internal.eq.b.b */
        public static final class C0122b extends an implements ae, CoverPhoto {
            public static final el CREATOR;
            private static final HashMap<String, C0112a<?, ?>> hY;
            private final int f30T;
            private int gE;
            private int gF;
            private String hL;
            private final Set<Integer> hZ;

            static {
                CREATOR = new el();
                hY = new HashMap();
                hY.put("height", C0112a.m135c("height", 2));
                hY.put("url", C0112a.m139f("url", 3));
                hY.put("width", C0112a.m135c("width", 4));
            }

            public C0122b() {
                this.f30T = 1;
                this.hZ = new HashSet();
            }

            C0122b(Set<Integer> set, int i, int i2, String str, int i3) {
                this.hZ = set;
                this.f30T = i;
                this.gF = i2;
                this.hL = str;
                this.gE = i3;
            }

            public HashMap<String, C0112a<?, ?>> m301G() {
                return hY;
            }

            protected boolean m302a(C0112a c0112a) {
                return this.hZ.contains(Integer.valueOf(c0112a.m146N()));
            }

            protected Object m303b(C0112a c0112a) {
                switch (c0112a.m146N()) {
                    case C0151R.styleable.View_paddingEnd /*2*/:
                        return Integer.valueOf(this.gF);
                    case C0151R.styleable.Toolbar_subtitle /*3*/:
                        return this.hL;
                    case C0151R.styleable.Toolbar_contentInsetStart /*4*/:
                        return Integer.valueOf(this.gE);
                    default:
                        throw new IllegalStateException("Unknown safe parcelable id=" + c0112a.m146N());
                }
            }

            Set<Integer> bz() {
                return this.hZ;
            }

            public C0122b cj() {
                return this;
            }

            public int describeContents() {
                return 0;
            }

            public boolean equals(Object obj) {
                if (!(obj instanceof C0122b)) {
                    return false;
                }
                if (this == obj) {
                    return true;
                }
                C0122b c0122b = (C0122b) obj;
                for (C0112a c0112a : hY.values()) {
                    if (m302a(c0112a)) {
                        if (!c0122b.m302a(c0112a)) {
                            return false;
                        }
                        if (!m303b(c0112a).equals(c0122b.m303b(c0112a))) {
                            return false;
                        }
                    } else if (c0122b.m302a(c0112a)) {
                        return false;
                    }
                }
                return true;
            }

            public /* synthetic */ Object freeze() {
                return cj();
            }

            public int getHeight() {
                return this.gF;
            }

            public String getUrl() {
                return this.hL;
            }

            public int getWidth() {
                return this.gE;
            }

            public int hashCode() {
                int i = 0;
                for (C0112a c0112a : hY.values()) {
                    int hashCode;
                    if (m302a(c0112a)) {
                        hashCode = m303b(c0112a).hashCode() + (i + c0112a.m146N());
                    } else {
                        hashCode = i;
                    }
                    i = hashCode;
                }
                return i;
            }

            protected Object m304j(String str) {
                return null;
            }

            protected boolean m305k(String str) {
                return false;
            }

            int m306u() {
                return this.f30T;
            }

            public void writeToParcel(Parcel out, int flags) {
                el.m274a(this, out, flags);
            }
        }

        static {
            CREATOR = new ej();
            hY = new HashMap();
            hY.put("coverInfo", C0112a.m133a("coverInfo", 2, C0121a.class));
            hY.put("coverPhoto", C0112a.m133a("coverPhoto", 3, C0122b.class));
            hY.put("layout", C0112a.m132a("layout", 4, new ak().m123b("banner", 0), false));
        }

        public C0123b() {
            this.f31T = 1;
            this.hZ = new HashSet();
        }

        C0123b(Set<Integer> set, int i, C0121a c0121a, C0122b c0122b, int i2) {
            this.hZ = set;
            this.f31T = i;
            this.jA = c0121a;
            this.jB = c0122b;
            this.jC = i2;
        }

        public HashMap<String, C0112a<?, ?>> m307G() {
            return hY;
        }

        protected boolean m308a(C0112a c0112a) {
            return this.hZ.contains(Integer.valueOf(c0112a.m146N()));
        }

        protected Object m309b(C0112a c0112a) {
            switch (c0112a.m146N()) {
                case C0151R.styleable.View_paddingEnd /*2*/:
                    return this.jA;
                case C0151R.styleable.Toolbar_subtitle /*3*/:
                    return this.jB;
                case C0151R.styleable.Toolbar_contentInsetStart /*4*/:
                    return Integer.valueOf(this.jC);
                default:
                    throw new IllegalStateException("Unknown safe parcelable id=" + c0112a.m146N());
            }
        }

        Set<Integer> bz() {
            return this.hZ;
        }

        C0121a cf() {
            return this.jA;
        }

        C0122b cg() {
            return this.jB;
        }

        public C0123b ch() {
            return this;
        }

        public int describeContents() {
            return 0;
        }

        public boolean equals(Object obj) {
            if (!(obj instanceof C0123b)) {
                return false;
            }
            if (this == obj) {
                return true;
            }
            C0123b c0123b = (C0123b) obj;
            for (C0112a c0112a : hY.values()) {
                if (m308a(c0112a)) {
                    if (!c0123b.m308a(c0112a)) {
                        return false;
                    }
                    if (!m309b(c0112a).equals(c0123b.m309b(c0112a))) {
                        return false;
                    }
                } else if (c0123b.m308a(c0112a)) {
                    return false;
                }
            }
            return true;
        }

        public /* synthetic */ Object freeze() {
            return ch();
        }

        public int getLayout() {
            return this.jC;
        }

        public int hashCode() {
            int i = 0;
            for (C0112a c0112a : hY.values()) {
                int hashCode;
                if (m308a(c0112a)) {
                    hashCode = m309b(c0112a).hashCode() + (i + c0112a.m146N());
                } else {
                    hashCode = i;
                }
                i = hashCode;
            }
            return i;
        }

        protected Object m310j(String str) {
            return null;
        }

        protected boolean m311k(String str) {
            return false;
        }

        int m312u() {
            return this.f31T;
        }

        public void writeToParcel(Parcel out, int flags) {
            ej.m268a(this, out, flags);
        }
    }

    /* renamed from: com.google.android.gms.internal.eq.c */
    public static final class C0124c extends an implements ae, Emails {
        public static final em CREATOR;
        private static final HashMap<String, C0112a<?, ?>> hY;
        private final int f32T;
        private int bl;
        private final Set<Integer> hZ;
        private boolean jF;
        private String mValue;

        static {
            CREATOR = new em();
            hY = new HashMap();
            hY.put("primary", C0112a.m138e("primary", 2));
            hY.put("type", C0112a.m132a("type", 3, new ak().m123b("home", 0).m123b("work", 1).m123b("other", 2), false));
            hY.put("value", C0112a.m139f("value", 4));
        }

        public C0124c() {
            this.f32T = 1;
            this.hZ = new HashSet();
        }

        C0124c(Set<Integer> set, int i, boolean z, int i2, String str) {
            this.hZ = set;
            this.f32T = i;
            this.jF = z;
            this.bl = i2;
            this.mValue = str;
        }

        public HashMap<String, C0112a<?, ?>> m313G() {
            return hY;
        }

        protected boolean m314a(C0112a c0112a) {
            return this.hZ.contains(Integer.valueOf(c0112a.m146N()));
        }

        protected Object m315b(C0112a c0112a) {
            switch (c0112a.m146N()) {
                case C0151R.styleable.View_paddingEnd /*2*/:
                    return Boolean.valueOf(this.jF);
                case C0151R.styleable.Toolbar_subtitle /*3*/:
                    return Integer.valueOf(this.bl);
                case C0151R.styleable.Toolbar_contentInsetStart /*4*/:
                    return this.mValue;
                default:
                    throw new IllegalStateException("Unknown safe parcelable id=" + c0112a.m146N());
            }
        }

        Set<Integer> bz() {
            return this.hZ;
        }

        public C0124c ck() {
            return this;
        }

        public int describeContents() {
            return 0;
        }

        public boolean equals(Object obj) {
            if (!(obj instanceof C0124c)) {
                return false;
            }
            if (this == obj) {
                return true;
            }
            C0124c c0124c = (C0124c) obj;
            for (C0112a c0112a : hY.values()) {
                if (m314a(c0112a)) {
                    if (!c0124c.m314a(c0112a)) {
                        return false;
                    }
                    if (!m315b(c0112a).equals(c0124c.m315b(c0112a))) {
                        return false;
                    }
                } else if (c0124c.m314a(c0112a)) {
                    return false;
                }
            }
            return true;
        }

        public /* synthetic */ Object freeze() {
            return ck();
        }

        public int getType() {
            return this.bl;
        }

        public String getValue() {
            return this.mValue;
        }

        public int hashCode() {
            int i = 0;
            for (C0112a c0112a : hY.values()) {
                int hashCode;
                if (m314a(c0112a)) {
                    hashCode = m315b(c0112a).hashCode() + (i + c0112a.m146N());
                } else {
                    hashCode = i;
                }
                i = hashCode;
            }
            return i;
        }

        public boolean isPrimary() {
            return this.jF;
        }

        protected Object m316j(String str) {
            return null;
        }

        protected boolean m317k(String str) {
            return false;
        }

        int m318u() {
            return this.f32T;
        }

        public void writeToParcel(Parcel out, int flags) {
            em.m277a(this, out, flags);
        }
    }

    /* renamed from: com.google.android.gms.internal.eq.d */
    public static final class C0125d extends an implements ae, Image {
        public static final en CREATOR;
        private static final HashMap<String, C0112a<?, ?>> hY;
        private final int f33T;
        private String hL;
        private final Set<Integer> hZ;

        static {
            CREATOR = new en();
            hY = new HashMap();
            hY.put("url", C0112a.m139f("url", 2));
        }

        public C0125d() {
            this.f33T = 1;
            this.hZ = new HashSet();
        }

        C0125d(Set<Integer> set, int i, String str) {
            this.hZ = set;
            this.f33T = i;
            this.hL = str;
        }

        public HashMap<String, C0112a<?, ?>> m319G() {
            return hY;
        }

        protected boolean m320a(C0112a c0112a) {
            return this.hZ.contains(Integer.valueOf(c0112a.m146N()));
        }

        protected Object m321b(C0112a c0112a) {
            switch (c0112a.m146N()) {
                case C0151R.styleable.View_paddingEnd /*2*/:
                    return this.hL;
                default:
                    throw new IllegalStateException("Unknown safe parcelable id=" + c0112a.m146N());
            }
        }

        Set<Integer> bz() {
            return this.hZ;
        }

        public C0125d cl() {
            return this;
        }

        public int describeContents() {
            return 0;
        }

        public boolean equals(Object obj) {
            if (!(obj instanceof C0125d)) {
                return false;
            }
            if (this == obj) {
                return true;
            }
            C0125d c0125d = (C0125d) obj;
            for (C0112a c0112a : hY.values()) {
                if (m320a(c0112a)) {
                    if (!c0125d.m320a(c0112a)) {
                        return false;
                    }
                    if (!m321b(c0112a).equals(c0125d.m321b(c0112a))) {
                        return false;
                    }
                } else if (c0125d.m320a(c0112a)) {
                    return false;
                }
            }
            return true;
        }

        public /* synthetic */ Object freeze() {
            return cl();
        }

        public String getUrl() {
            return this.hL;
        }

        public int hashCode() {
            int i = 0;
            for (C0112a c0112a : hY.values()) {
                int hashCode;
                if (m320a(c0112a)) {
                    hashCode = m321b(c0112a).hashCode() + (i + c0112a.m146N());
                } else {
                    hashCode = i;
                }
                i = hashCode;
            }
            return i;
        }

        protected Object m322j(String str) {
            return null;
        }

        protected boolean m323k(String str) {
            return false;
        }

        int m324u() {
            return this.f33T;
        }

        public void writeToParcel(Parcel out, int flags) {
            en.m280a(this, out, flags);
        }
    }

    /* renamed from: com.google.android.gms.internal.eq.e */
    public static final class C0126e extends an implements ae, Name {
        public static final eo CREATOR;
        private static final HashMap<String, C0112a<?, ?>> hY;
        private final int f34T;
        private final Set<Integer> hZ;
        private String iB;
        private String iy;
        private String jG;
        private String jH;
        private String jI;
        private String jJ;

        static {
            CREATOR = new eo();
            hY = new HashMap();
            hY.put("familyName", C0112a.m139f("familyName", 2));
            hY.put("formatted", C0112a.m139f("formatted", 3));
            hY.put("givenName", C0112a.m139f("givenName", 4));
            hY.put("honorificPrefix", C0112a.m139f("honorificPrefix", 5));
            hY.put("honorificSuffix", C0112a.m139f("honorificSuffix", 6));
            hY.put("middleName", C0112a.m139f("middleName", 7));
        }

        public C0126e() {
            this.f34T = 1;
            this.hZ = new HashSet();
        }

        C0126e(Set<Integer> set, int i, String str, String str2, String str3, String str4, String str5, String str6) {
            this.hZ = set;
            this.f34T = i;
            this.iy = str;
            this.jG = str2;
            this.iB = str3;
            this.jH = str4;
            this.jI = str5;
            this.jJ = str6;
        }

        public HashMap<String, C0112a<?, ?>> m325G() {
            return hY;
        }

        protected boolean m326a(C0112a c0112a) {
            return this.hZ.contains(Integer.valueOf(c0112a.m146N()));
        }

        protected Object m327b(C0112a c0112a) {
            switch (c0112a.m146N()) {
                case C0151R.styleable.View_paddingEnd /*2*/:
                    return this.iy;
                case C0151R.styleable.Toolbar_subtitle /*3*/:
                    return this.jG;
                case C0151R.styleable.Toolbar_contentInsetStart /*4*/:
                    return this.iB;
                case C0151R.styleable.Toolbar_contentInsetEnd /*5*/:
                    return this.jH;
                case C0151R.styleable.Toolbar_contentInsetLeft /*6*/:
                    return this.jI;
                case C0151R.styleable.Toolbar_contentInsetRight /*7*/:
                    return this.jJ;
                default:
                    throw new IllegalStateException("Unknown safe parcelable id=" + c0112a.m146N());
            }
        }

        Set<Integer> bz() {
            return this.hZ;
        }

        public C0126e cm() {
            return this;
        }

        public int describeContents() {
            return 0;
        }

        public boolean equals(Object obj) {
            if (!(obj instanceof C0126e)) {
                return false;
            }
            if (this == obj) {
                return true;
            }
            C0126e c0126e = (C0126e) obj;
            for (C0112a c0112a : hY.values()) {
                if (m326a(c0112a)) {
                    if (!c0126e.m326a(c0112a)) {
                        return false;
                    }
                    if (!m327b(c0112a).equals(c0126e.m327b(c0112a))) {
                        return false;
                    }
                } else if (c0126e.m326a(c0112a)) {
                    return false;
                }
            }
            return true;
        }

        public /* synthetic */ Object freeze() {
            return cm();
        }

        public String getFamilyName() {
            return this.iy;
        }

        public String getFormatted() {
            return this.jG;
        }

        public String getGivenName() {
            return this.iB;
        }

        public String getHonorificPrefix() {
            return this.jH;
        }

        public String getHonorificSuffix() {
            return this.jI;
        }

        public String getMiddleName() {
            return this.jJ;
        }

        public int hashCode() {
            int i = 0;
            for (C0112a c0112a : hY.values()) {
                int hashCode;
                if (m326a(c0112a)) {
                    hashCode = m327b(c0112a).hashCode() + (i + c0112a.m146N());
                } else {
                    hashCode = i;
                }
                i = hashCode;
            }
            return i;
        }

        protected Object m328j(String str) {
            return null;
        }

        protected boolean m329k(String str) {
            return false;
        }

        int m330u() {
            return this.f34T;
        }

        public void writeToParcel(Parcel out, int flags) {
            eo.m283a(this, out, flags);
        }
    }

    /* renamed from: com.google.android.gms.internal.eq.g */
    public static final class C0127g extends an implements ae, Organizations {
        public static final ep CREATOR;
        private static final HashMap<String, C0112a<?, ?>> hY;
        private final int f35T;
        private int bl;
        private String ck;
        private String gp;
        private final Set<Integer> hZ;
        private String iO;
        private String ix;
        private boolean jF;
        private String jK;
        private String jL;
        private String mName;

        static {
            CREATOR = new ep();
            hY = new HashMap();
            hY.put("department", C0112a.m139f("department", 2));
            hY.put("description", C0112a.m139f("description", 3));
            hY.put("endDate", C0112a.m139f("endDate", 4));
            hY.put("location", C0112a.m139f("location", 5));
            hY.put("name", C0112a.m139f("name", 6));
            hY.put("primary", C0112a.m138e("primary", 7));
            hY.put("startDate", C0112a.m139f("startDate", 8));
            hY.put("title", C0112a.m139f("title", 9));
            hY.put("type", C0112a.m132a("type", 10, new ak().m123b("work", 0).m123b("school", 1), false));
        }

        public C0127g() {
            this.f35T = 1;
            this.hZ = new HashSet();
        }

        C0127g(Set<Integer> set, int i, String str, String str2, String str3, String str4, String str5, boolean z, String str6, String str7, int i2) {
            this.hZ = set;
            this.f35T = i;
            this.jK = str;
            this.ck = str2;
            this.ix = str3;
            this.jL = str4;
            this.mName = str5;
            this.jF = z;
            this.iO = str6;
            this.gp = str7;
            this.bl = i2;
        }

        public HashMap<String, C0112a<?, ?>> m331G() {
            return hY;
        }

        protected boolean m332a(C0112a c0112a) {
            return this.hZ.contains(Integer.valueOf(c0112a.m146N()));
        }

        protected Object m333b(C0112a c0112a) {
            switch (c0112a.m146N()) {
                case C0151R.styleable.View_paddingEnd /*2*/:
                    return this.jK;
                case C0151R.styleable.Toolbar_subtitle /*3*/:
                    return this.ck;
                case C0151R.styleable.Toolbar_contentInsetStart /*4*/:
                    return this.ix;
                case C0151R.styleable.Toolbar_contentInsetEnd /*5*/:
                    return this.jL;
                case C0151R.styleable.Toolbar_contentInsetLeft /*6*/:
                    return this.mName;
                case C0151R.styleable.Toolbar_contentInsetRight /*7*/:
                    return Boolean.valueOf(this.jF);
                case C0151R.styleable.Toolbar_popupTheme /*8*/:
                    return this.iO;
                case C0151R.styleable.Toolbar_titleTextAppearance /*9*/:
                    return this.gp;
                case C0151R.styleable.Toolbar_subtitleTextAppearance /*10*/:
                    return Integer.valueOf(this.bl);
                default:
                    throw new IllegalStateException("Unknown safe parcelable id=" + c0112a.m146N());
            }
        }

        Set<Integer> bz() {
            return this.hZ;
        }

        public C0127g cn() {
            return this;
        }

        public int describeContents() {
            return 0;
        }

        public boolean equals(Object obj) {
            if (!(obj instanceof C0127g)) {
                return false;
            }
            if (this == obj) {
                return true;
            }
            C0127g c0127g = (C0127g) obj;
            for (C0112a c0112a : hY.values()) {
                if (m332a(c0112a)) {
                    if (!c0127g.m332a(c0112a)) {
                        return false;
                    }
                    if (!m333b(c0112a).equals(c0127g.m333b(c0112a))) {
                        return false;
                    }
                } else if (c0127g.m332a(c0112a)) {
                    return false;
                }
            }
            return true;
        }

        public /* synthetic */ Object freeze() {
            return cn();
        }

        public String getDepartment() {
            return this.jK;
        }

        public String getDescription() {
            return this.ck;
        }

        public String getEndDate() {
            return this.ix;
        }

        public String getLocation() {
            return this.jL;
        }

        public String getName() {
            return this.mName;
        }

        public String getStartDate() {
            return this.iO;
        }

        public String getTitle() {
            return this.gp;
        }

        public int getType() {
            return this.bl;
        }

        public int hashCode() {
            int i = 0;
            for (C0112a c0112a : hY.values()) {
                int hashCode;
                if (m332a(c0112a)) {
                    hashCode = m333b(c0112a).hashCode() + (i + c0112a.m146N());
                } else {
                    hashCode = i;
                }
                i = hashCode;
            }
            return i;
        }

        public boolean isPrimary() {
            return this.jF;
        }

        protected Object m334j(String str) {
            return null;
        }

        protected boolean m335k(String str) {
            return false;
        }

        int m336u() {
            return this.f35T;
        }

        public void writeToParcel(Parcel out, int flags) {
            ep.m286a(this, out, flags);
        }
    }

    /* renamed from: com.google.android.gms.internal.eq.h */
    public static final class C0128h extends an implements ae, PlacesLived {
        public static final et CREATOR;
        private static final HashMap<String, C0112a<?, ?>> hY;
        private final int f36T;
        private final Set<Integer> hZ;
        private boolean jF;
        private String mValue;

        static {
            CREATOR = new et();
            hY = new HashMap();
            hY.put("primary", C0112a.m138e("primary", 2));
            hY.put("value", C0112a.m139f("value", 3));
        }

        public C0128h() {
            this.f36T = 1;
            this.hZ = new HashSet();
        }

        C0128h(Set<Integer> set, int i, boolean z, String str) {
            this.hZ = set;
            this.f36T = i;
            this.jF = z;
            this.mValue = str;
        }

        public HashMap<String, C0112a<?, ?>> m337G() {
            return hY;
        }

        protected boolean m338a(C0112a c0112a) {
            return this.hZ.contains(Integer.valueOf(c0112a.m146N()));
        }

        protected Object m339b(C0112a c0112a) {
            switch (c0112a.m146N()) {
                case C0151R.styleable.View_paddingEnd /*2*/:
                    return Boolean.valueOf(this.jF);
                case C0151R.styleable.Toolbar_subtitle /*3*/:
                    return this.mValue;
                default:
                    throw new IllegalStateException("Unknown safe parcelable id=" + c0112a.m146N());
            }
        }

        Set<Integer> bz() {
            return this.hZ;
        }

        public C0128h co() {
            return this;
        }

        public int describeContents() {
            return 0;
        }

        public boolean equals(Object obj) {
            if (!(obj instanceof C0128h)) {
                return false;
            }
            if (this == obj) {
                return true;
            }
            C0128h c0128h = (C0128h) obj;
            for (C0112a c0112a : hY.values()) {
                if (m338a(c0112a)) {
                    if (!c0128h.m338a(c0112a)) {
                        return false;
                    }
                    if (!m339b(c0112a).equals(c0128h.m339b(c0112a))) {
                        return false;
                    }
                } else if (c0128h.m338a(c0112a)) {
                    return false;
                }
            }
            return true;
        }

        public /* synthetic */ Object freeze() {
            return co();
        }

        public String getValue() {
            return this.mValue;
        }

        public int hashCode() {
            int i = 0;
            for (C0112a c0112a : hY.values()) {
                int hashCode;
                if (m338a(c0112a)) {
                    hashCode = m339b(c0112a).hashCode() + (i + c0112a.m146N());
                } else {
                    hashCode = i;
                }
                i = hashCode;
            }
            return i;
        }

        public boolean isPrimary() {
            return this.jF;
        }

        protected Object m340j(String str) {
            return null;
        }

        protected boolean m341k(String str) {
            return false;
        }

        int m342u() {
            return this.f36T;
        }

        public void writeToParcel(Parcel out, int flags) {
            et.m357a(this, out, flags);
        }
    }

    /* renamed from: com.google.android.gms.internal.eq.i */
    public static final class C0129i extends an implements ae, Urls {
        public static final eu CREATOR;
        private static final HashMap<String, C0112a<?, ?>> hY;
        private final int f37T;
        private int bl;
        private final Set<Integer> hZ;
        private boolean jF;
        private String mValue;

        static {
            CREATOR = new eu();
            hY = new HashMap();
            hY.put("primary", C0112a.m138e("primary", 2));
            hY.put("type", C0112a.m132a("type", 3, new ak().m123b("home", 0).m123b("work", 1).m123b("blog", 2).m123b("profile", 3).m123b("other", 4), false));
            hY.put("value", C0112a.m139f("value", 4));
        }

        public C0129i() {
            this.f37T = 1;
            this.hZ = new HashSet();
        }

        C0129i(Set<Integer> set, int i, boolean z, int i2, String str) {
            this.hZ = set;
            this.f37T = i;
            this.jF = z;
            this.bl = i2;
            this.mValue = str;
        }

        public HashMap<String, C0112a<?, ?>> m343G() {
            return hY;
        }

        protected boolean m344a(C0112a c0112a) {
            return this.hZ.contains(Integer.valueOf(c0112a.m146N()));
        }

        protected Object m345b(C0112a c0112a) {
            switch (c0112a.m146N()) {
                case C0151R.styleable.View_paddingEnd /*2*/:
                    return Boolean.valueOf(this.jF);
                case C0151R.styleable.Toolbar_subtitle /*3*/:
                    return Integer.valueOf(this.bl);
                case C0151R.styleable.Toolbar_contentInsetStart /*4*/:
                    return this.mValue;
                default:
                    throw new IllegalStateException("Unknown safe parcelable id=" + c0112a.m146N());
            }
        }

        Set<Integer> bz() {
            return this.hZ;
        }

        public C0129i cp() {
            return this;
        }

        public int describeContents() {
            return 0;
        }

        public boolean equals(Object obj) {
            if (!(obj instanceof C0129i)) {
                return false;
            }
            if (this == obj) {
                return true;
            }
            C0129i c0129i = (C0129i) obj;
            for (C0112a c0112a : hY.values()) {
                if (m344a(c0112a)) {
                    if (!c0129i.m344a(c0112a)) {
                        return false;
                    }
                    if (!m345b(c0112a).equals(c0129i.m345b(c0112a))) {
                        return false;
                    }
                } else if (c0129i.m344a(c0112a)) {
                    return false;
                }
            }
            return true;
        }

        public /* synthetic */ Object freeze() {
            return cp();
        }

        public int getType() {
            return this.bl;
        }

        public String getValue() {
            return this.mValue;
        }

        public int hashCode() {
            int i = 0;
            for (C0112a c0112a : hY.values()) {
                int hashCode;
                if (m344a(c0112a)) {
                    hashCode = m345b(c0112a).hashCode() + (i + c0112a.m146N());
                } else {
                    hashCode = i;
                }
                i = hashCode;
            }
            return i;
        }

        public boolean isPrimary() {
            return this.jF;
        }

        protected Object m346j(String str) {
            return null;
        }

        protected boolean m347k(String str) {
            return false;
        }

        int m348u() {
            return this.f37T;
        }

        public void writeToParcel(Parcel out, int flags) {
            eu.m359a(this, out, flags);
        }
    }

    static {
        CREATOR = new er();
        hY = new HashMap();
        hY.put("aboutMe", C0112a.m139f("aboutMe", 2));
        hY.put("ageRange", C0112a.m133a("ageRange", 3, C0120a.class));
        hY.put("birthday", C0112a.m139f("birthday", 4));
        hY.put("braggingRights", C0112a.m139f("braggingRights", 5));
        hY.put("circledByCount", C0112a.m135c("circledByCount", 6));
        hY.put("cover", C0112a.m133a("cover", 7, C0123b.class));
        hY.put("currentLocation", C0112a.m139f("currentLocation", 8));
        hY.put("displayName", C0112a.m139f("displayName", 9));
        hY.put("emails", C0112a.m134b("emails", 10, C0124c.class));
        hY.put("etag", C0112a.m139f("etag", 11));
        hY.put("gender", C0112a.m132a("gender", 12, new ak().m123b("male", 0).m123b("female", 1).m123b("other", 2), false));
        hY.put("hasApp", C0112a.m138e("hasApp", 13));
        hY.put("id", C0112a.m139f("id", 14));
        hY.put("image", C0112a.m133a("image", 15, C0125d.class));
        hY.put("isPlusUser", C0112a.m138e("isPlusUser", 16));
        hY.put("language", C0112a.m139f("language", 18));
        hY.put("name", C0112a.m133a("name", 19, C0126e.class));
        hY.put("nickname", C0112a.m139f("nickname", 20));
        hY.put("objectType", C0112a.m132a("objectType", 21, new ak().m123b("person", 0).m123b("page", 1), false));
        hY.put("organizations", C0112a.m134b("organizations", 22, C0127g.class));
        hY.put("placesLived", C0112a.m134b("placesLived", 23, C0128h.class));
        hY.put("plusOneCount", C0112a.m135c("plusOneCount", 24));
        hY.put("relationshipStatus", C0112a.m132a("relationshipStatus", 25, new ak().m123b("single", 0).m123b("in_a_relationship", 1).m123b("engaged", 2).m123b("married", 3).m123b("its_complicated", 4).m123b("open_relationship", 5).m123b("widowed", 6).m123b("in_domestic_partnership", 7).m123b("in_civil_union", 8), false));
        hY.put("tagline", C0112a.m139f("tagline", 26));
        hY.put("url", C0112a.m139f("url", 27));
        hY.put("urls", C0112a.m134b("urls", 28, C0129i.class));
        hY.put("verified", C0112a.m138e("verified", 29));
    }

    public eq() {
        this.f38T = 1;
        this.hZ = new HashSet();
    }

    eq(Set<Integer> set, int i, String str, C0120a c0120a, String str2, String str3, int i2, C0123b c0123b, String str4, String str5, List<C0124c> list, String str6, int i3, boolean z, String str7, C0125d c0125d, boolean z2, String str8, C0126e c0126e, String str9, int i4, List<C0127g> list2, List<C0128h> list3, int i5, int i6, String str10, String str11, List<C0129i> list4, boolean z3) {
        this.hZ = set;
        this.f38T = i;
        this.ja = str;
        this.jb = c0120a;
        this.jc = str2;
        this.jd = str3;
        this.je = i2;
        this.jf = c0123b;
        this.jg = str4;
        this.bp = str5;
        this.jh = list;
        this.ji = str6;
        this.jj = i3;
        this.jk = z;
        this.iD = str7;
        this.jl = c0125d;
        this.jm = z2;
        this.jn = str8;
        this.jo = c0126e;
        this.jp = str9;
        this.jq = i4;
        this.jr = list2;
        this.js = list3;
        this.jt = i5;
        this.ju = i6;
        this.jv = str10;
        this.hL = str11;
        this.jw = list4;
        this.jx = z3;
    }

    public HashMap<String, C0112a<?, ?>> m349G() {
        return hY;
    }

    protected boolean m350a(C0112a c0112a) {
        return this.hZ.contains(Integer.valueOf(c0112a.m146N()));
    }

    protected Object m351b(C0112a c0112a) {
        switch (c0112a.m146N()) {
            case C0151R.styleable.View_paddingEnd /*2*/:
                return this.ja;
            case C0151R.styleable.Toolbar_subtitle /*3*/:
                return this.jb;
            case C0151R.styleable.Toolbar_contentInsetStart /*4*/:
                return this.jc;
            case C0151R.styleable.Toolbar_contentInsetEnd /*5*/:
                return this.jd;
            case C0151R.styleable.Toolbar_contentInsetLeft /*6*/:
                return Integer.valueOf(this.je);
            case C0151R.styleable.Toolbar_contentInsetRight /*7*/:
                return this.jf;
            case C0151R.styleable.Toolbar_popupTheme /*8*/:
                return this.jg;
            case C0151R.styleable.Toolbar_titleTextAppearance /*9*/:
                return this.bp;
            case C0151R.styleable.Toolbar_subtitleTextAppearance /*10*/:
                return this.jh;
            case C0151R.styleable.Toolbar_titleMargins /*11*/:
                return this.ji;
            case C0151R.styleable.Toolbar_titleMarginStart /*12*/:
                return Integer.valueOf(this.jj);
            case C0151R.styleable.Toolbar_titleMarginEnd /*13*/:
                return Boolean.valueOf(this.jk);
            case C0151R.styleable.Toolbar_titleMarginTop /*14*/:
                return this.iD;
            case C0151R.styleable.Toolbar_titleMarginBottom /*15*/:
                return this.jl;
            case C0151R.styleable.Toolbar_maxButtonHeight /*16*/:
                return Boolean.valueOf(this.jm);
            case C0151R.styleable.Toolbar_collapseIcon /*18*/:
                return this.jn;
            case C0151R.styleable.Toolbar_collapseContentDescription /*19*/:
                return this.jo;
            case C0151R.styleable.Toolbar_navigationIcon /*20*/:
                return this.jp;
            case C0151R.styleable.Toolbar_navigationContentDescription /*21*/:
                return Integer.valueOf(this.jq);
            case C0151R.styleable.Theme_actionMenuTextColor /*22*/:
                return this.jr;
            case C0151R.styleable.Theme_actionModeStyle /*23*/:
                return this.js;
            case C0151R.styleable.Theme_actionModeCloseButtonStyle /*24*/:
                return Integer.valueOf(this.jt);
            case C0151R.styleable.Theme_actionModeBackground /*25*/:
                return Integer.valueOf(this.ju);
            case C0151R.styleable.Theme_actionModeSplitBackground /*26*/:
                return this.jv;
            case C0151R.styleable.Theme_actionModeCloseDrawable /*27*/:
                return this.hL;
            case C0151R.styleable.Theme_actionModeCutDrawable /*28*/:
                return this.jw;
            case C0151R.styleable.Theme_actionModeCopyDrawable /*29*/:
                return Boolean.valueOf(this.jx);
            default:
                throw new IllegalStateException("Unknown safe parcelable id=" + c0112a.m146N());
        }
    }

    C0120a bU() {
        return this.jb;
    }

    C0123b bV() {
        return this.jf;
    }

    List<C0124c> bW() {
        return this.jh;
    }

    public String bX() {
        return this.ji;
    }

    C0125d bY() {
        return this.jl;
    }

    C0126e bZ() {
        return this.jo;
    }

    Set<Integer> bz() {
        return this.hZ;
    }

    List<C0127g> ca() {
        return this.jr;
    }

    List<C0128h> cb() {
        return this.js;
    }

    List<C0129i> cc() {
        return this.jw;
    }

    public eq cd() {
        return this;
    }

    public int describeContents() {
        return 0;
    }

    public boolean equals(Object obj) {
        if (!(obj instanceof eq)) {
            return false;
        }
        if (this == obj) {
            return true;
        }
        eq eqVar = (eq) obj;
        for (C0112a c0112a : hY.values()) {
            if (m350a(c0112a)) {
                if (!eqVar.m350a(c0112a)) {
                    return false;
                }
                if (!m351b(c0112a).equals(eqVar.m351b(c0112a))) {
                    return false;
                }
            } else if (eqVar.m350a(c0112a)) {
                return false;
            }
        }
        return true;
    }

    public /* synthetic */ Object freeze() {
        return cd();
    }

    public String getAboutMe() {
        return this.ja;
    }

    public String getBirthday() {
        return this.jc;
    }

    public String getBraggingRights() {
        return this.jd;
    }

    public int getCircledByCount() {
        return this.je;
    }

    public String getCurrentLocation() {
        return this.jg;
    }

    public String getDisplayName() {
        return this.bp;
    }

    public int getGender() {
        return this.jj;
    }

    public String getId() {
        return this.iD;
    }

    public String getLanguage() {
        return this.jn;
    }

    public String getNickname() {
        return this.jp;
    }

    public int getObjectType() {
        return this.jq;
    }

    public int getPlusOneCount() {
        return this.jt;
    }

    public int getRelationshipStatus() {
        return this.ju;
    }

    public String getTagline() {
        return this.jv;
    }

    public String getUrl() {
        return this.hL;
    }

    public int hashCode() {
        int i = 0;
        for (C0112a c0112a : hY.values()) {
            int hashCode;
            if (m350a(c0112a)) {
                hashCode = m351b(c0112a).hashCode() + (i + c0112a.m146N());
            } else {
                hashCode = i;
            }
            i = hashCode;
        }
        return i;
    }

    public boolean isHasApp() {
        return this.jk;
    }

    public boolean isPlusUser() {
        return this.jm;
    }

    public boolean isVerified() {
        return this.jx;
    }

    protected Object m352j(String str) {
        return null;
    }

    protected boolean m353k(String str) {
        return false;
    }

    int m354u() {
        return this.f38T;
    }

    public void writeToParcel(Parcel out, int flags) {
        er.m355a(this, out, flags);
    }
}
