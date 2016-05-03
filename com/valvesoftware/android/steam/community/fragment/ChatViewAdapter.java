package com.valvesoftware.android.steam.community.fragment;

import android.support.v4.app.FragmentActivity;
import android.text.ClipboardManager;
import android.text.SpannableString;
import android.text.method.LinkMovementMethod;
import android.text.method.MovementMethod;
import android.text.style.URLSpan;
import android.text.util.Linkify;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.View.OnLongClickListener;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;
import com.android.volley.toolbox.ImageLoader;
import com.android.volley.toolbox.NetworkImageView;
import com.valvesoftware.android.steam.community.AndroidUtils;
import com.valvesoftware.android.steam.community.C0151R;
import com.valvesoftware.android.steam.community.SteamCommunityApplication;
import com.valvesoftware.android.steam.community.model.UmqMessage;
import com.valvesoftware.android.steam.community.model.UmqMessageType;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.List;

public class ChatViewAdapter extends ArrayAdapter<UmqMessage> {
    private static UmqMessage chatPartnerIsTypingMessage;
    private final FragmentActivity activity;
    private final ChatFragment chatFragment;
    private String chatPartnerAvatarUrl;
    private ImageLoader imageLoader;
    private boolean m_bTyping;
    private LayoutInflater m_layoutInflater;
    private List<UmqMessage> m_list;
    private OnLongClickListener m_longClickHandler;
    private int m_numSecondsTimestamps;
    private final Comparator<UmqMessage> messageOrderComparator;

    /* renamed from: com.valvesoftware.android.steam.community.fragment.ChatViewAdapter.1 */
    class C02121 implements Comparator<UmqMessage> {
        private long maxTimeForSortingPurposes;

        C02121() {
            this.maxTimeForSortingPurposes = 2147483647L;
        }

        public int compare(UmqMessage lhs, UmqMessage rhs) {
            return (int) ((lhs.utcTimeStamp == 0 ? this.maxTimeForSortingPurposes : lhs.utcTimeStamp) - (rhs.utcTimeStamp == 0 ? this.maxTimeForSortingPurposes : rhs.utcTimeStamp));
        }
    }

    /* renamed from: com.valvesoftware.android.steam.community.fragment.ChatViewAdapter.2 */
    class C02132 implements OnLongClickListener {
        final /* synthetic */ FragmentActivity val$activity;

        C02132(FragmentActivity fragmentActivity) {
            this.val$activity = fragmentActivity;
        }

        public boolean onLongClick(View v) {
            try {
                ClipboardManager clipBoard = (ClipboardManager) this.val$activity.getSystemService("clipboard");
                if (clipBoard != null && (v instanceof TextView)) {
                    clipBoard.setText(((TextView) v).getText().toString());
                    int[] screenpos = new int[]{0, 0};
                    v.getLocationOnScreen(screenpos);
                    Toast toast = Toast.makeText(this.val$activity, C0151R.string.notification_chat_copied, 0);
                    toast.setGravity(49, 0, screenpos[1]);
                    toast.show();
                    return true;
                }
            } catch (Exception e) {
            }
            return false;
        }
    }

    /* renamed from: com.valvesoftware.android.steam.community.fragment.ChatViewAdapter.3 */
    class C02143 implements OnClickListener {
        final /* synthetic */ UmqMessage val$message;

        C02143(UmqMessage umqMessage) {
            this.val$message = umqMessage;
        }

        public void onClick(View v) {
            if (ChatViewAdapter.this.chatFragment != null) {
                ChatViewAdapter.this.chatFragment.resendMessage(this.val$message);
            }
        }
    }

    private static class ChatViewHolder {
        public NetworkImageView chatPartnerAvatar;
        public View chatPartnerLayoutContainer;
        public TextView chatPartnerTextView;
        public TextView chatPartnerTimestampTextView;
        public View extraPaddingView;
        public View loggedInUserLayoutContainer;
        public TextView loggedInUserTextView;
        public TextView loggedInUserTimestampTextView;
        public TextView sendErrorTextView;
        public UmqMessage umqMessage;

        private ChatViewHolder() {
        }
    }

    public void setChatPartnerAvatarUrl(String chatPartnerAvatarUrl) {
        if (this.chatPartnerAvatarUrl == null || !this.chatPartnerAvatarUrl.equals(chatPartnerAvatarUrl)) {
            this.chatPartnerAvatarUrl = chatPartnerAvatarUrl;
            notifyDataSetChanged();
        }
    }

