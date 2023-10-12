package com.example.actorfragment.fragments;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import android.text.format.DateUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.actorfragment.ViewModels.ActorsViewModel;
import com.example.actorfragment.entities.Actor;
import com.example.actorfragment.databinding.FragmentRemoveActorBinding;

public class RemoveActorFragment extends Fragment {

    FragmentRemoveActorBinding binding;

    public ActorsViewModel actorsViewModel;

    Actor selectedActor;
    long selectedActorId;

    public RemoveActorFragment(Actor selectedActor, long selectedActorId) {
        this.selectedActor = selectedActor;
        this.selectedActorId = selectedActorId;
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

        actorsViewModel = new ViewModelProvider(getActivity()).get(ActorsViewModel.class);

        setData();
        agreeBtnInit();
        disagreeBtnInit();
    }

    private void agreeBtnInit(){
        binding.selectedAgreeBtn.setOnClickListener(view -> {

            actorsViewModel.removeData((int) selectedActorId);

            getActivity().getSupportFragmentManager().popBackStack();
        });
    }

    private void disagreeBtnInit(){
        binding.selectedDisagreeBtn.setOnClickListener(view -> {
            getActivity().getSupportFragmentManager().popBackStack();
        });
    }

    public void setData(){
        binding.selectedActorImage.setImageDrawable(selectedActor.getPhotoResource());
        binding.selectedActorFullName.setText(selectedActor.getFullName());
        binding.selectedActorDateOfBorn.setText(DateUtils.formatDateTime(getContext(),
                selectedActor.getDateOfBorn().getTime(),
                DateUtils.FORMAT_SHOW_DATE | DateUtils.FORMAT_SHOW_YEAR)    );
    }

}