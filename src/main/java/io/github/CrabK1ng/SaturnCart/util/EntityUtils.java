package io.github.CrabK1ng.SaturnCart.util;

import com.badlogic.gdx.math.Vector3;
import com.badlogic.gdx.utils.Array;
import finalforeach.cosmicreach.blocks.BlockPosition;
import finalforeach.cosmicreach.entities.Entity;
import finalforeach.cosmicreach.entities.player.Player;
import finalforeach.cosmicreach.world.Zone;

import java.util.List;

public class EntityUtils {
	public static Player getPlayerAbovePos(Vector3 sourcePos, Zone zone) {
		Array<Player> array = zone.getPlayers();
		Vector3 pos = new Vector3((int) Math.floor(sourcePos.x), (int) Math.floor(sourcePos.y + 1), (int) Math.floor(sourcePos.z));

		for (int i = 0; i < array.size; i++) {
			Player player = array.get(i);
			if (player != null) {
				Entity entity = player.getEntity();
				if (entity != null) {
					Vector3 entityPos = entity.getPosition();
					Vector3 newVector3 = new Vector3(new Vector3((int) Math.floor(entityPos.x), (int) Math.floor(entityPos.y), (int) Math.floor(entityPos.z)));
					if (newVector3.equals(pos)){
						return player;
					}
				}
			}
		}
        return null;
    }

	public static boolean isPlayerInArea(Entity playerEntity, List<Vector3> area){
		Vector3 playerPos = playerEntity.getPosition();
		Vector3 pos1 = area.get(0);
		Vector3 pos2 = area.get(1);

		Vector3[] vector3s = Vector3Utils.getMinMaxCorners(pos1, pos2);
		return Vector3Utils.isInsideBox(playerPos, vector3s[0], vector3s[1]);
	}
}
