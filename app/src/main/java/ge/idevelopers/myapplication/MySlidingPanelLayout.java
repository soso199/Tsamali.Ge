package ge.idevelopers.myapplication;

import android.content.Context;
import android.support.v4.widget.SlidingPaneLayout;
import android.util.AttributeSet;
import android.view.MotionEvent;

/**
 * Created by soso on 4/4/17.
 */

public class MySlidingPanelLayout  extends SlidingPaneLayout {
    public MySlidingPanelLayout(Context context) {
        super(context);
        // TODO Auto-generated constructor stub
    }

    public MySlidingPanelLayout(Context context, AttributeSet attrs,
                                int defStyle) {
        super(context, attrs, defStyle);
        // TODO Auto-generated constructor stub
    }

    public MySlidingPanelLayout(Context context, AttributeSet attrs) {
        super(context, attrs);
        // TODO Auto-generated constructor stub
    }

    // ===========================================================
// Methods for/from SuperClass/Interfaces
// ===========================================================
    @Override
    public boolean onInterceptTouchEvent(MotionEvent ev) {

            return false;
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {

        return  true;
    }

}
