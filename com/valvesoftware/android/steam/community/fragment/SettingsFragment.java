package com.valvesoftware.android.steam.community.fragment;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.AlertDialog.Builder;
import android.app.DatePickerDialog.OnDateSetListener;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Bitmap;
import android.media.RingtoneManager;
import android.net.Uri;
import android.os.Build.VERSION;
import android.os.Bundle;
import android.support.v4.app.ListFragment;
import android.view.ContextThemeWrapper;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.CompoundButton.OnCheckedChangeListener;
import android.widget.DatePicker;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import com.valvesoftware.android.steam.community.C0151R;
import com.valvesoftware.android.steam.community.LoggedInUserAccountInfo;
import com.valvesoftware.android.steam.community.PersonaRepository;
import com.valvesoftware.android.steam.community.RepositoryCallback;
import com.valvesoftware.android.steam.community.SettingInfo;
import com.valvesoftware.android.steam.community.SettingInfo.AccessRight;
import com.valvesoftware.android.steam.community.SettingInfo.CustomDatePickerDialog;
import com.valvesoftware.android.steam.community.SettingInfo.DateConverter;
import com.valvesoftware.android.steam.community.SettingInfo.RadioSelectorItem;
import com.valvesoftware.android.steam.community.SettingInfo.SettingType;
import com.valvesoftware.android.steam.community.SettingInfoDB;
import com.valvesoftware.android.steam.community.SteamAppIntents;
import com.valvesoftware.android.steam.community.SteamCommunityApplication;
import com.valvesoftware.android.steam.community.activity.ActivityHelper;
import com.valvesoftware.android.steam.community.model.Persona;
import com.valvesoftware.android.steam.community.webrequests.Endpoints;
import com.valvesoftware.android.steam.community.webrequests.ImageRequestBuilder;
import com.valvesoftware.android.steam.community.webrequests.ImageResponseListener;
import java.util.ArrayList;
import java.util.Calendar;

public class SettingsFragment extends ListFragment {
    private SettingsListAdapter m_SettingsAdapter;
    private boolean m_bLoggedOnPresentation;
    private ListView m_listView;
    private Activity m_owner;
    private ArrayList<SettingInfo> m_settingsInfoArray;
    private View m_viewProfile;

    /* renamed from: com.valvesoftware.android.steam.community.fragment.SettingsFragment.1 */
    class C02491 implements OnClickListener {
        C02491() {
        }

        public void onClick(View v) {
            SettingsFragment.this.getActivity().startActivity(SteamAppIntents.visitProfileIntent(SettingsFragment.this.getActivity(), LoggedInUserAccountInfo.getLoginSteamID()));
        }
    }

    /* renamed from: com.valvesoftware.android.steam.community.fragment.SettingsFragment.2 */
    class C02502 implements RepositoryCallback<Persona> {
        final /* synthetic */ ImageView val$avatarView;
        final /* synthetic */ TextView val$nameView;

        C02502(TextView textView, ImageView imageView) {
            this.val$nameView = textView;
            this.val$avatarView = imageView;
        }

        public void dataAvailable(Persona persona) {
            if (persona != null) {
                this.val$nameView.setText(persona.personaName);
                SettingsFragment.this.getAvatar(persona, this.val$avatarView);
            }
        }

        public void end() {
        }
    }

    /* renamed from: com.valvesoftware.android.steam.community.fragment.SettingsFragment.3 */
    class C02513 extends ImageResponseListener {
        final /* synthetic */ ImageView val$avatarView;

        C02513(ImageView imageView) {
            this.val$avatarView = imageView;
        }

        public void onSuccess(Bitmap bitmap) {
            this.val$avatarView.setImageBitmap(bitmap);
        }
    }

