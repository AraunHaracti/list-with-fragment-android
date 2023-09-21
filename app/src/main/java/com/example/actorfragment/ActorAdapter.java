package com.example.actorfragment;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

public class ActorAdapter extends ArrayAdapter<Actor> {
    private LayoutInflater inflater;
    private int layout;
    private List<Actor> actors;

    public ActorAdapter(Context context, int resource, List<Actor> actors) {
        super(context, resource, actors);
        this.actors = actors;
        this.layout = resource;
        this.inflater = LayoutInflater.from(context);
    }

    public View getView(int position, View convertView, ViewGroup parent) {

        View view = inflater.inflate(this.layout, parent, false);

        ImageView photoView = view.findViewById(R.id.photo);
        TextView fullNameView = view.findViewById(R.id.full_name);
        TextView dateOfBornView = view.findViewById(R.id.date_of_born);

        Actor actor = actors.get(position);

        photoView.setImageResource(actor.getPhotoResource());
        fullNameView.setText(actor.getFullName());
        dateOfBornView.setText(actor.getDateOfBorn().toString());

        return view;
    }
}
