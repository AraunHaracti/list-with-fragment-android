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

import java.util.ArrayList;
import java.util.Date;

public class ListFragment extends Fragment {

    ArrayList<Actor> states = new ArrayList<>();
    ListView actorsList;

    public ListFragment() {

    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public void onViewCreated(@NonNull View conn, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(conn, savedInstanceState);

        setInitialData();
        setInitList(conn);


    }

    private void setInitList(View conn) {
        actorsList = conn.findViewById(R.id.actors_list);

        ActorAdapter actorAdapter = new ActorAdapter(this.getContext(), R.layout.list_item, states);

        actorsList.setAdapter(actorAdapter);

        AdapterView.OnItemClickListener itemListener = (parent, v, position, id) -> {
            Actor selectedActor = (Actor)parent.getItemAtPosition(position);
            Toast.makeText(getContext(), "Был выбран пункт " + selectedActor.getFullName(),
                    Toast.LENGTH_SHORT).show();
        };
        actorsList.setOnItemClickListener(itemListener);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_list, container, false);
    }

    private void setInitialData(){

        states.add(new Actor ("Brad Pitt", new Date(1963, 12, 18), R.drawable.brad_pitt));
        states.add(new Actor ("Tom Cruise", new Date(1962, 7, 3), R.drawable.tom_cruise));
        states.add(new Actor ("Johnny Depp", new Date(1963, 6, 9), R.drawable.johnny_depp));
        states.add(new Actor ("Angelina Jolie", new Date(1975, 6, 4), R.drawable.angelina_jolie));

    }
}