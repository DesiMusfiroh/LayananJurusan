package com.layanan.jurusan.data.remote

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.paging.Pager
import androidx.paging.PagingConfig
import com.layanan.jurusan.data.datasource.ListAnnouncementDataSource
import com.layanan.jurusan.data.datasource.ListNewsDataSource
import com.layanan.jurusan.data.datasource.ListSearchNewsDataSource
import com.layanan.jurusan.data.datasource.TestNewsDataSource
import com.layanan.jurusan.data.model.*
import com.layanan.jurusan.data.remote.api.ApiConfig
import com.layanan.jurusan.data.remote.response.ProfileJurusan.ProfileJurusanResponse
import com.layanan.jurusan.data.remote.response.ProfileJurusan.ProfileProdiResponse
import com.layanan.jurusan.data.remote.response.SaveFcmTokenResponse
import com.layanan.jurusan.data.remote.response.UserProfileResponse
import com.layanan.jurusan.data.remote.response.announcement.DetailAnnouncementResponse
import com.layanan.jurusan.data.remote.response.announcement.LatestAnnouncementResponse
import com.layanan.jurusan.data.model.Iku1Model
import com.layanan.jurusan.data.remote.response.civitas.*
import com.layanan.jurusan.data.remote.response.document.ListDocumentResponse
import com.layanan.jurusan.data.remote.response.iku.*
import com.layanan.jurusan.data.remote.response.news.LatestNewsResponse
import com.layanan.jurusan.data.remote.response.login.LoginDataResponse
import com.layanan.jurusan.data.remote.response.login.LoginResponse
import com.layanan.jurusan.data.remote.response.login.LogoutResponse
import com.layanan.jurusan.data.remote.response.news.DetailNewsResponse
import com.layanan.jurusan.data.remote.response.surat.JenisSuratResponse
import com.layanan.jurusan.data.remote.response.surat.KeywordSuratResponse
import com.layanan.jurusan.data.remote.response.surat.ListRiwayatSuratResponse
import com.layanan.jurusan.data.remote.response.surat.RiwayatSuratResponse
import com.layanan.jurusan.data.remote.response.userprofile.SignatureResponse
import com.loopj.android.http.AsyncHttpClient
import com.loopj.android.http.AsyncHttpResponseHandler
import com.loopj.android.http.RequestParams
import cz.msebera.android.httpclient.Header
import okhttp3.MultipartBody
import org.json.JSONObject
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class RemoteDataSource {
    companion object {
        fun getInstance(): RemoteDataSource {
            return RemoteDataSource()
        }
        private const val TAG = "Remote Data Source"
    }

    fun getUserLogin(username: String, password: String): LiveData<LoginDataResponse> {
        val user: MutableLiveData<LoginDataResponse> = MutableLiveData()
        val client = ApiConfig.getApiService().login(username, password)
        client.enqueue(object : Callback<LoginResponse> {
            override fun onResponse(call: Call<LoginResponse>, response: Response<LoginResponse>) {
                if (response.isSuccessful) {
                    user.postValue(response.body()?.data)
                    Log.d("HasilResponse",response.body().toString())
                } else {

                    Log.e(TAG, "onFailure: ${response.message()}")
                }
            }
            override fun onFailure(call: Call<LoginResponse>, t: Throwable) {
                Log.e(TAG, "onFailure: ${t.message.toString()}")
            }
        })
        return user
    }

    fun getLatestNews(): LiveData<List<NewsModel>> {
        val results = MutableLiveData<List<NewsModel>>()
        ApiConfig.getApiService().getLatestNews().enqueue(object: Callback<LatestNewsResponse> {
            override fun onResponse(call: Call<LatestNewsResponse>, response: Response<LatestNewsResponse>) {
                if (response.isSuccessful) {
                    results.postValue(response.body()?.news)
                } else {
                    Log.e(TAG, "onFailure Response: ${response.message()}")
                }
            }
            override fun onFailure(call: Call<LatestNewsResponse>, t: Throwable) {
                Log.e(TAG, "onFailure: ${t.message.toString()}")
            }
        })
        return results
    }

    fun getDetailNews(id: Int): LiveData<NewsModel>{
        val result = MutableLiveData<NewsModel>()
        ApiConfig.getApiService().getDetailNews(id).enqueue(object : Callback<DetailNewsResponse>{
            override fun onResponse(
                call: Call<DetailNewsResponse>,
                response: Response<DetailNewsResponse>
            ) {
                if (response.isSuccessful){
                    Log.d("HasilResponse",response.body().toString())
                    result.postValue(response.body()!!.data)
                }else{
                    Log.d("Failed","Tidak mendapat data")
                }
            }

            override fun onFailure(call: Call<DetailNewsResponse>, t: Throwable) {
                Log.e(TAG, "onFailure: ${t.message.toString()}")
            }

        })

        return result
    }

    fun getLatestAnnouncement(): LiveData<List<AnnouncementModel>> {
        val results = MutableLiveData<List<AnnouncementModel>>()
        ApiConfig.getApiService().getLatestAnnouncement().enqueue(object: Callback<LatestAnnouncementResponse> {
            override fun onResponse(call: Call<LatestAnnouncementResponse>, response: Response<LatestAnnouncementResponse>) {
                if (response.isSuccessful) {
                    results.postValue(response.body()?.announcements)
                } else {
                    Log.e(TAG, "onFailure Response: ${response.message()}")
                }
            }
            override fun onFailure(call: Call<LatestAnnouncementResponse>, t: Throwable) {
                Log.e(TAG, "onFailure: ${t.message.toString()}")
            }
        })
        return results
    }

    fun getListDocument(): LiveData<List<DocumentModel>> {
        val results = MutableLiveData<List<DocumentModel>>()
        ApiConfig.getApiService().getListDocument().enqueue(object: Callback<ListDocumentResponse> {
            override fun onResponse(call: Call<ListDocumentResponse>, response: Response<ListDocumentResponse>) {
                if (response.isSuccessful) {
                    results.postValue(response.body()?.data)
                } else {
                    Log.e(TAG, "onFailure Response: ${response.message()}")
                }
            }
            override fun onFailure(call: Call<ListDocumentResponse>, t: Throwable) {
                Log.e(TAG, "onFailure: ${t.message.toString()}")
            }
        })
        return results
    }

    fun listCharacter() = Pager(PagingConfig(pageSize = 5)) {
        TestNewsDataSource(ApiConfig.getApiService())
    }.flow

    fun getListNews() = Pager(PagingConfig(pageSize = 1)) {
        ListNewsDataSource(ApiConfig.getApiService())
    }.flow

    fun getSearchNews(search: String) = Pager(PagingConfig(pageSize = 5)){
        ListSearchNewsDataSource(ApiConfig.getApiService(),search)
    }.flow

    fun getListAnnouncement() = Pager(PagingConfig(pageSize = 1)) {
        Log.d("pengumuman",  ListAnnouncementDataSource(ApiConfig.getApiService()).toString())
        ListAnnouncementDataSource(ApiConfig.getApiService())
    }.flow

    fun getAnnouncement(id: Int): LiveData<AnnouncementModel>{
        val result = MutableLiveData<AnnouncementModel>()
        ApiConfig.getApiService().getAnnouncement(id).enqueue(object : Callback<DetailAnnouncementResponse>{
            override fun onResponse(
                call: Call<DetailAnnouncementResponse>,
                response: Response<DetailAnnouncementResponse>
            ) {
                if (response.isSuccessful){
                    Log.d("HasilResponse",response.body().toString())
                    result.postValue(response.body()!!.data)
                }else{
                    Log.d("Failed","Tidak mendapat data")
                }
            }

            override fun onFailure(call: Call<DetailAnnouncementResponse>, t: Throwable) {
                Log.e(TAG, "onFailure: ${t.message.toString()}")
            }

        })

        return result
    }

    fun saveFcmToken(fcmToken: String, jwtToken: String): LiveData<SaveFcmTokenResponse>{
        val result = MutableLiveData<SaveFcmTokenResponse>()
        ApiConfig.getApiService().saveToken(fcmToken,"Bearer ${jwtToken}").enqueue(object : Callback<SaveFcmTokenResponse>{
            override fun onResponse(
                call: Call<SaveFcmTokenResponse>,
                response: Response<SaveFcmTokenResponse>
            ) {
                result.postValue(response.body())
                Log.d("HasilResponse",response.body().toString())
            }

            override fun onFailure(call: Call<SaveFcmTokenResponse>, t: Throwable) {
                Log.e(TAG, "onFailure: ${t.message.toString()}")
            }

        })
        return result
    }

    fun getProfileJurusan(): LiveData<ProfileJurusanModel>{
        val result = MutableLiveData<ProfileJurusanModel>()
        ApiConfig.getApiService().getProfileJurusan().enqueue(object : Callback<ProfileJurusanResponse>{
            override fun onResponse(
                call: Call<ProfileJurusanResponse>,
                response: Response<ProfileJurusanResponse>
            ) {
                if (response.isSuccessful){
                    result.postValue(response.body()!!.data)
                }else{
                    Log.d("Failed","Tidak mendapat data jurusan")
                }
            }

            override fun onFailure(call: Call<ProfileJurusanResponse>, t: Throwable) {
                Log.e(TAG, "onFailure: ${t.message.toString()}")
            }

        })

        return result
    }

    fun getUserProfile(jwtToken: String): LiveData<UserModel?>{
        val result = MutableLiveData<UserModel?>()
        Log.d("JwtDataSource",jwtToken)
        ApiConfig.getApiService().getUserProfile("Bearer ${jwtToken}").enqueue(object : Callback<UserProfileResponse>{
            override fun onResponse(
                call: Call<UserProfileResponse>,
                response: Response<UserProfileResponse>
            ) {
                if (response.isSuccessful){
                    result.postValue(response.body()!!.data)
                    Log.d("HasilResponse",response.body().toString())
                }else{
                    Log.d("Failed","Tidak mendapat data User Profile")
                }
            }

            override fun onFailure(call: Call<UserProfileResponse>, t: Throwable) {
                Log.e(TAG, "onFailure: ${t.message.toString()}")
            }

        })

        return result
    }

    fun getProfileProdi(name: String): LiveData<ProfileProdiModel>{
        val result = MutableLiveData<ProfileProdiModel>()
        ApiConfig.getApiService().getProfileProdi(name).enqueue(object : Callback<ProfileProdiResponse>{
            override fun onResponse(
                call: Call<ProfileProdiResponse>,
                response: Response<ProfileProdiResponse>
            ) {
                if (response.isSuccessful){
                    result.postValue(response.body()!!.data)
                }else{
                    Log.d("Failed","Tidak mendapat data jurusan")
                }
            }

            override fun onFailure(call: Call<ProfileProdiResponse>, t: Throwable) {
                Log.e(TAG, "onFailure: ${t.message.toString()}")
            }

        })
        return result
    }

    fun  uploadSignature(image: MultipartBody.Part, jwtToken: String): LiveData<SignatureResponse>{
        val result = MutableLiveData<SignatureResponse>()
        ApiConfig.getApiService().uploadSignature(image,"Bearer ${jwtToken}").enqueue(object : Callback<SignatureResponse>{
            override fun onResponse(
                call: Call<SignatureResponse>,
                response: Response<SignatureResponse>
            ) {
                if (response.isSuccessful){
                    result.postValue(response.body())
                }else{
                    Log.d("Failed","Tidak mendapat data jurusan")
                }
            }

            override fun onFailure(call: Call<SignatureResponse>, t: Throwable) {
                Log.e(TAG, "onFailure: ${t.message.toString()}")
            }

        })
        return result
    }

    fun getIku1(year: String): LiveData<List<Iku1Model>> {
        val results = MutableLiveData<List<Iku1Model>>()
        ApiConfig.getApiService().getIku1(year).enqueue(object: Callback<Iku1Response> {
            override fun onResponse(call: Call<Iku1Response>, response: Response<Iku1Response>) {
                if (response.isSuccessful) {
                    results.postValue(response.body()?.data)
                } else {
                    Log.e(TAG, "onFailure Response: ${response.message()}")
                }
            }
            override fun onFailure(call: Call<Iku1Response>, t: Throwable) {
                Log.e(TAG, "onFailure: ${t.message.toString()}")
            }
        })
        return results
    }

    fun getIku2(year: String): LiveData<List<Iku2Model>> {
        val results = MutableLiveData<List<Iku2Model>>()
        ApiConfig.getApiService().getIku2(year).enqueue(object: Callback<Iku2Response> {
            override fun onResponse(call: Call<Iku2Response>, response: Response<Iku2Response>) {
                if (response.isSuccessful) {
                    Log.d("DataIku2", response.body()?.data.toString())
                    results.postValue(response.body()?.data)
                } else {
                    Log.e(TAG, "onFailure Response: ${response.message()}")
                }
            }
            override fun onFailure(call: Call<Iku2Response>, t: Throwable) {
                Log.e(TAG, "onFailure: ${t.message.toString()}")
            }
        })
        return results
    }

    fun getIku3(year: String): LiveData<List<Iku3Model>> {
        val results = MutableLiveData<List<Iku3Model>>()
        ApiConfig.getApiService().getIku3(year).enqueue(object: Callback<Iku3Response> {
            override fun onResponse(call: Call<Iku3Response>, response: Response<Iku3Response>) {
                if (response.isSuccessful) {
                    Log.d("DataIku2", response.body()?.data.toString())
                    results.postValue(response.body()?.data)
                } else {
                    Log.e(TAG, "onFailure Response: ${response.message()}")
                }
            }
            override fun onFailure(call: Call<Iku3Response>, t: Throwable) {
                Log.e(TAG, "onFailure: ${t.message.toString()}")
            }
        })
        return results
    }

    fun getIku4(year: String): LiveData<List<Iku4Model>> {
        val results = MutableLiveData<List<Iku4Model>>()
        ApiConfig.getApiService().getIku4(year).enqueue(object: Callback<Iku4Response> {
            override fun onResponse(call: Call<Iku4Response>, response: Response<Iku4Response>) {
                if (response.isSuccessful) {
                    Log.d("DataIku2", response.body()?.data.toString())
                    results.postValue(response.body()?.data)
                } else {
                    Log.e(TAG, "onFailure Response: ${response.message()}")
                }
            }
            override fun onFailure(call: Call<Iku4Response>, t: Throwable) {
                Log.e(TAG, "onFailure: ${t.message.toString()}")
            }
        })
        return results
    }

    fun getIku5(year: String): LiveData<List<Iku5Model>> {
        val results = MutableLiveData<List<Iku5Model>>()
        ApiConfig.getApiService().getIku5(year).enqueue(object: Callback<Iku5Response> {
            override fun onResponse(call: Call<Iku5Response>, response: Response<Iku5Response>) {
                if (response.isSuccessful) {
                    Log.d("DataIku2", response.body()?.data.toString())
                    results.postValue(response.body()?.data)
                } else {
                    Log.e(TAG, "onFailure Response: ${response.message()}")
                }
            }
            override fun onFailure(call: Call<Iku5Response>, t: Throwable) {
                Log.e(TAG, "onFailure: ${t.message.toString()}")
            }
        })
        return results
    }

    fun getIku6(year: String): LiveData<List<Iku6Model>> {
        val results = MutableLiveData<List<Iku6Model>>()
        ApiConfig.getApiService().getIku6(year).enqueue(object: Callback<Iku6Response> {
            override fun onResponse(call: Call<Iku6Response>, response: Response<Iku6Response>) {
                if (response.isSuccessful) {
                    results.postValue(response.body()?.data)
                } else {
                    Log.e(TAG, "onFailure Response: ${response.message()}")
                }
            }
            override fun onFailure(call: Call<Iku6Response>, t: Throwable) {
                Log.e(TAG, "onFailure: ${t.message.toString()}")
            }
        })
        return results
    }

    fun getIku7(year: String): LiveData<List<Iku7Model>> {
        val results = MutableLiveData<List<Iku7Model>>()
        ApiConfig.getApiService().getIku7(year).enqueue(object: Callback<Iku7Response> {
            override fun onResponse(call: Call<Iku7Response>, response: Response<Iku7Response>) {
                if (response.isSuccessful) {
                    results.postValue(response.body()?.data)
                } else {
                    Log.e(TAG, "onFailure Response: ${response.message()}")
                }
            }
            override fun onFailure(call: Call<Iku7Response>, t: Throwable) {
                Log.e(TAG, "onFailure: ${t.message.toString()}")
            }
        })
        return results
    }

    fun getIku8(year: String): LiveData<List<Iku8Model>> {
        val results = MutableLiveData<List<Iku8Model>>()
        ApiConfig.getApiService().getIku8(year).enqueue(object: Callback<Iku8Response> {
            override fun onResponse(call: Call<Iku8Response>, response: Response<Iku8Response>) {
                if (response.isSuccessful) {
                    results.postValue(response.body()?.data)
                } else {
                    Log.e(TAG, "onFailure Response: ${response.message()}")
                }
            }
            override fun onFailure(call: Call<Iku8Response>, t: Throwable) {
                Log.e(TAG, "onFailure: ${t.message.toString()}")
            }
        })
        return results
    }

    fun getJenisSurat(tipe: String): LiveData<List<JenisSuratModel>> {
        val results = MutableLiveData<List<JenisSuratModel>>()
        ApiConfig.getApiService().getJenisSurat(tipe).enqueue(object: Callback<JenisSuratResponse> {
            override fun onResponse(call: Call<JenisSuratResponse>, response: Response<JenisSuratResponse>) {
                if (response.isSuccessful) {
                    results.postValue(response.body()?.data)
                } else {
                    Log.e(TAG, "onFailure Response: ${response.message()}")
                }
            }
            override fun onFailure(call: Call<JenisSuratResponse>, t: Throwable) {
                Log.e(TAG, "onFailure: ${t.message.toString()}")
            }
        })
        return results
    }

    fun getKeywordSurat(jenisSuratId: Int): LiveData<List<KeywordSuratModel>> {
        val results = MutableLiveData<List<KeywordSuratModel>>()
        ApiConfig.getApiService().getKeywordSurat(jenisSuratId).enqueue(object: Callback<KeywordSuratResponse> {
            override fun onResponse(call: Call<KeywordSuratResponse>, response: Response<KeywordSuratResponse>) {
                if (response.isSuccessful) {
                    results.postValue(response.body()?.data)
                } else {
                    Log.e(TAG, "onFailure Response: ${response.message()}")
                }
            }
            override fun onFailure(call: Call<KeywordSuratResponse>, t: Throwable) {
                Log.e(TAG, "onFailure: ${t.message.toString()}")
            }
        })
        return results
    }

    fun storePermohonanSurat(params: RequestParams,jwtToken: String): LiveData<String>{
        val client = AsyncHttpClient()
        val message = MutableLiveData<String>()
        client.addHeader("Authorization", "Bearer ${jwtToken}")
        client.post("http://jurusan.doswiteljambi.com/api/surat/permohonan-surat",params,object: AsyncHttpResponseHandler(){
            override fun onSuccess(
                statusCode: Int,
                headers: Array<out Header>?,
                responseBody: ByteArray?
            ) {
                val result = String(responseBody!!)
                val responseObject = JSONObject(result)

                message.postValue(responseObject.getString("message"))


            }

            override fun onFailure(
                statusCode: Int,
                headers: Array<out Header>?,
                responseBody: ByteArray?,
                error: Throwable?
            ) {
                val result =  String(responseBody!!)
                val responseObject = JSONObject(result)
                message.postValue(responseObject.getString("message"))
            }

        })
        return message
    }


    fun getRiwayatSurat(jwtToken: String): LiveData<List<RiwayatSuratModel>> {
        val results = MutableLiveData<List<RiwayatSuratModel>>()
        ApiConfig.getApiService().getRiwayatSurat("Bearer ${jwtToken}").enqueue(object: Callback<ListRiwayatSuratResponse> {
            override fun onResponse(call: Call<ListRiwayatSuratResponse>, response: Response<ListRiwayatSuratResponse>) {
                if (response.isSuccessful) {
                    results.postValue(response.body()?.data)
                } else {
                    Log.e(TAG, "onFailure Response: ${response.message()}")
                }
            }
            override fun onFailure(call: Call<ListRiwayatSuratResponse>, t: Throwable) {
                Log.e(TAG, "onFailure: ${t.message.toString()}")
            }
        })
        return results
    }

    fun showRiwayatSurat(jwtToken: String, id: Int): LiveData<RiwayatSuratModel> {
        val results = MutableLiveData<RiwayatSuratModel>()
        ApiConfig.getApiService().showRiwayatSurat("Bearer ${jwtToken}", id).enqueue(object: Callback<RiwayatSuratResponse> {
            override fun onResponse(call: Call<RiwayatSuratResponse>, response: Response<RiwayatSuratResponse>) {
                if (response.isSuccessful) {
                    results.postValue(response.body()?.data)
                } else {
                    Log.e(TAG, "onFailure Response: ${response.message()}")
                }
            }
            override fun onFailure(call: Call<RiwayatSuratResponse>, t: Throwable) {
                Log.e(TAG, "onFailure: ${t.message.toString()}")
            }
        })
        return results
    }

    fun logout(jwtToken: String): LiveData<LogoutResponse>{
        val results = MutableLiveData<LogoutResponse>()
        ApiConfig.getApiService().logout("Bearer ${jwtToken}").enqueue(object: Callback<LogoutResponse> {
            override fun onResponse(call: Call<LogoutResponse>, response: Response<LogoutResponse>) {
                if (response.isSuccessful) {
                    results.postValue(response.body())
                } else {
                    Log.e(TAG, "onFailure Response: ${response.message()}")
                }
            }
            override fun onFailure(call: Call<LogoutResponse>, t: Throwable) {
                Log.e(TAG, "onFailure: ${t.message.toString()}")
            }
        })
        return results
    }

    fun getAngkatan(): LiveData<List<String>> {
        val results = MutableLiveData<List<String>>()
        ApiConfig.getApiService().getAngkatan().enqueue(object: Callback<AngkatanResponse> {
            override fun onResponse(call: Call<AngkatanResponse>, response: Response<AngkatanResponse>) {
                if (response.isSuccessful) {
                    results.postValue(response.body()?.data)
                } else {
                    Log.e(TAG, "onFailure Response: ${response.message()}")
                }
            }
            override fun onFailure(call: Call<AngkatanResponse>, t: Throwable) {
                Log.e(TAG, "onFailure: ${t.message.toString()}")
            }
        })
        return results
    }

    fun getMahasiswa(prodi: String, angkatan: String, status: String): LiveData<List<Mahasiswa>> {
        val results = MutableLiveData<List<Mahasiswa>>()
        ApiConfig.getApiService().getMahasiswa(prodi, angkatan, status).enqueue(object : Callback<MahasiswaResponse> {
            override fun onResponse(call: Call<MahasiswaResponse>, response: Response<MahasiswaResponse>) {
                if (response.isSuccessful) {
                    results.postValue(response.body()?.data)
                    Log.d(TAG, "remote mahasiswa ${response.body()?.data.toString()}")
                } else {
                    Log.e(TAG, "onFailure Response: ${response.message()}")
                }
            }

            override fun onFailure(call: Call<MahasiswaResponse>, t: Throwable) {
                Log.e(TAG, "onFailure: ${t.message.toString()}")
            }
        })
        return results
    }

    fun getDetailMahasiswa(id:Int ): LiveData<Mahasiswa> {
        val results = MutableLiveData<Mahasiswa>()
        ApiConfig.getApiService().getDetailMahasiswa(id).enqueue(object : Callback<DetailMahasiswaResponse> {
            override fun onResponse(call: Call<DetailMahasiswaResponse>, response: Response<DetailMahasiswaResponse>) {
                if (response.isSuccessful) {
                    results.postValue(response.body()?.data)
                    Log.d("DataResponse","Okesip")
                } else {
                    Log.e(TAG, "onFailure Response nih: ${response.message()}")
                }
            }

            override fun onFailure(call: Call<DetailMahasiswaResponse>, t: Throwable) {
                Log.e(TAG, "onFailure nih: ${t.message.toString()}")
            }
        })

        return results
    }

    fun getSearchMahasiswa(prodi: String, angkatan: String, status: String, search: String): LiveData<List<Mahasiswa>> {
        val results = MutableLiveData<List<Mahasiswa>>()
        ApiConfig.getApiService().getSearchMahasiswa(prodi, angkatan, status,search).enqueue(object : Callback<MahasiswaResponse> {
            override fun onResponse(call: Call<MahasiswaResponse>, response: Response<MahasiswaResponse>) {
                if (response.isSuccessful) {
                    results.postValue(response.body()?.data)
                    Log.d(TAG, "remote mahasiswa ${response.body()?.data.toString()}")
                } else {
                    Log.e(TAG, "onFailure Response: ${response.message()}")
                }
            }

            override fun onFailure(call: Call<MahasiswaResponse>, t: Throwable) {
                Log.e(TAG, "onFailure: ${t.message.toString()}")
            }
        })
        return results
    }

    fun getDosen(prodi: String, status: String): LiveData<List<DosenModel>> {
        val results = MutableLiveData<List<DosenModel>>()
        ApiConfig.getApiService().getDosen(prodi, status).enqueue(object : Callback<DosenResponse> {
            override fun onResponse(call: Call<DosenResponse>, response: Response<DosenResponse>) {
                if (response.isSuccessful) {
                    results.postValue(response.body()?.data)
                } else {
                    Log.e(TAG, "onFailure Response: ${response.message()}")
                }
            }

            override fun onFailure(call: Call<DosenResponse>, t: Throwable) {
                Log.e(TAG, "onFailure: ${t.message.toString()}")
            }
        })
        return results
    }

    fun getSearchDosen(prodi: String, status: String, search:String): LiveData<List<DosenModel>> {
        val results = MutableLiveData<List<DosenModel>>()
        ApiConfig.getApiService().getSearchDosen(prodi, status,search).enqueue(object : Callback<DosenResponse> {
            override fun onResponse(call: Call<DosenResponse>, response: Response<DosenResponse>) {
                if (response.isSuccessful) {
                    results.postValue(response.body()?.data)
                } else {
                    Log.e(TAG, "onFailure Response: ${response.message()}")
                }
            }

            override fun onFailure(call: Call<DosenResponse>, t: Throwable) {
                Log.e(TAG, "onFailure: ${t.message.toString()}")
            }
        })
        return results
    }

    fun getStatusDosen(): LiveData<List<String>> {
        val results = MutableLiveData<List<String>>()
        ApiConfig.getApiService().getStatusDosen().enqueue(object: Callback<StatusDosenResponse> {
            override fun onResponse(call: Call<StatusDosenResponse>, response: Response<StatusDosenResponse>) {
                if (response.isSuccessful) {
                    results.postValue(response.body()?.data)
                } else {
                    Log.e(TAG, "onFailure Response: ${response.message()}")
                }
            }
            override fun onFailure(call: Call<StatusDosenResponse>, t: Throwable) {
                Log.e(TAG, "onFailure: ${t.message.toString()}")
            }
        })
        return results
    }
}