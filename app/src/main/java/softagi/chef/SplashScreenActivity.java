package softagi.chef;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

import com.facebook.AccessToken;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

import java.sql.Time;
import java.util.Timer;
import java.util.TimerTask;

public class SplashScreenActivity extends AppCompatActivity
{
    ImageView imageView;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash_screen);

        getWindow().getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_LAYOUT_STABLE | View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN);

        imageView = findViewById(R.id.splash);

        imageView.setScaleX(0);
        imageView.setScaleY(0);

        imageView.animate().scaleXBy(1).scaleYBy(1).setDuration(3000);

        FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();

        AccessToken accessToken = AccessToken.getCurrentAccessToken();
        boolean isLoggedIn = accessToken != null && !accessToken.isExpired();

        if (isLoggedIn)
        {
            TimerTask timerTask = new TimerTask()
            {
                @Override
                public void run()
                {
                    startActivity(new Intent(getApplicationContext(), StartActivity.class));
                }
            };
            new Timer().schedule(timerTask, 3000);
        } else
            {
                if (user != null)
                {
                    if (user.getUid().equals("4YGbxHydJmezPPIPGyE43EqPs7A3"))
                    {
                        TimerTask timerTask = new TimerTask()
                        {
                            @Override
                            public void run()
                            {
                                startActivity(new Intent(getApplicationContext(), AdminActivity.class));
                                finish();
                            }
                        };
                        new Timer().schedule(timerTask, 3000);
                    } else
                        {
                            TimerTask timerTask = new TimerTask()
                            {
                                @Override
                                public void run()
                                {
                                    startActivity(new Intent(getApplicationContext(), StartActivity.class));
                                    finish();
                                }
                            };
                            new Timer().schedule(timerTask, 3000);
                        }
                } else
                {
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
    }
}
