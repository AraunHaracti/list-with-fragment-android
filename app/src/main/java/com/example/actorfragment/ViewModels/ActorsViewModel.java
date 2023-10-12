package com.example.actorfragment.ViewModels;

import android.app.Application;

import androidx.core.content.res.ResourcesCompat;
import androidx.lifecycle.AndroidViewModel;

import com.example.actorfragment.R;
import com.example.actorfragment.entities.Actor;

import java.util.ArrayList;
import java.util.Date;

public class ActorsViewModel extends AndroidViewModel {
    private final ArrayList<Actor> actors = new ArrayList<>();

    public ActorsViewModel(Application app){
        super(app);
        actors.add(new Actor ("Brad Pitt", new Date(63, 12, 18), ResourcesCompat.getDrawable(app.getResources(), R.drawable.brad_pitt,null)));
        actors.add(new Actor ("Tom Cruise", new Date(62, 7, 3), ResourcesCompat.getDrawable(app.getResources(), R.drawable.tom_cruise,null)));
        actors.add(new Actor ("Johnny Depp", new Date(63, 6, 9), ResourcesCompat.getDrawable(app.getResources(), R.drawable.johnny_depp,null)));
        actors.add(new Actor ("Angelina Jolie", new Date(75, 6, 4), ResourcesCompat.getDrawable(app.getResources(), R.drawable.angelina_jolie,null)));
        actors.add(new Actor ("Ryan Reynolds", new Date(76, 10, 23), ResourcesCompat.getDrawable(app.getResources(), R.drawable.ryan_reynolds,null)));
        actors.add(new Actor ("Ryan Gosling", new Date(80, 11, 12), ResourcesCompat.getDrawable(app.getResources(), R.drawable.ryan_gosling,null)));
    }

    public ArrayList<Actor> getData(){
        return actors;
    }

    public void addData(Actor actor){
        actors.add(actor);
    }

    public void removeData(int id){
        actors.remove(id);
    }
}
