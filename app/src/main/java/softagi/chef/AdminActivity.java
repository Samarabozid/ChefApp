package softagi.chef;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;
import java.util.List;

import softagi.chef.Models.MealModel;

public class AdminActivity extends AppCompatActivity
{

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin);
    }

    @Override
    public void onBackPressed() {
        finishAffinity();
    }

    public void signout(View view)
    {
        /*FirebaseAuth.getInstance().signOut();
        startActivity(new Intent(getApplicationContext(), MainActivity.class));*/

        List<String> r = new ArrayList<>();
        r.add("recipe 1");
        r.add("recipe 2");
        r.add("recipe 3");
        r.add("recipe 4");
        r.add("recipe 5");

        String img = "https://firebasestorage.googleapis.com/v0/b/chef-af391.appspot.com/o/images%2Fimages%2Flogo2.png?alt=media&token=72e4ed5c-c064-49f8-a545-3f79e0a5f630";
        MealModel mealModel = new MealModel("first meal", img, "first name","30 min", "4 persons", 2.5f, r);

        FirebaseDatabase firebaseDatabase = FirebaseDatabase.getInstance();
        DatabaseReference databaseReference = firebaseDatabase.getReference();

        String key = databaseReference.child("Meals").push().getKey();
        databaseReference.child("Meals").child(key).setValue(mealModel);
    }
}
