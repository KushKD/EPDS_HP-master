package in.epdshp.co.epds_hp;

import android.app.Activity;
import android.content.Intent;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;


public class Inspectors_Data_details extends Activity {

    TextView SrNo, District,NameofBlock,NameofInspector,PersonalMobileNumber,OfficialMobileNumber,OfficeAddress;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_inspectors__data_details);


        SrNo = (TextView)findViewById(R.id.insp_tv1);
        District = (TextView)findViewById(R.id.insp_tv2);
        NameofBlock = (TextView)findViewById(R.id.insp_tv3);
        NameofInspector = (TextView)findViewById(R.id.insp_tv4);
        PersonalMobileNumber = (TextView)findViewById(R.id.insp_tv5);
        OfficialMobileNumber = (TextView)findViewById(R.id.insp_tv6);
        OfficeAddress = (TextView)findViewById(R.id.insp_tv8);

        Intent getInspectorsIntent = getIntent();
        InspectorsPojo inspectorsDetails =	(InspectorsPojo) getInspectorsIntent.getSerializableExtra("INSPOJO");


        SrNo.setText(inspectorsDetails.getSrNo());
        District.setText(inspectorsDetails.getDistrict());
        NameofBlock.setText(inspectorsDetails.getNameofBlock());
        NameofInspector.setText(inspectorsDetails.getNameofInspector());
        PersonalMobileNumber.setText(inspectorsDetails.getPersonalMobileNumber());
        OfficialMobileNumber.setText(inspectorsDetails.getOfficialMobileNumber());
        OfficeAddress.setText(inspectorsDetails.getOfficeAddress());
    }

}
