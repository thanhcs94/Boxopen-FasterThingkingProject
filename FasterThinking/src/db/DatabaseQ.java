package db;

import java.io.IOException; 
import java.util.ArrayList;

import android.content.ContentValues;
import android.content.Context; 
import android.database.Cursor; 
import android.database.SQLException; 
import android.database.sqlite.SQLiteDatabase; 
import android.util.Log; 
//Class chính thao tac1 với dữ liệu
public class DatabaseQ<DataBaseHelper>  
{ 
	protected static final String TAG = "DataAdapter"; 

	private final Context mContext; 
	private SQLiteDatabase mDb; 
	private MyDataBaseHelper mDbHelper; 
	public DatabaseQ (Context context)  
	{ 
		this.mContext = context; 
		mDbHelper = new MyDataBaseHelper(mContext); 
	} 
	//khởi tạo 1 TestAdapter
	public DatabaseQ  createDatabase() throws SQLException  
	{ 
		try  
		{ 
			mDbHelper.createDataBase(); 
		}  
		catch (IOException mIOException)  
		{ 
			Log.e(TAG, mIOException.toString() + "  UnableToCreateDatabase"); 
			throw new Error("UnableToCreateDatabase"); 
		} 
		return this; 
	} 
	//Mở sau khi đã đươc khởi tạo
	public DatabaseQ  open() throws SQLException  
	{ 
		try  
		{ 
			mDbHelper.openDataBase(); 
			mDbHelper.close(); 
			mDb = mDbHelper.getWritableDatabase(); 
		}  
		catch (SQLException mSQLException)  
		{ 
			Log.e(TAG, "open >>"+ mSQLException.toString()); 
			throw mSQLException; 
		} 
		return this; 
	} 
	///close database
	public void close()  
	{ 
		mDbHelper.close(); 
	} 

			
		//get all cau hoi
		//Get all data from database
		public int getSizeListCH() 
		{ 	
			ArrayList<Question> arr = new ArrayList<>();
			String sql ="select *from mydata";//"SELECT id, CauNoiHay FROm data";  
			Cursor mCur = mDb.rawQuery(sql,null);
			
			if (mCur!=null) 
			{ 
				mCur.moveToNext(); 
			} 
			while(mCur.isAfterLast()==false){
				Question resut=new Question();
				resut.setId(mCur.getInt(mCur.getColumnIndex("id")));
				resut.setLinkanh(((mCur.getString(mCur.getColumnIndex("linkanh")))));
				resut.setLinkanh1(((mCur.getString(mCur.getColumnIndex("linkanh1")))));
				resut.setLinkanh2((mCur.getString(mCur.getColumnIndex("linkanh2"))));
				resut.setDapan(mCur.getString(mCur.getColumnIndex("dapan")));
				resut.setCauso(mCur.getInt(mCur.getColumnIndex("causo")));
				resut.setVang(mCur.getInt(mCur.getColumnIndex("vang")));
				resut.setKt1(mCur.getInt(mCur.getColumnIndex("kt1")));
				resut.setKt2(mCur.getInt(mCur.getColumnIndex("kt2")));
				mCur.moveToNext();
			
				arr.add(resut);
				
			}
			Log.d("CHCHCHCHHCHCHHCH", "Size "+arr.size());
			return arr.size();
		}
	
	//Get data voi CauNoiHay
	public Question getCauHoiID(int id) 
	{ 
		Question resut=new Question();
		String sql ="select *from mydata where id = "+String.valueOf(id);//"SELECT id, CauNoiHay FROm data";  
		Cursor mCur = mDb.rawQuery(sql,null);

		if (mCur!=null) 
		{ 
			mCur.moveToNext(); 
		} 
		resut.setId(mCur.getInt(mCur.getColumnIndex("id")));
		resut.setLinkanh(((mCur.getString(mCur.getColumnIndex("linkanh")))));
		resut.setLinkanh1(((mCur.getString(mCur.getColumnIndex("linkanh1")))));
		resut.setLinkanh2((mCur.getString(mCur.getColumnIndex("linkanh2"))));
		resut.setDapan(mCur.getString(mCur.getColumnIndex("dapan")));
		resut.setCauso(mCur.getInt(mCur.getColumnIndex("causo")));
		resut.setVang(mCur.getInt(mCur.getColumnIndex("vang")));
		resut.setKt1(mCur.getInt(mCur.getColumnIndex("kt1")));
		resut.setKt2(mCur.getInt(mCur.getColumnIndex("kt2")));
		Log.d("GetCauhoi", resut.toString());
		return resut;
	}
	
	public int getHighScore() 
	{ 
		Question resut=new Question();
		String sql ="select *from mydata where id = 1";
		Cursor mCur = mDb.rawQuery(sql,null);

		if (mCur!=null) 
		{ 
			mCur.moveToNext(); 
		} 
		resut.setId(mCur.getInt(mCur.getColumnIndex("id")));
		resut.setLinkanh(((mCur.getString(mCur.getColumnIndex("linkanh")))));
		resut.setLinkanh1(((mCur.getString(mCur.getColumnIndex("linkanh1")))));
		resut.setLinkanh2((mCur.getString(mCur.getColumnIndex("linkanh2"))));
		resut.setDapan(mCur.getString(mCur.getColumnIndex("dapan")));
		resut.setCauso(mCur.getInt(mCur.getColumnIndex("causo")));
		resut.setVang(mCur.getInt(mCur.getColumnIndex("vang")));
		resut.setKt1(mCur.getInt(mCur.getColumnIndex("kt1")));
		resut.setKt2(mCur.getInt(mCur.getColumnIndex("kt2")));
		Log.d("GetCauhoi", resut.toString());
		
		
			return resut.vang;
		
			
		
	}
	
