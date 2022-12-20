package com.applazada.Activity;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewpager2.widget.ViewPager2;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.graphics.PorterDuff;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.applazada.Adapter.CateAdapter;
import com.applazada.Adapter.sliderAdapter;
import com.applazada.Fragment.SliderFragment;
import com.applazada.Models.Category;
import com.applazada.Models.Photo;
import com.applazada.R;
import com.google.android.material.bottomnavigation.BottomNavigationItemView;
import com.google.android.material.bottomnavigation.BottomNavigationMenuView;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.navigation.NavigationBarView;

import java.util.ArrayList;
import java.util.List;
import java.util.TimerTask;

import me.relex.circleindicator.CircleIndicator;
import me.relex.circleindicator.CircleIndicator2;
import me.relex.circleindicator.CircleIndicator3;

public class HomeActivity extends AppCompatActivity {
    private RecyclerView rc_cate;

    private ViewPager2 v2_slider;
    private TextView txt_searched;
    private BottomNavigationView bottom_nav;
    List<Photo> photoList;
    List<Category> categoryList;
    List<String> history_search;
    int i;
    Animation text_animation_move_in,text_animation_move_out;
    private CircleIndicator3 slier_control;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        Init();
        setSliderData();
        setHistorySearchData();
        setCategoryData();
        setNotifyBottomNav();
        txt_searched.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                scan(v);
            }
        });

        java.util.Timer timer = new java.util.Timer();
        timer.scheduleAtFixedRate(new The_slide_timer(),4000,3000);
        timer.scheduleAtFixedRate(new The_searched_timer(),5000,3000);
        bottom_nav.setOnItemSelectedListener(new NavigationBarView.OnItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                switch (item.getItemId())
                {
                    case R.id.nav_home:
                        setHomeAction();
                        break;
                    case R.id.nav_shopping:
                        setShoppingAction();
                        break;
                    case R.id.nav_mess:
                        break;
                    case R.id.nav_cart:
                        break;
                    case R.id.nav_account:
                        break;
                    default:
                        break;
                }
                return true;
            }

            private void setShoppingAction() {
                startActivity( new Intent(HomeActivity.this, ShoppingActivity.class));
            }

            private void setHomeAction() {
                bottom_nav.getMenu().findItem(R.id.nav_home).setChecked(true);
                bottom_nav.getMenu().findItem(R.id.nav_home).setIcon(getDrawable(R.drawable.ic_home_heart));
                bottom_nav.getMenu().findItem(R.id.nav_home).setTitle("Cho bạn");

            }
        });
    }

    private void setCategoryData() {
        categoryList = getListCategory();
        RecyclerView.LayoutManager layoutManager = new GridLayoutManager(this,2,GridLayoutManager.HORIZONTAL, false);
        CateAdapter cateAdapter = new CateAdapter(categoryList);
        rc_cate.setAdapter(cateAdapter);
        rc_cate.setLayoutManager(layoutManager);
        rc_cate.setHasFixedSize(true);
    }



    private void setNotifyBottomNav() {
       BottomNavigationMenuView menu = (BottomNavigationMenuView)bottom_nav.getChildAt(0);
        BottomNavigationItemView item_nav_bn =(BottomNavigationItemView) menu.getChildAt(2);
        BottomNavigationItemView item_nav_shopping =(BottomNavigationItemView) menu.getChildAt(1);

        View notify_item_nav_shopping = LayoutInflater.from(this)
                .inflate(R.layout.item_bage,menu,false);
        TextView cart_badge =
        notify_item_nav_shopping.findViewById(R.id.cart_badge);
        cart_badge.setText("+20");
        View notify_item_nav_mess = LayoutInflater.from(this)
                .inflate(R.layout.item_bage,menu,false);
        item_nav_bn.addView(notify_item_nav_mess);
        item_nav_shopping.addView(notify_item_nav_shopping);

    }

    private void setHistorySearchData() {
        history_search = getListSearched();
    }

    private List<String> getListSearched() {
        List<String> list = new ArrayList<>();
        list.add(new String("iphone 14 pro max"));
        list.add(new String("thực phẩm bổ sung"));
        list.add(new String("quần áo"));
        list.add(new String("điện thoại giá rẻ"));
        return list;
    }

    private void setSliderData() {
        photoList = getListPhoto();
        sliderAdapter sliderAdapter = new sliderAdapter(this,photoList);
        v2_slider.setAdapter(sliderAdapter);
        slier_control.setViewPager(v2_slider);
    }

    private List<Photo> getListPhoto() {
        List<Photo> list = new ArrayList<>();
        list.add(new Photo("https://giamgia88.com/wp-content/uploads/2020/12/12.12-banner-copy.jpg"));
        list.add(new Photo("https://thuvienmuasam.com/uploads/default/optimized/2X/0/067d48d824f580e062d22fe367ca2820080bf587_2_1024x540.jpeg"));
        list.add(new Photo("https://tingiasoc.com/wp-content/uploads/2021/12/lazada-12-12-sale-to-cuoi-nam.png"));
        list.add(new Photo("https://magiamgialientuc.com/wp-content/uploads/2019/12/lazada-tet-2021.jpg"));
        list.add(new Photo("https://danhgiatot.cdn.vccloud.vn/wp-content/uploads/2022/01/lazada-sale-tet-2022-7.jpg"));
        return list;
    }

    private List<Category> getListCategory() {
        List<Category> list = new ArrayList<>();
        list.add(new Category("https://laz-img-cdn.alicdn.com/images/ims-web/TB1tq_lmBjTBKNjSZFuXXb0HFXa.png","","LazMall"));
        list.add(new Category("https://laz-img-cdn.alicdn.com/images/ims-web/TB1UiNthUT1gK0jSZFhXXaAtVXa.png","","Mã Giảm Giá"));
        list.add(new Category("https://www.pngkit.com/png/full/229-2296936_5-percent-off-png-icon-gim-gi-10.png","","Mua Hết <99k"));
        list.add(new Category("https://laz-img-cdn.alicdn.com/images/ims-web/TB1tq_lmBjTBKNjSZFuXXb0HFXa.png","","LazMall"));
        list.add(new Category("https://laz-img-cdn.alicdn.com/images/ims-web/TB1UiNthUT1gK0jSZFhXXaAtVXa.png","","Mã Giảm Giá"));
        list.add(new Category("https://www.pngkit.com/png/full/229-2296936_5-percent-off-png-icon-gim-gi-10.png","","Mua Hết <99k"));
        list.add(new Category("https://laz-img-cdn.alicdn.com/images/ims-web/TB1tq_lmBjTBKNjSZFuXXb0HFXa.png","","LazMall"));
        list.add(new Category("https://laz-img-cdn.alicdn.com/images/ims-web/TB1UiNthUT1gK0jSZFhXXaAtVXa.png","","Mã Giảm Giá"));
        list.add(new Category("https://www.pngkit.com/png/full/229-2296936_5-percent-off-png-icon-gim-gi-10.png","","Mua Hết <99k"));
        list.add(new Category("https://laz-img-cdn.alicdn.com/images/ims-web/TB1UiNthUT1gK0jSZFhXXaAtVXa.png","","Mã Giảm Giá"));
        list.add(new Category("https://www.pngkit.com/png/full/229-2296936_5-percent-off-png-icon-gim-gi-10.png","","Mua Hết <99k"));
        list.add(new Category("https://laz-img-cdn.alicdn.com/images/ims-web/TB1tq_lmBjTBKNjSZFuXXb0HFXa.png","","LazMall"));
        list.add(new Category("https://laz-img-cdn.alicdn.com/images/ims-web/TB1UiNthUT1gK0jSZFhXXaAtVXa.png","","Mã Giảm Giá"));
        list.add(new Category("https://www.pngkit.com/png/full/229-2296936_5-percent-off-png-icon-gim-gi-10.png","","Mua Hết <99k"));
        list.add(new Category("https://laz-img-cdn.alicdn.com/images/ims-web/TB1tq_lmBjTBKNjSZFuXXb0HFXa.png","","LazMall"));
        list.add(new Category("https://laz-img-cdn.alicdn.com/images/ims-web/TB1UiNthUT1gK0jSZFhXXaAtVXa.png","","Mã Giảm Giá"));
        list.add(new Category("https://www.pngkit.com/png/full/229-2296936_5-percent-off-png-icon-gim-gi-10.png","","Mua Hết <99k"));
        list.add(new Category("https://laz-img-cdn.alicdn.com/images/ims-web/TB1tq_lmBjTBKNjSZFuXXb0HFXa.png","","LazMall"));
        list.add(new Category("https://laz-img-cdn.alicdn.com/images/ims-web/TB1UiNthUT1gK0jSZFhXXaAtVXa.png","","Mã Giảm Giá"));
        list.add(new Category("https://www.pngkit.com/png/full/229-2296936_5-percent-off-png-icon-gim-gi-10.png","","Mua Hết <99k"));
        list.add(new Category("https://laz-img-cdn.alicdn.com/images/ims-web/TB1UiNthUT1gK0jSZFhXXaAtVXa.png","","Mã Giảm Giá"));
        list.add(new Category("https://www.pngkit.com/png/full/229-2296936_5-percent-off-png-icon-gim-gi-10.png","","Mua Hết <99k"));

        return list;
    }
    @SuppressLint("ResourceType")
    private void Init() {
        i=0;
        txt_searched = findViewById(R.id.txt_searched);
        v2_slider =(ViewPager2) findViewById(R.id.v2_slider);
        rc_cate = findViewById(R.id.rc_cate);
        slier_control = findViewById((R.id.slider_control));
        bottom_nav = findViewById(R.id.bottom_nav);
        text_animation_move_in = AnimationUtils.loadAnimation(this, R.drawable.animation_text_move_in);
        text_animation_move_out = AnimationUtils.loadAnimation(this,R.drawable.animation_text_move_out);
    }

    public static void buttonEffect(View button){
        button.setOnTouchListener(new View.OnTouchListener() {

            public boolean onTouch(View v, MotionEvent event) {
                switch (event.getAction()) {
                    case MotionEvent.ACTION_DOWN: {
                        v.getBackground().setColorFilter(0xe0f47521, PorterDuff.Mode.SRC_ATOP);
                        v.invalidate();
                        break;
                    }
                    case MotionEvent.ACTION_UP: {
                        v.getBackground().clearColorFilter();
                        v.invalidate();
                        break;
                    }
                }
                return false;
            }
        });
    }

    public void scan(View v){
        if(v.getId() == R.id.scan){
            Toast.makeText(this, "click", Toast.LENGTH_SHORT).show();
        }
    }
public class The_slide_timer extends TimerTask {
    @Override
    public void run() {

        HomeActivity.this.runOnUiThread(new Runnable() {
            @Override
            public void run() {

                if (v2_slider.getCurrentItem()< photoList.size()-1) {
                    v2_slider.setCurrentItem(v2_slider.getCurrentItem()+1);
                }
                else
                    v2_slider.setCurrentItem(0);
            }
        });
    }
}
public class The_searched_timer extends TimerTask {
        @Override
        public void run() {

            HomeActivity.this.runOnUiThread(new Runnable() {
                @Override
                public void run() {
                    if(history_search.size()>i){
                        txt_searched.setText(history_search.get(i));
                        i++;
                    }else{
                        txt_searched.setText(history_search.get(0));
                        i=0;
                    }
                    txt_searched.startAnimation(text_animation_move_in);
                }
            });
        }
    }


}