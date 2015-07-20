package com.citylife.aquarium;

import android.content.Intent;
import android.content.SharedPreferences;
import android.net.Uri;
import android.os.Bundle;
import android.preference.Preference;
import android.preference.PreferenceActivity;
import android.util.Log;

import com.google.android.gms.ads.AdListener;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.InterstitialAd;

public class MyParticleSettings extends PreferenceActivity implements
		SharedPreferences.OnSharedPreferenceChangeListener {

	private InterstitialAd interstitial;
	private AdRequest adRequest;
	
	@Override
	protected void onCreate(Bundle icicle) {
		super.onCreate(icicle);
		
		getPreferenceManager().setSharedPreferencesName(
				MyParticleWallpaper.SHARED_PREFS_NAME);
		addPreferencesFromResource(R.xml.settings);
		getPreferenceManager().getSharedPreferences()
				.registerOnSharedPreferenceChangeListener(this);
		Preference button = (Preference) getPreferenceManager().findPreference(
				"link");
		if (button != null) {
			button.setOnPreferenceClickListener(new Preference.OnPreferenceClickListener() {
				public boolean onPreferenceClick(Preference arg0) {
					Uri uri = Uri
							.parse("https://play.google.com/store/apps/developer?id=Elbialab");
					Intent intent = new Intent(Intent.ACTION_VIEW, uri);
					startActivity(intent);
					return true;
				}
			});
		}
		Preference button2 = (Preference) getPreferenceManager()
				.findPreference("exitlink");
		if (button2 != null) {
			button2.setOnPreferenceClickListener(new Preference.OnPreferenceClickListener() {
				public boolean onPreferenceClick(Preference arg0) {
					finish();
					return true;
				}
			});
		}
		// Admob Init
		
		interstitial = new InterstitialAd(this);
		interstitial.setAdUnitId(getString(R.string.admob_interstetial_id));
		interstitial.setAdListener(new AdListener() {
			@Override
			public void onAdLoaded() {
				displayInterstitial();
			}
		});
		adRequest = new AdRequest.Builder() 
        .addTestDevice(AdRequest.DEVICE_ID_EMULATOR)
        .addTestDevice("DD526B8468E58631F643D3665D666111")
        .addTestDevice("8C4C1836BFB79FABB2141D91C1A027BA")
        .build();
		
		interstitial.loadAd(adRequest);
	}
	
	public void displayInterstitial() {
		try
		{
			Log.e("bhhhhhh","showads");
		if(interstitial!=null){
			if (interstitial.isLoaded()) {
				Log.e("bhhhhhh","isLoaded");
			    interstitial.show();
			}
			else
			{
				Log.e("Check Bharath","Ad is not loaded");
				
			}
			}
		}catch (Exception e) {
				// TODO: handle exception
			}
		
	}

	@Override
	protected void onResume() {
		super.onResume();
	}
@Override
public void onBackPressed() {
	// TODO Auto-generated method stub
	super.onBackPressed();
	interstitial=null;
}
	@Override
	protected void onDestroy() {
		getPreferenceManager().getSharedPreferences()
				.unregisterOnSharedPreferenceChangeListener(this);
		super.onDestroy();
	}

	public void onSharedPreferenceChanged(SharedPreferences sharedPreferences,
			String key) {
	}

}
