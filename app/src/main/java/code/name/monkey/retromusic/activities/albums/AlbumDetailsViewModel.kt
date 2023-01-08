package code.name.monkey.retromusic.activities.albums

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import code.name.monkey.retromusic.interfaces.MusicServiceEventListener
import code.name.monkey.retromusic.model.Album
import code.name.monkey.retromusic.model.Artist
import code.name.monkey.retromusic.providers.RepositoryImpl
import code.name.monkey.retromusic.rest.model.LastFmAlbum
import kotlinx.coroutines.Deferred
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.async
import kotlinx.coroutines.launch

class AlbumDetailsViewModel(
    application: Application,
    private val albumId: Int
) : AndroidViewModel(application), MusicServiceEventListener {
    private val _repository = RepositoryImpl(application.applicationContext)
    private val _album = MutableLiveData<Album>()
    private val _artist = MutableLiveData<Artist>()
    private val _lastFmAlbum = MutableLiveData<LastFmAlbum>()

    fun getAlbum(): LiveData<Album> = _album
    fun getArtist(): LiveData<Artist> = _artist
    fun getAlbumInfo(): LiveData<LastFmAlbum> = _lastFmAlbum

    init {
        loadAlbumDetails()
    }

    private fun loadAlbumDetails() = viewModelScope.launch {
        val album = loadAlbumAsync.await() ?: throw NullPointerException("Album couldn't found")
        _album.postValue(album)
    }

    fun loadAlbumInfo(album: Album) = viewModelScope.launch(Dispatchers.IO) {
        val lastFmAlbum = _repository.albumInfo(album.artistName ?: "-", album.title ?: "-")
        _lastFmAlbum.postValue(lastFmAlbum)
    }

    fun loadArtist(artistId: Int) = viewModelScope.launch(Dispatchers.IO) {
        val artist = _repository.artistById(artistId)
        _artist.postValue(artist)
    }

    private val loadAlbumAsync: Deferred<Album?>
        get() = viewModelScope.async(Dispatchers.IO) {
            _repository.albumById(albumId)
        }

    override fun onMediaStoreChanged() {
        loadAlbumDetails()
    }

    override fun onServiceConnected() {}
    override fun onServiceDisconnected() {}
    override fun onQueueChanged() {}
    override fun onPlayingMetaChanged() {}
    override fun onPlayStateChanged() {}
    override fun onRepeatModeChanged() {}
    override fun onShuffleModeChanged() {}
}