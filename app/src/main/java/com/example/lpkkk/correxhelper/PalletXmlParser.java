package com.example.lpkkk.correxhelper;

import android.app.Activity;
import android.util.Log;

import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserException;
import org.xmlpull.v1.XmlPullParserFactory;


import java.io.InputStream;
import java.util.ArrayList;

/**
 * Created by lpkkk on 10/05/17.
 */

public class PalletXmlParser {

    private ArrayList<Pallet> palletsArray = new ArrayList<Pallet>();

    //private XmlPullParserFactory xmlFactoryObject = XmlPullParserFactory.newInstance();
    //private XmlPullParser myparser = xmlFactoryObject.newPullParser();

    public PalletXmlParser(Activity mActivity){
        XmlPullParserFactory xmlFactoryObject = null;
        XmlPullParser myparser = null;
        try {
            xmlFactoryObject = XmlPullParserFactory.newInstance();
            myparser = xmlFactoryObject.newPullParser();
        } catch (XmlPullParserException e) {
            e.printStackTrace();
        }
        try {
            Pallet tmppallet = null;
            String tmptext = "";
            InputStream is = mActivity.getAssets().open("pallets.xml");
            myparser.setInput(is, null);
            int event = myparser.getEventType();
            while (event != XmlPullParser.END_DOCUMENT)  {
                String name=myparser.getName();
                switch (event){
                    case XmlPullParser.START_TAG:
                        if("Pallet".equals(name)){
                            tmppallet = new Pallet();
                            tmppallet.setId(Integer.parseInt(myparser.getAttributeValue(0)));
                        }
                        if("Pallets".equals(name)) {
                            palletsArray = new ArrayList<Pallet>();
                        }
                        break;
                    case XmlPullParser.TEXT:
                         tmptext = myparser.getText().trim();
                    case XmlPullParser.END_TAG:
                        if("Pallet".equals(name)){
                            palletsArray.add(tmppallet);
                        }
                        if("Name".equals(name)){
                            tmppallet.setName(tmptext);
                        }
                        if("W".equals(name)){
                            tmppallet.setW(Integer.parseInt(tmptext));
                        }
                        if("L".equals(name)){
                            tmppallet.setL(Integer.parseInt(tmptext));
                        }
                        if("Loc".equals(name)){
                            tmppallet.setLoc(tmptext);
                        }
                        break;
                }
                event = myparser.next();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
            Log.v("hhh",palletsArray.toString());

    }
    public ArrayList<Pallet> getPalletsArray(){
        return palletsArray;
    }
}
