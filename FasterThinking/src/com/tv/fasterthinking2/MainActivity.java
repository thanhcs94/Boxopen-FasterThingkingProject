package com.tv.fasterthinking2;

import java.io.IOException;
import java.io.InputStream;
import java.util.Random;

import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;
import com.thanhcs.fasterthinking2.R;

import db.Question;
import db.DatabaseQ;
import android.annotation.SuppressLint;
import android.annotation.TargetApi;
import android.app.Activity;
import android.content.Intent;
import android.content.res.AssetFileDescriptor;
import android.graphics.drawable.Drawable;
import android.media.MediaPlayer;
import android.os.Build;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.util.Log;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

@SuppressLint("ResourceAsColor")
public class MainActivity extends Activity {
//	private AdView adView;
	static boolean isStop = false;
	static TextView tvCount;
	TextView tvDiem;
	int CHECK =  0;
	static int CHECK_END_TIME =0;
	static int Time = 4;
	static int DEMTIME = AppControl.DEM_TIME;
	int DIEM = 0;
	static int DAPAN  = 1;
	int CAUSO = 1;
	DatabaseQ mDB;
	Question mCauHoi;
	private static  CountDownTimer countDownTimer;
	private final static long startTime =AppControl.TIME_CONTROL;//chay toi 2 s la ket thuc
	private final static long interval = 1 * 1000;
	static ImageView imgDapan1, imgDapan2, imgDe; 
	MediaPlayer mPlayer;
	int MAMAU = 0;
	LinearLayout llRoot;
	
	Animation animAlpha;
	Animation animScale;
	Animation animRotate;
	int lololo = 0;//just for test in github , remove it/
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
//		
//		adView = (AdView)findViewById(R.id.adver2);
//		adView.loadAd(new AdRequest.Builder()
//		.addTestDevice(AdRequest.DEVICE_ID_EMULATOR)   
//		.addTestDevice("B3EEABB8EE11C2BE770B684D95219ECB").build());
//this line for test github		
		
		CHECK_END_TIME=0;
		llRoot = (LinearLayout)findViewById(R.id.root); 
		tvCount = (TextView)findViewById(R.id.tvcount);
		tvDiem = (TextView)findViewById(R.id.textView);
		imgDapan1 = (ImageView)findViewById(R.id.imgMath);
		imgDe = (ImageView)findViewById(R.id.imgPlay);
		imgDapan2 = (ImageView)findViewById(R.id.imgEnglish);
		tvCount.setText("Time : "+AppControl.DEM_TIME);
		tvDiem.setText("Score : "+DIEM);
		//tat mo loa 
		animAlpha = AnimationUtils.loadAnimation(MainActivity.this,
				R.animator.anim_alpha);
		animScale = AnimationUtils.loadAnimation(MainActivity.this,
				R.animator.anim_scale);
		animRotate = AnimationUtils.loadAnimation(MainActivity.this,
				R.animator.anim_rotate);
		
		
		
				
		setMauChoLayout();
		mDB = new DatabaseQ(MainActivity.this);
		mDB.createDatabase();
		mDB.open();


		CAUSO  = getCauSo();
		mCauHoi = mDB.getCauHoiID(CAUSO);
		TaiHinhAnhLen();
		
