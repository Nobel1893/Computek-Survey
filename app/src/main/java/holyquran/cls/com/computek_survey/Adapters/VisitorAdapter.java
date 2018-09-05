package holyquran.cls.com.computek_survey.Adapters;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

import holyquran.cls.com.computek_survey.Model.Visitor;
import holyquran.cls.com.computek_survey.R;

/**
 * Created by CLS on 8/27/2018.
 */

public class VisitorAdapter extends RecyclerView.Adapter<VisitorAdapter.ViewHolder>{
    List<Visitor> visitors;
    OnItemClickListener onItemClickListener;


    public void setOnItemClickListener(OnItemClickListener onItemClickListener) {
        this.onItemClickListener = onItemClickListener;
    }

    public VisitorAdapter(List<Visitor> visitors) {
        this.visitors = visitors;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.visitoritem ,null);
        ViewHolder viewHolder =new ViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, final int position) {

        Visitor visitor = visitors.get(position);
        holder.name.setText(visitor.getName());
        holder.phone.setText(visitor.getPhone());

        holder.parent.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (onItemClickListener!=null)
                    onItemClickListener.onItemClick(visitors.get(position),position);
            }
        });
    }

    @Override
    public int getItemCount() {
        return visitors.size();
    }

    static class ViewHolder extends RecyclerView.ViewHolder{

        TextView name;
        TextView phone;
        View parent;
        public ViewHolder(View cardView){
            super(cardView);
            name=cardView.findViewById(R.id.name);
            phone=cardView.findViewById(R.id.phone);
            parent=cardView.findViewById(R.id.parent);

        }



    }

    public static interface OnItemClickListener{
        void onItemClick(Visitor visitor, int position);
    }

}








