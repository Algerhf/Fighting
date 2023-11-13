package com.cuibaby.eat.star;

import android.content.Context;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.GradientDrawable;

public class ColorUtils {

    public static int getRandomColor() {
        StringBuffer buffer = new StringBuffer();
        buffer.append("#");
        for (int i = 0; i < 6; i++) {
            buffer.append(arr[(int) (Math.random() * arr.length)]);
        }
        return Color.parseColor(buffer.toString());
    }

    private static String[] arr = new String[]{
            "0",
            "1",
            "2",
            "3",
            "4",
            "5",
            "6",
            "7",
            "8",
            "9",
            "a",
            "b",
            "c",
            "d",
            "e",
            "f"
    };


    public static Drawable getRandomDrawable(Context context) {
        GradientDrawable drawable = new GradientDrawable();
        drawable.setOrientation(GradientDrawable.Orientation.TR_BL);
        drawable.setCornerRadius(Tools.dip2px(context, 10));
        drawable.setColors(new int[]{
                getRandomColor(),
                getRandomColor(),
                getRandomColor(),
                getRandomColor(),
        });
        return drawable;
    }

    public static Drawable getRandomDrawable(Context context, float radiusDp) {
        GradientDrawable drawable = new GradientDrawable();
        drawable.setOrientation(GradientDrawable.Orientation.TR_BL);
        drawable.setCornerRadius(Tools.dip2px(context, radiusDp));
        drawable.setColors(new int[]{
                getRandomColor(),
                getRandomColor(),
                getRandomColor(),
                getRandomColor(),
        });
        return drawable;
    }

}
