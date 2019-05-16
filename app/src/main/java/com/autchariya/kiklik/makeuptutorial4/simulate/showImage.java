package com.autchariya.kiklik.makeuptutorial4.simulate;

import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Base64;
import android.util.Log;
import android.view.Display;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.autchariya.kiklik.makeuptutorial4.MainActivity;
import com.autchariya.kiklik.makeuptutorial4.R;
import com.autchariya.kiklik.makeuptutorial4.simulate.model.Makeup;
import com.autchariya.kiklik.makeuptutorial4.simulate.service.Makeup_api;
import com.bumptech.glide.Glide;

import org.w3c.dom.Text;

import java.io.File;
import java.nio.charset.StandardCharsets;
import java.util.List;
import java.util.Objects;
import java.util.concurrent.TimeUnit;

import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.OkHttpClient;
import okhttp3.RequestBody;
import pl.aprilapps.easyphotopicker.DefaultCallback;
import pl.aprilapps.easyphotopicker.EasyImage;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class showImage extends AppCompatActivity {

    ImageView gallery_btn ;
    ImageView camera_btn ;
    ImageView splash_view;
    Bundle bundle ;
    OkHttpClient okHttpClient;
    File file;
    RequestBody type_;
    RequestBody style_;

    String Mode_;
    String Style_;
    Intent splash_intent;
    String flag;
    FrameLayout frame_layout;
    TextView close ,sure;
    Dialog mydialog;
    TextView text_load;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show_image);
        //splash_intent = new Intent(showImage.this,splash_screen.class);

        Toast.makeText(showImage.this, "โปรดเลือกรูปภาพที่เห็นใบหน้าชัดเจน", Toast.LENGTH_LONG).show();

        splash_view = findViewById(R.id.splash_view);
        frame_layout = findViewById(R.id.fragment_container);
        SharedPreferences prefs = getSharedPreferences("makeup_info", MODE_PRIVATE);
        Mode_ = prefs.getString("MODE"," ");
        Style_ = prefs.getString("STYLE"," ");

        Log.d("TAG_PREF","RESULT_MODE_STYLE = " +"MODE__"+ Mode_ +"STYLE__"+ Style_);

        gallery_btn = findViewById(R.id.gallery_button);
        camera_btn = findViewById(R.id.camera_button);

        mydialog = new Dialog(this);

        okHttpClient = new OkHttpClient.Builder()
                .connectTimeout(1, TimeUnit.MINUTES)
                .readTimeout(1, TimeUnit.MINUTES)
                .writeTimeout(1, TimeUnit.MINUTES)
                .build();
        bundle = new Bundle();

        gallery_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                EasyImage.openGallery(showImage.this,0);
            }
        });

        camera_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                EasyImage.openCamera(showImage.this,0);
            }
        });

    }

    @Override
    protected void onActivityResult(final int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        EasyImage.handleActivityResult(requestCode, resultCode, data, this, new DefaultCallback() {
            @Override
            public void onImagesPicked(@NonNull List<File> imageFiles, EasyImage.ImageSource source, int type) {

                Glide.with(showImage.this)
                        .load(R.drawable.splash_gif)
                        .into(splash_view);
                frame_layout.setVisibility(View.GONE);
                splash_view.setVisibility(View.VISIBLE);
            file = new File(imageFiles.get(0).getPath());
            type_ = RequestBody.create(MediaType.parse("text/plain"),Mode_);
            style_ = RequestBody.create(MediaType.parse("text/plain"),Style_);

            final MultipartBody.Part filePart = MultipartBody.Part.createFormData("image", file.getName(), RequestBody.create(MediaType.parse("image/*"), file));

            Retrofit.Builder builder = new Retrofit.Builder()
                    .baseUrl("http://10.199.66.95:8000/")
                    .client(okHttpClient)
                    .addConverterFactory(GsonConverterFactory.create());

                Retrofit retrofit = builder.build();
                Makeup_api client = retrofit.create(Makeup_api.class);

                Call<Makeup> call = client.apply_makeup(type_,style_,filePart);

                call.enqueue(new Callback<Makeup>() {
                    @Override
                    public void onResponse(Call<Makeup> call, Response<Makeup> response) {

                        if(!response.isSuccessful()){

                            finish();
                            startActivity(getIntent());


                        }
                        frame_layout.setVisibility(View.VISIBLE);
                        splash_view.setVisibility(View.GONE);
                        String   type_id = response.body().getType();
                        String   style_id = response.body().getStyle();
                        String   image_path = response.body().getImage();

                        byte[] img64 = image_path.getBytes(StandardCharsets.UTF_8);
                        byte[] decode_image64 = Base64.decode(img64, Base64.DEFAULT);

                        bundle.putByteArray("decode_image64",decode_image64);
                        preview_fragment frag_arg = new preview_fragment();
                        frag_arg.setArguments(bundle);


                        getSupportFragmentManager()
                                .beginTransaction()
                                .add(R.id.fragment_container,frag_arg)
                                .commit();

                    }

                    @Override
                    public void onFailure(Call<Makeup> call, Throwable t) {

                        Toast.makeText(showImage.this, "โปรดถ่ายภาพให้อยู่ในหน้าตรง", Toast.LENGTH_LONG).show();
                        Intent intent = new Intent(showImage.this, MainActivity.class);
                        startActivity(intent);
                        finish();



                    }
                });


            }

            @Override
            public void onImagePickerError(Exception e, EasyImage.ImageSource source, int type) {


            }
        });
    }


}
