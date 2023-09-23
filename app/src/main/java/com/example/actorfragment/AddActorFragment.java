package com.example.actorfragment;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.content.res.Resources;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.media.Image;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.core.content.res.ResourcesCompat;
import androidx.fragment.app.Fragment;

import android.text.format.DateUtils;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.DatePicker;
import android.widget.Toast;

import com.example.actorfragment.databinding.FragmentAddActorBinding;
import com.github.dhaval2404.imagepicker.ImagePicker;

import java.util.Calendar;
import java.util.Date;

public class AddActorFragment extends Fragment {

    FragmentAddActorBinding binding;


    public AddActorFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding = FragmentAddActorBinding.inflate(inflater, container, false);
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        initCommitBtn();
        setChangerDate();
        addPhotoBtn();
    }

    private void addPhotoBtn(){
        binding.photoBtn.setOnClickListener((v) -> {
            ImagePicker.with(this).start();
        });
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        binding.photo.setImageURI(data.getData());

    }

    Calendar dateAndTime=Calendar.getInstance();

    DatePickerDialog.OnDateSetListener d=new DatePickerDialog.OnDateSetListener() {
        public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
            dateAndTime.set(Calendar.YEAR, year);
            dateAndTime.set(Calendar.MONTH, monthOfYear);
            dateAndTime.set(Calendar.DAY_OF_MONTH, dayOfMonth);
            setInitialDateTime();
        }
    };

    public void setChangerDate() {
        binding.dateBtn.setOnClickListener((v) -> {
            new DatePickerDialog(binding.getRoot().getContext(), d,
                    dateAndTime.get(Calendar.YEAR),
                    dateAndTime.get(Calendar.MONTH),
                    dateAndTime.get(Calendar.DAY_OF_MONTH))
                    .show();
        });
    }

    private void setInitialDateTime() {
        binding.dateBtn.setText(DateUtils.formatDateTime(getContext(),
                dateAndTime.getTimeInMillis(),
                DateUtils.FORMAT_SHOW_DATE | DateUtils.FORMAT_SHOW_YEAR));
    }

    private void initCommitBtn(){
        binding.commitBtn.setOnClickListener((v)->{
            String fullName = binding.userFullName.getText().toString();
            Date dateOfBorn = dateAndTime.getTime();
            Drawable photo = binding.photo.getDrawable();
            Actor actor = new Actor(fullName, dateOfBorn, photo);

            ListFragment.actors.add(actor);

            getActivity().getSupportFragmentManager().popBackStack();
        });
    }
}