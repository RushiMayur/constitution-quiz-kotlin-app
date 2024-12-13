package com.rm.constiquiz

import android.content.Context
import android.os.AsyncTask
import android.util.Log
import java.io.BufferedReader
import java.io.InputStreamReader
import java.net.HttpURLConnection
import java.net.URL
import java.util.Properties

class GoogleCloudGenerativeLanguageTask(private val context: Context, private val callback: (String) -> Unit) : AsyncTask<String, Void, String>() {

    // Function to load the API key from config.properties
    private fun getApiKey(): String {
        val properties = Properties()
        val rawResource = context.resources.openRawResource(R.raw.config) // access the config.properties file
        properties.load(BufferedReader(InputStreamReader(rawResource)))
        return properties.getProperty("API_KEY", "") // Get the API key or default to empty string if not found
    }

    override fun doInBackground(vararg params: String?): String {
        val apiKey = getApiKey()  // Retrieve the API key from config.properties
        val text = params[0]

        try {
            val url = URL("https://generativelanguage.googleapis.com/v1beta/models/gemini-pro:generateContent?key=$apiKey")
            val connection = url.openConnection() as HttpURLConnection
            connection.requestMethod = "POST"
            connection.setRequestProperty("Content-Type", "application/json")
            connection.doOutput = true

            val postData = "{\"contents\":[{\"parts\":[{\"text\":\"$text\"}]}]}"
            connection.outputStream.write(postData.toByteArray(Charsets.UTF_8))

            val responseCode = connection.responseCode
            if (responseCode == HttpURLConnection.HTTP_OK) {
                val reader = BufferedReader(InputStreamReader(connection.inputStream))
                val response = StringBuilder()
                var line: String? = null
                while ({ line = reader.readLine(); line }() != null) {
                    response.append(line)
                }
                reader.close()
                return response.toString()
            } else {
                val errorStream = connection.errorStream
                val errorResponse = errorStream?.bufferedReader().use { it?.readText() }
                Log.e("GCL", "HTTP response code: $responseCode, Error response: $errorResponse")
            }
        } catch (e: Exception) {
            Log.e("GCL", "Error occurred: ${e.message}")
        }

        return "Error occurred."
    }

    override fun onPostExecute(result: String?) {
        Log.d("GCL", result ?: "Empty response")

        // Call the callback function with the result
        callback(result ?: "Empty response")
    }
}
