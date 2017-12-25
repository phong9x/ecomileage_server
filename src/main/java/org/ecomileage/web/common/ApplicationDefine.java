/*
 * Created on 27 thg 9 2017 ( Time 11:37:08 )
 * Code by Kenny Phong
 */
package org.ecomileage.web.common;

public class ApplicationDefine {
	public enum UserRole {

		StandardMember((int)1), OfficalMember((int)2), SponsorMember((int)3), Admin((int)4) ;
		
		int code;

		UserRole(int code) {
			this.code = code;
		}

		public int getCode() {
			return code;
		}
	}
	
	public enum VehicleCode_TypeVehicle {

		Bike((short)1), Bus((short)2), Subway((short)3) ;
		
		short code;

		VehicleCode_TypeVehicle(short code) {
			this.code = code;
		}

		public short getCode() {
			return code;
		}
	}
	
	public enum MileageApply_Type {

		Bike((short)1), Tranfer((short)2) ;
		
		short code;

		MileageApply_Type(short code) {
			this.code = code;
		}

		public short getCode() {
			return code;
		}
	}
	
	public enum MileageApply_TypeSend {

		NotSend((short)0), Send((short)1) ;
		
		short code;

		MileageApply_TypeSend(short code) {
			this.code = code;
		}

		public short getCode() {
			return code;
		}
	}
	
	public enum MileageApply_Status {

		Waitting((short)0), Accpet((short)1), Refure((short)2) ;
		
		short code;

		MileageApply_Status(short code) {
			this.code = code;
		}

		public short getCode() {
			return code;
		}
	}
	
	public enum VehicleCode_BikeTyeSend {

		Watting((short)1), Send((short)2) ;
		
		short code;

		VehicleCode_BikeTyeSend(short code) {
			this.code = code;
		}

		public short getCode() {
			return code;
		}
	}
	
	public enum VehicleCode_BikeTyeRegister {

		NotRegister((short)0), Register((short)1) ;
		
		short code;

		VehicleCode_BikeTyeRegister(short code) {
			this.code = code;
		}

		public short getCode() {
			return code;
		}
	}
	
	public enum VehicleCode_BikeStatus {

		 Watting((short)0), Approval((short)1), CancelApproval((short)2) ;
		
		short code;

		VehicleCode_BikeStatus(short code) {
			this.code = code;
		}

		public short getCode() {
			return code;
		}
	}
	
	public enum VehicleCode_PublicTransportStatus {

		 NotUse((short)0), Use((short)1), GiveUp((short)2) ;
		
		short code;

		VehicleCode_PublicTransportStatus(short code) {
			this.code = code;
		}

		public short getCode() {
			return code;
		}
	}
	
	public enum MileageHistory_Type {

		Bike((short)1), Bus((short)2), Subway((short)3) ;
		
		short code;

		MileageHistory_Type(short code) {
			this.code = code;
		}

		public short getCode() {
			return code;
		}
	}
	
	public enum MileageHistory_Status {

		NotSendRequest((short)0), RecivePoint((short)1), SendRequestAndNotConfirmed((short)2) , RegisterAgain((short)3), TranferButNotBike ((short)4);
		
		short code;

		MileageHistory_Status(short code) {
			this.code = code;
		}

		public short getCode() {
			return code;
		}
	}
	
	public enum MileageBackup_Status {

		waitting((int)0), approval((int)1), NotApproval((int)2) ;
		
		int code;

		MileageBackup_Status(int code) {
			this.code = code;
		}

		public int getCode() {
			return code;
		}
	}
	
	public enum MileageHistory_TypeCertificate {

		NFC((short)0), QRCode((short)1), Wifi((short)2) ;
		
		short code;

		MileageHistory_TypeCertificate(short code) {
			this.code = code;
		}

		public short getCode() {
			return code;
		}
	}
	
	public enum TeamMember_Status {

		Normal((short)0), SuggestManager((short)1), Accept((short)2) ;
		
		short code;

		TeamMember_Status(short code) {
			this.code = code;
		}

		public short getCode() {
			return code;
		}
	}
	
	public enum Notice_Type {

		Notice((short)1), BikeNews((short)2) ;
		
		short code;

		Notice_Type(short code) {
			this.code = code;
		}

		public short getCode() {
			return code;
		}
	}
	
	public enum SupportBikeCenter_Type {

		RentalBicycle((short)1), BikePark((short)2), BuyRepairBicycle((short)3) ;
		
		short code;

		SupportBikeCenter_Type(short code) {
			this.code = code;
		}

		public short getCode() {
			return code;
		}
	}
	
	public enum Payment_TypeMoney {

		Money((short)1), Point((short)2);
		
		short code;

		Payment_TypeMoney(short code) {
			this.code = code;
		}

		public short getCode() {
			return code;
		}
	}
	
	public enum Payment_TypeUse {

		Donate((short)1), Apply((short)2);
		
		short code;

		Payment_TypeUse(short code) {
			this.code = code;
		}

		public short getCode() {
			return code;
		}
	}
	
	public enum Payment_TypePoint {

		Bike((short)1), Tranfer((short)2), Previous((short)3);
		
		short code;

		Payment_TypePoint(short code) {
			this.code = code;
		}

		public short getCode() {
			return code;
		}
	}
	
	public enum Sponsor_MembershipFeeType {

		EveryMonth((int)1), EveryYear((int)2), OnceTime((int)3);
		
		int code;

		Sponsor_MembershipFeeType(int code) {
			this.code = code;
		}

		public int getCode() {
			return code;
		}
	}
	
	public enum Category_Type {

		Sponsor_Membership_Fee_Type((int)1), Sponsor_Membership_Fee((int)2), Sponsor_Bank((int)3), Report_Category((int)4), MileageApply_GiftCertificate((int)5);
		
		int code;

		Category_Type(int code) {
			this.code = code;
		}

		public int getCode() {
			return code;
		}
	}
	
	public enum Payment_TypeSponsor {

		Periodic((short)1), Temporar((short)2), UsePoint((short)3);
		
		short code;

		Payment_TypeSponsor(short code) {
			this.code = code;
		}

		public short getCode() {
			return code;
		}
	}
	
	public enum Payment_Status {

		Watting((short)1), PaymentSuccess((short)2), WaittingTranferMoney((short)2), Cancel((short)2);
		
		short code;

		Payment_Status(short code) {
			this.code = code;
		}

		public short getCode() {
			return code;
		}
	}
	
}