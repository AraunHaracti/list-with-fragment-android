package com.example.actorfragment.adapters;

import android.content.Context;
import android.text.format.DateUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.actorfragment.R;
import com.example.actorfragment.entities.Actor;

import java.util.List;

public class ActorAdapter extends ArrayAdapter<Actor> {
    private final LayoutInflater inflater;
    private final int layout;
    private final List<Actor> actors;

    public ActorAdapter(Context context, int resource, List<Actor> actors) {
        super(context, resource, actors);

        this.actors = actors;
        this.layout = resource;
        this.inflater = LayoutInflater.from(context);
    }

    public View getView(int position, View convertView, ViewGroup parent) {

        ViewHolder viewHolder;

        if (convertView == null) {
            convertView = inflater.inflate(this.layout, parent, false);
            viewHolder = new ViewHolder(convertView);
            convertView.setTag(viewHolder);
        }
        else{
            viewHolder = (ViewHolder) convertView.getTag();
        }

        Actor actor = actors.get(position);

        viewHolder.photoView.setImageDrawable(actor.getPhotoResource());
        viewHolder.fullNameView.setText(actor.getFullName());
        viewHolder.dateOfBornView.setText(DateUtils.formatDateTime(getContext(),
                actor.getDateOfBorn().getTime(),
                DateUtils.FORMAT_SHOW_DATE | DateUtils.FORMAT_SHOW_YEAR));

        return convertView;
    }

    private class ViewHolder {
        final ImageView photoView;
        final TextView fullNameView, dateOfBornView;
        ViewHolder(View view){
            photoView = view.findViewById(R.id.item_list_photo);
            fullNameView = view.findViewById(R.id.item_list_full_name);
            dateOfBornView = view.findViewById(R.id.item_list_date_of_born);
        }
    }
}
