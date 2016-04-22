package in.epdshp.co.epds_hp;

import android.app.Activity;
import android.content.Intent;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;


public class DFSC_Data_Detals extends Activity {

    TextView sno, district,address,landline,faxnumber,mobilenumber,icon,emailid;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dfsc__data__detals);
        sno = (TextView)findViewById(R.id.dfsc_tv1);
        district = (TextView)findViewById(R.id.dfsc_tv2);
        address = (TextView)findViewById(R.id.dfsc_tv3);
        landline = (TextView)findViewById(R.id.dfsc_tv4);
        faxnumber = (TextView)findViewById(R.id.dfsc_tv5);
        mobilenumber = (TextView)findViewById(R.id.dfsc_tv6);
        icon = (TextView)findViewById(R.id.dfsc_tv7);
        emailid = (TextView)findViewById(R.id.dfsc_tv8);


        Intent getRoomDetailsIntent = getIntent();
        DFSCPojo guestHouseDetails =	(DFSCPojo) getRoomDetailsIntent.getSerializableExtra("DFSCPOJO");

        sno.setText(guestHouseDetails.getSno());
        district.setText(guestHouseDetails.getDistrict());
        address.setText(guestHouseDetails.getAddress());
        landline.setText(guestHouseDetails.getLandline());
        faxnumber.setText(guestHouseDetails.getFaxnumber());
        mobilenumber.setText(guestHouseDetails.getMobileno());
        icon.setText(guestHouseDetails.getIcon());
        emailid.setText(guestHouseDetails.getEmailid());
    }


}
