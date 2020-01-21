package com.example.lpkkk.correxhelper;

import android.app.Activity;
import android.content.Context;
import android.graphics.Typeface;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;

import java.util.ArrayList;


/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link PalletSize.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link PalletSize#newInstance} factory method to
 * create an instance of this fragment.
 */


public class PalletSize extends Fragment {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private Activity myAct;
    private View me;

    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";
    private Button bSearch;
    private EditText etWidth, etLength, etMaxGap;
    private TableLayout resultTable;

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    private OnFragmentInteractionListener mListener;

    public PalletSize() {
        // Required empty public constructor

    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment PalletSize.
     */
    // TODO: Rename and change types and number of parameters
    public static PalletSize newInstance(String param1, String param2) {
        PalletSize fragment = new PalletSize();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }

    }

    private void searchForPallet(int mWidth, int mLenght, int mMaxGap){
        PalletXmlParser myParser = new PalletXmlParser(myAct);
        SearchEngine se = new SearchEngine(myParser.getPalletsArray());
        resetTable();
        addHeaders();
        ArrayList<Integer> listWL = se.getClosestWL(mWidth, mLenght,mMaxGap);
        ArrayList<Integer> listLW = se.getClosestWL(mLenght,mWidth, mMaxGap);

        int i = 0;
        while (i<listWL.size()){
            System.out.println("FoundWWWH: " + se.palletsArray.get(listWL.get(i)));
            addRow(se.palletsArray.get(listWL.get(i)).getName(),
                    se.palletsArray.get(listWL.get(i)).getW(),
                    se.palletsArray.get(listWL.get(i)).getL(),
                    se.palletsArray.get(listWL.get(i)).getLoc());
            i++;
        }
        i = 0;
        while (i<listLW.size()){
            System.out.println("FoundHHHW: " + se.palletsArray.get(listLW.get(i)));
            addRow(se.palletsArray.get(listLW.get(i)).getName(),
                    se.palletsArray.get(listLW.get(i)).getW(),
                    se.palletsArray.get(listLW.get(i)).getL(),
                    se.palletsArray.get(listLW.get(i)).getLoc());
            i++;
        }
    }

    private void addHeaders(){
        LinearLayout tmpL = new LinearLayout(resultTable.getContext());
        LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(
                ViewGroup.LayoutParams.WRAP_CONTENT,
                ViewGroup.LayoutParams.WRAP_CONTENT
        );
        TextView t1 = new TextView(tmpL.getContext());
        t1.setText("Name");
        TextView t2 = new TextView(tmpL.getContext());
        t2.setText("Width");
        TextView t3 = new TextView(tmpL.getContext());
        t3.setText("Lenght");
        TextView t4 = new TextView(tmpL.getContext());
        t4.setText("Possible loc");
        t1.setTypeface(null, Typeface.BOLD);
        t2.setTypeface(null, Typeface.BOLD);
        t3.setTypeface(null, Typeface.BOLD);
        t4.setTypeface(null, Typeface.BOLD);
        TableRow tmpr = new TableRow(tmpL.getContext());
        tmpr.addView(t1);
        tmpr.addView(t2);
        tmpr.addView(t3);
        tmpr.addView(t4);
        //tmpL.setLayoutParams(params);
        resultTable.addView(tmpr);
    }

    private void addRow(String name, int w, int l, String loc){
        LinearLayout tmpL = new LinearLayout(resultTable.getContext());
        LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(
                ViewGroup.LayoutParams.WRAP_CONTENT,
                ViewGroup.LayoutParams.WRAP_CONTENT
        );

        TextView t1 = new TextView(tmpL.getContext());
        t1.setText(name);
        TextView t2 = new TextView(tmpL.getContext());
        t2.setText("" + w);
        TextView t3 = new TextView(tmpL.getContext());
        t3.setText("" + l);
        TextView t4 = new TextView(tmpL.getContext());
        t4.setText(loc);
        //t1.setLayoutParams(params);
        //tmpL.addView(t1);
        //tmpL.addView(t2);
        //tmpL.addView(t3);
        TableRow tmpr = new TableRow(tmpL.getContext());
        tmpr.addView(t1);
        tmpr.addView(t2);
        tmpr.addView(t3);
        tmpr.addView(t4);
        //tmpL.setLayoutParams(params);
        resultTable.addView(tmpr);
    }

    private void resetTable(){
        resultTable.removeAllViews();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View tmp = inflater.inflate(R.layout.fragment_pallet_size, container, false);
        me = tmp;
        etWidth = (EditText)me.findViewById(R.id.etWidth);
        etLength = (EditText)me.findViewById(R.id.etLength);
        etMaxGap = (EditText)me.findViewById(R.id.etMaxGap);

        bSearch = (Button) me.findViewById(R.id.bSearch);
        bSearch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                searchForPallet(Integer.parseInt(etWidth.getText().toString()), Integer.parseInt(etLength.getText().toString()),Integer.parseInt(etMaxGap.getText().toString()));
                Log.v("aaa","a:" + etWidth.getText().toString());
            }
        });
        resultTable = (TableLayout) me.findViewById(R.id.tlResult);
        return tmp;
    }

    // TODO: Rename method, update argument and hook method into UI event
    public void onButtonPressed(Uri uri) {
        if (mListener != null) {
            mListener.onFragmentInteraction(uri);
        }
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        myAct = (Activity) context;

        if (context instanceof OnFragmentInteractionListener) {
            mListener = (OnFragmentInteractionListener) context;
        } else {
            //throw new RuntimeException(context.toString()
              //      + " must implement OnFragmentInteractionListener");
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }

    /**
     * This interface must be implemented by activities that contain this
     * fragment to allow an interaction in this fragment to be communicated
     * to the activity and potentially other fragments contained in that
     * activity.
     * <p>
     * See the Android Training lesson <a href=
     * "http://developer.android.com/training/basics/fragments/communicating.html"
     * >Communicating with Other Fragments</a> for more information.
     */
    public interface OnFragmentInteractionListener {
        // TODO: Update argument type and name
        void onFragmentInteraction(Uri uri);
    }
}
