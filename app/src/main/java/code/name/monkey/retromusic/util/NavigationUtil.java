/*
 * Copyright (c) 2019 Hemanth Savarala.
 *
 * Licensed under the GNU General Public License v3
 *
 * This is free software: you can redistribute it and/or modify it under
 * the terms of the GNU General Public License as published by
 *  the Free Software Foundation either version 3 of the License, or (at your option) any later version.
 *
 * This software is distributed in the hope that it will be useful, but WITHOUT ANY WARRANTY;
 * without even the implied warranty of MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.
 * See the GNU General Public License for more details.
 */

package code.name.monkey.retromusic.util;

import android.app.Activity;
import android.app.ActivityOptions;
import android.content.ActivityNotFoundException;
import android.content.Context;
import android.content.Intent;
import android.media.audiofx.AudioEffect;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.core.app.ActivityCompat;

import org.jetbrains.annotations.NotNull;

import code.name.monkey.retromusic.R;
import code.name.monkey.retromusic.activities.AboutActivity;
import code.name.monkey.retromusic.activities.albums.AlbumDetailsActivity;
import code.name.monkey.retromusic.activities.artists.ArtistDetailActivity;
import code.name.monkey.retromusic.activities.DriveModeActivity;
import code.name.monkey.retromusic.activities.GenreDetailsActivity;
import code.name.monkey.retromusic.activities.LicenseActivity;
import code.name.monkey.retromusic.activities.LyricsActivity;
import code.name.monkey.retromusic.activities.PlayingQueueActivity;
import code.name.monkey.retromusic.activities.PlaylistDetailActivity;
import code.name.monkey.retromusic.activities.PurchaseActivity;
import code.name.monkey.retromusic.activities.SearchActivity;
import code.name.monkey.retromusic.activities.SettingsActivity;
import code.name.monkey.retromusic.activities.SupportDevelopmentActivity;
import code.name.monkey.retromusic.activities.UserInfoActivity;
import code.name.monkey.retromusic.activities.WhatsNewActivity;
import code.name.monkey.retromusic.activities.bugreport.BugReportActivity;
import code.name.monkey.retromusic.helper.MusicPlayerRemote;
import code.name.monkey.retromusic.model.Genre;
import code.name.monkey.retromusic.model.Playlist;

import static code.name.monkey.retromusic.Constants.RATE_ON_GOOGLE_PLAY;
import static code.name.monkey.retromusic.util.RetroUtil.openUrl;


public class NavigationUtil {

    public static void bugReport(@NonNull Activity activity) {
        ActivityCompat.startActivity(activity, new Intent(activity, BugReportActivity.class), null);
    }

    public static void goToAbout(@NonNull Activity activity) {
        ActivityCompat.startActivity(activity, new Intent(activity, AboutActivity.class), null);
    }

    public static void goToAlbum(@NonNull Activity activity, int albumId) {
        Intent intent = new Intent(activity, AlbumDetailsActivity.class);
        intent.putExtra(AlbumDetailsActivity.EXTRA_ALBUM_ID, albumId);
        ActivityCompat.startActivity(activity, intent, null);
    }

    public static void goToAlbumOptions(@NonNull Activity activity,
                                        int albumId,
                                        @NonNull ActivityOptions options) {
        Intent intent = new Intent(activity, AlbumDetailsActivity.class);
        intent.putExtra(AlbumDetailsActivity.EXTRA_ALBUM_ID, albumId);
        ActivityCompat.startActivity(activity, intent, options.toBundle());
    }

    public static void goToArtist(@NonNull Activity activity, int i) {
        Intent intent = new Intent(activity, ArtistDetailActivity.class);
        intent.putExtra(ArtistDetailActivity.EXTRA_ARTIST_ID, i);
        ActivityCompat.startActivity(activity, intent, null);
    }

    public static void goToArtistOptions(@NotNull Activity activity,
                                         int artistId,
                                         @NonNull ActivityOptions options) {

        Intent intent = new Intent(activity, ArtistDetailActivity.class);
        intent.putExtra(ArtistDetailActivity.EXTRA_ARTIST_ID, artistId);
        ActivityCompat.startActivity(activity, intent, options.toBundle());
    }

