package com.perfecto.optimizer.activities;

import android.content.Context;
import android.database.Cursor;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.perfecto.optimizer.R;
import com.perfecto.optimizer.utils.Consts;
import com.perfecto.optimizer.utils.Record;
import com.perfecto.optimizer.utils.SQLHelper;
import com.squareup.picasso.Picasso;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by elishevak on 7/18/2016.
 */
public class ResultsAdapter extends RecyclerView.Adapter<ResultsAdapter.ViewHolder> {
    private List<Record> records;

    // Provide a reference to the views for each data item
    // Complex data items may need more than one view per item, and
    // you provide access to all the views for a data item in a view holder
    public static class ViewHolder extends RecyclerView.ViewHolder {
        // each data item is just a string in this case
        public View recordView;

        public ViewHolder(View v) {
            super(v);
            recordView = v;
        }
    }

    // Provide a suitable constructor (depends on the kind of dataset)
    public ResultsAdapter(SQLHelper helper, int top) throws IOException {
        Cursor cursor;
        if (top > 0) cursor = helper.getPartialReport(top);
        else cursor = helper.getReport();


        records = new ArrayList<>();

        if (cursor.moveToFirst()) {

            while (cursor.isAfterLast() == false) {
                Record record = new Record(
                        cursor.getDouble(cursor.getColumnIndex(Consts.Report.COLUMN_SHARE)),
                        cursor.getString(cursor.getColumnIndex(Consts.Report.COLUMN_IMAGE)),
                        cursor.getString(cursor.getColumnIndex(Consts.Report.COLUMN_NAME))
                );

                records.add(record);
                cursor.moveToNext();
            }
        }
    }

    // Create new views (invoked by the layout manager)
    @Override
    public ResultsAdapter.ViewHolder onCreateViewHolder(ViewGroup parent,
                                                        int viewType) {

        LayoutInflater layoutInflater = (LayoutInflater)
                parent.getContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);

        View v = layoutInflater.inflate(R.layout.record_view, parent, false);

        ViewHolder vh = new ViewHolder(v);
        return vh;
    }

    int getDefaultImage(Record record) {
        if (record.getDeviceName().contains("Apple")) {
            return (R.drawable.ios_phone);


        } else {
            return (R.drawable.android_phone);

        }
    }

    // Replace the contents of a view (invoked by the layout manager)
    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        // - get element from your dataset at this position
        // - replace the contents of the view with that element
        Record record = records.get(position);

        TextView recordIndex = (TextView) holder.recordView.findViewById(R.id.record_index);
        recordIndex.setText(String.valueOf(position + 1));

        ImageView deviceImage = (ImageView) holder.recordView.findViewById(R.id.device_image);
        Picasso.with(holder.itemView.getContext()).load(record.getImage()).error(getDefaultImage(record)).into(deviceImage);

        TextView deviceName = (TextView) holder.recordView.findViewById(R.id.device_name);
        deviceName.setText(record.getDeviceName());


    }

    // Return the size of your dataset (invoked by the layout manager)
    @Override
    public int getItemCount() {
        return records.size();
    }
}