package com.bw.adapter;

import android.content.Context;
import android.net.Uri;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.TextView;
import android.widget.Toast;

import com.bw.bean.CartBean;
import com.bw.shopcar.R;
import com.facebook.drawee.view.SimpleDraweeView;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by 1607c王晴
 * date 2019/3/7
 * Describe:
 */
public class CartAdapter extends RecyclerView.Adapter<CartAdapter.ViewHolder> {
    private Context context;
    private List<CartBean> list;
    private SecletAll secletAll;
    private SumAll sumAll;

    public CartAdapter(Context context) {
        this.context = context;
        list = new ArrayList<>();
    }

    public void addAll(List<CartBean> result) {
        if (result != null) {
            list.addAll(result);
        }
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(context).inflate(R.layout.cart_item, null, false);
        ViewHolder viewHolder = new ViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull final ViewHolder viewHolder, final int i) {
        viewHolder.cart_item_img.setImageURI(Uri.parse(list.get(i).getPic()));
        viewHolder.cart_item_title.setText(list.get(i).getCommodityName());
        viewHolder.cart_item_price.setText("¥：" + list.get(i).getPrice());
        viewHolder.cart_item_jian.setText("－");
        viewHolder.cart_item_num.setText("" + list.get(i).getCount());
        viewHolder.cart_item_jia.setText("＋");
        viewHolder.cart_item_cb.setChecked(list.get(i).isFlag());//设置CheckBox
        viewHolder.cart_item_cb.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                list.get(i).setFlag(viewHolder.cart_item_cb.isChecked());
                secletAll.onSelectAll(list);
            }
        });
        viewHolder.cart_item_jian.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (list.get(i).getCount()>1){
                    list.get(i).setCount(list.get(i).getCount()-1);
                    viewHolder.cart_item_num.setText(""+list.get(i).getCount());
                    sumAll.onSumAll(list);
                }else {
                    Toast.makeText(context, "亲，不能再少啦！！！", Toast.LENGTH_SHORT).show();
                }
            }
        });

        viewHolder.cart_item_jia.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int count1 = list.get(i).getCount();
               list.get(i).setCount(count1+1);
               viewHolder.cart_item_num.setText(list.get(i).getCount()+"");
               sumAll.onSumAll(list);
            }
        });

    }

    @Override
    public int getItemCount() {
        return list.size();
    }


    public class ViewHolder extends RecyclerView.ViewHolder {
        private final CheckBox cart_item_cb;
        private final SimpleDraweeView cart_item_img;
        private final TextView cart_item_title, cart_item_price,
                cart_item_jian, cart_item_num, cart_item_jia;


        public ViewHolder(@NonNull View itemView) {

            super(itemView);
            cart_item_cb = itemView.findViewById(R.id.cart_item_cb);
            cart_item_img = itemView.findViewById(R.id.cart_item_img);
            cart_item_title = itemView.findViewById(R.id.cart_item_title);
            cart_item_price = itemView.findViewById(R.id.cart_item_price);
            cart_item_jian = itemView.findViewById(R.id.cart_item_jian);
            cart_item_num = itemView.findViewById(R.id.cart_item_num);
            cart_item_jia = itemView.findViewById(R.id.cart_item_jia);

        }
    }

    public void getOnClick(SumAll sumAll){
        this.sumAll=sumAll;
    }

    public interface SumAll{
        void onSumAll(List<CartBean> list);
    }

    public void getSelectAll(SecletAll secletAll){
        this.secletAll=secletAll;
    }
    public interface SecletAll{
        void onSelectAll(List<CartBean> list);
    }
}
