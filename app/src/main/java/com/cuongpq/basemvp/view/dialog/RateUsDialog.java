package com.cuongpq.basemvp.view.dialog;

import android.app.Dialog;
import android.content.Context;
import android.database.Cursor;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.ScaleAnimation;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.databinding.DataBindingUtil;

import com.cuongpq.basemvp.R;
import com.cuongpq.basemvp.databinding.RateUsDialogLayoutBinding;
import com.cuongpq.basemvp.model.Member;
import com.cuongpq.basemvp.service.sqlite.SQLiteHelper;
import com.cuongpq.basemvp.view.ui.fragment.race.raceinfor.RaceInformationFragment;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class RateUsDialog extends Dialog {

    private RateUsDialogLayoutBinding binding;
    private float userRate = 0;
    private FirebaseUser firebaseUser;
    private SQLiteHelper sqLiteHelper;
    private String idAcount;
    private Member member;
    private FirebaseDatabase firebaseDatabase;
    private DatabaseReference databaseReference;

    public RateUsDialog(@NonNull Context context) {
        super(context);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.inflate(LayoutInflater.from(getContext()), R.layout.rate_us_dialog_layout, null, false);
        setContentView(binding.getRoot());
        sqLiteHelper = new SQLiteHelper(getContext(),"Data.sqlite",null,5);
        firebaseUser = FirebaseAuth.getInstance().getCurrentUser();
        idAcount = firebaseUser.getUid();
        firebaseDatabase = FirebaseDatabase.getInstance();
        getRate();
        onClick();
    }
    private void onClick(){
        binding.ratingBar.setOnRatingBarChangeListener(new RatingBar.OnRatingBarChangeListener() {
            @Override
            public void onRatingChanged(RatingBar ratingBar, float rating, boolean fromUser) {
                       if(rating <= 1){
                           binding.imgStatus.setImageResource(R.drawable.one_star);
                       }else if(rating <= 2){
                           binding.imgStatus.setImageResource(R.drawable.two_star);
                       }else if(rating <= 3){
                           binding.imgStatus.setImageResource(R.drawable.three_star);
                       }else if(rating <= 4){
                           binding.imgStatus.setImageResource(R.drawable.four_star);
                       }else if(rating <= 5){
                           binding.imgStatus.setImageResource(R.drawable.five_star);
                       }
                       animateImage(binding.imgStatus);
                       userRate = rating;
            }
        });
        binding.btnRateNow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String rateStr;
                rateStr = String.valueOf(userRate);
                sqLiteHelper.QueryData("UPDATE User1 SET Rate = '"+rateStr+"' WHERE Email = '"+member.getMemberAccount()+"'");
                firebaseDatabase.getReference().child(idAcount).child("rate").setValue(userRate);
                dismiss();
                Toast.makeText(getContext(),"Ok",Toast.LENGTH_SHORT).show();
            }
        });
        binding.btnLater.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dismiss();
            }
        });
    }
    private void animateImage(ImageView ratingImage){
        ScaleAnimation scaleAnimation = new ScaleAnimation(0,1f,0,1f,
                Animation.RELATIVE_TO_SELF,0.5f,Animation.RELATIVE_TO_SELF,0.5f);
        scaleAnimation.setFillAfter(true);
        scaleAnimation.setDuration(200);
        binding.imgStatus.setAnimation(scaleAnimation);
    }
    private void getRate(){
    /*    String strRate;
        Cursor data = sqLiteHelper.GetData("SELECT * FROM User1 WHERE Email = '"+member.getMemberAccount()+"'");
        while (data.moveToNext()){
            strRate = data.getString(5);
            userRate = Float.parseFloat(strRate);
        }
        binding.ratingBar.setRating(userRate); */
   //       binding.ratingBar.setRating(member.getRate());

        databaseReference = firebaseDatabase.getReference(idAcount);
        databaseReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                member = snapshot.getValue(Member.class);
                binding.ratingBar.setRating(member.getRate());
            }
            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }
}
