package multitouch.multitouchapp;

import android.content.DialogInterface;
import android.graphics.Canvas;
import android.media.Image;
import android.support.v7.app.AppCompatActivity;
import android.support.v4.content.ContextCompat;
import android.os.Bundle;
import android.app.Activity;
import android.view.View;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.util.AttributeSet;
import android.view.MotionEvent;
import java.util.UUID;
import android.provider.MediaStore;
import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.view.View.OnClickListener;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity implements OnClickListener{
    private CanvasView customCanvas;
    private ImageButton currPaint, drawBtn, eraseBtn;
    private float smallBrush, mediumBrush, largeBrush;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        customCanvas = (CanvasView) findViewById(R.id.mainCanvas);
        LinearLayout paintLayout = (LinearLayout) findViewById(R.id.paintColor);

        currPaint = (ImageButton) paintLayout.getChildAt(0);
        currPaint.setImageDrawable(ContextCompat.getDrawable(getApplicationContext()
                ,R.drawable.paint_pressed));

        smallBrush = getResources().getInteger(R.integer.small_size);
        mediumBrush = getResources().getInteger(R.integer.medium_size);
        largeBrush = getResources().getInteger(R.integer.large_size);

        drawBtn = (ImageButton)findViewById(R.id.buttonDraw);
        drawBtn.setOnClickListener(this);

        customCanvas.setBrushSize(mediumBrush);

        eraseBtn = (ImageButton)findViewById(R.id.buttonErase);
        eraseBtn.setOnClickListener(this);

    }

    public void clearCanvas(View v) {
        customCanvas.clearCanvas();
    }

    public void paintClicked(View view) {
        customCanvas.setErase(false);
        customCanvas.setBrushSize(customCanvas.getLastBrushSize());
        if (view != currPaint) {
            ImageButton imgView = (ImageButton)view;
            String color = view.getTag().toString();
            customCanvas.setColor(color);
            imgView.setImageDrawable(ContextCompat.getDrawable(getApplicationContext(), R.drawable.paint_pressed));
            currPaint.setImageDrawable(ContextCompat.getDrawable(getApplicationContext(), R.drawable.paint));
            currPaint=(ImageButton)view;


        }
    }


    @Override
    public void onClick(View view) {
        if (view.getId() == R.id.buttonDraw) {
            final Dialog brushDialog = new Dialog(this);
            brushDialog.setTitle("Brush size:");
            brushDialog.setContentView(R.layout.brush_chooser);
            ImageButton smallBtn = (ImageButton)brushDialog.findViewById(R.id.small_brush);
            smallBtn.setOnClickListener(new OnClickListener(){
                @Override
                public void onClick(View v) {
                    customCanvas.setErase(false);
                    customCanvas.setBrushSize(smallBrush);
                    customCanvas.setLastBrushSize(smallBrush);
                    brushDialog.dismiss();
                }
            });
            ImageButton mediumBtn = (ImageButton)brushDialog.findViewById(R.id.medium_brush);
            mediumBtn.setOnClickListener(new OnClickListener(){
                @Override
                public void onClick(View v) {
                    customCanvas.setErase(false);
                    customCanvas.setBrushSize(mediumBrush);
                    customCanvas.setLastBrushSize(mediumBrush);
                    brushDialog.dismiss();
                }
            });

            ImageButton largeBtn = (ImageButton)brushDialog.findViewById(R.id.large_brush);
            largeBtn.setOnClickListener(new OnClickListener(){
                @Override
                public void onClick(View v) {
                    customCanvas.setErase(false);
                    customCanvas.setBrushSize(largeBrush);
                    customCanvas.setLastBrushSize(largeBrush);
                    brushDialog.dismiss();
                }
            });
            brushDialog.show();
        } else if(view.getId()==R.id.buttonErase){
            //switch to erase - choose size
            final Dialog brushDialog = new Dialog(this);
            brushDialog.setTitle("Eraser size:");
            brushDialog.setContentView(R.layout.brush_chooser);
            ImageButton smallBtn = (ImageButton)brushDialog.findViewById(R.id.small_brush);
            smallBtn.setOnClickListener(new OnClickListener(){
                @Override
                public void onClick(View v) {
                    customCanvas.setErase(true);
                    customCanvas.setBrushSize(smallBrush);
                    brushDialog.dismiss();
                }
            });
            ImageButton mediumBtn = (ImageButton)brushDialog.findViewById(R.id.medium_brush);
            mediumBtn.setOnClickListener(new OnClickListener(){
                @Override
                public void onClick(View v) {
                    customCanvas.setErase(true);
                    customCanvas.setBrushSize(mediumBrush);
                    brushDialog.dismiss();
                }
            });
            ImageButton largeBtn = (ImageButton)brushDialog.findViewById(R.id.large_brush);
            largeBtn.setOnClickListener(new OnClickListener(){
                @Override
                public void onClick(View v) {
                    customCanvas.setErase(true);
                    customCanvas.setBrushSize(largeBrush);
                    brushDialog.dismiss();
                }
            });
            brushDialog.show();
        }
    }
}
