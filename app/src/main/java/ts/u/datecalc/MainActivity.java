package ts.u.datecalc;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import com.kakao.adfit.ads.AdListener;
import com.kakao.adfit.ads.ba.BannerAdView;

public class MainActivity extends AppCompatActivity {

    private static final String LOGTAG = "BannerTypeXML1";
    private BannerAdView adView = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initAdFit();

    }

    public  void OnClick_Between(View v)
    {
        Intent intent=new Intent(MainActivity.this,BetweenDateActivity.class);
        startActivity(intent);
    }

    public  void OnClick_Dday(View v)
    {
        Intent intent=new Intent(MainActivity.this,DdayActivity.class);
        startActivity(intent);
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

    private void initAdFit() {
        // AdFit sdk 초기화 시작
        adView = (BannerAdView) findViewById(R.id.adview);

        adView.setAdListener(new AdListener() {
            @Override
            public void onAdLoaded() {
                Log.d(LOGTAG, "onAdLoaded");
            }

            @Override
            public void onAdFailed(int code) {
                Log.d(LOGTAG, "onAdFailed : " + code);
            }

            @Override
            public void onAdClicked() {
                Log.d(LOGTAG, "onAdClicked");
            }
        });

        // 할당 받은 clientId 설정
        adView.setClientId("DAN-u87xphy9afz5");

        // 광고 갱신 시간 : 기본 60초
        // 0 으로 설정할 경우, 갱신하지 않음.
        adView.setRequestInterval(30);

        // 광고 사이즈 설정
        adView.setAdUnitSize("320x50");

        adView.loadAd();
    }


}
