package com.craftilo.admobtesting;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;

import com.google.android.gms.ads.AdListener;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;
import com.google.android.gms.ads.MobileAds;

import static com.google.android.gms.ads.AdRequest.ERROR_CODE_INTERNAL_ERROR;
import static com.google.android.gms.ads.AdRequest.ERROR_CODE_INVALID_REQUEST;
import static com.google.android.gms.ads.AdRequest.ERROR_CODE_NETWORK_ERROR;
import static com.google.android.gms.ads.AdRequest.ERROR_CODE_NO_FILL;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        MobileAds.initialize(this, "ca-app-pub-3940256099942544~3347511713");
        Log.d("Ads", "MobileSDK initialized Successfully");
        AdView mAdView = findViewById(R.id.adView);
        AdRequest adRequest = new AdRequest.Builder()
                .addTestDevice("77714E5EDCB9409C44DACB2965AC4695")
                .build();
        mAdView.loadAd(adRequest);
        Log.d("Ads", "Load Ad processed Successfully");

        mAdView.setAdListener(new AdListener() {
            @Override
            public void onAdLoaded() {
                // Code to be executed when an ad finishes loading.
                Log.d("Ads", "Loaded Successfully");
            }

            @Override
            public void onAdFailedToLoad(int errorCode) {
                // Code to be executed when an ad request fails.
                String reason = "";
                switch(errorCode){
                    case ERROR_CODE_INTERNAL_ERROR:
                        reason = " [ERROR_CODE_INTERNAL_ERROR]";
                        break;
                    case ERROR_CODE_INVALID_REQUEST  :
                        reason = " [ERROR_CODE_INVALID_REQUEST]";
                        break;
                    case ERROR_CODE_NETWORK_ERROR :
                        reason = " [ERROR_CODE_NETWORK_ERROR]";
                        break;
                    case ERROR_CODE_NO_FILL :
                        reason = " [ERROR_CODE_NO_FILL]";
                }
                Log.d("Ads", "Failed to Load. Reason: " + errorCode + reason);
            }

            @Override
            public void onAdOpened() {
                // Code to be executed when an ad opens an overlay that
                // covers the screen.
                Log.d("Ads", "Opened Successfully");
            }

            @Override
            public void onAdClicked() {
                // Code to be executed when the user clicks on an ad.
                Log.d("Ads", "Clicked Successfully");
            }

            @Override
            public void onAdLeftApplication() {
                // Code to be executed when the user has left the app.
                Log.d("Ads", "Left app due to click on Ad.");
            }

            @Override
            public void onAdClosed() {
                // Code to be executed when the user is about to return
                // to the app after tapping on an ad.
                Log.d("Ads", "Closed Successfully");
            }
        });
    }
}
