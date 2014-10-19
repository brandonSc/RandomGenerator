// 
// HomeScreen.java
// @author Brandon Schurman, Kurt Engsig
// 
package com.kb.randomizer;

import java.util.ArrayList;

import com.example.randomgenerator.R;

import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

/**
 * Main activity and main application controller
 */
public class HomeScreen extends Activity 
{
	private static int ANIM_CYCLES = 1;
	private static int ANIM_DURATION = 100;
	
	protected RelativeLayout layout;	// main layout containing widgets
	protected TextView msgText; 		// temp display for random results
	protected Button flipButton; 		// random heads or tails
	protected Button rollButton; 		// random 1-6, modifiable # of dice
	protected Button shuffleButton;		// shuffle the order of a list of pics or text
	protected Button divideButton;  	// divide pics or text into teams size x
	// ... two more buttons to add
	
	protected ArrayList<ImageView> images; // a list of images taken by the user to work with
	
	
	/**
	 * This method is the called automatically after startup
	 * it is analogous to a constructor
	 */
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_home_screen);
		
		/* init UI Elements */
		layout = (RelativeLayout)findViewById(R.id.layout);
		flipButton = (Button)findViewById(R.id.button1);
		rollButton = (Button)findViewById(R.id.button3);
		shuffleButton = (Button)findViewById(R.id.button2);
		divideButton = (Button)findViewById(R.id.button4);
		msgText = (TextView)findViewById(R.id.textView1);
		images = new ArrayList<ImageView>();
		
		/* setup UI event handlers */
		flipButton.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				new Thread(new Runnable(){
					@Override
					public void run(){
						// animate loading type thing
						String msg0 = ".";
						String msg1 = "..";
						String msg2 = "...";
						for( int i=0; i<ANIM_CYCLES; i++ ){
							try {
								setMsgText(msg0);
								Thread.sleep(ANIM_DURATION);
								setMsgText(msg1);
								Thread.sleep(ANIM_DURATION);
								setMsgText(msg2);
								Thread.sleep(ANIM_DURATION);
								setMsgText(msg1);
								Thread.sleep(ANIM_DURATION);
								setMsgText(msg0);
								Thread.sleep(ANIM_DURATION);								
							} catch( InterruptedException e ){
								e.printStackTrace();
							}
						}
						int coin = (int)Math.round(Math.random());
						String msg = coin == 0 ? "Heads" : "Tails";
						System.out.println(msg);
						setMsgText(msg);	
					}
				}).start();
			}
		});
		
		rollButton.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				new Thread(new Runnable(){
					@Override
					public void run(){
						// animate loading type thing
						String msg0 = ".";
						String msg1 = "..";
						String msg2 = "...";
						for( int i=0; i<ANIM_CYCLES; i++ ){
							try {
								// yes duplicate code here, but it is prob temporary anyways
								setMsgText(msg0);
								
								Thread.sleep(ANIM_DURATION);
								setMsgText(msg1);
								Thread.sleep(ANIM_DURATION);
								setMsgText(msg2);
								Thread.sleep(ANIM_DURATION);
								setMsgText(msg1);
								Thread.sleep(ANIM_DURATION);
								setMsgText(msg0);
								Thread.sleep(ANIM_DURATION);								
							} catch( InterruptedException e ){
								e.printStackTrace();
							}
						}
						int die = (int)(1+Math.round(Math.random()*5));
						System.out.println(die);
						setMsgText(""+die);	
					}
				}).start();
			}
		});
		
		shuffleButton.setOnClickListener(new OnClickListener(){
			@Override
			public void onClick(View v) {
				openCamera();
			}
		});
		
	}
	
	/**
	 * This method is called after "save" is clicked when taking a picture
	 * it saves the picture data then draws it to the screen via drawPicture()
	 */
	@Override
	protected void onActivityResult(int requestCode, int resultCode, Intent data) {
      super.onActivityResult(requestCode, resultCode, data);
      Bitmap bp = (Bitmap) data.getExtras().get("data");
//      imgView.setImageBitmap(bp);
      ImageView img = new ImageView(this);
      img.setImageBitmap(bp);
      images.add(img);
      drawPicture(img);
   }
	
	/**
	 * Call this method when you want to prompt the user to add a picture 
	 */
	public void openCamera(){
		Intent intent = new Intent(android.provider.MediaStore.ACTION_IMAGE_CAPTURE);
		startActivityForResult(intent, 0);
	}
	
	/**
	 * Draws a single picture to the display, next to existing picutres
	 * @param img ImaveView data to display
	 */
	public void drawPicture( ImageView img ){
		// TODO::: this method needs much work,
		// need to change where we draw images,
		// need to consider taking many images and how to display/wrap around these
		// **** might be easier to use a ListView ****
		// if used a list, we wouldnt have to call .setX/setY() and could support older android SDK
		int i = images.size();
		img.setX((i*200)+10);  // <-- limits us to recent versions android 
		img.setY(100);
		img.setMaxHeight(20);
		img.setMaxWidth(20);
		img.setMinimumHeight(20);
		img.setMinimumWidth(20);
		img.bringToFront();
		layout.addView(img);
	}
	
	/**
	 * draw all pictures, only useful when reinitializing the screen
	 */
	public void drawAllPictures(){
		for( int i=0; i<images.size(); i++ ){
			ImageView img = images.get(i);
			drawPicture(img);
//			img.setX((i*50)+10);
//			img.setY(100);
//			img.setMaxHeight(20);
//			img.setMaxWidth(20);
//			img.setMinimumHeight(20);
//			img.setMinimumWidth(20);
//			img.bringToFront();
//			layout.addView(img);
		}
	}
		
	/**
	 * Sets the text of the small TextView at the top of the screen
	 * using this for animating coin toss / dice roll
	 * @param msg string to set TextView to
	 */
	public void setMsgText( final String msg ){
		runOnUiThread(new Runnable(){
			@Override
			public void run() {
				msgText.setText(msg);	
			} 
		});
	}
	

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.activity_home_screen, menu);
		return true;
	}
}
