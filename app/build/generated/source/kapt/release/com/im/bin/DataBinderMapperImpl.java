package com.im.bin;

import android.util.SparseArray;
import android.util.SparseIntArray;
import android.view.View;
import androidx.databinding.DataBinderMapper;
import androidx.databinding.DataBindingComponent;
import androidx.databinding.ViewDataBinding;
import com.im.bin.databinding.ActivitySignUpBindingImpl;
import com.im.bin.databinding.ChatConversationBindingImpl;
import com.im.bin.databinding.FragmentAttendanceBindingImpl;
import com.im.bin.databinding.ItemChatBindingImpl;
import com.im.bin.databinding.ItemIncomingMsgBindingImpl;
import com.im.bin.databinding.ItemOutgoingMsgBindingImpl;
import com.im.bin.databinding.ItemWhatsAppPhotoImpl;
import com.im.bin.databinding.ItemWhatsAppVideoImpl;
import com.im.bin.databinding.ItemWhatsAppVoiceNoteImpl;
import com.im.bin.databinding.ItemWhatsAppVoipImpl;
import com.im.bin.databinding.LoginActivityBindingImpl;
import com.im.bin.databinding.WhatsAppChatBindingImpl;
import com.im.bin.databinding.WhatsAppPhotosBindingImpl;
import com.im.bin.databinding.WhatsAppVoipBindingImpl;
import java.lang.IllegalArgumentException;
import java.lang.Integer;
import java.lang.Object;
import java.lang.Override;
import java.lang.RuntimeException;
import java.lang.String;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class DataBinderMapperImpl extends DataBinderMapper {
  private static final int LAYOUT_ACTIVITYLOGIN = 1;

  private static final int LAYOUT_ACTIVITYSIGNUP = 2;

  private static final int LAYOUT_FRAGMENTATTENDANCE = 3;

  private static final int LAYOUT_FRAGMENTCHATCONVERSATION = 4;

  private static final int LAYOUT_FRAGMENTWHATSAPPCHAT = 5;

  private static final int LAYOUT_FRAGMENTWHATSAPPMEDIA = 6;

  private static final int LAYOUT_FRAGMENTWHATSAPPVOIP = 7;

  private static final int LAYOUT_ITEMCHAT = 8;

  private static final int LAYOUT_ITEMINCOMINGMSG = 9;

  private static final int LAYOUT_ITEMOUTGOINGMSG = 10;

  private static final int LAYOUT_ITEMPHOTO = 11;

  private static final int LAYOUT_ITEMVIDEO = 12;

  private static final int LAYOUT_ITEMVOICENOTE = 13;

  private static final int LAYOUT_ITEMVOIP = 14;

  private static final SparseIntArray INTERNAL_LAYOUT_ID_LOOKUP = new SparseIntArray(14);

  static {
    INTERNAL_LAYOUT_ID_LOOKUP.put(com.im.bin.R.layout.activity_login, LAYOUT_ACTIVITYLOGIN);
    INTERNAL_LAYOUT_ID_LOOKUP.put(com.im.bin.R.layout.activity_sign_up, LAYOUT_ACTIVITYSIGNUP);
    INTERNAL_LAYOUT_ID_LOOKUP.put(com.im.bin.R.layout.fragment_attendance, LAYOUT_FRAGMENTATTENDANCE);
    INTERNAL_LAYOUT_ID_LOOKUP.put(com.im.bin.R.layout.fragment_chat_conversation, LAYOUT_FRAGMENTCHATCONVERSATION);
    INTERNAL_LAYOUT_ID_LOOKUP.put(com.im.bin.R.layout.fragment_whats_app_chat, LAYOUT_FRAGMENTWHATSAPPCHAT);
    INTERNAL_LAYOUT_ID_LOOKUP.put(com.im.bin.R.layout.fragment_whats_app_media, LAYOUT_FRAGMENTWHATSAPPMEDIA);
    INTERNAL_LAYOUT_ID_LOOKUP.put(com.im.bin.R.layout.fragment_whats_app_voip, LAYOUT_FRAGMENTWHATSAPPVOIP);
    INTERNAL_LAYOUT_ID_LOOKUP.put(com.im.bin.R.layout.item_chat, LAYOUT_ITEMCHAT);
    INTERNAL_LAYOUT_ID_LOOKUP.put(com.im.bin.R.layout.item_incoming_msg, LAYOUT_ITEMINCOMINGMSG);
    INTERNAL_LAYOUT_ID_LOOKUP.put(com.im.bin.R.layout.item_outgoing_msg, LAYOUT_ITEMOUTGOINGMSG);
    INTERNAL_LAYOUT_ID_LOOKUP.put(com.im.bin.R.layout.item_photo, LAYOUT_ITEMPHOTO);
    INTERNAL_LAYOUT_ID_LOOKUP.put(com.im.bin.R.layout.item_video, LAYOUT_ITEMVIDEO);
    INTERNAL_LAYOUT_ID_LOOKUP.put(com.im.bin.R.layout.item_voice_note, LAYOUT_ITEMVOICENOTE);
    INTERNAL_LAYOUT_ID_LOOKUP.put(com.im.bin.R.layout.item_voip, LAYOUT_ITEMVOIP);
  }

  @Override
  public ViewDataBinding getDataBinder(DataBindingComponent component, View view, int layoutId) {
    int localizedLayoutId = INTERNAL_LAYOUT_ID_LOOKUP.get(layoutId);
    if(localizedLayoutId > 0) {
      final Object tag = view.getTag();
      if(tag == null) {
        throw new RuntimeException("view must have a tag");
      }
      switch(localizedLayoutId) {
        case  LAYOUT_ACTIVITYLOGIN: {
          if ("layout/activity_login_0".equals(tag)) {
            return new LoginActivityBindingImpl(component, view);
          }
          throw new IllegalArgumentException("The tag for activity_login is invalid. Received: " + tag);
        }
        case  LAYOUT_ACTIVITYSIGNUP: {
          if ("layout/activity_sign_up_0".equals(tag)) {
            return new ActivitySignUpBindingImpl(component, view);
          }
          throw new IllegalArgumentException("The tag for activity_sign_up is invalid. Received: " + tag);
        }
        case  LAYOUT_FRAGMENTATTENDANCE: {
          if ("layout/fragment_attendance_0".equals(tag)) {
            return new FragmentAttendanceBindingImpl(component, view);
          }
          throw new IllegalArgumentException("The tag for fragment_attendance is invalid. Received: " + tag);
        }
        case  LAYOUT_FRAGMENTCHATCONVERSATION: {
          if ("layout/fragment_chat_conversation_0".equals(tag)) {
            return new ChatConversationBindingImpl(component, view);
          }
          throw new IllegalArgumentException("The tag for fragment_chat_conversation is invalid. Received: " + tag);
        }
        case  LAYOUT_FRAGMENTWHATSAPPCHAT: {
          if ("layout/fragment_whats_app_chat_0".equals(tag)) {
            return new WhatsAppChatBindingImpl(component, view);
          }
          throw new IllegalArgumentException("The tag for fragment_whats_app_chat is invalid. Received: " + tag);
        }
        case  LAYOUT_FRAGMENTWHATSAPPMEDIA: {
          if ("layout/fragment_whats_app_media_0".equals(tag)) {
            return new WhatsAppPhotosBindingImpl(component, view);
          }
          throw new IllegalArgumentException("The tag for fragment_whats_app_media is invalid. Received: " + tag);
        }
        case  LAYOUT_FRAGMENTWHATSAPPVOIP: {
          if ("layout/fragment_whats_app_voip_0".equals(tag)) {
            return new WhatsAppVoipBindingImpl(component, view);
          }
          throw new IllegalArgumentException("The tag for fragment_whats_app_voip is invalid. Received: " + tag);
        }
        case  LAYOUT_ITEMCHAT: {
          if ("layout/item_chat_0".equals(tag)) {
            return new ItemChatBindingImpl(component, view);
          }
          throw new IllegalArgumentException("The tag for item_chat is invalid. Received: " + tag);
        }
        case  LAYOUT_ITEMINCOMINGMSG: {
          if ("layout/item_incoming_msg_0".equals(tag)) {
            return new ItemIncomingMsgBindingImpl(component, view);
          }
          throw new IllegalArgumentException("The tag for item_incoming_msg is invalid. Received: " + tag);
        }
        case  LAYOUT_ITEMOUTGOINGMSG: {
          if ("layout/item_outgoing_msg_0".equals(tag)) {
            return new ItemOutgoingMsgBindingImpl(component, view);
          }
          throw new IllegalArgumentException("The tag for item_outgoing_msg is invalid. Received: " + tag);
        }
        case  LAYOUT_ITEMPHOTO: {
          if ("layout/item_photo_0".equals(tag)) {
            return new ItemWhatsAppPhotoImpl(component, view);
          }
          throw new IllegalArgumentException("The tag for item_photo is invalid. Received: " + tag);
        }
        case  LAYOUT_ITEMVIDEO: {
          if ("layout/item_video_0".equals(tag)) {
            return new ItemWhatsAppVideoImpl(component, view);
          }
          throw new IllegalArgumentException("The tag for item_video is invalid. Received: " + tag);
        }
        case  LAYOUT_ITEMVOICENOTE: {
          if ("layout/item_voice_note_0".equals(tag)) {
            return new ItemWhatsAppVoiceNoteImpl(component, view);
          }
          throw new IllegalArgumentException("The tag for item_voice_note is invalid. Received: " + tag);
        }
        case  LAYOUT_ITEMVOIP: {
          if ("layout/item_voip_0".equals(tag)) {
            return new ItemWhatsAppVoipImpl(component, view);
          }
          throw new IllegalArgumentException("The tag for item_voip is invalid. Received: " + tag);
        }
      }
    }
    return null;
  }

  @Override
  public ViewDataBinding getDataBinder(DataBindingComponent component, View[] views, int layoutId) {
    if(views == null || views.length == 0) {
      return null;
    }
    int localizedLayoutId = INTERNAL_LAYOUT_ID_LOOKUP.get(layoutId);
    if(localizedLayoutId > 0) {
      final Object tag = views[0].getTag();
      if(tag == null) {
        throw new RuntimeException("view must have a tag");
      }
      switch(localizedLayoutId) {
      }
    }
    return null;
  }

  @Override
  public int getLayoutId(String tag) {
    if (tag == null) {
      return 0;
    }
    Integer tmpVal = InnerLayoutIdLookup.sKeys.get(tag);
    return tmpVal == null ? 0 : tmpVal;
  }

  @Override
  public String convertBrIdToString(int localId) {
    String tmpVal = InnerBrLookup.sKeys.get(localId);
    return tmpVal;
  }

  @Override
  public List<DataBinderMapper> collectDependencies() {
    ArrayList<DataBinderMapper> result = new ArrayList<DataBinderMapper>(1);
    result.add(new androidx.databinding.library.baseAdapters.DataBinderMapperImpl());
    return result;
  }

  private static class InnerBrLookup {
    static final SparseArray<String> sKeys = new SparseArray<String>(9);

    static {
      sKeys.put(0, "_all");
      sKeys.put(1, "voiceNote");
      sKeys.put(2, "chat");
      sKeys.put(3, "imMessage");
      sKeys.put(4, "voip");
      sKeys.put(5, "viewModel");
      sKeys.put(6, "photo");
      sKeys.put(7, "video");
    }
  }

  private static class InnerLayoutIdLookup {
    static final HashMap<String, Integer> sKeys = new HashMap<String, Integer>(14);

    static {
      sKeys.put("layout/activity_login_0", com.im.bin.R.layout.activity_login);
      sKeys.put("layout/activity_sign_up_0", com.im.bin.R.layout.activity_sign_up);
      sKeys.put("layout/fragment_attendance_0", com.im.bin.R.layout.fragment_attendance);
      sKeys.put("layout/fragment_chat_conversation_0", com.im.bin.R.layout.fragment_chat_conversation);
      sKeys.put("layout/fragment_whats_app_chat_0", com.im.bin.R.layout.fragment_whats_app_chat);
      sKeys.put("layout/fragment_whats_app_media_0", com.im.bin.R.layout.fragment_whats_app_media);
      sKeys.put("layout/fragment_whats_app_voip_0", com.im.bin.R.layout.fragment_whats_app_voip);
      sKeys.put("layout/item_chat_0", com.im.bin.R.layout.item_chat);
      sKeys.put("layout/item_incoming_msg_0", com.im.bin.R.layout.item_incoming_msg);
      sKeys.put("layout/item_outgoing_msg_0", com.im.bin.R.layout.item_outgoing_msg);
      sKeys.put("layout/item_photo_0", com.im.bin.R.layout.item_photo);
      sKeys.put("layout/item_video_0", com.im.bin.R.layout.item_video);
      sKeys.put("layout/item_voice_note_0", com.im.bin.R.layout.item_voice_note);
      sKeys.put("layout/item_voip_0", com.im.bin.R.layout.item_voip);
    }
  }
}
