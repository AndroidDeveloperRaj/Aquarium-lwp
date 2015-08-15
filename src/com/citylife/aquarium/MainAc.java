package com.citylife.aquarium;

import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdSize;
import com.google.android.gms.ads.AdView;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.app.WallpaperManager;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.net.Uri;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;

public class MainAc extends Activity{
	AdView mAdView;
	private AdRequest adRequest;
@Override
protected void onCreate(Bundle savedInstanceState) {
	// TODO Auto-generated method stub
	super.onCreate(savedInstanceState);
	//StartAppSDK.init(this, "103275689", "204441494", true);
	
	
	
	setContentView(R.layout.ac_main);
	mAdView = new AdView(this);
	mAdView.setAdUnitId(getResources().getString(R.string.banner));
	mAdView.setAdSize(AdSize.SMART_BANNER);
	//mAdView.setAdListener(new ToastAdListener(this));
	LinearLayout layout = (LinearLayout) findViewById(R.id.rootViewGroup);
	RelativeLayout.LayoutParams params = new RelativeLayout.LayoutParams(
			RelativeLayout.LayoutParams.MATCH_PARENT,
			RelativeLayout.LayoutParams.WRAP_CONTENT);
	layout.addView(mAdView, params);
	adRequest = new AdRequest.Builder() 
    .addTestDevice(AdRequest.DEVICE_ID_EMULATOR)
    .addTestDevice("DD526B8468E58631F643D3665D666111")
    .addTestDevice("8C4C1836BFB79FABB2141D91C1A027BA")
    .build();
	mAdView.loadAd(adRequest);
	Button preview=(Button)findViewById(R.id.preview);
	Button edit=(Button)findViewById(R.id.edit);
	
	
	
	preview.setOnClickListener(new View.OnClickListener() {
		
		@Override
		public void onClick(View arg0) {
		
			Intent intent = new Intent();
			intent.setAction(WallpaperManager.ACTION_LIVE_WALLPAPER_CHOOSER);
			startActivity(intent);
			
		}
	});
	
	edit.setOnClickListener(new View.OnClickListener() {
		
		@Override
		public void onClick(View arg0) {
		Intent i=new Intent(MainAc.this,MyParticleSettings.class);	
		i.putExtra("action",2);
		startActivity(i);
		
			
		}
	});

}
@Override
public boolean onKeyDown(int keyCode, KeyEvent event) {
	if ((keyCode == KeyEvent.KEYCODE_BACK)) {

		// abc=1;

		// Intent intent;
		// intent = new Intent().setClass(MainActivity.this,
		// letsmore.class);
		// startActivity(intent);

		// this.finish();
		
		
		AlertDialog alertDialog = new AlertDialog.Builder(
				MainAc.this).create();
		alertDialog.setTitle("Rate 5 stars before exit");
		alertDialog.setIcon(R.drawable.icon);
		alertDialog
				.setMessage("Dear user, your 5 stars rating will encourage us to better improve the product and provide you the most quality product.");
		alertDialog.setButton(AlertDialog.BUTTON_NEUTRAL, "Cancel",
				new DialogInterface.OnClickListener() {
					public void onClick(DialogInterface dialog, int which) {
						dialog.dismiss();
					}
				});
		alertDialog.setButton(AlertDialog.BUTTON_NEGATIVE, "Rate 5 stars",
				new DialogInterface.OnClickListener() {
					public void onClick(DialogInterface dialog, int which) {
						dialog.dismiss();
						
						gotoplay(getPackageName());
					}
				});
		alertDialog.setButton(AlertDialog.BUTTON_POSITIVE, "Exit",
				new DialogInterface.OnClickListener() {
					public void onClick(DialogInterface dialog, int which) {
						dialog.dismiss();
						//mInterstitial=null;
						finish();
					}
				});
		
		alertDialog.show();

	}
	return false;// super.onKeyDown(keyCode, event);
}

 public void gotoplay(String name) {

		try {
			String url = "market://details?id=" + name;
			Intent i1 = new Intent(Intent.ACTION_VIEW);
			i1.setData(Uri.parse(url));
			startActivity(i1);
		} catch (Exception e2) {
			// TODO: handle exception
			String url = "https://play.google.com/store/apps/details?id="
					+ name;
			Intent i1 = new Intent(Intent.ACTION_VIEW);
			i1.setData(Uri.parse(url));
			startActivity(i1);
		}

	}


}