    /* renamed from: com.valvesoftware.android.steam.community.fragment.SettingsFragment.4 */
    static /* synthetic */ class C02524 {
        static final /* synthetic */ int[] f65x7bb100de;
        static final /* synthetic */ int[] f66xb2ad8f70;

        static {
            f66xb2ad8f70 = new int[SettingType.values().length];
            try {
                f66xb2ad8f70[SettingType.INFO.ordinal()] = 1;
            } catch (NoSuchFieldError e) {
            }
            try {
                f66xb2ad8f70[SettingType.CHECK.ordinal()] = 2;
            } catch (NoSuchFieldError e2) {
            }
            try {
                f66xb2ad8f70[SettingType.DATE.ordinal()] = 3;
            } catch (NoSuchFieldError e3) {
            }
            try {
                f66xb2ad8f70[SettingType.URI.ordinal()] = 4;
            } catch (NoSuchFieldError e4) {
            }
            try {
                f66xb2ad8f70[SettingType.MARKET.ordinal()] = 5;
            } catch (NoSuchFieldError e5) {
            }
            try {
                f66xb2ad8f70[SettingType.RADIOSELECTOR.ordinal()] = 6;
            } catch (NoSuchFieldError e6) {
            }
            try {
                f66xb2ad8f70[SettingType.RINGTONESELECTOR.ordinal()] = 7;
            } catch (NoSuchFieldError e7) {
            }
            try {
                f66xb2ad8f70[SettingType.UNREADMSG.ordinal()] = 8;
            } catch (NoSuchFieldError e8) {
            }
            try {
                f66xb2ad8f70[SettingType.SECTION.ordinal()] = 9;
            } catch (NoSuchFieldError e9) {
            }
            f65x7bb100de = new int[AccessRight.values().length];
            try {
                f65x7bb100de[AccessRight.USER.ordinal()] = 1;
            } catch (NoSuchFieldError e10) {
            }
            try {
                f65x7bb100de[AccessRight.VALID_ACCOUNT.ordinal()] = 2;
            } catch (NoSuchFieldError e11) {
            }
            try {
                f65x7bb100de[AccessRight.CODE.ordinal()] = 3;
            } catch (NoSuchFieldError e12) {
            }
        }
    }

    public static class RadioSelectorItemOnClickListener implements OnClickListener {
        Activity activity;
        AlertDialog alert;
        DialogInterface.OnClickListener m_onRadioButtonSelected;
        SettingInfo settingInfo;
        TextView valueView;

        /* renamed from: com.valvesoftware.android.steam.community.fragment.SettingsFragment.RadioSelectorItemOnClickListener.1 */
        class C02531 implements DialogInterface.OnClickListener {
            C02531() {
            }

            public void onClick(DialogInterface dialog, int item) {
                RadioSelectorItem[] radios = (RadioSelectorItem[]) RadioSelectorItemOnClickListener.this.settingInfo.m_extraData;
                if (item >= 0 && item < radios.length) {
                    if (RadioSelectorItemOnClickListener.this.valueView != null) {
                        RadioSelectorItemOnClickListener.this.valueView.setText(radios[item].resid_text);
                    }
                    RadioSelectorItemOnClickListener.this.settingInfo.setValueAndCommit(RadioSelectorItemOnClickListener.this.activity.getApplicationContext(), String.valueOf(radios[item].value));
                    RadioSelectorItemOnClickListener.this.alert.dismiss();
                    RadioSelectorItemOnClickListener.this.onSettingChanged(radios[item]);
                }
            }
        }

        public RadioSelectorItemOnClickListener(Activity act, SettingInfo si, TextView value) {
            this.m_onRadioButtonSelected = new C02531();
            this.activity = act;
            this.settingInfo = si;
            this.valueView = value;
        }

        public void onClick(View btn) {
            Builder builder = new Builder(this.activity);
            builder.setTitle(this.settingInfo.m_resid);
            RadioSelectorItem[] radios = (RadioSelectorItem[]) this.settingInfo.m_extraData;
            CharSequence[] builderItems = new CharSequence[radios.length];
            RadioSelectorItem rsiValue = this.settingInfo.getRadioSelectorItemValue(this.activity.getApplicationContext());
            int iCheckedItem = -1;
            for (int j = 0; j < radios.length; j++) {
                builderItems[j] = this.activity.getString(radios[j].resid_text);
                if (rsiValue == radios[j]) {
                    iCheckedItem = j;
                }
            }
            builder.setSingleChoiceItems(builderItems, iCheckedItem, this.m_onRadioButtonSelected);
            this.alert = builder.create();
            this.alert.show();
        }

