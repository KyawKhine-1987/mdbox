package com.witts.mdbox.activity;

import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Rect;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.MotionEvent;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;

import com.witts.mdbox.R;
import com.witts.mdbox.model.WebServiceResult;
import com.witts.mdbox.model.enums.MDBCode;
import com.witts.mdbox.util.StringUtil;

import uk.co.chrisjenx.calligraphy.CalligraphyContextWrapper;

/**
 * Created by winhlaingtun on 8/22/15.
 */
public class BasedActivity extends AppCompatActivity {
    protected ProgressDialog progressDialog;
    protected boolean isShowingMessage;
    protected boolean hasMotion;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if(getActionBar() != null)
            getActionBar().setDisplayShowHomeEnabled(false);
        progressDialog = new ProgressDialog(this, AlertDialog.THEME_HOLO_LIGHT);
        progressDialog.setMessage(this.getString(R.string.progress_dialog_loading));
        progressDialog.setCancelable(false);
    }

    public boolean isNetWorkAvailable(){
        ConnectivityManager cm = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo netInfo = cm.getActiveNetworkInfo();
        if(netInfo!=null&&netInfo.isAvailable()&&netInfo.isConnected()){
            return true;
        }
        return false;
    }

    /**
     * method is used for checking valid email id format.
     *
     * @param result
     * @return boolean true for valid false for invalid
     */

//    public boolean handleError(WebServiceResult<?> result){
//        String message = null;
//        if(result.hasError()){
//            if (result.getMDBCode() == MDBCode.INVALID_ACCESS_TOKEN) {
//                Intent intent = new Intent(this, LanguageActivity.class);
//                intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
//                finish();
//                startActivity(intent);
//            }
//            else{
//                message = getMessage(result);
//                this.showAlert(message);
//            }
//            return false;
//
//        }
//        return true;
//    }

//    public String getMessage(WebServiceResult<?> result){
//
//        String message = "";
//
//        if(result.getMDBCode() == MDBCode.NO_INTERNET_CONNECTION_AVAILABLE){
//            message = getString(R.string.alert_no_internet_connection);
//        }
//        else{
//            message = StringUtil.isEmpty(result.getMessage())?
//                    getString(R.string.alert_application_error):result.getMessage();
//        }
//        return message;
//    }


    public synchronized void showAlert(String msg){
        if(isShowingMessage)return;
        isShowingMessage = true;
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setMessage(msg)
                .setCancelable(false)
                .setPositiveButton("OK", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        //do things
                        //dialog.cancel();
                        isShowingMessage = false;
                    }
                });
        try{


            AlertDialog alert = builder.create();

            alert.show();
        }catch (Exception e) {
            System.out.println("display dialog error");
        }
        //Toast.makeText(getApplication(), msg, Toast.LENGTH_LONG).show();
    }

    @Override
    protected void attachBaseContext(Context newBase) {
        super.attachBaseContext(CalligraphyContextWrapper.wrap(newBase));
    }

    public void onStop(){
        dismissProgressDialog();
        super.onStop();
    }

    public void showProgressDialog(){
        if(progressDialog!=null&&!progressDialog.isShowing()){
            progressDialog.show();
        }
    }

    public  void dismissProgressDialog(){
        if(progressDialog!=null&&progressDialog.isShowing()){
            progressDialog.dismiss();
        }
    }

    public void hideKeyBoard() {
        InputMethodManager methodManager = (InputMethodManager)getSystemService(INPUT_METHOD_SERVICE);
        View view = getCurrentFocus();
        if(view != null) {
            methodManager.hideSoftInputFromWindow(view.getWindowToken(), InputMethodManager.HIDE_NOT_ALWAYS);
        }
    }

    public void hideKeyBoard(View view) {
        InputMethodManager methodManager = (InputMethodManager)getSystemService(INPUT_METHOD_SERVICE);
        if(view != null) {
            methodManager.hideSoftInputFromWindow(view.getWindowToken(), InputMethodManager.HIDE_NOT_ALWAYS);
        }
    }

    //touch to hide keyboard
    @Override
    public boolean dispatchTouchEvent(MotionEvent ev) {
        if (ev.getAction() == MotionEvent.ACTION_UP && !hasMotion) {

            /**
             * It gets into the above IF-BLOCK if anywhere the screen is touched.
             */

            View v = getCurrentFocus();
            if ( v instanceof EditText) {


                /**
                 * Now, it gets into the above IF-BLOCK if an EditText is already in focus, and you tap somewhere else
                 * to take the focus away from that particular EditText. It could have 2 cases after tapping:
                 * 1. No EditText has focus
                 * 2. Focus is just shifted to the other EditText
                 */

                Rect outRect = new Rect();
                v.getGlobalVisibleRect(outRect);
                if (!outRect.contains((int)ev.getRawX(), (int)ev.getRawY())) {
                    v.clearFocus();
                    InputMethodManager imm = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
                    imm.hideSoftInputFromWindow(v.getWindowToken(), 0);
                }
            }
        }

        if(ev.getAction() == MotionEvent.ACTION_SCROLL || ev.getAction() == MotionEvent.ACTION_MOVE){
            hasMotion = true;
        }
        if(ev.getAction() == MotionEvent.ACTION_CANCEL || ev.getAction() == MotionEvent.ACTION_UP){
            hasMotion = false;
        }
        return super.dispatchTouchEvent(ev);
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        overridePendingTransition(R.anim.slide_in_reverse, R.anim.slide_out_reverse);
    }

    public void showKeyBoard(View v){
        InputMethodManager imm = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
        imm.showSoftInput(v, InputMethodManager.SHOW_IMPLICIT);
    }
}
