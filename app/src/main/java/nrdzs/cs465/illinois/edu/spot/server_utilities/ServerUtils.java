package nrdzs.cs465.illinois.edu.spot;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.FileInputStream;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.URL;

import android.graphics.Bitmap;
import android.util.Log;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Base64;
import android.graphics.BitmapFactory;


/*
	The ServerUtils class is meant to provide a relatively simple interface
	for interacting with the Spott backend.
 */
public class ServerUtils {
    private static final String UPLOAD_URL = "http://spott-server.herokuapp.com/upload";
    private static final String DOWNLOAD_URL = "http://spott-server.herokuapp.com/download";

    private static final String DOWNLOAD_PARAMS = "location=";
    private static final String BOUNDARY_STR = "----SPOTTBOUNDARY";


    /**
     * Call this function to upload a File to the server
     * @param  imageFile     The File that is going to be uploaded
     * @param  filename      A String corresponding to the filename used when storing
     *                         the file on the server.
     *                         NOTE: the .jpg is NOT included in the filename!
     * @param  floorLocation An int representing the floor location
     * 					       0 -> Left Side
     * 					       1 -> Center Side
     * 					       2 -> Right Side
     */
    public static void uploadImageToServer(File imageFile, String filename, int floorLocation) throws IOException {
        URL obj = new URL(UPLOAD_URL);
        HttpURLConnection con = (HttpURLConnection) obj.openConnection();
        con.setRequestMethod("POST");
        con.setRequestProperty("Content-type", "multipart/form-data; boundary=" + BOUNDARY_STR);
        con.setRequestProperty("Accept", "text/html");

        // For POST only - START
        con.setDoOutput(true);
        OutputStream os = con.getOutputStream();
        BufferedWriter bodyWriter = new BufferedWriter(new OutputStreamWriter(os));

        // write the filename using the 'date' key=value pair
        bodyWriter.write("\n\n--" + BOUNDARY_STR + "\n");
        bodyWriter.write("Content-Disposition: form-data; name=\"date\"\n\n");
        bodyWriter.write(filename + ".jpg\n");

        // write the floor location using the 'location' key=value pair
        bodyWriter.write("--" + BOUNDARY_STR + "\n");
        bodyWriter.write("Content-Disposition: form-data; name=\"location\"\n\n");
        bodyWriter.write(Integer.toString(floorLocation) + "\n");

        // write the image data using the 'src' key=value pair
        bodyWriter.write("--" + BOUNDARY_STR + "\n");
        bodyWriter.write("Content-Disposition: form-data; name=\"src\"; filename=\""
                + filename + ".jpg\"\n");
        bodyWriter.write("Content-Type: image/jpeg\n\n");
        bodyWriter.flush();

        // write the actual file contents into the request
        int bytesRead;
        byte[] dataBuffer = new byte[1024];
        FileInputStream is = new FileInputStream(imageFile);
        while((bytesRead = is.read(dataBuffer)) != -1) {
            os.write(dataBuffer, 0, bytesRead);
        }
        os.flush();

        // write the ending bits to close it off before sending it off to
        // the server
        bodyWriter.write("\n--" + BOUNDARY_STR + "--\n");
        bodyWriter.flush();
        os.close();
        bodyWriter.close();
        // For POST only - END

        int responseCode = con.getResponseCode();

        if (responseCode == HttpURLConnection.HTTP_OK) { //success
            BufferedReader in = new BufferedReader(new InputStreamReader(
                    con.getInputStream()));
            String inputLine;
            StringBuilder response = new StringBuilder();

            while ((inputLine = in.readLine()) != null) {
                response.append(inputLine);
            }
            in.close();

            Log.d("UPLOAD RESULT", response.toString());

        } else {
            Log.e("ERROR", "Image uploading failed!");
        }

    }

