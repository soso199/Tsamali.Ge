package ge.idevelopers.tsamali;

import android.util.Log;

import com.google.firebase.iid.FirebaseInstanceId;
import com.google.firebase.iid.FirebaseInstanceIdService;

/**
 * Created by soso on 4/3/17.
 */

public class MyFirebaseInstanceIDservice extends FirebaseInstanceIdService {
    private static final String TAG="MyFirebaseInsIDservice";

    @Override
    public void onTokenRefresh() {

        String refreshedToken=FirebaseInstanceId.getInstance().getToken();
        Log.d(TAG,"New Token: "+refreshedToken);

    }
}
