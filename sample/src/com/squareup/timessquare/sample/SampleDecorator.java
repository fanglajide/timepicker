package com.squareup.timessquare.sample;

import android.text.SpannableString;
import android.text.Spanned;
import android.text.TextUtils;
import android.text.style.RelativeSizeSpan;
import com.squareup.timessquare.CalendarCellDecorator;
import com.squareup.timessquare.CalendarCellView;
import com.squareup.timessquare.MonthCellDescriptor;

import java.text.SimpleDateFormat;
import java.util.Date;

public class SampleDecorator implements CalendarCellDecorator {
    @Override
    public void decorate(CalendarCellView cellView, Date date) {
        String dateString = Integer.toString(date.getDate());
        SpannableString string = new SpannableString(dateString + "\ntitle");
        string.setSpan(new RelativeSizeSpan(0.5f), 0, dateString.length(),
                Spanned.SPAN_INCLUSIVE_EXCLUSIVE);
        cellView.setText(string);
    }

    @Override
    public void decorate(CalendarCellView cellView, MonthCellDescriptor cell) {
        if (!cell.isCurrentMonth()) {
            //cellView.setText(null);
            return;
        }

        if (cell.getRangeState() == MonthCellDescriptor.RangeState.NONE && !cell.isToday()&&!cell.isTommorow()&&!cell.isSelected()) return;

        Date date = cell.getDate();
        SimpleDateFormat sf = new SimpleDateFormat("yyyyMMdd");
        String dateStr = sf.format(date);
        // String day = DateUtil.getDateStrCompareToDay(dateStr);
        String day = "";
        if (cell.isToday()) day = "today";
        if (cell.isTommorow()) day = "tommorrow";


        if (TextUtils.isEmpty(day)) day = date.getDay() + "";

        if (cell.getRangeState() == MonthCellDescriptor.RangeState.FIRST) {
            String dateString = day;
            SpannableString string = new SpannableString(dateString + "\nIN");
            string.setSpan(new RelativeSizeSpan(0.5f), 0, dateString.length(),
                    Spanned.SPAN_INCLUSIVE_EXCLUSIVE);
            cellView.setText(string);
            return;

        } else if (cell.getRangeState() == MonthCellDescriptor.RangeState.LAST) {
            String dateString = day;
            SpannableString string = new SpannableString(dateString + "\nOUT");
            string.setSpan(new RelativeSizeSpan(0.5f), 0, dateString.length(),
                    Spanned.SPAN_INCLUSIVE_EXCLUSIVE);
            cellView.setText(string);
            return;
        }
        cellView.setText(day);

    }
}