    public ChatViewAdapter(List<UmqMessage> list, LayoutInflater layoutInflater, FragmentActivity activity, ChatFragment chatFragment, String chatPartnerAvatarUrl) {
        super(activity, -1, list);
        this.m_numSecondsTimestamps = 900;
        this.m_bTyping = false;
        this.m_longClickHandler = null;
        this.messageOrderComparator = new C02121();
        Collections.sort(list, this.messageOrderComparator);
        this.chatFragment = chatFragment;
        this.chatPartnerAvatarUrl = chatPartnerAvatarUrl;
        this.m_list = list;
        this.m_layoutInflater = layoutInflater;
        this.activity = activity;
        this.imageLoader = SteamCommunityApplication.GetInstance().imageLoader;
        this.m_longClickHandler = new C02132(activity);
    }

    public void notifyDataSetChanged() {
        Collections.sort(this.m_list, this.messageOrderComparator);
        super.notifyDataSetChanged();
    }

    public void showIsTyping() {
        showIsTyping(true);
    }

    public void hideIsTyping() {
        showIsTyping(false);
    }

    private void showIsTyping(boolean typing) {
        if (this.m_bTyping != typing) {
            this.m_bTyping = typing;
            if (this.m_bTyping) {
                this.m_list.add(getTypingMessage());
            } else {
                this.m_list.remove(getTypingMessage());
            }
            notifyDataSetChanged();
        }
    }

    public void attach(ListView lv) {
        lv.setAdapter(this);
    }

    public View getView(int position, View convertView, ViewGroup parent) {
        ChatViewHolder holder;
        DateFormat fmtTimestamp;
        UmqMessage message = (UmqMessage) this.m_list.get(position);
        if (convertView == null) {
            convertView = this.m_layoutInflater.inflate(C0151R.layout.chat_simple_entry, null);
            holder = new ChatViewHolder();
            holder.chatPartnerLayoutContainer = convertView.findViewById(C0151R.id.chat_partner_layout);
            holder.loggedInUserLayoutContainer = convertView.findViewById(C0151R.id.chat_logged_in_user_layout);
            holder.chatPartnerAvatar = (NetworkImageView) convertView.findViewById(C0151R.id.avatar_chat_partner);
            holder.chatPartnerTextView = (TextView) convertView.findViewById(C0151R.id.chat_text_chat_partner);
            holder.loggedInUserTextView = (TextView) convertView.findViewById(C0151R.id.chat_text_logged_in_user);
            holder.extraPaddingView = convertView.findViewById(C0151R.id.extra_padding);
            holder.loggedInUserTimestampTextView = (TextView) convertView.findViewById(C0151R.id.chat_text_logged_in_user_timestamp);
            holder.chatPartnerTimestampTextView = (TextView) convertView.findViewById(C0151R.id.chat_text_partner_timestamp);
            holder.chatPartnerTextView.setOnLongClickListener(this.m_longClickHandler);
            holder.sendErrorTextView = (TextView) convertView.findViewById(C0151R.id.chat_text_send_error);
            holder.umqMessage = message;
            convertView.setTag(holder);
        } else {
            holder = (ChatViewHolder) convertView.getTag();
        }
        if (System.currentTimeMillis() / 86400000 == message.getUtcTimeStampInMilliseconds() / 86400000) {
            fmtTimestamp = SimpleDateFormat.getTimeInstance(3);
        } else {
            fmtTimestamp = SimpleDateFormat.getDateTimeInstance(3, 3);
        }
        Date d = new Date(message.getUtcTimeStampInMilliseconds());
        String timestamp = d.getTime() > 0 ? fmtTimestamp.format(d) : "";
        if (message.isIncoming) {
            holder.loggedInUserLayoutContainer.setVisibility(8);
            holder.chatPartnerLayoutContainer.setVisibility(0);
            holder.chatPartnerAvatar.setImageUrl(this.chatPartnerAvatarUrl, this.imageLoader);
            FormatMessageText(message, holder.chatPartnerTextView);
            if (message != getTypingMessage()) {
                holder.chatPartnerTimestampTextView.setText(timestamp);
                holder.chatPartnerTimestampTextView.setVisibility(0);
            } else {
                holder.chatPartnerTimestampTextView.setVisibility(8);
            }
        } else {
            holder.chatPartnerLayoutContainer.setVisibility(8);
            holder.loggedInUserLayoutContainer.setVisibility(0);
            FormatMessageText(message, holder.loggedInUserTextView);
            if (message.hadSendError) {
                OnClickListener onErrorRetryClickListener = new C02143(message);
                holder.loggedInUserLayoutContainer.setOnClickListener(onErrorRetryClickListener);
                holder.loggedInUserTextView.setOnClickListener(onErrorRetryClickListener);
                holder.sendErrorTextView.setOnClickListener(onErrorRetryClickListener);
                holder.sendErrorTextView.setVisibility(0);
                holder.loggedInUserTimestampTextView.setVisibility(8);
            } else {
                holder.loggedInUserTimestampTextView.setText(timestamp);
                holder.loggedInUserTimestampTextView.setVisibility(0);
                holder.sendErrorTextView.setVisibility(8);
                holder.loggedInUserTextView.setOnLongClickListener(this.m_longClickHandler);
            }
        }
        UmqMessage msgPrev = position > 0 ? (UmqMessage) this.m_list.get(position - 1) : null;
        if (msgPrev != null) {
            boolean z = msgPrev.isIncoming;
            boolean z2 = message.isIncoming;
            if (z != r0) {
                holder.extraPaddingView.setVisibility(0);
                return convertView;
            }
        }
        holder.extraPaddingView.setVisibility(8);
        return convertView;
    }

