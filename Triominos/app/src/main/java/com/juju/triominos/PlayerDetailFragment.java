package com.juju.triominos;

import android.app.AlertDialog;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Button;
import android.view.View.OnLongClickListener;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.app.Dialog;



import com.juju.triominos.dummy.DummyContent;

/**
 * A fragment representing a single Player detail screen.
 * This fragment is either contained in a {@link PlayerListActivity}
 * in two-pane mode (on tablets) or a {@link PlayerDetailActivity}
 * on handsets.
 */
public class PlayerDetailFragment extends Fragment {
    /**
     * The fragment argument representing the item ID that this fragment
     * represents.
     */
    public static final String ARG_ITEM_ID = "item_id";

    /**
     * The dummy content this fragment is presenting.
     */
    private DummyContent.DummyItem mItem;




    /**
     * Mandatory empty constructor for the fragment manager to instantiate the
     * fragment (e.g. upon screen orientation changes).
     */
    public PlayerDetailFragment() {
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments().containsKey(ARG_ITEM_ID)) {
            // Load the dummy content specified by the fragment
            // arguments. In a real-world scenario, use a Loader
            // to load content from a content provider.
            mItem = DummyContent.ITEM_MAP.get(getArguments().getString(ARG_ITEM_ID));
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
       final View rootView = inflater.inflate(R.layout.fragment_player_detail, container, false);

        // Show the dummy content as text in a TextView.
        if (mItem != null) {
            ((TextView) rootView.findViewById(R.id.player_detail)).setText(mItem.content);
        }
       // ((TextView) rootView.findViewById(R.id.player_detail)).setText(mItem.content);
        ((TextView) rootView.findViewById(R.id.player_score)).setText(""+mItem.score);

        Button pickb;
        Button twos;
        Button bridge;
        Button addb;
        Button reset;
        Button aide;

        pickb=(Button)rootView.findViewById(R.id.pick_up);
        twos=(Button)rootView.findViewById(R.id.twoside);
        bridge=(Button)rootView.findViewById(R.id.bridge);
        addb=(Button)rootView.findViewById(R.id.add);
        reset=(Button)rootView.findViewById(R.id.reset);
        aide=(Button)rootView.findViewById(R.id.aide);

        pickb.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                mItem.score-=5;
                ((TextView) rootView.findViewById(R.id.player_score)).setText(""+mItem.score);
                mItem.penalty++;
                System.out.println(mItem.penalty);
                if(mItem.penalty==4) {
                    mItem.score -= 5;
                    mItem.penalty = 0;
                    ((TextView) rootView.findViewById(R.id.player_score)).setText(""+mItem.score);
                }
                mItem.content=mItem.name+"   "+mItem.score;
                ((TextView) rootView.findViewById(R.id.player_detail)).setText(mItem.content);
            }
        });
/*
        pickb.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {
                mItem.score -= 10;
                ((TextView) rootView.findViewById(R.id.player_score)).setText("" + mItem.score);
                mItem.penalty = 0;
                return true;
            }
        });
*/
        twos.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                mItem.score+=50;
                ((TextView) rootView.findViewById(R.id.player_score)).setText(""+mItem.score);
                mItem.penalty = 0;
                mItem.content=mItem.name+"   "+mItem.score;
                ((TextView) rootView.findViewById(R.id.player_detail)).setText(mItem.content);
            }
        });

        twos.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {
                mItem.score+=60;
                ((TextView) rootView.findViewById(R.id.player_score)).setText("" + mItem.score);
                mItem.penalty = 0;
                mItem.content=mItem.name+"   "+mItem.score;
                ((TextView) rootView.findViewById(R.id.player_detail)).setText(mItem.content);
                return true;
            }
        });

        bridge.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                mItem.score+=40;
                ((TextView) rootView.findViewById(R.id.player_score)).setText(""+mItem.score);
                mItem.penalty = 0;
                mItem.content=mItem.name+"   "+mItem.score;
                ((TextView) rootView.findViewById(R.id.player_detail)).setText(mItem.content);
            }
        });

        reset.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                mItem.score=0;
                ((TextView) rootView.findViewById(R.id.player_score)).setText(""+mItem.score);
                mItem.penalty = 0;
                mItem.content=mItem.name+"   "+mItem.score;
                ((TextView) rootView.findViewById(R.id.player_detail)).setText(mItem.content);
            }
        });

        aide.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                AlertDialog.Builder builder=new AlertDialog.Builder(getActivity());
                builder.setMessage(R.string.aidedetail);
                builder.setTitle(R.string.aidebutton);
                builder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        dialog.dismiss();
                    }
                });
                builder.create();
                builder.show();
            }
        });

        addb.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                AlertDialog.Builder builder=new AlertDialog.Builder(getActivity());
                LayoutInflater inflater = getActivity().getLayoutInflater();
                builder.setView(inflater.inflate(R.layout.add_dialog, null));


                builder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int id) {
                        Dialog f=(Dialog) dialog;
                        EditText nb1=(EditText)f.findViewById(R.id.nb1);
                        EditText nb2=(EditText)f.findViewById(R.id.nb2);
                        EditText nb3=(EditText)f.findViewById(R.id.nb3);

                        int a,b,c;
                        if(nb1.getText().length() ==0)
                            nb1.setText("0");
                        if(nb2.getText().length() ==0)
                            nb2.setText("0");
                        if(nb3.getText().length() ==0)
                            nb3.setText("0");
                            a = Integer.parseInt(nb1.getText().toString());
                            b = Integer.parseInt(nb2.getText().toString());
                            c = Integer.parseInt(nb3.getText().toString());

                        mItem.score +=a+b+c;
                        ((TextView) rootView.findViewById(R.id.player_score)).setText("" + mItem.score);
                        mItem.content=mItem.name+"   "+mItem.score;
                        ((TextView) rootView.findViewById(R.id.player_detail)).setText(mItem.content);
                    }
                });
                builder.setNegativeButton(R.string.negative, new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        dialog.dismiss();
                    }
                });
                final AlertDialog dialog = builder.create();
                dialog.getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_VISIBLE);
                dialog.show();
                mItem.penalty = 0;
            }
        });
        return rootView;
    }


}
