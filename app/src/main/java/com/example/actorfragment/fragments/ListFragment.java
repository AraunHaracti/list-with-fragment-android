package com.example.actorfragment.fragments;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;

import com.example.actorfragment.ViewModels.ActorsViewModel;
import com.example.actorfragment.entities.Actor;
import com.example.actorfragment.adapters.ActorAdapter;
import com.example.actorfragment.R;
import com.example.actorfragment.databinding.FragmentListBinding;

import java.util.ArrayList;
import java.util.List;

public class ListFragment extends Fragment {

    FragmentListBinding binding;

    public ActorsViewModel actorsViewModel;
    public ListView actorsList;

    public ListFragment() {
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);


        Thread load = new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    binding.loadBar.post(() -> binding.loadBar.setVisibility(View.VISIBLE));
                    binding.actorsList.post(() -> binding.actorsList.setVisibility(View.GONE));
                    Thread.sleep(3000);
                    binding.loadBar.post(() -> binding.loadBar.setVisibility(View.GONE));
                    binding.actorsList.post(() -> binding.actorsList.setVisibility(View.VISIBLE));
                } catch (InterruptedException e){
                    e.printStackTrace();
                }
            }
        });

        load.start();

        actorsViewModel = new ViewModelProvider(getActivity()).get(ActorsViewModel.class);

        setInitList(view);
        addUserBtnInit();
    }

    private void setInitList(View view) {
        actorsList = binding.actorsList;

        ActorAdapter actorAdapter = new ActorAdapter(this.getContext(), R.layout.list_item, actorsViewModel.getData());

        actorsList.setAdapter(actorAdapter);

        AdapterView.OnItemClickListener itemAddListener = (parent, v, position, id) -> {
            Actor selectedActor = (Actor)parent.getItemAtPosition(position);

            List<Actor> actorList = new ArrayList<Actor>();
            actorList.add(selectedActor);

            ActorInfoFragment actorInfoFragment = ActorInfoFragment.getInstance(actorList);

            getActivity().getSupportFragmentManager().beginTransaction()
                    .replace(R.id.main_fragment, actorInfoFragment, "info_actor")
                    .addToBackStack(null)
                    .commit();
        };
        actorsList.setOnItemClickListener(itemAddListener);

        AdapterView.OnItemLongClickListener itemDeleteListener = (parent, v, position, id) -> {
            Actor selectedActor = (Actor)parent.getItemAtPosition(position);

            getActivity().getSupportFragmentManager().beginTransaction()
                    .replace(R.id.main_fragment, new RemoveActorFragment(selectedActor, id),"remove_actor")
                    .addToBackStack(null)
                    .commit();
            return false;
        };

        actorsList.setOnItemLongClickListener(itemDeleteListener);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding = FragmentListBinding.inflate(inflater, container, false);
        return binding.getRoot();
    }

    private void addUserBtnInit(){
        binding.addUserBtn.setOnClickListener((view)->{
            getActivity().getSupportFragmentManager().beginTransaction()
                    .replace(R.id.main_fragment, new AddActorFragment(),"add_actor")
                    .addToBackStack(null)
                    .commit();
        });
    }
}