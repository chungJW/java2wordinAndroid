package com.example.testjavatoword;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import word.api.interfaces.IDocument;
import word.utils.Utils;
import word.w2004.Document2004;
import word.w2004.Document2004.Encoding;
import word.w2004.elements.BreakLine;
import word.w2004.elements.Heading1;
import word.w2004.elements.Paragraph;
import android.os.Bundle;
import android.os.Environment;
import android.app.Activity;
import android.util.Log;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;

public class MainActivity extends Activity {
	IDocument myDoc = new Document2004();
	EditText et1;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		et1 = (EditText)findViewById(R.id.editText1);
		Button b1 = (Button)findViewById(R.id.button1);
		b1.setOnClickListener(new OnClickListener(){

			
			public void onClick(View arg0) {
				
				IDocument myDoc = null;
				
				File newDocument = new File(Environment.getExternalStorageDirectory().getPath()+"/myFile.doc");
				Log.d("java2word","Create File finish!");
				PrintWriter writer = null;
				
							
							
					try {
						writer = new PrintWriter(newDocument);
					} catch (FileNotFoundException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					myDoc = new Document2004();
					myDoc.encoding(Encoding.UTF_8);
					myDoc.addEle(BreakLine.times(1).create());
					myDoc.addEle(Heading1.with("Insert Image Details").create());

					
					myDoc.addEle(Paragraph
			                .with("This document inside the paragraph, coming from '/src/test/resources/dtpick.gif': "
			                        + Picture.from_FULL_LOCAL_PATHL(
			                                Utils.getAppRoot()+
			                                        Environment.getExternalStorageDirectory().getPath()+"/pic.jpg"
			                                        /*from your Device's RootDirectory*/)
			                                .getContent()+et1.getText().toString()));
					Log.d("java2word","Add Image FINISH");
					
					String text = myDoc.getContent();
				 	writer.println(text);
		            writer.close();
			
			}			
		});
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

}
