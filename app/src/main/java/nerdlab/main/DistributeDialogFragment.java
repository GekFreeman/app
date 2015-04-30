package nerdlab.main;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.support.v4.app.DialogFragment;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.guideapplication.R;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

import model.Image;

public class DistributeDialogFragment extends DialogFragment
{
	final int PICK_IMAGE=1;

	private Button mCancleButton;
	
	private TextView mDateTextView;
	
	private DatePicker mDatePicker;

	private ImageView chooseFromAlbum;

	private ImageView chooseEmojicons;

	private Uri imageUri;

	private boolean mIsDatePickerAppear = false;
	
	private void setDateTime()
	{
		int activityYear = mDatePicker.getYear() ;
		int activityMonth = mDatePicker.getMonth()+1;
		int activityDay = mDatePicker.getDayOfMonth();
		mDateTextView.setText(activityYear + "." + activityMonth + "." + activityDay);
	}
	
	@Override
	public void onCreate(Bundle savedInstanceState)
	{
		super.onCreate(savedInstanceState);
	}
	
	@Override
	public Dialog onCreateDialog(Bundle savedInstanceState)
	{
		View v = getActivity().getLayoutInflater().inflate(R.layout.dialog_fragment, null);
		
		//not create buttons here
		Dialog distributeDialog = new AlertDialog.Builder(getActivity())
										.setView(v)
										.create();

		chooseFromAlbum=(ImageView)v.findViewById(R.id.choose_from_album);

		chooseFromAlbum.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {

				File outputImage = new File(Environment.
						getExternalStorageDirectory(), "output_image.jpg");
				try {
					if (outputImage.exists()) {
						outputImage.delete();
					}
					outputImage.createNewFile();
				} catch (IOException e) {
					e.printStackTrace();
				}
				imageUri = Uri.fromFile(outputImage);
				Intent intent = new Intent();
				intent.setType("image/*");
				intent.setAction(intent.ACTION_GET_CONTENT);
				intent.putExtra("crop", true);
				intent.putExtra("scale", true);
				intent.putExtra(MediaStore.EXTRA_OUTPUT, imageUri);
				startActivityForResult(intent.createChooser(intent,"Select Picture"), PICK_IMAGE);
			}
		});




		chooseEmojicons=(ImageView)v.findViewById(R.id.choose_emoticon_icon);
		chooseEmojicons.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				Toast.makeText(getActivity(),"Emojicons",Toast.LENGTH_SHORT).show();
			}
		});
		mCancleButton = (Button)v.findViewById(R.id.cancleButton);
		mCancleButton.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View arg0) {
				getDialog().dismiss();
			}
		});


		mDatePicker = (DatePicker)v.findViewById(R.id.datePicker);
		mDatePicker.init(mDatePicker.getYear(), mDatePicker.getMonth(), mDatePicker.getDayOfMonth(), new DatePicker.OnDateChangedListener() {
			
			@Override
			public void onDateChanged(DatePicker view, int year, int monthOfYear,
					int dayOfMonth) {			
				setDateTime();
				
			}
		});
		
		mDateTextView = (TextView)v.findViewById(R.id.actionDateTextView);
		mDateTextView.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View arg0) {
				hideKeyboard();
				if (mIsDatePickerAppear) {
					mDatePicker.setVisibility(View.GONE);
					mIsDatePickerAppear = false;
				} else {
					mDatePicker.setVisibility(View.VISIBLE);
					mIsDatePickerAppear = true;
				}

			}
		});

		Button publish=(Button)v.findViewById(R.id.publish);
		publish.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				getDialog().dismiss();
			}
		});
		
		// initialize activity date.
		setDateTime();
		
		return distributeDialog;
	}


	private void hideKeyboard() {
		// Check if no view has focus:
		View view = getActivity().getCurrentFocus();
		if (view != null) {
			InputMethodManager inputManager = (InputMethodManager) getActivity().getSystemService(Context.INPUT_METHOD_SERVICE);
			inputManager.hideSoftInputFromWindow(view.getWindowToken(), InputMethodManager.HIDE_NOT_ALWAYS);
		}
	}


}
