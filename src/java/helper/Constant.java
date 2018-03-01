package helper;

public class Constant {
    public static int VIEC_TOI_DUOC_GIAO = 1;
    public static int VIEC_TOI_YEU_CAU = 2;
    public static int CONG_VIEC_LIEN_QUAN = 3;
    public static int CONG_VIEC_CUA_TEAM = 4;
    public static int CONG_VIEC_CUA_BO_PHAN_IT = 5;

    public static int ALL = 0;
    public static int NEW = 1;
    public static int IN_PROGRESS = 2;
    public static int RESOLVED = 3;
    public static int FEEDBACK = 4;
    public static int CLOSED = 5;
    public static int OUT_OF_DATE = 6;  // CANCELLED

    public static String formatDateToSqlFromView(String date){ // mm/dd/yyyy
        if (date != null){
            String[] dates = date.split("/");
            if (dates.length == 3){
                return dates[2] + "-" + dates[0] + "-" + dates[1];
            }
        }
        return date; // yyyy/mm/dd
    }

    public static String formatDateToClientFromDb(String date){  //yyyy/mm/dd
        if (date != null){
            String[] dates = date.split("/");
            if (dates.length == 3){
                return dates[2] + "-" + dates[1] + "-" + dates[0];
            }
        }
        return date;  // dd/mm/yyyy
    }
}
