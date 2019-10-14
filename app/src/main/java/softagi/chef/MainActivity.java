package softagi.chef;

import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentPagerAdapter;
import androidx.viewpager.widget.ViewPager;

import android.app.ProgressDialog;
import android.content.Intent;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.pm.Signature;
import android.os.Build;
import android.os.Bundle;
import android.util.Base64;
import android.util.Log;
import android.widget.TextView;
import android.widget.Toast;

import java.net.MalformedURLException;
import java.net.URL;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Arrays;

import com.facebook.AccessToken;
import com.facebook.CallbackManager;
import com.facebook.FacebookCallback;
import com.facebook.FacebookException;
import com.facebook.FacebookSdk;
import com.facebook.GraphRequest;
import com.facebook.GraphResponse;
import com.facebook.appevents.AppEventsLogger;
import com.facebook.login.LoginResult;
import com.facebook.login.widget.LoginButton;
import com.google.android.material.tabs.TabLayout;
import com.squareup.picasso.Picasso;

import org.json.JSONException;
import org.json.JSONObject;

import de.hdodenhof.circleimageview.CircleImageView;
import softagi.chef.Fragments.SigninFragment;
import softagi.chef.Fragments.SignupFragment;

public class MainActivity extends AppCompatActivity
{
    private CallbackManager callbackManager;
    private LoginButton loginButton;

    FragmentPagerAdapter fragmentPagerAdapter;
    ViewPager viewPager;
    TabLayout tabLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        viewPager = findViewById(R.id.viewpager);
        tabLayout = findViewById(R.id.tabs);

        fragmentPagerAdapter = new FragmentPagerAdapter(getSupportFragmentManager())
        {
            private final Fragment[] mFragments = new Fragment[]
                    {
                            new SigninFragment(),
                            new SignupFragment()
                    };
            private final String[] mFragmentNames = new String[]
                    {
                            "SIGN IN",
                            "SIGN UP",
                    };

            @Override
            public Fragment getItem(int position)
            {
                return mFragments[position];
            }

            @Override
            public int getCount()
            {
                return mFragments.length;
            }

            @Nullable
            @Override
            public CharSequence getPageTitle(int position)
            {
                return mFragmentNames[position];
            }
        };

        viewPager.setAdapter(fragmentPagerAdapter);
        tabLayout.setupWithViewPager(viewPager);

        //printhash();
        /*FacebookSdk.sdkInitialize(MainActivity.this);
        AppEventsLogger.activateApp(MainActivity.this);*/

        callbackManager = CallbackManager.Factory.create();

        loginButton = findViewById(R.id.login_btn);

        loginButton.setReadPermissions(Arrays.asList("public_profile", "email","user_birthday","user_friends"));
        //If you are using in a fragment, call loginButton.setFragment(this);

        //Callback registration
        loginButton.registerCallback(callbackManager, new FacebookCallback<LoginResult>()
        {
            @Override
            public void onSuccess(LoginResult loginResult)
            {
                /*GraphRequest graphRequest = GraphRequest.newMeRequest(loginResult.getAccessToken(), new GraphRequest.GraphJSONObjectCallback() {
                    @Override
                    public void onCompleted(JSONObject object, GraphResponse response)
                    {
                        progressDialog.dismiss();
                        Log.d("response", response.toString());
                        getdatafromfacebook(object);
                    }
                });*/
                Toast.makeText(getApplicationContext(), "Done ..", Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onCancel() {
                // App code
            }

            @Override
            public void onError(FacebookException exception)
            {
                // App code
            }
        });

        AccessToken accessToken = AccessToken.getCurrentAccessToken();
        boolean isLoggedIn = accessToken != null && !accessToken.isExpired();

        if (isLoggedIn)
        {
            Toast.makeText(getApplicationContext(), "loged in ..", Toast.LENGTH_SHORT).show();
        }
    }

    /*private void getdatafromfacebook(JSONObject object)
    {
        try
        {
            //URL profile_picture = new URL("https://graph.facebok.com/" + object.getString("id") + "/picture?width=250&height=250");
            String s = "https://graph.facebok.com/" + object.getString("id") + "/picture?width=250&height=250";

            Picasso.get()
                    .load(s)
                    .into(circleImageView);

            result_txt.setText(object.getString("email") + "\n" + object.getString("birthday") + "\n" + object.getJSONObject("friends").getJSONObject("summary").getString("total_count"));
        }
        catch (JSONException e)
        {
            e.printStackTrace();
        }
    }*/

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data)
    {
        super.onActivityResult(requestCode, resultCode, data);
        callbackManager.onActivityResult(requestCode, resultCode, data);
    }

    private void printhash()
    {
        try
        {
            PackageInfo packageInfo = getPackageManager().getPackageInfo("softagi.chef", PackageManager.GET_SIGNATURES);

            for (Signature signature : packageInfo.signatures)
            {
                MessageDigest messageDigest = MessageDigest.getInstance("SHA");
                messageDigest.update(signature.toByteArray());
                Log.d("KeyHash", Base64.encodeToString(messageDigest.digest(), Base64.DEFAULT));
            }
        } catch (PackageManager.NameNotFoundException | NoSuchAlgorithmException e)
        {
            e.printStackTrace();
        }
    }

    private long exitTime = 0;

    @RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN)
    public void doExitApp() {
        if ((System.currentTimeMillis() - exitTime) > 2000) {
            Toast.makeText(this, "Press again to exit", Toast.LENGTH_SHORT).show();
            exitTime = System.currentTimeMillis();
        } else {
            finishAffinity();
        }
    }

    @RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN)
    @Override
    public void onBackPressed()
    {
        doExitApp();
    }
}
