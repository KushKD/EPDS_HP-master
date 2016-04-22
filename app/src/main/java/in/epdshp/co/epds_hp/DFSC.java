package in.epdshp.co.epds_hp;

import android.app.Activity;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.ProgressBar;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

public class DFSC extends Activity {



    static final String filename_dfsc = "dfsc.xml";
    List<DFSCPojo> InsObjectList;
    ListView list;
    ProgressBar pb;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dfsc);

        DFSC_Async DA = new DFSC_Async();
        DA.execute(filename_dfsc);

        list = (ListView) findViewById(R.id.list);

        pb = (ProgressBar) findViewById(R.id.progressBar1);
        pb.setVisibility(View.VISIBLE);


        list.setOnItemClickListener(new AdapterView.OnItemClickListener() {

            @Override
            public void onItemClick(AdapterView<?> parent, View view,int position, long id) {
                // TODO Auto-generated method stub

                DFSCPojo DFSCDetails = (DFSCPojo)	parent.getItemAtPosition(position);

                Intent dfscd = new Intent();
                dfscd.putExtra("DFSCPOJO", DFSCDetails);
                dfscd.setClass(DFSC.this, DFSC_Data_Detals.class);

                // update database for my favorite member
                //  memberDetailsDBAdapter.updateFavrouiteList(searchMemberDetails);
                startActivity(dfscd);
                // finish();

            }
        });



    }

    class DFSC_Async extends AsyncTask<String, String, List<DFSCPojo>> {

        String filename_toread = null;

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            // pb.setVisibility(View.VISIBLE);



        }

        @Override
        protected List<DFSCPojo> doInBackground(String... params) {

            filename_toread = params[0];
            DocumentBuilderFactory docBuilderFactory = DocumentBuilderFactory.newInstance();
            DocumentBuilder docBuilder = null;
            try {
                docBuilder = docBuilderFactory.newDocumentBuilder();
            } catch (ParserConfigurationException e) {
                Log.d("@@@@",e.getLocalizedMessage());
            }
            try {
                Document doc = docBuilder.parse(getAssets().open(filename_toread));


                InsObjectList = new ArrayList<DFSCPojo>();

                doc.getDocumentElement().normalize();
                NodeList INSList = doc.getElementsByTagName("dfscdata");

                List<DFSCPojo> map = null;

                for (int i = 0; i < INSList.getLength(); i++) {
                    System.out.println(Integer.toString(INSList.getLength()));
                    map = new ArrayList<>();
                    DFSCPojo INP = new DFSCPojo();
                    Node firstWeatherNode = INSList.item(i);
                    if (firstWeatherNode.getNodeType() == Node.ELEMENT_NODE) {
                        Element firstWeatherElement = (Element) firstWeatherNode;
                        //-------
                        NodeList idList = firstWeatherElement.getElementsByTagName("sno");
                        Element firstIdElement = (Element)idList.item(0);
                        NodeList textIdList = firstIdElement.getChildNodes();
                        //--sno
                        INP.setSno(textIdList.item(0).getNodeValue().trim());

                        //2.-------
                        NodeList cityList = firstWeatherElement.getElementsByTagName("district");
                        Element firstCityElement = (Element)cityList.item(0);
                        NodeList textCityList = firstCityElement.getChildNodes();
                        //--district
                        INP.setDistrict(textCityList.item(0).getNodeValue().trim());

                        //3.-------
                        NodeList tempList = firstWeatherElement.getElementsByTagName("address");
                        Element firstTempElement = (Element)tempList.item(0);
                        NodeList textTempList = firstTempElement.getChildNodes();
                        //--address
                        INP.setAddress(textTempList.item(0).getNodeValue().trim());

                        //Landline
                        NodeList landlineList = firstWeatherElement.getElementsByTagName("landline");
                        Element firstlandlineElement = (Element)landlineList.item(0);
                        NodeList textlandlineList = firstlandlineElement.getChildNodes();
                        //--address
                        INP.setLandline(textlandlineList.item(0).getNodeValue().trim());

                        //4.-------
                        NodeList condList = firstWeatherElement.getElementsByTagName("faxnumber");
                        Element firstCondElement = (Element)condList.item(0);
                        NodeList textCondList = firstCondElement.getChildNodes();
                        //--faxnumber
                        INP.setFaxnumber(textCondList.item(0).getNodeValue().trim());

                        //5.-------
                        NodeList speedList = firstWeatherElement.getElementsByTagName("mobileno");
                        Element firstSpeedElement = (Element)speedList.item(0);
                        NodeList textSpeedList = firstSpeedElement.getChildNodes();
                        //--mobileno
                        INP.setMobileno(textSpeedList.item(0).getNodeValue().trim());

                        //6.-------
                        NodeList iconList = firstWeatherElement.getElementsByTagName("icon");
                        Element firstIconElement = (Element)iconList.item(0);
                        NodeList textIconList = firstIconElement.getChildNodes();
                        //--icon
                        INP.setIcon(textIconList.item(0).getNodeValue().trim());

                        //7.------
                        //6.-------
                        NodeList emailList = firstWeatherElement.getElementsByTagName("emailid");
                        Element firstemailElement = (Element)emailList.item(0);
                        NodeList textemailList = firstemailElement.getChildNodes();
                        //--email
                        INP.setEmailid(textemailList.item(0).getNodeValue().trim());

                        InsObjectList.add(INP);
                    }


                }
            }catch (Exception e){
                Log.d("Error:", e.getLocalizedMessage());
            }
            return  InsObjectList;
        }


        @Override
        protected void onPostExecute(List<DFSCPojo> s) {
            super.onPostExecute(s);
            updateui(s);
        }
    }

    private void updateui(List<DFSCPojo> listCollection) {

        BinderData_DFSC bindingData = new BinderData_DFSC(this, R.layout.item_in, listCollection);




        Log.i("BEFORE", "<<------------- Before SetAdapter-------------->>");

        list.setAdapter(bindingData);

        Log.i("AFTER", "<<------------- After SetAdapter-------------->>");
        pb.setVisibility(View.INVISIBLE);
    }
}
