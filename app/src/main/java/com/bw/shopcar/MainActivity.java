package com.bw.shopcar;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.TextView;
import android.widget.Toast;

import com.bw.adapter.CartAdapter;
import com.bw.bean.CartBean;
import com.bw.bean.Result;
import com.bw.core.DataCall;
import com.bw.presenter.CartPresenter;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MainActivity extends AppCompatActivity {


    @BindView(R.id.cart_recy)
    RecyclerView cartRecy;
    @BindView(R.id.cart_checkAll)
    CheckBox cartCheckAll;
    @BindView(R.id.cart_total)
    TextView cartTotal;
    @BindView(R.id.cart_btn_pay)
    Button cartBtnPay;
    private CartAdapter cartAdapter;
    private List<CartBean> result;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        initData();
    }

    private void initData() {
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(MainActivity.this);
        linearLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        cartRecy.setLayoutManager(linearLayoutManager);

        cartAdapter = new CartAdapter(MainActivity.this);
        cartRecy.setAdapter(cartAdapter);

        CartPresenter cartPresenter = new CartPresenter(new CartCall());
        cartPresenter.reqeust(256, "1551947519324256");
    }

    class CartCall implements DataCall<Result<List<CartBean>>> {

        @Override
        public void onSuccess(Result<List<CartBean>> data) {
            if (data.getStatus().equals("0000")) {
                Toast.makeText(MainActivity.this, data.getMessage(), Toast.LENGTH_SHORT).show();
                result = data.getResult();
                //若选中全选的复选框，单个的复选框也要被选择中
                cartCheckAll.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        for (int i = 0; i < result.size(); i++) {
                            result.get(i).setFlag(cartCheckAll.isChecked());
                        }
                        //适配器需刷新
                        cartAdapter.notifyDataSetChanged();
                        //总价许多要跟随着改变
                        cartTotal.setText("¥：" + getPrice(result));
                    }
                });
                /**
                 * 接口回调
                 *
                 */
                cartAdapter.getOnClick(new CartAdapter.SumAll() {
                    @Override
                    public void onSumAll(List<CartBean> list) {
                        cartTotal.setText("¥："+getPrice(result));
                    }

                });

                cartAdapter.getSelectAll(new CartAdapter.SecletAll() {
                    @Override
                    public void onSelectAll(List<CartBean> list) {
                        boolean falg=true;
                        for (CartBean cartBean : list) {
                            if (!cartBean.isFlag()){
                                falg=false;
                            }
                        }
                        cartTotal.setText("¥："+getPrice(result));
                        cartCheckAll.setChecked(falg);
                    }
                });

                cartAdapter.addAll(data.getResult());
                cartAdapter.notifyDataSetChanged();
            }
        }

        @Override
        public void onFail(Throwable throwable) {
            Toast.makeText(MainActivity.this, "查询失败", Toast.LENGTH_SHORT).show();

        }
    }

    //总价
    public double getPrice(List<CartBean> list) {
        //初始化总价
        double total = 0.0;
        for (int i = 0; i < list.size(); i++) {
            if (list.get(i).isFlag()) {//如果单个复选框被选中了，总价跟着改变
                total += list.get(i).getPrice() * list.get(i).getCount();
            }

        }
        return total;

    }

}
