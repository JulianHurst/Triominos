package com.juju.triominos.dummy;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.view.LayoutInflater;
import android.widget.EditText;
import android.widget.TextView;
import android.content.Context;

import com.juju.triominos.PlayerDetailActivity;
import com.juju.triominos.R;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Helper class for providing sample content for user interfaces created by
 * Android template wizards.
 * <p/>
 * TODO: Replace all uses of this class before publishing your app.
 */
public class DummyContent {
    static int nb;

    /**
     * An array of sample (dummy) items.
     */
    public static List<DummyItem> ITEMS = new ArrayList<DummyItem>();

    /**
     * A map of sample (dummy) items, by ID.
     */
    public static Map<String, DummyItem> ITEM_MAP = new HashMap<String, DummyItem>();
/*
    static {
        // Add 3 sample items.


        addItem(new DummyItem("1", "Joueur 1"));
        addItem(new DummyItem("2", "Joueur 2"));
        addItem(new DummyItem("3", "Joueur 3"));
    }
*/

    public static void init(String name) {
        //String s=String.valueOf(id);
        String s = String.valueOf(nb);
        addItem(new DummyItem(s, name));
        nb++;
    }

    private static void addItem(DummyItem item) {
        ITEMS.add(item);
        ITEM_MAP.put(item.id, item);
    }

    /**
     * A dummy item representing a piece of content.
     */
    public static class DummyItem {
        public String id;
        public String content;
        public String name;
        public int score;
        public int penalty;

        public DummyItem(String id, String content) {
            this.id = id;
            this.content = content;
            this.name=content;
            this.score = 0;
            this.penalty = 0;
        }

        @Override
        public String toString() {
            return content;
        }
    }
}
