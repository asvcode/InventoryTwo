package com.example.android.inventorytwo.data;

import android.net.Uri;
import android.content.ContentResolver;
import android.provider.BaseColumns;

public final class InventoryContract {

    public static final String CONTENT_AUTHORITY = "com.example.android.inventorytwo";
    public static final Uri BASE_CONTENT_URI = Uri.parse("content://" + CONTENT_AUTHORITY);
    public static final String PATH_INVENTORY = "pets";


    private InventoryContract() {
    }

    public static final class InventoryEntry implements BaseColumns {

        public static final Uri CONTENT_URI = Uri.withAppendedPath(BASE_CONTENT_URI, PATH_INVENTORY);

        public static final String CONTENT_LIST_TYPE =
                ContentResolver.CURSOR_DIR_BASE_TYPE + "/" + CONTENT_AUTHORITY + "/" + PATH_INVENTORY;

        public static final String CONTENT_ITEM_TYPE =
                ContentResolver.CURSOR_ITEM_BASE_TYPE + "/" + CONTENT_AUTHORITY + "/" + PATH_INVENTORY;

        public final static String TABLE_NAME = "pets";

        public final static String _ID = BaseColumns._ID;

        public final static String COLUMN_ITEM_NAME = "name";

        public final static String COLUMN_ITEM_QUANTITY = "quantity";

        public final static String COLUMN_ITEM_PRICE = "price";

        public final static String COLUMN_ITEM_PICTURE = "picture";
    }

}

