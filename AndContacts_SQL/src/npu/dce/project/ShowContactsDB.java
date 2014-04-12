package npu.dce.project;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.SimpleCursorAdapter;

public class ShowContactsDB extends Activity
{
	private Button showall,showbyid;
	private EditText txt_searchbox;
	private MyDBAdapter myDBAdapter;
	private ListView lv;
	private Cursor cursor;
	private SimpleCursorAdapter dataAdapter;
	
	@Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.showdb);
    
    	showbyid = (Button) findViewById(R.id.btn_showbyid);
    	showall = (Button) findViewById(R.id.btn_showall);
    	
    	lv = (ListView)findViewById(R.id.lv_contacts);
    	
    	myDBAdapter = new MyDBAdapter(this);
    	myDBAdapter.open();
    	
  	  	displayListView("ALL");

        showbyid.setOnClickListener(new View.OnClickListener() {	
			@Override
			public void onClick(View v) {
				txt_searchbox = (EditText) findViewById(R.id.txt_searchbox); 
				displayListView(txt_searchbox.getText().toString());
			}
		});
        
    	showall.setOnClickListener(new View.OnClickListener() {	
			@Override
			public void onClick(View v) {
				
				displayListView("ALL");
			}
		});
    }

	@SuppressLint("NewApi")
	private void displayListView(String whichView)
	{
		if (whichView.equals("ALL"))//which view -> all Contacts
		{
			cursor = myDBAdapter.getAllEntries();
		}
		else //which view -> by Contact Name
		{
			cursor = myDBAdapter.getEntry(whichView);
		}
		 
		  // The desired columns to be bound
		  String[] columns = new String[] {
		    MyDBAdapter.KEY_ID,
		    MyDBAdapter.KEY_NAME,
		    MyDBAdapter.KEY_PHONE,
		    MyDBAdapter.KEY_EMAIL,
		    MyDBAdapter.KEY_POSTALADDR
		  };
		 
		  // the XML defined views which the data will be bound to
		  int[] to = new int[] { 
		    R.id.lv_id,
		    R.id.lv_name,
		    R.id.lv_phone,
		    R.id.lv_email,
		    R.id.lv_add
		  };

	  dataAdapter = new SimpleCursorAdapter(this, R.layout.each_contact_lv_item,cursor, columns, to,0);
	  lv.setAdapter(dataAdapter);

	 }
}
