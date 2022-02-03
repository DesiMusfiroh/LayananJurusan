package com.layanan.jurusan.data

import android.util.Log
import androidx.lifecycle.LiveData
import com.layanan.jurusan.data.model.AnnouncementModel
import com.layanan.jurusan.data.model.NewsModel
import com.layanan.jurusan.data.model.UserModel
import com.layanan.jurusan.data.remote.RemoteDataSource
import com.layanan.jurusan.data.remote.response.SaveFcmTokenResponse
import com.layanan.jurusan.data.remote.response.login.LoginDataResponse
import com.loopj.android.http.RequestParams
import okhttp3.MultipartBody

class DataRepository private constructor(
        private val remoteDataSource: RemoteDataSource,
) {
    companion object {
        @Volatile
        private var instance: DataRepository? = null

        fun getInstance(remoteData: RemoteDataSource): DataRepository =
                instance ?: synchronized(this) {
                    instance ?: DataRepository(remoteData).apply {
                        instance = this
                    }
                }
    }
    fun getLatestNews(): LiveData<List<NewsModel>> = remoteDataSource.getLatestNews()

    fun getLatestAnnouncement(): LiveData<List<AnnouncementModel>> = remoteDataSource.getLatestAnnouncement()

    fun getTestNews() = remoteDataSource.getListNews()

    fun getListNews() = remoteDataSource.getListNews()

    fun getListAnnouncement() = remoteDataSource.getListAnnouncement()

    fun login(username: String,password: String): LiveData<LoginDataResponse> = remoteDataSource.getUserLogin(username,password)

    fun getDetailNews(id: Int): LiveData<NewsModel> = remoteDataSource.getDetailNews(id)

    fun listCharacter() = remoteDataSource.listCharacter()

    fun getAnnouncement(id: Int): LiveData<AnnouncementModel> = remoteDataSource.getAnnouncement(id)

    fun saveFcmToken(fcmToken: String, jwtToken: String): LiveData<SaveFcmTokenResponse> = remoteDataSource.saveFcmToken(fcmToken,jwtToken)

    fun getSearchNews(search: String) = remoteDataSource.getSearchNews(search)

    fun getProfileJurusan() = remoteDataSource.getProfileJurusan()

    fun getUserProfile(jwtToken: String): LiveData<UserModel>{
        Log.d("JwtRepository",jwtToken)
        return remoteDataSource.getUserProfile(jwtToken)
    }

    fun getProfileProdi(name: String) = remoteDataSource.getProfileProdi(name)

    fun uploadSignature(image: MultipartBody.Part, jwtToken: String) = remoteDataSource.uploadSignature(image,jwtToken)

    fun getIku1(year: String) = remoteDataSource.getIku1(year)
    fun getIku2(year: String) = remoteDataSource.getIku2(year)
    fun getIku3(year: String) = remoteDataSource.getIku3(year)
    fun getIku4(year: String) = remoteDataSource.getIku4(year)
    fun getIku5(year: String) = remoteDataSource.getIku5(year)
    fun getIku6(year: String) = remoteDataSource.getIku6(year)
    fun getIku7(year: String) = remoteDataSource.getIku7(year)
    fun getIku8(year: String) = remoteDataSource.getIku8(year)

    fun getJenisSurat(tipe: String) = remoteDataSource.getJenisSurat(tipe)
    fun getKeywordSurat(jenisSuratId: Int)= remoteDataSource.getKeywordSurat(jenisSuratId)
    fun storePermohonanSurat(params: RequestParams, jwtToken: String): LiveData<String>{
        return remoteDataSource.storePermohonanSurat(params,jwtToken)
    }
    fun getRiwayatSurat(jwtToken: String) = remoteDataSource.getRiwayatSurat(jwtToken)

    fun showRiwayatSurat(jwtToken: String, id: Int) = remoteDataSource.showRiwayatSurat(jwtToken,id)

    fun logout(jwtToken: String) = remoteDataSource.logout(jwtToken)

    fun getAngkatan() = remoteDataSource.getAngkatan()
    fun getMahasiswa(prodi: String, angkatan: String, status: String) = remoteDataSource.getMahasiswa(prodi, angkatan, status)
}