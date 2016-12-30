package com.mdsoft.plugin;

import org.apache.cordova.*;
import org.json.JSONArray;
import org.json.JSONException;
import java.io.IOException;

//Wifi
import android.net.NetworkInfo;
import android.net.wifi.WifiManager;
import android.net.wifi.WifiConfiguration;
import android.net.wifi.WifiEnterpriseConfig;
import android.net.wifi.ScanResult;
import android.net.wifi.WifiInfo;
import android.net.wifi.SupplicantState;
import android.content.Context;

public class serviceDiscovery extends CordovaPlugin {
	
	private WifiManager wifiManager;
	
	@Override
    public void initialize(CordovaInterface cordova, CordovaWebView webView) {
        super.initialize(cordova, webView);
        this.wifiManager = (WifiManager) cordova.getActivity().getSystemService(Context.WIFI_SERVICE);
    }

	@Override
    public boolean execute(String action, JSONArray args, final CallbackContext callbackContext) {
		
        final cordovaSSDP mCordovaSSDP = new cordovaSSDP();

        if (action.equals("getNetworkServices")) {
			
            final String service = args.optString(0); //"ssdp:all"; //

            cordova.getThreadPool().execute(new Runnable() {
               @Override
               public void run() {
                  try{
                      mCordovaSSDP.search(service, callbackContext);
                    }catch(IOException e){
                      e.printStackTrace();
                    }
               }
            });  
			
            return true;

		} else if(action.equals("getConnectedSSID")) {
            return this.getConnectedSSID(callbackContext);
        } else {
            return false;
        }
		
    }
	
	/**
     * This method retrieves the SSID for the currently connected network
     *
     *    @param    callbackContext        A Cordova callback context
     *    @return    true if SSID found, false if not.
    */
    private boolean getConnectedSSID(CallbackContext callbackContext){
		if(!wifiManager.isWifiEnabled()){
            callbackContext.error("Wifi is disabled");
            return false;
        }
		
        WifiInfo info = wifiManager.getConnectionInfo();

        if(info == null){
            callbackContext.error("Unable to read wifi info");
            return false;
        }

        String ssid = info.getSSID();
        if(ssid.isEmpty()) {
            ssid = info.getBSSID();
        }
        if(ssid.isEmpty()){
            callbackContext.error("SSID is empty");
            return false;
        }
		
        callbackContext.success(ssid);
        return true;
    }
}
