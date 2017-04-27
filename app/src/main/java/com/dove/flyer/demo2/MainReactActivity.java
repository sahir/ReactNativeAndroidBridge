package com.dove.flyer.demo2;

import android.os.Bundle;
import android.view.KeyEvent;
import android.widget.Toast;

import com.facebook.react.ReactActivity;
import com.facebook.react.ReactInstanceManager;
import com.facebook.react.ReactRootView;
import com.facebook.react.bridge.ReactContext;
import com.facebook.react.bridge.WritableMap;
import com.facebook.react.common.LifecycleState;
import com.facebook.react.modules.core.DeviceEventManagerModule;
import com.facebook.react.shell.MainReactPackage;

import javax.annotation.Nullable;


/**
 * The type Main react activity.
 *
 * @author Sahir Saiyed
 *         https://github.com/sahir
 */
public class MainReactActivity extends ReactActivity {
    private ReactRootView mReactRootView;
    private ReactInstanceManager mReactInstanceManager;
    ReactContext reactContext;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        Bundle initialProps = new Bundle();
        initialProps.putString("message", "Welcome to React Native! This page will help you install React Native on your system, so that you can build apps with it right away. If you already have React Native installed, you can skip ahead to the Tutorial.\n" +
                "\n");

        mReactRootView = new ReactRootView(MainReactActivity.this);
        mReactInstanceManager = ReactInstanceManager.builder()
                .setApplication(getApplication())
                .setBundleAssetName("index.android.bundle")
                .setJSMainModuleName("index.android")
                .addPackage(new MainReactPackage())
                .addPackage(new AnExampleReactPackage())
                .setUseDeveloperSupport(BuildConfig.DEBUG)
                .setInitialLifecycleState(LifecycleState.RESUMED)
                .setCurrentActivity(MainReactActivity.this)
                .build();

        mReactRootView.startReactApplication(mReactInstanceManager, "HelloWorld", initialProps);
        setContentView(mReactRootView);
    }


    private void sendEvent(ReactContext reactContext,
                           String eventName,
                           @Nullable WritableMap params) {
        reactContext
                .getJSModule(DeviceEventManagerModule.RCTDeviceEventEmitter.class)
                .emit(eventName, params);
    }

    @Nullable
    @Override
    protected String getMainComponentName() {
        return super.getMainComponentName();
    }

    @Override
    public void invokeDefaultOnBackPressed() {
        super.onBackPressed();
    }

    @Override
    public void onBackPressed() {

        super.onBackPressed();

    }

    @Override
    public boolean onKeyUp(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_MENU && mReactInstanceManager != null) {
            mReactInstanceManager.showDevOptionsDialog();
            return true;
        }
        return super.onKeyUp(keyCode, event);
    }

    /**
     * My method string.
     *
     * @param message the message
     * @return the string
     */
    public String activitySendEvent(String message) {

        Toast.makeText(this, message + " React.ReactActivity ", Toast.LENGTH_SHORT).show();
        sendEventQrCode();
        return "line 108";

        //ToastCustomModule.sendEvent(reactContext,"sahir");
    }

    public void sendEventQrCode() {
        String qrCode = "876398776";
        ReactContext reactContext = mReactInstanceManager.getCurrentReactContext();
        if (reactContext != null) {
            reactContext.getJSModule(DeviceEventManagerModule.RCTDeviceEventEmitter.class)
                    .emit("qrCode", qrCode);
        }
    }


}