    public static void goToGenre(@NonNull Activity activity, @NonNull Genre genre) {
        Intent intent = new Intent(activity, GenreDetailsActivity.class);
        intent.putExtra(GenreDetailsActivity.EXTRA_GENRE_ID, genre);
        ActivityCompat.startActivity(activity, intent, null);
    }

    public static void goToLyrics(@NonNull Activity activity) {
        Intent intent = new Intent(activity, LyricsActivity.class);
        ActivityCompat.startActivity(activity, intent, null);
    }

    public static void goToOpenSource(@NonNull Activity activity) {
        ActivityCompat.startActivity(activity, new Intent(activity, LicenseActivity.class), null);
    }

    public static void goToPlayStore(@NonNull Activity activity) {
        openUrl(activity, RATE_ON_GOOGLE_PLAY);
    }

    public static void goToPlayingQueue(@NonNull Activity activity) {
        Intent intent = new Intent(activity, PlayingQueueActivity.class);
        ActivityCompat.startActivity(activity, intent, null);
    }

    public static void goToPlaylistNew(@NonNull Activity activity, @NonNull Playlist playlist) {
        Intent intent = new Intent(activity, PlaylistDetailActivity.class);
        intent.putExtra(PlaylistDetailActivity.Companion.getEXTRA_PLAYLIST(), playlist);
        ActivityCompat.startActivity(activity, intent, null);
    }

    public static void goToProVersion(@NonNull Context context) {
        ActivityCompat.startActivity(context, new Intent(context, PurchaseActivity.class), null);
    }

    public static void goToSearch(@NonNull Activity activity,
                                  @NonNull ActivityOptions activityOptions) {
        ActivityCompat.startActivity(activity, new Intent(activity, SearchActivity.class),
                activityOptions.toBundle());
    }

    public static void goToSearch(@NonNull Activity activity) {
        ActivityCompat.startActivity(activity, new Intent(activity, SearchActivity.class),
                null);
    }

    public static void goToSearch(@NonNull Activity activity, boolean isMicOpen,
                                  @NonNull ActivityOptions activityOptions) {
        ActivityCompat.startActivity(activity, new Intent(activity, SearchActivity.class)
                        .putExtra(SearchActivity.EXTRA_SHOW_MIC, isMicOpen),
                activityOptions.toBundle());
    }

    public static void goToSettings(@NonNull Activity activity) {
        ActivityCompat.startActivity(activity, new Intent(activity, SettingsActivity.class), null);
    }

    public static void goToSupportDevelopment(@NonNull Activity activity) {
        ActivityCompat.startActivity(activity, new Intent(activity, SupportDevelopmentActivity.class), null);
    }

    public static void goToUserInfo(@NonNull Activity activity,
                                    @NonNull ActivityOptions activityOptions) {
        ActivityCompat.startActivity(activity, new Intent(activity, UserInfoActivity.class),
                activityOptions.toBundle());
    }

    public static void gotoDriveMode(@NotNull final Activity activity) {
        ActivityCompat.startActivity(activity, new Intent(activity, DriveModeActivity.class), null);
    }

    public static void gotoWhatNews(@NonNull Activity activity) {
        ActivityCompat.startActivity(activity, new Intent(activity, WhatsNewActivity.class), null);
    }

    public static void openEqualizer(@NonNull final Activity activity) {
        stockEqalizer(activity);
    }

    private static void stockEqalizer(@NonNull Activity activity) {
        final int sessionId = MusicPlayerRemote.INSTANCE.getAudioSessionId();
        if (sessionId == AudioEffect.ERROR_BAD_VALUE) {
            Toast.makeText(activity, activity.getResources().getString(R.string.no_audio_ID),
                    Toast.LENGTH_LONG).show();
        } else {
            try {
                final Intent effects = new Intent(AudioEffect.ACTION_DISPLAY_AUDIO_EFFECT_CONTROL_PANEL);
                effects.putExtra(AudioEffect.EXTRA_AUDIO_SESSION, sessionId);
                effects.putExtra(AudioEffect.EXTRA_CONTENT_TYPE, AudioEffect.CONTENT_TYPE_MUSIC);
                activity.startActivityForResult(effects, 0);
            } catch (@NonNull final ActivityNotFoundException notFound) {
                Toast.makeText(activity, activity.getResources().getString(R.string.no_equalizer),
                        Toast.LENGTH_SHORT).show();
            }
        }
    }


}
