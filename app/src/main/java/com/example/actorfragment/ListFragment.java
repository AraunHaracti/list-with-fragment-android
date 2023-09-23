package com.example.actorfragment;

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

import com.example.actorfragment.databinding.FragmentListBinding;

import java.util.ArrayList;
import java.util.Date;

public class ListFragment extends Fragment {

    FragmentListBinding binding;

    public static ArrayList<Actor> actors = new ArrayList<>();
    public ListView actorsList;

    public ListFragment() {

    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setInitialData();
    }

    @Override
    public void onViewCreated(@NonNull View conn, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(conn, savedInstanceState);

        setInitList(conn);
        addUserBtnInit();
    }

    private void setInitList(View conn) {
        actorsList = binding.actorsList;

        ActorAdapter actorAdapter = new ActorAdapter(this.getContext(), R.layout.list_item, actors);

        actorsList.setAdapter(actorAdapter);

        AdapterView.OnItemClickListener itemAddListener = (parent, v, position, id) -> {
            Actor selectedActor = (Actor)parent.getItemAtPosition(position);
            Toast.makeText(getContext(), "Был выбран пункт " + selectedActor.getFullName(),
                    Toast.LENGTH_SHORT).show();
        };
        actorsList.setOnItemClickListener(itemAddListener);

        AdapterView.OnItemLongClickListener itemDeleteListener = (parent, v, position, id) -> {
            Actor selectedActor = (Actor)parent.getItemAtPosition(position);

            getActivity().getSupportFragmentManager().beginTransaction()
                    .replace(R.id.main_fragment, new RemoveActorFragment(selectedActor),"remove_actor")
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

        actors.add(new Actor ("Brad Pitt", new Date(1963, 12, 18), getResources().getDrawable(R.drawable.brad_pitt)));
        actors.add(new Actor ("Tom Cruise", new Date(1962, 7, 3), getResources().getDrawable(R.drawable.tom_cruise)));
        actors.add(new Actor ("Johnny Depp", new Date(1963, 6, 9), getResources().getDrawable(R.drawable.johnny_depp)));
        actors.add(new Actor ("Angelina Jolie", new Date(1975, 6, 4), getResources().getDrawable(R.drawable.angelina_jolie)));
        actors.add(new Actor ("Ryan Reynolds", new Date(1976, 10, 23), getResources().getDrawable(R.drawable.ryan_reynolds)));
        actors.add(new Actor ("Ryan Gosling", new Date(1980, 11, 12), getResources().getDrawable(R.drawable.ryan_gosling)));

    }
}