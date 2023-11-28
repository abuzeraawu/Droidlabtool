package com.auazer.droidlabtool;




import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.cardview.widget.CardView;

import com.android.billingclient.api.BillingClient;
import com.android.billingclient.api.BillingClientStateListener;
import com.android.billingclient.api.BillingFlowParams;
import com.android.billingclient.api.BillingResult;
import com.android.billingclient.api.PurchasesUpdatedListener;
import com.android.billingclient.api.SkuDetails;
import com.android.billingclient.api.SkuDetailsParams;

import java.util.Arrays;
import java.util.List;
import java.util.Objects;

public class MainActivity extends AppCompatActivity {

    private static final String TAG = "MainActivity";
    private BillingClient billingClient;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Initialize the toolbar
        Toolbar toolbar = findViewById(R.id.my_toolbar);
        setSupportActionBar(toolbar);

        // Set a title (optional)
        Objects.requireNonNull(getSupportActionBar()).setTitle("droid lab");

        PurchasesUpdatedListener purchasesUpdatedListener = new PurchasesUpdatedListener() {
            @Override
            public void onPurchasesUpdated(BillingResult billingResult, List<com.android.billingclient.api.Purchase> purchases) {
                // To be implemented in a later section.
            }
        };

        billingClient = BillingClient.newBuilder(this)
                .setListener(purchasesUpdatedListener)
                .enablePendingPurchases()
                .build();

        billingClient.startConnection(new BillingClientStateListener() {
            @Override
            public void onBillingSetupFinished(BillingResult billingResult) {
                if (billingResult.getResponseCode() == BillingClient.BillingResponseCode.OK) {
                    // The BillingClient is ready. You can query purchases here.
                }
            }

            @Override
            public void onBillingServiceDisconnected() {
                // Try to restart the connection on the next request to
                // Google Play by calling the startConnection() method.
            }
        });

        // Connect the CardViews
        CardView cardView1 = findViewById(R.id.cardView1);

        cardView1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Log.d(TAG, "CardView1 clicked");

                // Check if billing client is connected before initiating purchase
                if (billingClient.isReady()) {
                    Log.d(TAG, "BillingClient is ready. Initiating purchase...");

                    // Start VersionuninstallActivity
                    startActivity(new Intent(MainActivity.this, VersionuninstallActivity.class));

                    // Initiate purchase for a specific product (replace "your_product_sku_here" with the actual SKU)
                    initiatePurchase();
                } else {
                    Log.e(TAG, "BillingClient not ready. Unable to initiate purchase.");
                    // Handle the error or retry connection
                }
            }
        });











    }

    // Example method for starting the purchase flow
    private void initiatePurchase() {
        // Use BillingClient to query for the SkuDetails
        SkuDetailsParams params = SkuDetailsParams.newBuilder()
                .setSkusList(Arrays.asList("15rupees"))
                .setType(BillingClient.SkuType.INAPP)  // For in-app products
                .build();

        billingClient.querySkuDetailsAsync(params, (billingResult, skuDetailsList) -> {
            if (billingResult.getResponseCode() == BillingClient.BillingResponseCode.OK && skuDetailsList != null) {
                // SkuDetails retrieval successful, launch the billing flow
                for (SkuDetails skuDetails : skuDetailsList) {
                    // Display product information if needed
                    String title = skuDetails.getTitle();
                    String price = skuDetails.getPrice();


                    // Now, launch the billing flow
                    BillingFlowParams billingFlowParams = BillingFlowParams.newBuilder()
                            .setSkuDetails(skuDetails)
                            .build();

                    BillingResult responseCode = billingClient.launchBillingFlow(this, billingFlowParams);

                    // Handle the response code if needed
                    if (responseCode.getResponseCode() == BillingClient.BillingResponseCode.OK) {
                        // Purchase initiated successfully
                    } else {
                        // Handle the error
                        Log.e(TAG, "Error initiating purchase: " + responseCode.getDebugMessage());
                    }
                }
            } else {
                // Handle the error
                Log.e(TAG, "Error retrieving SkuDetails: " + billingResult.getDebugMessage());
            }
        });
    }}

