package hansune.android.exam.ctcalendar;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.Paint;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.GridView;
import android.widget.TextView;
import ctartifact.android.common.Oneday;

public class Ex11_CalendarActivity extends Activity implements OnClickListener,

	OnItemClickListener {
	
	Paint foreground = new Paint(Paint.ANTI_ALIAS_FLAG);
	ArrayList<String> mItems;
	ArrayAdapter<String> adapter;
	TextView textYear, textMon;
	
	Date date = new Date();// ���ÿ� ��¥�� ���� ���ش�.
	int year = date.getYear() + 1900;
	int mon = date.getMonth() + 1;
	
	ArrayList<String> daylist; //���� ����� ������ �ִ´�. 1,2,3,4,.... 28?30?31? 
    ArrayList<String> actlist; //���ڿ� �ش��ϴ� Ȱ�������� ������ �ִ´�.
    
    //int[] test = new int[31];
    
    private int dayCnt;
    private int maxDay = 0;
    private int oneday_width =0;
    private int oneday_height =0;
    private int mSelect = -1;
    
    
	/** Called when the activity is first created. */

	@Override
	public void onCreate(Bundle savedInstanceState) {

		super.onCreate(savedInstanceState);

		setContentView(R.layout.main2);

		
		//foreground.setColor(getTextColor());
		
		textYear = (TextView) this.findViewById(R.id.edit1);
		textMon = (TextView) this.findViewById(R.id.edit2);

		mItems = new ArrayList<String>();

		adapter = new ArrayAdapter<String>(this,
		android.R.layout.simple_list_item_1, mItems);

		GridView grid = (GridView) this.findViewById(R.id.grid1);
		grid.setAdapter(adapter);
		grid.setOnItemClickListener(this);
		
		
		Date date = new Date();// ���ÿ� ��¥�� ���� ���ش�.
		int year = date.getYear() + 1900;
		int mon = date.getMonth() + 1;

		textYear.setText(year + "");
		textMon.setText(mon + "");

		fillDate(year, mon);
		
		Button prevmonth, nextmonth, btnmove;
		
		btnmove = (Button) this.findViewById(R.id.bt1);
		prevmonth = (Button) findViewById(R.id.btn_prevmonth);
		nextmonth = (Button) findViewById(R.id.btn_nextmonth);
		
		btnmove.setOnClickListener(this);
		prevmonth.setOnClickListener(this);
		nextmonth.setOnClickListener(this);
		
		if(actlist==null)actlist = new ArrayList<String>();
        actlist.clear();
        
        /*daylist.add("��");actlist.add("");
        daylist.add("��");actlist.add("");
        daylist.add("ȭ");actlist.add("");
        daylist.add("��");actlist.add("");
        daylist.add("��");actlist.add("");
        daylist.add("��");actlist.add("");
        daylist.add("��");actlist.add("");*/
        
        makeCalendar(year, mon);
        
        
	}

	private void makeCalendar(int year, int mon) {
		
		Date current = new Date(year - 1900, mon - 1, 1);

		maxDay = current.getDay(); // ���ϵ� int�� ����.
		
		for(int i=0;i<maxDay;i++) //���ڸ� �ִ´�.
        {
            daylist.add(Integer.toString(i));
            actlist.add("");
        }
		
		final Oneday[] oneday = new Oneday[daylist.size()];
        final Calendar today = Calendar.getInstance();
        String sToday = String.valueOf(today);
        // TableLayout tl =(TableLayout)findViewById(R.id.tl_calendar_monthly);
       // tl.removeAllViews();
 
        dayCnt = 0;
        int maxRow = ((daylist.size() > 42)? 7:6);
        int maxColumn = 7;
 
       // int daylistsize = daylist.size()-1;
        for(int i=0; i<maxRow; i++ )
        {
            //TableRow tr = new TableRow(this);
           // tr.setLayoutParams(new LayoutParams(LayoutParams.WRAP_CONTENT,LayoutParams.WRAP_CONTENT));
            for(int j=0; j<maxColumn; j++)
            {
                //calender_oneday�� ������ ������ �ִ´�. 
                oneday[dayCnt] = new Oneday(getApplicationContext());
 
                //���Ϻ� ���� ���ϱ� 
                if((dayCnt % 7) == 0){
                    oneday[dayCnt].setTextDayColor(Color.RED);
                } else if((dayCnt % 7) == 6){
                    oneday[dayCnt].setTextDayColor(Color.BLUE);
                } else {
                    oneday[dayCnt].setTextDayColor(Color.BLACK);
                }
               
              //���� ǥ��
                if(oneday[dayCnt].getDay() == today.get(Calendar.DAY_OF_MONTH)
                        && oneday[dayCnt].getMonth() == today.get(Calendar.MONTH)
                        && oneday[dayCnt].getYear() == today.get(Calendar.YEAR)){
                     
                    oneday[dayCnt].isToday = true;
                    actlist.set(dayCnt,"����");
                    oneday[dayCnt].invalidate();
                    mSelect = dayCnt;
                }
            }
        
        }
		
	}

	

    
	
	@Override
	public void onClick(View arg0) {

		// TODO Auto-generated method stub

		if (arg0.getId() == R.id.bt1) {

			int year = Integer.parseInt(textYear.getText().toString());

			int mon = Integer.parseInt(textMon.getText().toString());

			fillDate(year, mon);

		}
		
		else if (arg0.getId() == R.id.btn_prevmonth) {
			//Date date = new Date();// ���ÿ� ��¥�� ���� ���ش�.
			//int year = Integer.parseInt(textYear.getText().toString());
			//int mon = Integer.parseInt(textMon.getText().toString());
		
			mon = mon-1;
			textYear.setText(year + "");
			textMon.setText(mon + "");
			if(mon==0){
				mon=12;
				year = year-1;
				textYear.setText(year + "");
				textMon.setText(mon + "");
			}
			fillDate(year, mon);
		}
		
		else if (arg0.getId() == R.id.btn_nextmonth) {
			//Date date = new Date();// ���ÿ� ��¥�� ���� ���ش�.
			//textYear.setText(textYear.getText().toString() + "");
			//textMon.setText(textMon.getText().toString() + "");
			mon = mon + 1;
			
			textYear.setText(year + "");
			textMon.setText(mon + "");	
			
			//textYear.setText(textYear.getText().toString() + "");
			//textMon.setText(textMon.getText().toString() + "");
			
			//textYear.getText().toString()
			//textMon.getText().toString()
			
			
			if(mon==13){
				mon=1;
				year = year+1;
				textYear.setText(year + "");
				textMon.setText(mon + "");
			}
			
			fillDate(year, mon);
		}

	}

	private void fillDate(int year, int mon) {
		if(daylist==null)daylist = new ArrayList<String>();
        daylist.clear();
        
		mItems.clear();
		mItems.add("��");
		mItems.add("��");
		mItems.add("ȭ");
		mItems.add("��");
		mItems.add("��");
		mItems.add("��");
		mItems.add("��");
		
		Date current = new Date(year - 1900, mon - 1, 1);

		int day = current.getDay(); // ���ϵ� int�� ����.
		final Oneday[] oneday = new Oneday[daylist.size()];
		
		for (int i = 0; i < day; i++) {
			
			mItems.add("");
			
			//daylist.add(Integer.toString(i));
			//actlist.add("");
			//makeCalendar();
			
		}
		

		current.setDate(32);// 32�ϱ��� �Է��ϸ� 1�Ϸ� �ٲ��ش�.

		int last = 32 - current.getDate();
		
		for (int i = 1; i <= last; i++) {

			mItems.add(i + "");
			
		}

		adapter.notifyDataSetChanged();

	}
	
	/*private void makeCalendar()
    {
		final Oneday[] oneday = new Oneday[daylist.size()];
		
		dayCnt = 0;
        int maxRow = ((daylist.size() > 42)? 7:6);
        int maxColumn = 7;
        
        //oneday_width = getWindow().getWindowManager().getDefaultDisplay().getWidth();
        //oneday_height = getWindow().getWindowManager().getDefaultDisplay().getHeight();
 
       // oneday_height = ((((oneday_height >= oneday_width)?oneday_height:oneday_width) - tl.getTop()) / (maxRow+1))-10;
        //oneday_width = (oneday_width / maxColumn)+1;
        
        int daylistsize =daylist.size()-1;
        for(int i=1;i<=maxRow;i++ )
        {
            //TableRow tr = new TableRow(this);
            //tr.setLayoutParams(new LayoutParams(LayoutParams.WRAP_CONTENT,LayoutParams.WRAP_CONTENT));
            for(int j=1;j<=maxColumn;j++)
            {
                //calender_oneday�� ������ ������ �ִ´�. 
                //oneday[dayCnt] = new Oneday(getApplicationContext());
 
                //���Ϻ� ���� ���ϱ� 
                if((dayCnt % 7) == 0){
                    oneday[dayCnt].setTextDayColor(Color.RED);
                } else if((dayCnt % 7) == 6){
                    oneday[dayCnt].setTextDayColor(Color.BLUE);
                } else {
                    oneday[dayCnt].setTextDayColor(Color.YELLOW);
                }
            }
        }
        
    }*/
	
	
	
	@Override
	public void onItemClick(AdapterView<?> arg0, View arg1, int arg2, long arg3) {

		// TODO Auto-generated method stub

		if (mItems.get(arg2).equals("")) {

			;

		} else {

			Intent intent = new Intent(this, ExToday.class);// �ش� ���� ��������

			intent.putExtra("Param1", textYear.getText().toString() + "/"

			+ textMon.getText().toString() + "/" + mItems.get(arg2));

			startActivity(intent);

		}

	}

}
