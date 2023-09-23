package com.example.actorfragment.fragments;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import com.example.actorfragment.Options;
import com.example.actorfragment.entities.Actor;
import com.example.actorfragment.adapters.ActorAdapter;
import com.example.actorfragment.R;
import com.example.actorfragment.databinding.FragmentListBinding;

import java.util.ArrayList;
import java.util.Date;

public class ListFragment extends Fragment {

    FragmentListBinding binding;
    public static ArrayList<Actor> actors = new ArrayList<>();
    public ListView actorsList;

    public ListFragment() {}

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        setInitialData();
        setInitList(view);
        addUserBtnInit();
    }

    private void setInitList(View view) {
        actorsList = binding.actorsList;

        ActorAdapter actorAdapter = new ActorAdapter(this.getContext(), R.layout.list_item, actors);

        actorsList.setAdapter(actorAdapter);

//        AdapterView.OnItemClickListener itemAddListener = (parent, v, position, id) -> {
//            Actor selectedActor = (Actor)parent.getItemAtPosition(position);
//            Toast.makeText(getContext(), "Был выбран пункт " + selectedActor.getFullName(),
//                    Toast.LENGTH_SHORT).show();
//        };
//        actorsList.setOnItemClickListener(itemAddListener);

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

    private void setInitialData(){

        if (!Options.isFirstInit) {
            actors.add(new Actor ("Brad Pitt", new Date(63, 12, 18), getResources().getDrawable(R.drawable.brad_pitt)));
            actors.add(new Actor ("Tom Cruise", new Date(62, 7, 3), getResources().getDrawable(R.drawable.tom_cruise)));
            actors.add(new Actor ("Johnny Depp", new Date(63, 6, 9), getResources().getDrawable(R.drawable.johnny_depp)));
            actors.add(new Actor ("Angelina Jolie", new Date(75, 6, 4), getResources().getDrawable(R.drawable.angelina_jolie)));
            actors.add(new Actor ("Ryan Reynolds", new Date(76, 10, 23), getResources().getDrawable(R.drawable.ryan_reynolds)));
            actors.add(new Actor ("Ryan Gosling", new Date(80, 11, 12), getResources().getDrawable(R.drawable.ryan_gosling)));

            Options.isFirstInit = !Options.isFirstInit;
        }
    }
}