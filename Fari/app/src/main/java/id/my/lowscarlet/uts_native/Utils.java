package id.my.lowscarlet.uts_native;

import android.content.Context;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;

public class Utils {
    public static EditText createEditText(Context context, String hint) {
        EditText et = new EditText(context);
        LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(
                LinearLayout.LayoutParams.MATCH_PARENT,
                LinearLayout.LayoutParams.WRAP_CONTENT
        );
        et.setLayoutParams(params);
        et.setHint(hint);
        return et;
    }
    public static TextView createTextView(Context context) {
        TextView tv = new TextView(context);
        return tv;
    }
}