        public void onSettingChanged(RadioSelectorItem sel) {
        }
    }

    private class SettingsListAdapter extends ArrayAdapter<SettingInfo> {
        private ArrayList<SettingInfo> items;

        /* renamed from: com.valvesoftware.android.steam.community.fragment.SettingsFragment.SettingsListAdapter.1 */
        class C02541 implements OnClickListener {
            final /* synthetic */ ImageView val$chevronView;
            final /* synthetic */ SettingInfo val$settingInfo;
            final /* synthetic */ TextView val$valueView;

            C02541(TextView textView, SettingInfo settingInfo, ImageView imageView) {
                this.val$valueView = textView;
                this.val$settingInfo = settingInfo;
                this.val$chevronView = imageView;
            }

            public void onClick(View btn) {
                try {
                    SettingsFragment.this.getActivity().startActivity(new Intent("android.intent.action.VIEW").setData(Uri.parse("market://details?id=com.valvesoftware.android.steam.community")));
                } catch (Exception e) {
                    this.val$valueView.setText(this.val$settingInfo.m_defaultValue + " / " + SettingsFragment.this.getActivity().getString(C0151R.string.Market_Unavailable));
                    try {
                        SettingsFragment.this.getActivity().startActivity(new Intent("android.intent.action.VIEW").setData(Uri.parse("http://store.steampowered.com/mobile")));
                    } catch (Exception e2) {
                        this.val$chevronView.setVisibility(8);
                    }
                }
            }
        }

        /* renamed from: com.valvesoftware.android.steam.community.fragment.SettingsFragment.SettingsListAdapter.2 */
        class C02552 implements OnClickListener {
            C02552() {
            }

            public void onClick(View v) {
                SettingsFragment.this.refreshListView();
            }
        }

        /* renamed from: com.valvesoftware.android.steam.community.fragment.SettingsFragment.SettingsListAdapter.3 */
        class C02563 implements OnCheckedChangeListener {
            final /* synthetic */ SettingInfo val$settingInfo;

            C02563(SettingInfo settingInfo) {
                this.val$settingInfo = settingInfo;
            }

            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                this.val$settingInfo.setValueAndCommit(SettingsFragment.this.m_owner.getApplicationContext(), isChecked ? "1" : "");
            }
        }

        /* renamed from: com.valvesoftware.android.steam.community.fragment.SettingsFragment.SettingsListAdapter.4 */
        class C02574 implements OnClickListener {
            final /* synthetic */ CheckBox val$checkBox;

            C02574(CheckBox checkBox) {
                this.val$checkBox = checkBox;
            }

            public void onClick(View v) {
                this.val$checkBox.setChecked(!this.val$checkBox.isChecked());
            }
        }

        /* renamed from: com.valvesoftware.android.steam.community.fragment.SettingsFragment.SettingsListAdapter.5 */
        class C02595 implements OnClickListener {
            final /* synthetic */ Calendar val$myDOB;
            final /* synthetic */ SettingInfo val$settingInfo;
            final /* synthetic */ TextView val$valueView;

            /* renamed from: com.valvesoftware.android.steam.community.fragment.SettingsFragment.SettingsListAdapter.5.1 */
            class C02581 implements OnDateSetListener {
                C02581() {
                }

                public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
                    C02595.this.val$myDOB.set(year, monthOfYear, dayOfMonth);
                    String sValue = DateConverter.makeValue(year, monthOfYear, dayOfMonth);
                    if (!(sValue == null || sValue.equals(""))) {
                        C02595.this.val$valueView.setText(DateConverter.formatDate(sValue));
                    }
                    C02595.this.val$settingInfo.setValueAndCommit(SettingsFragment.this.m_owner.getApplicationContext(), sValue);
                }
            }

            C02595(Calendar calendar, TextView textView, SettingInfo settingInfo) {
                this.val$myDOB = calendar;
                this.val$valueView = textView;
                this.val$settingInfo = settingInfo;
            }

