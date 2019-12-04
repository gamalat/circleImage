package cleancodesoft.com.uploadcircleimage;

import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.provider.MediaStore;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.URL;

import de.hdodenhof.circleimageview.CircleImageView;

public class MainActivity extends AppCompatActivity {
private CircleImageView profileImage;
ImageView profileImage2;
private static final int PICK_IMAGE =1;
Uri imageUri;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        profileImage=(CircleImageView) findViewById(R.id.profile_image);
     //   profileImage2=(ImageView) findViewById(R.id.profile_image2);
        profileImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent gallrey=new Intent();
                gallrey.setType("image/+");
                gallrey.setAction(Intent.ACTION_GET_CONTENT);
                startActivityForResult(Intent.createChooser(gallrey,"select picture"),PICK_IMAGE);
            }
        });
    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode==PICK_IMAGE || requestCode==RESULT_OK){
            imageUri=data.getData();
            profileImage.setImageURI(imageUri);
            Log.d("imageUrl", "onActivityResult: "+imageUri);
            try {
                Bitmap bitmap= MediaStore.Images.Media.getBitmap(getContentResolver(),imageUri);
                profileImage.setImageBitmap(bitmap);
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }

        }
    }
}
