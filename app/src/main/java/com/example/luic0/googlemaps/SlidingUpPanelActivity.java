package com.example.luic0.googlemaps;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.Html;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.sothree.slidinguppanel.SlidingUpPanelLayout;

import butterknife.BindView;
import butterknife.ButterKnife;

public class SlidingUpPanelActivity extends AppCompatActivity {
    private static final String TAG = "SlidingUpPanelActivity";

    @BindView(R.id.sliding_layout)
    SlidingUpPanelLayout mLayout;

    @BindView(R.id.img_mostrar_ocultar)
    ImageView panelImg;

    @BindView(R.id.txt_mostrar)
    TextView txtPanelLabel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sliding_up_panel);
        ButterKnife.bind(this);

        mLayout.addPanelSlideListener(new SlidingUpPanelLayout.PanelSlideListener() {
            @Override
            public void onPanelSlide(View panel, float slideOffset) {
                Log.i(TAG, "onPanelSlide, offset " + slideOffset);
            }

            @Override
            public void onPanelStateChanged(View panel, SlidingUpPanelLayout.PanelState previousState, SlidingUpPanelLayout.PanelState newState) {
                Log.i(TAG, "onPanelStateChanged " + newState);
                if (mLayout.getPanelState() == SlidingUpPanelLayout.PanelState.COLLAPSED) {
                    panelImg.setImageDrawable(getDrawable(R.drawable.ic_dropdown_arrow));
                    txtPanelLabel.setText(R.string.mostrar);
                } else if( mLayout.getPanelState() == SlidingUpPanelLayout.PanelState.EXPANDED){
                    mLayout.setAnchorPoint(0.7f);
                    mLayout.setPanelState(SlidingUpPanelLayout.PanelState.ANCHORED);
                    panelImg.setImageDrawable(getDrawable(R.drawable.ic_dropdown_arrow));
                    txtPanelLabel.setText(R.string.ocultar);
                }
            }
        });
        mLayout.setFadeOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mLayout.setPanelState(SlidingUpPanelLayout.PanelState.COLLAPSED);
            }
        });

        TextView t = findViewById(R.id.txt_mostrar);
        t.setText(getString(R.string.mostrar));
        ImageView img = findViewById(R.id.img_mostrar_ocultar);
        if (img.isClickable()){

            img.setOnClickListener((View.OnClickListener) Html.fromHtml(getString(R.string.ocultar)));
        }
    }

    @Override
    public boolean onPrepareOptionsMenu(Menu menu) {
        return super.onPrepareOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()){
            case R.id.img_mostrar_ocultar: {
                if (mLayout != null) {
                    if (mLayout.getPanelState() != SlidingUpPanelLayout.PanelState.COLLAPSED) {
                        mLayout.setPanelState(SlidingUpPanelLayout.PanelState.COLLAPSED);
                        item.setIcon(R.drawable.ic_dropdown_arrow);
                        item.setTitle(R.string.mostrar);
                    } else {
                        mLayout.setAnchorPoint(0.5f);
                        mLayout.setPanelState(SlidingUpPanelLayout.PanelState.EXPANDED);
                        item.setIcon(R.drawable.ic_dropdown_arrow);
                        item.setTitle(R.string.ocultar);
                    }
                }
                return true;
            }
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onBackPressed() {
        if (mLayout != null &&
                (mLayout.getPanelState() == SlidingUpPanelLayout.PanelState.EXPANDED || mLayout.getPanelState() == SlidingUpPanelLayout.PanelState.ANCHORED)) {
            mLayout.setPanelState(SlidingUpPanelLayout.PanelState.COLLAPSED);
        } else {
            super.onBackPressed();
        }
    }
}
