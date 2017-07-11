package com.educareapps.tourism;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.educareapps.mylibrary.ImageProcessing;



/**
 * TaskPackAdapter
 * Created by ibrar on 6/22/2016.
 */
public class AdapterTourPackage extends BaseAdapter {

    Context context;
    ImageProcessing imageProcessing;
    int[] tourPic;
    String[] tourTitle;
    String[] duration;
    String[] detail;

    LayoutInflater inflater;

    public AdapterTourPackage(Context context, int[] tourPic, String[] tourTitle, String[] duration, String[] detail ){
        this.context = context;
        this.tourPic = tourPic;
        this.tourTitle = tourTitle;
        this.duration = duration;
        this.detail = detail;
        imageProcessing = new ImageProcessing(context);
        inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }


    @Override
    public int getCount() {
        return tourPic.length;
    }

    @Override
    public Object getItem(int position) {
        return null;
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    static class ViewHolder {

        public ImageView ivGridItem;
        public TextView tvTitle, tvDuration, tvDetail;

    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder holder = null;
        if (convertView == null) {
            holder = new ViewHolder();
            convertView = inflater.inflate(R.layout.cell_tour_package, null);
            holder.ivGridItem = (ImageView) convertView.findViewById(R.id.ivtourPackPic);
            holder.tvTitle = (TextView) convertView.findViewById(R.id.tvTitle);
            holder.tvDuration = (TextView) convertView.findViewById(R.id.tvDuration);
            holder.tvDetail = (TextView) convertView.findViewById(R.id.tvDetail);
            convertView.setTag(holder);

        } else {
            holder = (ViewHolder) convertView.getTag();
        }

        holder.ivGridItem.setImageResource(tourPic[position]);
        holder.tvTitle.setText(tourTitle[position]);
        holder.tvDuration.setText(duration[position]);
        holder.tvDetail.setText(detail[position]);

        //imageProcessing.setImageWith_loader(holder.ivGridItem, tasks.get(position).getTaskImage() );
        //holder.ivGridItem.setImageBitmap(imageProcessing.getImage(tasks.get(position).getTaskImage()));

        return convertView;
    }
}
