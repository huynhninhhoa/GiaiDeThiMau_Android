package vn.ntu.edu.nguyenthanhhuynh.dangkymonhoc;

import android.app.DatePickerDialog;
import android.content.Context;
import android.widget.DatePicker;

import java.util.Calendar;

public class MyDatePicker {
    Context context;
    Calendar calendar;

    OnMyDateChangeListener onDateChangedListener;

    public MyDatePicker(Context context, Calendar calendar, OnMyDateChangeListener onDateChangedListener) {
        this.context = context;
        this.calendar = calendar;
        this.onDateChangedListener = onDateChangedListener;
    }

    public void showMyDatePicker(){
        DatePickerDialog.OnDateSetListener listener = new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                calendar.set(year, month, dayOfMonth);
                if(onDateChangedListener != null)
                    onDateChangedListener.dateUpdate(calendar);
            }
        };

        DatePickerDialog datePickerDialog = new DatePickerDialog(
                context,
                listener,
                calendar.get(calendar.YEAR),
                calendar.get(calendar.MONTH),
                calendar.get(calendar.DAY_OF_MONTH));
        //calendar.get(calendar.DAY_OF_WEEK);

        datePickerDialog.show();
    }

    public static interface OnMyDateChangeListener{
        public void dateUpdate(Calendar calendar);
    }
}
