package com.witts.mdbox.adapter;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.Display;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.witts.mdbox.R;
import com.witts.mdbox.activity.HotelRoomDetailActivity;

/**
 * Created by wm02 on 4/6/2017.
 */

public class MenuFragmentAdapter extends RecyclerView.Adapter<MenuFragmentAdapter.MenuFragmentAdapterViewHolder> {

    private final String TAG = this.getClass().getSimpleName();
    public static Context mContext;
    public static onMenuFragmentAdapterOnClickHandler mClickHandler;
    private MenuFragmentAdapterViewHolder mViewHolder;
    private Animation animScale;

    public MenuFragmentAdapter() {
    }

    public MenuFragmentAdapter(Context context, onMenuFragmentAdapterOnClickHandler dh) {
        mContext = context;
        mClickHandler = dh;
    }

    @Override
    public MenuFragmentAdapterViewHolder onCreateViewHolder(ViewGroup viewGroup, int viewType) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.list_menu_adapter, viewGroup, false);
        mViewHolder = new MenuFragmentAdapterViewHolder(view);
        return mViewHolder;
    }

    @Override
    public void onBindViewHolder(MenuFragmentAdapterViewHolder holder, int position) {
        loadLanguageMenu(holder, position);
    }

    private void loadLanguageMenu(final MenuFragmentAdapterViewHolder holder, int position) {
        if (position == 0) {
            holder.lnrbackground.setBackgroundResource(R.drawable.bgone);
            holder.imgmenu.setImageDrawable(mContext.getResources().getDrawable(R.drawable.hotelbedroom));
            holder.txtlabel.setText("Hotel Bedroom");
//            holder.onMenuFragmentListener = new MenuFragmentAdapterViewHolder.ViewHolderClickListener() {
//                @Override
//                public void onClick(View v) {
//                    animScale = AnimationUtils.loadAnimation(mContext, R.anim.scale_up);
//                    holder.llcontainer.startAnimation(animScale);
//                    holder.llcontainer.bringToFront();
//                    holder.lnrbackground.setBackgroundResource(R.drawable.menu_background_one);
//                    goToBedroomDetail();
//                }
//            };
        } else if (position == 1) {
            holder.lnrbackground.setBackgroundResource(R.drawable.bgtwo);
            holder.imgmenu.setImageDrawable(mContext.getResources().getDrawable(R.drawable.playground));
            holder.txtlabel.setText("Swimming pool");
            holder.onMenuFragmentListener = new MenuFragmentAdapterViewHolder.ViewHolderClickListener() {
                @Override
                public void onClick(View v) {
                    animScale = AnimationUtils.loadAnimation(mContext, R.anim.scale_up);
                    holder.llcontainer.startAnimation(animScale);
                    holder.llcontainer.bringToFront();
                    holder.lnrbackground.setBackgroundResource(R.drawable.menu_background_two);
                    goToBedroomDetail();
                }
            };
        } else if (position == 2) {
            holder.lnrbackground.setBackgroundResource(R.drawable.bgthree);
            holder.imgmenu.setImageDrawable(mContext.getResources().getDrawable(R.drawable.foodanddrink));
            holder.txtlabel.setText("Food and Drink");
            holder.onMenuFragmentListener = new MenuFragmentAdapterViewHolder.ViewHolderClickListener() {
                @Override
                public void onClick(View v) {
                    animScale = AnimationUtils.loadAnimation(mContext, R.anim.scale_up);
                    holder.llcontainer.startAnimation(animScale);
                    holder.llcontainer.bringToFront();
                    holder.lnrbackground.setBackgroundResource(R.drawable.menu_background_three);
                    goToBedroomDetail();
                }
            };
        } else if (position == 3) {
            holder.lnrbackground.setBackgroundResource(R.drawable.bgfour);
            holder.imgmenu.setImageDrawable(mContext.getResources().getDrawable(R.drawable.hotelservice));
            holder.txtlabel.setText("Hotel Service");
            holder.onMenuFragmentListener = new MenuFragmentAdapterViewHolder.ViewHolderClickListener() {
                @Override
                public void onClick(View v) {
                    animScale = AnimationUtils.loadAnimation(mContext, R.anim.scale_up);
                    holder.llcontainer.startAnimation(animScale);
                    holder.llcontainer.bringToFront();
                    holder.lnrbackground.setBackgroundResource(R.drawable.menu_background_four);
                    goToBedroomDetail();
                }
            };
        } else if (position == 4) {
            holder.lnrbackground.setBackgroundResource(R.drawable.bgfive);
            holder.imgmenu.setImageDrawable(mContext.getResources().getDrawable(R.drawable.noodlefuncy));
            holder.txtlabel.setText("Noodle");
            holder.onMenuFragmentListener = new MenuFragmentAdapterViewHolder.ViewHolderClickListener() {
                @Override
                public void onClick(View v) {
                    animScale = AnimationUtils.loadAnimation(mContext, R.anim.scale_up);
                    holder.llcontainer.startAnimation(animScale);
                    holder.llcontainer.bringToFront();
                    holder.lnrbackground.setBackgroundResource(R.drawable.menu_background_five);
                    goToBedroomDetail();
                }
            };
        } else if (position == 5) {
            holder.lnrbackground.setBackgroundResource(R.drawable.bgsix);
            holder.imgmenu.setImageDrawable(mContext.getResources().getDrawable(R.drawable.serviceclock));
            holder.txtlabel.setText("Room Service");
            holder.onMenuFragmentListener = new MenuFragmentAdapterViewHolder.ViewHolderClickListener() {
                @Override
                public void onClick(View v) {
                    animScale = AnimationUtils.loadAnimation(mContext, R.anim.scale_up);
                    holder.llcontainer.startAnimation(animScale);
                    holder.llcontainer.bringToFront();
                    holder.lnrbackground.setBackgroundResource(R.drawable.menu_background_six);
                    goToBedroomDetail();
                }
            };
        } else if (position == 6) {
            holder.lnrbackground.setBackgroundResource(R.drawable.bgone);
            holder.imgmenu.setImageDrawable(mContext.getResources().getDrawable(R.drawable.mm));
            holder.txtlabel.setText("Map");
            holder.onMenuFragmentListener = new MenuFragmentAdapterViewHolder.ViewHolderClickListener() {
                @Override
                public void onClick(View v) {
                    animScale = AnimationUtils.loadAnimation(mContext, R.anim.scale_up);
                    holder.llcontainer.startAnimation(animScale);
                    holder.llcontainer.bringToFront();
                    holder.lnrbackground.setBackgroundResource(R.drawable.menu_background_one);
                    goToBedroomDetail();
                }
            };
        }
//        else if (position == 7){
//            holder.lnrbackground.setBackgroundResource(R.drawable.menu_background_eight);
//            holder.imgmenu.setImageDrawable(mContext.getResources().getDrawable(R.drawable.cinema));
//            holder.txtlabel.setText("Cinema");
//        }
    }

    private void goToBedroomDetail() {
        Activity activity = (Activity) mContext;
        Intent intent = new Intent(mContext, HotelRoomDetailActivity.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        mContext.getApplicationContext().startActivity(intent);
        //activity.finish();
    }

    @Override
    public int getItemViewType(int position) {
        return position;
    }

    @Override
    public int getItemCount() {
        return 7;
    }


    public static class MenuFragmentAdapterViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        public LinearLayout lnrbackground;
        public LinearLayout llcontainer;
        public ImageView imgmenu;
        public TextView txtlabel;
        public ViewHolderClickListener onMenuFragmentListener;

        public MenuFragmentAdapterViewHolder(View itemView) {
            super(itemView);
            WindowManager wm = (WindowManager) MenuFragmentAdapter.mContext.getSystemService(Context.WINDOW_SERVICE);
            Display display = wm.getDefaultDisplay();
//        Point size = new Point();
//        int width = display.getWidth();  // deprecated
//        int height = display.getHeight();
            lnrbackground = (LinearLayout) itemView.findViewById(R.id.llbackground);
            llcontainer=(LinearLayout) itemView.findViewById(R.id.llcontainer);
            //lnrbackground.setMinimumWidth(width/5);
            imgmenu = (ImageView) itemView.findViewById(R.id.imgmenu);
            txtlabel = (TextView) itemView.findViewById(R.id.txtlabel);
            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {
            mClickHandler.onClick(getItemViewType(),new MenuFragmentAdapterViewHolder(v));
        }

        /*@Override
        public boolean onHover(View v, MotionEvent event) {
            onMenuFragmentHoverListener.onHover(v);
            return true;
        }*/

        public static interface ViewHolderClickListener {
            public void onClick(View v);
        }

        public static interface ViewHolderHoverListener {
            public void onHover(View v);
        }
    }

        public static interface onMenuFragmentAdapterOnClickHandler {
            void onClick(int position,MenuFragmentAdapterViewHolder vh);
        }

    }
