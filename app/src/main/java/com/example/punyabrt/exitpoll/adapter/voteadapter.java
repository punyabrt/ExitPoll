package com.example.punyabrt.exitpoll.adapter;

import android.content.Context;
import android.content.res.AssetManager;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.Drawable;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.punyabrt.exitpoll.R;
import com.example.punyabrt.exitpoll.model.Votemodel;



import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.util.List;

public class voteadapter extends ArrayAdapter<Votemodel> {

    private Context mContext;
    private int mResource;
    private List<Votemodel> mVoteItemList;

    public voteadapter(@NonNull Context context,  /// เอาข้อมูลมาแสดงเป็น list
                           int resource,
                           @NonNull List<Votemodel> phoneItemList) {
        super(context, resource, phoneItemList);
        this.mContext = context;
        this.mResource = resource;
        this.mVoteItemList = phoneItemList;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        LayoutInflater inflater = (LayoutInflater) mContext.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View view = inflater.inflate(mResource, parent, false);

        TextView scoreTextView = view.findViewById(R.id.show_textView);
        ImageView imageView = view.findViewById(R.id.show_imageView);

        Votemodel item = mVoteItemList.get(position);
        String score = item.score;
        String filename = item.image;

        scoreTextView.setText(score);

        AssetManager am = mContext.getAssets();
        try {
            InputStream is = am.open(filename);
            Drawable drawable = Drawable.createFromStream(is, "");
            imageView.setImageDrawable(drawable);
        } catch (IOException e) {
            File privateDir = mContext.getFilesDir();
            File logoFile = new File(privateDir, filename);

            Bitmap bitmap = BitmapFactory.decodeFile(logoFile.getAbsolutePath(), null);
            imageView.setImageBitmap(bitmap);

            e.printStackTrace();
        }

        return view;
    }
}
