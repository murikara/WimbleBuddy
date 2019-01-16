package com.example.wimblebuddy.fragment;

import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.os.Bundle;
import android.support.v4.app.DialogFragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.Toast;

import com.example.wimblebuddy.R;
import com.example.wimblebuddy.database.model.Gamecard;
import com.example.wimblebuddy.database.repository.GamecardRepository;
import com.example.wimblebuddy.viewmodel.HomeViewModel;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Locale;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class CreateGameFragment extends DialogFragment {

    final Calendar myCalendar = Calendar.getInstance();
    private static final String TAG = "CreateGameFragment";
    final Calendar timeCalendar = Calendar.getInstance();
    private HomeViewModel mViewModel;
    private boolean full = false;

    @BindView(R.id.dateTextView)
    TextView dateTextView;
    @BindView(R.id.timeFromTextView)
    TextView timeFromTextView;
    @BindView(R.id.timeTillTextView)
    TextView timeTillTextView;
    @BindView(R.id.locationEditText)
    EditText locationEditText;

    int selectedTimeFrom;
    int selectedTimeTill;

    private String defaultTime = "00:00";
    private String defaultDate = "-/-/-";

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_create_game, container, false);
        ButterKnife.bind(this, v);
        mViewModel = new HomeViewModel(getActivity());
        // Do all the stuff to initialize your custom view
        return v;
    }

    DatePickerDialog.OnDateSetListener date = new DatePickerDialog.OnDateSetListener() {
        @Override
        public void onDateSet(DatePicker view, int year, int monthOfYear,
                              int dayOfMonth) {
            myCalendar.set(Calendar.YEAR, year);
            myCalendar.set(Calendar.MONTH, monthOfYear);
            myCalendar.set(Calendar.DAY_OF_MONTH, dayOfMonth);
            updateLabel();
        }
    };

    public void setTime(final boolean fromOrTill){
        int hour = timeCalendar.get(Calendar.HOUR_OF_DAY);
        int hourTill = selectedTimeFrom + 1;
        int minute = Calendar.MINUTE;
        TimePickerDialog mTimePicker;
        if(fromOrTill) {
            mTimePicker = new TimePickerDialog(getActivity(), new TimePickerDialog.OnTimeSetListener() {
                @Override
                public void onTimeSet(TimePicker timePicker, int selectedHour, int selectedMinute) {
                    timeFromTextView.setText(selectedHour + ":00");
                    selectedTimeFrom = selectedHour;
                }
            }, hour, minute, true);//Yes 24 hour time
        } else {
            Log.e(TAG, "setTime: starting from hour " + hourTill);
            mTimePicker = new TimePickerDialog(getActivity(), new TimePickerDialog.OnTimeSetListener() {
                @Override
                public void onTimeSet(TimePicker timePicker, int selectedHour, int selectedMinute) {
                    if(selectedTimeFrom < selectedHour) {
                        timeTillTextView.setText(selectedHour + ":00");
                        selectedTimeTill = selectedHour;
                    } else {
                        Toast.makeText(getActivity(), getString(R.string.fragment_create_game_error_time),
                                Toast.LENGTH_LONG).show();
                    }
                }
            }, hourTill, minute, true);//Yes 24 hour time
        }
        mTimePicker.setTitle(getString(R.string.fragment_create_game_timepicker_title));
        mTimePicker.show();
    }

    private void updateLabel() {
        String myFormat = "MM/dd/yy"; //In which you need put here
        SimpleDateFormat sdf = new SimpleDateFormat(myFormat, Locale.UK);
        dateTextView.setText(sdf.format(myCalendar.getTime()));
    }

    @OnClick(R.id.dateButton)
    public void setDate(){
        if(getActivity() != null) {
            new DatePickerDialog(getActivity(), date, myCalendar
                    .get(Calendar.YEAR), myCalendar.get(Calendar.MONTH),
                    myCalendar.get(Calendar.DAY_OF_MONTH)).show();
        } else {
            Log.e(TAG, "setDate: Context is null from getActivity" );
        }
        Log.e(TAG, "setDate: out of method " );
    }

    @OnClick(R.id.timeFromButton)
    public void setTimeFrom(){
        if(getActivity() != null){
            setTime(true);
        }
    }

    @OnClick(R.id.timeTillButton)
    public void setTimeTill(){
        if(getActivity() != null){
            setTime(false);
        }
    }

    @OnClick(R.id.createGameButton)
    public void createGame(){
        if((!dateTextView.getText().equals(defaultDate)) && (!timeFromTextView.getText().equals(defaultTime))
        && (!timeTillTextView.getText().equals(defaultTime)) && locationEditText.getText() != null){
            String locationString = locationEditText.getText().toString();
            Log.e(TAG, "createGame: location " + locationString);
            String dateString = dateTextView.getText().toString();
            Log.e(TAG, "createGame: date " + dateString);
            String timeFromString = timeFromTextView.getText().toString();
            Log.e(TAG, "createGame: timeFrom " + timeFromString);
            String timeTillString = timeTillTextView.getText().toString();
            Log.e(TAG, "createGame: timeTill " + timeTillString);

            Gamecard newCard = new Gamecard(locationString, dateString, timeFromString, timeTillString, full);
            mViewModel.insert(newCard);
            Toast.makeText(getActivity(), getString(R.string.fragment_create_game_succes),
                    Toast.LENGTH_LONG).show();
//            Bundle args = new Bundle();
//            args.putSerializable("Gamecard", newCard);
//            getTargetFragment().setArguments(args);
            dismiss();
        } else {
            Toast.makeText(getActivity(), getString(R.string.fragment_create_game_error_info),
                    Toast.LENGTH_LONG).show();
        }
    }
}
