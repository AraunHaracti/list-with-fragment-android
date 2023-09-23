package com.example.actorfragment.fragments;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.text.format.DateUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.DatePicker;

import com.example.actorfragment.entities.Actor;
import com.example.actorfragment.databinding.FragmentAddActorBinding;
import com.github.dhaval2404.imagepicker.ImagePicker;

import java.util.Calendar;
import java.util.Date;

public class AddActorFragment extends Fragment {

    FragmentAddActorBinding binding;

    public AddActorFragment() {}

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

        commitBtnInit();
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

        binding.photoBtn.setImageURI(data.getData());

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
        binding.dateView.setText(DateUtils.formatDateTime(getContext(),
                dateAndTime.getTimeInMillis(),
                DateUtils.FORMAT_SHOW_DATE | DateUtils.FORMAT_SHOW_YEAR));
    }

    private void commitBtnInit(){
        binding.commitBtn.setOnClickListener((v)->{
            String fullName = binding.userFullName.getText().toString();
            Date dateOfBorn = dateAndTime.getTime();
            Drawable photo = binding.photoBtn.getDrawable();
            Actor actor = new Actor(fullName, dateOfBorn, photo);

            ListFragment.actors.add(actor);

            getActivity().getSupportFragmentManager().popBackStack();
        });
    }
}