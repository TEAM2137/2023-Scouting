package com.team2137.rapidreactscouting;

import android.text.InputFilter;
import android.text.Spanned;

class InputRangeFilter implements InputFilter {
    private final int min;
    private final int max;

    public InputRangeFilter(int min, int max) {
        this.min = min;
        this.max = max;
    }

    public InputRangeFilter(String min, String max) {
        this.min = Integer.parseInt(min);
        this.max = Integer.parseInt(max);
    }

    @Override
    public CharSequence filter(CharSequence source, int start, int end, Spanned dest, int dstart, int dend) {
        try {
            int input = Integer.parseInt(dest.toString() + source.toString());
            if (isInRange(min, max, input))
                // Accept original.
                return null;
            else
                // Too large/small.
                return "";
        } catch (NumberFormatException nfe) {
            // Not a number.
            return "";
        }
    }

    private boolean isInRange(int min, int max, int input) {
        return max > min ? input >= min && input <= max : input >= max && input <= min;
    }
}