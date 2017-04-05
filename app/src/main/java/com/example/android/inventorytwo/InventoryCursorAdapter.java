package com.example.android.inventorytwo;

import android.content.ContentResolver;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.net.Uri;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Adapter;
import android.widget.Button;
import android.widget.CursorAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.android.inventorytwo.data.InventoryContract.InventoryEntry;

public class InventoryCursorAdapter extends CursorAdapter {

    public static final String LOG_TAG = Adapter.class.getSimpleName();
    private Context mContexts;

    public InventoryCursorAdapter(Context context, Cursor c) {
        super(context, c, 0);
        mContexts = context;
    }

    @Override
    public View newView(Context context, Cursor cursor, ViewGroup viewGroup) {
        View view = LayoutInflater.from(context).inflate(R.layout.list_item, viewGroup, false);
        ProductViewHolder productViewHolder = new ProductViewHolder(view);
        view.setTag(productViewHolder);
        return view;
    }

    @Override
    public void bindView(final View view, Context context, Cursor cursor) {

        ProductViewHolder productViewHolder = (ProductViewHolder) view.getTag();

        final String name = cursor.getString(cursor.getColumnIndex(InventoryEntry.COLUMN_ITEM_NAME));
        final int quantity = cursor.getInt(cursor.getColumnIndex(InventoryEntry.COLUMN_ITEM_QUANTITY));
        final double priceVal = cursor.getDouble(cursor.getColumnIndex(InventoryEntry.COLUMN_ITEM_PRICE));
        final int id = cursor.getInt(cursor.getColumnIndex(InventoryEntry._ID));

        final Cursor cursorVal = cursor;
        String priceStatement = "Price $" + priceVal;
        String quantityStatement = " Quantity " + quantity;
        final Uri uri = InventoryEntry.CONTENT_URI;

        productViewHolder.mNameTextView.setText(name);
        productViewHolder.mPriceTextView.setText(priceStatement);
        productViewHolder.mQuantityTextView.setText(quantityStatement);

        productViewHolder.mSaleButtons.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.d(LOG_TAG, "Quantity " + quantity);
                ContentResolver resolver = view.getContext().getContentResolver();
                ContentValues values = new ContentValues();
                if (quantity > 0) {
                    int quantity = cursorVal.getInt(cursorVal.getColumnIndex(InventoryEntry.COLUMN_ITEM_QUANTITY));
                    int quantityValue = quantity;
                    values.put(InventoryEntry.COLUMN_ITEM_QUANTITY, --quantityValue);
                    resolver.update(
                            uri,
                            values,
                            null,
                            null);
                    mContexts.getContentResolver().notifyChange(uri, null);
                }
            }
        });
    }

    public static class ProductViewHolder {
        public final TextView mNameTextView;
        public final TextView mQuantityTextView;
        public final TextView mPriceTextView;
        public final Button mSaleButtons;

        public ProductViewHolder(View view) {
            mNameTextView = (TextView) view.findViewById(R.id.name);
            mQuantityTextView = (TextView) view.findViewById(R.id.quantity);
            mPriceTextView = (TextView) view.findViewById(R.id.list_price);
            mSaleButtons = (Button) view.findViewById(R.id.sell_button);
        }
    }
}