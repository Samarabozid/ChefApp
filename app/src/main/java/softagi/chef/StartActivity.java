package softagi.chef;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.facebook.AccessToken;
import com.facebook.GraphRequest;
import com.facebook.GraphResponse;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.jsibbold.zoomage.ZoomageView;
import com.squareup.picasso.Picasso;
import com.victor.loading.rotate.RotateLoading;

import org.json.JSONException;
import org.json.JSONObject;

import de.hdodenhof.circleimageview.CircleImageView;

public class StartActivity extends AppCompatActivity
{
    TextView txtName, email;
    ZoomageView circleImageView;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_start);

        txtName = findViewById(R.id.txtName);
        email = findViewById(R.id.email);
        circleImageView = findViewById(R.id.img);

        checkLoginStatus();
    }

    private void checkLoginStatus()
    {
        if (AccessToken.getCurrentAccessToken() != null)
        {
            loadUserProfile(AccessToken.getCurrentAccessToken());
        }
    }

    private void loadUserProfile(AccessToken newAccessToken)
    {
        GraphRequest request = GraphRequest.newMeRequest(newAccessToken, new GraphRequest.GraphJSONObjectCallback()
        {
            @Override
            public void onCompleted(JSONObject object, GraphResponse response)
            {
                try
                {
                    String first_name = object.getString("first_name");
                    String last_name = object.getString("last_name");
                    String mail = object.getString("email");

                    String id = object.getString("id");
                    String image_url = "https://graph.facebook.com/" + id + "/picture?width=1500&height=1500";

                    txtName.setText(first_name + " " + last_name);
                    email.setText(mail);
                    Picasso.get().load(image_url).into(circleImageView);

                    //Log.d("HHHH", object.toString());
                } catch (JSONException e)
                {
                    e.printStackTrace();
                }
            }
        });

        Bundle parameters = new Bundle();
        parameters.putString("fields", "first_name,last_name,email,id");
        request.setParameters(parameters);
        request.executeAsync();
    }


    @Override
    public void onBackPressed()
    {
        finishAffinity();
    }

    public void signout(View view)
    {
        FirebaseAuth.getInstance().signOut();
        startActivity(new Intent(getApplicationContext(), MainActivity.class));
    }
}
