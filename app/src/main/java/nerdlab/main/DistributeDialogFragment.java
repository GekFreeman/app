package nerdlab.main;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.DialogFragment;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.TextView;

import com.example.guideapplication.R;

public class DistributeDialogFragment extends DialogFragment
{
	private Button mCancleButton;
	
	private TextView mDateTextView;
	
	private DatePicker mDatePicker;
	
	private boolean mIsDatePickerAppear = false;
	
	private void setDateTime()
	{
		int activityYear = mDatePicker.getYear() ;
		int activityMonth = mDatePicker.getMonth();
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
				if (mIsDatePickerAppear)
				{
					mDatePicker.setVisibility(View.GONE);
					mIsDatePickerAppear = false;
				}
				else 
				{
					mDatePicker.setVisibility(View.VISIBLE);
					mIsDatePickerAppear = true;
				}
				
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
