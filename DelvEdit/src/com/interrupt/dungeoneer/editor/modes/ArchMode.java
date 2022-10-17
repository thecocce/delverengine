package com.interrupt.dungeoneer.editor.modes;

import com.badlogic.gdx.math.Vector3;
import com.interrupt.dungeoneer.editor.EditorApplication;
import com.interrupt.dungeoneer.editor.selection.TileSelectionInfo;
import com.interrupt.dungeoneer.tiles.Tile;

public class ArchMode extends CarveMode {
    public ArchMode(EditorApplication inEditor) {
        super(inEditor);
        canCarve = false;
        canExtrude = false;
    }

    @Override
    public void adjustTileHeights(Vector3 dragStart, Vector3 dragOffset, boolean isCeiling) {
        for (TileSelectionInfo info : tileSelection) {
            Tile t = info.tile;
            if (t == null) {
                continue;
            }

            // Get the arch heights
            float widthMod = (info.x + 0.5f - tileSelection.x) / (float)tileSelection.width;
            float heightMod = (info.y + 0.5f - tileSelection.y) / (float)tileSelection.height;

            widthMod = (float)Math.sin(widthMod * Math.PI);
            heightMod = (float)Math.sin(heightMod * Math.PI);

            // Pick our arch direction based on selection differences
            boolean xMode = tileSelection.width > tileSelection.height;
            float pickedArchMod = xMode ? widthMod : heightMod;

            if (!isCeiling) {
                t.floorHeight -= dragOffset.y * pickedArchMod;
            } else if (isCeiling) {
                t.ceilHeight -= dragOffset.y * pickedArchMod;
            }

            t.packHeights();

            if (t.getMinOpenHeight() < 0f) {
                t.compressFloorAndCeiling(true);
            }
        }
    }
}
