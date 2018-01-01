package com.example.pulkit.internshala_demo1;

import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.iid.FirebaseInstanceId;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;

import java.util.HashMap;
import java.util.Map;

import butterknife.BindView;
import butterknife.ButterKnife;

public class UploadData extends AppCompatActivity {

    private FirebaseAuth mAuth;
    private DatabaseReference mRootRef;
    private StorageReference mStorageRef;
    private String uid;
    @BindView(R.id.nameeditText) private EditText nameedt;
    @BindView(R.id.mobileeditText) private EditText mobileedt;
    @BindView(R.id.addressline1editText) private EditText addressline1edt;
    @BindView(R.id.addressline2editText) private EditText addressline2edt;
    @BindView(R.id.cityeditText) private EditText cityedt;
    @BindView(R.id.stateeditText) private EditText stateedt;
    @BindView(R.id.nearestlandmarkeditText) private EditText nearestlandmarkedt;
    @BindView(R.id.landlineNoeditText) private EditText landlineedt;
    @BindView(R.id.alernatenoeditText) private EditText alternateedt;
    @BindView(R.id.emailideditText) private EditText emailidedt;
    @BindView(R.id.imageView3) private ImageView imageView;




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_upload_data);
        ButterKnife.bind(this);

        mAuth = FirebaseAuth.getInstance();
        uid = mAuth.getCurrentUser().getUid();

        mRootRef = FirebaseDatabase.getInstance().getReference().child("users");
        mStorageRef = FirebaseStorage.getInstance().getReference();
    }

    public void getimage(View view) {
        // start picker to get image for cropping and then use the image in cropping activity
//        CropImage.activity()
//                .setAspectRatio(1, 1)
//                .setGuidelines(CropImageView.Guidelines.ON)
//                .setMinCropWindowSize(500, 500)
//                .start(SettingsActivity.this);
    }

    public void uploadData(View view) {

        String name = nameedt.getText().toString();
        String mobile = mobileedt.getText().toString();
        String addressline1 = addressline1edt.getText().toString();
        String addressline2 = addressline2edt.getText().toString();
        String city = cityedt.getText().toString();
        String state = stateedt.getText().toString();
        String emailid = emailidedt.getText().toString();
        String alternate = alternateedt.getText().toString();
        String landlie = landlineedt.getText().toString();
        String device_token = FirebaseInstanceId.getInstance().getToken();



        Map<String,Object> userMap = new HashMap<>();
        userMap.put("name",name);
        userMap.put("mobile",mobile);
        userMap.put("addressline1",addressline1);
        userMap.put("addressline2",addressline2);
        userMap.put("city",city);
        userMap.put("state",state);
        userMap.put("emailid",emailid);
        userMap.put("alternate",alternate);
        userMap.put("landline",landlie);
        userMap.put("device_token",device_token);

        mRootRef.child(uid).setValue(userMap).addOnCompleteListener(new OnCompleteListener<Void>() {
            @Override
            public void onComplete(@NonNull Task<Void> task) {

            }
        });
    }
}
