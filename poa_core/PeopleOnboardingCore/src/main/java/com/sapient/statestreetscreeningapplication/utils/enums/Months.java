package com.sapient.statestreetscreeningapplication.utils.enums;

public enum Months {
JAN,
FEB,
MARCH,
APRIL,
MAY,
JUNE,
JULY,
AUG,
SEPT,
OCT,
NOV,
DEC;

public static Months getMonth(int integerMonth){
	
	Months enumMonth =null;
	
	switch (integerMonth) {
	case 0:  enumMonth = Months.JAN;
	         break;
	case 1:  enumMonth = Months.FEB;
             break;
	case 2:  enumMonth = Months.MARCH;
             break;
	case 3:  enumMonth = Months.APRIL;
             break;
    case 4:  enumMonth = Months.MAY;
             break;
    case 5:  enumMonth = Months.JUNE;
             break;
    case 6:  enumMonth = Months.JULY;
             break;
    case 7:  enumMonth = Months.AUG;
             break;
    case 8:  enumMonth = Months.SEPT;
             break;
    case 9:  enumMonth = Months.OCT;
             break;
    case 10: enumMonth = Months.NOV;
             break;
    case 11: enumMonth = Months.DEC;
             break;
	default: break;
	}
	
	return enumMonth;
}

public static int getValue(Months month){
	
	int value = 12;
	
	switch (month) {
	case JAN:   value = 0; 
	            break;
	case FEB:   value = 1;
                break;
	case MARCH: value = 2;
                break;
	case APRIL: value = 3;
                break;
    case MAY:   value = 4;
                break;
    case JUNE:  value = 5;
                break;
    case JULY:  value = 6;
                break;
    case AUG:   value = 7;
                break;
    case SEPT:  value = 8;
                break;
    case OCT:   value = 9;
                break;
    case NOV:   value = 10;
                break;
    case DEC:   value = 11;
                break;
	default:    break;
	}
	
	return value;
}


}
