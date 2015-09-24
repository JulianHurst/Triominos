package com.juju.triominos;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.view.Display;
import android.view.LayoutInflater;
import android.view.WindowManager;
import android.widget.Button;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.EditText;
import android.widget.TextView;

import com.juju.triominos.dummy.DummyContent;


/**
 * An activity representing a list of Players. This activity
 * has different presentations for handset and tablet-size devices. On
 * handsets, the activity presents a list of items, which when touched,
 * lead to a {@link PlayerDetailActivity} representing
 * item details. On tablets, the activity presents the list of items and
 * item details side-by-side using two vertical panes.
 * <p/>
 * The activity makes heavy use of fragments. The list of items is a
 * {@link PlayerListFragment} and the item details
 * (if present) is a {@link PlayerDetailFragment}.
 * <p/>
 * This activity also implements the required
 * {@link PlayerListFragment.Callbacks} interface
 * to listen for item selections.
 */
public class PlayerListActivity extends FragmentActivity
        implements PlayerListFragment.Callbacks {

    /**
     * Whether or not the activity is in two-pane mode, i.e. running on a tablet
     * device.
     */
    private boolean mTwoPane;
    static final boolean firstrun=true;
    //public boolean first;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_player_list);

        if (findViewById(R.id.player_detail_container) != null) {
            // The detail container view will be present only in the
            // large-screen layouts (res/values-large and
            // res/values-sw600dp). If this view is present, then the
            // activity should be in two-pane mode.
            mTwoPane = true;

            // In two-pane mode, list items should be given the
            // 'activated' state when touched.
            ((PlayerListFragment) getSupportFragmentManager()
                    .findFragmentById(R.id.player_list))
                    .setActivateOnItemClick(true);
        }
        // TODO: If exposing deep links into your app, handle intents here.
        Button aplay=(Button)findViewById(R.id.aplayer);
        Button aide=(Button)findViewById(R.id.aide);
        aplay.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                AlertDialog.Builder builder = new AlertDialog.Builder(PlayerListActivity.this);
                LayoutInflater inflater = PlayerListActivity.this.getLayoutInflater();
                builder.setView(inflater.inflate(R.layout.names_dialog, null));

                builder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int id) {
                        String name;
                        Dialog f = (Dialog) dialog;
                        EditText nb2 = (EditText) f.findViewById(R.id.name);
                        //if(!nb2.getText().toString().matches(""))
                        if(nb2.getText().toString().trim().length() > 0)
                            DummyContent.init(nb2.getText().toString());
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
                }
        });
        aide.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                AlertDialog.Builder builder=new AlertDialog.Builder(PlayerListActivity.this);
                builder.setMessage(R.string.aidelist);
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
        /*
        boolean firstrun = getSharedPreferences("PREFERENCE", MODE_PRIVATE).getBoolean("firstrun", true);
        if (firstrun) {
            AlertDialog.Builder builder = new AlertDialog.Builder(PlayerListActivity.this);
            LayoutInflater inflater = PlayerListActivity.this.getLayoutInflater();
            builder.setView(inflater.inflate(R.layout.number_players_dialog, null));

            builder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int id) {
                    final int nb;
                    Dialog f = (Dialog) dialog;
                    EditText nb1 = (EditText) f.findViewById(R.id.nb);
                    nb = Integer.parseInt(nb1.getText().toString());

                    for (int i = 0; i < nb; i++) {
                        AlertDialog.Builder builder = new AlertDialog.Builder(PlayerListActivity.this);
                        LayoutInflater inflater = PlayerListActivity.this.getLayoutInflater();
                        builder.setView(inflater.inflate(R.layout.names_dialog, null));
                        final int a = i;

                        builder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int id) {
                                String name;
                                Dialog f = (Dialog) dialog;
                                EditText nb2 = (EditText) f.findViewById(R.id.name);
                                DummyContent.init(a, nb2.getText().toString());
                            }
                        });
                        builder.setNegativeButton("annuler", new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int id) {
                                dialog.dismiss();
                            }
                        });
                        builder.create();
                        builder.show();
                    }
                }
            });
            builder.setNegativeButton("annuler", new DialogInterface.OnClickListener() {
                public void onClick(DialogInterface dialog, int id) {
                    dialog.dismiss();
                }
            });
            builder.create();
            builder.show();
            getSharedPreferences("PREFERENCE", MODE_PRIVATE).edit().putBoolean("firstrun",false).commit();

        }
        */

    }
/*
    public void onSaveInstanceState(Bundle savedInstanceState) {
        savedInstanceState.putBoolean("firstrun", false);
        super.onSaveInstanceState(savedInstanceState);
    }
*/
        /**
         * Callback method from {@link PlayerListFragment.Callbacks}
         * indicating that the item with the given ID was selected.
         */
    @Override
    public void onItemSelected(String id) {
        if (mTwoPane) {
            // In two-pane mode, show the detail view in this activity by
            // adding or replacing the detail fragment using a
            // fragment transaction.
            Bundle arguments = new Bundle();
            arguments.putString(PlayerDetailFragment.ARG_ITEM_ID, id);
            PlayerDetailFragment fragment = new PlayerDetailFragment();
            fragment.setArguments(arguments);
            getSupportFragmentManager().beginTransaction()
                    .replace(R.id.player_detail_container, fragment)
                    .commit();

        } else {
            // In single-pane mode, simply start the detail activity
            // for the selected item ID.
            Intent detailIntent = new Intent(this, PlayerDetailActivity.class);
            detailIntent.putExtra(PlayerDetailFragment.ARG_ITEM_ID, id);
            startActivity(detailIntent);
        }
    }
}
