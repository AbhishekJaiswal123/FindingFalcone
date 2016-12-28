package com.abhishek.findingfalcone.api;

import com.abhishek.findingfalcone.utils.Constants;
import com.android.volley.DefaultRetryPolicy;
import com.android.volley.NetworkResponse;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.HttpHeaderParser;
import com.android.volley.toolbox.JsonArrayRequest;
import org.json.JSONArray;
import org.json.JSONException;
import java.io.UnsupportedEncodingException;


/**
 * Created by abhishek on 22/12/16.
 */

public class GetApi extends JsonArrayRequest {

    private Response.Listener<JSONArray> listener;

    public GetApi(String url, Response.Listener<JSONArray> listener, Response.ErrorListener errorListener) {
        super(url, listener, errorListener);
        this.listener = listener;
        setRetryPolicy(new DefaultRetryPolicy(Constants.API_TIMEOUT_MS,
                DefaultRetryPolicy.DEFAULT_MAX_RETRIES,
                DefaultRetryPolicy.DEFAULT_BACKOFF_MULT));
    }

    @Override
    protected Response<JSONArray> parseNetworkResponse(NetworkResponse response) {
        try
        {
            String json = new String(response.data, HttpHeaderParser.parseCharset(response.headers));
            JSONArray obj = new JSONArray(json);
            return Response.success(obj, HttpHeaderParser.parseCacheHeaders(response));
        }
        catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        catch (JSONException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    protected VolleyError parseNetworkError(VolleyError volleyError) {
        return super.parseNetworkError(volleyError);
    }

    @Override
    protected void deliverResponse(JSONArray response) {
         listener.onResponse(response);
    }


}
