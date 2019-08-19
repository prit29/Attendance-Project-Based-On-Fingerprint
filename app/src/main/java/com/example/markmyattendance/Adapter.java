package com.example.markmyattendance;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

public class Adapter extends RecyclerView.Adapter<Adapter.progViewHolder> implements Filterable {

    private List<RecordClass> data;
    private List<RecordClass> data_dummy;

    public Adapter(List<RecordClass> data){
        this.data = data;
        data_dummy = new ArrayList<>(data);
    }

    @NonNull
    @Override
    public progViewHolder onCreateViewHolder(@NonNull final ViewGroup viewGroup, int i) {
        LayoutInflater l1 = LayoutInflater.from(viewGroup.getContext());
        View view = l1.inflate(R.layout.layers3,viewGroup,false);
        final progViewHolder view2 = new progViewHolder(view);
        return view2;
    }

    @Override
    public void onBindViewHolder(@NonNull progViewHolder progViewHolder, int position) {
        progViewHolder.t1.setText(data.get(position).getName());
        progViewHolder.t2.setText(data.get(position).getRoll());
        progViewHolder.t3.setText(data.get(position).getStd());
        progViewHolder.t4.setText(data.get(position).getLine());
        progViewHolder.t5.setText(data.get(position).getLect());
        progViewHolder.t6.setText(data.get(position).getStatus());
        progViewHolder.t7.setText(data.get(position).getTime());
    }


    @Override
    public int getItemCount() {
        return data.size();
    }

    public  void removeItem(int position) {
        data.remove(position);
        notifyItemRemoved(position);
    }

    @Override
    public Filter getFilter() {
        return examplefilter;
    }

    private Filter examplefilter = new Filter() {
        @Override
        protected FilterResults performFiltering(CharSequence constraint) {
            List<RecordClass> filtered = new ArrayList<>();

            if(constraint == null || constraint.length()==0){
                filtered.addAll(data_dummy);
            }else {
                String check = constraint.toString().toLowerCase().trim();

                for(RecordClass items:data_dummy){
                    if(items.getLect().toLowerCase().contains(check)){
                        filtered.add(items);
                    }
                }
            }

            FilterResults results = new FilterResults();
            results.values = filtered;

            return results;
        }

        @Override
        protected void publishResults(CharSequence constraint, FilterResults results) {
            data.clear();
            data.addAll((List) results.values);
            notifyDataSetChanged();
        }
    };

    public class progViewHolder extends RecyclerView.ViewHolder{

        TextView t1,t2,t3,t4,t5,t6,t7;

        public progViewHolder(View convertView){
            super(convertView);
            t1 = convertView.findViewById(R.id.t1);
            t2 = convertView.findViewById(R.id.t2);
            t3 = convertView.findViewById(R.id.t3);
            t4 = convertView.findViewById(R.id.t4);
            t5 = convertView.findViewById(R.id.t5);
            t6 = convertView.findViewById(R.id.t6);
            t7 = convertView.findViewById(R.id.t7);
        }
    }
}
