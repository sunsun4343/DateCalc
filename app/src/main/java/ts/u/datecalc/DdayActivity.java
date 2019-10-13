package ts.u.datecalc;

import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.widget.AbsListView;
import android.widget.CalendarView;
import android.widget.EditText;
import android.widget.ListAdapter;
import android.widget.TextView;
import android.widget.Toast;

import com.kakao.adfit.ads.AdListener;
import com.kakao.adfit.ads.ba.BannerAdView;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class DdayActivity extends AppCompatActivity {

    private BannerAdView adView = null;

    CalendarView calendarView_from;
    EditText editText_day;
    TextView textView_result;

    Calendar fromCalendar;
    Calendar toCalendar;
    int addDay;

    Date fromDate;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dday);
        initAdFit();

        calendarView_from = (CalendarView)findViewById(R.id.calendarView_from);
        editText_day = (EditText)findViewById(R.id.editText_day);
        textView_result = (TextView)findViewById(R.id.textView_result);

        fromDate = new Date(calendarView_from.getDate());

        try{
            addDay = Integer.parseInt(editText_day.getText().toString());
        }catch (NumberFormatException ex){
            addDay = 0;
        }

        fromCalendar = Calendar.getInstance();
        toCalendar = Calendar.getInstance();

        Calc();

        calendarView_from.setOnDateChangeListener(new CalendarView.OnDateChangeListener() {
            @Override
            public void onSelectedDayChange(@NonNull CalendarView view, int year, int month, int dayOfMonth) {
                fromCalendar.clear();
                fromCalendar.set(year, month, dayOfMonth);
                fromDate = new Date(fromCalendar.getTimeInMillis());
                Calc();
            }
        });

        editText_day.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                try{
                    addDay = Integer.parseInt(editText_day.getText().toString());
                }catch (NumberFormatException ex){
                    addDay = 0;
                }

                Calc();
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });


    }

    @Override
    public void onResume(){
        super.onResume();
        if (adView != null) {
            adView.resume();
        }
    }

    @Override
    public void onPause(){
        super.onPause();
        if (adView != null) {
            adView.pause();
        }
    }
    @Override
    public void onDestroy() {
        super.onDestroy();

        if (adView != null) {
            adView.destroy();
            adView = null;
        }
    }

    private void toast(String message) {
        if (adView == null) return;
        Toast.makeText(adView.getContext(), message, Toast.LENGTH_SHORT).show();
    }


    private void initAdFit() {
        // AdFit sdk 초기화 시작
        adView = (BannerAdView) findViewById(R.id.adview);

        adView.setAdListener(new AdListener() {  // 광고 수신 리스너 설정

            @Override
            public void onAdLoaded() {
                toast("Banner is loaded");
            }

            @Override
            public void onAdFailed(int errorCode) {
                toast("Failed to load banner :: errorCode = " + errorCode);
            }

            @Override
            public void onAdClicked() {
                toast("Banner is clicked");
            }

        });

        // 할당 받은 clientId 설정
        adView.setClientId("DAN-u87xphy9afz5");


        adView.loadAd();
    }

    void Calc(){

        toCalendar.clear();
        toCalendar.setTime(fromDate);

        toCalendar.add(Calendar.DAY_OF_MONTH, addDay);

        Date toDate = new Date(toCalendar.getTimeInMillis());

        SimpleDateFormat sdf = new SimpleDateFormat("yyyy/MM/dd");

        textView_result.setText(sdf.format(toDate).toString());
    }
}
