package com.android.brother.entities;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by sagar on 08-06-2017.
 */

public class Brother implements Parcelable {

    private int brotherId;
    private String brotherName;
    private String whyJoined;
    private String brotherPicture;
    private String brotherMajor;
    private String brotherCrossSemester;
    private String brotherFunFact;

    public Brother(int brotherId, String brotherName, String whyJoined, String brotherPicture, String brotherMajor, String brotherCrossSemester, String brotherFunFact) {
        this.brotherId = brotherId;
        this.brotherName = brotherName;
        this.whyJoined = whyJoined;
        this.brotherPicture = brotherPicture;
        this.brotherMajor = brotherMajor;
        this.brotherCrossSemester = brotherCrossSemester;
        this.brotherFunFact = brotherFunFact;
    }

    protected Brother(Parcel in) {
        brotherId = in.readInt();
        brotherName = in.readString();
        whyJoined = in.readString();
        brotherPicture = in.readString();
        brotherMajor = in.readString();
        brotherCrossSemester = in.readString();
        brotherFunFact = in.readString();
    }

    public static final Creator<Brother> CREATOR = new Creator<Brother>() {
        @Override
        public Brother createFromParcel(Parcel in) {
            return new Brother(in);
        }

        @Override
        public Brother[] newArray(int size) {
            return new Brother[size];
        }
    };

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeInt(brotherId);
        parcel.writeString(brotherName);
        parcel.writeString(whyJoined);
        parcel.writeString(brotherPicture);
        parcel.writeString(brotherMajor);
        parcel.writeString(brotherCrossSemester);
        parcel.writeString(brotherFunFact);
    }

    public int getBrotherId() {
        return brotherId;
    }

    public String getBrotherName() {
        return brotherName;
    }

    public String getWhyJoined() {
        return whyJoined;
    }

    public String getBrotherPicture() {
        return brotherPicture;
    }

    public String getBrotherMajor() {
        return brotherMajor;
    }

    public String getBrotherCrossSemester() {
        return brotherCrossSemester;
    }

    public String getBrotherFunFact() {
        return brotherFunFact;
    }
}