            public void onClick(View btn) {
                Context context = SettingsFragment.this.m_owner;
                if (VERSION.SDK_INT > 10) {
                    context = new ContextThemeWrapper(context, 16973939);
                }
                new CustomDatePickerDialog(context, new C02581(), this.val$myDOB, C0151R.string.settings_personal_dob_instr).show();
            }
        }

        /* renamed from: com.valvesoftware.android.steam.community.fragment.SettingsFragment.SettingsListAdapter.6 */
        class C02606 implements OnClickListener {
            final /* synthetic */ SettingInfo val$settingInfo;

            C02606(SettingInfo settingInfo) {
                this.val$settingInfo = settingInfo;
            }

            public void onClick(View btn) {
                SettingsFragment.this.startActivity(SteamAppIntents.mainActivityIntent(SettingsFragment.this.getActivity(), Uri.parse(this.val$settingInfo.m_defaultValue)));
            }
        }

        /* renamed from: com.valvesoftware.android.steam.community.fragment.SettingsFragment.SettingsListAdapter.7 */
        class C02617 implements OnClickListener {
            final /* synthetic */ SettingInfo val$settingInfo;

            C02617(SettingInfo settingInfo) {
                this.val$settingInfo = settingInfo;
            }

            public void onClick(View v) {
                try {
                    Intent intent = new Intent("android.intent.action.RINGTONE_PICKER");
                    intent.putExtra("android.intent.extra.ringtone.TYPE", 2);
                    intent.putExtra("android.intent.extra.ringtone.TITLE", SettingsFragment.this.getActivity().getString(this.val$settingInfo.m_resid));
                    try {
                        intent.putExtra("android.intent.extra.ringtone.EXISTING_URI", Uri.parse(this.val$settingInfo.getValue(SettingsFragment.this.m_owner.getApplicationContext())));
                    } catch (Exception e) {
                        intent.putExtra("android.intent.extra.ringtone.EXISTING_URI", (Uri) null);
                    }
                    intent.putExtra("android.intent.extra.ringtone.DEFAULT_URI", Uri.parse(this.val$settingInfo.m_defaultValue));
                    intent.putExtra("android.intent.extra.ringtone.SHOW_DEFAULT", true);
                    intent.putExtra("android.intent.extra.ringtone.SHOW_SILENT", false);
                    SettingsFragment.this.getActivity().startActivityForResult(intent, SettingInfoDB.ringToneSelectorRequestCode);
                } catch (Exception e2) {
                }
            }
        }

        public SettingsListAdapter(Context context, int textViewResourceId, ArrayList<SettingInfo> items) {
            super(context, textViewResourceId, items);
            this.items = items;
        }

        public View getView(int position, View convertView, ViewGroup parent) {
            View v = convertView;
            SettingInfo settingInfo = (SettingInfo) this.items.get(position);
            if (settingInfo == null) {
                return v;
            }
            if (v == null) {
                v = ((LayoutInflater) SettingsFragment.this.m_owner.getSystemService("layout_inflater")).inflate(C0151R.layout.settings_list_item_info, null);
                v.setClickable(true);
            }
            v.setOnClickListener(null);
            TextView titleView = (TextView) v.findViewById(C0151R.id.label);
            titleView.setText(settingInfo.m_resid);
            TextView valueView = (TextView) v.findViewById(C0151R.id.info);
            valueView.setText("");
            if (settingInfo.m_resid_detailed != 0) {
                valueView.setText(settingInfo.m_resid_detailed);
            }
            ImageView chevronView = (ImageView) v.findViewById(C0151R.id.imageChevron);
            chevronView.setVisibility(8);
            CheckBox checkBox = (CheckBox) v.findViewById(C0151R.id.setting_checkbox);
            checkBox.setVisibility(8);
            SettingsFragment settingsFragment;
            switch (C02524.f66xb2ad8f70[settingInfo.m_type.ordinal()]) {
                case C0151R.styleable.View_paddingStart /*1*/:
                    valueView.setText(settingInfo.m_defaultValue);
                    break;
                case C0151R.styleable.View_paddingEnd /*2*/:
                    boolean bValue = settingInfo.getBooleanValue(SettingsFragment.this.m_owner.getApplicationContext());
                    checkBox.setVisibility(0);
                    checkBox.setChecked(bValue);
                    checkBox.setOnCheckedChangeListener(new C02563(settingInfo));
                    v.setOnClickListener(new C02574(checkBox));
                    break;
                case C0151R.styleable.Toolbar_subtitle /*3*/:
                    chevronView.setVisibility(0);
                    String sValue = settingInfo.getValue(SettingsFragment.this.m_owner.getApplicationContext());
                    Calendar myDOB = DateConverter.makeCalendar(sValue);
                    if (sValue != null) {
                        if (!sValue.equals("")) {
                            valueView.setText(DateConverter.formatDate(sValue));
                            v.setOnClickListener(new C02595(myDOB, valueView, settingInfo));
                            break;
                        }
                    }
                    valueView.setText(C0151R.string.date_not_set);
                    v.setOnClickListener(new C02595(myDOB, valueView, settingInfo));
                case C0151R.styleable.Toolbar_contentInsetStart /*4*/:
                    chevronView.setVisibility(0);
                    v.setOnClickListener(new C02606(settingInfo));
                    break;
                case C0151R.styleable.Toolbar_contentInsetEnd /*5*/:
                    chevronView.setVisibility(0);
                    valueView.setText(settingInfo.m_defaultValue);
                    v.setOnClickListener(new C02541(valueView, settingInfo, chevronView));
                    break;
                case C0151R.styleable.Toolbar_contentInsetLeft /*6*/:
                    chevronView.setVisibility(0);
                    valueView.setText(settingInfo.getRadioSelectorItemValue(SettingsFragment.this.m_owner.getApplicationContext()).resid_text);
                    v.setOnClickListener(new RadioSelectorItemOnClickListener(SettingsFragment.this.getActivity(), settingInfo, valueView));
                    break;
                case C0151R.styleable.Toolbar_contentInsetRight /*7*/:
                    chevronView.setVisibility(0);
                    try {
                        String curValue = settingInfo.getValue(SettingsFragment.this.m_owner.getApplicationContext());
                        if (curValue != null) {
                            if (settingInfo.m_defaultValue.equals(curValue)) {
                                valueView.setText(C0151R.string.settings_notifications_ring_steam);
                                v.setOnClickListener(new C02617(settingInfo));
                                break;
                            }
                        }
                        settingsFragment = SettingsFragment.this;
                        valueView.setText(RingtoneManager.getRingtone(r0.getActivity(), Uri.parse(curValue)).getTitle(SettingsFragment.this.getActivity()));
                    } catch (Exception e) {
                        valueView.setText(C0151R.string.settings_notifications_ring_default);
                    }
                    v.setOnClickListener(new C02617(settingInfo));
                case C0151R.styleable.Toolbar_popupTheme /*8*/:
                    chevronView.setVisibility(0);
                    int numUnreadMsgs = 0;
                    if (LoggedInUserAccountInfo.isLoggedIn()) {
                        numUnreadMsgs = 0;
                    }
                    settingsFragment = SettingsFragment.this;
                    titleView.setText(r0.getActivity().getString(settingInfo.m_resid).replace("#", String.valueOf(numUnreadMsgs)));
                    v.setOnClickListener(new C02552());
                    break;
            }
            return v;
        }
    }

    public SettingsFragment() {
        this.m_owner = null;
        this.m_settingsInfoArray = new ArrayList();
        this.m_listView = null;
        this.m_SettingsAdapter = null;
        this.m_viewProfile = null;
        this.m_bLoggedOnPresentation = false;
    }

    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(C0151R.layout.settings_fragment, container, false);
    }

    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        this.m_owner = getActivity();
        if (this.m_SettingsAdapter == null) {
            this.m_SettingsAdapter = new SettingsListAdapter(this.m_owner, C0151R.layout.settings_list_item_info, this.m_settingsInfoArray);
        }
        if (this.m_listView == null) {
            this.m_listView = getListView();
        }
        setListAdapter(this.m_SettingsAdapter);
        if (this.m_owner != null && ActivityHelper.fragmentIsActive(this)) {
            this.m_owner.setTitle(C0151R.string.Settings);
        }
    }

    public void onResume() {
        super.onResume();
        if (getActivity() != null) {
            refreshListView();
        }
    }

    private void setupUserAccountView(View v) {
        v = getView();
        if (v != null) {
            TextView nameView = (TextView) v.findViewById(C0151R.id.name);
            ImageView avatarView = (ImageView) v.findViewById(C0151R.id.avatar);
            ImageView avatarViewFrame = (ImageView) v.findViewById(C0151R.id.avatar_frame);
            View avatarNameContainer = v.findViewById(C0151R.id.avatar_name_container);
            avatarView.setImageResource(C0151R.drawable.placeholder_contact);
            avatarNameContainer.setOnClickListener(new C02491());
            PersonaRepository.getDetailedPersonaInfo(LoggedInUserAccountInfo.getLoginSteamID(), new C02502(nameView, avatarView));
            avatarViewFrame.setImageResource(null != null ? C0151R.drawable.avatar_frame_online : C0151R.drawable.avatar_frame_offline);
            nameView.setTextColor(getActivity().getResources().getColor(null != null ? C0151R.color.online : C0151R.color.offline));
        }
    }

    private void getAvatar(Persona loggedInUser, ImageView avatarView) {
        ImageRequestBuilder requestBuilder = Endpoints.getImageUrlRequestBuilder(loggedInUser.fullAvatarUrl);
        requestBuilder.setResponseListener(new C02513(avatarView));
        SteamCommunityApplication.GetInstance().sendRequest(requestBuilder);
    }

    public void refreshListView() {
        this.m_bLoggedOnPresentation = LoggedInUserAccountInfo.isLoggedIn();
        this.m_settingsInfoArray.clear();
        setupUserAccountView(this.m_viewProfile);
        SettingInfoDB settingInfoDb = SteamCommunityApplication.GetInstance().GetSettingInfoDB();
        this.m_settingsInfoArray.addAll(settingInfoDb.GetSettingsList());
        int j = this.m_settingsInfoArray.size();
        while (true) {
            int j2 = j - 1;
            if (j > 0) {
                boolean bValid = false;
                SettingInfo settingInfo = (SettingInfo) this.m_settingsInfoArray.get(j2);
                switch (C02524.f65x7bb100de[settingInfo.m_access.ordinal()]) {
                    case C0151R.styleable.View_paddingStart /*1*/:
                        bValid = true;
                        break;
                    case C0151R.styleable.View_paddingEnd /*2*/:
                        bValid = this.m_bLoggedOnPresentation;
                        break;
                    case C0151R.styleable.Toolbar_subtitle /*3*/:
                        if (settingInfo == settingInfoDb.m_settingSslUntrustedPrompt) {
                            bValid = settingInfo.getRadioSelectorItemValue(this.m_owner.getApplicationContext()).value == -1;
                            break;
                        }
                        break;
                }
                if (bValid) {
                    switch (C02524.f66xb2ad8f70[settingInfo.m_type.ordinal()]) {
                        case C0151R.styleable.View_paddingStart /*1*/:
                        case C0151R.styleable.View_paddingEnd /*2*/:
                        case C0151R.styleable.Toolbar_subtitle /*3*/:
                        case C0151R.styleable.Toolbar_contentInsetStart /*4*/:
                        case C0151R.styleable.Toolbar_contentInsetEnd /*5*/:
                        case C0151R.styleable.Toolbar_contentInsetLeft /*6*/:
                        case C0151R.styleable.Toolbar_contentInsetRight /*7*/:
                        case C0151R.styleable.Toolbar_popupTheme /*8*/:
                            break;
                        default:
                            bValid = false;
                            break;
                    }
                }
                if (!bValid) {
                    this.m_settingsInfoArray.remove(j2);
                }
                j = j2;
            } else {
                this.m_SettingsAdapter.notifyDataSetChanged();
                return;
            }
        }
    }
}
