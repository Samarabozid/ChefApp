package softagi.chef.Fragments;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.victor.loading.rotate.RotateLoading;

import softagi.chef.AdminActivity;
import softagi.chef.R;
import softagi.chef.StartActivity;

public class SigninFragment extends Fragment
{
    View view;
    EditText email,password;
    TextView forgotpassword;
    Button sign_in,guest;

    String email_txt,password_txt;

    FirebaseAuth auth;
    FirebaseDatabase firebaseDatabase;
    DatabaseReference databaseReference;

    // Firebase User
    FirebaseUser user;

    RotateLoading rotateLoading;
    CheckBox checkBox;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState)
    {
        view = inflater.inflate(R.layout.sign_in_fragment, container, false);
        return view;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState)
    {
        super.onActivityCreated(savedInstanceState);

        auth = FirebaseAuth.getInstance();
        firebaseDatabase = FirebaseDatabase.getInstance();
        databaseReference = firebaseDatabase.getReference();

        email = view.findViewById(R.id.email_field);
        password = view.findViewById(R.id.password_field);
        sign_in = view.findViewById(R.id.sign_in_btn);
        guest = view.findViewById(R.id.guest_btn);
        forgotpassword = view.findViewById(R.id.forgot_password_txt);
        rotateLoading = view.findViewById(R.id.signinrotateloading);
        checkBox = view.findViewById(R.id.remember_me_checkbox);

        forgotpassword.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                FirebaseAuth auth = FirebaseAuth.getInstance();
                final String emailAddress = email.getText().toString();

                if (TextUtils.isEmpty(emailAddress))
                {
                    Toast.makeText(getContext(), "please enter your email firstly", Toast.LENGTH_SHORT).show();
                    return;
                }

                if (emailAddress.equals("admin@admin.com"))
                {
                    Toast.makeText(getContext(), "you can't reset admin account password", Toast.LENGTH_SHORT).show();
                    return;
                }

                auth.sendPasswordResetEmail(emailAddress)
                        .addOnCompleteListener(new OnCompleteListener<Void>() {
                            @Override
                            public void onComplete(@NonNull Task<Void> task) {
                                if (task.isSuccessful())
                                {
                                    Toast.makeText(getContext(), "password reset email has been sent to : " + emailAddress, Toast.LENGTH_SHORT).show();
                                }
                            }
                        });
            }
        });


        sign_in.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                email_txt = email.getText().toString();
                password_txt = password.getText().toString();

                if (TextUtils.isEmpty(email_txt))
                {
                    Toast.makeText(getContext(), "please enter your email", Toast.LENGTH_SHORT).show();
                    return;
                }

                if (TextUtils.isEmpty(password_txt))
                {
                    Toast.makeText(getContext(), "please enter your password", Toast.LENGTH_SHORT).show();
                    return;
                }

                if (email_txt.equals("admin@admin.com") && password_txt.equals("admin1234"))
                {
                    rotateLoading.start();

                    AdminLogin(email_txt,password_txt);
                } else
                {
                    rotateLoading.start();

                    UserLogin(email_txt,password_txt);
                }
            }
        });

        guest.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                Intent intent = new Intent(getContext(), StartActivity.class);
                startActivity(intent);
            }
        });
    }

    private void AdminLogin(String email, String password)
    {
        auth.signInWithEmailAndPassword(email,password)
                .addOnCompleteListener(new OnCompleteListener<AuthResult>()
                {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task)
                    {
                        if (task.isSuccessful())
                        {
                            rotateLoading.stop();

                            Intent intent = new Intent(getContext(), AdminActivity.class);
                            startActivity(intent);
                        } else
                        {
                            rotateLoading.stop();
                            String taskmessage = task.getException().getMessage();
                            Toast.makeText(getContext(), taskmessage, Toast.LENGTH_LONG).show();
                        }
                    }
                });
    }

    private void UserLogin(String email, String password)
    {
        auth.signInWithEmailAndPassword(email,password)
                .addOnCompleteListener(new OnCompleteListener<AuthResult>()
                {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task)
                    {
                        if (task.isSuccessful())
                        {
                            rotateLoading.stop();

                            Intent intent = new Intent(getContext(), StartActivity.class);
                            startActivity(intent);
                        } else
                        {
                            rotateLoading.stop();
                            String taskmessage = task.getException().getMessage();
                            Toast.makeText(getContext(), taskmessage, Toast.LENGTH_LONG).show();
                        }
                    }
                });
    }
}