    /**
     * Call this function to grab a JSON Array of image data corresponding to
     *     all the images that were uploaded at a given floor location
     * @param  floorLocation An int representing the floor location
     * 					       It works as follows:
     * 					       0 -> Left Side
     * 					       1 -> Center Side
     * 					       2 -> Right Side
     * 					       3 -> Entire Floor
     * @return  A JSONArray holding all the data. To get the binary
     * 					       image data out of it, call extractBitmapData
     */
    public static JSONArray downloadImagesFromServer(int floorLocation) throws IOException {
        URL obj = new URL(DOWNLOAD_URL);
        HttpURLConnection con = (HttpURLConnection) obj.openConnection();
        con.setRequestMethod("POST");
        con.setRequestProperty("Content-type", "application/x-www-form-urlencoded");
        con.setRequestProperty("Accept", "application/json");

        // For POST only - START
        con.setDoOutput(true);
        OutputStream os = con.getOutputStream();
        os.write(DOWNLOAD_PARAMS.concat(Integer.toString(floorLocation)).getBytes());
        os.flush();
        os.close();
        // For POST only - END

        int responseCode = con.getResponseCode();

        JSONArray jsonArray = null;
        if (responseCode == HttpURLConnection.HTTP_OK) { //success
            BufferedReader in = new BufferedReader(new InputStreamReader(
                    con.getInputStream()));
            String inputLine;
            StringBuilder response = new StringBuilder();

            while ((inputLine = in.readLine()) != null) {
                response.append(inputLine);
            }
            in.close();

            // print result
            try {
                jsonArray = new JSONArray(response.toString());
            } catch (JSONException e) {
                Log.e("ERROR", "JSONArray failed at line 162!");
            }
        } else {
            Log.e("ERROR", "Image downloading failed!");
        }

        return jsonArray;
    }

    /**
     * Call this function to extract bitmap data out of a JSONArray returned
     *     by downloadImagesFromServer
     * @param  jsonArray     The JSONArray to extract from
     * @return           An ArrayList storing the raw bitmap data as Bitmap instances
     */
    public static ArrayList<Bitmap> extractBitmapData(JSONArray jsonArray) {
        ArrayList<Bitmap> bmArray = new ArrayList<Bitmap>();
        for (int i = 0; i < jsonArray.length(); i++) {
            // first get the JSON object at that index
            JSONObject currObj = null;
            try {
                currObj = jsonArray.getJSONObject(i);
            } catch (JSONException e) {
                Log.e("ERROR", "JSON Object failed at line 184!");
            }

            // image data is stored like this
            String img_data = null;
            try {
                img_data = currObj.getString("data");
            } catch(JSONException e) {
                Log.e("ERROR", "JSON Object failed at line 193!");
            } catch (NullPointerException e) {
                Log.e("ERROR", "Could not find 'data' attribute in JSONObject!");
            }

            // Do whatever you need to with that data, like base64 decoding
            // and making a Bitmap
            byte[] decoded_img_data = Base64.getMimeDecoder().decode(img_data);
            Bitmap curr_bm = BitmapFactory.decodeByteArray(
                    decoded_img_data, 0, decoded_img_data.length);
            bmArray.add(curr_bm);
        }

        return bmArray;
    }

    /**
     * Call this function to extract date information out of a JSONArray returned
     *     by downloadImagesFromServer
     * @param  jsonArray     The JSONArray to extract from
     * @return           An ArrayList storing the date information as Strings
     */
    public static ArrayList<String> extractDateInfo(JSONArray jsonArray) {
        ArrayList<String> dateArray = new ArrayList<String>();
        for (int i = 0; i < jsonArray.length(); i++) {
            // first get the JSON object at that index
            JSONObject currObj = null;
            try {
                currObj = jsonArray.getJSONObject(i);
            } catch (JSONException e) {
                Log.e("ERROR", "JSONObject failed at line 223!");
            }

            // image dates are stored like this
            String img_date;
            try {
                img_date = currObj.getString("date");
                dateArray.add(img_date);
            } catch (JSONException e) {
                Log.e("ERROR", "JSONObject failed at line 233!");
            } catch (NullPointerException e) {
                Log.e("ERROR", "JSON object has no attribute 'date'!");
            }
        }

        return dateArray;
    }
}
