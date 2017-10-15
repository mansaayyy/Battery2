package com.example.manasi.battery2;

        import android.content.BroadcastReceiver;
        import android.content.Context;
        import android.content.Intent;
        import android.content.IntentFilter;
        import android.os.BatteryManager;
        import android.support.v7.app.AppCompatActivity;
        import android.os.Bundle;
        import android.widget.TextView;
        import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    private TextView batteryTxt;
    private TextView charge;
    private TextView Acusb;
    private BroadcastReceiver mBatInfoReceiver = new BroadcastReceiver(){
        @Override
        public void onReceive(Context ctxt, Intent intent) {
            int level = intent.getIntExtra(BatteryManager.EXTRA_LEVEL, 0);
            batteryTxt.setText(String.valueOf(level) + "%");
            int status = intent.getIntExtra(BatteryManager.EXTRA_STATUS, -1);



            boolean isCharging = status == BatteryManager.BATTERY_STATUS_CHARGING ||
                    status == BatteryManager.BATTERY_STATUS_FULL;
            if (isCharging){
                charge.setText("The device is charging");

            }else{
                charge.setText("The device is not charging");

            }
            int status1 = intent.getIntExtra(BatteryManager.EXTRA_STATUS, -1);

            boolean plug = status1 == BatteryManager.BATTERY_PLUGGED_AC ||
                    status1 == BatteryManager.BATTERY_PLUGGED_USB;

            if (plug){
                Acusb.setText("BATTERY PLUGGED AC");

            }else{
                Acusb.setText("BATTERY PLUGGED USB");

            }
        }
    };

    @Override
    public void onCreate(Bundle b) {
        super.onCreate(b);
        setContentView(R.layout.activity_main);
        batteryTxt = (TextView) this.findViewById(R.id.batteryTxt);
        charge = (TextView) this.findViewById(R.id.charging);
        Acusb = (TextView) this.findViewById(R.id.acusb);

        this.registerReceiver(this.mBatInfoReceiver, new IntentFilter(Intent.ACTION_BATTERY_CHANGED));
    }

}
