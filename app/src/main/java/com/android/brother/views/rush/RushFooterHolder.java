package com.android.brother.views.rush;

import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;

import com.android.brother.R;
import com.squareup.picasso.Picasso;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by sagar on 21-06-2017.
 */

public class RushFooterHolder extends RecyclerView.ViewHolder {

    @BindView(R.id.footer_facebook)
    ImageView facebook;
    @BindView(R.id.footer_twitter)
    ImageView twitter;
    @BindView(R.id.footer_snapchat)
    ImageView snapChat;
    @BindView(R.id.footer_instagram)
    ImageView instagram;

    public RushFooterHolder(View itemView) {
        super(itemView);
        ButterKnife.bind(this, itemView);

        facebook.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent;
                try{
                    view.getContext().getPackageManager().getPackageInfo("com.facebook.katana", 0);
                    intent = new Intent(Intent.ACTION_VIEW, Uri.parse("fb://profile/93211205814"));
                    intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                } catch (PackageManager.NameNotFoundException e){
                    intent = new Intent(Intent.ACTION_VIEW, Uri.parse("https://www.facebook.com/MarilynManson"));
                }

                view.getContext().startActivity(intent);
            }
        });

        twitter.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent;
                try{
                    view.getContext().getPackageManager().getPackageInfo("com.twitter.android", 0);
                    intent = new Intent(Intent.ACTION_VIEW, Uri.parse("twitter://user?user_id=28206360"));
                    intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                } catch (PackageManager.NameNotFoundException e){
                    intent = new Intent(Intent.ACTION_VIEW, Uri.parse("https://twitter.com/marilynmanson"));
                }

                view.getContext().startActivity(intent);
            }
        });

        instagram.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent;
                try{
                    view.getContext().getPackageManager().getPackageInfo("com.instagram.android", 0);
                    intent = new Intent(Intent.ACTION_VIEW, Uri.parse("https://www.instagram.com/_u/marilynmanson"));
                    intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                } catch (PackageManager.NameNotFoundException e){
                    intent = new Intent(Intent.ACTION_VIEW, Uri.parse("https://www.instagram.com/marilynmanson"));
                }

                view.getContext().startActivity(intent);
            }
        });

        snapChat.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse("http://snapchat.com/add/" + "EMINEM"));
                view.getContext().startActivity(intent);
            }
        });
    }

    public void populate(Context context){
        Picasso.with(context).load(R.drawable.facebook)
                .fit()
                .centerCrop()
                .into(facebook);

        Picasso.with(context).load(R.drawable.twitter)
                .fit()
                .centerCrop()
                .into(twitter);

        Picasso.with(context).load(R.drawable.instagram)
                .fit()
                .centerCrop()
                .into(instagram);

        Picasso.with(context).load(R.drawable.snapchat)
                .fit()
                .centerCrop()
                .into(snapChat);
    }
}
