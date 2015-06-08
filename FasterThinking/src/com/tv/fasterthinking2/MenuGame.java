package com.tv.fasterthinking2;

import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;
import com.thanhcs.fasterthinking2.R;
import com.tv.fasterthinking2.MainActivity.MyCountDownTimer;

import android.animation.Animator;
import android.animation.AnimatorInflater;
import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.drawable.ColorDrawable;
import android.net.Uri;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.view.Menu;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;

public class MenuGame extends Activity {
	int checkLoa = 0;
	static int tat_mo_loa  = 1;
	ImageButton btnspeak;
	ImageView btsetting;
	int test =0;
	int DEM =0;
	Animator animation;
	//private AdView adView;
	@SuppressLint("NewApi") @Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.menugame);
		
//		adView = (AdView)findViewById(R.id.adver);
//		adView.loadAd(new AdRequest.Builder(
//		.addTestDevice(AdRequest.DEVICE_ID_EMULATOR)   
//		.addTestDevice("B3EEABB8EE11C2BE770B684D95219ECB").build());
//		
		
		btnspeak = (ImageButton) findViewById(R.id.btspeak);
		btsetting = (ImageView)findViewById(R.id.btsetting);
		
		findViewById(R.id.imgMath).setVisibility(View.INVISIBLE);
		findViewById(R.id.imgPlay).setVisibility(View.INVISIBLE);
		findViewById(R.id.imgEnglish).setVisibility(View.INVISIBLE);
		MyCountDownTimer countDownTimer = new MyCountDownTimer(5000, 1000);
		countDownTimer.start();

		
		
		btnspeak.setVisibility(View.INVISIBLE);
		findViewById(R.id.btinfor).setVisibility(View.INVISIBLE);
		findViewById(R.id.btnshare).setVisibility(View.INVISIBLE);
		findViewById(R.id.btmore).setVisibility(View.INVISIBLE);
		
		 animation = AnimatorInflater.loadAnimator(this, R.anim.rotate_around_center_point);
		animation.setTarget(btsetting);
		animation.start();
		
		btsetting.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View arg0) {
				// TODO Auto-generated method stub
				animation.setTarget(btsetting);
				animation.start();
				test++;
				if(test%2==0)
				{
					btnspeak.setVisibility(View.INVISIBLE);
					findViewById(R.id.btinfor).setVisibility(View.INVISIBLE);
					findViewById(R.id.btnshare).setVisibility(View.INVISIBLE);
					findViewById(R.id.btmore).setVisibility(View.INVISIBLE);
				}
				else
				{
					btnspeak.setVisibility(View.VISIBLE);
					findViewById(R.id.btinfor).setVisibility(View.VISIBLE);
					findViewById(R.id.btnshare).setVisibility(View.VISIBLE);
					findViewById(R.id.btmore).setVisibility(View.VISIBLE);
				}
				
			}
		});
		
		btnspeak.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View v) {

				checkLoa++;
				if(checkLoa%2==1)
				{
					btnspeak.setBackgroundResource(R.drawable.speakoff);
					tat_mo_loa  =0;
				}	
				else
				{
					btnspeak.setBackgroundResource(R.drawable.speakon);	
					tat_mo_loa = 1;
				}

			}
		});

		findViewById(R.id.imgPlay).setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View v) {
				AppControl.CHECKMENU = 1;
		//		AppControl.TIME_CONTROL = 3000;
				Intent intent =  new Intent(MenuGame.this, MainActivity.class);
				startActivity(intent);		
				overridePendingTransition(R.animator.bottom_in, R.animator.top_out);
			}
		});


		findViewById(R.id.imgMath).setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View v) {
				AppControl.CHECKMENU = 2;
			//	AppControl.TIME_CONTROL =3000;
				Intent intent =  new Intent(MenuGame.this, MainActivity.class);
				startActivity(intent);		
				overridePendingTransition(R.animator.bottom_in, R.animator.top_out);
			}
		});

		findViewById(R.id.imgEnglish).setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View v) {
				AppControl.CHECKMENU = 3;
			//	AppControl.TIME_CONTROL = 3000;
				Intent intent =  new Intent(MenuGame.this, MainActivity.class);
				startActivity(intent);		
				overridePendingTransition(R.animator.bottom_in, R.animator.top_out);

			}
		});

		findViewById(R.id.btinfor).setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View v) {
				opendialog();

			}

			private void opendialog() {
				
				final Dialog dialog = new Dialog(MenuGame.this , R.style.AppTheme);
				//	dialog.setTitle("Hold on for a minute!");
					 dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);			
					dialog.setContentView(R.layout.dialod_custome_temp);
					dialog.getWindow().setBackgroundDrawable(new ColorDrawable(R.color.transparent));
					dialog.show();
					Button positive_button = (Button)dialog.findViewById(R.id.positive_button);
					positive_button.setOnClickListener(new View.OnClickListener() {
						
						@Override
						public void onClick(View v) {
							// TODO Auto-generated method stub
							// TODO Auto-generated method stub
							Intent share = new Intent(android.content.Intent.ACTION_SEND); 
							share.setType("text/plain");
							share.addFlags(Intent.FLAG_ACTIVITY_CLEAR_WHEN_TASK_RESET); 
							share.putExtra(Intent.EXTRA_SUBJECT, "Downoad game Faster Thinking");
							share.putExtra(Intent.EXTRA_TEXT, "https://play.google.com/store/apps/details?id=com.thanhcs.fasterthinking");
							startActivity(Intent.createChooser(share, "Share text to..."));
							
						}
					});
					
				
			}
		});

		findViewById(R.id.btnshare).setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				Intent share = new Intent(android.content.Intent.ACTION_SEND); 
				share.setType("text/plain");
				share.addFlags(Intent.FLAG_ACTIVITY_CLEAR_WHEN_TASK_RESET); 
				share.putExtra(Intent.EXTRA_SUBJECT, "Downoad game Faster Thinking");
				share.putExtra(Intent.EXTRA_TEXT, "https://play.google.com/store/apps/details?id=com.thanhcs.fasterthinking");
				startActivity(Intent.createChooser(share, "Share text to..."));

			}
		});

		findViewById(R.id.btmore).setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View v) {
				String url = "https://play.google.com/store/apps/developer?id=ThanhCS94";
				Intent i = new Intent(Intent.ACTION_VIEW);
				i.setData(Uri.parse(url));
				startActivity(i);

			}
		});

	
	

	
	}
	
	public class MyCountDownTimer extends CountDownTimer {
		public  MyCountDownTimer(long startTime, long interval) {
			super(startTime, interval);
		}

		@Override
		public void onFinish() {

		}

		@SuppressLint("NewApi") @Override
		public void onTick(long millisUntilFinished) {
			DEM++;
			if(DEM==1)
			{
				findViewById(R.id.imgPlay).setVisibility(View.VISIBLE);
				animation.start();
				animation.setTarget(findViewById(R.id.imgPlay));
				animation.start();
			}
			if(DEM==2)
			{
				findViewById(R.id.imgMath).setVisibility(View.VISIBLE);
				findViewById(R.id.imgEnglish).setVisibility(View.VISIBLE);
				animation.start();
			}
			
			}
		}
}

	
//	@Override
//	public void onPause() {
//		adView.pause();
//		super.onPause();
//	}
//
//	@Override
//	public void onResume() {
//		super.onResume();
//		adView.resume();
//	}
//
//	@Override
//	public void onDestroy() {
//		adView.destroy();
//		super.onDestroy();
//	}
	
