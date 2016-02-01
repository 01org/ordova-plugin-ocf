package com.intel.cordova.plugin.oic;

// Java
import java.util.ArrayList;

// Cordova
import org.apache.cordova.CallbackContext;
import org.apache.cordova.PluginResult;

// Android
import android.content.Context;
import android.util.Log;

// Third party
import org.json.JSONArray;
import org.json.JSONException;


public class OICBackendMock implements OICBackendInterface {
    public OICBackendMock(Context context) {
    }

    public void findResources(JSONArray args, CallbackContext cc)
            throws JSONException {
        String deviceId;
        String resourcePath;
        ArrayList<String> resourceTypes;
        JSONArray resourceTypesJson;

        resourceTypes = new ArrayList<String>();

        try {
            deviceId = args.getJSONObject(0).getString("deviceId");
            resourcePath = args.getJSONObject(0).getString("resourcePath");
            resourceTypesJson = args.getJSONObject(0).getJSONArray("resourceTypes");
            if (resourceTypesJson != null) {
                for (int i = 0; i < resourceTypesJson.length(); i++) {
                    resourceTypes.add(resourceTypesJson.getString(i));
                }
            }
        } catch (JSONException ex) {
            deviceId = "";
            resourcePath = "";
        }

        // Create dummy resource event
        OICResourceId id = new OICResourceId(deviceId, resourcePath);
        OICResource res = new OICResource(id, resourceTypes);
        OICResourceEvent ev = new OICResourceEvent(res);
        PluginResult result = new PluginResult(PluginResult.Status.OK, ev.toJSON());
        result.setKeepCallback(true);
        cc.sendPluginResult(result);
    }
}
