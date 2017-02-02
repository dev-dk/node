package in.inferon.vivekroadwaysfinal.utill;

import android.app.Activity;
import android.content.Context;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.support.annotation.NonNull;
import android.support.design.widget.AppBarLayout;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.text.Editable;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.amazonaws.util.IOUtils;

import org.codehaus.jackson.map.DeserializationConfig;
import org.codehaus.jackson.map.ObjectMapper;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.text.DecimalFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Locale;
import java.util.concurrent.TimeUnit;
import java.util.zip.GZIPInputStream;
import java.util.zip.GZIPOutputStream;

import in.inferon.vivekroadwaysfinal.R;
import in.inferon.vivekroadwaysfinal.ui.models.OK;
import in.inferon.vivekroadwaysfinal.ui.models.trip_models.Journey;
import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class Filler implements SCont {
    private static final String TAG = "FILLER";
    public Context context;

    public Filler(Context context) {
        this.context = context;
    }

    public void ToolbarEndHandler(Activity activity) {
        AppBarLayout layout, sub_layout;
        DrawerLayout drawerLayout;
        layout = (AppBarLayout) activity.findViewById(R.id.App_layout);
        sub_layout = (AppBarLayout) activity.findViewById(R.id.appbar_layout);
        drawerLayout = (DrawerLayout) activity.findViewById(R.id.drawer_layout);
        drawerLayout.setDrawerLockMode(DrawerLayout.LOCK_MODE_UNDEFINED);
        drawerLayout.closeDrawer(GravityCompat.END);
        layout.setVisibility(View.VISIBLE);
        sub_layout.setVisibility(View.GONE);
    }


    public static void ToolbarStartVisibility(Activity activity) {

        AppBarLayout layout, sub_layout;
        DrawerLayout drawerLayout;
        layout = (AppBarLayout) activity.findViewById(R.id.App_layout);
        sub_layout = (AppBarLayout) activity.findViewById(R.id.appbar_layout);
        drawerLayout = (DrawerLayout) activity.findViewById(R.id.drawer_layout);
        drawerLayout.setDrawerLockMode(DrawerLayout.LOCK_MODE_LOCKED_CLOSED);
        layout.setVisibility(View.GONE);
        sub_layout.setVisibility(View.VISIBLE);
    }

    public boolean isfilled(List<EditText> ets) {
        boolean flag = false;
        for (int i = 0; i < ets.size(); i++) {
            boolean equals = ets.get(i).getText().toString().equals("");
            if (equals) {
                flag = false;
                break;
            } else {
                flag = true;
            }
        }
        return flag;
    }

    public static OK isFilled(List<EditText> ets) {
        OK ok = new OK();
        boolean flag = false;
        for (int i = 0; i < ets.size(); i++) {
            EditText editText = ets.get(i);
            boolean equals = editText.getText().toString().trim().equals("");
            if (equals) {
                flag = false;
                ok.setEditText(editText);
                editText.setError("Field required");
                break;
            } else {
                flag = true;
            }
        }
        ok.setFilled(flag);
        ok.setMessage("Field Required");
        return ok;
    }

    public static OK isFilled_tv(List<TextView> ets) {
        OK ok = new OK();
        boolean flag = false;
        for (int i = 0; i < ets.size(); i++) {
            TextView textView = ets.get(i);
            boolean equals = textView.getText().toString().trim().equals("") || textView.getText().toString().trim().equals("Date");
            if (equals) {
                flag = false;
                ok.setTextView(textView);
                textView.setError("Field required");
                break;
            } else {
                flag = true;
            }
        }
        ok.setFilled(flag);
        ok.setMessage("Field Required");
        return ok;
    }

    public static String getDateString(String dateString) {
        dateString = dateString.replaceAll("T.*", "");
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd", Locale.getDefault());
        SimpleDateFormat nsdf = new SimpleDateFormat("dd-MMM-yy", Locale.getDefault());
        Date date = null;
        try {
            date = sdf.parse(dateString);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return nsdf.format(date).replace("-", " ");
    }

    public static String getDateStringYMD(String dateString) {
        String mdatestring = dateString.replace(" ", "-");
        SimpleDateFormat nsdf = new SimpleDateFormat("dd-MMM-yy", Locale.getDefault());
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd", Locale.getDefault());
        Date date = null;
        try {
            date = nsdf.parse(mdatestring);
            Log.i(TAG, "getDateStringYMD: date" + date);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        String newDate = sdf.format(date);
        Log.i(TAG, "getDateStringYMD: newDate " + newDate);
        return newDate;
    }

    public static Date getCurrentDate() {
        try {
            Calendar cal = Calendar.getInstance();
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd", Locale.getDefault());
            String current_date = sdf.format(cal.getTime());
            return sdf.parse(current_date);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return new Date();

    }

   /* public static String getMonthDiff(String licEndDate) {
        licEndDate = licEndDate.replaceAll("T.*", "");
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd", Locale.getDefault());
        Date startDate = new Date();
        Date endDate = null;
        try {
            endDate = sdf.parse(licEndDate);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        int monthsDifference = getMonthsDifference(startDate, endDate);
        return String.valueOf(monthsDifference);
    }
*/
    /*public static int getMonthsDifference(Date date1, Date date2) {
        int m1 = date1.getYear() * 12 + (date1.getMonth() + 1);
        int m2 = date2.getYear() * 12 + (date2.getMonth() + 1);
        return (m2 - m1);
    }*/

    public static Long getDateDiffString(Date dateOne, Date dateTwo) {
        long timeOne = dateOne.getTime();
        long timeTwo = dateTwo.getTime();
        long oneDay = 1000 * 60 * 60 * 24;
        return (timeTwo - timeOne) / oneDay;
    }

    public static double getMileage(double distance_in_km, double fuel_in_ltrs) {
        return round(distance_in_km / fuel_in_ltrs, 2);
    }

    public static double round(double value, int places) {
        if (places < 0) throw new IllegalArgumentException();
        double factor = (long) Math.pow(10, places);
        value = value * factor;
        double tmp = Math.round(value);
        return tmp / factor;
    }

    public static String getStringFromDouble(double value) {
        return String.valueOf(value);
    }

    public static String getFormattedStringFromDouble(double value) {
        DecimalFormat decimalFormat = new DecimalFormat("##.##");
        Log.e(TAG, "getFormattedStringFromDouble: " + decimalFormat.format(value));
        return decimalFormat.format(value);
    }
/*
    public static String getStringFromInt(int value) {
        return String.valueOf(value);
    }*/

    public static String getStringFromLong(long value) {
        return String.valueOf(value);
    }

    public static String getStringFromTV(TextView textView) {
        if (!TextUtils.isEmpty(textView.getText()) || !textView.getText().toString().equals("Date"))
            return textView.getText().toString();
        else
            return "0";
    }

    public static String getRsStringFromLong(long rs) {
        DecimalFormat df = new DecimalFormat("##,##,##,###");
        String string = df.format(rs);
        string = string.equals("") ? "0" : string;
        return string;
    }

    public static long getRsLongFromString(String rs) {
        rs = rs.equals("") ? "0" : rs;
        rs = rs.replaceAll(",", "");
        return Long.parseLong(rs);
    }

    public static String getRsFormat(String rs) {
        DecimalFormat df = new DecimalFormat("##,##,##,###");
        rs = rs.equals("") ? "0" : rs;
        long temp = (long) Double.parseDouble(rs);
        String string = df.format(temp);
        string = string.equals("") ? "0" : string;
        return string;
    }


    @NonNull
    public static String getStringFromET(EditText editText) {
        String text = editText.getText().toString();
        text = text.equals("") ? "0" : text;
        text = text.replaceAll(",", "");
        return text;
    }

    public static double getDoubleFromET(EditText editText) {
        String string = editText.getText().toString();
        string = string.equals("") ? "0" : string;
        string = string.replaceAll(",", "");
        return Double.parseDouble(string);
    }


    public static double getDoubleFromTV(TextView textView) {
        String string = textView.getText().toString();
        string = string.equals("") ? "0" : string;
        string = string.replaceAll(",", "");
        return Double.parseDouble(string);
    }

    public static double getDoubleFromEditable(Editable editable) {
        String text = editable.toString();
        text = text.equals("") ? "0" : text;
        text = text.replaceAll(",", "");
        return Double.parseDouble(text);
    }

    public static long getTotDist(long starting_odo, long closing_odo) {
        return closing_odo - starting_odo;
    }

    public File stringToFile(String info,
                             String filename) throws IOException {
        Log.i("FileHandling", "jSON Object To File");
        FileOutputStream FOS = context.openFileOutput(filename, Context.MODE_PRIVATE);
        Log.e("JSON", info + " " + filename);
        FOS.write(info.getBytes("UTF-8"));
        FOS.close();
        return new File(context.getFilesDir(), filename);
    }

   /* public static int getIntFromET(EditText value) {
        String string = value.getText().toString().trim();
        return Integer.parseInt(string);
    }*/


    public static long getLongFromET(EditText value) {
        String string = value.getText().toString();
        string = string.equals("") ? "0" : string;

        string = string.replaceAll(",", "");
        int db = (int) Double.parseDouble(string);
        string = String.valueOf(db);
        return getRsLongFromString(string);
    }

    public static long getLongFromTV(TextView textView) {
        String string = textView.getText().toString();
        string = string.equals("") ? "0" : string;
        string = string.replaceAll(",", "");
        double value = Double.parseDouble(string);
        return (long) value;
    }

    public File stringToZipFile(String info, String filename) throws IOException {
        deletePreviousFile(filename);
        Log.i("Filler", "Json Object to Zip File name : " + filename);
        FileOutputStream fos = context.openFileOutput(filename + ".gz",
                Context.MODE_PRIVATE);
        GZIPOutputStream gzos = new GZIPOutputStream(fos);
        gzos.write(info.getBytes("UTF-8"));
        gzos.close();
        return new File(context.getFilesDir(), filename + ".gz");
    }

/*    public File bytesToFile(byte[] info, String filename) throws IOException {
        deletePreviousFile(filename);
        Log.i("Filler", "byte to File -> : " + filename);
        FileOutputStream fos = context.openFileOutput(filename,
                Context.MODE_PRIVATE);
        fos.write(info);
        fos.close();
        return new File(context.getFilesDir(), filename);
    }*/

    public File streamToFile(InputStream stream, String filename) {
        deletePreviousFile(filename);
        GZIPInputStream FGIS;
        BufferedReader reader;
        File file = null;
        try {
            FGIS = new GZIPInputStream(stream);
            reader = new BufferedReader(new InputStreamReader(FGIS, "UTF-8"));
            String raw_string = Filler.streamToString(reader);
            //json object would require
            file = stringToFile(raw_string, filename);
            Log.i("FILL", "Convertable String : " + raw_string.length());
            if (file.exists()) {
                Log.i("FILL", "File created : ->" + filename);

            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return file;
    }

    private void deletePreviousFile(String filename) {
        File file = getZipFile(filename);
        if (file.exists()) {
            if (file.delete()) {
                Log.i("Fill", "File deleted -> " + filename);
            }
        } else {
            Log.i("Fill", "File Not exists -> " + filename);
        }
    }

    public File getZipFile(String filename) {
        return new File(context.getFilesDir(), filename + ".gz");
    }

    public static String pojoToJsonString(Object object) {
        ObjectMapper mapper = new ObjectMapper();
        String result = "";
        try {
            result = mapper.writeValueAsString(object);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return result;
    }

    public boolean isOnline() {
        ConnectivityManager connMgr = (ConnectivityManager)
                context.getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo networkInfo = connMgr.getActiveNetworkInfo();
        return (networkInfo != null && networkInfo.isConnected());
    }

    public static APIL getApil() {
        Retrofit retrofit = new Retrofit.Builder().client(getClient()).baseUrl(BASE_URL).addConverterFactory(GsonConverterFactory.create()).build();
        return retrofit.create(APIL.class);
    }

    private static OkHttpClient getClient() {
        return new OkHttpClient.Builder()
                .connectTimeout(5, TimeUnit.MINUTES)
                .readTimeout(5, TimeUnit.MINUTES)
                .build();
    }

    public static <T> T stringToPojo(String info, Class<?> targetClass) throws ClassNotFoundException, IOException {
        ObjectMapper om = new ObjectMapper();
        om.configure(DeserializationConfig.Feature.FAIL_ON_UNKNOWN_PROPERTIES, false);
        return om.readValue(info, om.getTypeFactory().constructCollectionType(List.class, Class.forName(targetClass.getName())));
    }

    public <T> T fileToPojo(String filename, Class<?> targetClass) throws IOException, ClassNotFoundException {
        File file = new File(context.getFilesDir(), filename);
        FileInputStream fileis = new FileInputStream(file);
        BufferedReader br = new BufferedReader(new InputStreamReader(fileis, "UTF-8"));
        String string;
        StringBuilder sb = new StringBuilder();
        while ((string = br.readLine()) != null) {
            sb.append(string);
        }
        Log.d(TAG, "fileToPojo: " + sb.toString());
        return stringToPojo(sb.toString(), targetClass);
    }

    public String fileToString(String filename) throws IOException {
        File file = new File(context.getFilesDir(), filename);
        FileInputStream fileis = new FileInputStream(file);
        BufferedReader br = new BufferedReader(new InputStreamReader(fileis, "UTF-8"));
        String string;
        StringBuilder sb = new StringBuilder();
        while ((string = br.readLine()) != null) {
            sb.append(string);
        }
        return sb.toString();
    }

    public static String streamToString(BufferedReader reader)
            throws IOException {
        StringBuilder sb = new StringBuilder();
        String input;
        while ((input = reader.readLine()) != null) {
            sb.append(input);
        }
        return sb.toString();
    }

    public void showToast(String s) {
        Toast.makeText(context, s, Toast.LENGTH_SHORT).show();
    }

    public String getFileContent(String fileName) {
        String content = null;
        try {
            File file = getFile(fileName);
            FileInputStream fileInputStream = new FileInputStream(file);
            content = IOUtils.toString(fileInputStream);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return content;
    }

    public File getFile(String filename) {
        return new File(context.getFilesDir(), filename);
    }

    public boolean createFile(String fileName) {
        try {
            File tempFile = getFile(fileName);
            if (!tempFile.exists())
                return tempFile.createNewFile();
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return false;
    }


    public static String getRsStringFromDouble(double v) {
        DecimalFormat df = new DecimalFormat("##,##,##,###.##");
        return df.format(v);
    }

    public static String getThumbFileName(String fileName) {
        return fileName.substring(0, fileName.lastIndexOf(".")) + "_thumb.jpg";
    }

    public static ArrayList<String> getThumbFileName(ArrayList<String> filePathList) {
        for (int i = 0; i < filePathList.size(); i++) {
            String fileName = getThumbFileName(filePathList.get(i));
            filePathList.remove(i);
            filePathList.add(i, fileName);
        }
        return filePathList;
    }

    public static String getFileNameFromThumb(String url) {
        url = url.replace("_thumb", "");
        return url;
    }

    public static ArrayList<String> removeNonThumbImage(List<String> fileNameFromServer) {
        ArrayList<String> correctName = new ArrayList<>();
        for (int i = 0; i < fileNameFromServer.size(); i++) {
            String name = fileNameFromServer.get(i);
            if (name.contains("thumb")) {
                correctName.add(fileNameFromServer.get(i));
            }
        }
        return correctName;
    }

    public static String getDayFromDate(String date) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(new Date(Filler.getDateString(date)));
        String[] days = new String[]{"Sunday", "Monday", "Tuesday", "Wednesday", "Thusday", "Friday", "Saturday"};
        Log.d(TAG, "getDayFromDate: " + days[calendar.get(Calendar.DAY_OF_WEEK) - 1]);
        return days[calendar.get(Calendar.DAY_OF_WEEK) - 1];
    }

    public static String getReplaceURL(String url) {
        return url.replaceAll("\\s+", "+");
    }

    public List<Journey> isJourneyExists(List<View> fields, String fileName) throws IOException, ClassNotFoundException {
        List<Journey> journeys = fileToPojo(fileName, Journey.class);
        if (journeys != null && journeys.size() >= 1) {
            Log.d(TAG, "isJourneyExists: DISABLED" + journeys.size());
            Filler.disableFields(fields);
            return journeys;
        } else {
            Log.d(TAG, "isJourneyExists: ENABLED");
            Filler.enableFields(fields);
            return journeys;
        }
    }

    public static void enableFields(List<View> fields) {
        for (View v :
                fields) {
            v.setEnabled(true);
        }
    }

    public static void disableFields(List<View> fields) {
        for (View v :
                fields) {
            v.setEnabled(false);
        }
    }

    public static long getPhnNo(EditText editText) {
        String string = editText.getText().toString();
        string = string.equals("") ? "0" : string;
        return Long.parseLong(string);
    }

    public static double getRsDoubleFromString(String rs) {
        rs = rs.equals("") ? "0" : rs;
        rs = rs.replaceAll(",", "");
        return Double.parseDouble(rs);
    }

    public static ArrayList<String> getNonThumbArrayList(ArrayList<String> filePathList) {
        for (int i = 0; i < filePathList.size(); i++) {
            if (filePathList.get(i).contains("_thumb")) {
                filePathList.remove(i);
            }
        }
        return filePathList;
    }

    public static boolean deleteContents(File dir) {
        File[] files = dir.listFiles();
        boolean success = true;
        if (files != null) {
            for (File file : files) {
                if (file.isDirectory()) {
                    success &= deleteContents(file);
                }
                if (!file.delete()) {
                    Log.d("Failed to delete ", file.getPath());
                    success = false;
                }
            }
        }
        return success;
    }

    public String getUserID() {
        SharedPreferences loginCookie = context.getSharedPreferences("LOGIN", Context.MODE_PRIVATE);
        return loginCookie.getString("USERID", "user_id");
    }

    public boolean getUploadBoolean() {
        SharedPreferences upload_pref = context.getSharedPreferences("UPLOAD", Context.MODE_PRIVATE);
        return upload_pref.getBoolean("Service", false);
    }

    public void updateUploadBoolean(boolean flag) {
        SharedPreferences upload_pref = context.getSharedPreferences("UPLOAD", Context.MODE_PRIVATE);
        SharedPreferences.Editor uploadEditor = upload_pref.edit();
        uploadEditor.putBoolean("Service", flag);
        uploadEditor.apply();
    }

    public static long getMillisecFromSec(long sec) {
        return sec * 1000;
    }

    public static String getS3UrlFromLUrl(String url, String journey_id, boolean isthumb) {
        final String trip_id = journey_id.substring(0, journey_id.lastIndexOf("-"));
        final String name = url.substring(url.lastIndexOf("/") + 1);
        if (isthumb) {
            return getReplaceURL(Constants.S3_TRIP_URL + trip_id + "/" + journey_id + "/" + getSubFolder(name) + "/" + name);
        } else {
            String nonthmb = name;
            if (name.contains("_thumb")) {
                nonthmb = name.replace("_thumb", "");
            }
            return getReplaceURL(Constants.S3_TRIP_URL + trip_id + "/" + journey_id + "/" + getSubFolder(name) + "/" + nonthmb);
        }
    }

    public static String getDescription(String url) {
        String desc = url.substring(url.lastIndexOf("/") + 1);
        if (desc.contains("thumb")) {
            return desc.replace("_thumb", "");
        }
        return desc;
    }

    public static String getSubFolder(String name) {
        String subFolder;
        Log.d(TAG, "getSubFolder: " + name + " " + Constants.NAGARACHI + " " + !name.contains(Constants.NAGARACHI) + " " +
                Constants.BILL_PADI + " " + !name.contains(Constants.BILL_PADI));
        if (!(name.contains(Constants.NAGARACHI) || name.contains(Constants.BILL_PADI))) {
            String[] fName = Filler.getFileNameFromThumb(name).split("_");
            subFolder = fName[0];
            Log.d(TAG, "getSubFolder: fName[0] " + subFolder);
            if (subFolder.equalsIgnoreCase("Driver")) {
                Log.d(TAG, "getSubFolder: subfolder+=_fName[1] " + subFolder + "_" + fName[1]);
                subFolder += "_" + fName[1];
            }
        } else {
            Log.d(TAG, "getSubFolder: file.getName() " + name);
            subFolder = Filler.getFileNameFromThumb(name);
            Log.d(TAG, "getSubFolder: Filler.getFileNameFromThumb() " + subFolder);
            subFolder = subFolder.split("\\.")[0];
            Log.d(TAG, "getSubFolder: sunfolder else " + subFolder);
        }
        Log.d(TAG, "getSubFolder: " + subFolder);
        return subFolder;
    }

    public static Bitmap getThumbnail(Bitmap bitmap) {
        int THUMBNAIL_HEIGHT = bitmap.getHeight() / 4;
//        int THUMBNAIL_WIDTH = 66;

        Float width = (float) bitmap.getWidth();
        Float height = (float) bitmap.getHeight();
        Float ratio = width / height;
        bitmap = Bitmap.createScaledBitmap(bitmap, (int) (THUMBNAIL_HEIGHT * ratio), THUMBNAIL_HEIGHT, false);
        return bitmap;
    }
}
