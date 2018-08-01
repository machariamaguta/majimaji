package mfanyakazi.com.mobiwater;

import android.app.AlertDialog;
import android.app.Dialog;
import android.support.v4.app.DialogFragment;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.text.TextUtils;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.iid.FirebaseInstanceId;
import com.google.firebase.iid.InstanceIdResult;

import mfanyakazi.com.mobiwater.service.NetworkService;
import mfanyakazi.com.mobiwater.service.SendToken;
import mfanyakazi.com.mobiwater.utils.PrefUtils;

public class PhoneNumberDialogue extends DialogFragment implements View.OnClickListener {

    private Button cancelBtn, submitBtn;
    private EditText phoneNumberEt;
    private More more;
    private PrefUtils prefUtils;

    public PhoneNumberDialogue() {
    }

    public static PhoneNumberDialogue getInstance() {
        return new PhoneNumberDialogue();
    }

    @NonNull
    @Override
    public Dialog onCreateDialog(Bundle onSavedInstanceState) {
        LayoutInflater inflater = getActivity().getLayoutInflater();
        View view = inflater.inflate(R.layout.fragment_phonenumber, null);

        more = (More) getActivity();
        prefUtils = more.getPrefUtils();
        phoneNumberEt = (EditText) view.findViewById(R.id.phone_number_et);
        cancelBtn = (Button) view.findViewById(R.id.cancel_btn);
        submitBtn = (Button) view.findViewById(R.id.submit_btn);

        cancelBtn.setOnClickListener(this);
        submitBtn.setOnClickListener(this);

        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        builder.setView(view);

        return builder.create();

    }


    private void sendVolunteerDetails() {
        String phoneNumber = phoneNumberEt.getText().toString().trim();


        if (validateFields(phoneNumber)) {
            String formatedNumber = "254"+phoneNumber.substring(phoneNumber.length()-9);
            Log.e("formatedNo", formatedNumber);
            prefUtils.setPhoneNumber(phoneNumber);
            prefUtils.setHasSetPhoneNumber(true);
            if(prefUtils.getAppToken()!=null){
                String appToken = prefUtils.getAppToken();
                Log.e("tokenset",appToken);

                NetworkService service = MainApplication.getInstance().getNetworkService();
                SendToken sendToken = new SendToken(service);
                sendToken.send(appToken, formatedNumber);

            }
            dismiss();
        }
    }

    private boolean validateFields(String phoneNumber) {
        phoneNumberEt.setError(null);

        if (TextUtils.isEmpty(phoneNumber)) {
            phoneNumberEt.setError("full name is required");
            return false;
        }

        return true;
    }






    @Override
    public void onClick(View view) {
        int id = view.getId();
        switch (id){
            case R.id.submit_btn:
                sendVolunteerDetails();
                break;
            case R.id.cancel_btn:
                // dismiss dialog and do nothing
                dismiss();
                break;

        }



    }


}
