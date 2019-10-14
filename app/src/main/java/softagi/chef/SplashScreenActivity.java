package softagi.chef;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

import java.sql.Time;
import java.util.Timer;
import java.util.TimerTask;

public class SplashScreenActivity extends AppCompatActivity
{
    ImageView imageView;


    @RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN)
    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash_screen);

        getWindow().getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_LAYOUT_STABLE | View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN | View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR);

        imageView = findViewById(R.id.splash);

        //bindLogo();
        imageView.setScaleX(0);
        imageView.setScaleY(0);

        imageView.animate().scaleXBy(1).scaleYBy(1).setDuration(3000);

        TimerTask timerTask = new TimerTask()
        {
            @Override
            public void run()
            {
                startActivity(new Intent(getApplicationContext(), CardWizardActivity.class));
                finish();
            }
        };
        new Timer().schedule(timerTask, 3000);
    }
}
