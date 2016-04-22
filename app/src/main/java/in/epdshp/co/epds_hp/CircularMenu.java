package in.epdshp.co.epds_hp;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.telephony.PhoneStateListener;
import android.telephony.TelephonyManager;
import android.util.Log;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.RotateAnimation;
import android.widget.TextView;
import android.widget.Toast;

import in.epdshp.co.epds_hppresentationui.CircleImageView;
import in.epdshp.co.epds_hppresentationui.CircleLayout;

/**
 * Created by HPZ231 on 19-05-2015.
 */
public class CircularMenu extends Activity implements CircleLayout.OnItemSelectedListener, CircleLayout.OnItemClickListener, CircleLayout.OnRotationFinishedListener, CircleLayout.OnCenterClickListener {
    private TextView selectedTextView;
    final Context context = this;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_circular_menu);

        // Set Phone Listeners
        // add PhoneStateListener
        PhoneCallListener phoneListener = new PhoneCallListener();
        TelephonyManager telephonyManager = (TelephonyManager) this.getSystemService(Context.TELEPHONY_SERVICE);
        telephonyManager.listen(phoneListener, PhoneStateListener.LISTEN_CALL_STATE);


        // Set listeners
        CircleLayout circleMenu = (CircleLayout) findViewById(R.id.main_circle_layout);
        circleMenu.setOnItemSelectedListener((CircleLayout.OnItemSelectedListener) this);
        circleMenu.setOnItemClickListener((CircleLayout.OnItemClickListener) this);
        circleMenu.setOnRotationFinishedListener((CircleLayout.OnRotationFinishedListener) this);
        circleMenu.setOnCenterClickListener((CircleLayout.OnCenterClickListener) this);

        selectedTextView = (TextView) findViewById(R.id.main_selected_textView);
        selectedTextView.setText(((CircleImageView) circleMenu.getSelectedItem()).getName());
    }

    @Override
    public void onItemSelected(View view, String name) {
        selectedTextView.setText(name);

        switch (view.getId()) {
            case R.id.main_info:
                // Handle calendar selection
                break;
            case R.id.main_cloud_image:
                // Handle cloud selection
                break;
            case R.id.main_facebook_image:
                // Handle facebook selection
                break;
            case R.id.main_key_image:
                // Handle key selection
                break;
            case R.id.main_profile_image:
                // Handle profile selection
                break;
            case R.id.main_tap_phone:
                // Handle tap selection
                break;

            case R.id.main_ration_card_image:
                //Handle Tab Selection
                break;
        }
    }


    @Override
    public void onItemClick(View view, String name) {
       // Toast.makeText(getApplicationContext(), getResources().getString(R.string.start_app) + " " + name, Toast.LENGTH_SHORT).show();

        switch (view.getId()) {
            case R.id.main_info:
                Intent i = new Intent(CircularMenu.this, Info.class);
                startActivity(i);
                break;
            case R.id.main_cloud_image:
                Intent i2 = new Intent(CircularMenu.this, Cloud.class);
                startActivity(i2);
                break;
            case R.id.main_facebook_image:
               Intent i1 = new Intent(CircularMenu.this, FaceBook_WebView.class);
                startActivity(i1);
                break;
            case R.id.main_key_image:
                // Handle key click
                Intent i4 = new Intent(CircularMenu.this , Inspectors.class);
                startActivity(i4);
                break;
            case R.id.main_profile_image:
                // Handle profile click DFSC Data
                Intent i3 = new Intent(CircularMenu.this , DFSC.class);
                startActivity(i3);

                break;
            case R.id.main_tap_phone:
                // Handle tap click  //call epds Toll Free
               // Intent i = new Intent(CircularMenu.this , Phone_Call_Activity.class);
               // startActivity(i);
               AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(context);

                // set title
                alertDialogBuilder.setTitle("Give a Call");

                // set dialog message
                alertDialogBuilder
                        .setMessage("You are about to call ePDS Department Himachal Pradesh")
                        .setCancelable(false)
                        .setPositiveButton("Call",new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog,int id) {
                                // if this button is clicked, close
                                // current activity
                                Intent callIntent = new Intent(Intent.ACTION_CALL);
                                callIntent.setData(Uri.parse("tel:1967"));
                                startActivity(callIntent);

                            }
                        })
                        .setNegativeButton("Don't Call", new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int id) {
                                // if this button is clicked, just close
                                // the dialog box and do nothing
                                dialog.cancel();
                            }
                        });

                // create alert dialog
                AlertDialog alertDialog = alertDialogBuilder.create();

                // show it
                alertDialog.show();



                break;

            case R.id.main_ration_card_image:
                //Handle Tab Selection //Find Your Ration Card
                break;
        }
    }



    @Override
    public void onRotationFinished(View view, String name) {
        Animation animation = new RotateAnimation(0, 360, view.getWidth() / 2,view.getHeight() / 2);
        animation.setDuration(500);  //default is 250
        view.startAnimation(animation);

    }

    @Override
    public void onCenterClick() {
       // Toast.makeText(getApplicationContext(), R.string.center_click, Toast.LENGTH_SHORT).show();
    }


    private class PhoneCallListener extends PhoneStateListener {

        private boolean isPhoneCalling = false;

        String LOG_TAG = "LOGGING 123";

        @Override
        public void onCallStateChanged(int state, String incomingNumber) {

            if (TelephonyManager.CALL_STATE_RINGING == state) {
                // phone ringing
                Log.i(LOG_TAG, "RINGING, number: " + incomingNumber);
            }

            if (TelephonyManager.CALL_STATE_OFFHOOK == state) {
                // active
                Log.i(LOG_TAG, "OFFHOOK");

                isPhoneCalling = true;
            }

            if (TelephonyManager.CALL_STATE_IDLE == state) {
                // run when class initial and phone call ended, need detect flag
                // from CALL_STATE_OFFHOOK
                Log.i(LOG_TAG, "IDLE");

                if (isPhoneCalling) {

                    Log.i(LOG_TAG, "restart app");

                    // restart app
                    //Intent i = getBaseContext().getPackageManager().getLaunchIntentForPackage(getBaseContext().getPackageName());
                    //i.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                    if ((getIntent().getFlags() & Intent.FLAG_ACTIVITY_BROUGHT_TO_FRONT) != 0) {
                        // Activity was brought to front and not created,
                        // Thus finishing this will get us to the last viewed activity
                        finish();
                        return;
                    }
                    isPhoneCalling = false;
                }

            }
        }
    }
}
