package ts.u.datecalc;

import android.os.Debug;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.CalendarView;
import android.widget.TextView;

import org.w3c.dom.Text;

import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

public class BetweenDateActivity extends AppCompatActivity {

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
