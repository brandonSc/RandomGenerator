// 
// HomeScreen.java
// @author Brandon Schurman
// 
package com.example.randomgenerator;
import org.w3c.dom.Text;

import android.os.Bundle;
import android.app.Activity;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.TextView;

/**
 * Main activity and main application controller
 */
public class HomeScreen extends Activity 
{
	public static int ANIM_CYCLES = 1;
	public static int ANIM_DURATION = 100;
	
	protected TextView msgText; 		// temp display for random results
	protected Button flipButton; 	// random heads or tails
	protected Button rollButton; 	// random 1-6, modifiable # of dice
	protected Button shuffleButton; // shuffle the order of a list of pics or text
	protected Button divideButton;  // divide pics or text into teams size x
	// ... two more buttons to add
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_home_screen);
		
		/* init UI Elements */
		flipButton = (Button)findViewById(R.id.button1);
		rollButton = (Button)findViewById(R.id.button3);
		shuffleButton = (Button)findViewById(R.id.button2);
		divideButton = (Button)findViewById(R.id.button4);
		msgText = (TextView)findViewById(R.id.textView1);
		
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
		
	}
	
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
