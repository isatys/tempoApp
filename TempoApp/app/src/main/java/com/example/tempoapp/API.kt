import retrofit2.Call
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import retrofit2.http.Query

data class TempoInfo(
    val remainingDays: Int,
    val currentColor: String,
    val nextColor: String,
    val colorHistory: List<String>
)

interface EdfApi {
    @GET("tempo")
    fun getTempoInfo(@Query("api_key") apiKey: String): Call<TempoInfo>
}

class EdfApiClient {
    private val retrofit = Retrofit.Builder()
        .baseUrl("https://api.edf.fr/particulier/v2/")
        .addConverterFactory(GsonConverterFactory.create())
        .build()

    private val edfApi = retrofit.create(EdfApi::class.java)

    fun getTempoInfo(apiKey: String): TempoInfo? {
        val response = edfApi.getTempoInfo(apiKey).execute()
        return response.body()
    }
}