	public int getHighScoreMath() 
	{ 
		Question resut=new Question();
		String sql ="select *from mydata where id = 2";
		Cursor mCur = mDb.rawQuery(sql,null);

		if (mCur!=null) 
		{ 
			mCur.moveToNext(); 
		} 
		resut.setId(mCur.getInt(mCur.getColumnIndex("id")));
		resut.setLinkanh(((mCur.getString(mCur.getColumnIndex("linkanh")))));
		resut.setLinkanh1(((mCur.getString(mCur.getColumnIndex("linkanh1")))));
		resut.setLinkanh2((mCur.getString(mCur.getColumnIndex("linkanh2"))));
		resut.setDapan(mCur.getString(mCur.getColumnIndex("dapan")));
		resut.setCauso(mCur.getInt(mCur.getColumnIndex("causo")));
		resut.setVang(mCur.getInt(mCur.getColumnIndex("vang")));
		resut.setKt1(mCur.getInt(mCur.getColumnIndex("kt1")));
		resut.setKt2(mCur.getInt(mCur.getColumnIndex("kt2")));
		Log.d("GetCauhoi", resut.toString());
		
		
			return resut.vang;
		
			
		
	}

	
	public int getHighScoreEngLish() 
	{ 
		Question resut=new Question();
		String sql ="select *from mydata where id = 3";
		Cursor mCur = mDb.rawQuery(sql,null);

		if (mCur!=null) 
		{ 
			mCur.moveToNext(); 
		} 
		resut.setId(mCur.getInt(mCur.getColumnIndex("id")));
		resut.setLinkanh(((mCur.getString(mCur.getColumnIndex("linkanh")))));
		resut.setLinkanh1(((mCur.getString(mCur.getColumnIndex("linkanh1")))));
		resut.setLinkanh2((mCur.getString(mCur.getColumnIndex("linkanh2"))));
		resut.setDapan(mCur.getString(mCur.getColumnIndex("dapan")));
		resut.setCauso(mCur.getInt(mCur.getColumnIndex("causo")));
		resut.setVang(mCur.getInt(mCur.getColumnIndex("vang")));
		resut.setKt1(mCur.getInt(mCur.getColumnIndex("kt1")));
		resut.setKt2(mCur.getInt(mCur.getColumnIndex("kt2")));
		Log.d("GetCauhoi", resut.toString());
		
		
			return resut.vang;
		
			
		
	}
	
	
	public int getvitriLuu() 
	{ 
		Question resut=new Question();
		String sql ="select *from mydata where id = 1";
		Cursor mCur = mDb.rawQuery(sql,null);

		if (mCur!=null) 
		{ 
			mCur.moveToNext(); 
		} 
		resut.setId(mCur.getInt(mCur.getColumnIndex("id")));
		resut.setLinkanh(((mCur.getString(mCur.getColumnIndex("linkanh")))));
		resut.setLinkanh1(((mCur.getString(mCur.getColumnIndex("linkanh1")))));
		resut.setLinkanh2((mCur.getString(mCur.getColumnIndex("linkanh2"))));
		resut.setDapan(mCur.getString(mCur.getColumnIndex("dapan")));
		resut.setCauso(mCur.getInt(mCur.getColumnIndex("causo")));
		resut.setVang(mCur.getInt(mCur.getColumnIndex("vang")));
		resut.setKt1(mCur.getInt(mCur.getColumnIndex("kt1")));
		resut.setKt2(mCur.getInt(mCur.getColumnIndex("kt2")));
		Log.d("GetCauhoi", resut.toString());
		
		
			return resut.kt2;
		
			
		
	}

	
	
	public int getVang() 
	{ 
		Question resut=new Question();
		String sql ="select *from mydata where id = 1";
		Cursor mCur = mDb.rawQuery(sql,null);

		if (mCur!=null) 
		{ 
			mCur.moveToNext(); 
		} 
		resut.setId(mCur.getInt(mCur.getColumnIndex("id")));
		resut.setLinkanh(((mCur.getString(mCur.getColumnIndex("linkanh")))));
		resut.setLinkanh1(((mCur.getString(mCur.getColumnIndex("linkanh1")))));
		resut.setLinkanh2((mCur.getString(mCur.getColumnIndex("linkanh2"))));
		resut.setDapan(mCur.getString(mCur.getColumnIndex("dapan")));
		resut.setCauso(mCur.getInt(mCur.getColumnIndex("causo")));
		resut.setVang(mCur.getInt(mCur.getColumnIndex("vang")));
		resut.setKt1(mCur.getInt(mCur.getColumnIndex("kt1")));
		resut.setKt2(mCur.getInt(mCur.getColumnIndex("kt2")));
		Log.d("GetCauhoi", resut.toString());
		
		
			return resut.vang;
		
			
		
	}
	
	//update 1 từ vào database
	public void updatetCauViTri(Question p) 
	{ 
		ContentValues values = new ContentValues();
		mDbHelper.getWritableDatabase();
		values.put("id",p.getId());
		values.put("linkanh", p.getLinkanh());
		values.put("linkanh1", p.getLinkanh1());
		values.put("linkanh2", p.getLinkanh2());
		values.put("dapan",p.getDapan());
		values.put("causo",p.getCauso());
		values.put("vang",p.getVang());
		values.put("kt1",p.getKt1());
		values.put("kt2",p.getKt2());
		mDb.update("mydata", values,"id"+" = "+p.getId(), null);
		Log.d("DiemCao", "update thanh cong" +p.getDapan()+" | "+p.getKt2());
		
	}

	
} 


