package in.epdshp.co.epds_hp;

        import java.util.ArrayList;
        import java.util.HashMap;
        import java.util.List;

        import android.app.Activity;
        import android.content.Context;
        import android.graphics.drawable.Drawable;
        import android.view.LayoutInflater;
        import android.view.View;
        import android.view.ViewGroup;
        import android.widget.ArrayAdapter;
        import android.widget.BaseAdapter;
        import android.widget.ImageView;
        import android.widget.TextView;

public class BinderData_DFSC extends ArrayAdapter<DFSCPojo> {


    private Context context;
    private List<DFSCPojo> DfscCollection;


    public BinderData_DFSC(Context context, int resource, List<DFSCPojo> objects) {
        super(context, resource, objects);
        this.context = context;
        this.DfscCollection = objects;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {


        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Activity.LAYOUT_INFLATER_SERVICE);
        View view = inflater.inflate(R.layout.item_in, parent, false);


        //Display flower name in the TextView widget
        DFSCPojo ip = DfscCollection.get(position);
        TextView tv = (TextView) view.findViewById(R.id.textView1_room_no);
      //  TextView tv2 = (TextView)view.findViewById(R.id.textView2_floor_entrance);




        tv.setText(ip.getDistrict());
       // tv2.setText(ip.getAddress());


        return view;
    }

}