		imgDapan2.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View v) {
				if(CHECK_END_TIME==0)
				{
					CHECK++;
					if(CHECK==1)
					{
						DEMTIME = AppControl.DEM_TIME ;
						CountStart();
						DAPAN =2;
					}
					else
					{
						countDownTimer.cancel();
						DEMTIME = AppControl.DEM_TIME ;
						CountStart();
						DAPAN =2;
					}

					if(mCauHoi.getKt2()==1)
					{
						countDownTimer.cancel();
						PhatNhacDung();
						CAUSO  = getCauSo();
						DIEM++;
						mCauHoi = mDB.getCauHoiID(CAUSO);
						TaiHinhAnhLen();
						countDownTimer.start();

					}
					else
					{
						llRoot.startAnimation(animRotate);
						PhatNhacSai();
						countDownTimer.cancel();
						Intent intent = new Intent(MainActivity.this,  FailGame.class);
						intent.putExtra("diem", DIEM);
						intent.putExtra("fail", 1);
						startActivity(intent);
						overridePendingTransition(R.animator.bottom_in, R.animator.top_out);
						finish();
					}

				}
			}
		});

		imgDapan1.setOnClickListener(new View.OnClickListener() {

			@TargetApi(Build.VERSION_CODES.HONEYCOMB)
			@Override
			public void onClick(View v) {
				if(CHECK_END_TIME==0)
				{
					CHECK++;
					if(CHECK==1)
					{

						DEMTIME = AppControl.DEM_TIME ;
						DAPAN =1;
						CountStart();
					}
					else
					{
						countDownTimer.cancel();
						DEMTIME = AppControl.DEM_TIME;
						CountStart();
						DAPAN =1;
					}
					if(mCauHoi.getKt2()==2)
					{
						countDownTimer.cancel();
						PhatNhacDung();
						CAUSO  = getCauSo();
						DIEM++;
						mCauHoi = mDB.getCauHoiID(CAUSO);
						TaiHinhAnhLen();
						countDownTimer.start();
					}
					else
					{
						llRoot.startAnimation(animRotate);
						PhatNhacSai();
						countDownTimer.cancel();
						Intent intent = new Intent(MainActivity.this,  FailGame.class);
						intent.putExtra("diem", DIEM);
						intent.putExtra("fail", 1);
						startActivity(intent);
						overridePendingTransition(R.animator.bottom_in, R.animator.top_out);
						finish();
					}

				}
			}
		});

	}


	private int getCauSo() {
		int temp  =  CAUSO;
		Random random = new Random();
		if(AppControl.CHECKMENU==1)
		{
			int ChSize = mDB.getSizeListCH();
			do
			{
				CAUSO = 1+random.nextInt(ChSize);
			}
			while(CAUSO==temp);
		
		}
		else if(AppControl.CHECKMENU==2)
		{
			//load toand hoc tu cau ? -> cau ?
			/**
			 * tu cau so  -> cau so
			 * 
			 */
			do
			{
				CAUSO = random.nextInt(99)+1 ;//tu 10 -> 20
			}
			while(CAUSO==temp);
		}
		else
		{
			do
			{
				CAUSO = random.nextInt(147)+101;//tu 10 -> 20
			}
			while(CAUSO==temp);
		
			
			
		}
		return CAUSO;
	}


	private void setMauChoLayout() {
		Random ran = new Random();
		MAMAU = ran.nextInt(5);
		llRoot.setBackgroundResource(AppControl.arrMaMau2[MAMAU]);

	}


	private void TaiHinhAnhLen() {
	//	tvDiem.setText("Cau So : "+CAUSO);
		tvDiem.setText("Score : " +DIEM);
		String temp = mCauHoi.getLinkanh();
		String temp1 = mCauHoi.getLinkanh1();
		String temp2 = mCauHoi.getLinkanh2();
		Drawable d = loadHinhAnh(temp);
		Drawable d1 = loadHinhAnh(temp1);
		Drawable d2 = loadHinhAnh(temp2);
		imgDe.setImageDrawable(d);
		imgDapan1.setImageDrawable(d1);
		imgDapan2.setImageDrawable(d2);
	}


	private void startSound(String filename){
		try {
			AssetFileDescriptor afd = getAssets().openFd(filename);
			MediaPlayer player = new MediaPlayer();
			player.setDataSource(afd.getFileDescriptor(), afd.getStartOffset(), afd.getLength());
			player.prepare();
			player.start();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	public void PhatNhacDung() {
		if(MenuGame.tat_mo_loa==1)
		{
			startSound("sound/scored.wav");
		}
		else
		{
			
		}
		
	}
	
	
	public void PhatNhacSai() {
		if(MenuGame.tat_mo_loa==1)
		{
			startSound("sound/fail.wav");
		}
		else
		{
			
		}
	}
	private Drawable loadHinhAnh(String temp2) {
		
		Drawable d = null;
		try 
		{
			// get input stream
			InputStream ims = getAssets().open(temp2);
			// load image as Drawable
			d = Drawable.createFromStream(ims, null);
			// set image to ImageView

		}
		catch(IOException ex) 
		{
			return null;
		}

		return d;
	}


	protected void nextQuestion() {
		countDownTimer.cancel();
		imgDe.setImageResource(R.drawable.ic_launcher);
		imgDapan1.setImageResource(R.drawable.ic_launcher);
		imgDapan2.setImageResource(R.drawable.ic_launcher);
		countDownTimer.start();

	}

	private void CountStart() {
		countDownTimer = new MyCountDownTimer(startTime, interval);
		countDownTimer.start();

	}

	public class MyCountDownTimer extends CountDownTimer {
		public  MyCountDownTimer(long startTime, long interval) {
			super(startTime, interval);
		}

		@Override
		public void onFinish() {

		}

		@Override
		public void onTick(long millisUntilFinished) {
			DEMTIME--;
			tvCount.setText("Time : "+DEMTIME);
			if(DEMTIME==0)
			{
				DEMTIME=-1;
				llRoot.startAnimation(animRotate);
				PhatNhacSai();
				Intent intent = new Intent(MainActivity.this,  FailGame.class);
				intent.putExtra("diem", DIEM);
				intent.putExtra("fail", 0);
				
				MainActivity.CHECK_END_TIME=1;
				startActivity(intent);
				MainActivity.countDownTimer.cancel();
				overridePendingTransition(R.animator.bottom_in, R.animator.top_out);
				finish();
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

}
