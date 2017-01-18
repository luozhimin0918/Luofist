package com.luo.luofist;

import android.content.Intent;
import android.os.CountDownTimer;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import com.luo.luofist.base.BaseAppCompatActivity;
import butterknife.InjectView;
import butterknife.OnClick;

/**
 * Created by Luozhimin on 2017/1/18.13:24
 */

public class SplashActivity extends BaseAppCompatActivity {
    @InjectView(R.id.ivBg)
    ImageView ivBg;
    @InjectView(R.id.tvSkip)
    TextView tvSkip;
    int[] imgs = new int[]{
            R.mipmap.irving,
            R.mipmap.bryant,
            R.mipmap.james,
            R.mipmap.harden,
            R.mipmap.curry};

    private CountDownTimer timer;

    @Override
    protected int getContentViewLayoutID() {
        return R.layout.activity_splash;
    }

    @Override
    protected void initViewsAndEvents() {
        int index = (int) (Math.random() * imgs.length);

        ivBg.setImageResource(imgs[index]);

        timer = new CountDownTimer(3500, 1000) {
            @Override
            public void onTick(long millisUntilFinished) {
                tvSkip.setText(String.format(getResources().getString(R.string.skip), (int) (millisUntilFinished / 1000 + 0.1)));
            }

            @Override
            public void onFinish() {
                tvSkip.setText(String.format(getResources().getString(R.string.skip), 0));
                startActivity(new Intent(mContext, MainActivity.class));
                finish();
            }
        };
        timer.start();
    }


    @OnClick({R.id.ivBg, R.id.tvSkip})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.ivBg:
                break;
            case R.id.tvSkip:
                if (timer != null)
                    timer.cancel();

                startActivity(new Intent(mContext, MainActivity.class));
                finish();
                break;
        }
    }


    @Override
    protected void onDestroy() {
        super.onDestroy();

        if (timer != null) {
            timer.cancel();
        }
    }

}
