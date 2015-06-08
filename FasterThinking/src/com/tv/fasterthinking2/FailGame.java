package com.tv.fasterthinking2;

import java.io.File;
import java.io.FileOutputStream;
import java.util.Random;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.Bitmap.CompressFormat;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.util.Log;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;
import com.thanhcs.fasterthinking2.R;

import db.Question;
import db.DatabaseQ;

@SuppressLint("ResourceAsColor")
public class FailGame extends Activity{
	Button bt1;
	TextView tvDiem, tvDiemCaoI, tvFail;
	LinearLayout llFail;
	int MAMAU = 0;
	int DIEM;
	int KYLUC;
	DatabaseQ mDB;
	Question mCauHoi;
	Context mBase;
	View content2;
	//animation
	Animation animAlpha;
	Animation animScale;
	Animation animRotate;
	int checkFail;
	String TYPE = "" ;
//	private AdView adView;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.dialog_fail);
//		adView = (AdView)findViewById(R.id.adver3);
//		adView.loadAd(new AdRequest.Builder()
//		.addTestDevice(AdRequest.DEVICE_ID_EMULATOR)   
//		.addTestDevice("B3EEABB8EE11C2BE770B684D95219ECB").build());
		
		llFail = (LinearLayout)findViewById(R.id.fail);
		tvDiem = (TextView)findViewById(R.id.tvdiem);
		tvDiemCaoI  =(TextView)findViewById(R.id.tvHscore);
		tvFail  =(TextView)findViewById(R.id.tvfail);

		//set mau cho layout
		setMauChoLayout();


		//get animation

		animAlpha = AnimationUtils.loadAnimation(FailGame.this,
				R.animator.anim_alpha);
		animScale = AnimationUtils.loadAnimation(FailGame.this,
				R.animator.anim_scale);
		animRotate = AnimationUtils.loadAnimation(FailGame.this,
				R.animator.anim_rotate);
		
		content2 = findViewById(R.id.fail);
		content2.setDrawingCacheEnabled(true);

		//lay diem cao cua game :
		mDB = new DatabaseQ(FailGame.this);
		mDB.createDatabase();
		mDB.open();
	
		if(AppControl.CHECKMENU==1)
		{
			KYLUC = mDB.getHighScore();		
		}
		else if
		(AppControl.CHECKMENU==2)
		{
			// lad hight score
			TYPE = "MATH :";
			KYLUC = mDB.getHighScoreMath();	
//			Toast.makeText(DialogFail.this, "Cao Toan", Toast.LENGTH_SHORT).show();
		}
		else
		{
			//load high scor
			TYPE = "ENGLISH :";
			KYLUC = mDB.getHighScoreEngLish();	
//			Toast.makeText(DialogFail.this, "Cao _anh", Toast.LENGTH_SHORT).show();
		}
	


		// lay dien tu main activity
		Intent inten = getIntent();
		DIEM = inten.getIntExtra("diem", 0);
		checkFail = inten.getIntExtra("fail", 0);
		if(checkFail==0)
		{
			tvFail.setText(TYPE +" Time Out");
		}
		else
		{
			tvFail.setText(TYPE +" Game Over");
		}
				
		if(DIEM>KYLUC)
		{
			if(AppControl.CHECKMENU==1)
			{
				mCauHoi = mDB.getCauHoiID(1);
				mCauHoi.setVang(DIEM);
				mDB.updatetCauViTri(mCauHoi);
				tvDiem.setText("New : "+DIEM);
				tvDiemCaoI.setText("Best : "+KYLUC);
				tvDiem.setBackgroundResource(R.color.hoido);
				tvDiem.startAnimation(animScale);	
			}

			else if(AppControl.CHECKMENU==2)
			{
					mCauHoi = mDB.getCauHoiID(2);
					mCauHoi.setVang(DIEM);
					mDB.updatetCauViTri(mCauHoi);
					tvDiem.setText("New : "+DIEM);
					tvDiemCaoI.setText("Best : "+KYLUC);
					tvDiem.setBackgroundResource(R.color.hoido);
					tvDiem.startAnimation(animScale);	
				
		//		Toast.makeText(DialogFail.this, "Toan hoc", Toast.LENGTH_SHORT).show();
			}

			else
			{
				
					mCauHoi = mDB.getCauHoiID(3);
					mCauHoi.setVang(DIEM);
					mDB.updatetCauViTri(mCauHoi);
					tvDiem.setText("New : "+DIEM);
					tvDiemCaoI.setText("Best : "+KYLUC);
					tvDiem.setBackgroundResource(R.color.hoido);
					tvDiem.startAnimation(animScale);	
				
	//			Toast.makeText(DialogFail.this, "Tieng anh", Toast.LENGTH_SHORT).show();
			}


		}
		else
		{
			tvDiem.setText("New : "+DIEM);
			tvDiemCaoI.setText("Best : "+KYLUC);
		}	
		findViewById(R.id.btchoilai).setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View v) {
				Intent intent= new Intent(FailGame.this, MainActivity.class);
				// setResult(MainActivity.RESULT_CODE_SAVE2, intent);
				startActivity(intent); 
				overridePendingTransition(R.animator.right_in, R.animator.left_out);
				finish();


			}
		});


		findViewById(R.id.btshare).setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View v) {
				takingScreenshot();
				shareImage();

			}

		});


		findViewById(R.id.btvemenu).setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View v) {
				Intent intent = new Intent(FailGame.this,  MenuGame.class);
				startActivity(intent);
				overridePendingTransition(R.animator.bottom_in, R.animator.top_out);
				finish();

			}

		});


	}






	protected void shareImage() {
		Intent share = new Intent(Intent.ACTION_SEND);
		// If you want to share a png image only, you can do:
		// setType("image/png"); OR for jpeg: setType("image/jpeg");
		share.setType("image/*");
		// Make sure you put example png image named myImage.png in your
		// directory
		String imagePath = Environment.getExternalStorageDirectory()
				+ "/ft.png";
		File imageFileToShare = new File(imagePath);
		Uri uri = Uri.fromFile(imageFileToShare);
		share.putExtra(Intent.EXTRA_STREAM, uri);
		startActivity(Intent.createChooser(share, "Share image to..."));
	}

	protected void takingScreenshot()
	{

		//content2 = findViewById(R.id.fail);
		Bitmap bitmap = content2.getDrawingCache();
		File file = new File( Environment.getExternalStorageDirectory() + "/ft.png");
		try 
		{
			file.createNewFile();
			FileOutputStream ostream = new FileOutputStream(file);
			bitmap.compress(CompressFormat.PNG, 100, ostream);
			ostream.close();
		} 
		catch (Exception e) 
		{
			e.printStackTrace();
		}
		Toast.makeText(FailGame.this, "Take sceen shot", Toast.LENGTH_SHORT).show();
	}

	private void setMauChoLayout() {
		Random ran = new Random();
		MAMAU = ran.nextInt(11);
		llFail.setBackgroundResource(AppControl.arrMaMau[MAMAU]);

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
