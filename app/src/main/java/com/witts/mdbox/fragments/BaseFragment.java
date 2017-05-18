package com.witts.mdbox.fragments;

import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.View;
import android.view.inputmethod.InputMethodManager;

import com.witts.mdbox.R;
import com.witts.mdbox.model.WebServiceResult;
import com.witts.mdbox.model.enums.MDBCode;
import com.witts.mdbox.util.StringUtil;

import static android.content.Context.INPUT_METHOD_SERVICE;

/**
 * Created by winhlaingtun on 1/29/17.
 */

public class BaseFragment extends Fragment {
    protected ProgressDialog progressDialog;
    protected boolean isShowingMessage;

    public BaseFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        progressDialog = new ProgressDialog(getActivity());
        progressDialog.setMessage(this.getString(R.string.progress_dialog_loading));
        progressDialog.setCancelable(false);
    }

//    /**
//     * method is used for checking valid email id format.
//     *
//     * @param result
//     * @return boolean true for valid false for invalid
//     */

//    public boolean handleError(WebServiceResult<?> result){
//        String message = null;
//        if(result.hasError()){
//            message = getMessage(result);
//            this.showAlert(message);
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
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
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
        InputMethodManager methodManager = (InputMethodManager) getActivity().getSystemService(INPUT_METHOD_SERVICE);
        View view = getActivity().getCurrentFocus();
        if(view != null) {
            methodManager.hideSoftInputFromWindow(view.getWindowToken(), InputMethodManager.HIDE_NOT_ALWAYS);
        }
    }

    public void showKeyBoard(View v){
        InputMethodManager imm = (InputMethodManager) getActivity().getSystemService(Context.INPUT_METHOD_SERVICE);
        imm.showSoftInput(v, InputMethodManager.SHOW_IMPLICIT);
    }
}