    public View getDropDownView(int position, View convertView, ViewGroup parent) {
        return getView(position, convertView, parent);
    }

    private void FormatMessageText(UmqMessage message, TextView text) {
        SpannableString spannedText;
        String sTimestamp = null;
        try {
            if (this.m_numSecondsTimestamps <= 0) {
                sTimestamp = SimpleDateFormat.getTimeInstance(3).format(new Date(message.getUtcTimeStampInMilliseconds())) + " : ";
            }
        } catch (Exception e) {
        }
        if (sTimestamp != null) {
            try {
                spannedText = SpannableString.valueOf(new StringBuilder().append(sTimestamp).append(message.text).toString());
            } catch (Exception e2) {
                if (sTimestamp != null) {
                    AndroidUtils.setTextViewText(text, new StringBuilder().append(sTimestamp).append(message.text).toString());
                    return;
                }
                AndroidUtils.setTextViewText(text, message.text);
                return;
            }
        }
        spannedText = SpannableString.valueOf(message.text);
        Linkify.addLinks(spannedText, 15);
        Object[] urls = spannedText.getSpans(0, spannedText.length(), Object.class);
        if (urls != null && urls.length > 0) {
            boolean bAlertUnsafeLinks = SteamCommunityApplication.GetInstance().GetSettingInfoDB().m_settingChatsAlertLinks.getBooleanValue(SteamCommunityApplication.GetInstance().getApplicationContext());
            for (Object xUrl : urls) {
                int nStart = spannedText.getSpanStart(xUrl);
                int nEnd = spannedText.getSpanEnd(xUrl);
                int flags = spannedText.getSpanFlags(xUrl);
                if (xUrl instanceof URLSpan) {
                    spannedText.removeSpan(xUrl);
                    if (sTimestamp != null && nStart < sTimestamp.length()) {
                        nStart = sTimestamp.length();
                    }
                    if (nEnd > nStart) {
                        boolean z;
                        URLSpan uRLSpan = (URLSpan) xUrl;
                        if (bAlertUnsafeLinks && UrlChecker.isUrlUnsafe((URLSpan) xUrl)) {
                            z = true;
                        } else {
                            z = false;
                        }
                        spannedText.setSpan(new UnsafeClickableURL(uRLSpan, z, this.activity), nStart, nEnd, flags);
                    }
                } else if (sTimestamp != null && nStart < sTimestamp.length()) {
                    nStart = sTimestamp.length();
                    spannedText.removeSpan(xUrl);
                    if (nEnd > nStart) {
                        spannedText.setSpan(xUrl, nStart, nEnd, flags);
                    }
                }
            }
        }
        try {
            text.setText(spannedText);
            MovementMethod mm = text.getMovementMethod();
            if (mm == null || !(mm instanceof LinkMovementMethod)) {
                text.setMovementMethod(LinkMovementMethod.getInstance());
            }
        } catch (Exception e3) {
            text.setText("");
        }
    }

    private static UmqMessage getTypingMessage() {
        if (chatPartnerIsTypingMessage == null) {
            chatPartnerIsTypingMessage = new UmqMessage();
            chatPartnerIsTypingMessage.isIncoming = true;
            chatPartnerIsTypingMessage.text = "...";
            chatPartnerIsTypingMessage.type = UmqMessageType.TYPING;
            chatPartnerIsTypingMessage.utcTimeStamp = 2147483647L;
        }
        return chatPartnerIsTypingMessage;
    }
}
