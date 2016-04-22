package in.epdshp.co.epds_hp;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.List;

/**
 * Created by KD on 7/31/2015.
 */
public class BinderData_Inspectors extends ArrayAdapter<InspectorsPojo> {

    private Context context;
         private List<InspectorsPojo> InspectorCollectoion;


                 public BinderData_Inspectors(Context context, int resource, List<InspectorsPojo> objects) {
                 super(context, resource, objects);
                 this.context = context;
                 this.InspectorCollectoion = objects;
             }

    @Override
         public View getView(int position, View convertView, ViewGroup parent) {


                LayoutInflater inflater = (LayoutInflater) context.getSystemService(Activity.LAYOUT_INFLATER_SERVICE);
                 View view = inflater.inflate(R.layout.item_in, parent, false);


                 //Display flower name in the TextView widget
                 InspectorsPojo ip = InspectorCollectoion.get(position);
                 TextView tv = (TextView) view.findViewById(R.id.textView1_room_no);
                // TextView tv2 = (TextView)view.findViewById(R.id.textView2_floor_entrance);




                 tv.setText(ip.getNameofBlock());
                // tv2.setText(ip.getOfficialMobileNumber());


                 return view;
             }


}

