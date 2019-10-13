package ts.u.datecalc;

import android.os.Debug;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.CalendarView;
import android.widget.TextView;
import android.widget.Toast;

import com.kakao.adfit.ads.AdListener;
import com.kakao.adfit.ads.ba.BannerAdView;

import org.w3c.dom.Text;

import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

public class BetweenDateActivity extends AppCompatActivity {

    private BannerAdView adView = null;

    CalendarView calendarView_from;
    CalendarView calendarView_to;
    TextView textView_result;

    Calendar fromCalendar;
    Calendar toCalendar;

//    Date fromDate;
//    Date toDate;

    int differenceDay = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_between_date);
        initAdFit();

        calendarView_from = (CalendarView)findViewById(R.id.calendarView_from);
        calendarView_to = (CalendarView)findViewById(R.id.calendarView_to);
        textView_result = (TextView)findViewById(R.id.textView_result);

        fromCalendar = Calendar.getInstance();
        toCalendar = Calendar.getInstance();

        fromCalendar.clear();
        fromCalendar.setTimeInMillis(calendarView_from.getDate());
        toCalendar.clear();
        toCalendar.setTimeInMillis(calendarView_to.getDate());
        Calc();

        calendarView_from.setOnDateChangeListener(new CalendarView.OnDateChangeListener() {
            @Override
            public void onSelectedDayChange(@NonNull CalendarView view, int year, int month, int dayOfMonth) {
                fromCalendar.clear();
                fromCalendar.set(year, month, dayOfMonth);

                Log.d("debug","call");
                Calc();
            }
        });

        calendarView_to.setOnDateChangeListener(new CalendarView.OnDateChangeListener() {
            @Override
            public void onSelectedDayChange(@NonNull CalendarView view, int year, int month, int dayOfMonth) {
                toCalendar.clear();
                toCalendar.set(year, month, dayOfMonth);
                Calc();
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

        long fromMS = fromCalendar.getTimeInMillis();
        long fromS = (long)(fromMS / 1000);
        long fromM = (long)(fromS / 60);
        long fromH = (long)(fromM / 60);
        int fromD = (int)(fromH / 24);

        long toMS = toCalendar.getTimeInMillis();
        long toS = (long)(toMS / 1000);
        long toM = (long)(toS / 60);
        long toH = (long)(toM / 60);
        int toD = (int)(toH / 24);

        differenceDay = toD - fromD;

        textView_result.setText(differenceDay + "일");
    }

}
