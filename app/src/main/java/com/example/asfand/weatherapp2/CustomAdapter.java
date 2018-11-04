package com.example.asfand.weatherapp2;


import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * Created by Asfand on 14-Nov-17.
 */

public class CustomAdapter extends BaseAdapter {
    private ArrayList<dayrowshow> row;
    private LayoutInflater thisinflater;

    public CustomAdapter(Context context, ArrayList<dayrowshow> aRow) {

        this.row = aRow;
        thisinflater = ( LayoutInflater.from(context) );

    }

    @Override
    public int getCount() {
        return row.size();
    }

    @Override
    public Object getItem(int position) {
        return  row.get( position );

    }

    @Override
    public long getItemId(int position) {
        return position;

    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        if (convertView == null) {
            convertView = thisinflater.inflate( R.layout.dayslistviewlayout, parent, false );

            TextView dayt = (TextView) convertView.findViewById(R.id.daytext);
            TextView conditiont = (TextView) convertView.findViewById(R.id.conditionofday);
            TextView temperaturedayt =(TextView) convertView.findViewById(R.id.temperatureofday);

            ImageView conditionImage = (ImageView) convertView.findViewById(R.id.conditiondfayimage);

            dayrowshow currentRow = (dayrowshow) getItem(position);

            dayt.setText( currentRow.getDay() );
            conditiont.setText( currentRow.getConditionday() );
            temperaturedayt.setText(currentRow.getTemperatureofday());
            conditionImage.setImageResource(currentRow.getConditionimageofday() );

        }

        return convertView;
    }
}
