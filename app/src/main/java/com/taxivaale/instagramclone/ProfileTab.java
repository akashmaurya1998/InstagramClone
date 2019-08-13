package com.taxivaale.instagramclone;


import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.parse.ParseException;
import com.parse.ParseUser;
import com.parse.SaveCallback;
import com.shashank.sony.fancytoastlib.FancyToast;


/**
 * A simple {@link Fragment} subclass.
 */
public class ProfileTab extends Fragment {


    private EditText edtProfileName, edtProfileBio, edtProfileProfession, edtProfileHobbies, edtProfileFavSport;
    private Button btnUpdate;

    public ProfileTab() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_profile_tab, container, false);

        edtProfileName = view.findViewById(R.id.edtProfileName);
        edtProfileBio = view.findViewById(R.id.edtProfileBio);
        edtProfileProfession = view.findViewById(R.id.edtProfilePro);
        edtProfileHobbies = view.findViewById(R.id.edtProfileHobbies);
        edtProfileFavSport = view.findViewById(R.id.edtProfileFavSport);

        final ParseUser parseUser = ParseUser.getCurrentUser();

        if (parseUser.get("profileName") == null){

        }else {
            edtProfileName.setText(parseUser.get("profileName").toString());

        }

        if (parseUser.get("profileFavSport") == null){

        } else {
            edtProfileFavSport.setText(parseUser.get("profileFavSport").toString());

        }

        if (parseUser.get("profileHobbies") == null){

        } else {
            edtProfileHobbies.setText(parseUser.get("profileHobbies").toString());

        }

        if (parseUser.get("profileProfession") == null){

        } else {

            edtProfileProfession.setText(parseUser.get("profileProfession").toString());

        }

        if (parseUser.get("profileBio") == null){

        } else {
            edtProfileBio.setText(parseUser.get("profileBio").toString());

        }


        btnUpdate = view.findViewById(R.id.btnUpdate);

        btnUpdate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                parseUser.put("profileName", edtProfileName.getText().toString());
                parseUser.put("profileBio", edtProfileBio.getText().toString());
                parseUser.put("profileProfession", edtProfileProfession.getText().toString());
                parseUser.put("profileHobbies", edtProfileHobbies.getText().toString());
                parseUser.put("profileFavSport", edtProfileFavSport.getText().toString());

                parseUser.saveInBackground(new SaveCallback() {
                    @Override
                    public void done(ParseException e) {
                        if (e == null) {
                            FancyToast.makeText(getContext(), "Info Updated", FancyToast.LENGTH_SHORT, FancyToast.INFO, false).show();
                        }
                        else {
                            FancyToast.makeText(getContext(), e.getMessage(), FancyToast.LENGTH_SHORT, FancyToast.ERROR, false).show();
                        }
                    }
                });

            }
        });

        return view;
    }

}
