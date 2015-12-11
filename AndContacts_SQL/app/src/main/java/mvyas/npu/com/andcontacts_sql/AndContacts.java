package mvyas.npu.com.andcontacts_sql;

import android.app.TabActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TabHost;
import android.widget.Toast;


public class AndContacts extends TabActivity {

	private static final int SHOW_CONTACTS = 0;
	private Button saveb,cancelb;
	private EditText txtname,txtemail,txtphone,txtpostaladd;
	private String strName,strEmail,strPhone,strPostalAdd;
	
	private MyDBAdapter myDBAdapter;
	
	TabHost mTabHost = null;
	
	
	@Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
        
        mTabHost = getTabHost(); 
        
        mTabHost.addTab(mTabHost.newTabSpec("tab_test1").setIndicator("Contacts", getResources().getDrawable(R.drawable.contact)).setContent(R.id.contactsLayout)); 
        mTabHost.addTab(mTabHost.newTabSpec("tab_test2").setIndicator("Music", getResources().getDrawable(R.drawable.music)).setContent(R.id.musicLayout)); 
        mTabHost.addTab(mTabHost.newTabSpec("tab_test3").setIndicator("Video", getResources().getDrawable(R.drawable.video)).setContent(R.id.videoLayout)); 
         
        mTabHost.setCurrentTab(0); 
    	saveb = (Button) findViewById(R.id.buttonsave);
    	cancelb = (Button) findViewById(R.id.buttoncancel);
    	
    	txtname = (EditText) findViewById(R.id.txtname);  	
    	txtemail = (EditText) findViewById(R.id.txtemail);
    	txtphone = (EditText) findViewById(R.id.txtphone);
    	txtpostaladd = (EditText) findViewById(R.id.txtpostaladdress);
    	
    	myDBAdapter = new MyDBAdapter(this);
    	myDBAdapter.open();
    	
    	//myDBAdapter.deleteAllEntries();
        
  	  	
    	saveb.setOnClickListener(new View.OnClickListener() {	
			@Override
			public void onClick(View v) {
				
			   	strName = txtname.getText().toString();  	
		    	strEmail = txtemail.getText().toString();
		    	strPhone = txtphone.getText().toString();
		    	strPostalAdd = txtpostaladd.getText().toString();
		    	
				DataModel newContact = new DataModel(4,strName,strEmail,strPhone,strPostalAdd);
	            myDBAdapter.insertEntry(newContact);
	            //updateArray();
	            
	            txtname.setText("");
	            txtemail.setText("");
	            txtphone.setText("");
	            txtpostaladd.setText("");
	            
	            Toast.makeText(AndContacts.this,"Data sucessfully saved into DataBase ! ",Toast.LENGTH_LONG).show();
			}
		});
        
    	
        cancelb.setOnClickListener(new View.OnClickListener() {	
			@Override
			public void onClick(View v) {
				
				setResult(RESULT_CANCELED, null);
				finish();
			}
		});
  	 }
  	
	public boolean onCreateOptionsMenu(Menu menu)
    {
        MenuInflater menuInflater = getMenuInflater();
        menuInflater.inflate(R.menu.menu, menu);
        return true;
    }
	public boolean onOptionsItemSelected(MenuItem item) {
	    super.onOptionsItemSelected(item);

	    switch (item.getItemId())
	    {
	    case R.id.filter_name:
	        Intent i = new Intent(this, ShowContactsDB.class);
	        startActivityForResult(i, SHOW_CONTACTS);
	        return true;

	    default:
	        return super.onOptionsItemSelected(item);
	    }
	  }
	  
	/*protected void addcontact() {
		ArrayList ops = new ArrayList();
		   int rawContactInsertIndex = ops.size();
		
		   ops.add(ContentProviderOperation.newInsert(RawContacts.CONTENT_URI)
		        .withValue(RawContacts.ACCOUNT_TYPE, null)
		        .withValue(RawContacts.ACCOUNT_NAME,null )
		      .build());
		
		   //phone field
		   ops.add(ContentProviderOperation.newInsert(ContactsContract.Data.CONTENT_URI)
		           .withValueBackReference(ContactsContract.Data.RAW_CONTACT_ID,rawContactInsertIndex)
		           .withValue(Data.MIMETYPE, Phone.CONTENT_ITEM_TYPE)
		           .withValue(Phone.NUMBER, txtphone.getText().toString())
		           .build());
		
		   //name field
		   ops.add(ContentProviderOperation.newInsert(Data.CONTENT_URI)
		           .withValueBackReference(Data.RAW_CONTACT_ID, rawContactInsertIndex)
		           .withValue(Data.MIMETYPE, StructuredName.CONTENT_ITEM_TYPE)
		           .withValue(StructuredName.DISPLAY_NAME, txtname.getText().toString())
		           .build());
		   
		   //email field
		   ops.add(ContentProviderOperation.newInsert(ContactsContract.Data.CONTENT_URI) 
		           .withValueBackReference(Data.RAW_CONTACT_ID, 0)
		
		   .withValue(ContactsContract.Data.MIMETYPE,ContactsContract.CommonDataKinds.Email.CONTENT_ITEM_TYPE)
		   .withValue(ContactsContract.CommonDataKinds.Email.DATA, txtemail.getText().toString())
		   .withValue(ContactsContract.CommonDataKinds.Email.TYPE, ContactsContract.CommonDataKinds.Email.TYPE_HOME)
		   .build());
	   
	   
		   try {
		       //update data to the database, operations are run in batchmode
		       getContentResolver().applyBatch(ContactsContract.AUTHORITY, ops);
		   } catch (RemoteException e) {
		       e.printStackTrace();
		   } catch (OperationApplicationException e) {
		       e.printStackTrace();
		   }
			
	}*/

    
    
}