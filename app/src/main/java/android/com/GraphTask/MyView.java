package android.com.GraphTask;

import android.content.Context;
import android.graphics.Typeface;
import android.support.annotation.StringRes;
import android.util.TypedValue;
import android.view.Gravity;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;

/**
 * Created by Алексей on 31.05.2015.
 */
public class MyView {
    static final int TABLE_ROW_MIN_WIDTH = 75;
    static final int TEXT_SIZE = 18;


    public static void fillNumberNodesToTableRow(Context context, TableLayout tableLayout,
                                          int quantityNodes){
        // строка для вывода номера вершины
        TableRow tableRow = new TableRow(context);

        TextView textView = new TextView(context);
        setText(textView, "№");

        tableRow.addView(textView);

        for (Integer i = 1; i <= quantityNodes; i++) {
            textView = new TextView(context);
            setText(textView, i.toString());
            tableRow.addView(textView);
        }

        tableLayout.addView(tableRow);

    }

    public static void viewArray(Context context, TableLayout tableLayout, String title, int arr[]){
        TableRow tableRow = new TableRow(context);

        // выводим в 1-ый столбец заголовок
        TextView textView = new TextView(context);
        setText(textView, title);
        /*textView.setText(title);
        textView.setMinWidth(TABLE_ROW_MIN_WIDTH);
        textView.setTextSize(TypedValue.COMPLEX_UNIT_DIP, TEXT_SIZE);
        textView.setTypeface(Typeface.SERIF, Typeface.NORMAL);
        textView.setGravity(Gravity.CENTER_HORIZONTAL);*/
        tableRow.addView(textView);

        for (int i = 0; i <arr.length; i++) {
            textView = new TextView(context);
            Integer tmp = arr[i];
            if (title.equals("ftr")){
                tmp++;
            }
            setText(textView, tmp.toString());
            /*textView.setText(tmp.toString());
            textView.setMinWidth(TABLE_ROW_MIN_WIDTH);
            textView.setTextSize(TypedValue.COMPLEX_UNIT_DIP, TEXT_SIZE);
            textView.setTypeface(Typeface.SERIF, Typeface.NORMAL);
            textView.setGravity(Gravity.CENTER_HORIZONTAL);*/
            tableRow.addView(textView);
        }

        tableLayout.addView(tableRow);
    }


    public static void setText(TextView textView, String text){
        textView.setText(text);
        textView.setMinWidth(TABLE_ROW_MIN_WIDTH);
        textView.setTextSize(TypedValue.COMPLEX_UNIT_DIP, TEXT_SIZE);
        textView.setTypeface(Typeface.SERIF, Typeface.NORMAL);
        textView.setGravity(Gravity.CENTER_HORIZONTAL);
    }

    public static void setText(TextView textView, @StringRes int id){
        textView.setText(id);
        textView.setMinWidth(TABLE_ROW_MIN_WIDTH);
        textView.setTextSize(TypedValue.COMPLEX_UNIT_DIP, TEXT_SIZE);
        textView.setTypeface(Typeface.SERIF, Typeface.NORMAL);
        textView.setGravity(Gravity.CENTER_HORIZONTAL);
    }

}
