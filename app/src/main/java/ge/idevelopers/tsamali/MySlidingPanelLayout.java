package ge.idevelopers.tsamali;

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

//        float xTouchPosDp = ev.getX()/getResources().getDisplayMetrics().density;
//
//        if(!isOpen()){
//            if(xTouchPosDp < 30){
//                //If the panel is closed (map pane being entirely shown)
//                //and the touch event occur on the first 30 horizontal dp's
//                //Let the SlidingPaneLayout onTouchEvent() method handle the
//                //motion event alone (the GoogleMap object won't receive the event
//                //and depending on the movement, the panel will open)
//                return true;
//            }else{
//                //Now, if the panel is closed, but the touch event occur
//                //on the rest of the screen, let the GoogleMap object handle
//                //the motion event.
//                return false;
//            }
//        }
//        else {
//            //If the panel is opened, let the SlidingPaneLayout handle the
//            //motion event normally.
            //return super.onInterceptTouchEvent(ev);
        return false;
      //  }
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {

       // return  super.onTouchEvent(event);
        return false;
    }

}
