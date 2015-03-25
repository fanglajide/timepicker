package com.squareup.timessquare;

import java.util.Date;

public interface CalendarCellDecorator {
  void decorate(CalendarCellView cellView, Date date);
    void decorate(CalendarCellView cellView,MonthCellDescriptor cell);
}
