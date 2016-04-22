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

import java.util.ArrayList;
import java.util.List;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;


public class Inspectors extends Activity {

    public static String filename_inspectors = "HP_BlockWiseInspectorData.xml";
    List<InspectorsPojo> InsObjectList;
    ListView list;
    ProgressBar pb;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_inspectors);

        Inspectors_Async IA = new Inspectors_Async();
        IA.execute(filename_inspectors);

        list = (ListView) findViewById(R.id.list);

        pb = (ProgressBar) findViewById(R.id.progressBar1);
         pb.setVisibility(View.VISIBLE);


        list.setOnItemClickListener(new AdapterView.OnItemClickListener() {

            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                // TODO Auto-generated method stub

                InspectorsPojo INDetails = (InspectorsPojo) parent.getItemAtPosition(position);

                Intent dfscd = new Intent();
                dfscd.putExtra("INSPOJO", INDetails);
                dfscd.setClass(Inspectors.this, Inspectors_Data_details.class);

                // update database for my favorite member
                //  memberDetailsDBAdapter.updateFavrouiteList(searchMemberDetails);
                startActivity(dfscd);
                // finish();

            }
        });




    }

    class Inspectors_Async extends AsyncTask<String, String, List<InspectorsPojo>> {

        String filename_toread = null;

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
           // pb.setVisibility(View.VISIBLE);

        }

        @Override
        protected List<InspectorsPojo> doInBackground(String... params) {

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


                InsObjectList = new ArrayList<InspectorsPojo>();

                doc.getDocumentElement().normalize();
                NodeList INSList = doc.getElementsByTagName("in");

                List<InspectorsPojo> map = null;

                for (int i = 0; i < INSList.getLength(); i++) {
                    System.out.println(Integer.toString(INSList.getLength()));
                    map = new ArrayList<>();
                    InspectorsPojo INP = new InspectorsPojo();
                    Node firstWeatherNode = INSList.item(i);
                    if (firstWeatherNode.getNodeType() == Node.ELEMENT_NODE) {
                        Element firstWeatherElement = (Element) firstWeatherNode;
                        //-------
                        NodeList idList = firstWeatherElement.getElementsByTagName("SrNo");
                        Element firstIdElement = (Element) idList.item(0);
                        NodeList textIdList = firstIdElement.getChildNodes();
                        //--sno
                       // INP.setSrNo(Node) textIdList.item(0)).getNodeValue().toString().trim();
                        INP.setSrNo(textIdList.item(0).getNodeValue().trim());
                       // System.out.println("========"+ INP.getSrNo());

                        //2.-------
                        NodeList cityList = firstWeatherElement.getElementsByTagName("District");
                        Element firstCityElement = (Element) cityList.item(0);
                        NodeList textCityList = firstCityElement.getChildNodes();
                        INP.setDistrict(textCityList.item(0).getNodeValue().trim());
                       // System.out.println("========" + INP.getDistrict());

                        //3.-------
                        NodeList tempList = firstWeatherElement.getElementsByTagName("NameofBlock");
                        Element firstTempElement = (Element) tempList.item(0);
                        NodeList textTempList = firstTempElement.getChildNodes();
                        //--address
                        INP.setNameofBlock(textTempList.item(0).getNodeValue().trim());
                       // System.out.println("========" + INP.getNameofBlock());

                        //Landline
                        NodeList landlineList = firstWeatherElement.getElementsByTagName("NameofInspector");
                        Element firstlandlineElement = (Element) landlineList.item(0);
                        NodeList textlandlineList = firstlandlineElement.getChildNodes();
                        //--address
                        INP.setNameofInspector(textlandlineList.item(0).getNodeValue().trim());
                       // System.out.println("========" + INP.getNameofInspector());

                        //4.-------
                        NodeList condList = firstWeatherElement.getElementsByTagName("PersonalMobileNumber");
                        Element firstCondElement = (Element) condList.item(0);
                        NodeList textCondList = firstCondElement.getChildNodes();
                        //--faxnumber
                        INP.setPersonalMobileNumber(textCondList.item(0).getNodeValue().trim());
                       // System.out.println("========" + INP.getPersonalMobileNumber());

                        //5.-------
                        NodeList speedList = firstWeatherElement.getElementsByTagName("OfficialMobileNumber");
                        Element firstSpeedElement = (Element) speedList.item(0);
                        NodeList textSpeedList = firstSpeedElement.getChildNodes();
                        //--mobileno
                        INP.setOfficialMobileNumber(textSpeedList.item(0).getNodeValue().trim());
                       // System.out.println("========" + INP.getOfficialMobileNumber());

                        //6.-------
                        NodeList iconList = firstWeatherElement.getElementsByTagName("OfficeAddress");
                        Element firstIconElement = (Element) iconList.item(0);
                        NodeList textIconList = firstIconElement.getChildNodes();
                        //--icon
                        INP.setOfficeAddress(textIconList.item(0).getNodeValue().trim());
                        //System.out.println("========" + INP.getOfficeAddress());


                        //Add to the Arraylist
                        InsObjectList.add(INP);
                    }


                }
            }catch (Exception e){
                Log.d("Error:", e.getLocalizedMessage());
            }
   return  InsObjectList;
        }


        @Override
        protected void onPostExecute(List<InspectorsPojo> s) {
            super.onPostExecute(s);

            updateui(s);
        }
    }

    private void updateui(List<InspectorsPojo> listCollection) {

        BinderData_Inspectors bindingData = new BinderData_Inspectors(this, R.layout.item_in, listCollection);




        Log.i("BEFORE", "<<------------- Before SetAdapter-------------->>");

        list.setAdapter(bindingData);

        Log.i("AFTER", "<<------------- After SetAdapter-------------->>");
        pb.setVisibility(View.INVISIBLE);
    }

}


