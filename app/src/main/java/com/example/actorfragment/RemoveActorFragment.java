package com.example.actorfragment;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.actorfragment.databinding.FragmentAddActorBinding;
import com.example.actorfragment.databinding.FragmentRemoveActorBinding;

import java.lang.reflect.Array;
import java.util.Arrays;
import java.util.List;

public class RemoveActorFragment extends Fragment {

    FragmentRemoveActorBinding binding;

    Actor selectedActor;

    public RemoveActorFragment(Actor selectedActor) {
        this.selectedActor = selectedActor;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding = FragmentRemoveActorBinding.inflate(inflater, container, false);
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        setData();
    }

    public void setData(){
        binding.selectedActorImage.setImageDrawable(selectedActor.getPhotoResource());
        binding.selectedActorFullName.setText(selectedActor.getFullName());
        binding.selectedActorDateOfBorn.setText(selectedActor.getDateOfBorn().toString());
    }

}