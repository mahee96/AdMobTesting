/*
Finally, this is the post I was looking for all week, almost wasted more than 2 days to find the difference between Ad AppID, AdUnitID, and where to put them appropriately.

Major Differences:
ca-app-pub-3940256099942544~3347511713  <- Test AppID    (has Tilde ~)
ca-app-pub-3940256099942544/6300978111  <- Test AdUnitID (has forward slash /)

AppID -> Manifest tag, MobileSdk Initialization.
Found in AdmobPage->App->AppSettings->AppID
<meta-data
    android:name="com.google.android.gms.ads.APPLICATION_ID"
    android:value="AppID Here"/>
and
MobileAds.initialize(this, "AppID Here");

AdUnitID (Banner/Small/Interstitial., etc)
Found in AdmobPage->AdUnits and appropriate Ad units like banner etc.
AdView instances can be inflated from XML having (AdUnitID set from above step) or dynamically set at runtime using mAdView.setAdUnitID("your AdUnitID here")

<com.google.android.gms.ads.AdView
    android:id="@+id/adView"
    android:layout_width="wrap_content"
    android:layout_height="wrap_content"
    android:layout_alignParentBottom="true"
    android:layout_centerHorizontal="true"
    app:adSize="BANNER"
    app:adUnitId="ca-app-pub-3940256099942544/6300978111"
    app:layout_constraintBottom_toBottomOf="parent"
    app:layout_constraintEnd_toEndOf="parent"
    app:layout_constraintHorizontal_bias="0.494"
    app:layout_constraintStart_toStartOf="parent"
    app:layout_constraintTop_toBottomOf="@+id/textView"
    app:layout_constraintVertical_bias="0.892">
</com.google.android.gms.ads.AdView>

*/


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

    /*Major Differences:
    ca-app-pub-3940256099942544/6300978111  <- Test AppID    (has Tilde ~)
    ca-app-pub-3940256099942544~3347511713  <- Test AdUnitID (has forward slash /)*/

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
