package com.layanan.jurusan.viewmodel

import android.content.Context
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.layanan.jurusan.data.DataRepository
import com.layanan.jurusan.di.Injection
import com.layanan.jurusan.ui.CharacterViewModel
import com.layanan.jurusan.ui.announcement.AnnouncementViewModel
import com.layanan.jurusan.ui.announcement.ListAnnouncementViewModel
import com.layanan.jurusan.ui.civitas.CivitasViewModel
import com.layanan.jurusan.ui.civitas.DosenViewModel
import com.layanan.jurusan.ui.civitas.MahasiswaViewModel
import com.layanan.jurusan.ui.document.DocumentViewModel
import com.layanan.jurusan.ui.home.HomeViewModel
import com.layanan.jurusan.ui.iku.IkuViewModel
import com.layanan.jurusan.ui.jurusan.JurusanViewModel
import com.layanan.jurusan.ui.login.LoginViewModel
import com.layanan.jurusan.ui.mail.FormSuratViewModel
import com.layanan.jurusan.ui.mail.ListRiwayatSuratViewModel
import com.layanan.jurusan.ui.mail.MailViewModel
import com.layanan.jurusan.ui.mail.RiwayatSuratViewModel
import com.layanan.jurusan.ui.news.DetailNewsViewModel
import com.layanan.jurusan.ui.news.NewsViewModel
import com.layanan.jurusan.ui.notification.NotificationViewModel
import com.layanan.jurusan.ui.prodi.ProdiViewModel
import com.layanan.jurusan.ui.profile.ProfileFragmentViewModel
import com.layanan.jurusan.ui.profile.ProfileViewModel
import com.layanan.jurusan.ui.profile.SignatureViewModel

class ViewModelFactory private constructor(private val dataRepository: DataRepository)
    : ViewModelProvider.NewInstanceFactory() {

    companion object {
        @Volatile
        private var instance: ViewModelFactory? = null

        fun getInstance(context: Context): ViewModelFactory =
                instance ?: synchronized(this) {
                    ViewModelFactory(Injection.provideRepository(context)).apply { instance = this }
                }
    }

    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return when {
            modelClass.isAssignableFrom(HomeViewModel::class.java) -> {
                HomeViewModel(dataRepository) as T
            }
            modelClass.isAssignableFrom(ProfileFragmentViewModel::class.java) -> {
                ProfileFragmentViewModel(dataRepository) as T
            }
            modelClass.isAssignableFrom(ProfileViewModel::class.java) -> {
                ProfileViewModel(dataRepository) as T
            }
            modelClass.isAssignableFrom(LoginViewModel::class.java) -> {
                LoginViewModel(dataRepository) as T
            }
            modelClass.isAssignableFrom(NewsViewModel::class.java) -> {
                NewsViewModel(dataRepository) as T
            }
            modelClass.isAssignableFrom(AnnouncementViewModel::class.java) -> {
                AnnouncementViewModel(dataRepository) as T
            }
            modelClass.isAssignableFrom(DocumentViewModel::class.java) -> {
                DocumentViewModel(dataRepository) as T
            }
            modelClass.isAssignableFrom(DetailNewsViewModel::class.java) -> {
                DetailNewsViewModel(dataRepository) as T
            }
            modelClass.isAssignableFrom(CharacterViewModel::class.java) -> {
                CharacterViewModel(dataRepository) as T
            }
            modelClass.isAssignableFrom(AnnouncementViewModel::class.java) -> {
                AnnouncementViewModel(dataRepository) as T
            }
            modelClass.isAssignableFrom(ListAnnouncementViewModel::class.java) -> {
                ListAnnouncementViewModel(dataRepository) as T
            }
            modelClass.isAssignableFrom(JurusanViewModel::class.java) -> {
                JurusanViewModel(dataRepository) as T
            }
            modelClass.isAssignableFrom(ProdiViewModel::class.java) -> {
                ProdiViewModel(dataRepository) as T
            }
            modelClass.isAssignableFrom(SignatureViewModel::class.java) -> {
                SignatureViewModel(dataRepository) as T
            }
            modelClass.isAssignableFrom(NotificationViewModel::class.java) -> {
                NotificationViewModel(dataRepository) as T
            }
            modelClass.isAssignableFrom(IkuViewModel::class.java) -> {
                IkuViewModel(dataRepository) as T
            }
            modelClass.isAssignableFrom(MailViewModel::class.java) -> {
                MailViewModel(dataRepository) as T
            }
            modelClass.isAssignableFrom(FormSuratViewModel::class.java) -> {
                FormSuratViewModel(dataRepository) as T
            }
            modelClass.isAssignableFrom(RiwayatSuratViewModel::class.java) -> {
                RiwayatSuratViewModel(dataRepository) as T
            }
            modelClass.isAssignableFrom(ListRiwayatSuratViewModel::class.java) -> {
                ListRiwayatSuratViewModel(dataRepository) as T
            }
            modelClass.isAssignableFrom(CivitasViewModel::class.java) -> {
                CivitasViewModel(dataRepository) as T
            }
            modelClass.isAssignableFrom(MahasiswaViewModel::class.java) -> {
                MahasiswaViewModel(dataRepository) as T
            }
            modelClass.isAssignableFrom(DosenViewModel::class.java) -> {
                DosenViewModel(dataRepository) as T
            }
            else -> throw Throwable("Unknown ViewModel class: " + modelClass.name)
        }

    }
}