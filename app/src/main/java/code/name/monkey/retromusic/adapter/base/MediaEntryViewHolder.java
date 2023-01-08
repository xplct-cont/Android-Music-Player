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

package code.name.monkey.retromusic.adapter.base;

import android.graphics.Color;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.card.MaterialCardView;
import com.h6ah4i.android.widget.advrecyclerview.utils.AbstractDraggableSwipeableItemViewHolder;

import code.name.monkey.retromusic.R;

public class MediaEntryViewHolder extends AbstractDraggableSwipeableItemViewHolder
        implements View.OnLongClickListener, View.OnClickListener {

    @Nullable
    public View dragView;

    @Nullable
    public View dummyContainer;

    @Nullable
    public ImageView image;
    @Nullable
    public ImageView artistImage;

    @Nullable
    public ImageView playerImage;

    @Nullable
    public ViewGroup imageContainer;

    @Nullable
    public MaterialCardView imageContainerCard;

    @Nullable
    public TextView imageText;

    @Nullable
    public MaterialCardView imageTextContainer;

    @Nullable
    public View mask;

    @Nullable
    public View menu;

    @Nullable
    public View paletteColorContainer;

    @Nullable
    public ImageButton playSongs;

    @Nullable
    public RecyclerView recyclerView;

    @Nullable
    public TextView text;

    @Nullable
    public TextView time;

    @Nullable
    public TextView title;

    public MediaEntryViewHolder(@NonNull View itemView) {
        super(itemView);
        title = itemView.findViewById(R.id.title);
        text = itemView.findViewById(R.id.text);

        image = itemView.findViewById(R.id.image);
        artistImage = itemView.findViewById(R.id.artistImage);
        playerImage = itemView.findViewById(R.id.player_image);
        time = itemView.findViewById(R.id.time);

        imageText = itemView.findViewById(R.id.imageText);
        imageContainer = itemView.findViewById(R.id.imageContainer);
        imageTextContainer = itemView.findViewById(R.id.imageTextContainer);
        imageContainerCard = itemView.findViewById(R.id.imageContainerCard);

        menu = itemView.findViewById(R.id.menu);
        dragView = itemView.findViewById(R.id.drag_view);
        paletteColorContainer = itemView.findViewById(R.id.paletteColorContainer);
        recyclerView = itemView.findViewById(R.id.recycler_view);
        mask = itemView.findViewById(R.id.mask);
        playSongs = itemView.findViewById(R.id.playSongs);
        dummyContainer = itemView.findViewById(R.id.dummy_view);

        if (imageContainerCard != null) {
            imageContainerCard.setCardBackgroundColor(Color.TRANSPARENT);
        }
        itemView.setOnClickListener(this);
        itemView.setOnLongClickListener(this);
    }

    @Override
    public View getSwipeableContainerView() {
        return null;
    }

    @Override
    public void onClick(View v) {

    }

    @Override
    public boolean onLongClick(View v) {
        return false;
    }

    public void setImageTransitionName(@NonNull String transitionName) {
        if (imageContainerCard != null) {
            imageContainerCard.setTransitionName(transitionName);
        }
        if (image != null) {
            image.setTransitionName(transitionName);
        }
    }
}
