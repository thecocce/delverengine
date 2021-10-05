package com.interrupt.dungeoneer.editor.gizmos;

import com.badlogic.gdx.math.Vector3;
import com.interrupt.dungeoneer.editor.ui.Handles;
import com.interrupt.dungeoneer.entities.areas.Area;

@GizmoFor(target = Area.class)
public class AreaGizmo extends EntityGizmo {
    public AreaGizmo(Area entity) {
        super(entity);
    }

    @Override
    public void draw() {
        super.draw();

        Area area = (Area) entity;
        Vector3 boundingBoxCenter = new Vector3(area.x, area.z - 0.5f + (area.collision.z / 2), area.y);
        Vector3 boundingBoxSize = new Vector3(area.collision.x, area.collision.z / 2, area.collision.y);
        Handles.drawWireCube(boundingBoxCenter, boundingBoxSize);
    }
}
