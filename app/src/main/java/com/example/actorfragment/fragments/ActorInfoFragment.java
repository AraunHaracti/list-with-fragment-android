package com.example.actorfragment.fragments;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.text.format.DateUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.actorfragment.databinding.FragmentActorInfoBinding;
import com.example.actorfragment.entities.Actor;

import java.io.Serializable;
import java.util.List;

public class ActorInfoFragment extends Fragment {

    private static final String ARG_ACTOR = "actor";
    private List<Actor> actors;

    FragmentActorInfoBinding fragmentActorInfoBinding;

    public static ActorInfoFragment getInstance(List<Actor> actors){
        ActorInfoFragment actorInfoFragment = new ActorInfoFragment();
        Bundle args = new Bundle();
        args.putSerializable(ARG_ACTOR, (Serializable) actors);
        actorInfoFragment.setArguments(args);
        return actorInfoFragment;
    }

    public ActorInfoFragment() {}

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            actors = (List<Actor>) getArguments().getSerializable(ARG_ACTOR);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        fragmentActorInfoBinding = FragmentActorInfoBinding.inflate(inflater, container, false);
        return fragmentActorInfoBinding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        setData();
    }

    private void setData(){
        if(actors == null) return;

        Actor actor = actors.get(0);

        fragmentActorInfoBinding.itemPhotoIndividualFragment.setImageDrawable(actor.getPhotoResource());
        fragmentActorInfoBinding.itemFullNameIndividualFragment.setText(actor.getFullName());
        fragmentActorInfoBinding.itemDateOfBornIndividualFragment.setText(DateUtils.formatDateTime(getContext(),
                actor.getDateOfBorn().getTime(),
                DateUtils.FORMAT_SHOW_DATE | DateUtils.FORMAT_SHOW_YEAR));
    }
}
